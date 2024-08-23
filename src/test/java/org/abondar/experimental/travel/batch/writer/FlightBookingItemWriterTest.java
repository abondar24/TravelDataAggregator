package org.abondar.experimental.travel.batch.writer;

import org.abondar.experimental.travel.mapper.FlightBookingMapper;
import org.abondar.experimental.travel.model.db.FlightBooking;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.batch.item.Chunk;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import static org.mockito.Mockito.*;


public class FlightBookingItemWriterTest extends BaseWriterTest {

    @Mock
    private FlightBookingMapper flightBookingMapper;

    @InjectMocks
    private FlightBookingItemWriter flightBookingItemWriter;

    @Test
    public void writeTest() throws Exception {
        var flightBooking = new FlightBooking(0L, "test", "test",
                "test", "test", "test",
                Timestamp.from(Instant.now()), Timestamp.from(Instant.now()));

        var bookings = List.of(flightBooking);
        var chunk = new Chunk<>(bookings);

        doNothing().when(flightBookingMapper).insertFlightBookings(bookings);

        flightBookingItemWriter.write(chunk);

        verify(flightBookingMapper, times(1)).insertFlightBookings(bookings);
    }
}
