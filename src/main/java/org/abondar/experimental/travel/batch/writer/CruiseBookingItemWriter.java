package org.abondar.experimental.travel.batch.writer;

import lombok.RequiredArgsConstructor;
import org.abondar.experimental.travel.mapper.CruiseBookingMapper;
import org.abondar.experimental.travel.mapper.TripInfoMapper;
import org.abondar.experimental.travel.model.db.CruiseBooking;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CruiseBookingItemWriter extends BaseItemWriter<CruiseBooking> {

    private final CruiseBookingMapper cruiseBookingMapper;

    public CruiseBookingItemWriter(TripInfoMapper tripInfoMapper, CruiseBookingMapper cruiseBookingMapper) {
        super(tripInfoMapper);
        this.cruiseBookingMapper = cruiseBookingMapper;
    }

    @Override
    protected void insertBookings(List<CruiseBooking> bookings) {
        cruiseBookingMapper.insertCruiseBookings(bookings);
    }

    @Override
    protected String getTripId(CruiseBooking booking) {
        return booking.getTripId();
    }

}
