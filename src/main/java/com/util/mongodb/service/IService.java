package com.util.mongodb.service;

import com.util.mongodb.common.page.IPage;
import com.util.mongodb.core.warpper.Wrapper;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * IService
 *
 * @author ngchunho
 * @version 1.0.0
 * @description
 * @date 2022/1/25 16:31
 */
public interface IService<T> extends Serializable {

    /**
     * 新增实体
     *
     * @param entity
     * @return
     */
    T save(T entity);

    /**
     * 批量新增实体
     *
     * @param entityList
     * @return
     */
    Collection<T> saveBatch(Collection<T> entityList);

    /**
     * 更新实体
     *
     * @param entity
     * @return
     */
    T updateById(T entity);

    /**
     * 批量更新实体
     *
     * @param entityList
     * @return
     */
    Collection<T> updateBatchById(Collection<T> entityList);

    /**
     * 删除实体
     *
     * @param entity
     * @return
     */
    boolean remove(T entity);

    /**
     * 条件删除实体
     *
     * @param wrapper
     * @return
     */
    Collection<T> remove(Wrapper<T> wrapper);

    /**
     * 统计总数
     *
     * @return
     */
    long count();

    /**
     * 条件统计总数
     *
     * @param wrapper
     * @return
     */
    long count(Wrapper<T> wrapper);

    /**
     * 列表查询
     *
     * @return
     */
    List<T> list();

    /**
     * 条件列表查询
     *
     * @param wrapper
     * @return
     */
    List<T> list(Wrapper<T> wrapper);

    /**
     * 分页查询
     *
     * @param page
     * @param <E>
     * @return
     */
    <E extends IPage<T>> E page(E page);

    /**
     * 条件分页查询
     *
     * @param page
     * @param queryWrapper
     * @param <E>
     * @return
     */
    <E extends IPage<T>> E page(E page, Wrapper<T> queryWrapper);
}
