package com.example.Diplomna.repo;

import com.example.Diplomna.model.Subscription;
import com.example.Diplomna.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface SubscriptionRepo extends JpaRepository<Subscription, Long> {
    @Query("SELECT s.user_target.id FROM Subscription s WHERE s.user.id = :userId")
    List<Long> findSubscribedUserIdsByUserId(@Param("userId") Long userId);

}
