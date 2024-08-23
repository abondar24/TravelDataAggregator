package org.abondar.experimental.travel.service;

import org.abondar.experimental.travel.properties.EmailProperties;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmailServiceTest {

    @Mock
    private JavaMailSender mailSender;

    @Mock
    private EmailProperties emailProperties;

    @Mock
    private ReportService reportService;

    @InjectMocks
    private EmailServiceImpl emailService;

    @Test
    void sendEmailTest() {


        when(emailProperties.getTo()).thenReturn("test@abondar.org");
        when(emailProperties.getSubject()).thenReturn("test subject");
        when(reportService.buildReport()).thenReturn("test report");

        emailService.sendEmail();

        verify(mailSender).send(any(SimpleMailMessage.class));
    }
}
