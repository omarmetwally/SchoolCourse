package com.example.omarmetwally.schoolcourse.student;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import com.example.omarmetwally.schoolcourse.R;

public class Search_City extends AppCompatActivity {


    ExpandableListView expandableListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search__city);



        expandableListView=(ExpandableListView) findViewById(R.id.expand);

        final ExpandableListViewAdapter adapter=new ExpandableListViewAdapter(Search_City.this);
        expandableListView.setAdapter(adapter);


        //if(expandableListView!=null)
       // Toast.makeText(Search_City.this,adapter.govv+" // "+adapter.city,Toast.LENGTH_LONG).show();




    }

    public void onBackPressed(View view) {
        super.onBackPressed();
    }
}
