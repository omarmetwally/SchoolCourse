package com.example.omarmetwally.schoolcourse.teacher;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.omarmetwally.schoolcourse.R;

public class teacherTimetable extends Fragment {

    TextView s;
    View vv;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        vv = inflater.inflate(R.layout.fragment_mytimetable, container, false);



        return vv;
    }

}
