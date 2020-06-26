package com.covengers.grouping.jobs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.word2vec.Word2Vec;
import org.deeplearning4j.text.tokenization.tokenizer.preprocessor.CommonPreprocessor;
import org.deeplearning4j.text.tokenization.tokenizerfactory.DefaultTokenizerFactory;
import org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory;
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
import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.covengers.grouping.HashtagLearnIterator;
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
    private final List<Group> groupList = new ArrayList<>();

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private Word2Vec vec;

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
                .queryString("SELECT a FROM Group a")
                .build();
    }

    @StepScope
    @Bean
    public CompositeItemProcessor<Group, Group> hashtagLearnProcessor() {
        final CompositeItemProcessor<Group, Group> processor =
                new CompositeItemProcessor<>();

        processor.setDelegates(Arrays.asList(learnProcessor(), saveModelProcessor()));

        return processor;
    }


    public ItemProcessor<Group, Group> learnProcessor() {
        return group -> {
            groupList.add(group);
            return group;
        };
    }

    public ItemProcessor<Group, Group> saveModelProcessor(){
        final int batchSize = 1000;
        final int iterations = 3;
        final int layerSize = 150;

        vec = new Word2Vec.Builder()
                .batchSize(batchSize)
                .minWordFrequency(1)
                .useAdaGrad(false)
                .layerSize(layerSize)
                .iterations(iterations)
                .learningRate(0.025)
                .minLearningRate(1e-3)
                .negativeSample(10)

                .build();

        final TokenizerFactory tokenizerFactory = new DefaultTokenizerFactory();
        tokenizerFactory.setTokenPreProcessor(new CommonPreprocessor());
        vec.setTokenizerFactory(tokenizerFactory);
        vec.setSequenceIterator(new HashtagLearnIterator(groupList));
        vec.fit();

        try {
            WordVectorSerializer.writeWordVectors(vec, "pathToWriteto.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return group -> {
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
