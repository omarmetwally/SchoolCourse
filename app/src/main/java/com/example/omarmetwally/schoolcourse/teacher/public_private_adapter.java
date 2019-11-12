package com.example.omarmetwally.schoolcourse.teacher;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.omarmetwally.schoolcourse.R;

import java.util.ArrayList;


public class public_private_adapter extends RecyclerView.Adapter<public_private_adapter.ViewHolder> implements View.OnClickListener,View.OnLongClickListener {


    ArrayList<public_privateClassesContent> content;

    private ItemClickListener i;
    private Context mContext;

    public public_private_adapter(Context context, ArrayList<public_privateClassesContent> contact) {
        //super(context, R.layout.teachercontent, contact);

        content=contact;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.publuc_private_list_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {



        public_privateClassesContent data=content.get(position);




        String classId=data.getClassId();
        String className=data.getClassName();
        String  centerId=data.getCenterId();
        String time=data.getTime();
        String dateSqunce=data.getDateSqunce();
        String capacity=data.getCapacity();
        String subject=data.getSubject();
        String centerName=data.getCenterName();







        holder.capacity.setText("Capacity : "+capacity);
        if (capacity.equals("0"))
            holder.capacity.setTextColor(Color.parseColor("#FF0000"));
        holder.cent.setText(centerName);
        holder.classs.setText(subject+" "+className);
        holder.time.setText(time);








    }

    @Override
    public int getItemCount() {
        return content.size();
    }

    @Override
    public void onClick(View v) {


    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        // CircleImageView image;
        TextView capacity,cent,classs,time;


        public ViewHolder(View itemView) {
            super(itemView);
            // image = itemView.findViewById(R.id.image_view);
            capacity = itemView.findViewById(R.id.capacityC);
            cent=itemView.findViewById(R.id.centername);
            classs =itemView.findViewById(R.id.classname);
            time=itemView.findViewById(R.id.timeC);

        }
    }
}
