package com.example.xiaocaiyidie;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.annotation.Nullable;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_layout);

        // 这行代码可以将状态栏隐藏起来
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Timer timer = new Timer(); // 新建实例演示操作
        timer.schedule(startMain, 0);// 这里时间设置为0，方便自己调试，记得之后改成2000
    }


    // 延迟的方法
    TimerTask startMain = new TimerTask() {
        @Override
        public void run() {
            //等待两秒后，跳转到MainActivity
            startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
            WelcomeActivity.this.finish(); // 将该Activity销毁掉，要不然返回的时候就会跳转到欢迎页面
        }
    };
}
