package com.example.spartanguide.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.*;

@Entity
@Table(name = "bookings")
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bookingId;

	@ManyToOne
	@JoinColumn(name = "student_id", nullable = false)
	@JsonIgnoreProperties({ "subscriptions" })
	private Student student;

	@ManyToOne
	@JoinColumn(name = "tour_id", nullable = false)
	@JsonIgnoreProperties({ "bookings", "guide" })
	private Tour tour;

	@Column(nullable = false)
	private boolean paid;

	public Booking() {
	}

	public Booking(Long bookingId, Student student, Tour tour, boolean paid) {
		this.bookingId = bookingId;
		this.student = student;
		this.tour = tour;
		this.paid = paid;
	}

	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Tour getTour() {
		return tour;
	}

	public void setTour(Tour tour) {
		this.tour = tour;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

}