package com.example.yubatest.entity.common;

import lombok.Data;

@Data
public abstract class PageQuery {
    private int pageSize;
    private int pageIndex;


}
