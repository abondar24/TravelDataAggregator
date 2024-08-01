package org.abondar.experimental.travel.model.db;

import java.time.Instant;

public record HotelBooking(
        Long id,
        Long cityId,
        Integer numberOfPeople,
        Instant bookingStart,
        Instant bookingEnd
) {
}
