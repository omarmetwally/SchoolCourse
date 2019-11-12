package com.example.omarmetwally.schoolcourse.teacher;

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
import android.widget.TextView;
import android.widget.Toast;

import com.example.omarmetwally.schoolcourse.Api;
import com.example.omarmetwally.schoolcourse.R;
import com.example.omarmetwally.schoolcourse.RetrofitClient;
import com.example.omarmetwally.schoolcourse.student.LoadingScreen;
import com.example.omarmetwally.schoolcourse.student.mycourses_adapter;
import com.example.omarmetwally.schoolcourse.student.mycourses_getdata;

import java.sql.Time;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApplyForCenterFragment extends Fragment {

    TextView s;
    View vv;
    RecyclerView mreRecyclerView;
    Api a;
    ArrayList<apply_forCenterData> typedata =new ArrayList<>();
    String Center_Name,city,phone_img,region,img;
    apply_adapter adapter;
    RetrofitClient test;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        vv = inflater.inflate(R.layout.fragment_apply_for_center, container, false);
        final DialogFragment loadingScreen=LoadingScreen.getInstance();
        loadingScreen.show(getFragmentManager(),"loading Screen");
        loadingScreen.setCancelable(false);



        mreRecyclerView=vv.findViewById(R.id.recApply);
        mreRecyclerView.setHasFixedSize(true);
        mreRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(test.Base_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        a = retrofit.create(Api.class);
        final Call<List<apply_forCenterData>> call=a.applyy();

        call.enqueue(new Callback<List<apply_forCenterData>>() {
            @Override
            public void onResponse(Call<List<apply_forCenterData>> call, Response<List<apply_forCenterData>> response) {

                List<apply_forCenterData> posts=response.body();
                for (apply_forCenterData post : posts) {


                    Center_Name=post.getName();
                    city=post.getCity();
                    region=post.getRegion();

                    img=post.getImage();
                    phone_img=post.getPhone();


                    if (img == null)
                        img="Private Class";

                    if (phone_img == null)
                        phone_img="Private Class";




                    post.setName(Center_Name);
                    post.setCity(city);
                    post.setRegion(region);

                    typedata.add(post);

                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {


                        loadingScreen.dismiss();
                        adapter=new apply_adapter(getContext(),typedata);
                        mreRecyclerView.setAdapter(adapter);

                    }
                },2000);



            }

            @Override
            public void onFailure(Call<List<apply_forCenterData>> call, Throwable t) {
                loadingScreen.dismiss();

                Toast.makeText(getContext(),"65555",Toast.LENGTH_LONG).show();
            }
        });


        return vv;
    }
  /*  private boolean checktimings(String time, String endtime) {

        String pattern = "HH:mm";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);

        try {
            Date date1 = sdf.parse(time);
            Date date2 = sdf.parse(endtime);

            if(date1.before(date2)) {
                return true;
            } else {

                return false;
            }
        } catch (ParseException e){
            e.printStackTrace();
        }
        return false;
    }*/
}
/*        s=(TextView)vv.findViewById(R.id.testtime);

       // Date currentTime = Calendar.getInstance().getTime();

        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat format=new SimpleDateFormat("HH:mm:ss");


        String time=format.format(calendar.getTime());

        String d="23:20:00",d1="21:00:01";


        if(checktimings(d,time))
            s.setText(d);
        else
            s.setText(time);
*/
