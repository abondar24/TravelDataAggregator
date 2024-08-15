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

            default -> throw new IllegalArgumentException("Invalid file type");
            //TODO: add case for flight once flight job is implemented
            //TODO: add case for aggregeate once agg job is  implemented
        }

        return path;
    }
}
