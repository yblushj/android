package com.example.appdemo;


import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class doingActivity extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		// 获取传过来的图片的资源名字例如:remencaipu_1
		String frontname = intent.getExtras().getString("ID");

		setContentView(R.layout.doing);

		makename(frontname);

	}

	/*
	 * private String findname(String frontname) { // TODO Auto-generated method
	 * stub
	 * 
	 * String name = null; switch (frontname) { case "zhaocan": name ="zhaocan";
	 * break; case R.id.dianxin: name ="dianxin"; break; } return name; }
	 */

	private void makename(String frontname) {
		int n = 0;
		int m = 0;
		Resources res = getResources();
		n = res.getIdentifier(frontname, "drawable",
				getPackageName());
		m =getResources().getIdentifier(frontname + "_" + "do", "string", getPackageName());
		System.out.println(m);
		System.out.println(n);
		if (n != 0) {
			LinearLayout linear = (LinearLayout) findViewById(R.id.doing);
			TextView temp = new TextView(doingActivity.this);
			// temp.setWidth(80);
			// 根据屏幕的宽度设置按钮的高度
			//TextView temp1 = new TextView(doingActivity.this);
			//TextView tupian=(TextView)findViewById(R.id.tupian);
			//TextView wenzhi=(TextView)findViewById(R.id.wenzhi);
			linear.addView(temp);
//			linear.addView(tupian);
			temp.setBackgroundResource(n);
//			String doing=this.getString(m);
//			wenzhi.setText(doing);
			if(m != 0){
			TextView temp1 = new TextView(doingActivity.this);
			linear.addView(temp1);
			String doing=this.getString(m);
			temp1.setText(doing);
			System.out.println("设置了");}
			
		}else{
			
		}

	}

}