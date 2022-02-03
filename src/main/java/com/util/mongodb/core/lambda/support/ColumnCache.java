package com.util.mongodb.core.lambda.support;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * ColumnCache
 *
 * @author ngchunho
 * @version 1.0.0
 * @description
 * @date 2022/1/28 16:56
 */
@Data
@AllArgsConstructor
public class ColumnCache implements Serializable {

    /**
     * 使用 column
     */
    private String column;
    /**
     * 查询 column
     */
    private String columnSelect;
}
