package org.abondar.experimental.travel.batch.writer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.abondar.experimental.travel.mapper.FlightBookingMapper;
import org.abondar.experimental.travel.model.db.FlightBooking;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class FlightBookingItemWriter implements ItemWriter<FlightBooking> {

    private final FlightBookingMapper flightBookingMapper;

    @Override
    public void write(Chunk<? extends FlightBooking> chunk) throws Exception {
        var bookings = (List<FlightBooking>) chunk.getItems();

        if (!bookings.isEmpty()) {
            flightBookingMapper.insertFlightBookings(bookings);
        }

        log.info("Hotel bookings saved to db");
    }
}
