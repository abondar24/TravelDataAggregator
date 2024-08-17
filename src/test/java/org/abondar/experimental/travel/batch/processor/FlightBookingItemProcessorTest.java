package org.abondar.experimental.travel.batch.processor;

import org.abondar.experimental.travel.model.batch.FlightBatchItem;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlightBookingItemProcessorTest {

    @Test
    public void processTest() throws Exception {
        var flightBooking = new FlightBatchItem("test", "test",
                "test", "test", "test",
                ZonedDateTime.now(), ZonedDateTime.now());

        var processor = new FlightBookingItemProcessor();
        var result = processor.process(flightBooking);

        assertEquals(0L, result.getId());
        assertEquals(flightBooking.tripId(), result.getTripId());
        assertEquals(flightBooking.destinationCity(),result.getDestinationCity());
    }
}
