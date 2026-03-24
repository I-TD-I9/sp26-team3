package com.example.spartanguide.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.spartanguide.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {
	@Query(value = "SELECT b.* FROM bookings b WHERE b.student_id = :studentId", nativeQuery = true)
	List<Booking> findByStudentUserId(Long studentId);

	@Query(value = "SELECT b.* FROM bookings b WHERE b.tour_id = :tourId", nativeQuery = true)
	List<Booking> findByTourTourId(Long tourId);
}