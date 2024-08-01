package org.abondar.experimental.travel.batch.hotel;


import lombok.RequiredArgsConstructor;
import org.abondar.experimental.travel.file.FileService;
import org.abondar.experimental.travel.file.FileType;
import org.abondar.experimental.travel.mapper.HotelBookingMapper;
import org.abondar.experimental.travel.model.batch.HotelBatchItem;
import org.abondar.experimental.travel.model.db.HotelBooking;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class HotelBookingBatchConfig {


    private final FileService fileService;

    @Bean
    public HotelBookingItemProcessor hotelBookingItemProcessor() {
        return new HotelBookingItemProcessor();
    }


    @Bean
    public HotelBookingItemWriter writer(HotelBookingMapper hotelBookingMapper) {

        return new HotelBookingItemWriter(hotelBookingMapper);
    }

    @Bean
    public Job importHotelBookingsJob(JobRepository jobRepository, Step step1, HotelBookingCompletionListener listener) {
        return new JobBuilder("importHotelBookingsJob", jobRepository)
                .listener(listener)
                .start(step1)
                .build();
    }

    @Bean
    public JsonItemReader<HotelBatchItem> hotelBookingReader() {

        return new JsonItemReaderBuilder<HotelBatchItem>()
                .resource(fileService.getFile(FileType.HOTEL))
                .jsonObjectReader(new JacksonJsonObjectReader<>(HotelBatchItem.class))
                .name("hotelBookingReader")
                .build();
    }

    @Bean
    public Step step1(JobRepository jobRepository,
                      JsonItemReader<HotelBatchItem> reader, HotelBookingItemProcessor processor,
                      PlatformTransactionManager transactionManager, HotelBookingItemWriter writer) {
        return new StepBuilder("step1", jobRepository)
                .<HotelBatchItem, HotelBooking>chunk(3, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }


}
