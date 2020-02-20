package com.example.omarmetwally.schoolcourse.student;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.omarmetwally.schoolcourse.Api;
import com.example.omarmetwally.schoolcourse.R;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class HomeFragment extends Fragment {

    Api a;
    static int filterr=1;
    ListView gv;

    dataAdapter data;
    EditText search;
    Button searh,Scity;
    String id,fname, lname ;
    final ArrayList<GetTeacher> typeData = new ArrayList<GetTeacher>();
    final ArrayList<GetTeacher> typeData2 = new ArrayList<>();
 ArrayList<String> subject,center,stage;
     List<GetTeacher> ss=new ArrayList<>();

    View vv;

   //  static  int filter=1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        vv = inflater.inflate(R.layout.fragment_home, container, false);





        searh=(Button)vv.findViewById(R.id.search_techeeer);
        searh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),TeacherSearch.class));
            }
        });








        Scity=(Button)vv.findViewById(R.id.citybtn);
        Scity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),Search_City.class));
            }
        });
        // final View vshow= inflater.inflate(R.layout.filter_layout,null);

        //final RadioGroup radioGroup = (RadioGroup)vshow.findViewById(R.id.radiogroup);


      /*  Rall=vshow.findViewById(R.id.radiogroup);
        int index=Rall.getCheckedRadioButtonId();
        Rselected=vshow.findViewById(index);*/

      return vv;
    }


}