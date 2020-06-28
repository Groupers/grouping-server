package com.covengers.grouping;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.Assert;

import com.covengers.grouping.vo.HashtagVo;

public class HashtagVoListExtractor<T> extends BeanWrapperFieldExtractor<T> {

    private String[] names;

    public void setNames(String[] names) {
        Assert.notNull(names, "Names must be non-null");
        this.names = names.clone();
    }

    @Override
    public Object[] extract(Object item) {
        final List<Object> values = new ArrayList<>();

        final List<HashtagVo> list= (List<HashtagVo>) item;
        list.forEach(o -> {
            final BeanWrapper bw = new BeanWrapperImpl(o);
            for (String propertyName : names) {
                values.add(bw.getPropertyValue(propertyName));
            }

        });
        return values.toArray();
    }
    @Override
    public void afterPropertiesSet() {
        Assert.notNull(names, "The 'names' property must be set.");
    }
}


