package org.abondar.experimental.travel.config;

import lombok.RequiredArgsConstructor;
import org.abondar.experimental.travel.properties.SftpProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.sftp.session.DefaultSftpSessionFactory;

@Configuration
@RequiredArgsConstructor
@ConditionalOnProperty(name = "sftp.enabled", havingValue = "true")
public class SftpConfig {

    private final SftpProperties sftpProperties;


    @Bean
    public DefaultSftpSessionFactory sessionFactory() {
        DefaultSftpSessionFactory factory = new DefaultSftpSessionFactory();
        factory.setHost(sftpProperties.getHost());
        factory.setPort(sftpProperties.getPort());
        factory.setUser(sftpProperties.getUsername());
        factory.setPassword(sftpProperties.getPassword());
        factory.setAllowUnknownKeys(true);
        factory.setTimeout(10000);

        return factory;
    }

}
