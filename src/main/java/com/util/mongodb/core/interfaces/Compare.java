package com.util.mongodb.core.interfaces;

import java.io.Serializable;

/**
 * Compare
 *
 * @author ngchunho
 * @version 1.0.0
 * @description
 * @date 2021/7/6 15:14
 */
public interface Compare<Children, R> extends Serializable {

    /**
     * 等于 =
     *
     * @param column 字段
     * @param val    值
     * @return children
     */
    Children eq(R column, Object val);

    /**
     * 不等于 <>
     *
     * @param condition 执行条件
     * @param column    字段
     * @param val       值
     * @return children
     */
    Children ne(R column, Object val);

    /**
     * 大于 >
     *
     * @param column 字段
     * @param val    值
     * @return children
     */
    Children gt(R column, Object val);

    /**
     * 大于等于 >=
     *
     * @param column 字段
     * @param val    值
     * @return children
     */
    Children ge(R column, Object val);

    /**
     * 小于 <
     *
     * @param column 字段
     * @param val    值
     * @return children
     */
    Children lt(R column, Object val);

    /**
     * 小于等于 <=
     *
     * @param column 字段
     * @param val    值
     * @return children
     */
    Children le(R column, Object val);

    /**
     * BETWEEN 值1 AND 值2
     *
     * @param column 字段
     * @param val1   值1
     * @param val2   值2
     * @return children
     */
    Children between(R column, Object val1, Object val2);

    /**
     * NOT BETWEEN 值1 AND 值2
     *
     * @param column 字段
     * @param val1   值1
     * @param val2   值2
     * @return children
     */
    Children notBetween(R column, Object val1, Object val2);

    /**
     * LIKE '%值%'
     *
     * @param column 字段
     * @param val    值
     * @return children
     */
    Children like(R column, Object val);

    /**
     * NOT LIKE '%值%'
     *
     * @param column 字段
     * @param val    值
     * @return children
     */
    Children notLike(R column, Object val);

    /**
     * LIKE '%值'
     *
     * @param column 字段
     * @param val    值
     * @return children
     */
    Children likeLeft(R column, Object val);

    /**
     * LIKE '值%'
     *
     * @param column 字段
     * @param val    值
     * @return children
     */
    Children likeRight(R column, Object val);
}
