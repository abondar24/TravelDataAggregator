package org.abondar.experimental.travel.batch.processor;

import org.abondar.experimental.travel.model.batch.CruiseBatchItem;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CruiseBookingItemProcessorTest {

    @Test
    public void processorTest() throws Exception {
        var cruiseBookingItem = new CruiseBatchItem("test","test12","test",
                "test", ZonedDateTime.now(),ZonedDateTime.now());

        var processor = new CruiseBookingItemProcessor();

       var res = processor.process(cruiseBookingItem);

       assertEquals(cruiseBookingItem.getTripId(),res.getTripId());
       assertEquals(cruiseBookingItem.getSourcePort(),res.getSourcePort());
    }
}
