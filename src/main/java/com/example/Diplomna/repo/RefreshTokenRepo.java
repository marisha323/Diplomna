package com.example.Diplomna.repo;

import com.example.Diplomna.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepo extends JpaRepository<RefreshToken,Long> {
}
