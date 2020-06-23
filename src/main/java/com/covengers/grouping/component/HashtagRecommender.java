package com.covengers.grouping.component;

import com.covengers.grouping.constant.ResponseCode;
import com.covengers.grouping.dto.vo.RecommendHashtagVo;
import com.covengers.grouping.exception.CommonException;
import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.word2vec.Word2Vec;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HashtagRecommender {
    private final int recommendWordCount = 10;
    public RecommendHashtagVo recommend(String keyword) {

        try {
            final Word2Vec word2Vec = WordVectorSerializer.loadFullModel("./src/main/resources/word2VecModel.txt");

            final List<String> hashtagList = (List)word2Vec.wordsNearest(keyword, recommendWordCount);

            return RecommendHashtagVo
                    .builder()
                    .hashtagList(hashtagList)
                    .build();

        } catch (Exception e) {
            throw new CommonException(ResponseCode.UNKNOWN_ERROR);
        }
    }
}
