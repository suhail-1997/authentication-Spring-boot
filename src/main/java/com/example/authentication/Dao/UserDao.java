package com.example.authentication.Dao;

import java.util.List;

import com.example.authentication.Model.User;

public interface UserDao {
    public boolean checkEmail(String email);
    public List<User> checkUser(String email,String password);
    public int registerUser(String name,long phone,String email,String password);
}