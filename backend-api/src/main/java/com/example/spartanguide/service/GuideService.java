package com.example.spartanguide.service;

import com.example.spartanguide.entity.Guide;
import com.example.spartanguide.repository.GuideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GuideService {

	@Autowired
	private GuideRepository guideRepository;

	public Guide createGuide(Guide guide) {
		return guideRepository.save(guide);
	}

	public Optional<Guide> getGuideById(Long id) {
		return guideRepository.findById(id);
	}

	public List<Guide> getAllGuides() {
		return guideRepository.findAll();
	}

	public Guide updateGuide(Long id, Guide guideDetails) {
		return guideRepository.findById(id).map(guide -> {
			if (guideDetails.getEmail() != null) {
				guide.setEmail(guideDetails.getEmail());
			}
			if (guideDetails.getBio() != null) {
				guide.setBio(guideDetails.getBio());
			}
			if (guideDetails.getStatus() != null) {
				guide.setStatus(guideDetails.getStatus());
			}
			return guideRepository.save(guide);
		}).orElseThrow(() -> new RuntimeException("Guide not found"));
	}

	public void deleteGuide(Long id) {
		guideRepository.deleteById(id);
	}

	public Guide getGuideByEmail(String email) {
		return guideRepository.findByEmail(email);
	}
}
