package org.abondar.experimental.travel.model.report;

import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
@Builder
public class HotelBookingReportItem {
    private String tripId;
    private String hotelCity;
    private String hotelName;
    private Integer numberOfPeople;
    private ZonedDateTime bookingStart;
    private ZonedDateTime bookingEnd;

    private String flightSourceCity;
    private String flightDestinationCity;
    private String flightNumber;
    private String flightTicketNumber;
    private ZonedDateTime departureTime;
    private ZonedDateTime arrivalTime;

    private String cruiseTicketNumber;
    private String cruiseSourcePort;
    private String cruiseDestinationPort;
    private ZonedDateTime cruiseStart;
    private ZonedDateTime cruiseEnd;
}
