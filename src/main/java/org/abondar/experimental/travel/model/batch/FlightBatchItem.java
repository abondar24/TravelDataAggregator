package org.abondar.experimental.travel.model.batch;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightBatchItem {
    private String destinationCity;
    private String flightNumber;
    private String ticketNumber;
    private Instant arrivalTime;
}
