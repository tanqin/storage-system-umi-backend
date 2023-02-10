package com.tanqin.entity;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class QueryPageParams {
    private static final Integer DEFAULT_PAGE_NUM = 1;
    private static final Integer DEFAULT_PAGE_SIZE = 10;

    private Integer pageNum = DEFAULT_PAGE_NUM;
    private Integer pageSize = DEFAULT_PAGE_SIZE;

    private Map params = new HashMap<>();
}
