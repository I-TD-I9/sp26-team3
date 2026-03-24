package com.example.spartanguide.controller;

import com.example.spartanguide.entity.Tour;
import com.example.spartanguide.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tours")
public class TourController {

	@Autowired
	private TourService tourService;

	@PostMapping
	public ResponseEntity<Tour> createTour(@RequestBody Tour tour) {
		Tour createdTour = tourService.createTour(tour);
		return new ResponseEntity<>(createdTour, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<Tour>> getAllTours() {
		List<Tour> tours = tourService.getAllTours();
		return new ResponseEntity<>(tours, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Tour> getTourById(@PathVariable Long id) {
		Optional<Tour> tour = tourService.getTourById(id);
		return tour.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
			.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Tour> updateTour(@PathVariable Long id, @RequestBody Tour tourDetails) {
		try {
			Tour updatedTour = tourService.updateTour(id, tourDetails);
			return new ResponseEntity<>(updatedTour, HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTour(@PathVariable Long id) {
		tourService.deleteTour(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}