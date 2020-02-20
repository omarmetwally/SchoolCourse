package com.example.omarmetwally.schoolcourse.student;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.omarmetwally.schoolcourse.R;

import java.util.ArrayList;

public class centerAdapter extends RecyclerView.Adapter<centerAdapter.ViewHolder> {


    ArrayList<centerGetData> content;
    //vars
    private Context mContext;




    String c=new String("omars");



    public  centerAdapter(Context context, ArrayList<centerGetData> contact){

        content=contact;
        mContext = context;



    }




    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.centername, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int position) {


        centerGetData data=content.get(position);

        final String centerid=data.getCenterId();
        final String centername=data.getFirstname();

        viewHolder.centName.setText(centername);


    }

    @Override
    public int getItemCount() {
        return content.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        // CircleImageView image;
        TextView centName;


        public ViewHolder(View itemView) {
            super(itemView);
            // image = itemView.findViewById(R.id.image_view);
            centName = itemView.findViewById(R.id.centerN);


        }


    }
}
