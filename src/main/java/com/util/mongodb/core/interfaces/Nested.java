package com.util.mongodb.core.interfaces;

import java.io.Serializable;
import java.util.function.Consumer;

/**
 * Nested
 *
 * @author ngchunho
 * @version 1.0.0
 * @description
 * @date 2021/7/7 14:45
 */
public interface Nested<Param, Children> extends Serializable {

    /**
     * AND 嵌套
     * <p>
     * 例: and(i -> i.eq("name", "李白").ne("status", "活着"))
     * </p>
     *
     * @param consumer 消费函数
     * @return children
     */
    Children and(Consumer<Param> consumer);

    /**
     * OR 嵌套
     * <p>
     * 例: or(i -&gt; i.eq("name", "李白").ne("status", "活着"))
     * </p>
     *
     * @param consumer 消费函数
     * @return children
     */
    Children or(Consumer<Param> consumer);
}
