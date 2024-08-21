package org.abondar.experimental.travel.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "email.enabled", havingValue = "false")
@Slf4j
public class DummyEmailService implements EmailService {

    @Override
    public void sendEmail() {
        log.info("Sending of email reports is disabled");
    }
}
