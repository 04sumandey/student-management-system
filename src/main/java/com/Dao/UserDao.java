package com.Dao;


import com.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
//    private static final String INSERT_USER_SQL = ;
    private String jdbcURL = "jdbc:mysql://localhost:3306/student_management?useSSl=false";
    private String jdbcUsername = "root";
    private String jdbcpassword = "root";
    private static final String Insert_User_SQL = "INSERT INTO users " + "(id,name,email,phNo)" + " VALUES (?,?,?,?);";
    private static final String SELECT_USER_BY_ID = "select id,name,email,phNo from users where id=?";
    private static final String SELECT_ALL_USER = "select * from users ";
    private static final String DELETE_USER_SQL = "delete from users where id=?;";
    private static final String UPDATE_USER_SQL = "update users set name =?,email = ?,phNo =? where id=?;";

    protected Connection getConnection() {
        java.sql.Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcpassword);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
    public void insertUser(User user) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Insert_User_SQL)) {
            preparedStatement.setInt(1,user.getId());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPhNo());
            System.out.println("Prepared Statement : "+preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public User getUser(int id) {
        User user = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String phNo = rs.getString("phNo");
                user = new User(id, name, email, phNo);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public List<User> selectAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USER)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String phNo = rs.getString("phNo");
                users.add(new User(id, name, email, phNo));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public boolean deleteUser(int id) {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_USER_SQL)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rowDeleted;
    }

    public boolean updateUser(User user) {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USER_SQL)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPhNo());
            statement.setInt(4, user.getId());
            rowUpdated = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rowUpdated;
    }


}

