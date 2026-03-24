package com.example.spartanguide.controller;

import com.example.spartanguide.entity.Subscription;
import com.example.spartanguide.entity.Subscription.SubscriptionStatus;
import com.example.spartanguide.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

	@Autowired
	private SubscriptionService subscriptionService;

	@PostMapping
	public ResponseEntity<Subscription> createSubscription(@RequestBody Subscription subscription) {
		Subscription createdSubscription = subscriptionService.createSubscription(subscription);
		return new ResponseEntity<>(createdSubscription, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<Subscription>> getAllSubscriptions() {
		List<Subscription> subscriptions = subscriptionService.getAllSubscriptions();
		return new ResponseEntity<>(subscriptions, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Subscription> getSubscriptionById(@PathVariable Long id) {
		Optional<Subscription> subscription = subscriptionService.getSubscriptionById(id);
		return subscription.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
			.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@GetMapping("/student/{studentId}")
	public ResponseEntity<List<Subscription>> getSubscriptionsByStudentId(@PathVariable Long studentId) {
		List<Subscription> subscriptions = subscriptionService.getSubscriptionsByStudentId(studentId);
		return new ResponseEntity<>(subscriptions, HttpStatus.OK);
	}

	@GetMapping("/status/{status}")
	public ResponseEntity<List<Subscription>> getSubscriptionsByStatus(@PathVariable SubscriptionStatus status) {
		List<Subscription> subscriptions = subscriptionService.getSubscriptionsByStatus(status);
		return new ResponseEntity<>(subscriptions, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Subscription> updateSubscription(@PathVariable Long id,
			@RequestBody Subscription subscriptionDetails) {
		try {
			Subscription updatedSubscription = subscriptionService.updateSubscription(id, subscriptionDetails);
			return new ResponseEntity<>(updatedSubscription, HttpStatus.OK);
		} catch (RuntimeException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteSubscription(@PathVariable Long id) {
		subscriptionService.deleteSubscription(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}