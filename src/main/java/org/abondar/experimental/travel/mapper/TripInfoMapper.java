package org.abondar.experimental.travel.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TripInfoMapper {


    void insertTripIds(@Param("tripIds") List<String> tripIds);

    List<String> selectRecentTripIds();

}
