package com.example.spartanguide.service;

import com.example.spartanguide.entity.Tour;
import com.example.spartanguide.repository.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TourService {

	@Autowired
	private TourRepository tourRepository;

	public Tour createTour(Tour tour) {
		tour.setTourId(null);
		return tourRepository.save(tour);
	}

	public Optional<Tour> getTourById(Long id) {
		return tourRepository.findById(id);
	}

	public List<Tour> getAllTours() {
		return tourRepository.findAll();
	}

	public List<Tour> getToursByGuideId(Long guideId) {
		return tourRepository.findByGuideUserId(guideId);
	}

	public Tour updateTour(Long id, Tour tourDetails) {
		return tourRepository.findById(id).map(tour -> {
			tour.setGuide(tourDetails.getGuide());
			tour.setTitle(tourDetails.getTitle());
			tour.setDescription(tourDetails.getDescription());
			tour.setLocation(tourDetails.getLocation());
			tour.setPrice(tourDetails.getPrice());
			tour.setCapacity(tourDetails.getCapacity());
			tour.setPublished(tourDetails.isPublished());
			return tourRepository.save(tour);
		}).orElseThrow(() -> new RuntimeException("Tour not found"));
	}

	public void deleteTour(Long id) {
		tourRepository.deleteById(id);
	}
}