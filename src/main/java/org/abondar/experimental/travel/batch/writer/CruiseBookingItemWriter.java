package org.abondar.experimental.travel.batch.writer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.abondar.experimental.travel.mapper.CruiseBookingMapper;
import org.abondar.experimental.travel.model.db.CruiseBooking;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class CruiseBookingItemWriter implements ItemWriter<CruiseBooking> {

    private final CruiseBookingMapper cruiseBookingMapper;


    @Override
    public void write(Chunk<? extends CruiseBooking> chunk) throws Exception {
        var bookings = (List<CruiseBooking>) chunk.getItems();

        if (!bookings.isEmpty()) {
            cruiseBookingMapper.insertCruiseBookings(bookings);
        }

        log.info("Cruise bookings saved to db");
    }
}
