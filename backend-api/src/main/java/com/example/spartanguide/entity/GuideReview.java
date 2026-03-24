package com.example.spartanguide.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "guide_reviews")
public class GuideReview {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long reviewId;

	@ManyToOne
	@JoinColumn(name = "reviewer_student_id", nullable = false)
	@JsonIgnoreProperties({ "subscriptions" })
	private Student reviewer;

	@ManyToOne
	@JoinColumn(name = "guide_id", nullable = false)
	@JsonIgnoreProperties({ "tours" })
	private Guide guide;

	@Column(nullable = false)
	private Integer rating;

	@Column(columnDefinition = "TEXT")
	private String comment;

	@Column(nullable = false, updatable = false)
	@JsonIgnore
	private LocalDateTime createdAt;

	@Column(nullable = false)
	@JsonIgnore
	private LocalDateTime updatedAt;

	@PrePersist
	protected void onCreate() {
		createdAt = LocalDateTime.now();
		updatedAt = LocalDateTime.now();
	}

	@PreUpdate
	protected void onUpdate() {
		updatedAt = LocalDateTime.now();
	}

	public GuideReview() {
	}

	public GuideReview(Long reviewId, Student reviewer, Guide guide, Integer rating, String comment,
			LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.reviewId = reviewId;
		this.reviewer = reviewer;
		this.guide = guide;
		this.rating = rating;
		this.comment = comment;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Long getReviewId() {
		return reviewId;
	}

	public void setReviewId(Long reviewId) {
		this.reviewId = reviewId;
	}

	public Student getReviewer() {
		return reviewer;
	}

	public void setReviewer(Student reviewer) {
		this.reviewer = reviewer;
	}

	public Guide getGuide() {
		return guide;
	}

	public void setGuide(Guide guide) {
		this.guide = guide;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

}