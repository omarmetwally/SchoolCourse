package com.example.omarmetwally.schoolcourse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class startup extends AppCompatActivity {
TextView b,b2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);




        b=(TextView) findViewById(R.id.student);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(startup.this,signIn.class));
            }
        });
        b2=(TextView) findViewById(R.id.teacher);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(startup.this,signInTeacher.class));
            }
        });
    }
}
