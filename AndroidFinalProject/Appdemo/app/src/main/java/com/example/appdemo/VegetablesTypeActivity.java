package com.example.appdemo;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;

public class VegetablesTypeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		// 获取被点击的按钮的ID
		Intent intent = getIntent();
		int id = intent.getExtras().getInt("ID");

		setContentView(R.layout.eat);

		int x = 0;
		int i = 1;

		int identifi = 0x7f050051;
		String name = ID(id);
		while (x != list(name, i, identifi)) {
			identifi++;
			i++;
		}

	}

	// 根据点击的按钮的ID判断需要加载的图片
	private String ID(int id) {
		String name = null;
		switch (id) {
		case R.id.remencaipu:
			name = "remencaipu";
			break;
		case R.id.nianyefan:
			name = "nianyefan";
			break;
		case R.id.tang:
			name = "tang";
			break;
		case R.id.zaocan:
			name = "zaocan";
			break;
		case R.id.dangao:
			name = "dangao";
			break;
		case R.id.doufu:
			name = "doufu";
			break;
		case R.id.xia:
			name = "xia";
			break;
		case R.id.paigu:
			name = "paigu";
			break;
		case R.id.ji:
			name = "ji";
			break;
		case R.id.yu:
			name = "yu";
			break;
		case R.id.niurou:
			name = "niurou";
			break;
		case R.id.yangrou:
			name = "yangrou";
			break;
		case R.id.jidan:
			name = "jidan";
			break;
		case R.id.sanyao:
			name = "sanyao";
			break;
		case R.id.ou:
			name = "ou";
			break;
		case R.id.luobo:
			name = "luobo";
			break;
		case R.id.baicai:
			name = "baicai";
			break;
		case R.id.jingzhengu:
			name = "jingzhengu";
			break;
		}
		return name;
	}

	// 生成按钮的测试代码
	private int list(final String name, final int i, int identifi) {
		int n = 0;
		Resources res = getResources();
		n = res.getIdentifier(name + "_" + i, "drawable", getPackageName());
		if (n != 0) {

			LinearLayout linear = (LinearLayout) findViewById(R.id.layout);
			Button temp = new Button(VegetablesTypeActivity.this);
			// temp.setWidth(80);
			// 根据屏幕的宽度设置按钮的高度
			temp.setHeight(screan() * 6 / 10);
			// 设置动态资源ID
			temp.setId(identifi);
			linear.addView(temp);
			temp.setBackgroundResource(n);
			temp.setOnClickListener(new OnClickListener() {

				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(VegetablesTypeActivity.this,
							doingActivity.class);
					intent.putExtra("ID", name + "_" + i);
					VegetablesTypeActivity.this.startActivity(intent);

					// finish();
				}

			});
		}

		return n;
	}

	// 获取屏幕宽度
	private int screan() {
		WindowManager wm = this.getWindowManager();
		int width = wm.getDefaultDisplay().getWidth();
		return width;
	}

	private void makeimagebutton(String str) {
		LinearLayout linear = (LinearLayout) findViewById(R.id.layout);
		ImageButton btn1 = new ImageButton(VegetablesTypeActivity.this);
		btn1.setMinimumWidth(80);
		linear.addView(btn1);
		// 得到一个resources对象
		Resources res = getResources();
		// 通过resources.getIdentifier返回一个资源ID的值
		int n = res.getIdentifier(str, "drawable", getPackageName());
		btn1.setBackgroundResource(n);

	}

	private void getAllFileName(String path, ArrayList<String> fileName)

	{
		File file = new File(path);
		if (file.list() != null)
			fileName.addAll(Arrays.asList(file.list()));
	}

	private void List() {

		ArrayList<String> listFileName = new ArrayList<String>();
		getAllFileName(
				"D:\\workspace\\androidworkspace\\project\\res\\drawable-hdpi",
				listFileName);
		for (String name : listFileName) {
			String str1 = name.substring(0, name.indexOf("."));
			makeimagebutton(str1);

		}
	}
}
