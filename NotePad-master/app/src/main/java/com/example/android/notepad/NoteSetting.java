package com.example.android.notepad;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class NoteSetting extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_setting);

        //点击跳转到qq
        TextView textView = findViewById((R.id.qq));
        textView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent();
                intent.setData(Uri.parse("https://im.qq.com/index"));
                intent.setAction(Intent.ACTION_VIEW);
                startActivity(intent);
            }
        });

        //点击跳转到微信
        TextView textView1 = findViewById(R.id.wechat);
        textView1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent();
                intent.setData(Uri.parse("https://weixin.qq.com/"));
                intent.setAction(Intent.ACTION_VIEW);
                startActivity(intent);
            }
        });


    }
}