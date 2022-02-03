package com.util.mongodb.core.lambda.support;

import java.io.Serializable;
import java.util.function.Function;

/**
 * SelectFunction
 *
 * @author ngchunho
 * @version 1.0.0
 * @description
 * @date 2021/7/6 15:11
 */
@FunctionalInterface
public interface SelectFunction<T, R> extends Function<T, R>, Serializable {
}
