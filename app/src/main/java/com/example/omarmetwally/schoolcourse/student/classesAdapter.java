package com.example.omarmetwally.schoolcourse.student;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.omarmetwally.schoolcourse.R;

import java.util.ArrayList;

public class classesAdapter extends RecyclerView.Adapter<classesAdapter.ViewHolder> {


    private static final String TAG = "RecyclerViewAdapter";

    ArrayList<classesGetData> content;



    private Context mContext;

    public classesAdapter(Context context, ArrayList<classesGetData> contact) {

        content=contact;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.timetable_design, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");


        classesGetData data=content.get(position);

        String dayy=data.getDay().toUpperCase()+"\nStarts on "+data.getStartDay();
        String time=data.getStartTime()+" - "+data.getEndTime();
        String cap=data.getCapacity();


        holder.day.setText(dayy);
        holder.time.setText(time);
        holder.cap.setText("Capacity : "+cap);



        if(cap.equals("0"))
        {
            holder.select.setBackgroundColor(R.drawable.redroundedbutton);
        }







    }

    @Override
    public int getItemCount() {return content.size(); }

    public class ViewHolder extends RecyclerView.ViewHolder{

       // CircleImageView image;
        TextView day,time,cap;
        Button select;

        public ViewHolder(View itemView) {
            super(itemView);
           // image = itemView.findViewById(R.id.image_view);
            day = itemView.findViewById(R.id.day);
            time=itemView.findViewById(R.id.time);
            cap=itemView.findViewById(R.id.capacity);
            select=itemView.findViewById(R.id.selectbtn);

        }
    }








}
