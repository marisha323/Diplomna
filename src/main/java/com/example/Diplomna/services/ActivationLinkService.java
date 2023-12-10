package com.example.Diplomna.services;
import com.example.Diplomna.auth.ActivationResponse;
import com.example.Diplomna.model.ActivationLink;
import com.example.Diplomna.model.User;
import com.example.Diplomna.repo.ActivationLinkRepo;
import com.example.Diplomna.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ActivationLinkService {
    private final ActivationLinkRepo activationLinkRepo;
    private final UserRepo userRepo;

    public ActivationLink createActivationLink(User user) {
        ActivationLink link = ActivationLink.builder()
                .link(generateLink())
                .user(user)
                .build();

        activationLinkRepo.save(link);

        return link;
    }

    private String generateLink() {
        return UUID.randomUUID().toString();
    }

    public ActivationResponse activate(Long userId, String code) throws Exception {
        User user = userRepo.findById(userId)
                .orElseThrow(()->new Exception("User not found"));

        if (user.isActivated()) {
            throw new Exception("User already activated");
        }

        ActivationLink link = activationLinkRepo.findByUserId(userId)
                .orElseThrow(()->new Exception("Wrong activation link"));
        if (!link.getLink().equals(code)) {
            throw new Exception("Wrong activation link");
        }

        user.setActivated(true);
        userRepo.save(user);

        return ActivationResponse.builder()
                .email(user.getEmail())
                .build();
    }
}