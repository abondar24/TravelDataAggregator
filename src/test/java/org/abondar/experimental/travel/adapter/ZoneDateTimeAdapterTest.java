package org.abondar.experimental.travel.adapter;

import org.abondar.experimental.travel.model.batch.ZonedDateTimeAdapter;
import org.junit.jupiter.api.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ZoneDateTimeAdapterTest {

    @Test
    public void unmarshalTest() throws Exception {
        var adapter = new ZonedDateTimeAdapter();

        var date = "31-12-2023 14:23:00 EST";
        var res = adapter.unmarshal(date);

        assertEquals(31, res.getDayOfMonth());
        assertEquals(12, res.getMonthValue());
        assertEquals(2023, res.getYear());

        System.out.println(res);

    }

    @Test
    public void marshalTest() throws Exception {
        var adapter = new ZonedDateTimeAdapter();

        var date = ZonedDateTime.of(2023, 12, 31, 14, 23, 0, 0, ZoneId.of("America/New_York"));
        var expectedString = "31-12-2023 14:23:00 EST";
        var result = adapter.marshal(date);

        assertEquals(expectedString, result);

    }
}
