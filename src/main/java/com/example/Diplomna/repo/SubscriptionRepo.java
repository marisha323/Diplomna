package com.example.Diplomna.repo;

import com.example.Diplomna.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SubscriptionRepo extends JpaRepository<Subscription,Long> {

    @Query("SELECT COUNT(s.id) FROM Subscription s WHERE s.user_target.id = :userId")
    long countSubscribers(@Param("userId") Long userId);
}
