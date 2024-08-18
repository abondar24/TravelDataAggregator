package org.abondar.experimental.travel.model.batch;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.time.ZonedDateTime;

public class ZonedDateTimeAdapter extends XmlAdapter<String, ZonedDateTime> {


    @Override
    public ZonedDateTime unmarshal(String s) throws Exception {
        return ZonedDateTime.parse(s, FormatUtil.FORMATTER);
    }

    @Override
    public String marshal(ZonedDateTime zonedDateTime) throws Exception {
        return zonedDateTime.format(FormatUtil.FORMATTER);
    }
}
