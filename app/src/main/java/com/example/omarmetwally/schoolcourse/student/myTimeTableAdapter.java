package com.example.omarmetwally.schoolcourse.student;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.omarmetwally.schoolcourse.R;

import java.util.ArrayList;

public class myTimeTableAdapter extends RecyclerView.Adapter<myTimeTableAdapter.ViewHolder> {


    ArrayList<MyTimeTableGetData> content;
    private Context mContext;

    public myTimeTableAdapter(Context context, ArrayList<MyTimeTableGetData> contact) {
        //super(context, R.layout.teachercontent, contact);

        content=contact;
        mContext = context;
    }




    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mytimetable, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {


        MyTimeTableGetData data=content.get(position);


        String classname=data.getClassName();
        String classtime=data.getStartTime()+" - "+data.getEndTime();





        holder.className.setText(classname);
        holder.classTime.setText(classtime);






    }

    @Override
    public int getItemCount() {
        return content.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView className,classTime;


        public ViewHolder(View itemView) {
            super(itemView);
            className = itemView.findViewById(R.id.className);
            classTime=itemView.findViewById(R.id.classTime);

        }
    }
}
