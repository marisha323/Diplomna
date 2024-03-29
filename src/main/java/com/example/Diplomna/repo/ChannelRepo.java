package com.example.Diplomna.repo;

import com.example.Diplomna.model.Channel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChannelRepo extends JpaRepository<Channel,Long> {
    List<Channel> findChannelsByUser_Id(Long userId);
    Optional<Channel> findByUserId(Long userId);
}
