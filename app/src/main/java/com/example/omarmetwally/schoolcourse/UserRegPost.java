package com.example.omarmetwally.schoolcourse;

import retrofit2.http.Field;

public class UserRegPost {


    private  String FirstName,LastName,Email,City,Region,Password,MajorID;

    private boolean Gender,Active;

    public UserRegPost(String firstname, String lastname, String email, String city, String region, String password, boolean gender) {

        FirstName = firstname;
        LastName=lastname;
        Email = email;
        City = city;
        Region=region;
        Gender=gender;
        //Active=active;
        Password = password;
        //MajorID = majorID;


    }

}

