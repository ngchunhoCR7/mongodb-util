package com.util.mongodb.core.interfaces;

import java.io.Serializable;

/**
 * Join
 *
 * @author ngchunho
 * @version 1.0.0
 * @description
 * @date 2021/7/7 14:48
 */
public interface Join<Children> extends Serializable {

    /**
     * 拼接 OR
     *
     * @return children
     */
    Children or();
}
