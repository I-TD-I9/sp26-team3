package com.example.spartanguide.service;

import com.example.spartanguide.entity.Student;
import com.example.spartanguide.entity.Subscription;
import com.example.spartanguide.entity.Subscription.SubscriptionStatus;
import com.example.spartanguide.repository.StudentRepository;
import com.example.spartanguide.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionService {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private SubscriptionRepository subscriptionRepository;

	public Subscription createSubscription(Subscription subscription) {
		prepareStudents(subscription);
		return subscriptionRepository.save(subscription);
	}

	public Optional<Subscription> getSubscriptionById(Long id) {
		return subscriptionRepository.findById(id);
	}

	public List<Subscription> getAllSubscriptions() {
		return subscriptionRepository.findAll();
	}

	public List<Subscription> getSubscriptionsByStudentId(Long studentId) {
		return subscriptionRepository.findByStudentsUserId(studentId);
	}

	public List<Subscription> getSubscriptionsByStatus(SubscriptionStatus status) {
		return subscriptionRepository.findByStatus(status);
	}

	public Subscription updateSubscription(Long id, Subscription subscriptionDetails) {
		return subscriptionRepository.findById(id).map(subscription -> {
			if (subscriptionDetails.getStudents() != null && !subscriptionDetails.getStudents().isEmpty()) {
				subscription.setStudents(resolveStudents(subscriptionDetails));
			}
			if (subscriptionDetails.getStatus() != null) {
				subscription.setStatus(subscriptionDetails.getStatus());
			}
			if (subscriptionDetails.getStartDate() != null) {
				subscription.setStartDate(subscriptionDetails.getStartDate());
			}
			if (subscriptionDetails.getEndDate() != null) {
				subscription.setEndDate(subscriptionDetails.getEndDate());
			}
			return subscriptionRepository.save(subscription);
		}).orElseThrow(() -> new RuntimeException("Subscription not found"));
	}

	public void deleteSubscription(Long id) {
		subscriptionRepository.deleteById(id);
	}

	private void prepareStudents(Subscription subscription) {
		subscription.setStudents(resolveStudents(subscription));
	}

	private List<Student> resolveStudents(Subscription subscription) {
		if (subscription.getStudents() == null || subscription.getStudents().isEmpty()) {
			throw new RuntimeException("At least one student is required");
		}

		return subscription.getStudents().stream().map(student -> {
			if (student == null || student.getStudentId() == null) {
				throw new RuntimeException("Student not found");
			}

			return studentRepository.findById(student.getStudentId())
				.orElseThrow(() -> new RuntimeException("Student not found"));
		}).toList();
	}
}