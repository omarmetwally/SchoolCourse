package com.example.omarmetwally.schoolcourse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.omarmetwally.schoolcourse.student.Main2Activity;
import com.example.omarmetwally.schoolcourse.teacher.TeacherMain;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class signInTeacher extends AppCompatActivity {

    EditText user,pass;
    TextView regi;
    TextView sign;
    boolean in=false;
    private  Api a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_teacher);


        sign=(TextView) findViewById(R.id.signin1);
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                creatPost();

            }
        });
        user=(EditText) findViewById(R.id.emailT);
        user.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // user.setCompoundDrawablesWithIntrinsicBounds(R.drawable.rectanglee, 0,R.drawable.email, 0);
                //  pass.setCompoundDrawablesWithIntrinsicBounds(0, 0,R.drawable.password, 0);


                return false;}


        });
        pass=(EditText) findViewById(R.id.passT);
        pass.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //  user.setCompoundDrawablesWithIntrinsicBounds(0, 0,R.drawable.email, 0);
                // pass.setCompoundDrawablesWithIntrinsicBounds(R.drawable.rectanglee, 0,R.drawable.password, 0);

                return false;}

        });

        regi=(TextView)findViewById(R.id.reg);
        regi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(signInTeacher.this,sinupTeacher.class));
            }
        });





    }


    private void creatPost()
    {
        String email=user.getText().toString().trim();
        String password=pass.getText().toString().trim();
        if(email.isEmpty())
        {
            user.setError("Email is required");
            user.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            user.setError("Enter a valid Email");
            user.requestFocus();
            return;
        }

        if(password.isEmpty())
        {
            pass.setError("Password is required");
            pass.requestFocus();
            return;
        }

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://10.42.0.233:45455/api/User/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        a= retrofit.create(Api.class);

        UserLoginPost userLoginPost=new UserLoginPost(email,password);

        //  RetrofitClient z;
        Call<UserLoginPost> call =a.userLogin(userLoginPost);

        call.enqueue(new Callback<UserLoginPost>() {
            @Override
            public void onResponse(Call<UserLoginPost> call, Response<UserLoginPost> response) {



                if(!response.isSuccessful())
                {
                    user.setError("Email or password is wrong");
                    user.requestFocus();
                    return;

                }
                startActivity(new Intent(signInTeacher.this,TeacherMain.class));
            }

            @Override
            public void onFailure(Call<UserLoginPost> call, Throwable t) {
                startActivity(new Intent(signInTeacher.this,TeacherMain.class));




                // startActivity(new Intent(signIn.this,TeacherSearch.class));
            }
        });




    }

    @Override
    public void onBackPressed() {
        backbtn(null);
    }

    void backbtn(View view){

        Intent intent = new Intent(signInTeacher.this, startup.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP );
        startActivity(intent);
        finish();

    }

}

