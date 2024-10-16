package org.abondar.experimental.travel.batch.job;

import org.junit.jupiter.api.Tag;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;


@Testcontainers
@ActiveProfiles("integration")
@Tag("integration")
@SpringBatchTest
public class BasicJobITest {

    @Container
    protected static final MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:8.4.0")
            .withExposedPorts(3306)
            .withDatabaseName("travel_data")
            .withUsername("travel")
            .withPassword("travel!@#");


    @Container
    protected static final GenericContainer<?> smtpContainer = new GenericContainer<>("mailhog/mailhog")
            .withExposedPorts(1025);


    @Autowired
    protected JobLauncherTestUtils jobLauncherTestUtils;

    @DynamicPropertySource
    static void configureTestProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mySQLContainer::getUsername);
        registry.add("spring.datasource.password", mySQLContainer::getPassword);
        registry.add("spring.mail.host", smtpContainer::getHost);
        registry.add("spring.mail.port", smtpContainer::getFirstMappedPort);
    }

    protected void executeTest(Job job) throws Exception {
        jobLauncherTestUtils.setJob(job);
        JobExecution jobExecution = jobLauncherTestUtils.launchJob();
        assertEquals("COMPLETED", jobExecution.getStatus().toString());
    }
}
