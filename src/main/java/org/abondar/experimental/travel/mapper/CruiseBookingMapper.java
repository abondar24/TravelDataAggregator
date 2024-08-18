package org.abondar.experimental.travel.mapper;

import org.abondar.experimental.travel.model.db.CruiseBooking;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CruiseBookingMapper {

  void  insertCruiseBookings(@Param("cruiseBookings") List<CruiseBooking> cruiseBookings);
}
