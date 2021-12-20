package com.t3h.news.mapper;

import com.t3h.news.model.NewsModel;
import com.t3h.news.utils.MapUtils;

import java.sql.ResultSet;

public class NewsMapper implements IRowMapper<NewsModel> {

    @Override
    public NewsModel map(ResultSet resultSet) {
        return MapUtils.mapRow(new NewsModel(),resultSet);
    }
}
