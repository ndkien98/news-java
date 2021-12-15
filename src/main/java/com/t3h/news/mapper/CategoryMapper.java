package com.t3h.news.mapper;

import com.t3h.news.model.CategoryModel;
import com.t3h.news.utils.MapUtils;

import java.sql.ResultSet;

public class CategoryMapper implements IRowMapper<CategoryModel> {

    @Override
    public CategoryModel map(ResultSet resultSet) {
        return MapUtils.mapRow(new CategoryModel(),resultSet);
    }
}
