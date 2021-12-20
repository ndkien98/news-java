package com.t3h.news.dao.impl;

import com.t3h.news.dao.INewsDao;
import com.t3h.news.mapper.NewsMapper;
import com.t3h.news.model.NewsModel;
import com.t3h.news.model.request.NewsRequest;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.List;

@Component
public class INewsDaoImpl extends GenericDaoImpl<NewsModel> implements INewsDao {

    public List<NewsModel> getList(){
        String sql = "select * from news";
        return getList(sql,new NewsMapper());
    }

    public int insert(NewsRequest newsRequest){
        String sql = "insert into news(title," +
                "content," +
                "avatar," +
                "author," +
                "categoryId," +
                "originalResource,numberAccess,censor,createDate,updateDate) " +
                "values (?,?,?,?,?,?,?,?,?,?)";
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        insert(sql, newsRequest.getTitle(),
                newsRequest.getContent(),
                newsRequest.getAvatar(),
                newsRequest.getAuthor(),
                newsRequest.getCategoryId(),
                newsRequest.getOriginalResource(),
                newsRequest.getNumberAccess(),
                newsRequest.getCensor(),
                currentTime,
                currentTime
                );
        return 0;
    }

    public List<NewsModel> findByProperties(int numberAccess,int  censor){
        String sql = "select * from news where numberAccess = ? and censor = ? ";
        return findByProperties(sql,new NewsMapper(),numberAccess,censor);
    }



}
