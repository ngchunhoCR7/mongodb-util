package com.util.mongodb.core.warpper;

import com.util.mongodb.core.lambda.LambdaUtils;
import com.util.mongodb.core.lambda.support.SelectFunction;

/**
 * AbstractLambdaWrapper
 *
 * @author ngchunho
 * @version 1.0.0
 * @description
 * @date 2021/7/6 18:11
 */
public abstract class AbstractLambdaWrapper<T, Children extends AbstractLambdaWrapper<T, Children>> extends AbstractWrapper<T, SelectFunction<T, ?>, Children> {

    @Override
    protected String columnToString(SelectFunction<T, ?> column) {
        return getColumn(column);
    }

    /**
     * 获取字段名称
     *
     * @param column 输入的 lambda 对象
     * @return
     */
    private String getColumn(SelectFunction<T, ?> column) {
        return LambdaUtils.serializeLambda(column, true);
    }
}
