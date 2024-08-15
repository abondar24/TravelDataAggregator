package org.abondar.experimental.travel.batch.processor;

import org.abondar.experimental.travel.model.batch.HotelBatchItem;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HotelBookingItemProcessorTest {


    @Test
    public void processTest() throws Exception {
        var hotelBatchItem = new HotelBatchItem("test", "test",
                "test",
                1, ZonedDateTime.now(), ZonedDateTime.now());
        var processor = new HotelBookingItemProcessor();

        var res = processor.process(hotelBatchItem);

        assertEquals(0L, res.getId());
        assertEquals(hotelBatchItem.city(), res.getCity());
        assertEquals(hotelBatchItem.hotelName(), res.getHotelName());
        assertEquals(hotelBatchItem.numberOfPeople(), res.getNumberOfPeople());

    }

}
