package com.example.spartanguide.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.spartanguide.entity.Tour;

public interface TourRepository extends JpaRepository<Tour, Long> {
	@Query(value = "SELECT t.* FROM tours t WHERE t.guide_id = :guideId", nativeQuery = true)
	List<Tour> findByGuideUserId(Long guideId);
}