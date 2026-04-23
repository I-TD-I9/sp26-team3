package com.example.spartanguide.service;

import com.example.spartanguide.entity.Guide;
import com.example.spartanguide.repository.GuideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
			if (guideDetails.getName() != null) {
				guide.setName(guideDetails.getName());
			}
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

	public void saveProfilePicture(Guide guide, MultipartFile profilePicture) {
		if (profilePicture == null || profilePicture.isEmpty()) {
			return;
		}
		String originalFileName = profilePicture.getOriginalFilename();
		try {
			if (originalFileName != null && originalFileName.contains(".")) {
				String fileExtension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
				String fileName = guide.getGuideId() + "." + fileExtension;
				String uploadDir = new ClassPathResource("static/profile-pictures/avatar.png").getFile().getParent() + "/";
				Path filePath = Paths.get(uploadDir + fileName);
				InputStream inputStream = profilePicture.getInputStream();
				Files.createDirectories(Paths.get(uploadDir));
				Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
				guide.setProfilePicturePath(fileName);
				guideRepository.save(guide);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
