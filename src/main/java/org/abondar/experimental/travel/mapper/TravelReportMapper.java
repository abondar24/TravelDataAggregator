package org.abondar.experimental.travel.mapper;

import org.abondar.experimental.travel.model.report.HotelBookingReportItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TravelReportMapper {

    List<HotelBookingReportItem> getDataByTripIds(@Param("tripIds") List<String> tripIds);
}
