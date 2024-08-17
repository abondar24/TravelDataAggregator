package org.abondar.experimental.travel.batch.job.hotel;

import org.abondar.experimental.travel.batch.job.BasicJobITest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.utility.MountableFile;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(properties = {"spring.batch.job.name=hotelBookingsJob"})
public class HotelBookingJobITest  extends BasicJobITest {

    @Container
    private static final GenericContainer<?> sftpContainer = new GenericContainer<>("atmoz/sftp:latest")
            .withExposedPorts(22)
            .withCopyFileToContainer(MountableFile.forClasspathResource("testData/hotel_bookings.json", 0777),
                    "/home/fUser/data/hotel/hotel_bookings.json")
            .withCommand("fUser:fPass:::data");

    @Autowired
    @Qualifier(value = "hotelBookingsJob")
    private Job job;


    @DynamicPropertySource
    static void configureTestProperties(DynamicPropertyRegistry registry) {
        registry.add("sftp.host", sftpContainer::getHost);
        registry.add("sftp.port", sftpContainer::getFirstMappedPort);
    }

    @BeforeEach
    public void init() {
        sftpContainer.start();
    }

    @Test
    public void testHotelBookingJob() throws Exception {
        jobLauncherTestUtils.setJob(job);
        JobExecution jobExecution = jobLauncherTestUtils.launchJob();
        assertEquals("COMPLETED", jobExecution.getStatus().toString());

    }

    @AfterAll
    public static void cleanUp() {
        sftpContainer.stop();
    }
}
