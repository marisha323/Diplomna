package com.example.Diplomna.auth.services;

import com.example.Diplomna.model.ActivationLink;
import com.example.Diplomna.model.User;
import com.example.Diplomna.repo.UserRepo;
import com.example.Diplomna.services.ActivationLinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MailService {
    private final String DOMAIN = "http://localhost:8085";
    private final String ROUTE = "/api/auth/activate";

    private final ActivationLinkService activationLinkService;
    private final UserRepo userRepo;
    private final JavaMailSender mailSender;

    public String sendActivationLink(User user) throws Exception {
            return createLink(user);
    }

    private String createLink(User user) throws Exception {
        var link = activationLinkService.createActivationLink(user);
        try
        {

            StringBuilder linkBuilder = new StringBuilder();
            linkBuilder.append(DOMAIN);
            linkBuilder.append(ROUTE);
            linkBuilder.append("?user_id=").append(link.getUser().getId());
            linkBuilder.append("&code=").append(link.getLink());

            //sendLink(user, linkBuilder.toString());

            return linkBuilder.toString();
        } catch (Exception ex) {
            activationLinkService.deleteLinkById(link.getId());
            userRepo.deleteById(user.getId());
            throw new Exception(ex.getMessage());
        }
    }

    private void sendLink(User user, String link) throws Exception {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(user.getEmail());
        simpleMailMessage.setSubject("Активація облікового запису на OwUa");
        simpleMailMessage.setText("Для завершення реєстрації перейдіть за посиланням: \n" + link);

        mailSender.send(simpleMailMessage);

    }
}
