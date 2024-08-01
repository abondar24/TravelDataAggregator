package org.abondar.experimental.travel.model.batch;

import java.time.Instant;

public record HotelBatchItem(
        String city,
        Integer numberOfPeople,
        Instant bookingStart,
        Instant bookingEnd
) {
}
