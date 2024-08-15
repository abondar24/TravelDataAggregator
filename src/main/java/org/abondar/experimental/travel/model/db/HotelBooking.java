package org.abondar.experimental.travel.model.db;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class HotelBooking {
    private Long id;
    private String tripId;
    private String city;
    private String hotelName;
    private Integer numberOfPeople;
    private Timestamp bookingStart;
    private Timestamp bookingEnd;


}
