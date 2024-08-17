package org.abondar.experimental.travel.model.db;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class FlightBooking{
   private Long id;
   private String tripId;
   private String sourceCity;
   private String destinationCity;
   private String flightNumber;
   private String ticketNumber;
   private Timestamp departureTime;
   private Timestamp arrivalTime;
}
