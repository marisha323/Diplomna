package com.example.Diplomna.auth;

import com.example.Diplomna.model.ActivationLink;
import com.example.Diplomna.model.User;
import com.example.Diplomna.services.ActivationLinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MailService {
    private final String DOMAIN = "http://localhost:8085";
    private final String ROUTE = "api/auth/activate";

    private final ActivationLinkService activationLinkService;

    public String sendActivationLink(User user) {
        var link = activationLinkService.createActivationLink(user);

        StringBuilder linkBuilder = new StringBuilder();
        linkBuilder.append(DOMAIN);
        linkBuilder.append(ROUTE);
        linkBuilder.append("?user_id=").append(link.getUser().getId());
        linkBuilder.append("&code=").append(link.getLink());

        return linkBuilder.toString();
    }




}
