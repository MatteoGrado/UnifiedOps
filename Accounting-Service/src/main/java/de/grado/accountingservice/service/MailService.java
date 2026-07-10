package de.grado.accountingservice.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MailService
{
    private final JavaMailSender mailSender;

    public void sendMail(String to, String subject, Object bodyData)
    {
        String from = "matteo.grado@UnifiedOps.com";

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(String.valueOf(bodyData));

        mailSender.send(message);
    }
}
