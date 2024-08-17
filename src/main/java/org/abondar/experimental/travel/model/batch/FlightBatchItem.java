package org.abondar.experimental.travel.model.batch;

import java.time.ZonedDateTime;


public record FlightBatchItem(
        String tripId,
        String sourceCity,
        String destinationCity,
        String flightNumber,
        String ticketNumber,
        ZonedDateTime departureTime,
        ZonedDateTime arrivalTime
){}
