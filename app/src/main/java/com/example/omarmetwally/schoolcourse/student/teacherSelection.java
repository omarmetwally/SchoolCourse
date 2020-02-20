package com.example.omarmetwally.schoolcourse.student;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
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

public class teacherSelection extends AppCompatActivity {

    private  static final  String [] con=new String[]{"basmla","el safwa"};

    String centerN,centerID,clssID;
    String subN,subID,stageN,stageID;
    String d,Sd,c,t1,t2;

    classesAdapter adapter;
    Api a;
    ArrayAdapter adapterspinner;
    ArrayList<classesGetData>typedata =new ArrayList<>();

    List<String> lstspinner=new ArrayList<String>();
    List<String> lstspinnerid=new ArrayList<String>();

    LinearLayoutManager layoutManager;
    RecyclerView recyclerView ;

    TextView price;


    View v;
    Spinner s1,s2;

    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> times = new ArrayList<>();
    RecyclerView mreRecyclerView;
    TextView tName;
    String j,id;
    OkHttpClient okHttpClient;
     String Auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_teacher_selection);

        layoutManager = new LinearLayoutManager(teacherSelection.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView = findViewById(R.id.recyclerView);





        SharedPreferences prefs = getSharedPreferences("token", MODE_PRIVATE);


        Auth="Bearer "+ prefs.getString("token",null);





        v=(View)findViewById(R.id.divider2);

        price=(TextView)findViewById(R.id.price);

        s1=(Spinner)findViewById(R.id.centerSpinner);
        s2=(Spinner)findViewById(R.id.subSpinner);
        tName=(TextView)findViewById(R.id.tname);
        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null)
        {
             j =(String) b.get("name");
            tName.setText(j);
            id=(String) b.get("id");
        }

        //Toast.makeText(this," "+id,Toast.LENGTH_LONG).show();



        lstspinner.add("Select a Center");
        lstspinnerid.add(null);




         okHttpClient=new OkHttpClient.Builder().
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
                .baseUrl("https://stc-api.herokuapp.com/api/UserSelection/"+id+"/Centers/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        a = retrofit.create(Api.class);
        final Call<List<centerGetData>> call=a.mycenters();

        call.enqueue(new Callback<List<centerGetData>>() {
            @Override
            public void onResponse(Call<List<centerGetData>> call, Response<List<centerGetData>> response) {
                List<centerGetData> posts=response.body();

                for (centerGetData post : posts) {

                    centerN=post.getFirstname();
                    centerID=post.getCenterId();

                    post.setFirstname(centerN);
                    post.setCenterId(centerID);


                   // typedata.add(post);
                    lstspinner.add(centerN);
                    lstspinnerid.add(centerID);
                   // Toast.makeText(teacherSelection.this,lstspinner.get(0),Toast.LENGTH_LONG).show();



                }
                //adapter=new centerAdapter(teacherSelection.this,typedata);


               /* LinearLayoutManager layoutManager = new LinearLayoutManager(getApplication(), LinearLayoutManager.HORIZONTAL, false);
                final RecyclerView recyclerView = findViewById(R.id.centerrec);
                recyclerView.setLayoutManager(layoutManager);

                recyclerView.setAdapter(adapter);*/

              /*  adapterspinner = new ArrayAdapter(teacherSelection.this, android.R.layout.simple_spinner_dropdown_item, lstspinner);

               s1.setAdapter(adapterspinner);
*/


            }

            @Override
            public void onFailure(Call<List<centerGetData>> call, Throwable t) {

            }
        });





        adapterspinner = new ArrayAdapter(teacherSelection.this, android.R.layout.simple_spinner_dropdown_item, lstspinner);

        s1.setAdapter(adapterspinner);

        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long ids) {

                if(lstspinnerid.get(position)!=null) {

                    price.setText("0 L.E");

                    recyclerView.setAdapter(null);
                   // Toast.makeText(teacherSelection.this, lstspinnerid.get(position), Toast.LENGTH_LONG).show();

                    typedata.clear();

                    String poo=lstspinnerid.get(position);

                    sub(id,poo);

                    //lstsub.clear();
                   // lstsubID.clear();
                    //lststage.clear();
                    //lststageID.clear();


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });





        final DialogFragment loadingScreen=LoadingScreen.getInstance();
        loadingScreen.show(getSupportFragmentManager(),"loading Screen");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loadingScreen.dismiss();

            }
        },1000);





       /* LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);
        classesAdapter adapter = new classesAdapter(this, mNames, times);
        recyclerView.setAdapter(adapter);*/






    }


    public void loadclasses(String tid,String cid,String sub,String stg){

      //  v.getLayoutParams().height=15;
        typedata.clear();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://stc-api.herokuapp.com/api/UserSelection/"+tid+"/"+cid+"/"+sub+"/"+stg+"/class/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        a = retrofit.create(Api.class);
        final Call<List<classesGetData>> call=a.classesData();
        call.enqueue(new Callback<List<classesGetData>>() {
            @Override
            public void onResponse(Call<List<classesGetData>> call, Response<List<classesGetData>> response) {

                List<classesGetData> posts=response.body();

                for (classesGetData post : posts) {
                    d=post.getDay();
                    Sd=post.getStartDay();
                    c=post.getCapacity();
                    clssID=post.getClassId();
                    t1=post.getStartTime();
                    t2=post.getEndTime();
                    String p=post.getPrice();


                    post.setDay(d);
                    post.setStartDay(Sd);
                    post.setCapacity(c);
                    post.setStartTime(t1);
                    post.setEndTime(t2);
                    post.setPrice(p);
                    post.setClassId(clssID);

                    typedata.add(post);

                    price.setText(p+" L.E");

                }
                adapter=new classesAdapter(teacherSelection.this,typedata);

                //price.setText(typedata.get(0).getPrice());


                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);



            }

            @Override
            public void onFailure(Call<List<classesGetData>> call, Throwable t) {

            }
        });



       // initRecyclerView();

    }

    private void initRecyclerView(){

        /*LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);
        classesAdapter adapter = new classesAdapter(this, mNames, times);
        recyclerView.setAdapter(adapter);*/
    }


    private void sub(String tid, final String cid)
    {

        recyclerView.setAdapter(null);

        final  List<String> lstsub=new ArrayList<String>();
      final List<String> lstsubID=new ArrayList<String>();
      final List<String> lststage=new ArrayList<String>();
      final List<String> lststageID=new ArrayList<String>();

        lstsub.add("Select a Subject");
        lstsubID.add(null);

        lststage.add("Select a stage");
        lststageID.add(null);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://stc-api.herokuapp.com/api/UserSelection/"+tid+"/"+cid+"/Subjects/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        a = retrofit.create(Api.class);
        final Call<List<subjectGetData>> call=a.subData();

        call.enqueue(new Callback<List<subjectGetData>>() {
            @Override
            public void onResponse(Call<List<subjectGetData>> call, Response<List<subjectGetData>> response) {
                List<subjectGetData> posts=response.body();

                for (subjectGetData post : posts) {

                    subN=post.getSubjectName();
                    subID=post.getSubjectId();

                    stageN=post.getStageName();
                    stageID=post.getStageId();

                    post.setSubjectName(subN);
                    post.setSubjectId(subID);
                    post.setStageName(stageN);
                    post.setStageId(stageID);


                    lstsub.add(subN+" - "+stageN);
                    lstsubID.add(subID);

                    lststage.add(stageN);
                    lststageID.add(stageID);



                }


            }

            @Override
            public void onFailure(Call<List<subjectGetData>> call, Throwable t) {

            }
        });
        adapterspinner = new ArrayAdapter(teacherSelection.this, android.R.layout.simple_spinner_dropdown_item, lstsub);

        s2.setAdapter(adapterspinner);
        s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long idd) {

                if(lstsubID.get(position)!=null) {
                    Toast.makeText(teacherSelection.this, lstsubID.get(position), Toast.LENGTH_LONG).show();

                    loadclasses(id, cid, lstsubID.get(position), lststageID.get(position));
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }


    public void onBackPressed(View view) {
        super.onBackPressed();
    }
}
