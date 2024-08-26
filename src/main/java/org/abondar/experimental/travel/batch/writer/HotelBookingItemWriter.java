package org.abondar.experimental.travel.batch.writer;


import lombok.extern.slf4j.Slf4j;
import org.abondar.experimental.travel.mapper.HotelBookingMapper;
import org.abondar.experimental.travel.mapper.TripInfoMapper;
import org.abondar.experimental.travel.model.db.HotelBooking;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class HotelBookingItemWriter extends BaseItemWriter<HotelBooking> {


    private final HotelBookingMapper hotelBookingMapper;

    public HotelBookingItemWriter(TripInfoMapper tripInfoMapper, HotelBookingMapper hotelBookingMapper) {
        super(tripInfoMapper);
        this.hotelBookingMapper = hotelBookingMapper;
    }


    @Override
    protected void insertBookings(List<HotelBooking> bookings) {
        hotelBookingMapper.insertHotelBookings(bookings);
    }

    @Override
    protected String getTripId(HotelBooking booking) {
        return booking.getTripId();
    }
}
