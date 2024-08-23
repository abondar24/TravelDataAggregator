package org.abondar.experimental.travel.batch.writer;

import org.abondar.experimental.travel.mapper.CruiseBookingMapper;
import org.abondar.experimental.travel.model.db.CruiseBooking;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.batch.item.Chunk;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import static org.mockito.Mockito.*;

public class CruiseBookingItemWriterTest extends BaseWriterTest {

    @Mock
    private CruiseBookingMapper cruiseBookingMapper;

    @InjectMocks
    private CruiseBookingItemWriter cruiseBookingItemWriter;

    @Test
    public void writeTest() throws Exception {
        var cruiseBooking = new CruiseBooking(0L, "test", "test",
                "test", "test",
                Timestamp.from(Instant.now()), Timestamp.from(Instant.now()));

        var bookings = List.of(cruiseBooking);
        var chunk = new Chunk<>(bookings);

        doNothing().when(cruiseBookingMapper).insertCruiseBookings(bookings);

        cruiseBookingItemWriter.write(chunk);

        verify(cruiseBookingMapper, times(1)).insertCruiseBookings(bookings);

    }
}
