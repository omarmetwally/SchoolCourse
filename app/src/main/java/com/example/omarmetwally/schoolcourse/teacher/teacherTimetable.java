package com.example.omarmetwally.schoolcourse.teacher;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.example.omarmetwally.schoolcourse.student.MyTimeTableGetData;
import com.example.omarmetwally.schoolcourse.student.myTimeTableAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;

public class teacherTimetable extends Fragment {

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
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        vv = inflater.inflate(R.layout.fragment_mytimetable, container, false);


        SharedPreferences prefs = this.getActivity().getSharedPreferences("token", MODE_PRIVATE);
        final  String Auth="Bearer "+ prefs.getString("token",null);


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

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://stc-api.herokuapp.com/api/UserSelection/AllTimeTableStundet/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
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


        return vv;
    }

}
