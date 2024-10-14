package com.example.yubatest.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.yubatest.entity.common.PageQuery;
import lombok.Data;

import java.io.Serializable;

@Data
public class PageHelper implements Serializable {

    public static IPage queryToPage(PageQuery pageQuery) {
        Page page = new Page((long)pageQuery.getPageIndex(), (long)pageQuery.getPageSize());
        return page;
    }


}
