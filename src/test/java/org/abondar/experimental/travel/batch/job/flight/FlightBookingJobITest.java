package org.abondar.experimental.travel.batch.job.flight;

import org.abondar.experimental.travel.batch.job.BasicJobITest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.MountableFile;

@SpringBootTest(properties = "spring.batch.job.name=flightBookingsJob")
public class FlightBookingJobITest extends BasicJobITest {
    @Container
    private static final GenericContainer<?> sftpContainer = new GenericContainer<>("atmoz/sftp:latest")
            .withExposedPorts(22)
            .withCopyFileToContainer(MountableFile.forClasspathResource("testData/flight_bookings.csv", 0777),
                    "/home/fUser/data/flight/flight_bookings.csv")
            .withCommand("fUser:fPass:::data");

    @Autowired
    @Qualifier(value = "flightBookingsJob")
    private Job job;


    @DynamicPropertySource
    static void configureTestProperties(DynamicPropertyRegistry registry) {
        registry.add("sftp.host", sftpContainer::getHost);
        registry.add("sftp.port", sftpContainer::getFirstMappedPort);
    }

    @AfterAll
    public static void cleanUp() {
        sftpContainer.stop();
    }

    @BeforeEach
    public void init() {
        sftpContainer.start();
    }

    @Test
    public void testFlightBookingJob() throws Exception {
        executeTest(job);
    }
}
