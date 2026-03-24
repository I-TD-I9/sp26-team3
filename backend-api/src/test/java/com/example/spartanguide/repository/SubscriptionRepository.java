package com.example.spartanguide.repository;

import com.example.spartanguide.entity.Subscription;
import com.example.spartanguide.entity.Subscription.SubscriptionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
	@Query(value = "SELECT s.* FROM subscriptions s JOIN student_subscriptions ss ON s.subscription_id = ss.subscription_id WHERE ss.student_id = :studentId", nativeQuery = true)
	List<Subscription> findByStudentsUserId(Long studentId);
	List<Subscription> findByStatus(SubscriptionStatus status);

	@Query(value = "SELECT s.* FROM subscriptions s JOIN student_subscriptions ss ON s.subscription_id = ss.subscription_id WHERE ss.student_id = :studentId AND s.status = :status", nativeQuery = true)
	List<Subscription> findByStudentsUserIdAndStatusValue(Long studentId, String status);

	default List<Subscription> findByStudentsUserIdAndStatus(Long studentId, SubscriptionStatus status) {
		return findByStudentsUserIdAndStatusValue(studentId, status.name());
	}
}