package com.example.Diplomna.repo;

import com.example.Diplomna.model.Subscription;
import com.example.Diplomna.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface SubscriptionRepo extends JpaRepository<Subscription, Long> {
    @Query("SELECT s.user_target.id FROM Subscription s WHERE s.user.id = :userId AND s.Unsubscribed != true")
    List<Long> findSubscribedUserIdsByUserId(@Param("userId") Long userId);

    @Query("SELECT COUNT(s.id) FROM Subscription s WHERE s.user_target.id = :userId")
    long countSubscribers(@Param("userId") Long userId);

    @Query("SELECT s FROM Subscription s WHERE s.user.id = :userId AND s.user_target.id = :targetUserId")
    Subscription findByUserIdAndTargetUserId(@Param("userId") Long userId, @Param("targetUserId") Long targetUserId);

    List<Subscription> findByUser_Id(Long userId);

}
