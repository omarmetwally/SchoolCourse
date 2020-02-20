package com.example.omarmetwally.schoolcourse.student;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.omarmetwally.schoolcourse.Api;
import com.example.omarmetwally.schoolcourse.R;
import com.example.omarmetwally.schoolcourse.UserLoginPost;
import com.example.omarmetwally.schoolcourse.signIn;
import com.example.omarmetwally.schoolcourse.startup;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;

public class classesAdapter extends RecyclerView.Adapter<classesAdapter.ViewHolder> {


    private static final String TAG = "RecyclerViewAdapter";

    ArrayList<classesGetData> content;
     String Id;


      Api a;

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
        Id=data.getClassId();


        holder.day.setText(dayy);
        holder.time.setText(time);
        holder.cap.setText("Capacity : "+cap);



        if(cap.equals("0"))
        {
            holder.select.setBackgroundColor(R.drawable.redroundedbutton);
        }



        holder.select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reservePost reservePost=new reservePost(Id);

                SharedPreferences prefs = mContext.getSharedPreferences("token", MODE_PRIVATE);


               final String Auth="Bearer "+ prefs.getString("token",null);




                OkHttpClient okHttpClient=new OkHttpClient.Builder().
                        addInterceptor(new Interceptor() {
                            @Override
                            public okhttp3.Response intercept(Chain chain) throws IOException {
                                Request original=chain.request();

                                Request.Builder requestBilder=original.newBuilder()
                                        .addHeader("Authorization",Auth)
                                        .method(original.method(),original.body());

                                Request request=requestBilder.build();

                                return chain.proceed(request);
                            }
                        }).build();



                Retrofit retrofit=new Retrofit.Builder()
                        .baseUrl("https://stc-api.herokuapp.com/api/Reservation/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(okHttpClient)
                        .build();
                a= retrofit.create(Api.class);
                Call<reservePost> call =a.reserve(reservePost);

                call.enqueue(new Callback<reservePost>() {
                    @Override
                    public void onResponse(Call<reservePost> call, Response<reservePost> response) {


                        Toast.makeText(mContext,"Successful reservation",Toast.LENGTH_LONG).show();



                        Intent intent = new Intent(mContext, Student_main.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP );
                        mContext.startActivity(intent);

                        ((Activity)mContext).finish();


                    }

                    @Override
                    public void onFailure(Call<reservePost> call, Throwable t) {


                       // Toast.makeText(signIn.this,"Check your internet connection",Toast.LENGTH_LONG).show();
                        }
                });


            }
        });




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
