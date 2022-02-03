package com.util.mongodb.core.warpper;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.query.Query;

import java.io.Serializable;

/**
 * Wrapper
 *
 * @author ngchunho
 * @version 1.0.0
 * @description
 * @date 2021/7/6 15:35
 */
public abstract class Wrapper<T> implements Serializable {

    /**
     * 实体对象（子类实现）
     *
     * @return 泛型 T
     */
    public abstract T getEntity();

    /**
     * 获取query查询条件
     */
    public abstract Query getQueryCondition();

    public abstract Query getQueryCondition(PageRequest page);

//    public abstract Class<T> getEntityClass();

    public abstract Query page(PageRequest page);
}
