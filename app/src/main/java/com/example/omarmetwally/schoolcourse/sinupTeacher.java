package com.example.omarmetwally.schoolcourse;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.omarmetwally.schoolcourse.student.Main2Activity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class sinupTeacher extends AppCompatActivity {

    TextView st,sign;

    private  Api a;

    EditText Fname,Lname,email,password,confpass;
    Spinner city,region;
    Button reg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sinup_teacher);


        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://10.42.0.233:45455/api/User/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        a= retrofit.create(Api.class);


        sign=(TextView)findViewById(R.id.signIn);
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(sinupTeacher.this,signIn.class));
            }
        });


        Fname=(EditText)findViewById(R.id.Teacher_Fname);
        Lname=(EditText)findViewById(R.id.Teacher_Lname);
        email=(EditText)findViewById(R.id.Teacher_email);
        password=(EditText)findViewById(R.id.Teacher_pass);
        confpass=(EditText)findViewById(R.id.Teacher_conf);
        city=(Spinner) findViewById(R.id.Teacher_city);

        reg=(Button)findViewById(R.id.Teacher_reg);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                creatPost();
            }
        });



    }




    private  void creatPost(){

        String fnamee=Fname.getText().toString().trim();
        String lnamee=Lname.getText().toString().trim();
        String emaill=email.getText().toString().trim();
        String pass=password.getText().toString().trim();
        String pass2=confpass.getText().toString().trim();
        if(fnamee.isEmpty())
        {
            Fname.setError("First name is required");
            Fname.requestFocus();
            return;
        }
        if(lnamee.isEmpty())
        {
            Lname.setError("Last name is required");
            Lname.requestFocus();
            return;
        }
        if(emaill.isEmpty())
        {
            email.setError("Email is required");
            email.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(emaill).matches())
        {
            email.setError("Enter a valid Email");
            email.requestFocus();
            return;
        }

        if(pass.isEmpty())
        {
            password.setError("Password is required");
            password.requestFocus();
            return;
        }

        if(!pass.equals(pass2))
        {
            confpass.setError("Password doesn't match");
            confpass.requestFocus();
            return;
        }
        UserRegPost userRegPost =new UserRegPost(fnamee,lnamee,emaill,"6OCT","Giza",pass,false);

      //  RetrofitClient z;
        Call<UserRegPost> call =a.creatPostTeacher(userRegPost);

        call.enqueue(new Callback<UserRegPost>() {
            @Override
            public void onResponse(Call<UserRegPost> call, Response<UserRegPost> response) {


                //String s= response.body().toString();
                if(!response.isSuccessful())
                {
                    email.setError("Email is exist");
                    email.requestFocus();
                    return;

                }

                Toast.makeText(sinupTeacher.this,"Faild registration",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<UserRegPost> call, Throwable t) {
                // Toast.makeText(signupStudent.this,t.getMessage(),Toast.LENGTH_LONG).show();
               // Toast.makeText(sinupTeacher.this,"Successful registration",Toast.LENGTH_LONG).show();




                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(sinupTeacher.this);
                alertDialogBuilder.setTitle("Teacher Registration");
                alertDialogBuilder
                        .setMessage("Your Account has been  submitted successfully \nplease wait for  activation")
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

                                        startActivity(new Intent(sinupTeacher.this,Main2Activity.class));
                                    }
                                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();



               // startActivity(new Intent(sinupTeacher.this,TeacherSearch.class));

            }
        });

    }





















    void backbtn(View view){

       /* Intent intent = new Intent(sinupTeacher.this, signup2.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP );
        startActivity(intent);
        finish();*/
       onBackPressed();
    }
}
