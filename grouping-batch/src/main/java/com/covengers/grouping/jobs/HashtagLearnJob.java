package com.covengers.grouping.jobs;

import javax.persistence.EntityManagerFactory;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.covengers.grouping.constants.JobConstants;
import com.covengers.grouping.domain.Group;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Configuration
@ConditionalOnProperty(name = JobConstants.VM_OPTION_JOB_NAMES,
        havingValue = "hashtagLearnJob")
public class HashtagLearnJob {

    private static final int CHUNK_SIZE = 10;

    private final EntityManagerFactory entityManagerFactory;

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job hashtagLearningJob() {
        return jobBuilderFactory.get("hashtagLearnJob")
                                .incrementer(new RunIdIncrementer())
                                .start(hashtagLearnJobStep())
                                .build();
    }


    @StepScope
    @Bean
    public JpaPagingItemReader<Group> hashtagLearnReader() {
        return new JpaPagingItemReaderBuilder<Group>()
                .name("groupReader")
                .entityManagerFactory(entityManagerFactory)
                .pageSize(CHUNK_SIZE)
                .queryString("SELECT a FROM Crew a")
                .build();
    }

    @StepScope
    @Bean
    public ItemProcessor<Group, Group> hashtagLearnProcessor() {

        return group -> {
            // 여기에서 학습.
            return group;
        };
    }

    @StepScope
    @Bean
    public JpaItemWriter<Group> writer() {
        final JpaItemWriter<Group> writer = new JpaItemWriter<>();
        writer.setEntityManagerFactory(entityManagerFactory);
        return writer;
    }


        @Bean
    public Step hashtagLearnJobStep() {
        return stepBuilderFactory.get("hashtagLearnJobStep")
                .<Group, Group>chunk(CHUNK_SIZE)
                .reader(hashtagLearnReader())
                .processor(hashtagLearnProcessor())
                .writer(writer())
                .faultTolerant()
                .build();
    }
}
