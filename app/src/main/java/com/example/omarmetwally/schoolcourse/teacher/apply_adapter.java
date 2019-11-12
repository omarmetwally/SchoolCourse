package com.example.omarmetwally.schoolcourse.teacher;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.omarmetwally.schoolcourse.R;
import com.example.omarmetwally.schoolcourse.student.mycourses_adapter;
import com.example.omarmetwally.schoolcourse.student.mycourses_getdata;

import java.util.ArrayList;


public class apply_adapter extends RecyclerView.Adapter<apply_adapter.ViewHolder>  {

    private static final String TAG = "RecyclerViewAdapter";

    ArrayList<apply_forCenterData> content;
    //vars
    private Context mContext;

    public apply_adapter(Context context, ArrayList<apply_forCenterData> contact) {
        //super(context, R.layout.teachercontent, contact);

        content=contact;
        mContext = context;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.centeners_apply, viewGroup, false);
        return new ViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        apply_forCenterData data=content.get(position);



        String city=data.getCity()+" - "+data.getRegion();
        String center=data.getName();

        String phone=data.getPhone();




        holder.cent.setText(center);
        holder.city.setText(city);
        holder.phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:01126513889"));
                mContext.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return content.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        // CircleImageView image;
        TextView cent,city;
        ImageView phone;


        public ViewHolder(View itemView) {
            super(itemView);
           //  image = itemView.findViewById(R.id.img);

            cent=itemView.findViewById(R.id.centNameapply);
            city =itemView.findViewById(R.id.city);
            phone=itemView.findViewById(R.id.phone);
        }
    }

}
