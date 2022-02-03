package com.util.mongodb.core.interfaces;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

/**
 * Function
 *
 * @author ngchunho
 * @version 1.0.0
 * @description
 * @date 2021/7/7 14:50
 */
public interface Function<Children, R> extends Serializable {

    /**
     * 字段 IS NULL
     * <p>例: isNull("name")</p>
     *
     * @param column 字段
     * @return children
     */
    Children isNull(R column);

    /**
     * 字段 IS NOT NULL
     * <p>例: isNotNull("name")</p>
     *
     * @param column 字段
     * @return children
     */
    Children isNotNull(R column);

    /**
     * 字段 IN (value.get(0), value.get(1), ...)
     * <p>例: in("id", Arrays.asList(1, 2, 3, 4, 5))</p>
     *
     * @param column 字段
     * @param coll   数据集合
     * @return children
     */
    Children in(R column, Collection<?> coll);

    /**
     * 字段 NOT IN (value.get(0), value.get(1), ...)
     * <p>例: notIn("id", Arrays.asList(1, 2, 3, 4, 5))</p>
     *
     * @param column 字段
     * @param coll   数据集合
     * @return children
     */
    Children notIn(R column, Collection<?> coll);

    /**
     * 排序：ORDER BY 字段, ... ASC
     * <p>例: orderByAsc("name")</p>
     *
     * @param column 字段
     * @return children
     */
    Children orderByAsc(R column);

    /**
     * 排序：ORDER BY 字段, ... ASC
     * <p>例: orderByAsc("id", "name")</p>
     *
     * @param columns 字段数组
     * @return children
     */
    Children orderByAsc(R... columns);

    /**
     * 排序：ORDER BY 字段, ... DESC
     * <p>例: orderByDesc("name")</p>
     *
     * @param column 字段
     * @return children
     */
    Children orderByDesc(R column);

    /**
     * 排序：ORDER BY 字段, ... DESC
     * <p>例: orderByDesc("id", "name")</p>
     *
     * @param columns 字段数组
     * @return children
     */
    Children orderByDesc(R... columns);

    // TODO group by 需要使用Aggregation和统计同时使用
    Children groupBy(R... columns);

    // TODO having 需要Aggregation和统计同时使用
    Children having(Map<String, String> columnMap);
}
