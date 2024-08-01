package org.abondar.experimental.travel.model.batch;

import java.time.Instant;

public record FlightBatchItem(
        String destinationCity,
        String flightNumber,
        String ticketNumber,
        Instant arrivalTime
) {
}
