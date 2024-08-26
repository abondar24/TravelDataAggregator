package org.abondar.experimental.travel.batch.writer;

import org.abondar.experimental.travel.mapper.FlightBookingMapper;
import org.abondar.experimental.travel.mapper.TripInfoMapper;
import org.abondar.experimental.travel.model.db.FlightBooking;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FlightBookingItemWriter extends BaseItemWriter<FlightBooking> {

    private final FlightBookingMapper flightBookingMapper;

    public FlightBookingItemWriter(TripInfoMapper tripInfoMapper, FlightBookingMapper flightBookingMapper) {
        super(tripInfoMapper);
        this.flightBookingMapper = flightBookingMapper;
    }

    @Override
    protected void insertBookings(List<FlightBooking> bookings) {
        flightBookingMapper.insertFlightBookings(bookings);
    }

    @Override
    protected String getTripId(FlightBooking booking) {
        return booking.getTripId();
    }
}
