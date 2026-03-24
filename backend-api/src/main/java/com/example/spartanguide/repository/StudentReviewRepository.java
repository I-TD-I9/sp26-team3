package com.example.spartanguide.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.spartanguide.entity.StudentReview;

public interface StudentReviewRepository extends JpaRepository<StudentReview, Long> {
	@Query(value = "SELECT r.* FROM student_reviews r WHERE r.student_id = :studentId", nativeQuery = true)
	List<StudentReview> findByStudentUserId(Long studentId);

	@Query(value = "SELECT r.* FROM student_reviews r WHERE r.reviewer_guide_id = :guideId", nativeQuery = true)
	List<StudentReview> findByReviewerUserId(Long guideId);
}