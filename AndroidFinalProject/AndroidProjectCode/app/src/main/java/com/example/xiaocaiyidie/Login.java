package com.example.xiaocaiyidie;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;

public class Login extends Activity {
    private String userpassword;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_login);

        // 修改状态栏的颜色
        setStatusBarColor(this, R.color.white);

        EditText editTextUserName = findViewById(R.id.username_edit);
        EditText editTextPassword = findViewById(R.id.userpassword_edit);
        Button button = findViewById(R.id.ligin_button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if ("123".equals(editTextUserName.getText().toString()) && "123".equals(editTextPassword.getText().toString())) {
                    Toast.makeText(Login.this, "登录成功", Toast.LENGTH_SHORT).show();
                    // 延迟的方法
                    TimerTask startMain = new TimerTask() {
                        @Override
                        public void run() {
                            //等待两秒后，跳转到MainActivity
                            startActivity(new Intent(Login.this, MainActivity.class));
                            Login.this.finish(); // 将该Activity销毁掉，要不然返回的时候就会跳转到欢迎页面
                        }
                    };
                    Timer timer = new Timer(); // 新建实例演示操作
                    timer.schedule(startMain, 500);// 这里时间设置为0，方便自己调试，记得之后改成2000
//                    Intent intent = new Intent(Login.this, MainActivity.class);
//                    startActivity(intent);

                } else if ("".equals(editTextUserName.getText().toString())) {
                    Toast.makeText(Login.this, "用户名不能为空！", Toast.LENGTH_SHORT).show();
                } else if ("".equals(editTextPassword.getText().toString())) {
                    Toast.makeText(Login.this, "密码不能为空！", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Login.this, "输入错误，请重试", Toast.LENGTH_SHORT).show();
                }
            }
        });
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
