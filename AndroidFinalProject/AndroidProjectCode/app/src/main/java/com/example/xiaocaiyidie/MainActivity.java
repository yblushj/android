package com.example.xiaocaiyidie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleAdapter;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // 修改状态栏的颜色
        setStatusBarColor(this, R.color.theme_color);

        /*
        * 设置底部导航栏
        */
        // 获取页面上的底部导航栏控件
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        // 配置navigation与底部菜单之间的联系
        // 底部菜单的样式里面的item里面的ID与navigation布局里面指定的ID必须相同，否则会出现绑定失败的情况
        AppBarConfiguration configuration = new AppBarConfiguration.Builder(R.id.navigation_home, R.id.navigation_recipe, R.id.navigation_collect, R.id.navigation_my).build();
        // 建立fragment容器的控制器，这个容器就是页面的上的fragment容器
        NavController navController = Navigation.findNavController(this, R.id.fragment);
        // 启动
        NavigationUI.setupActionBarWithNavController(this, navController, configuration);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
        System.out.println("调用了main");
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



//        @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        //使用菜单填充器获取menu下的菜单资源文件
//        getMenuInflater().inflate(R.menu.actionbat_units,menu);
//        //获取搜索的菜单组件
//        MenuItem menuItem = menu.findItem(R.id.search);
//        searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
//        searchView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
//                startActivity(intent);
//            }
//        });
//        //设置搜索的事件
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                Toast t = Toast.makeText(MainActivity.this, query, Toast.LENGTH_SHORT);
//                t.setGravity(Gravity.TOP,0,0);
//                t.show();
//                return false;
//            }
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return false;
//            }
//        });
//
//        return super.onCreateOptionsMenu(menu);
//    }
}