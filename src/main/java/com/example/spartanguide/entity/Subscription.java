package com.example.spartanguide.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "subscriptions")
public class Subscription {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long subscriptionId;

	@ManyToMany
	@JoinTable(name = "student_subscriptions", joinColumns = @JoinColumn(name = "subscription_id"), inverseJoinColumns = @JoinColumn(name = "student_id"))
	@JsonIgnoreProperties({ "subscriptions" })
	private List<Student> students;

	@Column(nullable = false)
	private String planName;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private SubscriptionStatus status;

	@Column(nullable = false)
	private LocalDate startDate;

	private LocalDate endDate;

	@Column(nullable = false)
	private boolean autoRenew;

	@Column(nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@Column(nullable = false)
	private LocalDateTime updatedAt;

	public enum SubscriptionStatus {
		ACTIVE,
		CANCELLED,
		COMPLETED
	}

	@PrePersist
	protected void onCreate() {
		createdAt = LocalDateTime.now();
		updatedAt = LocalDateTime.now();
	}

	@PreUpdate
	protected void onUpdate() {
		updatedAt = LocalDateTime.now();
	}

	public Subscription() {
	}

	public Subscription(Long subscriptionId, List<Student> students, String planName, SubscriptionStatus status,
			LocalDate startDate, LocalDate endDate, boolean autoRenew, LocalDateTime createdAt,
			LocalDateTime updatedAt) {
		this.subscriptionId = subscriptionId;
		this.students = students;
		this.planName = planName;
		this.status = status;
		this.startDate = startDate;
		this.endDate = endDate;
		this.autoRenew = autoRenew;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Long getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(Long subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public SubscriptionStatus getStatus() {
		return status;
	}

	public void setStatus(SubscriptionStatus status) {
		this.status = status;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public boolean isAutoRenew() {
		return autoRenew;
	}

	public void setAutoRenew(boolean autoRenew) {
		this.autoRenew = autoRenew;
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