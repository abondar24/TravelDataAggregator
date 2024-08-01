package org.abondar.experimental.travel.mapper;

import org.abondar.experimental.travel.model.db.HotelBooking;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@Mapper
@Qualifier("hotelBookingMapper")
public interface HotelBookingMapper {

  void insertHotelBookings(@Param("hotelBookings") List<HotelBooking> hotelBookings);

}
