package com.example.Diplomna.repo;

import com.example.Diplomna.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {

}
