package com.example.omarmetwally.schoolcourse.student;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class ExpandableListViewAdapter extends BaseExpandableListAdapter {

String[] gov={"Giza","Cairo","Alexandria","Monofia"};
String[][] cites={{"6 October","sheikh Zayed","Kerdasa","Manshiyet Al Qanater","Embaba"},
        {"Shobra","Masr el gdeda","Naser City"},
        {"Burj Al Arab","Sidi bishr","El Montazh"},
        {"Shbeen El Koom","Ashmon"}};

Context context;

String govv,city;
public ExpandableListViewAdapter(Context context)
{
    this.context=context;
}
    @Override
    public int getGroupCount() {
        return gov.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return cites[groupPosition].length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return gov[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return cites[groupPosition][childPosition];
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {



    TextView txt=new TextView(context);
    txt.setText(gov[groupPosition]);


        txt.setPadding(100,100,0,100);

        txt.setTextColor(Color.parseColor("#F67F21"));
        txt.setTextSize(20);
        txt.setTypeface(Typeface.DEFAULT_BOLD);

    return txt;










    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
       final TextView txt=new TextView(context);
        txt.setText(cites[groupPosition][childPosition]);

        final LayoutInflater lay=LayoutInflater.from(context);
        txt.setPadding(100,50,0,100);
        txt.setTextColor(Color.BLACK);
        txt.setTextSize(15);
        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context,gov[groupPosition]+" // "+txt.getText().toString(),Toast.LENGTH_LONG).show();


                Intent intent = new Intent(context, TeacherSearch.class);
                context.startActivity(intent);


            }
        });

        return txt;

    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
