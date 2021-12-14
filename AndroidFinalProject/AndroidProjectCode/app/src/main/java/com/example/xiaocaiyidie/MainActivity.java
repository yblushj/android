package com.example.xiaocaiyidie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager.widget.ViewPager;
import android.app.Activity;
import android.graphics.Matrix;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;
import fragments.CollectFragment;
import fragments.HomeFragment;
import fragments.MyFragment;
import fragments.RecipeFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ViewPager.OnPageChangeListener{

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

    private HomeFragment homeFragment;
    private RecipeFragment recipeFragment;
    private CollectFragment collectFragment;
    private MyFragment myFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.naviga_bar);

        // 修改状态栏的颜色
        setStatusBarColor(this, R.color.theme_color);

        // 设置底部导航栏
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        NavController navController = Navigation.findNavController(this, R.id.fragment);
        AppBarConfiguration configuration = new AppBarConfiguration.Builder(R.id.navigation_home, R.id.navigation_recipe, R.id.navigation_collect, R.id.navigation_my).build();
        NavigationUI.setupActionBarWithNavController(this, navController, configuration);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        System.out.println("显示了一次");

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
        title_one.setTextColor(this.getResources().getColor(R.color.theme_color));   // 设置颜色
        title_one.setOnClickListener(this);
        title_two.setOnClickListener(this);
        title_three.setOnClickListener(this);
        viewpager.addOnPageChangeListener(this);
    }

    /**
       设置TabHost选中文字背景色的切换
     */
    @Override
    public void onClick(View v) {
        title_one = (TextView) findViewById(R.id.title_one);
        title_two = (TextView) findViewById(R.id.title_two);
        title_three = (TextView) findViewById(R.id.title_three);

        System.out.println("你惦记了");
        switch (v.getId()) {
            case R.id.title_one:
                title_one.setTextColor(this.getResources().getColor(R.color.theme_color));
                title_two.setTextColor(this.getResources().getColor(R.color.gray));
                title_three.setTextColor(this.getResources().getColor(R.color.gray));
                viewpager.setCurrentItem(0);
                System.out.println("你惦记了one");
                break;
            case R.id.title_two:
                title_one.setTextColor(this.getResources().getColor(R.color.gray));
                title_two.setTextColor(this.getResources().getColor(R.color.theme_color));
                title_three.setTextColor(this.getResources().getColor(R.color.gray));

                viewpager.setCurrentItem(1);
                System.out.println("你惦记了two");
                break;
            case R.id.title_three:
                title_one.setTextColor(this.getResources().getColor(R.color.gray));
                title_two.setTextColor(this.getResources().getColor(R.color.gray));
                title_three.setTextColor(this.getResources().getColor(R.color.theme_color));
                viewpager.setCurrentItem(2);
                System.out.println("你惦记了three");
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



    /**
     * 修改状态栏颜色，支持4.4以上版本
     * @param activity
     * @param colorId
     */
    public static void setStatusBarColor(Activity activity, int colorId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(activity.getResources().getColor(colorId));
        }
    }
}