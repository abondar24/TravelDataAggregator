package org.abondar.experimental.travel.mapper;

import org.abondar.experimental.travel.model.db.FlightBooking;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FlightBookingMapper {

    void insertFlightBookings(@Param("flightBookings") List<FlightBooking> flightBookings);
}
