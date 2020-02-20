package com.example.omarmetwally.schoolcourse.student;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.omarmetwally.schoolcourse.Api;
import com.example.omarmetwally.schoolcourse.R;

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

public class myCoursesFragment extends Fragment {

    RecyclerView mreRecyclerView;
    mycourses_adapter adapter;
    Api a;
    ArrayList<mycourses_getdata>typedata =new ArrayList<>();
    String Coure_Name,Center_Name,Classnum;
    View vv;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        vv = inflater.inflate(R.layout.fragment_mycourses, container, false);




        SharedPreferences prefs = getActivity().getSharedPreferences("token", MODE_PRIVATE);
        final String Auth="Bearer "+ prefs.getString("token",null);

        Toast.makeText(getContext(),Auth,Toast.LENGTH_LONG).show();



        mreRecyclerView=vv.findViewById(R.id.MycoursesrecyclerView);
        mreRecyclerView.setHasFixedSize(true);
        mreRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        String sssss="601bfff3-af9f-4312-ad87-504bd13cc578";

        final DialogFragment loadingScreen=LoadingScreen.getInstance();
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
                .baseUrl("https://stc-api.herokuapp.com/api/UserSelection/AllCoursesStudent/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        a = retrofit.create(Api.class);
        final Call<List<mycourses_getdata>> call=a.mycourses();

        call.enqueue(new Callback<List<mycourses_getdata>>() {
            @Override
            public void onResponse(Call<List<mycourses_getdata>> call, Response<List<mycourses_getdata>> response) {



                List<mycourses_getdata> posts=response.body();

                for (mycourses_getdata post : posts) {


                    Coure_Name=post.getCourseName();
                    Center_Name=post.getCenterName();
                    Classnum=post.getClassNumber();


                    if (Center_Name == null)
                        Center_Name="Private Class";


                    /*if(Center_Name.isEmpty())
                    Center_Name="Private";

*/
                        post.setCourseName(Coure_Name);
                        post.setCenterName(Center_Name);
                        post.setClassNumber(Classnum);

                    typedata.add(post);

                }


               //Toast.makeText(getContext(),Coure_Name,Toast.LENGTH_LONG).show();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {


                        loadingScreen.dismiss();
                        adapter=new mycourses_adapter(getContext(),typedata);
                        mreRecyclerView.setAdapter(adapter);

                    }
                },2000);


                }

            @Override
            public void onFailure(Call<List<mycourses_getdata>> call, Throwable t) {

                Toast.makeText(getContext(),t.toString(),Toast.LENGTH_LONG).show();
                loadingScreen.dismiss();


            }
        });









       return vv;
       // return inflater.inflate(R.layout.fragment_mycourses, null);
    }
}