package com.example.batch.batchprocessing;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class MyBatchConfiguration {

    @Bean
    public FlatFileItemReader<Foo> fooReader() {
        return new FlatFileItemReaderBuilder<Foo>()
                .name("fooReader")
                .resource(new ClassPathResource("hello"))
                .delimited()
                .names(new String[]{"some", "thing"})
                .fieldSetMapper(
                        new BeanWrapperFieldSetMapper<Foo>(){{
                            setTargetType(Foo.class);
                        }})
                .build();
    }

    @Bean
    public FooProcessor fooProcessor() {
        return new FooProcessor();
    }

    @Bean
    public BarWriter barWriter() {
        return new BarWriter();
    }

    @Bean
    public Job ioSampleJob(JobRepository jobRepository,
                           Step step2) {
        return new JobBuilder("ioSampleJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .flow(step2)
                .end().build();
    }


    @Bean
    public Step step2(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("step1", jobRepository)
                .<Foo, Bar>chunk(2, transactionManager)
                .reader(fooReader())
                .processor(fooProcessor())
                .writer(barWriter())
                .build();
    }
}
