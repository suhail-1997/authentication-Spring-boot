package com.example.authentication.Model;

public class User{
    private int userid;
    private String name,phone,email;
    public User(){

    }
    public User(int userid,String name,String phone,String email)
    {
        this.userid = userid;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }
    public int getUserId(){
        return this.userid;
    }
    public String getName(){
        return this.name;
    }
    public String getEmail() {
        return this.email;
    }
    public String getPhone() {
        return this.phone;
    }


}