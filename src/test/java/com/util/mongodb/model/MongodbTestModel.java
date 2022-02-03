package com.util.mongodb.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

/**
 * MongodbTestModel
 *
 * @author ngchunho
 * @version 1.0.0
 * @description
 * @date 2021/3/31 11:28
 */
@Data
@Builder
@Accessors(chain = true)
@Document(collection = "mongodb_test")
@ApiModel(value = "mongodb测试模型对象", description = "mongodb测试模型对象")
public class MongodbTestModel {

    /**
     * 主键id
     */
    @MongoId(targetType = FieldType.STRING)
    @ApiModelProperty(value = "主键id")
    private String id;

    /**
     * column1
     */
    @ApiModelProperty(value = "column1")
    private String column1;

    /**
     * column2
     */
    @Field("column2")
    @ApiModelProperty(value = "column2")
    private String column2;

    /**
     * column3
     */
    @Field("column3")
    @ApiModelProperty(value = "column3")
    private String column3;
}
