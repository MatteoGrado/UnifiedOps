package de.grado.accountingservice.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessagePreparator;
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

        MimeMailMessage message = new MimeMailMessage();

        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);

        //message.setText();

        mailSender.send(message.getMimeMessage());
    }
}
