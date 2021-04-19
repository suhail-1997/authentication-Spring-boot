package com.example.authentication.Dao;

import java.util.List;

import com.example.authentication.Model.User;
import com.example.authentication.mapper.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public boolean checkEmail(String email) {
        List<User> userList = jdbcTemplate.query("select * from users where email = ?",new UserMapper(),email);
        if(userList.size() > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public List<User> checkUser(String email, String password) {
        List<User> user = jdbcTemplate.query("select * from users where email = ? and password = ?",new UserMapper(), new Object[] { email, password });
        return user;
    }

    @Override
    public int registerUser(String name, long phone, String email, String password) {
        return jdbcTemplate.update("insert into user (name,phone,email,password) values (?,?,?,?)", name,phone,email,password);

    }
    
}
