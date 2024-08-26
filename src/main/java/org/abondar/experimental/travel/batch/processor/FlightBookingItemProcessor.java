package org.abondar.experimental.travel.batch.processor;

import lombok.extern.slf4j.Slf4j;
import org.abondar.experimental.travel.model.batch.FlightBatchItem;
import org.abondar.experimental.travel.model.db.FlightBooking;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FlightBookingItemProcessor extends BaseItemProcessor<FlightBatchItem, FlightBooking> {
    @Override
    protected FlightBooking createBooking(FlightBatchItem item) {

        var departureTime = toTimestamp(item.departureTime());
        var arrivalTime = toTimestamp(item.arrivalTime());

        return new FlightBooking(0L, item.tripId(), item.sourceCity(),
                item.destinationCity(), item.flightNumber(), item.ticketNumber(), departureTime, arrivalTime);

    }

}
