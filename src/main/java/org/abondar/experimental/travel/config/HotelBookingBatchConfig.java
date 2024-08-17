package org.abondar.experimental.travel.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.abondar.experimental.travel.batch.listener.HotelBookingCompletionListener;
import org.abondar.experimental.travel.batch.processor.HotelBookingItemProcessor;
import org.abondar.experimental.travel.batch.writer.HotelBookingItemWriter;
import org.abondar.experimental.travel.file.FileService;
import org.abondar.experimental.travel.file.FileType;
import org.abondar.experimental.travel.model.batch.HotelBatchItem;
import org.abondar.experimental.travel.model.db.HotelBooking;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
@ConditionalOnProperty(name ="spring.batch.job.name" ,havingValue = "hotelBookingsJob")
public class HotelBookingBatchConfig {



    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        return new ObjectMapper()
                .registerModule(new JavaTimeModule());
    }


    @Bean
    @Qualifier("hotelBookingsJob")
    public Job importHotelBookingsJob(JobRepository jobRepository, @Qualifier("hotelBookingStep") Step step1,
                                      HotelBookingCompletionListener listener) {
        return new JobBuilder("hotelBookingsJob", jobRepository)
                .listener(listener)
                .start(step1)
                .incrementer(new RunIdIncrementer())
                .build();
    }

    @Bean
    public JsonItemReader<HotelBatchItem> hotelBookingReader(ObjectMapper objectMapper,FileService fileService) {

        return new JsonItemReaderBuilder<HotelBatchItem>()
                .resource(fileService.getFile(FileType.HOTEL))
                .jsonObjectReader(new JacksonJsonObjectReader<>(objectMapper, HotelBatchItem.class))
                .name("hotelBookingsReader")
                .build();
    }

    @Bean
    @Qualifier("hotelBookingStep")
    public Step hotelBookingStep(JobRepository jobRepository,
                                 JsonItemReader<HotelBatchItem> reader, HotelBookingItemProcessor processor,
                                 PlatformTransactionManager transactionManager, HotelBookingItemWriter writer) {
        return new StepBuilder("hotelBookingsStep", jobRepository)
                .<HotelBatchItem, HotelBooking>chunk(3, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }


}
