package com.example.omarmetwally.schoolcourse.student;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.omarmetwally.schoolcourse.Api;
import com.example.omarmetwally.schoolcourse.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyTimeTable extends Fragment {

    Api a;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    int dayswitch=0;
    String day,time1,time2,classname;
    RecyclerView rec1,rec2,rec3,rec4,rec5,rec6,rec7;
    myTimeTableAdapter ad1,ad2,ad3,ad4,ad5,ad6,ad7;
    ArrayList<MyTimeTableGetData> typedata1=new ArrayList<>(),typedata2=new ArrayList<>(),typedata3=new ArrayList<>(),typedata4=new ArrayList<>(),typedata5=new ArrayList<>(),typedata6=new ArrayList<>(),typedata7=new ArrayList<>() ;

    TextView d1,d2,d3,d4,d5,d6,d7;

    View vv;
    FrameLayout bio,ar;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        vv= inflater.inflate(R.layout.fragment_mytimetable, container, false);

        String sssss="601bfff3-af9f-4312-ad87-504bd13cc578";

        d1=vv.findViewById(R.id.d1);
        d2=vv.findViewById(R.id.d2);
        d3=vv.findViewById(R.id.d3);
        d4=vv.findViewById(R.id.d4);
        d5=vv.findViewById(R.id.d5);
        d6=vv.findViewById(R.id.d6);
        d7=vv.findViewById(R.id.d7);

        rec1=vv.findViewById(R.id.rec1);
        rec1.setHasFixedSize(true);
        rec1.setLayoutManager(new LinearLayoutManager(getContext()));


        rec2=vv.findViewById(R.id.rec2);
        rec2.setHasFixedSize(true);
        rec2.setLayoutManager(new LinearLayoutManager(getContext()));


        rec3=vv.findViewById(R.id.rec3);
        rec3.setHasFixedSize(true);
        rec3.setLayoutManager(new LinearLayoutManager(getContext()));


        rec4=vv.findViewById(R.id.rec4);
        rec4.setHasFixedSize(true);
        rec4.setLayoutManager(new LinearLayoutManager(getContext()));


        rec5=vv.findViewById(R.id.rec5);
        rec5.setHasFixedSize(true);
        rec5.setLayoutManager(new LinearLayoutManager(getContext()));


        rec6=vv.findViewById(R.id.rec6);
        rec6.setHasFixedSize(true);
        rec6.setLayoutManager(new LinearLayoutManager(getContext()));


        rec7=vv.findViewById(R.id.rec7);
        rec7.setHasFixedSize(true);
        rec7.setLayoutManager(new LinearLayoutManager(getContext()));




        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.42.0.233:45455/api/UserSelection/AllTimeTableStundet/"+sssss+"/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        a = retrofit.create(Api.class);
        final Call<List<MyTimeTableGetData>> call=a.mytimetable();

        call.enqueue(new Callback<List<MyTimeTableGetData>>() {
            @Override
            public void onResponse(Call<List<MyTimeTableGetData>> call, Response<List<MyTimeTableGetData>> response) {

                List<MyTimeTableGetData> posts=response.body();

                for (MyTimeTableGetData post : posts) {

                    day=post.getDay().toLowerCase();
                    time1=post.getStartTime();
                    time2=post.getEndTime();
                    classname=post.getClassName();

                    switch (day){
                        case "saturday":

                            d1.setText("Saturday");
                            d1.setTextSize(20);
                            post.setClassName(classname);
                            post.setStartTime(time1);
                            post.setEndTime(time2);
                            typedata1.add(post);
                            Toast.makeText(getContext(),day,Toast.LENGTH_SHORT).show();


                            break;
                        case "sunday":
                            d2.setText("Sunday");
                            d2.setTextSize(20);
                            post.setClassName(classname);
                            post.setStartTime(time1);
                            post.setEndTime(time2);
                            typedata2.add(post);

                            break;
                        case"monday":
                            d3.setText("Monday");
                            d3.setTextSize(20);
                            post.setClassName(classname);
                            post.setStartTime(time1);
                            post.setEndTime(time2);
                            typedata3.add(post);

                            break;
                        case"tuesday":
                            d4.setText("Tuesday");
                            d4.setTextSize(20);
                            post.setClassName(classname);
                            post.setStartTime(time1);
                            post.setEndTime(time2);
                            typedata4.add(post);
                            break;
                        case "wednesday":
                            d5.setText("wednesday");
                            d5.setTextSize(20);
                            post.setClassName(classname);
                            post.setStartTime(time1);
                            post.setEndTime(time2);
                            typedata5.add(post);
                            break;
                        case "thursday":
                            d6.setText("Thursday");
                            d6.setTextSize(20);
                            post.setClassName(classname);
                            post.setStartTime(time1);
                            post.setEndTime(time2);
                            typedata6.add(post);
                            break;
                        case "friday":
                            d7.setText("Friday");
                            d7.setTextSize(20);
                            post.setClassName(classname);
                            post.setStartTime(time1);
                            post.setEndTime(time2);
                            typedata7.add(post);
                            break;
                            default:
                                Toast.makeText(getContext(),"Something wrong with time table database ",Toast.LENGTH_SHORT).show();



                    }


                }

                ad1=new myTimeTableAdapter(getContext(),typedata1);
                rec1.setAdapter(ad1);

                ad2=new myTimeTableAdapter(getContext(),typedata2);
                rec2.setAdapter(ad2);

                ad3=new myTimeTableAdapter(getContext(),typedata3);
                rec3.setAdapter(ad3);

                ad4=new myTimeTableAdapter(getContext(),typedata4);
                rec4.setAdapter(ad4);

                ad5=new myTimeTableAdapter(getContext(),typedata5);
                rec5.setAdapter(ad5);

                ad6=new myTimeTableAdapter(getContext(),typedata6);
                rec6.setAdapter(ad6);

                ad7=new myTimeTableAdapter(getContext(),typedata7);
                rec7.setAdapter(ad7);




            }

            @Override
            public void onFailure(Call<List<MyTimeTableGetData>> call, Throwable t) {

            }
        });





















































      // final View vshow = inflater.inflate(R.layout.attendance, container, false);

       /* bio=(FrameLayout)vv.findViewById(R.id.bio_frame);
        ar=(FrameLayout)vv.findViewById(R.id.arabic_frame);

        bio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);


              //  Toast.makeText(getActivity(),"tmaaaam",Toast.LENGTH_LONG).show();


            }

        });
       final LayoutInflater lay=LayoutInflater.from(getActivity());

        ar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                final View vshow= lay.inflate(R.layout.attendance,null);

                AlertDialog.Builder msg=new AlertDialog.Builder(getActivity());
                msg.setView(vshow);

                msg.setPositiveButton("Send", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Toast.makeText(getActivity(),"Attendance submitted",Toast.LENGTH_LONG).show();
                    }
                });
                msg.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                    }
                });
                msg.create().show();
//                msg.show();

               // return vshow;
            }
        });

        */
       return  vv;
        //return inflater.inflate(R.layout.fragment_mytimetable, null);
    }


}