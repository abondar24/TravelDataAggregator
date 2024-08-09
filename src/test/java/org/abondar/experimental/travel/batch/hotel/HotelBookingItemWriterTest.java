package org.abondar.experimental.travel.batch.hotel;

import org.abondar.experimental.travel.mapper.HotelBookingMapper;
import org.abondar.experimental.travel.model.db.HotelBooking;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.batch.item.Chunk;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HotelBookingItemWriterTest {

    @Mock
    private HotelBookingMapper hotelBookingMapper;

    @InjectMocks
    private HotelBookingItemWriter hotelBookingItemWriter;

    @Test
    public void writeTest() throws Exception {
        HotelBooking booking1 = new HotelBooking(1L, "Amsterdam", "Sheraton",
                2, Timestamp.from(Instant.now()), Timestamp.from(Instant.now()));
        HotelBooking booking2 = new HotelBooking(2L, "Paris", "Mariott",
                1, Timestamp.from(Instant.now()), Timestamp.from(Instant.now()));
        List<HotelBooking> bookings = List.of(booking1, booking2);
        Chunk<HotelBooking> chunk = new Chunk<>(bookings);

        doNothing().when(hotelBookingMapper).insertHotelBookings(bookings);

        hotelBookingItemWriter.write(chunk);

        verify(hotelBookingMapper, times(1)).insertHotelBookings(bookings);
    }

}
