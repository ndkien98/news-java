package com.t3h.news.dao;

import com.t3h.news.model.NewsModel;
import com.t3h.news.model.request.NewsRequest;

import java.util.List;

public interface INewsDao {

    List<NewsModel> getList();

    int insert(NewsRequest newsRequest);

    List<NewsModel> findByProperties(int numberAccess,int  censor);
}
