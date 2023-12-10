package com.example.Diplomna.repo;

import com.example.Diplomna.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRoleRepo extends JpaRepository<UserRole,Long> {

}
