package org.abondar.experimental.travel.model.batch;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

import java.time.ZonedDateTime;

@Component
public class FlightBatchItemFieldSetMapper implements FieldSetMapper<FlightBatchItem> {


    @Override
    public FlightBatchItem mapFieldSet(FieldSet fieldSet) throws BindException {
        return new FlightBatchItem(
                fieldSet.readString("tripId"),
                fieldSet.readString("sourceCity"),
                fieldSet.readString("destinationCity"),
                fieldSet.readString("flightNumber"),
                fieldSet.readString("ticketNumber"),
                ZonedDateTime.parse(fieldSet.readString("departureTime"), FormatUtil.FORMATTER),
                ZonedDateTime.parse(fieldSet.readString("arrivalTime"), FormatUtil.FORMATTER)
        );
    }
}
