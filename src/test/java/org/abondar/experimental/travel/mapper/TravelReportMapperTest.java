package org.abondar.experimental.travel.mapper;


import org.abondar.experimental.travel.model.db.CruiseBooking;
import org.abondar.experimental.travel.model.db.FlightBooking;
import org.abondar.experimental.travel.model.db.HotelBooking;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TravelReportMapperTest {


    @Autowired
    private TravelReportMapper mapper;

    @Autowired
    private HotelBookingMapper hotelBookingMapper;

    @Autowired
    private FlightBookingMapper flightBookingMapper;

    @Autowired
    private CruiseBookingMapper cruiseBookingMapper;


    @Test
    public void getTravelDataTest() {
        var cruiseBooking = new CruiseBooking(0L, "test", "test",
                "test", "test",
                Timestamp.from(Instant.now()), Timestamp.from(Instant.now()));
        cruiseBookingMapper.insertCruiseBookings(List.of(cruiseBooking));

        var flightBooking = new FlightBooking(0L, "test", "test",
                "test", "test", "test",
                Timestamp.from(Instant.now()), Timestamp.from(Instant.now()));
       flightBookingMapper.insertFlightBookings(List.of(flightBooking));

        HotelBooking hotelBooking = new HotelBooking(0L, "test",
                "Amsterdam", "Sheraton",
                2, Timestamp.from(Instant.now()), Timestamp.from(Instant.now()));
        hotelBookingMapper.insertHotelBookings(List.of(hotelBooking));


        var res = mapper.getDataByTripIds(List.of("test"));

        assertNotNull(res);
        assertEquals(1, res.size());

        assertEquals(hotelBooking.getTripId(),res.get(0).getTripId());
        assertEquals(flightBooking.getTicketNumber(),res.get(0).getFlightTicketNumber());
        assertEquals(cruiseBooking.getSourcePort(),res.get(0).getCruiseSourcePort());
    }


    @Test
    public void getTravelDataPartialTest() {
        var cruiseBooking = new CruiseBooking(0L, "test", "test",
                "test", "test",
                Timestamp.from(Instant.now()), Timestamp.from(Instant.now()));
        cruiseBookingMapper.insertCruiseBookings(List.of(cruiseBooking));


        var res = mapper.getDataByTripIds(List.of("test"));

        assertEquals(cruiseBooking.getSourcePort(),res.get(0).getCruiseSourcePort());
        assertNull(res.get(0).getFlightNumber());
    }

}
