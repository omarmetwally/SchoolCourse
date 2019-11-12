package com.example.omarmetwally.schoolcourse.student;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.omarmetwally.schoolcourse.R;

import java.util.ArrayList;

public class mycourses_adapter extends RecyclerView.Adapter<mycourses_adapter.ViewHolder> {


    private static final String TAG = "RecyclerViewAdapter";

    ArrayList<mycourses_getdata> content;
    //vars
    private Context mContext;

    public mycourses_adapter(Context context, ArrayList<mycourses_getdata> contact) {
        //super(context, R.layout.teachercontent, contact);

        content=contact;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mycourses, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {



        mycourses_getdata data=content.get(position);


        String subject=data.getCourseName();
        String clss=data.getClassNumber();
        String center=data.getCenterName();





        holder.sub.setText(subject);
        holder.cent.setText(center);
        holder.classs.setText("Class number "+clss);





    }

    @Override
    public int getItemCount() {
        return content.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        // CircleImageView image;
        TextView sub,cent,classs;


        public ViewHolder(View itemView) {
            super(itemView);
            // image = itemView.findViewById(R.id.image_view);
            sub = itemView.findViewById(R.id.sub_name);
            cent=itemView.findViewById(R.id.center_name);
            classs =itemView.findViewById(R.id.class_num);

        }
    }



}
