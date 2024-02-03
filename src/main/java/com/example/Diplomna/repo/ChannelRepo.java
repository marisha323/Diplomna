package com.example.Diplomna.repo;

import com.example.Diplomna.model.Channel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChannelRepo extends JpaRepository<Channel,Long> {
    List<Channel> findChannelsByUser_Id(Long userId);
}
