package com.t3h.news.dao.impl;

import com.t3h.news.dao.INewsDao;
import com.t3h.news.model.NewsModel;
import com.t3h.news.model.request.InsertNewsRequest;
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
            Class.forName("com.mysql.cj.jdbc.Driver");
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
                newsModel.setId(Integer.parseInt(resultSet.getString("id")));
                newsModel.setTitle(resultSet.getString("title"));
                newsModel.setContent(resultSet.getString("content"));
                newsModel.setCreatedDate(resultSet.getTimestamp("createDate"));
                newsModel.setUpdateDate(resultSet.getTimestamp("updateDate"));
                newsModel.setAvatar(resultSet.getString("avatar"));
                newsModels.add(newsModel);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        return newsModels;
    }

    public int insert(InsertNewsRequest insertNewsRequest){

        Connection connection = this.getConnection();
        String sql = "insert into news(title,content,avatar,author,categoryId,originalResource,numberAccess,censor) " +
                "values (?,?,?,?,?,?,?,?)";
        PreparedStatement preparedStatement = null;

        Integer newId =0;
        String sqlMaxId = "select max(id) as id from news";

        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,insertNewsRequest.getTitle());
            preparedStatement.setString(2,insertNewsRequest.getContent());
            preparedStatement.setString(3,insertNewsRequest.getAvatar());
            preparedStatement.setString(4,insertNewsRequest.getAuthor());
            preparedStatement.setInt(5,insertNewsRequest.getCategoryId());
            preparedStatement.setString(6,insertNewsRequest.getOriginalResource());
            preparedStatement.setInt(7,insertNewsRequest.getNumberAccess());
            preparedStatement.setInt(8,insertNewsRequest.getCensor());
            preparedStatement.executeUpdate();
            connection.commit();

            ResultSet resultSet = connection.prepareStatement(sqlMaxId).executeQuery();
            while (resultSet.next()){
                newId = Integer.parseInt(resultSet.getString("id"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }finally {
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        return newId;
    }


}
