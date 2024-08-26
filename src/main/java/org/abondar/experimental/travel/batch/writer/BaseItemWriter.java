package org.abondar.experimental.travel.batch.writer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.abondar.experimental.travel.mapper.TripInfoMapper;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public abstract class BaseItemWriter<T> implements ItemWriter<T> {

    protected final TripInfoMapper tripInfoMapper;

    protected abstract void insertBookings(List<T> bookings);

    protected abstract String getTripId(T booking);

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public void write(Chunk<? extends T> chunk) throws Exception {
        var bookings = (List<T>) chunk.getItems();

        if (!bookings.isEmpty()) {
            insertBookings(bookings);

            var tripIds = bookings.stream()
                    .map(this::getTripId)
                    .distinct()
                    .toList();

            tripInfoMapper.insertTripIds(tripIds);

            log.info("{} bookings saved to db", bookings.get(0).getClass().getSimpleName());
        }

    }

}
