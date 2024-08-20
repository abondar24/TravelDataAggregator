package org.abondar.experimental.travel.batch.writer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.abondar.experimental.travel.mapper.CruiseBookingMapper;
import org.abondar.experimental.travel.mapper.TripInfoMapper;
import org.abondar.experimental.travel.model.db.CruiseBooking;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class CruiseBookingItemWriter implements ItemWriter<CruiseBooking> {

    private final CruiseBookingMapper cruiseBookingMapper;

    private final TripInfoMapper tripInfoMapper;


    @Override
    @Transactional
    public void write(Chunk<? extends CruiseBooking> chunk) throws Exception {
        var bookings = (List<CruiseBooking>) chunk.getItems();

        if (!bookings.isEmpty()) {
            cruiseBookingMapper.insertCruiseBookings(bookings);

            var tripIds = bookings.stream()
                    .map(CruiseBooking::getTripId)
                    .distinct()
                    .toList();

            tripInfoMapper.insertTripIds(tripIds);
        }

        log.info("Cruise bookings saved to db");
    }
}
