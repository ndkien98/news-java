package com.t3h.news.dao;

import com.t3h.news.model.NewsModel;
import com.t3h.news.model.request.InsertNewsRequest;

import java.util.List;

public interface INewsDao {

    List<NewsModel> getList();

    int insert(InsertNewsRequest insertNewsRequest);

}
