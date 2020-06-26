package com.covengers.grouping;

import java.util.Iterator;
import java.util.List;

import org.deeplearning4j.models.sequencevectors.interfaces.SequenceIterator;
import org.deeplearning4j.models.sequencevectors.sequence.Sequence;
import org.deeplearning4j.models.word2vec.VocabWord;

import com.covengers.grouping.domain.Group;

public class HashtagLearnIterator implements SequenceIterator<VocabWord> {

    private final Iterator<Group> groupIterator;

    public HashtagLearnIterator(List<Group> groupList) {
        groupIterator = groupList.iterator();
    }

    @Override
    public boolean hasMoreSequences() {
        return groupIterator.hasNext();
    }

    @Override
    public Sequence<VocabWord> nextSequence() {
        final Sequence<VocabWord> vocabWordSequence = new Sequence<>();
        groupIterator.next().getHashtagList().forEach(
                hashtagVo -> vocabWordSequence.addElement(new VocabWord(1.0f, hashtagVo.getHashtag())));
        return vocabWordSequence;
    }

    @Override
    public void reset() {

    }
}
