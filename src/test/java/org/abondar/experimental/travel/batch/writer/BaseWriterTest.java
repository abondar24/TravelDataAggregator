package org.abondar.experimental.travel.batch.writer;

import org.abondar.experimental.travel.mapper.TripInfoMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.doNothing;

@ExtendWith(MockitoExtension.class)
public class BaseWriterTest {

    @Mock
    private TripInfoMapper tripInfoMapper;

    @BeforeEach
    public void init() {
        doNothing().when(tripInfoMapper).insertTripIds(List.of("test"));
    }
}
