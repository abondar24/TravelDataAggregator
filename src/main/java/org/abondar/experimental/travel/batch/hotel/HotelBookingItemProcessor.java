package org.abondar.experimental.travel.batch.hotel;

import lombok.extern.slf4j.Slf4j;
import org.abondar.experimental.travel.model.batch.HotelBatchItem;
import org.abondar.experimental.travel.model.db.HotelBooking;
import org.springframework.batch.item.ItemProcessor;

@Slf4j
public class HotelBookingItemProcessor implements ItemProcessor<HotelBatchItem, HotelBooking> {


    @Override
    public HotelBooking process(HotelBatchItem item) throws Exception {


        var hotelBooking = new HotelBooking(0L, item.city(), item.numberOfPeople(),
                item.bookingStart(), item.bookingEnd());

        log.info("Hotel booking processed");

        return hotelBooking;
    }
}
