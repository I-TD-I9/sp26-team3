package com.example.spartanguide.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.spartanguide.entity.GuideReview;

public interface GuideReviewRepository extends JpaRepository<GuideReview, Long> {
	@Query(value = "SELECT r.* FROM guide_reviews r WHERE r.guide_id = :guideId", nativeQuery = true)
	List<GuideReview> findByGuideUserId(Long guideId);

	@Query(value = "SELECT r.* FROM guide_reviews r WHERE r.reviewer_student_id = :studentId", nativeQuery = true)
	List<GuideReview> findByReviewerUserId(Long studentId);
}