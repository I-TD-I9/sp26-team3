package com.example.spartanguide.controller;

import com.example.spartanguide.entity.StudentReview;
import com.example.spartanguide.service.StudentReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/student-reviews")
public class StudentReviewController {

	@Autowired
	private StudentReviewService studentReviewService;

	@PostMapping
	public ResponseEntity<StudentReview> createStudentReview(@RequestBody StudentReview review) {
		StudentReview createdReview = studentReviewService.createStudentReview(review);
		return new ResponseEntity<>(createdReview, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<StudentReview>> getAllStudentReviews() {
		List<StudentReview> reviews = studentReviewService.getAllStudentReviews();
		return new ResponseEntity<>(reviews, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<StudentReview> getStudentReviewById(@PathVariable Long id) {
		Optional<StudentReview> review = studentReviewService.getStudentReviewById(id);
		return review.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
			.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@GetMapping("/student/{studentId}")
	public ResponseEntity<List<StudentReview>> getReviewsByStudentId(@PathVariable Long studentId) {
		List<StudentReview> reviews = studentReviewService.getReviewsByStudentId(studentId);
		return new ResponseEntity<>(reviews, HttpStatus.OK);
	}

	@GetMapping("/guide/{guideId}")
	public ResponseEntity<List<StudentReview>> getReviewsByGuideId(@PathVariable Long guideId) {
		List<StudentReview> reviews = studentReviewService.getReviewsByReviewerId(guideId);
		return new ResponseEntity<>(reviews, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<StudentReview> updateStudentReview(@PathVariable Long id,
			@RequestBody StudentReview reviewDetails) {
		try {
			StudentReview updatedReview = studentReviewService.updateStudentReview(id, reviewDetails);
			return new ResponseEntity<>(updatedReview, HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteStudentReview(@PathVariable Long id) {
		studentReviewService.deleteStudentReview(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}