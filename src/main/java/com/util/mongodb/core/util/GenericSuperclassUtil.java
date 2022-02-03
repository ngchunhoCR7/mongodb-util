//package com.util.mongodb.core.util;
//
//import java.lang.reflect.ParameterizedType;
//import java.lang.reflect.Type;
//
///**
// * GenericSuperclassUtil
// *
// * @author ngchunho
// * @version 1.0.0
// * @description
// * @date 2021/8/5 16:42
// */
//public class GenericSuperclassUtil {
//
//    /**
//     * TODO ClassCastException
//     * 获取泛型类Class对象，不是泛型类则返回null
//     */
//    public static Class<?> getActualTypeArgument(Class<?> clazz) {
//        Class<?> entityClass = null;
//        Type genericSuperclass = clazz.getGenericSuperclass();
//        if (genericSuperclass instanceof ParameterizedType) {
//            Type[] actualTypeArguments = ((ParameterizedType) genericSuperclass).getActualTypeArguments();
//            if (actualTypeArguments != null && actualTypeArguments.length > 0) {
//                entityClass = (Class<?>) actualTypeArguments[0];
//            }
//        }
//
//        return entityClass;
//    }
//}
