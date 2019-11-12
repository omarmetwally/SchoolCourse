package com.example.omarmetwally.schoolcourse.teacher;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.omarmetwally.schoolcourse.R;

import java.util.ArrayList;
import java.util.List;

public class Fragment_private extends Fragment {
    View vv;
    List<String> lstspinner=new ArrayList<String>();
    ArrayAdapter adapterspinner;
    Spinner s;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        vv = inflater.inflate(R.layout.fragment_private, container, false);
        s=(Spinner)vv.findViewById(R.id.privateSpinner);

        lstspinner.add("saturday".toUpperCase());
        lstspinner.add("sunday".toUpperCase());
        lstspinner.add("monday".toUpperCase());
        lstspinner.add("tuesday".toUpperCase());
        lstspinner.add("wednesday".toUpperCase());
        lstspinner.add("thursday".toUpperCase());
        lstspinner.add("friday".toUpperCase());
        adapterspinner = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, lstspinner);
        s.setAdapter(adapterspinner);

        return vv;
    }
}
