package com.util.mongodb.service.impl;

import com.util.mongodb.common.page.IPage;
import com.util.mongodb.core.util.ReflectionUtil;
import com.util.mongodb.core.warpper.Wrapper;
import com.util.mongodb.core.warpper.Wrappers;
import com.util.mongodb.service.IService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ServiceImpl
 *
 * @author ngchunho
 * @version 1.0.0
 * @description
 * @date 2021/8/4 19:36
 */
@Slf4j
public class ServiceImpl<T> implements IService<T> {

    @Autowired
    private MongoTemplate mongoTemplate;

    protected Class<T> entityClass = currentModelClass();

    protected Class<T> currentModelClass() {
        // TODO index代表实现类继承父类的层级，此处的Service只有一层所以传入0
        return (Class<T>) ReflectionUtil.getSuperClassGenericType(getClass(), 0);
    }

    @Override
    public T save(T entity) {
        return mongoTemplate.insert(entity);
    }

    @Override
    public Collection<T> saveBatch(Collection<T> entityList) {
        return mongoTemplate.insertAll(entityList);
    }

    @Override
    public boolean remove(T entity) {
//        // TODO 假如使用findAndRemove，要创建根据mongoId新建的query
//        return mongoTemplate.findAndRemove().wasAcknowledged();
        return mongoTemplate.remove(entity).wasAcknowledged();
    }

    @Override
    public Collection<T> remove(Wrapper<T> wrapper) {
        return mongoTemplate.findAllAndRemove(wrapper.getQueryCondition(), entityClass);
    }

    @Override
    public T updateById(T entity) {
        // TODO 支持拓展更新策略
        return mongoTemplate.save(entity);
    }

    @Override
    public Collection<T> updateBatchById(Collection<T> entityList) {
        return entityList.stream().map(this::updateById).collect(Collectors.toList());
    }

    @Override
    public long count() {
        return mongoTemplate.count(Wrappers.emptyWrapper().getQueryCondition(), entityClass);
    }

    @Override
    public long count(Wrapper<T> wrapper) {
        return mongoTemplate.count(wrapper.getQueryCondition(), entityClass);
    }

    @Override
    public List<T> list() {
        return mongoTemplate.find(Wrappers.emptyWrapper().getQueryCondition(), entityClass);
    }

    @Override
    public List<T> list(Wrapper<T> wrapper) {
        return mongoTemplate.find(wrapper.getQueryCondition(), entityClass);
    }

    @Override
    public <E extends IPage<T>> E page(E page) {
        List<T> records = mongoTemplate.find(Wrappers.emptyWrapper().page(page.toPageRequest()), entityClass);
        return (E) page.setRecords(records).setTotal(count());
    }

    @Override
    public <E extends IPage<T>> E page(E page, Wrapper<T> queryWrapper) {
        List<T> records = mongoTemplate.find(queryWrapper.getQueryCondition(page.toPageRequest()), entityClass);
        return (E) page.setRecords(records).setTotal(count());
    }
}
