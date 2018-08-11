package com.sboyd.bookme;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {

    private String email;
    private String username;
    private String phone;

    public User()
    {

    }

    public User(String email, String username, String phone){
        this.email = email;
        this.username = username;
        this.phone = phone;
    }



    public String getEmail()
    {
        return this.email;
    }

    public String getUsername()
    {
        return this.username;
    }

    public String getPhone()
    {
        return this.phone;
    }



}
