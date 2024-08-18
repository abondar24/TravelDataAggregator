package org.abondar.experimental.travel.batch.processor;

import lombok.extern.slf4j.Slf4j;
import org.abondar.experimental.travel.model.batch.CruiseBatchItem;
import org.abondar.experimental.travel.model.db.CruiseBooking;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@Slf4j
public class CruiseBookingItemProcessor implements ItemProcessor<CruiseBatchItem, CruiseBooking> {
    @Override
    public CruiseBooking process(CruiseBatchItem item) throws Exception {

        var cruiseStart = Timestamp.from(item.getCruiseStart().toInstant());
        var cruiseEnd = Timestamp.from(item.getCruiseEnd().toInstant());

        var cruiseBooking = new CruiseBooking(0L, item.getTripId(), item.getTicketNumber(),
                item.getSourcePort(), item.getDestinationPort(), cruiseStart, cruiseEnd);


        log.info("Cruise booking processed");

        return cruiseBooking;
    }
}
