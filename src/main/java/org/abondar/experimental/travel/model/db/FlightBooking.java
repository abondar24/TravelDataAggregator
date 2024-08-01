package org.abondar.experimental.travel.model.db;

import java.time.Instant;

public record FlightBooking(
        Long id,
        Long destinationCityId,
        String flightNumber,
        String ticketNumber,
        Instant arrivalTime
) {
}
