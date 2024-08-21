package org.abondar.experimental.travel.config;

import org.abondar.experimental.travel.batch.listener.JobCompletionListener;
import org.abondar.experimental.travel.batch.processor.FlightBookingItemProcessor;
import org.abondar.experimental.travel.batch.writer.FlightBookingItemWriter;
import org.abondar.experimental.travel.file.FileService;
import org.abondar.experimental.travel.file.FileType;
import org.abondar.experimental.travel.model.batch.FlightBatchItemFieldSetMapper;
import org.abondar.experimental.travel.model.batch.FlightBatchItem;
import org.abondar.experimental.travel.model.db.FlightBooking;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@ConditionalOnProperty(name = "spring.batch.job.name", havingValue = "flightBookingsJob")
public class FlightBookingBatchConfig {

    @Bean
    @Qualifier("flightBookingsJob")
    public Job importFlightBookingsJob(JobRepository jobRepository, @Qualifier("flightBookingStep") Step flightBookingStep,
                                       JobCompletionListener listener) {
        return new JobBuilder("flightBookingsJob", jobRepository)
                .listener(listener)
                .start(flightBookingStep)
                .incrementer(new RunIdIncrementer())
                .build();
    }

    @Bean
    public FlatFileItemReader<FlightBatchItem> flightBookingsReader(FlightBatchItemFieldSetMapper fieldSetMapper,
                                                                    FileService fileService) {

        var itemReader = new FlatFileItemReader<FlightBatchItem>();
        itemReader.setResource(fileService.getFile(FileType.FLIGHT));

        itemReader.setLineMapper(new DefaultLineMapper<>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{

                setNames("tripId", "sourceCity", "destinationCity", "flightNumber", "ticketNumber",
                        "departureTime", "arrivalTime");
            }});
            setFieldSetMapper(fieldSetMapper);
        }});
        return itemReader;
    }

    @Bean
    @Qualifier("flightBookingStep")
    public Step flightBookingStep(JobRepository jobRepository,
                                  FlatFileItemReader<FlightBatchItem> reader, FlightBookingItemProcessor processor,
                                  PlatformTransactionManager transactionManager, FlightBookingItemWriter writer) {
        return new StepBuilder("flightBookingsStep", jobRepository)
                .<FlightBatchItem, FlightBooking>chunk(3, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }


}
