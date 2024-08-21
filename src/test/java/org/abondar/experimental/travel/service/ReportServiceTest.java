package org.abondar.experimental.travel.service;

import org.abondar.experimental.travel.mapper.TravelReportMapper;
import org.abondar.experimental.travel.mapper.TripInfoMapper;
import org.abondar.experimental.travel.model.report.HotelBookingReportItem;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReportServiceTest {

    @Mock
    private TripInfoMapper tripInfoMapper;

    @Mock
    private TravelReportMapper travelReportMapper;

    @InjectMocks
    private ReportService reportService;


    @Mock
    private TemplateEngine templateEngine;


    @Test
    void buildReportTest() {
        var tripIds = List.of("trip1", "trip2");
        when(tripInfoMapper.selectRecentTripIds()).thenReturn(tripIds);

        var reportItems = List.of(
                HotelBookingReportItem.builder().tripId("trip1").hotelCity("City1").build(),
                HotelBookingReportItem.builder().tripId("trip2").hotelCity("City2").build()
        );
        when(travelReportMapper.getDataByTripIds(tripIds)).thenReturn(reportItems);

        when(templateEngine.process(eq("report"), any(Context.class))).thenReturn("Generated HTML");

        String result = reportService.buildReport();

        assertEquals("Generated HTML", result);
        verify(tripInfoMapper).selectRecentTripIds();
        verify(travelReportMapper).getDataByTripIds(tripIds);
        verify(templateEngine).process(eq("report"), any(Context.class));
    }

}
