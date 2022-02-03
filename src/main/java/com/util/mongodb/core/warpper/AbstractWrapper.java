package com.util.mongodb.core.warpper;

import cn.hutool.core.util.ArrayUtil;
import com.google.common.collect.Lists;
import com.util.mongodb.core.interfaces.Compare;
import com.util.mongodb.core.interfaces.Function;
import com.util.mongodb.core.interfaces.Join;
import com.util.mongodb.core.interfaces.Nested;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * AbstractWrapper
 *
 * @author ngchunho
 * @version 1.0.0
 * @description
 * @date 2021/7/6 15:33
 */
public abstract class AbstractWrapper<T, R, Children extends AbstractWrapper<T, R, Children>> extends Wrapper<T>
        implements Compare<Children, R>, Function<Children, R>, Join<Children>, Nested<Children, Children> {

    /**
     * 占位符
     */
    protected final Children typedThis = (Children) this;

    protected Query query = new Query();
    protected Criteria criteria = new Criteria();
    protected List<Sort.Order> orders = Lists.newArrayList();
    protected Aggregation aggregation = Aggregation.newAggregation(Aggregation.match(criteria));

    /**
     * or条件是否存在
     */
    protected boolean orCriteria = false;
    /**
     * and条件是否存在
     */
    protected boolean andCriteria = false;
    /**
     * or条件查询
     */
    protected List<Criteria> orCriteriaList = Lists.newArrayList();
    /**
     * and条件查询
     */
    protected List<Criteria> andCriteriaList = Lists.newArrayList();

    /**
     * 映射实体类
     */
    private T entity;
    /**
     * 实体类型(主要用于确定泛型以及取TableInfo缓存)
     */
    private Class<T> entityClass;

    @Override
    public T getEntity() {
        return entity;
    }

    public Children setEntity(T entity) {
        this.entity = entity;
        return typedThis;
    }

    protected Class<T> getEntityClass() {
        if (entityClass == null && entity != null) {
            entityClass = (Class<T>) entity.getClass();
        }
        return entityClass;
    }

    public Children setEntityClass(Class<T> entityClass) {
        if (entityClass != null) {
            this.entityClass = entityClass;
        }
        return typedThis;
    }

    @Override
    public Children eq(R column, Object val) {
        criteria.andOperator(Criteria.where(columnToString(column)).is(val));
        return typedThis;
    }

    @Override
    public Children ne(R column, Object val) {
        criteria.andOperator(Criteria.where(columnToString(column)).ne(val));
        return typedThis;
    }

    @Override
    public Children gt(R column, Object val) {
        criteria.andOperator(Criteria.where(columnToString(column)).gt(val));
        return typedThis;
    }

    @Override
    public Children ge(R column, Object val) {
        criteria.andOperator(Criteria.where(columnToString(column)).gte(val));
        return typedThis;
    }

    @Override
    public Children lt(R column, Object val) {
        criteria.andOperator(Criteria.where(columnToString(column)).lt(val));
        return typedThis;
    }

    @Override
    public Children le(R column, Object val) {
        criteria.andOperator(Criteria.where(columnToString(column)).lte(val));
        return typedThis;
    }

    @Override
    public Children between(R column, Object val1, Object val2) {
        criteria.andOperator(Criteria.where(columnToString(column)).gte(val1).lte(val2));
        return typedThis;
    }

    @Override
    public Children notBetween(R column, Object val1, Object val2) {
        criteria.andOperator(Criteria.where(columnToString(column)).lt(val1).gt(val2));
        return typedThis;
    }

    @Override
    public Children like(R column, Object val) {
        Pattern pattern = Pattern.compile("^.*" + val + ".*$", Pattern.CASE_INSENSITIVE);
        criteria.andOperator(Criteria.where(columnToString(column)).regex(pattern));
        return typedThis;
    }

    @Override
    public Children notLike(R column, Object val) {
        Pattern pattern = Pattern.compile("^((?!" + val + ").)*$", Pattern.CASE_INSENSITIVE);
        criteria.andOperator(Criteria.where(columnToString(column)).regex(pattern));
        return typedThis;
    }

    @Override
    public Children likeLeft(R column, Object val) {
        Pattern pattern = Pattern.compile("^" + val + ".*$", Pattern.CASE_INSENSITIVE);
        criteria.andOperator(Criteria.where(columnToString(column)).regex(pattern));
        return typedThis;
    }

    @Override
    public Children likeRight(R column, Object val) {
        Pattern pattern = Pattern.compile("^.*" + val + "$", Pattern.CASE_INSENSITIVE);
        criteria.andOperator(Criteria.where(columnToString(column)).regex(pattern));
        return typedThis;
    }

    @Override
    public Children isNull(R column) {
        criteria.andOperator(Criteria.where(columnToString(column)).is(null));
        return typedThis;
    }

    @Override
    public Children isNotNull(R column) {
        criteria.andOperator(Criteria.where(columnToString(column)).ne(null));
        return typedThis;
    }

    @Override
    public Children in(R column, Collection<?> coll) {
        criteria.andOperator(Criteria.where(columnToString(column)).in(coll));
        return typedThis;
    }

    @Override
    public Children notIn(R column, Collection<?> coll) {
        criteria.andOperator(Criteria.where(columnToString(column)).nin(coll));
        return typedThis;
    }

    @Override
    public Children orderByAsc(R column) {
        orders.add(Sort.Order.asc(columnToString(column)));
        return typedThis;
    }

    @SafeVarargs
    @Override
    public final Children orderByAsc(R... columns) {
        if (ArrayUtil.isNotEmpty(columns)) {
            columnsToString(columns).forEach(column -> orders.add(Sort.Order.asc(column)));
        }
        return typedThis;
    }

    @Override
    public Children orderByDesc(R column) {
        orders.add(Sort.Order.desc(columnToString(column)));
        return typedThis;
    }

    @SafeVarargs
    @Override
    public final Children orderByDesc(R... columns) {
        if (ArrayUtil.isNotEmpty(columns)) {
            columnsToString(columns).forEach(column -> orders.add(Sort.Order.desc(column)));
        }
        return typedThis;
    }

    @Override
    public Children groupBy(R... columns) {
        // TODO 待开发
        return null;
    }

    @Override
    public Children having(Map<String, String> columnMap) {
        // TODO 待开发
        return null;
    }

    @Override
    public Children or() {
        // 判断是否有and条件
        if (andCriteria) {
            // 把and条件查询进行合并
            criteria.andOperator(andCriteriaList);
            // 重置and查询条件
            andCriteria = false;
            // 重置and查询条件列表
            andCriteriaList = Lists.newArrayList();
        }
        // 把现有查询条件添加到条件列表
        orCriteriaList.add(criteria);
        // 设置条件状态为存在
        orCriteria = true;
        // 重置查询条件
        criteria = new Criteria();
        return typedThis;
    }

    @Override
    public Children and(/*Criteria criteria, */Consumer<Children> consumer) {
        Children instance = instance();
//        consumer.a
        return null;
    }

    @Override
    public Children or(/*Criteria criteria, */Consumer<Children> consumer) {
        return null;
    }

//    @Override
//    public Children groupBy(R... columns) {
//        if (ArrayUtil.isNotEmpty(columns)) {
//            List<String> list = columnsToString(columns);
//            String[] fields = list.toArray(new String[0]);
//            aggregation = Aggregation.newAggregation(
//                    Aggregation.match(criteria),
//                    Aggregation.group(fields),
//                    Aggregation.sort(getSort())
//            );
//        }
//        return typedThis;
//    }

    protected void initNeed() {
        getEntityClass();
    }

    protected String columnToString(R column) {
        return (String) column;
    }

    @SafeVarargs
    protected final List<String> columnsToString(R... columns) {
        return Arrays.stream(columns).map(this::columnToString).collect(Collectors.toList());
    }

    protected abstract Children instance();

    protected Sort getSort() {
        return Sort.by(orders);
    }

    protected void setQuery(Query query) {
        this.query = query;
    }

    protected void setCriteria(Criteria criteria) {
        this.criteria = criteria;
    }

    protected Query getQuery() {
        return query;
    }

    protected Criteria getCriteria() {
        return criteria;
    }

    public List<Sort.Order> getOrders() {
        return orders;
    }

    public Aggregation getAggregation() {
        return aggregation;
    }

    /**
     * 获取query查询条件
     */
    @Override
    public Query getQueryCondition() {
        query.addCriteria(criteria);
        query.with(getSort());
        return query;
    }

    /**
     * 获取query查询条件
     */
    @Override
    public Query getQueryCondition(PageRequest page) {
        query.addCriteria(criteria);
        query.with(getSort());
        query.with(page);
        return query;
    }

//    @Override
//    public Class<T> getEntityClass() {
//        // TODO index代表实现类继承父类的层级，此处的Service只有一层所以传入0
//        return (Class<T>) ReflectionUtil.getSuperClassGenericType(this.getClass(), 0);
//    }

    @Override
    public Query page(PageRequest page) {
        return query.with(page);
    }
}
