package com.example.xiaocaiyidie;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrendsActivity extends AppCompatActivity {
    private ActionBar actionBar;
    private View addNew_view;  // 在后面添加一个空白view “没有更多内容了”
    // 存储的是动态列表
    ListView listViewTrends;
    SimpleAdapter simpleAdapterTrends;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_trends);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);  // 设置显示返回箭头
        // 修改状态栏的颜色
        setStatusBarColor(this, R.color.theme_color);


        listViewTrends =findViewById(R.id.my_function_trends_list);
        simpleAdapterTrends = new SimpleAdapter(
                getApplicationContext(),
                getTrendsData(),
                R.layout.recipe_trends_list_item,
                new String[]{"userheadimage","username", "creatTime", "pushcontent", "pushphoto"},
                new int[]{
                        R.id.recipe_trends_userheadimage,
                        R.id.recipe_trends_username,
                        R.id.recipe_trends_creat_time,
                        R.id.recipe_trends_user_pushcontent,
                        R.id.recipe_trends_user_pushphoto
                });
        listViewTrends.setAdapter(simpleAdapterTrends);
        addNew_view = getLayoutInflater().inflate(R.layout.addnew_view,null);
        //将layout的内容加入到listView当中去
        listViewTrends.addFooterView(addNew_view);
    }

    // 点击后返回到上一界面
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:   //返回键的id
                this.finish();
                return false;
            default:
                return super.onOptionsItemSelected(item);
        }
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


    // 设置动态列表数据
    private List<Map<String,Object>> getTrendsData() {
        int [] userheadimage = {R.drawable.head};
        String [] username = {"吃萝卜的小奶虎"};
        String [] creatTime = {"12分钟前"};
        String [] pushcontent = {"又是元气满满的一天！加油，打工人！"};
        int [] pushphoto = {R.drawable.image_2};
        List<Map<String,Object>> list= new ArrayList<>();
        for(int i=0;i<7;i++){
            Map  map = new HashMap();
            map.put("userheadimage", userheadimage[0]);
            map.put("username", username[0]);
            map.put("creatTime", creatTime[0]);
            map.put("pushcontent", pushcontent[0]);
            map.put("pushphoto", pushphoto[0]);
            list.add(map);
        }
        return list;
    }
}
