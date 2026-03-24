package com.example.spartanguide.controller;

import com.example.spartanguide.entity.Booking;
import com.example.spartanguide.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

	@Autowired
	private BookingService bookingService;

	@PostMapping
	public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
		Booking createdBooking = bookingService.createBooking(booking);
		return new ResponseEntity<>(createdBooking, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<Booking>> getAllBookings() {
		List<Booking> bookings = bookingService.getAllBookings();
		return new ResponseEntity<>(bookings, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Booking> getBookingById(@PathVariable Long id) {
		Optional<Booking> booking = bookingService.getBookingById(id);
		return booking.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
			.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@GetMapping("/student/{studentId}")
	public ResponseEntity<List<Booking>> getBookingsByStudentId(@PathVariable Long studentId) {
		List<Booking> bookings = bookingService.getBookingsByStudentId(studentId);
		return new ResponseEntity<>(bookings, HttpStatus.OK);
	}

	@GetMapping("/tour/{tourId}")
	public ResponseEntity<List<Booking>> getBookingsByTourId(@PathVariable Long tourId) {
		List<Booking> bookings = bookingService.getBookingsByTourId(tourId);
		return new ResponseEntity<>(bookings, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Booking> updateBooking(@PathVariable Long id, @RequestBody Booking bookingDetails) {
		try {
			Booking updatedBooking = bookingService.updateBooking(id, bookingDetails);
			return new ResponseEntity<>(updatedBooking, HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
		bookingService.deleteBooking(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}