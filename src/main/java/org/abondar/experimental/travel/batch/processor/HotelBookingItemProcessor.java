package org.abondar.experimental.travel.batch.processor;

import lombok.extern.slf4j.Slf4j;
import org.abondar.experimental.travel.model.batch.HotelBatchItem;
import org.abondar.experimental.travel.model.db.HotelBooking;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@Slf4j
public class HotelBookingItemProcessor implements ItemProcessor<HotelBatchItem, HotelBooking> {


    @Override
    public HotelBooking process(HotelBatchItem item) throws Exception {

        var fromTs = Timestamp.from(item.bookingStart().toInstant());
        var toTs = Timestamp.from(item.bookingEnd().toInstant());

        var hotelBooking = new HotelBooking(0L,item.tripId(), item.city(),
                item.hotelName(),item.numberOfPeople(),fromTs,toTs);

        log.info("Hotel booking processed");

        return hotelBooking;
    }
}
