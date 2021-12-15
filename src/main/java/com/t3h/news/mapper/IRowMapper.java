package com.t3h.news.mapper;

import java.sql.ResultSet;

public interface IRowMapper<T>{
    T map(ResultSet resultSet);
}
