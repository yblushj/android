package com.example.xiaocaiyidie;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class RecipeDeatils extends AppCompatActivity {
    private ActionBar actionBar;
    private String intentName;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_detail);

        // 修改状态栏的颜色
        setStatusBarColor(this, R.color.theme_color);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);  // 设置显示返回箭头

        RadiuImageView radiuImageView = findViewById(R.id.recipe_details_image);
        TextView textView = findViewById(R.id.recipe_details_name);

        Intent intent = getIntent();
        intentName = intent.getStringExtra("name");
        if (intentName.equals("花生酪")) {
            radiuImageView.setImageResource(R.drawable.num3);
            textView.setText("花生酪");
        } else if (intentName.equals("巧克力曲奇")) {
            radiuImageView.setImageResource(R.drawable.num5);
            textView.setText("巧克力曲奇");
        } else if (intentName.equals("水果沙拉")) {
            radiuImageView.setImageResource(R.drawable.food6);
            textView.setText("水果沙拉");
        } else if (intentName.equals("芥香虾球")) {
            radiuImageView.setImageResource(R.drawable.num6);
            textView.setText("芥香虾球");
        } else if (intentName.equals("麻辣虾尾")) {
            radiuImageView.setImageResource(R.drawable.num7);
            textView.setText("麻辣虾尾");
        } else if (intentName.equals("家庭版佛跳墙")) {
            radiuImageView.setImageResource(R.drawable.num8);
            textView.setText("家庭版佛跳墙");
        } else if (intentName.equals("红烧鱼块")) {
            radiuImageView.setImageResource(R.drawable.num9);
            textView.setText("红烧鱼块");
        } else if (intentName.equals("青椒白玉菇炒鸡蛋")) {
            radiuImageView.setImageResource(R.drawable.num10);
            textView.setText("青椒白玉菇炒鸡蛋");
        } else if (intentName.equals("干锅菜花")) {
            radiuImageView.setImageResource(R.drawable.num14);
            textView.setText("干锅菜花");
        } else{
            System.out.println("无");
        }
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
}

