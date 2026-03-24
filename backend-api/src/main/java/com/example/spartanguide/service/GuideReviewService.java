package com.example.spartanguide.service;

import com.example.spartanguide.entity.GuideReview;
import com.example.spartanguide.repository.GuideReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GuideReviewService {

	@Autowired
	private GuideReviewRepository guideReviewRepository;

	public GuideReview createGuideReview(GuideReview review) {
		return guideReviewRepository.save(review);
	}

	public Optional<GuideReview> getGuideReviewById(Long id) {
		return guideReviewRepository.findById(id);
	}

	public List<GuideReview> getAllGuideReviews() {
		return guideReviewRepository.findAll();
	}

	public List<GuideReview> getReviewsByGuideId(Long guideId) {
		return guideReviewRepository.findByGuideUserId(guideId);
	}

	public List<GuideReview> getReviewsByReviewerId(Long studentId) {
		return guideReviewRepository.findByReviewerUserId(studentId);
	}

	public GuideReview updateGuideReview(Long id, GuideReview reviewDetails) {
		return guideReviewRepository.findById(id).map(review -> {
			if (reviewDetails.getReviewer() != null) {
				review.setReviewer(reviewDetails.getReviewer());
			}
			if (reviewDetails.getGuide() != null) {
				review.setGuide(reviewDetails.getGuide());
			}
			if (reviewDetails.getRating() != null) {
				review.setRating(reviewDetails.getRating());
			}
			if (reviewDetails.getComment() != null) {
				review.setComment(reviewDetails.getComment());
			}
			return guideReviewRepository.save(review);
		}).orElseThrow(() -> new RuntimeException("Guide review not found"));
	}

	public void deleteGuideReview(Long id) {
		guideReviewRepository.deleteById(id);
	}
}