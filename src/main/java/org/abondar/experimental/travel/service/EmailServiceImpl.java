package org.abondar.experimental.travel.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.abondar.experimental.travel.properties.EmailProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
@ConditionalOnProperty(name = "email.enabled", havingValue = "true")
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    private final ReportService reportService;

    private final EmailProperties emailProperties;

    @Override
    public void sendEmail(){
        var report = reportService.buildReport();

        var email = new SimpleMailMessage();
        email.setTo(emailProperties.getTo());
        email.setSubject(emailProperties.getSubject() + ZonedDateTime.now());
        email.setText(report);

        try {
            mailSender.send(email);
            log.info("Email sent to {}", emailProperties.getTo());
        } catch (Exception ex) {
            log.error("Failed to send email to {}", emailProperties.getTo(), ex);
        }

    }



}
