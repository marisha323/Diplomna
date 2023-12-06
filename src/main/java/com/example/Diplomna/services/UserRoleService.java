package com.example.Diplomna.services;

import com.example.Diplomna.model.UserRole;
import com.example.Diplomna.repo.UserRoleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRoleService {

    @Autowired
    private final UserRoleRepo roleRepository;
    public UserRole getUserRole() {
        return roleRepository.findByName("ROLE_USER").get();
    }
}
