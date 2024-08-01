package org.abondar.experimental.travel.file;


import org.springframework.core.io.Resource;

@FunctionalInterface
public interface FileService {

    Resource getFile(FileType fileType);
}
