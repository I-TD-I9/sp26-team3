package com.example.spartanguide.entity;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

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
