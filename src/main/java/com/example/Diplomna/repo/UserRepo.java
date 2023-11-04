package com.example.Diplomna.repo;

import com.example.Diplomna.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {
}
