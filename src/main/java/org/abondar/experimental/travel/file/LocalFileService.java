package org.abondar.experimental.travel.file;


import lombok.RequiredArgsConstructor;
import org.abondar.experimental.travel.properties.BatchLocalProperties;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LocalFileService implements FileService {


    private final BatchLocalProperties batchLocalProperties;


    @Override
    public Resource getFile(FileType fileType) {

        switch (fileType){
            case HOTEL -> {
                return new FileSystemResource(batchLocalProperties.getHotelFile());
            }
           //TODO: add case for flight once flight job is implemented
           //TODO: add case for aggregeate once agg job is  implemented
        }

        return null;
    }
}
