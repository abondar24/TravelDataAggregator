package org.abondar.experimental.travel.batch.processor;

import lombok.extern.slf4j.Slf4j;
import org.abondar.experimental.travel.model.batch.FlightBatchItem;
import org.abondar.experimental.travel.model.db.FlightBooking;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@Slf4j
public class FlightBookingItemProcessor implements ItemProcessor<FlightBatchItem, FlightBooking> {
    @Override
    public FlightBooking process(FlightBatchItem item) throws Exception {

        var departureTime = Timestamp.from(item.departureTime().toInstant());
        var arrivalTime = Timestamp.from(item.arrivalTime().toInstant());

        var flightBooking = new FlightBooking(0L, item.tripId(), item.sourceCity(),
                item.destinationCity(), item.flightNumber(), item.ticketNumber(), departureTime, arrivalTime);

        log.info("Flight booking item processed");

        return flightBooking;
    }
}
