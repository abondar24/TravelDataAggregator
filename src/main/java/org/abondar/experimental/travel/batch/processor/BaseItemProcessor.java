package org.abondar.experimental.travel.batch.processor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

import java.sql.Timestamp;
import java.time.ZonedDateTime;

@Slf4j
public abstract class BaseItemProcessor<I, O> implements ItemProcessor<I, O> {


    protected Timestamp toTimestamp(ZonedDateTime dateTime) {
        return dateTime != null ? Timestamp.from(dateTime.toInstant()) : null;
    }


    protected abstract O createBooking(I item);


    @Override
    public O process(I item) throws Exception {
        O booking = createBooking(item);
        log.info("{} processed", booking.getClass().getSimpleName());
        return booking;
    }


}
