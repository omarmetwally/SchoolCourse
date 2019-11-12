package com.example.omarmetwally.schoolcourse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class signup2 extends AppCompatActivity {

    Button std,tech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup2);
        std=(Button)findViewById(R.id.stdAcc);
        std.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(signup2.this,signupStudent.class));

            }
        });

        tech=(Button)findViewById(R.id.techAcc);
        tech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(signup2.this,sinupTeacher.class));

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
