package com.example.spartanguide.entity;


import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.*;

import java.util.List;

@Entity
@Table(name = "students")
@PrimaryKeyJoinColumn(name = "student_id")
public class Student extends User {

	@Column(nullable = false)
	private String major;

	@ManyToMany(mappedBy = "students")
	@JsonIgnoreProperties({ "students" })
	private List<Subscription> subscriptions;

	public Student() {
	}

	public Student(Long studentId, String name, String email, String passwordHash, UserRole role, UserStatus status,
			java.time.LocalDateTime createdAt, java.time.LocalDateTime updatedAt, String major, List<Subscription> subscriptions) {
		super(studentId, name, email, passwordHash, role, status, createdAt, updatedAt);
		this.major = major;
		this.subscriptions = subscriptions;
	}

	@Transient
	@JsonProperty("studentId")
	public Long getStudentId() {
		return super.getUserId();
	}

	@JsonProperty("studentId")
	public void setStudentId(Long studentId) {
		super.setUserId(studentId);
	}

	@Override
	@JsonIgnore
	public Long getUserId() {
		return super.getUserId();
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public List<Subscription> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(List<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}
}
