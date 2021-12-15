package com.t3h.news.dao;

import com.t3h.news.mapper.IRowMapper;

import java.util.List;

public interface IGenericDAO <T extends Object>{

    List<T> getList(String sql, IRowMapper<T> rowMapper);

    void insert(T t,Object... parameters);

}
