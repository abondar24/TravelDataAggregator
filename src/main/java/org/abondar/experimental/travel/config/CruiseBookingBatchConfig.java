package org.abondar.experimental.travel.config;

import org.abondar.experimental.travel.batch.listener.CruiseBookingCompletionListener;
import org.abondar.experimental.travel.batch.processor.CruiseBookingItemProcessor;
import org.abondar.experimental.travel.batch.writer.CruiseBookingItemWriter;
import org.abondar.experimental.travel.file.FileService;
import org.abondar.experimental.travel.file.FileType;
import org.abondar.experimental.travel.model.batch.CruiseBatchItem;
import org.abondar.experimental.travel.model.db.CruiseBooking;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.batch.item.xml.builder.StaxEventItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@ConditionalOnProperty(name = "spring.batch.job.name", havingValue = "cruiseBookingsJob")
public class CruiseBookingBatchConfig {

    @Bean
    @Qualifier("cruiseBookingsJob")
    public Job importCruiseBookingsJob(JobRepository jobRepository,
                                       @Qualifier("cruiseBookingStep") Step cruiseBookingStep,
                                       CruiseBookingCompletionListener listener) {
        return new JobBuilder("cruiseBookingsJob", jobRepository)
                .listener(listener)
                .start(cruiseBookingStep)
                .incrementer(new RunIdIncrementer())
                .build();
    }


    @Bean
    public StaxEventItemReader<CruiseBatchItem> cruiseBookingReader(FileService fileService,
                                                                    Jaxb2Marshaller cruiseMarshaller) {

        return new StaxEventItemReaderBuilder<CruiseBatchItem>()
                .name("cruiseItemReader")
                .resource(fileService.getFile(FileType.CRUISE))
                .addFragmentRootElements("cruise")
                .unmarshaller(cruiseMarshaller)
                .build();
    }


    @Bean
    public Jaxb2Marshaller cruiseMarshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setClassesToBeBound(CruiseBatchItem.class);

        return marshaller;
    }


    @Bean
    @Qualifier("cruiseBookingStep")
    public Step cruiseBookingStep(JobRepository jobRepository,
                                  StaxEventItemReader<CruiseBatchItem> reader, CruiseBookingItemProcessor processor,
                                  PlatformTransactionManager transactionManager, CruiseBookingItemWriter writer) {
        return new StepBuilder("cruiseBookingsStep", jobRepository)
                .<CruiseBatchItem, CruiseBooking>chunk(3, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }
}
