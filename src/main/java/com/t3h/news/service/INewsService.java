package com.t3h.news.service;

import com.t3h.news.model.NewsModel;
import com.t3h.news.model.request.InsertNewsRequest;

import java.util.List;

public interface INewsService {
    List<NewsModel> getList();

    NewsModel insert(InsertNewsRequest insertNewsRequest);
}
