package com.util.mongodb.controller;

import com.google.common.collect.Lists;
import com.util.mongodb.common.api.response.R;
import com.util.mongodb.core.warpper.impl.LambdaQueryWrapper;
import com.util.mongodb.core.warpper.impl.QueryWrapper;
import com.util.mongodb.model.MongodbTestModel;
import com.util.mongodb.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * TestController
 *
 * @author ngchunho
 * @version 1.0.0
 * @description
 * @date 2021/7/6 17:14
 */
@RestController
public class TestController {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private ITestService recordService;

    @GetMapping("/test")
    public R<?> test() {
        Query query = new QueryWrapper<MongodbTestModel>()
                .eq("column1", "aaa")
                .like("column2", "bbb")
                .orderByDesc("column3")
                .getQueryCondition();
        List<MongodbTestModel> list = mongoTemplate.find(query, MongodbTestModel.class);
        return R.ok(list);
    }

    @GetMapping("/test1")
    public R<?> test1() {
        List<String> column3List = Lists.newArrayList();
        column3List.add("aaa");
        column3List.add("bbb");
        column3List.add("ccc");

        List<MongodbTestModel> list = recordService.list(
                new LambdaQueryWrapper<MongodbTestModel>()
                        .in(MongodbTestModel::getColumn3, column3List)
                        .orderByAsc(MongodbTestModel::getColumn1, MongodbTestModel::getColumn2)
        );
        return R.ok(list);
    }
}
