package com.example.spartanguide.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.*;

import java.util.List;

@Entity
@Table(name = "guides")
@PrimaryKeyJoinColumn(name = "guide_id")
public class Guide extends User {

	@Column(columnDefinition = "TEXT")
	private String bio;

	@OneToMany(mappedBy = "guide", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnoreProperties({ "guide"})
	private List<Tour> tours;

	public Guide() {
	}

	public Guide(Long guideId, String name, String email, String password, UserRole role, UserStatus status,
			java.time.LocalDateTime createdAt, java.time.LocalDateTime updatedAt, String bio, List<Tour> tours) {
		super(guideId, name, email, password, role, status, createdAt, updatedAt);
		this.bio = bio;
		this.tours = tours;
	}

	@Transient
	@JsonProperty("guideId")
	public Long getGuideId() {
		return super.getUserId();
	}

	@JsonProperty("guideId")
	public void setGuideId(Long guideId) {
		super.setUserId(guideId);
	}

	@Override
	@JsonIgnore
	public Long getUserId() {
		return super.getUserId();
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public List<Tour> getTours() {
		return tours;
	}

	public void setTours(List<Tour> tours) {
		this.tours = tours;
	}
}
