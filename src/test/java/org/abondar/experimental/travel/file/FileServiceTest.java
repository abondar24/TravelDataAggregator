package org.abondar.experimental.travel.file;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ByteArrayResource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FileServiceTest {


    @Mock
    private FileService fileService;

    @Test
    public void getFileTest() throws Exception {
        var resource = new ByteArrayResource("test".getBytes());

        when(fileService.getFile(FileType.HOTEL)).thenReturn(resource);

        var retrieved = fileService.getFile(FileType.HOTEL);

        assertNotNull(retrieved);
        assertEquals(resource, retrieved);
    }
}
