package com.example.appdemo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class repwdActivity extends Activity {
	private EditText accountEt;
	private EditText pwdEt;
	private EditText pwdEt1;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.repwd);
		initView();
	}

	// 为按钮设置资源ID对象
	private void initView() {
		accountEt = (EditText) findViewById(R.id.accountEt);
		pwdEt = (EditText) findViewById(R.id.pwdEt);
		pwdEt1 = (EditText) findViewById(R.id.pwdEt1);
	}

	// 确认修改密码
	public void subBtn(View v) {

		String oldpwd = accountEt.getText().toString();
		String newpwd = pwdEt.getText().toString();
		String newpwd1 = pwdEt1.getText().toString();
		System.out.println(newpwd + "hhh" + newpwd1);

		if (newpwd.equals(newpwd1)) { // 判断两次输入密码是否一致
			String value = sharePrefer();

			if (value != "[未登录]" && value != " ")// 判断用户是否登录
			{
				String pwd = sharePrefer1();
				if (pwd.equals(oldpwd)) {
					SQLiteDatabase dbsqlitedatabase = null;
					// 打开数据库
					dbsqlitedatabase = SQLiteDatabase.openOrCreateDatabase(this
							.getFilesDir().toString() + "/login.db", null);
					// 修改密码
					ContentValues updatepwd = new ContentValues();
					updatepwd.put("Pwd", newpwd);
					String where = "User_Name=?";
					String[] where1 = { value };
					dbsqlitedatabase.update("login", updatepwd, where, where1);
					// 关闭数据库连接
					dbsqlitedatabase.close();
					@SuppressLint("WrongConstant") Toast t = Toast.makeText(repwdActivity.this, "修改成功", 0);
					// 显示对话框
					t.show();
					// 注销
					hh();
				}

				else {
					Toast t = Toast.makeText(repwdActivity.this, "对不起，旧密码输入错误",
							0);
					// 显示对话框
					t.show();
				}
			} else {

				@SuppressLint("WrongConstant") Toast t = Toast.makeText(repwdActivity.this, "对不起，你还未登录", 0);
				// 显示对话框
				t.show();

			}

		} else {
			@SuppressLint("WrongConstant") Toast t = Toast.makeText(repwdActivity.this, "两次新密码的输入不一致", 0);
			// 显示对话框
			t.show();

		}

	}

	public String sharePrefer() {
		SharedPreferences SP = getSharedPreferences("SharePreferences", 0);
		String name = SP.getString("name", "[未登录]");
		System.out.println(name);
		return name;
	}

	public String sharePrefer1() {
		SharedPreferences SP = getSharedPreferences("SharePreferences", 0);
		String pwd = SP.getString("pwd", "[未登录]");
		System.out.println(pwd);
		return pwd;
	}

	// 注销
	public void hh() {
		SharedPreferences SP = getSharedPreferences("SharePreferences", 0);
		Editor hh = SP.edit();
		// 修改该建对应的值
		hh.putString("name", "[未登录]");
		String name = SP.getString("name", "[未登录]");
		System.out.println(name + "name删除失败hh");
		hh.commit();
		Intent intent = new Intent(repwdActivity.this, loginActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
				| android.content.Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
	}

}
