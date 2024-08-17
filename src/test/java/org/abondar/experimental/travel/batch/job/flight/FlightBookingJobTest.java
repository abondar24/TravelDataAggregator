package org.abondar.experimental.travel.batch.job.flight;

import org.abondar.experimental.travel.batch.job.BasicJobTest;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "spring.batch.job.name=flightBookingsJob")
public class FlightBookingJobTest extends BasicJobTest {


    @Autowired
    @Qualifier(value = "flightBookingsJob")
    private Job job;

    @Test
    void testJob() throws Exception {
        runTest(job);
    }
}
