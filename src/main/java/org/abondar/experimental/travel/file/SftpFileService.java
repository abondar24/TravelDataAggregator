package org.abondar.experimental.travel.file;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.integration.sftp.session.DefaultSftpSessionFactory;
import org.springframework.integration.sftp.session.SftpSession;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
@RequiredArgsConstructor
@ConditionalOnProperty(name = "sftp.enabled", havingValue = "true")
@Slf4j
public class SftpFileService implements FileService {

    private final DefaultSftpSessionFactory sftpSessionFactory;

    private final FileResolver fileResolver;


    @Override
    public Resource getFile(FileType fileType) {
        var filePath = fileResolver.resolveFile(fileType);

        try (SftpSession session = sftpSessionFactory.getSession();
             InputStream inputStream = session.readRaw(filePath)) {

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesRead);
            }

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());

            return new ByteArrayResource(byteArrayInputStream.readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException("Failed to download file from SFTP", e);
        }
    }

}
