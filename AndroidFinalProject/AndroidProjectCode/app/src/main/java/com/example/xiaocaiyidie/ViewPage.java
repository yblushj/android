package com.example.xiaocaiyidie;

import android.app.AppComponentFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.RequiresApi;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;


public class ViewPage extends AppCompatActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {
    private static final String TAG = "你惦记了我";
    private ViewPager viewpager;
    private View slider;
    private TextView title_one;
    private TextView title_two;
    private TextView title_three;

    private ArrayList<View> listViews;
    private int offset = 0;//移动条图片的偏移量
    private int currIndex = 0;//当前页面的编号
    private int bmpWidth;// 移动条图片的长度
    private int one = 0; //移动条滑动一页的距离
    private int two = 0; //滑动条移动两页的距离

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_fragment);

        viewpager = (ViewPager) findViewById(R.id.viewpager);
        title_one = (TextView) findViewById(R.id.title_one);
        title_two = (TextView) findViewById(R.id.title_two);
        title_three = (TextView) findViewById(R.id.title_three);
        slider = (View) findViewById(R.id.slider);

        //下划线动画的相关设置 -- start

        // 屏幕相关
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int screenW = dm.widthPixels;// 获取分辨率宽度

        bmpWidth = screenW / 3;  // 获取滑块应该的宽度

        // 设置滑块宽度
        slider.getLayoutParams().width = bmpWidth;

        // 计算初始滑块偏移量
        offset = (screenW / 3 - bmpWidth) / 2;// 计算偏移量

        Matrix matrix = new Matrix();
        matrix.postTranslate(offset, 0);

        //移动的距离
        one = offset * 2 + bmpWidth;// 移动一页的偏移量,比如1->2,或者2->3
        two = one * 2;// 移动两页的偏移量,比如1直接跳3

        // 往 ViewPager 填充 View，同时设置点击事件与页面切换事件
        listViews = new ArrayList<View>();
        LayoutInflater mInflater = getLayoutInflater();
        listViews.add(mInflater.inflate(R.layout.view_one, null, false));
        listViews.add(mInflater.inflate(R.layout.view_two, null, false));
        listViews.add(mInflater.inflate(R.layout.view_three, null, false));

        viewpager.setAdapter(new MsPagerAdapter(listViews));
        viewpager.setCurrentItem(0);          //设置ViewPager当前页，从0开始算

        title_one.setOnClickListener(this);
        title_two.setOnClickListener(this);
        title_three.setOnClickListener(this);

        viewpager.addOnPageChangeListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.title_one:
                viewpager.setCurrentItem(0);
                break;
            case R.id.title_two:
                viewpager.setCurrentItem(1);
                break;
            case R.id.title_three:
                viewpager.setCurrentItem(2);
                break;
        }
    }

    @Override
    public void onPageSelected(int index) {

        int fromX = currIndex * bmpWidth;

        int toX = index * bmpWidth;

        Animation animation =  new TranslateAnimation(fromX, toX, 0, 0);

        currIndex = index;
        animation.setFillAfter(true);// true表示图片停在动画结束位置
        animation.setDuration(300); //设置动画时间为300毫秒
        slider.startAnimation(animation);//开始动画
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }
}
