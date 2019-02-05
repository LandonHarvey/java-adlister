package com.codeup.adlister.dao;

import com.codeup.adlister.models.User;

import java.sql.*;

public class MySQLUsersDao implements Users {
    private Connection connection = null;

    public MySQLUsersDao (Config config) {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            connection = DriverManager.getConnection(
                    config.getUrl(),
                    config.getUsername(),
                    config.getPassword()
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database!", e);
        }
    }

    @Override
    public User findByUsername(String username) {
        String query = "SELECT * FROM users WHERE username = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return createUserFromResult(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error: could not locate user '" + username + "'", e);
        } catch (RuntimeException e) {
            throw new RuntimeException("Error: could not locate user '" + username + "'", e);
        }
    }
    @Override
    public Long insert(User user) {
        String query = "INSERT INTO users(username, user_email, user_password) VALUES (?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, user.getUsername());
            stmt.setString(2,user.getEmail());
            stmt.setString(3,user.getPassword());
//            stmt.executeUpdate(createInsertQuery(ad), Statement.RETURN_GENERATED_KEYS);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating a new user.", e);
        }
    }

    public User createUserFromResult(ResultSet rs){
        User user = new User();
        try {
            user.setId(rs.getLong(1));
            user.setUsername(rs.getString(2));
            user.setEmail(rs.getString(3));
            user.setPassword(rs.getString(4));
        }catch (SQLException e){
            System.out.println(e);
        }
        return user;
    }
}
