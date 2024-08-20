package org.abondar.experimental.travel.batch.writer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.abondar.experimental.travel.mapper.FlightBookingMapper;
import org.abondar.experimental.travel.mapper.TripInfoMapper;
import org.abondar.experimental.travel.model.db.FlightBooking;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class FlightBookingItemWriter implements ItemWriter<FlightBooking> {

    private final FlightBookingMapper flightBookingMapper;

    private final TripInfoMapper tripInfoMapper;

    @Override
    @Transactional
    public void write(Chunk<? extends FlightBooking> chunk) throws Exception {
        var bookings = (List<FlightBooking>) chunk.getItems();

        if (!bookings.isEmpty()) {
            flightBookingMapper.insertFlightBookings(bookings);

            var tripIds = bookings.stream()
                    .map(FlightBooking::getTripId)
                    .distinct()
                    .toList();

            tripInfoMapper.insertTripIds(tripIds);
        }

        log.info("Hotel bookings saved to db");
    }
}
