package com.covengers.grouping.jobs;

import com.covengers.grouping.constants.JobConstants;
import com.covengers.grouping.domain.Group;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.word2vec.Word2Vec;
import org.deeplearning4j.text.sentenceiterator.BasicLineIterator;
import org.deeplearning4j.text.sentenceiterator.SentenceIterator;
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
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;
import java.util.Iterator;
import java.util.Optional;

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
                .queryString("SELECT a FROM Group a")
                .build();
    }

    @StepScope
    @Bean
    public ItemProcessor<Group, Group> hashtagLearnProcessor() {

        int batchSize = 1000;
        int iterations = 3;
        int layerSize = 150;

        Word2Vec vec = new Word2Vec.Builder()
                .batchSize(batchSize)
                .minWordFrequency(1)
                .useAdaGrad(false)
                .layerSize(layerSize)
                .iterations(iterations)
                .learningRate(0.025)
                .minLearningRate(1e-3)
                .negativeSample(10)
                .build();

        return group -> {
            final TokenizerFactory tokenizerFactory = new DefaultTokenizerFactory();
            tokenizerFactory.setTokenPreProcessor(new CommonPreprocessor());

            Optional<String> optionalString =  group.toHashtagList().stream()
                                                                    .map(hashtagVo -> hashtagVo.getHashtag())
                                                                    .reduce((a,b) -> a + " " + b);
            if(!optionalString.isPresent()) {
                return group;
            }
            //vec.setSentenceIterator(new Iterator(optionalString.get()));
            vec.setTokenizerFactory(tokenizerFactory);
            vec.fit();
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
