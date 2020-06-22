package com.covengers.grouping.component;

import com.covengers.grouping.constant.ResponseCode;
import com.covengers.grouping.dto.vo.RecommendGroupVo;
import com.covengers.grouping.exception.CommonException;
import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.word2vec.Word2Vec;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Word2VecRecommendMaker {

    public RecommendGroupVo recommend(String keyword) {

        try {
            Word2Vec word2Vec = WordVectorSerializer.loadFullModel("./src/main/resources/word2VecModel.txt");

            List<String> groupList =
                    (List)word2Vec.wordsNearest(keyword, 10);

            return RecommendGroupVo
                    .builder()
                    .groupList(groupList)
                    .build();

        } catch (Exception e) {
            throw new CommonException(ResponseCode.UNKNOWN_ERROR);
        }
    }
}
