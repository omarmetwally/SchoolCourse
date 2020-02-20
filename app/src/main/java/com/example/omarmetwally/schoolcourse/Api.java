package com.example.omarmetwally.schoolcourse;

import com.example.omarmetwally.schoolcourse.student.GetTeacher;
import com.example.omarmetwally.schoolcourse.student.MyTimeTableGetData;
import com.example.omarmetwally.schoolcourse.student.centerGetData;
import com.example.omarmetwally.schoolcourse.student.classesGetData;
import com.example.omarmetwally.schoolcourse.student.mycourses_getdata;
import com.example.omarmetwally.schoolcourse.student.reservePost;
import com.example.omarmetwally.schoolcourse.student.subjectGetData;
import com.example.omarmetwally.schoolcourse.teacher.apply_forCenterData;
import com.example.omarmetwally.schoolcourse.teacher.public_privateClassesContent;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {

    /*@FormUrlEncoded
   @POST("register")
    Call<ResponseBody>creatuser(
            @Field("FullName") String name,
            @Field("City") String city,
            @Field("Email") String email,
            @Field("Password")String pass,
            @Field("MajorID") String major

    );*/


   @POST("registrationStudent")
    Call<UserRegPost>creatPostStudent(@Body UserRegPost userRegPost);

   @POST("registrationTeacher")
    Call<UserRegPost>creatPostTeacher(@Body UserRegPost userRegPost);

   @POST("login")
   Call<UserLoginPost>userLogin(@Body UserLoginPost userLoginPost);

   @POST("reserver")
   Call<reservePost>reserve(@Body reservePost reservePost);


 @GET("Search/getTeachersWithCenter")
    Call<List<GetTeacher>>getTeacher();


    @GET(" ")
    Call<List<mycourses_getdata>>mycourses();

    @GET(" ")
    Call<List<MyTimeTableGetData>>mytimetable();

    @GET(" ")
    Call<List<centerGetData>>mycenters();

    @GET(" ")
    Call<List<subjectGetData>>subData();

    @GET(" ")
    Call<List<classesGetData>>classesData();

    @GET(" ")
    Call<List<public_privateClassesContent>>content();


    @GET("api/TeacherRequest/AllCenters/")
    Call<List<apply_forCenterData>>applyy();






}
