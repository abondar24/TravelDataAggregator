package org.abondar.experimental.travel.batch.hotel;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@SpringBatchTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class HotelBookingJobTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;


    @Test
    void testJob() throws Exception {


        JobExecution jobExecution = jobLauncherTestUtils.launchJob();

        assertEquals("COMPLETED", jobExecution.getStatus().toString());

    }
}
