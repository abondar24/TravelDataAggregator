package org.abondar.experimental.travel.model.db;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class CruiseBooking {
    private Long id;
    private String tripId;
    private String ticketNumber;
    private String sourcePort;
    private String destinationPort;
    private Timestamp cruiseStart;
    private Timestamp cruiseEnd;
}
