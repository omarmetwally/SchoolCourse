package com.example.omarmetwally.schoolcourse.teacher;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.omarmetwally.schoolcourse.Api;
import com.example.omarmetwally.schoolcourse.R;
import com.example.omarmetwally.schoolcourse.student.LoadingScreen;
import com.example.omarmetwally.schoolcourse.student.mycourses_adapter;
import com.example.omarmetwally.schoolcourse.student.mycourses_getdata;
import com.example.omarmetwally.schoolcourse.student.teacherSelection;

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

public class Fragment_public extends Fragment {

    View vv;
    List<String> lstspinner=new ArrayList<String>();
    ArrayAdapter adapterspinner;
    Spinner s;
    public_private_adapter adapter;
    ArrayList<public_privateClassesContent>typedata =new ArrayList<>();
    RecyclerView mreRecyclerView;

    Api a;
    String classId,className,subject,centerId,time,dateSqunce,capacity,centerName;
    final DialogFragment loadingScreen=LoadingScreen.getInstance();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        vv = inflater.inflate(R.layout.fragment_public, container, false);

        mreRecyclerView=vv.findViewById(R.id.recPublic);
        mreRecyclerView.setHasFixedSize(true);
        mreRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        s=(Spinner)vv.findViewById(R.id.publicSpinner);

        lstspinner.add("saturday".toUpperCase());
        lstspinner.add("sunday".toUpperCase());
        lstspinner.add("monday".toUpperCase());
        lstspinner.add("tuesday".toUpperCase());
        lstspinner.add("wednesday".toUpperCase());
        lstspinner.add("thursday".toUpperCase());
        lstspinner.add("friday".toUpperCase());
        adapterspinner = new ArrayAdapter(getContext(), android.R.layout.simple_dropdown_item_1line, lstspinner);
        s.setAdapter(adapterspinner);

        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext()," "+lstspinner.get(position),Toast.LENGTH_LONG).show();

                classes(lstspinner.get(position).toLowerCase());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });






        return vv;
    }
    void classes(final String date)
    {
        typedata.clear();
        mreRecyclerView.removeAllViews();
        String sssss="9b3dbc75-feb5-416e-baec-e5e560f1ce5e";

        SharedPreferences prefs = getActivity().getSharedPreferences("token", MODE_PRIVATE);
        final String Auth="Bearer "+ prefs.getString("token",null);

        loadingScreen.show(getFragmentManager(),"loading Screen");


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
                .baseUrl("https://stc-api.herokuapp.com//api/classes/SeeAllCenterClasses/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        a = retrofit.create(Api.class);
        final Call<List<public_privateClassesContent>> call=a.content();

        call.enqueue(new Callback<List<public_privateClassesContent>>() {
            @Override
            public void onResponse(Call<List<public_privateClassesContent>> call, Response<List<public_privateClassesContent>> response) {
                Toast.makeText(getContext(),"Done 200",Toast.LENGTH_LONG).show();


                List<public_privateClassesContent> posts=response.body();

                for (public_privateClassesContent post : posts) {

                    String classId,className,subject,centerId,time,dateSqunce,capacity,centerName;

                    classId=post.getClassId();
                    className=post.getClassName();
                    subject=post.getSubject();
                    centerId=post.getCenterId();
                    time=post.getTime();
                    dateSqunce=post.getDateSqunce();
                    capacity=post.getCapacity();
                    centerName=post.getCenterName();





                    if(date.equals(dateSqunce)) {
                        post.setClassId(classId);
                        post.setClassName(className);
                        post.setSubject(subject);
                        post.setCenterId(centerId);
                        post.setTime(time);
                        post.setDateSqunce(dateSqunce);
                        post.setCapacity(capacity);
                        post.setCenterName(centerName);


                    typedata.add(post);}

                }


                // Toast.makeText(getContext(),Coure_Name,Toast.LENGTH_LONG).show();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {


                        loadingScreen.dismiss();
                        adapter=new public_private_adapter(getContext(),typedata);
                        mreRecyclerView.setAdapter(adapter);

                    }
                },1000);

            }

            @Override
            public void onFailure(Call<List<public_privateClassesContent>> call, Throwable t) {
                loadingScreen.dismiss();

            }
        });


    }
}
