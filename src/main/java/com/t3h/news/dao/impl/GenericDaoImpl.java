package com.t3h.news.dao.impl;

import com.t3h.news.dao.IGenericDAO;
import com.t3h.news.mapper.IRowMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class GenericDaoImpl<T> implements IGenericDAO<T> {

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

    @Override
    public List<T> getList(String sql, IRowMapper<T> rowMapper) {

        Connection connection = getConnection();

        PreparedStatement preparedStatement = null;
        List<T> resultSet = new ArrayList<>();

        ResultSet resultSetDatabase = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSetDatabase = preparedStatement.executeQuery();

            while (resultSetDatabase.next()){
                T t = rowMapper.map(resultSetDatabase);
                resultSet.add(t);
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

        return resultSet;
    }

    @Override
    public void insert(T t, Object... parameters) {

    }


}
