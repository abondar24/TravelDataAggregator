package org.abondar.experimental.travel.mapper;

import org.abondar.experimental.travel.model.db.City;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Qualifier;

@Mapper
@Qualifier("cityMapper")
public interface CityMapper {

    @Insert("INSERT INTO city (name) VALUES (#{newCity.name})")
    void insertCity(@Param("newCity") City city);

    @Select("SELECT * FROM city WHERE name = #{cityName}")
    City findCityByName(@Param("cityName") String cityName);

}
