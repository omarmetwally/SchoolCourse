package com.example.omarmetwally.schoolcourse.teacher;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.Toast;

import com.example.omarmetwally.schoolcourse.R;

public class Fragment_public_private extends Fragment {

    TabLayout t;
   // ViewPager p;
    View vv;
    //ViewPagerAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vv = inflater.inflate(R.layout.fragment_public_private, container, false);
        t=(TabLayout)vv.findViewById(R.id.tabb);
        ViewPager  p=(ViewPager)vv.findViewById(R.id.viewview);

       // ViewPagerAdapter adapter=new ViewPagerAdapter(getFragmentManager());

        //adapter.AddFragment(new Fragment_public(),"Public");
      //  adapter.AddFragment(new Fragment_private(),"Private");

       // p.setAdapter(adapter);
        //t.setupWithViewPager(p);


        t.addTab(t.newTab().setText("Public"));
        t.addTab(t.newTab().setText("Private"));


        t.setTabGravity(TabLayout.GRAVITY_FILL);


        final ViewPager viewPager = (ViewPager)vv.findViewById(R.id.viewview);

        final ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager(), t.getTabCount());

        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(t));
        t.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });




        return vv;

    }

}
