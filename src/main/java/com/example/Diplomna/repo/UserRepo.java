package com.example.Diplomna.repo;

import com.example.Diplomna.model.User;
import com.example.Diplomna.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {

    Optional<User> findByUserName(String userName);
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

}
