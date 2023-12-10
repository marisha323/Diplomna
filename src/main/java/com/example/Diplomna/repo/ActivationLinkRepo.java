package com.example.Diplomna.repo;

import com.example.Diplomna.model.ActivationLink;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ActivationLinkRepo extends JpaRepository<ActivationLink,Long> {
    Optional<ActivationLink> findByUserId(Long userId);
}
