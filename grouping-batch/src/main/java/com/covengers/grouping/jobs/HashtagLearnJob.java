package com.covengers.grouping.jobs;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Supplier;

import javax.persistence.EntityManagerFactory;

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
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.covengers.grouping.HashtagVoListExtractor;
import com.covengers.grouping.constants.JobConstants;
import com.covengers.grouping.domain.Group;
import com.covengers.grouping.vo.HashtagVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Configuration
@ConditionalOnProperty(name = JobConstants.VM_OPTION_JOB_NAMES,
        havingValue = "hashtagLearnJob")
public class HashtagLearnJob {

    private static final int CHUNK_SIZE = 10;
    private static final String DELIMITER = "/";
    private static final String CURRENT_FOLDER_PATH = "user.dir";
    private static final String BASED_JOB_NAME = "hashtagLearnJob";
    private static final String MODEL_FILE_NAME = "hashtag_word2vec.txt";
    private static final String HASHTAG_FILE_NAME = "hashtag.txt";

    private static final Supplier<Path> GET_HASHTAG_FILE_PATH_SUPPLIER = () ->
            Paths.get(System.getProperty(CURRENT_FOLDER_PATH) + DELIMITER
                      + BASED_JOB_NAME + DELIMITER + HASHTAG_FILE_NAME);

    private static final Supplier<Path> GET_MODEL_FILE_PATH_SUPPLIER = () ->
            Paths.get(System.getProperty(CURRENT_FOLDER_PATH) + DELIMITER
                      + BASED_JOB_NAME + DELIMITER + MODEL_FILE_NAME);

    private static final int BATCH_SIZE = 1000;
    private static final int ITERATIONS = 3;
    private static final int LAYER_SIZE = 150;

    private final EntityManagerFactory entityManagerFactory;

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job hashtagLearningJob() {
        return jobBuilderFactory.get("hashtagLearnJob")
                                .incrementer(new RunIdIncrementer())
                                .start(extractHashtagStep())
                                .next(learnHashtagStep())
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
    public ItemProcessor<Group, List<HashtagVo>> extractHashtagFromGroupProcessor() {
        return Group::getHashtagList;
    }

    @StepScope
    @Bean
    public FlatFileItemWriter<List<HashtagVo>> hashTagWriter() {

        final Path filePath = GET_HASHTAG_FILE_PATH_SUPPLIER.get();
        if (!filePath.toFile().exists()) {
            try {
                Files.createDirectory(filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        final HashtagVoListExtractor<List<HashtagVo>> fieldExtractor =
                new HashtagVoListExtractor<>();

        fieldExtractor.setNames(
                new String[] { "hashtag" });

        fieldExtractor.afterPropertiesSet();

        final DelimitedLineAggregator<List<HashtagVo>> lineAggregator =
                new DelimitedLineAggregator<>();
        lineAggregator.setDelimiter(" ");
        lineAggregator.setFieldExtractor(fieldExtractor);

        return new FlatFileItemWriterBuilder<List<HashtagVo>>()
                .name("hashTagWriter")
                .resource(new FileSystemResource(GET_HASHTAG_FILE_PATH_SUPPLIER.get().toFile()))
                .lineAggregator(lineAggregator)
                .build();
    }

    @Bean
    public Step extractHashtagStep() {
        return stepBuilderFactory.get("extractHashtagStep")
                .<Group, List<HashtagVo>>chunk(CHUNK_SIZE)
                .reader(hashtagLearnReader())
                .processor(extractHashtagFromGroupProcessor())
                .writer(hashTagWriter())
                .faultTolerant()
                .build();
    }

    @Bean
    public Step learnHashtagStep() {
        return stepBuilderFactory.get("learnHashtagStep")
                                 .tasklet((contribution, chunkContext) -> {
                                     SentenceIterator iter = null;
                                     try {
                                         iter = new BasicLineIterator(GET_HASHTAG_FILE_PATH_SUPPLIER.get().toString());
                                     } catch (FileNotFoundException e) {
                                         e.printStackTrace();
                                     }

                                     final TokenizerFactory tokenizerFactory = new DefaultTokenizerFactory();
                                     tokenizerFactory.setTokenPreProcessor(new CommonPreprocessor());

                                     final Word2Vec vec = new Word2Vec.Builder()
                                             .batchSize(BATCH_SIZE)
                                             .minWordFrequency(1)
                                             .useAdaGrad(false)
                                             .layerSize(LAYER_SIZE)
                                             .iterations(ITERATIONS)
                                             .learningRate(0.225)
                                             .minLearningRate(1e-3)
                                             .negativeSample(10)
                                             .tokenizerFactory(tokenizerFactory)
                                             .iterate(iter)
                                             .build();

                                     vec.fit();

                                     WordVectorSerializer.writeFullModel(vec,
                                             GET_MODEL_FILE_PATH_SUPPLIER.get().toString());

                                     log.info("band : " + vec.wordsNearest("band", 2));
                                     log.info("music : " + vec.wordsNearest("music", 2));
                                     log.info("soccer : " + vec.wordsNearest("soccer", 2));
                                     log.info("java" + vec.wordsNearest("java", 2));

                                     return RepeatStatus.FINISHED;
                                 })
                                 .build();
    }
}
