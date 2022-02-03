package com.util.mongodb.core.warpper.impl;

import com.util.mongodb.core.warpper.AbstractLambdaWrapper;
import com.util.mongodb.core.warpper.AbstractWrapper;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * LambdaQueryWrapper
 *
 * @author ngchunho
 * @version 1.0.0
 * @description
 * @date 2021/7/6 16:00
 */
public class LambdaQueryWrapper<T> extends AbstractLambdaWrapper<T, LambdaQueryWrapper<T>> {

    public LambdaQueryWrapper() {
    }

    public LambdaQueryWrapper(T entity) {
        super.setEntity(entity);
        super.initNeed();
    }

    public LambdaQueryWrapper(Class<T> entityClass) {
        super.setEntityClass(entityClass);
        super.initNeed();
    }

    public LambdaQueryWrapper(Query query) {
        setQuery(query);
    }

    public LambdaQueryWrapper(Criteria criteria) {
        setCriteria(criteria);
    }

    /**
     * QueryWrapper.lambda 构造方法
     */
    public LambdaQueryWrapper(T entity, Class<T> entityClass, Query query, Criteria criteria,
                              boolean orCriteria, boolean andCriteria,
                              List<Criteria> orCriteriaList, List<Criteria> andCriteriaList) {
        super.setEntity(entity);
        super.setEntityClass(entityClass);
        this.query = query;
        this.criteria = criteria;
        this.orCriteria = orCriteria;
        this.andCriteria = andCriteria;
        this.orCriteriaList = orCriteriaList;
        this.andCriteriaList = andCriteriaList;
    }

    @Override
    protected LambdaQueryWrapper<T> instance() {
        return new LambdaQueryWrapper<>();
    }
}
