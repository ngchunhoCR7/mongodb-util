package com.util.mongodb;

import cn.hutool.core.lang.UUID;
import com.google.common.collect.Lists;
import com.util.mongodb.common.api.response.R;
import com.util.mongodb.common.page.Page;
import com.util.mongodb.controller.TestController;
import com.util.mongodb.core.warpper.impl.LambdaQueryWrapper;
import com.util.mongodb.model.MongodbTestModel;
import com.util.mongodb.service.impl.TestServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

@SpringBootTest
class MongodbToolApplicationTests {

    @Autowired
    private TestController testController;

    @Autowired
    private TestServiceImpl testService;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    void contextLoads() {
        testController.test1();
    }

    @Test
    void insert() {
        MongodbTestModel mongodbTestModel = MongodbTestModel.builder()
                .id(UUID.randomUUID().toString(true))
                .column1("aaa")
                .column2("bbb")
                .column3("ccc")
                .build();
        mongoTemplate.save(mongodbTestModel);
    }

    @Test
    void update() {
        String id = "5bf961c69d4841c58154a6abb157b99f";
        MongodbTestModel byId = mongoTemplate.findById(id, MongodbTestModel.class);
        assert byId != null;
        byId.setColumn1("aaaa");
        mongoTemplate.save(byId);
    }

    @Test
    void page() {
        Page<MongodbTestModel> page = testService.page(new Page<>(1, 10));
        System.out.println(page);
    }

    @Test
    void list() {
        List<String> column3List = Lists.newArrayList();
        column3List.add("aaa");
        column3List.add("bbb");
        column3List.add("ccc");

        List<MongodbTestModel> list = testService.list(
                new LambdaQueryWrapper<MongodbTestModel>()
                        .in(MongodbTestModel::getColumn3, column3List)
                        .orderByAsc(MongodbTestModel::getColumn1, MongodbTestModel::getColumn2)
        );
        System.out.println(list);
    }

    @Test
    void and() {
        List<MongodbTestModel> list = testService.list(
                new LambdaQueryWrapper<MongodbTestModel>()
                        .and(i -> i.eq(MongodbTestModel::getColumn1, "aaa"))
        );
    }

}
