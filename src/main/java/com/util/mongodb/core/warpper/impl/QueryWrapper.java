package com.util.mongodb.core.warpper.impl;

import com.util.mongodb.core.warpper.AbstractWrapper;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

/**
 * QueryWrapper
 *
 * @author ngchunho
 * @version 1.0.0
 * @description
 * @date 2021/7/6 15:52
 */
public class QueryWrapper<T> extends AbstractWrapper<T, String, QueryWrapper<T>> {

    public QueryWrapper() {
    }

    public QueryWrapper(T entity) {
        super.setEntity(entity);
        super.initNeed();
    }

    public QueryWrapper(Query query) {
        setQuery(query);
    }

    public QueryWrapper(Criteria criteria) {
        setCriteria(criteria);
    }

    public QueryWrapper(Query query, Criteria criteria) {
        setQuery(query);
        setCriteria(criteria);
    }

    @Override
    protected QueryWrapper<T> instance() {
//        return new QueryWrapper<>(getQuery(), getCriteria());
        return new QueryWrapper<>();
    }

    /**
     * 返回一个支持 lambda 函数写法的 wrapper
     */
    public LambdaQueryWrapper<T> lambda() {
        return new LambdaQueryWrapper<>(getEntity(), getEntityClass(), query, criteria,
                orCriteria, andCriteria, orCriteriaList, andCriteriaList);
    }
}
