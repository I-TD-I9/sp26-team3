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
	@JsonIgnoreProperties({ "students", "createdAt", "updatedAt" })
	private List<Subscription> subscriptions;

	public Student() {
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
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

	public List<Subscription> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(List<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}

	@Override
	@JsonIgnore
	public Long getUserId() {
		return super.getUserId();
	}
}
