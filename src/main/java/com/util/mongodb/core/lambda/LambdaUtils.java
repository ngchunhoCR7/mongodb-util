package com.util.mongodb.core.lambda;

import com.util.mongodb.core.lambda.support.ColumnCache;
import com.util.mongodb.core.lambda.support.SelectFunction;

import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * LambdaUtils
 *
 * @author ngchunho
 * @version 1.0.0
 * @description
 * @date 2021/7/7 11:20
 */
public class LambdaUtils {

    /**
     * 字段映射
     */
    private static final Map<String, Map<String, ColumnCache>> COLUMN_CACHE_MAP = new ConcurrentHashMap<>();

    /**
     * SerializedLambda 反序列化缓存
     */
    private static final Map<Class<?>, SerializedLambda> CLASS_LAMBDA_CACHE = new ConcurrentHashMap<>();

    /**
     * 序列化 lambda 对象并返回解析的字段名称
     *
     * @param column    需要解析的 lambda 对象
     * @param serialize 是否序列化
     * @return 字段名称
     */
    public static String serializeLambda(SelectFunction<?, ?> column, Boolean serialize) {
        SerializedLambda lambda = CLASS_LAMBDA_CACHE.get(column.getClass());
        if (lambda == null) {
            if (serialize) {
                lambda = SerializedLambda.resolve(column);
            } else {
                lambda = SerializedLambda.writeReplace(column);
            }
            // 放入缓存
            CLASS_LAMBDA_CACHE.put(lambda.getClass(), lambda);
        }
        return methodToProperty(lambda.getImplMethodName());
    }

    /**
     * 方法转为字段名称
     *
     * @param name 方法名称
     * @return 字段名称
     */
    private static String methodToProperty(String name) {
        if (name.startsWith("is")) {
            name = name.substring(2);
        } else if (name.startsWith("get") || name.startsWith("set")) {
            name = name.substring(3);
        } else {
            throw new RuntimeException("Error parsing property name '" + name + "'.  Didn't start with 'is', 'get' or 'set'.");
        }

        if (name.length() == 1 || (name.length() > 1 && !Character.isUpperCase(name.charAt(1)))) {
            name = name.substring(0, 1).toLowerCase(Locale.ENGLISH) + name.substring(1);
        }

        return name;
    }
}
