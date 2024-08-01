package org.abondar.experimental.travel.model.db;

import java.time.Instant;

public record FlightBooking(
        Long id,
        String destinationCity,
        String flightNumber,
        String ticketNumber,
        Instant arrivalTime
) {
}
