package com.example.appdemo;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MaindoingActivity extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Intent intent = getIntent();
		int id = intent.getExtras().getInt("ID");
		setContentView(R.layout.maindoing);
		String name = ID(id);
		makename(name);
	}

	// 根据点击的按钮的ID判断需要加载的图片
	private String ID(int id) {
		String name = null;
		switch (id) {
		case R.id.shouye_1:
			name = "shouye_1_do";
			break;
		case R.id.shouye_2:
			name = "shouye_2_do";
			break;
		case R.id.shouye_3:
			name = "shouye_3_do";
			break;
		case R.id.shouye_4:
			name = "shouye_4_do";
			break;
		case R.id.shouye_5:
			name = "shouye_5_do";
			break;
		case R.id.shouye_6:
			name = "shouye_6_do";
			break;
		case R.id.shouye_7:
			name = "shouye_7_do";
			break;
		case R.id.shouye_8:
			name = "shouye_8_do";
			break;
		case R.id.shouye_9:
			name = "shouye_9_do";
			break;
		case R.id.shouye_10:
			name = "shouye_10_do";
			break;
		}
		return name;
	}

	// 生成按钮的测试代码
	private void makename(String frontname) {
		int n = 0;
		Resources res = getResources();
		n = res.getIdentifier(frontname, "drawable", getPackageName());
		if (n != 0) {

			LinearLayout linear = (LinearLayout) findViewById(R.id.maindoing);
			TextView temp = new TextView(MaindoingActivity.this);
			// temp.setWidth(80);
			// 根据屏幕的宽度设置按钮的高度

			linear.addView(temp);
			temp.setBackgroundResource(n);
		}

	}
}
