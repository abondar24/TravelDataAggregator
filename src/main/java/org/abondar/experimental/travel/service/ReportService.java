package org.abondar.experimental.travel.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.abondar.experimental.travel.mapper.TravelReportMapper;
import org.abondar.experimental.travel.mapper.TripInfoMapper;
import org.abondar.experimental.travel.model.report.HotelBookingReportItem;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReportService {

    private final TripInfoMapper tripInfoMapper;

    private final TravelReportMapper travelReportMapper;

    private final TemplateEngine templateEngine;


    public String buildReport() {
        var latestTripIds = tripInfoMapper.selectRecentTripIds();
        log.info("Fetched latest trip ids: {}", latestTripIds);

        var reportData = travelReportMapper.getDataByTripIds(latestTripIds);
        log.info("Fetched raw report data");

        return buildHtmlReport(reportData);
    }


    private String buildHtmlReport(List<HotelBookingReportItem> reportData) {
        var context = new Context();
        context.setVariable("reports", reportData);

        log.info("Building HTML report");
        return templateEngine.process("report", context);
    }

}
