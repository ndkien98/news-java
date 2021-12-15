package com.t3h.news.dao;

import com.t3h.news.model.CategoryModel;

import java.util.List;

public interface ICategoryDao extends IGenericDAO<CategoryModel>{

    List<CategoryModel> getList();
}
