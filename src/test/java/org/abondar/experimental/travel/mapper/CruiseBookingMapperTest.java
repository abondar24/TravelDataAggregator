package org.abondar.experimental.travel.mapper;

import org.abondar.experimental.travel.model.db.CruiseBooking;
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
public class CruiseBookingMapperTest {

    @Autowired
    private CruiseBookingMapper mapper;

    @Test
    public void insertCruiseBooking() {

        var cruiseBooking = new CruiseBooking(0L, "test", "test",
                "test", "test",
                Timestamp.from(Instant.now()), Timestamp.from(Instant.now()));


        mapper.insertCruiseBookings(List.of(cruiseBooking));

        assertTrue(cruiseBooking.getId() > 0);
    }

}
