package org.abondar.experimental.travel.model.batch;


import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name="cruise")
@XmlAccessorType(XmlAccessType.FIELD)
public class CruiseBatchItem {

        @XmlElement(name = "trip_id")
        private String tripId;

        @XmlElement(name = "ticket_number")
        private String ticketNumber;

        @XmlElement(name = "source_port")
        private String sourcePort;

        @XmlElement(name = "destination_port")
        private String destinationPort;

        @XmlElement(name = "cruise_start")
        @XmlJavaTypeAdapter(ZonedDateTimeAdapter.class)
        private ZonedDateTime cruiseStart;

        @XmlElement(name = "cruise_end")
        @XmlJavaTypeAdapter(ZonedDateTimeAdapter.class)
        private ZonedDateTime cruiseEnd;
}

