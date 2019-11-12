package com.example.omarmetwally.schoolcourse.student;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

public class TeacherSearch extends AppCompatActivity {

    Api a;
    static int filterr=1;
    ListView gv;
    dataAdapter data;
    EditText search;
    Button searh;
    String id,fname, lname ;
    final ArrayList<GetTeacher> typeData = new ArrayList<GetTeacher>();
    final ArrayList<GetTeacher> typeData2 = new ArrayList<>();
    ArrayList<String> subject,center,stage;
    List<GetTeacher> ss=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tech_search);


        search=(EditText)findViewById(R.id.searchBar);

        search.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                final int DRAWABLE_RIGHT = 2;
                if(event.getAction() == MotionEvent.ACTION_UP) {
                    if(event.getRawX() >= (search.getRight() - search.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // your action here
                        LayoutInflater lay=LayoutInflater.from(TeacherSearch.this);
                        final View vshow= lay.inflate(R.layout.filter_layout,null);
                        RadioGroup radioGroup = (RadioGroup)vshow.findViewById(R.id.radiogroup);

                        RadioButton to_check;


                        switch (filterr){


                            case 1:
                                to_check=(RadioButton)vshow.findViewById(R.id.teacherradioButton);
                                to_check.setChecked(true);
                                break;
                            case 2:
                                to_check=(RadioButton)vshow.findViewById(R.id.subjectradioButton);
                                to_check.setChecked(true);
                                break;

                        }












                        AlertDialog.Builder msg=new AlertDialog.Builder(TeacherSearch.this);
                        msg.setView(vshow);


                        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
                        {
                            @Override
                            public void onCheckedChanged(RadioGroup group, int checkedId) {

                                filterr=checkedId;
                                RadioButton rr=vshow.findViewById(checkedId);

                                if(filterr==checkedId)
                                    rr.setChecked(true);
                                // Toast.makeText(getActivity(),""+rr.getText(),Toast.LENGTH_SHORT).show();
                                if(rr.getText().equals("Teacher name")) {
                                    filter_By_name();
                                    rr.setChecked(true);

                                }

                                else if (rr.getText().equals("Subject"))
                                    filter_By_sub();
                            }
                        });


                        msg.create();
                        msg.show();
                        return true;
                    }
                }

                return false;
            }
        });


        final DialogFragment loadingScreen=LoadingScreen.getInstance();
        loadingScreen.show(getSupportFragmentManager(),"loading Screen");


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.42.0.233:45455/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        a = retrofit.create(Api.class);
        final Call<List<GetTeacher>>call=a.getTeacher();
        call.enqueue(new Callback<List<GetTeacher>>() {
            @Override
            public void onResponse(Call<List<GetTeacher>> call, Response<List<GetTeacher>> response) {


                ss=response.body();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {


                        loadingScreen.dismiss();
                        filter_By_name();
                        mainSearch();

                    }
                },2000);

                //loading();




                // filter_By_sub(response.body());

            }

            @Override
            public void onFailure(Call<List<GetTeacher>> call, Throwable t) {

               Toast.makeText(TeacherSearch.this,"Check your internet",Toast.LENGTH_LONG).show();
                loadingScreen.dismiss();
            }
        });


    }


    public void loading(){
       final DialogFragment loadingScreen=LoadingScreen.getInstance();
       loadingScreen.show(getSupportFragmentManager(),"loading Screen");
       new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {
               loadingScreen.dismiss();
           }
       },3000);



    }
    public void filter_By_Center()
    {



        //filterr=1;
        //lw 3awz suggestion list mn el DB w msh metkrara
      /*  String[] con2=new String[ss.size()];

        for(int i=0;i<ss.size();i++){


            con2[i]=ss.get(i).getCity();

        }
        LinkedHashSet<String> lhSetColors =
                new LinkedHashSet<String>(Arrays.asList(con2));
        String[] newArray = lhSetColors.toArray(new String[ lhSetColors.size() ]);*/


        // final AutoCompleteTextView editText = vv.findViewById(R.id.searchBar);
       /* final AutoCompleteTextView editText = vv.findViewById(R.id.searchBar);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, newArray);
        editText.setAdapter(adapter);

*/






        search=(EditText)findViewById(R.id.searchBar);

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                List<GetTeacher> posts = ss;

                typeData2.clear();

                for (GetTeacher post : posts) {

                    GetTeacher t = new GetTeacher();

                    fname = post.gettFirstName();
                    lname = post.gettLastName();
                    stage = post.getStageName();
                    center = post.getCity();
                    subject = post.getSubject();
                    id=post.getTid();


                    if ((center.toString().contains(search.getText().toString())

                            ||center.toString().toLowerCase().contains(search.getText().toString().toLowerCase()))
                            ) {
                        t.settFirstName(fname + " " + lname);
                        t.setCity(center);
                        t.setStageName(stage);
                        t.setSubject(subject);
                        t.setTid(id);

                        //   Toast.makeText(TeacherSearch.this,"Great JOB",Toast.LENGTH_LONG).show();


                        typeData2.add(t);


                        // Toast.makeText(TeacherSearch.this,t.gettLastName(),Toast.LENGTH_LONG).show();

                    } else if (search.getText().toString().isEmpty()) {

                        typeData2.clear();

                        data = new dataAdapter(TeacherSearch.this,typeData);

                        gv.setAdapter(data);
                        return;


                    }


                }

                data = new dataAdapter(TeacherSearch.this, typeData2);
                gv.setAdapter(data);

                gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                        Toast.makeText(TeacherSearch.this,""+typeData2.get(position).gettFirstName(),Toast.LENGTH_LONG).show();

                        Intent i = new Intent(TeacherSearch.this, teacherSelection.class);

                        i.putExtra("id", typeData2.get(position).getTid());
                        i.putExtra("name", typeData2.get(position).gettFirstName());
                        i.putExtra("subject", typeData2.get(position).getSubject());

                        startActivity(i);
                    }
                });
            }
        });
    }



    public void filter_By_name(){

        filterr=1;



        search=(EditText)findViewById(R.id.searchBar);
        search.setHint("Teacher Name");
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                List<GetTeacher> posts = ss;

                typeData2.clear();

                for (GetTeacher post : posts) {

                    GetTeacher t = new GetTeacher();

                    fname = post.gettFirstName();
                    lname = post.gettLastName();
                    stage = post.getStageName();
                    center = post.getCity();
                    subject = post.getSubject();
                    id=post.getTid();

                    if (fname.toLowerCase().contains((search.getText().toString().toLowerCase()))
                            ||fname.contains((search.getText().toString()))
                            ||lname.toLowerCase().contains((search.getText().toString().toLowerCase()))
                            ||lname.contains((search.getText().toString()))
                            ) {
                        t.settFirstName(fname + " " + lname);
                        t.setCity(center);
                        t.setStageName(stage);
                        t.setSubject(subject);
                        t.setTid(id);


                          // Toast.makeText(TeacherSearch.this,subject.get(1),Toast.LENGTH_LONG).show();


                        typeData2.add(t);


                        // Toast.makeText(TeacherSearch.this,t.gettLastName(),Toast.LENGTH_LONG).show();

                    } else if (search.getText().toString().isEmpty()) {

                        typeData2.clear();

                        data = new dataAdapter(TeacherSearch.this,typeData);

                        gv.setAdapter(data);
                        return;


                    }


                }

                data = new dataAdapter(TeacherSearch.this, typeData2);
                gv.setAdapter(data);

                gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                        Toast.makeText(TeacherSearch.this,""+typeData2.get(position).gettFirstName(),Toast.LENGTH_LONG).show();

                        Intent i = new Intent(TeacherSearch.this, teacherSelection.class);

                        i.putExtra("id", typeData2.get(position).getTid());
                        i.putExtra("name", typeData2.get(position).gettFirstName());
                        i.putExtra("subject", typeData2.get(position).getSubject());
                        startActivity(i);

                    }
                });
            }
        });
    }


    public void filter_By_sub(){

        filterr=2;


        search=(EditText)findViewById(R.id.searchBar);
        search.setHint("By Subject");
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                List<GetTeacher> posts = ss;

                typeData2.clear();

                for (GetTeacher post : posts) {

                    GetTeacher t = new GetTeacher();

                    fname = post.gettFirstName();
                    lname = post.gettLastName();
                    stage = post.getStageName();
                    center = post.getCity();
                    subject = post.getSubject();
                    id=post.getTid();


                    if (subject.toString().contains(search.getText().toString())

                            ||subject.toString().toLowerCase().contains(search.getText().toString().toLowerCase())) {
                        t.settFirstName(fname + " " + lname);
                        t.setCity(center);
                        t.setStageName(stage);
                        t.setSubject(subject);
                        t.setTid(id);

                        //   Toast.makeText(TeacherSearch.this,"Great JOB",Toast.LENGTH_LONG).show();


                        typeData2.add(t);


                        // Toast.makeText(TeacherSearch.this,t.gettLastName(),Toast.LENGTH_LONG).show();

                    } else if (search.getText().toString().isEmpty()) {

                        typeData2.clear();

                        data = new dataAdapter(TeacherSearch.this,typeData);

                        gv.setAdapter(data);
                        return;


                    }


                }

                data = new dataAdapter(TeacherSearch.this, typeData2);
                gv.setAdapter(data);

                gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                      //  Toast.makeText(TeacherSearch.this,""+typeData2.get(position).gettFirstName(),Toast.LENGTH_LONG).show();

                        Intent i = new Intent(TeacherSearch.this, teacherSelection.class);

                        i.putExtra("id", typeData2.get(position).getTid());
                        i.putExtra("name", typeData2.get(position).gettFirstName());
                        i.putExtra("subject", typeData2.get(position).getSubject());
                        startActivity(i);
                    }
                });
            }
        });
    }


    public void mainSearch() {


        List<GetTeacher> posts = ss;



        for (GetTeacher post : posts) {


            GetTeacher t = new GetTeacher();

            fname = post.gettFirstName();
            lname = post.gettLastName();
            stage = post.getStageName();
            center = post.getCity();
            subject = post.getSubject();
            id = post.getTid();




            t.settFirstName(fname + " " + lname);
            t.setCity(center);
            t.setStageName(stage);

            t.setSubject(subject);
            t.setTid(id);

            //Toast.makeText(TeacherSearch.this,subject.get(j),Toast.LENGTH_LONG).show();

           // j++;
            typeData.add(t);


        }
        data = new dataAdapter(TeacherSearch.this, typeData);


        gv = (ListView) findViewById(R.id.liiist);


        gv.setAdapter(data);

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //  Toast.makeText(getActivity(), "" + typeData.get(position).gettFirstName(), Toast.LENGTH_LONG).show();

                Intent i = new Intent(TeacherSearch.this, teacherSelection.class);

                i.putExtra("id", typeData.get(position).getTid());
                i.putExtra("name", typeData.get(position).gettFirstName());
               // i.putExtra("subject", typeData.get(position).getSubject());

                startActivity(i);


            }
        });

    }

}




