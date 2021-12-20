package com.t3h.news.service.impl;

import com.t3h.news.dao.INewsDao;
import com.t3h.news.model.NewsModel;
import com.t3h.news.model.request.NewsRequest;
import com.t3h.news.service.INewsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class INewsServiceImpl implements INewsService {

    private INewsDao iNewsDao;

    public INewsServiceImpl(INewsDao iNewsDao) {
        this.iNewsDao = iNewsDao;
    }

    public List<NewsModel> getList(){
        return iNewsDao.getList();
    }

    @Override
    public NewsModel insert(NewsRequest newsRequest) {
        iNewsDao.insert(newsRequest);
        return null;
    }

    @Override
    public List<NewsModel> findByProperties(int numberAccess, int censor) {
        return iNewsDao.findByProperties(numberAccess,censor);
    }
}
