package org.abondar.experimental.travel.batch.processor;

import org.abondar.experimental.travel.model.batch.HotelBatchItem;
import org.abondar.experimental.travel.model.db.HotelBooking;
import org.springframework.stereotype.Component;

@Component
public class HotelBookingItemProcessor extends BaseItemProcessor<HotelBatchItem, HotelBooking> {


    @Override
    protected HotelBooking createBooking(HotelBatchItem item) {
        var fromTs = toTimestamp(item.bookingStart());
        var toTs = toTimestamp(item.bookingEnd());

        return new HotelBooking(0L, item.tripId(), item.city(),
                item.hotelName(), item.numberOfPeople(), fromTs, toTs);
    }
}
