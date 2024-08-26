package org.abondar.experimental.travel.batch.processor;

import org.abondar.experimental.travel.model.batch.CruiseBatchItem;
import org.abondar.experimental.travel.model.db.CruiseBooking;
import org.springframework.stereotype.Component;

@Component
public class CruiseBookingItemProcessor extends BaseItemProcessor<CruiseBatchItem, CruiseBooking> {
    @Override
    protected CruiseBooking createBooking(CruiseBatchItem item) {

        var cruiseStart = toTimestamp(item.getCruiseStart());
        var cruiseEnd = toTimestamp(item.getCruiseEnd());

        return new CruiseBooking(0L, item.getTripId(), item.getTicketNumber(),
                item.getSourcePort(), item.getDestinationPort(), cruiseStart, cruiseEnd);
    }

}
