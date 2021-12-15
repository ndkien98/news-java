package com.t3h.news.service.impl;

import com.t3h.news.dao.ICategoryDao;
import com.t3h.news.model.CategoryModel;
import com.t3h.news.service.ICategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements ICategoryService {

    private ICategoryDao categoryDao;

    public CategoryServiceImpl(ICategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public List<CategoryModel> getList() {
        return categoryDao.getList();
    }
}
