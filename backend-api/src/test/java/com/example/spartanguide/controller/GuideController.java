package com.example.spartanguide.controller;

import com.example.spartanguide.entity.Guide;
import com.example.spartanguide.service.GuideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/guides")
public class GuideController {

	@Autowired
	private GuideService guideService;

	@PostMapping
	public ResponseEntity<Guide> createGuide(@RequestBody Guide guide) {
		Guide createdGuide = guideService.createGuide(guide);
		return new ResponseEntity<>(createdGuide, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<Guide>> getAllGuides() {
		List<Guide> guides = guideService.getAllGuides();
		return new ResponseEntity<>(guides, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Guide> getGuideById(@PathVariable Long id) {
		Optional<Guide> guide = guideService.getGuideById(id);
		return guide.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
			.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@GetMapping("/email/{email}")
	public ResponseEntity<Guide> getGuideByEmail(@PathVariable String email) {
		Guide guide = guideService.getGuideByEmail(email);
		return guide != null ? new ResponseEntity<>(guide, HttpStatus.OK)
			: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Guide> updateGuide(@PathVariable Long id, @RequestBody Guide guideDetails) {
		try {
			Guide updatedGuide = guideService.updateGuide(id, guideDetails);
			return new ResponseEntity<>(updatedGuide, HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteGuide(@PathVariable Long id) {
		guideService.deleteGuide(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
