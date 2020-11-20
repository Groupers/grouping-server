package com.covengers.grouping.component;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.word2vec.Word2Vec;
import org.springframework.stereotype.Component;

import com.covengers.grouping.constant.ResponseCode;
import com.covengers.grouping.exception.CommonException;
import com.covengers.grouping.vo.RecommendHashtagVo;

@Component
public class HashtagRecommender {
    private static final int RECOMMEND_WORD_COUNT = 10;
    private static final String DELIMITER = "/";
    private static final String CURRENT_FOLDER_PATH = "user.dir";
    private static final String BASED_JOB_NAME = "hashtagLearnJob";
    private static final String MODEL_FILE_NAME = "hashtag_word2vec.txt";

    private static final Supplier<Path> GET_MODEL_FILE_PATH_SUPPLIER = () ->
            Paths.get(System.getProperty(CURRENT_FOLDER_PATH) + DELIMITER
                    + BASED_JOB_NAME + DELIMITER + MODEL_FILE_NAME);

    public RecommendHashtagVo recommend(String keyword) {

        try {
            final Word2Vec word2Vec = WordVectorSerializer.loadFullModel(
                    GET_MODEL_FILE_PATH_SUPPLIER.get().toString());

            final List<String> hashtagList =
                    word2Vec.wordsNearest(keyword, RECOMMEND_WORD_COUNT)
                            .stream()
                            .collect(Collectors.toList());

            return RecommendHashtagVo
                    .builder()
                    .hashtagList(hashtagList)
                    .build();

        } catch (Exception e) {
            throw new CommonException(ResponseCode.UNKNOWN_ERROR);
        }
    }
}
