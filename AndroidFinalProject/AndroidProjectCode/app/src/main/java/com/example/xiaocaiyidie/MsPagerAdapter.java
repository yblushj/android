package com.example.xiaocaiyidie;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;

import java.util.ArrayList;

public class MsPagerAdapter extends PagerAdapter {

//    private ArrayList<View> mData;
//    private ArrayList<String> mTitle;
//
//    public MsPagerAdapter() {
//    }
//
//    public MsPagerAdapter(ArrayList<View> view_list, ArrayList<String> title) {
//        super();
//        this.mData = view_list;
//        this.mTitle = title;
//    }
//
//    @Override
//    public int getCount() {
//        return mData.size();
//    }
//
//    @Override
//    public boolean isViewFromObject(View view, Object object) {
//        return view == object;
//    }
//
//    @Override
//    public Object instantiateItem(ViewGroup container, int position) {
//        container.addView(mData.get(position));
//        return mData.get(position);
//    }
//
//    @Override
//    public void destroyItem(ViewGroup container, int position, Object object) {
//        container.removeView(mData.get(position));
//    }
//
//    @Nullable
//    @Override
//    public CharSequence getPageTitle(int position) {
//        return mTitle.get(position);
//    }


    private ArrayList<View> mData;

    public MsPagerAdapter() {
    }

    public MsPagerAdapter(ArrayList<View> view_list) {
        super();
        this.mData = view_list;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mData.get(position));
        return mData.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mData.get(position));
    }
}