package com.example.spartanguide.service;

import com.example.spartanguide.entity.StudentReview;
import com.example.spartanguide.repository.StudentReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentReviewService {

	@Autowired
	private StudentReviewRepository studentReviewRepository;

	public StudentReview createStudentReview(StudentReview review) {
		return studentReviewRepository.save(review);
	}

	public Optional<StudentReview> getStudentReviewById(Long id) {
		return studentReviewRepository.findById(id);
	}

	public List<StudentReview> getAllStudentReviews() {
		return studentReviewRepository.findAll();
	}

	public List<StudentReview> getReviewsByStudentId(Long studentId) {
		return studentReviewRepository.findByStudentUserId(studentId);
	}

	public List<StudentReview> getReviewsByReviewerId(Long guideId) {
		return studentReviewRepository.findByReviewerUserId(guideId);
	}

	public StudentReview updateStudentReview(Long id, StudentReview reviewDetails) {
		return studentReviewRepository.findById(id).map(review -> {
			if (reviewDetails.getReviewer() != null) {
				review.setReviewer(reviewDetails.getReviewer());
			}
			if (reviewDetails.getStudent() != null) {
				review.setStudent(reviewDetails.getStudent());
			}
			if (reviewDetails.getRating() != null) {
				review.setRating(reviewDetails.getRating());
			}
			if (reviewDetails.getComment() != null) {
				review.setComment(reviewDetails.getComment());
			}
			return studentReviewRepository.save(review);
		}).orElseThrow(() -> new RuntimeException("Student review not found"));
	}

	public void deleteStudentReview(Long id) {
		studentReviewRepository.deleteById(id);
	}
}