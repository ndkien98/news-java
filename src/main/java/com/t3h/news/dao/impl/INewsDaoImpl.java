package com.t3h.news.dao.impl;

import com.t3h.news.dao.INewsDao;
import com.t3h.news.mapper.NewsMapper;
import com.t3h.news.model.NewsModel;
import com.t3h.news.model.request.InsertNewsRequest;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class INewsDaoImpl extends GenericDaoImpl<NewsModel> implements INewsDao {

    public List<NewsModel> getList(){
        String sql = "select * from news";
        return getList(sql,new NewsMapper());
    }

    public int insert(InsertNewsRequest insertNewsRequest){
        String sql = "insert into news(title," +
                "content," +
                "avatar," +
                "author," +
                "categoryId," +
                "originalResource,numberAccess,censor,createDate,updateDate) " +
                "values (?,?,?,?,?,?,?,?,?,?)";
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        insert(sql,insertNewsRequest.getTitle(),
                insertNewsRequest.getContent(),
                insertNewsRequest.getAvatar(),
                insertNewsRequest.getAuthor(),
                insertNewsRequest.getCategoryId(),
                insertNewsRequest.getOriginalResource(),
                insertNewsRequest.getNumberAccess(),
                insertNewsRequest.getCensor(),
                currentTime,
                currentTime
                );
        return 0;
    }


}
