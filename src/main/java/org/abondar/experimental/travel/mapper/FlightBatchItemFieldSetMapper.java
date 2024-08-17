package org.abondar.experimental.travel.mapper;

import org.abondar.experimental.travel.model.batch.FlightBatchItem;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Component
public class FlightBatchItemFieldSetMapper implements FieldSetMapper<FlightBatchItem> {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy H:mm:ss z", Locale.ENGLISH);


    @Override
    public FlightBatchItem mapFieldSet(FieldSet fieldSet) throws BindException {
        return new FlightBatchItem(
                fieldSet.readString("tripId"),
                fieldSet.readString("sourceCity"),
                fieldSet.readString("destinationCity"),
                fieldSet.readString("flightNumber"),
                fieldSet.readString("ticketNumber"),
                ZonedDateTime.parse(fieldSet.readString("departureTime"), formatter),
                ZonedDateTime.parse(fieldSet.readString("arrivalTime"), formatter)
        );
    }
}
