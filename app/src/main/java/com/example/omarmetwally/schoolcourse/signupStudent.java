package com.example.omarmetwally.schoolcourse;

import android.content.Intent;
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

public class signupStudent extends AppCompatActivity {


    private  Api a;
    TextView sign;
    EditText Fname,Lname,email,password,confpass;
    Spinner city,stage;
    Button reg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_student);



         Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://10.42.0.233:45455/api/User/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
          a= retrofit.create(Api.class);


        sign=(TextView)findViewById(R.id.signIn);
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(signupStudent.this,signIn.class));
            }
        });


        Fname=(EditText)findViewById(R.id.stdName);
        Lname=(EditText)findViewById(R.id.lastName);
        email=(EditText)findViewById(R.id.stdEmail);
        password=(EditText)findViewById(R.id.stdPass);
        confpass=(EditText)findViewById(R.id.stdPassConf);
        city=(Spinner) findViewById(R.id.stdCity);
        stage=(Spinner) findViewById(R.id.stdStage);
        reg=(Button)findViewById(R.id.stdBtnReg);



        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // userInput();
                creatPost();
            }
        });





    }


   /* void userInput()
    {
        String namee=name.getText().toString();
        String emaill=email.getText().toString();
        String pass=password.getText().toString();
        String pass2=confpass.getText().toString();


        Call<ResponseBody> call = RetrofitClient.getInstance().getApi().creatuser(namee,"Giza",emaill,pass,"2");

        Toast.makeText(signupStudent.this,call.toString(),Toast.LENGTH_LONG).show();


        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {


                   String s= response.raw().toString();
                   // Toast.makeText(signupStudent.this,s,Toast.LENGTH_LONG).show();




            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                //Toast.makeText(signupStudent.this,throwable.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

    }*/


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

        RetrofitClient z;
        Call<UserRegPost> call =a.creatPostStudent(userRegPost);

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

                    Toast.makeText(signupStudent.this,"faild registration",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<UserRegPost> call, Throwable t) {
               // Toast.makeText(signupStudent.this,t.getMessage(),Toast.LENGTH_LONG).show();
                Toast.makeText(signupStudent.this,"Successful registration",Toast.LENGTH_LONG).show();

                startActivity(new Intent(signupStudent.this,Main2Activity.class));

            }
        });

   }
    void backbtn(View view){

      /*  Intent intent = new Intent(signupStudent.this, signup2.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP );
        startActivity(intent);
        finish();*/
      onBackPressed();
    }

}
