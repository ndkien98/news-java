package com.t3h.news.dao.impl;

import com.t3h.news.dao.INewsDao;
import com.t3h.news.model.NewsModel;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class INewsDaoImpl implements INewsDao {

    public Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/24h";
        String user = "root";
        String password = "root";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            return null;
        }
    }


    public List<NewsModel> getList(){

        Connection connection = this.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "select * from news";
        ResultSet resultSet = null;

        List<NewsModel> newsModels = new ArrayList<>();
        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                NewsModel newsModel = new NewsModel();
                newsModel.setId(Integer.parseInt(resultSet.getString(1)));
                newsModel.setTitle(resultSet.getString(2));
                newsModel.setContent(resultSet.getString(3));
                newsModel.setCreatedDate(resultSet.getTimestamp(4));
                newsModel.setUpdateDate(resultSet.getTimestamp(5));
                newsModel.setAvatar(resultSet.getString(6));
                newsModels.add(newsModel);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newsModels;
    }

}
