package com.util.mongodb.common.page;

import org.springframework.data.domain.PageRequest;

import java.io.Serializable;
import java.util.List;

/**
 * IPage
 *
 * @author ngchunho
 * @version 1.0.0
 * @description
 * @date 2022/1/27 14:52
 */
public interface IPage<T> extends Serializable {

    /**
     * 分页记录列表
     *
     * @return 分页对象记录列表
     */
    List<T> getRecords();

    /**
     * 设置分页记录列表
     */
    IPage<T> setRecords(List<T> records);

    /**
     * 当前满足条件总行数
     *
     * @return 总条数
     */
    long getTotal();

    /**
     * 设置当前满足条件总行数
     */
    IPage<T> setTotal(long total);

    /**
     * 获取每页显示条数
     *
     * @return 每页显示条数
     */
    long getSize();

    /**
     * 设置每页显示条数
     */
    IPage<T> setSize(long size);

    /**
     * 当前页，默认 1
     *
     * @return 当前页
     */
    long getCurrent();

    /**
     * 设置当前页
     */
    IPage<T> setCurrent(long current);

    /**
     * 转化为PageRequest对象
     */
    <E extends IPage<T>> PageRequest toPageRequest();
}
