package com.example.appdemo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class loginActivity extends Activity {
	public String name = "name";

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

	}

	// 跳转到找回密码页面
	public void login_pwd(View v) {
		Intent intent = new Intent();
		intent.setClass(loginActivity.this, findpwdActivity.class);
		loginActivity.this.startActivity(intent);

	}

	public void login_back(View v) {
		Intent intent = new Intent();
		intent.setClass(loginActivity.this, loginORregisterActivity.class);
		loginActivity.this.startActivity(intent);

	}

	public void login(View v) {

		EditText user = (EditText) findViewById(R.id.login_user_edit);
		EditText passwd = (EditText) findViewById(R.id.login_passwd_edit);
		String Name = user.getText().toString();
		String Pwd = passwd.getText().toString();
		if (!(Name.equals("") || Pwd.equals(""))) {
			SQLiteDatabase dbsqlitedatabase = null;
			// 打开数据库
			dbsqlitedatabase = SQLiteDatabase.openOrCreateDatabase(this
					.getFilesDir().toString() + "/login.db", null);

			Cursor resultCursor = dbsqlitedatabase.query("login", new String[] {
					"User_No", "User_Name", "Pwd", "Phone" }, "User_Name="
					+ "'" + Name + "'" + " and Pwd =" + "'" + Pwd + "'", null,
					null, null, null);
			if (resultCursor.moveToFirst()) {

				// if((Name.equals(resultCursor.getString(1))) &&
				// (Pwd.equals(resultCursor.getString(2))))
				// {

				@SuppressLint("WrongConstant") Toast t = Toast.makeText(loginActivity.this, "登录成功", 0);
				// 显示对话框
				t.show();

				sharePrefer(Name, Pwd);
				Intent intent = new Intent();
				intent.setClass(loginActivity.this, MainActivity.class);
				loginActivity.this.startActivity(intent);
				// }
				// else{
				//
				// Toast t = Toast.makeText(loginActivity.this, "登录失败", 0);
				// //显示对话框
				// t.show();
				// }
			}

			else {
				@SuppressLint("WrongConstant") Toast t = Toast.makeText(loginActivity.this, "登录失败,用户不存在或密码错误",
						0);
				// 显示对话框
				t.show();
			}
			resultCursor.close();
			dbsqlitedatabase.close();
		} else {
			@SuppressLint("WrongConstant") Toast t = Toast.makeText(loginActivity.this, "用户名和密码不能为空", 0);
			// 显示对话框
			t.show();

		}

	}

	public void sharePrefer(String Name, String Pwd) {
		SharedPreferences SP = getSharedPreferences("SharePreferences",
				MODE_PRIVATE);
		Editor hh = SP.edit();
		hh.putString(name, Name);
		hh.putString("pwd", Pwd);
		hh.commit();
		// Toast t = Toast.makeText(loginActivity.this, "保存成功", 0);
		// 显示对话框
		// t.show();
		// 显示对话框
	}
}
