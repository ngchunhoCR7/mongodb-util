package com.util.mongodb.common.page;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;
import java.util.List;

/**
 * Page
 *
 * @author ngchunho
 * @version 1.0.0
 * @description
 * @date 2022/1/27 14:37
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class Page <T> implements IPage<T> {

    /**
     * 查询数据列表
     */
    private List<T> records = Collections.emptyList();
    /**
     * 总数
     */
    private long total = 0;
    /**
     * 每页显示条数，默认 10
     */
    private long size = 10;
    /**
     * 当前页
     */
    private long current = 1;

    public Page(long current, long size) {
        if (current > 1) {
            this.current = current;
        }
        this.size = size;
    }

    long getPages() {
        if (getSize() == 0) {
            return 0L;
        }
        long pages = getTotal() / getSize();
        if (getTotal() % getSize() != 0) {
            pages++;
        }
        return pages;
    }

    @Override
    public PageRequest toPageRequest() {
        return PageRequest.of((int) (getCurrent() - 1), (int) getSize());
    }
}
