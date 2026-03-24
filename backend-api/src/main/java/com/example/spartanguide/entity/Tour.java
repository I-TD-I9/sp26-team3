package com.example.spartanguide.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "tours")
public class Tour {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long tourId;

	@ManyToOne
	@JoinColumn(name = "guide_id", nullable = false)
	@JsonIgnoreProperties({ "tours" })
	private Guide guide;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String location;

	@Column(columnDefinition = "TEXT")
	private String description;

	@Column(nullable = false)
	private BigDecimal price;

	@Column(nullable = false)
	private int capacity;

	@Column(nullable = false)
	private boolean published;

	@OneToMany(mappedBy = "tour", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnoreProperties({ "tour"})
	private List<Booking> bookings;

	public Tour() {
	}

	public Long getTourId() {
		return tourId;
	}

	public void setTourId(Long tourId) {
		this.tourId = tourId;
	}

	public Guide getGuide() {
		return guide;
	}

	public void setGuide(Guide guide) {
		this.guide = guide;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}
}