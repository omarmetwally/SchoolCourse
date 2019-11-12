package com.example.omarmetwally.schoolcourse;

public class UserLoginPost {
    private String Email,Password;
    private String token;


    public String getToken() {
        return token;
    }

    public UserLoginPost(String email,String pass)
    {

        Email=email;
        Password=pass;
    }
}
