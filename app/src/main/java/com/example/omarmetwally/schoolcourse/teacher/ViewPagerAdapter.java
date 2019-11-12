package com.example.omarmetwally.schoolcourse.teacher;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    int numtab;
    public ViewPagerAdapter( FragmentManager fm,int numtab) {
        super(fm);
        this.numtab=numtab;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Fragment_public tab1 = new Fragment_public();
                return tab1;
            case 1:
                Fragment_private tab2 = new Fragment_private();
                return tab2;


            default:
                return null;
        }
    }
    public void settab(int po)
    {
        getItem(1);
    }
    @Override
    public int getCount() {
        return numtab;
    }

   /* private  List<Fragment> fragmentList;
    private  List<String> FragmentListTitle;
    public  ViewPagerAdapter(FragmentManager fm) {
        super(fm);
        FragmentListTitle=new ArrayList<>();
        fragmentList=new ArrayList<>();
        FragmentListTitle.add("public");
        fragmentList.add(new Fragment_public());
        FragmentListTitle.add("private");
        fragmentList.add(new Fragment_private());
    }

    @Override
    public Fragment getItem(int i) {
        return fragmentList.get(i);
    }

    @Override
    public int getCount() {
        return FragmentListTitle.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return FragmentListTitle.get(position);
    }

    public void AddFragment(Fragment fragment,String Title)
    {

        fragmentList.add(fragment);

        FragmentListTitle.add(Title);
    }*/
}
