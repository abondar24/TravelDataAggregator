package org.abondar.experimental.travel.model.batch;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;

public record HotelBatchItem(
        String city,

        @JsonProperty("trip_id")
        String tripId,

        @JsonProperty("hotel_name")
        String hotelName,

        @JsonProperty("number_of_people")
        Integer numberOfPeople,

        @JsonProperty("booking_start")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss z")
        ZonedDateTime bookingStart,

        @JsonProperty("booking_end")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss z")
        ZonedDateTime bookingEnd
) {
}
