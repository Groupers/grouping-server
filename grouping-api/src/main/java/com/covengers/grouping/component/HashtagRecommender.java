package com.covengers.grouping.component;

import java.util.List;
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

    public RecommendHashtagVo recommend(String keyword) {

        try {
            final Word2Vec word2Vec = WordVectorSerializer.loadFullModel(
                    "./src/main/resources/word2VecModel.txt");

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
