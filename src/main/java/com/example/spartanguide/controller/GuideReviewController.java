package com.example.spartanguide.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spartanguide.entity.GuideReview;
import com.example.spartanguide.service.GuideReviewService;

@RestController
@RequestMapping("/api/guide-reviews")
public class GuideReviewController {

	@Autowired
	private GuideReviewService guideReviewService;

	@PostMapping
	public ResponseEntity<GuideReview> createGuideReview(@RequestBody GuideReview review) {
		GuideReview createdReview = guideReviewService.createGuideReview(review);
		return new ResponseEntity<>(createdReview, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<GuideReview>> getAllGuideReviews() {
		List<GuideReview> reviews = guideReviewService.getAllGuideReviews();
		return new ResponseEntity<>(reviews, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<GuideReview> getGuideReviewById(@PathVariable Long id) {
		Optional<GuideReview> review = guideReviewService.getGuideReviewById(id);
		return review.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
			.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@GetMapping("/guide/{guideId}")
	public ResponseEntity<List<GuideReview>> getReviewsByGuideId(@PathVariable Long guideId) {
		List<GuideReview> reviews = guideReviewService.getReviewsByGuideId(guideId);
		return new ResponseEntity<>(reviews, HttpStatus.OK);
	}

	@GetMapping("/student/{studentId}")
	public ResponseEntity<List<GuideReview>> getReviewsByStudentId(@PathVariable Long studentId) {
		List<GuideReview> reviews = guideReviewService.getReviewsByReviewerId(studentId);
		return new ResponseEntity<>(reviews, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<GuideReview> updateGuideReview(@PathVariable Long id,
			@RequestBody GuideReview reviewDetails) {
		try {
			GuideReview updatedReview = guideReviewService.updateGuideReview(id, reviewDetails);
			return new ResponseEntity<>(updatedReview, HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteGuideReview(@PathVariable Long id) {
		guideReviewService.deleteGuideReview(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}