package org.abondar.experimental.travel.batch.writer;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.abondar.experimental.travel.mapper.HotelBookingMapper;
import org.abondar.experimental.travel.model.db.HotelBooking;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class HotelBookingItemWriter implements ItemWriter<HotelBooking> {


    private final HotelBookingMapper hotelBookingMapper;


    @Override
    public void write(Chunk<? extends HotelBooking> chunk) throws Exception {

        var bookings = (List<HotelBooking>) chunk.getItems();
        hotelBookingMapper.insertHotelBookings(bookings);
        log.info("Hotel bookings saved to db");
    }
}
