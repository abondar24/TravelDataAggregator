package org.abondar.experimental.travel.mapper;

import org.abondar.experimental.travel.model.db.HotelBooking;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("test")
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class HotelBookingMapperTest {


    @Autowired
    private HotelBookingMapper mapper;

    @Test
    public void insertHotelBooking() {
        HotelBooking hotelBooking = new HotelBooking(0L, "Amsterdam", "Sheraton",
                2, Timestamp.from(Instant.now()), Timestamp.from(Instant.now()));

        mapper.insertHotelBookings(List.of(hotelBooking));

        assertTrue(hotelBooking.getId()>0);

    }

}
