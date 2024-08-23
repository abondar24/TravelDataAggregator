package org.abondar.experimental.travel.batch.writer;

import org.abondar.experimental.travel.mapper.HotelBookingMapper;
import org.abondar.experimental.travel.model.db.HotelBooking;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.batch.item.Chunk;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import static org.mockito.Mockito.*;

public class HotelBookingItemWriterTest extends BaseWriterTest {

    @Mock
    private HotelBookingMapper hotelBookingMapper;

    @InjectMocks
    private HotelBookingItemWriter hotelBookingItemWriter;

    @Test
    public void writeTest() throws Exception {
        HotelBooking booking = new HotelBooking(1L, "test", "Amsterdam", "Sheraton",
                2, Timestamp.from(Instant.now()), Timestamp.from(Instant.now()));
        List<HotelBooking> bookings = List.of(booking);
        Chunk<HotelBooking> chunk = new Chunk<>(bookings);

        doNothing().when(hotelBookingMapper).insertHotelBookings(bookings);

        hotelBookingItemWriter.write(chunk);

        verify(hotelBookingMapper, times(1)).insertHotelBookings(bookings);
    }

}
