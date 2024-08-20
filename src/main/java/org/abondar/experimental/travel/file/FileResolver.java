package org.abondar.experimental.travel.file;

import lombok.RequiredArgsConstructor;
import org.abondar.experimental.travel.properties.FileProperties;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileResolver {

    private final FileProperties fileProperties;

    public String resolveFile(FileType fileType) {
        var path = "";
        switch (fileType) {
            case HOTEL -> path = fileProperties.getHotel();

            case FLIGHT -> path = fileProperties.getFlight();

            case CRUISE -> path = fileProperties.getCruise();

            case REPORT -> path = fileProperties.getReport();

            default -> throw new IllegalArgumentException("Invalid file type");

        }

        return path;
    }
}
