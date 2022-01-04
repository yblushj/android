package com.example.xiaocaiyidie;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
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

public class AttentionActivity extends AppCompatActivity {
    private ActionBar actionBar;
    ListView listView;
    SimpleAdapter simpleAdapter;
    private View addNew_view;  // 在后面添加一个空白view “没有更多内容了”
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_attention);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);  // 设置显示返回箭头
        // 修改状态栏的颜色
        setStatusBarColor(this, R.color.theme_color);


        // 显示关注列表 填充listviwe
        listView =findViewById(R.id.my_function_attention_list);
        simpleAdapter = new SimpleAdapter(
                getApplicationContext(),
                getData(),
                R.layout.my_function_item,
                new String[]{"headimage","username", "usermiaoshu"},
                new int[]{
                        R.id.my_function_headimage,
                        R.id.my_function_username,
                        R.id.my_function_usermiaoshu,
                });
        listView.setAdapter(simpleAdapter);
        addNew_view = getLayoutInflater().inflate(R.layout.addnew_view,null);
        //将layout的内容加入到listView当中去
        listView.addFooterView(addNew_view);
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

    // 设置关注列表数据
    private List<Map<String,Object>> getData() {
        String [] username={"爱喝水的小脑虎"};
        String [] usermiaoshu={"可咸可甜的肉松饼最好吃了！"};
        int [] headimage={R.drawable.head};
        List<Map<String,Object>> list= new ArrayList<>();
        for(int i=0;i<23;i++){
            Map  map = new HashMap();
            map.put("headimage",headimage[0]);
            map.put("username",username[0]);
            map.put("usermiaoshu",usermiaoshu[0]);
            list.add(map);
        }
        return list;
    }
}
