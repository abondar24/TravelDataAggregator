package org.abondar.experimental.travel.file;


import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@ConditionalOnProperty(name = "sftp.enabled", havingValue = "false")
public class LocalFileService implements FileService {


    private final FileResolver fileResolver;


    @Override
    public Resource getFile(FileType fileType) {

        var filePath = fileResolver.resolveFile(fileType);
        return new FileSystemResource(filePath);
    }
}
