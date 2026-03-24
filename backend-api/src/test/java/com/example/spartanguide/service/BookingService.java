package com.example.spartanguide.service;

import com.example.spartanguide.entity.Booking;
import com.example.spartanguide.entity.Student;
import com.example.spartanguide.entity.Subscription;
import com.example.spartanguide.entity.Tour;
import com.example.spartanguide.entity.Subscription.SubscriptionStatus;
import com.example.spartanguide.repository.BookingRepository;
import com.example.spartanguide.repository.StudentRepository;
import com.example.spartanguide.repository.SubscriptionRepository;
import com.example.spartanguide.repository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private TourRepository tourRepository;

	@Autowired
	private SubscriptionRepository subscriptionRepository;

	public Booking createBooking(Booking booking) {
		prepareBooking(booking);
		return bookingRepository.save(booking);
	}

	public Optional<Booking> getBookingById(Long id) {
		return bookingRepository.findById(id);
	}

	public List<Booking> getAllBookings() {
		return bookingRepository.findAll();
	}

	public List<Booking> getBookingsByStudentId(Long studentId) {
		return bookingRepository.findByStudentUserId(studentId);
	}

	public List<Booking> getBookingsByTourId(Long tourId) {
		return bookingRepository.findByTourTourId(tourId);
	}

	public Booking updateBooking(Long id, Booking bookingDetails) {
		return bookingRepository.findById(id).map(booking -> {
			if (bookingDetails.getStudent() != null && bookingDetails.getStudent().getStudentId() != null) {
				booking.setStudent(resolveStudent(bookingDetails.getStudent().getStudentId()));
			}
			if (bookingDetails.getTour() != null && bookingDetails.getTour().getTourId() != null) {
				booking.setTour(resolveTour(bookingDetails.getTour().getTourId()));
			}
			booking.setPaid(bookingDetails.isPaid());
			applyBookingRules(booking);
			return bookingRepository.save(booking);
		}).orElseThrow(() -> new RuntimeException("Booking not found"));
	}

	public void deleteBooking(Long id) {
		bookingRepository.deleteById(id);
	}

	private void prepareBooking(Booking booking) {
		if (booking.getStudent() == null || booking.getStudent().getStudentId() == null) {
			throw new RuntimeException("Student not found");
		}

		if (booking.getTour() == null || booking.getTour().getTourId() == null) {
			throw new RuntimeException("Tour not found");
		}

		booking.setStudent(resolveStudent(booking.getStudent().getStudentId()));
		booking.setTour(resolveTour(booking.getTour().getTourId()));
		applyBookingRules(booking);
	}

	private void applyBookingRules(Booking booking) {
		BigDecimal price = booking.getTour().getPrice();
		boolean hasActiveSubscription = hasActiveSubscription(booking.getStudent().getStudentId());
		boolean paymentNeeded = price.compareTo(BigDecimal.ZERO) > 0 && !hasActiveSubscription;

		if (hasActiveSubscription) {
			booking.setPaid(true);
			return;
		}

		if (paymentNeeded && !booking.isPaid()) {
			throw new RuntimeException("Payment is required to book this tour");
		}
	}

	private boolean hasActiveSubscription(Long studentId) {
		LocalDate today = LocalDate.now();

		return subscriptionRepository.findByStudentsUserIdAndStatus(studentId, SubscriptionStatus.ACTIVE).stream()
			.anyMatch(subscription -> isActiveOnDate(subscription, today));
	}

	private boolean isActiveOnDate(Subscription subscription, LocalDate date) {
		if (subscription.getStartDate() != null && subscription.getStartDate().isAfter(date)) {
			return false;
		}

		return subscription.getEndDate() == null || !subscription.getEndDate().isBefore(date);
	}

	private Student resolveStudent(Long studentId) {
		return studentRepository.findById(studentId)
			.orElseThrow(() -> new RuntimeException("Student not found"));
	}

	private Tour resolveTour(Long tourId) {
		return tourRepository.findById(tourId)
			.orElseThrow(() -> new RuntimeException("Tour not found"));
	}
}