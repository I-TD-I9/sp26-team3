package com.example.spartanguide.service;

import com.example.spartanguide.entity.Student;
import com.example.spartanguide.repository.StudentRepository;
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
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;

	public Student createStudent(Student student) {
		return studentRepository.save(student);
	}

	public Optional<Student> getStudentById(Long id) {
		return studentRepository.findById(id);
	}

	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	public Student updateStudent(Long id, Student studentDetails) {
		return studentRepository.findById(id).map(student -> {
			if (studentDetails.getName() != null) {
				student.setName(studentDetails.getName());
			}
			if (studentDetails.getEmail() != null) {
				student.setEmail(studentDetails.getEmail());
			}
			if (studentDetails.getMajor() != null) {
				student.setMajor(studentDetails.getMajor());
			}
			if (studentDetails.getStatus() != null) {
				student.setStatus(studentDetails.getStatus());
			}
			return studentRepository.save(student);
		}).orElseThrow(() -> new RuntimeException("Student not found"));
	}

	public void deleteStudent(Long id) {
		studentRepository.deleteById(id);
	}

	public Student getStudentByEmail(String email) {
		return studentRepository.findByEmail(email);
	}

	public void saveProfilePicture(Student student, MultipartFile profilePicture) {
		if (profilePicture == null || profilePicture.isEmpty()) {
			return;
		}
		String originalFileName = profilePicture.getOriginalFilename();
		try {
			if (originalFileName != null && originalFileName.contains(".")) {
				String fileExtension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
				String fileName = student.getStudentId() + "." + fileExtension;
				String uploadDir = new ClassPathResource("static/profile-pictures/avatar.png").getFile().getParent() + "/";
				Path filePath = Paths.get(uploadDir + fileName);
				InputStream inputStream = profilePicture.getInputStream();
				Files.createDirectories(Paths.get(uploadDir));
				Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
				student.setProfilePicturePath(fileName);
				studentRepository.save(student);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
