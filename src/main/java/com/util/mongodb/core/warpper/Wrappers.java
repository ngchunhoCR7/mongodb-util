package com.util.mongodb.core.warpper;

import com.util.mongodb.core.warpper.impl.LambdaQueryWrapper;
import com.util.mongodb.core.warpper.impl.QueryWrapper;

/**
 * Wrappers
 *
 * @author ngchunho
 * @version 1.0.0
 * @description
 * @date 2022/1/27 14:13
 */
public class Wrappers {

    /**
     * 空的 EmptyWrapper
     */
    private static final QueryWrapper<?> EMPTY_WRAPPER = new EmptyWrapper<>();

    public static <T> QueryWrapper<T> emptyWrapper() {
        return (QueryWrapper<T>) EMPTY_WRAPPER;
    }

    /**
     * 获取 QueryWrapper<T>
     *
     * @param <T> 实体类泛型
     * @return QueryWrapper<T>
     */
    public static <T> QueryWrapper<T> query() {
        return new QueryWrapper<>();
    }

    /**
     * 获取 QueryWrapper<T>
     *
     * @param entity 实体类
     * @param <T>    实体类泛型
     * @return QueryWrapper<T>
     */
    public static <T> QueryWrapper<T> query(T entity) {
        return new QueryWrapper<>(entity);
    }

    /**
     * 获取 LambdaQueryWrapper<T>
     *
     * @param <T> 实体类泛型
     * @return LambdaQueryWrapper<T>
     */
    public static <T> LambdaQueryWrapper<T> lambdaQuery() {
        return new LambdaQueryWrapper<>();
    }

    /**
     * 获取 LambdaQueryWrapper<T>
     *
     * @param entity 实体类
     * @param <T>    实体类泛型
     * @return LambdaQueryWrapper<T>
     */
    public static <T> LambdaQueryWrapper<T> lambdaQuery(T entity) {
        return new LambdaQueryWrapper<>(entity);
    }

    /**
     * 获取 LambdaQueryWrapper<T>
     *
     * @param entityClass 实体类class
     * @param <T>         实体类泛型
     * @return LambdaQueryWrapper<T>
     * @since 3.3.1
     */
    public static <T> LambdaQueryWrapper<T> lambdaQuery(Class<T> entityClass) {
        return new LambdaQueryWrapper<>(entityClass);
    }

    /**
     * 一个空的QueryWrapper子类该类不包含任何条件
     */
    private static class EmptyWrapper<T> extends QueryWrapper<T> {

    }
}
