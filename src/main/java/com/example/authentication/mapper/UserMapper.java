package com.example.authentication.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.authentication.Model.User;

import org.springframework.jdbc.core.RowMapper;

public class UserMapper implements RowMapper<User>{

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User(rs.getInt("userid"),rs.getString("name"),rs.getString("phone"),rs.getString("email"));
        return user;
    }
    
}
