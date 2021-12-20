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
        String password = "1234";
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
            this.rollback(connection);
        }finally {
           this.closeConnection(connection);
            assert preparedStatement != null;
            this.closePreparedStatement(preparedStatement);
        }
        return resultSet;
    }

    @Override
    public void insert(String sql, Object... parameters) {
        Connection connection = getConnection();
        PreparedStatement preparedStatement = null;
        try {
            connection.setAutoCommit(false);
            preparedStatement  = connection.prepareStatement(sql);
            this.setParameter(preparedStatement,parameters);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            this.rollback(connection);
        }finally {
            this.closeConnection(connection);
            assert preparedStatement != null;
            this.closePreparedStatement(preparedStatement);
        }
    }

    public void closeConnection(Connection connection){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void rollback(Connection connection){
        try {
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closePreparedStatement(PreparedStatement preparedStatement){
        try {
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setParameter(PreparedStatement preparedStatement,Object... parameters) throws SQLException {
        for (int i = 0; i < parameters.length; i++) {
            int index = i + 1;
            if (parameters[i] instanceof String){
                preparedStatement.setString(index, (String) parameters[i]);
            }else if (parameters[i] instanceof Integer){
                preparedStatement.setInt(index, (Integer) parameters[i]);
            }else if (parameters[i] instanceof Float){
                preparedStatement.setFloat(index, (Float) parameters[i]);
            }else if (parameters[i] instanceof Long){
                preparedStatement.setLong(index, (Long) parameters[i]);
            }else if (parameters[i] instanceof Boolean){
                preparedStatement.setBoolean(index, (Boolean) parameters[i]);
            }else if (parameters[i] instanceof Timestamp){
                preparedStatement.setTimestamp(index, (Timestamp) parameters[i]);
            }
        }

    }


}
