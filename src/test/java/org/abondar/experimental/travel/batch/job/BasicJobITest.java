package org.abondar.experimental.travel.batch.job;

import org.junit.jupiter.api.Tag;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@ActiveProfiles("sftp")
@Tag("integration")
public class BasicJobITest {

    @Container
    protected static final MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:8.4.0")
            .withExposedPorts(3306)
            .withDatabaseName("travel_data")
            .withUsername("travel")
            .withPassword("travel!@#");


    @DynamicPropertySource
    static void configureTestProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mySQLContainer::getUsername);
        registry.add("spring.datasource.password", mySQLContainer::getPassword);
    }
}
