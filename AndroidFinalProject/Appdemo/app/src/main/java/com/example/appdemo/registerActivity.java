package com.example.appdemo;

import java.io.File;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class registerActivity extends Activity {

	private SQLiteDatabase m_dbSQLiteDatabase = null;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);

	}

	private SQLiteDatabase OpenOrCreateDB() {

		SQLiteDatabase dbSQLiteDatabase = null;
		String path = this.getFilesDir().toString() + "/login.db";
		File file = new File(path);

		if (file.exists()) {

			dbSQLiteDatabase = SQLiteDatabase.openOrCreateDatabase(
					"/data/data/com.delicacy/files/login.db", null);
			System.out.println(this.getFilesDir().getPath().toString()
					+ "/login.db");

			/*
			 * String sqlCreateTable ="CREATE TABLE login " +
			 * "(User_No INTEGER PRIMARY KEY,User_Name TEXT NOT NULL," +
			 * "Pwd TEXT NOT NULL, Phone text ,Sex text,Occupation text)";
			 * dbSQLiteDatabase.execSQL(sqlCreateTable);
			 */
		} else {
			dbSQLiteDatabase = SQLiteDatabase.openOrCreateDatabase(this
					.getFilesDir().toString() + "/login.db", null);
			String sqlCreateTable = "CREATE TABLE login "
					+ "(User_No INTEGER PRIMARY KEY,User_Name TEXT NOT NULL,"
					+ "Pwd TEXT NOT NULL, Phone TEXT ,Sex TEXT,Occupation TEXT)";
			dbSQLiteDatabase.execSQL(sqlCreateTable);
		}

		return dbSQLiteDatabase;
	}

	public void login_back(View v) {

		Intent intent = new Intent();
		intent.setClass(registerActivity.this, loginORregisterActivity.class);
		registerActivity.this.startActivity(intent);

	}

	public void register(View v) {

		EditText user = (EditText) findViewById(R.id.user);
		EditText passwd = (EditText) findViewById(R.id.passwd);
		EditText phone = (EditText) findViewById(R.id.dianhua);
		String Phone = "null";
		String Name = user.getText().toString();
		String Pwd = passwd.getText().toString();
		if (!(Name.equals("") || Pwd.equals(""))) {
			m_dbSQLiteDatabase = OpenOrCreateDB();

			System.out.println(m_dbSQLiteDatabase + "1");

			if (m_dbSQLiteDatabase != null) {

				System.out.println(m_dbSQLiteDatabase + "2");
				System.out.println(Name + Pwd);
				Phone = phone.getText().toString();
				// 打开数据库
				// 插入数据

				/*
				 * cvContentValues.put("User_Name", Name);
				 * cvContentValues.put("Pwd",Pwd );
				 * cvContentValues.put("Phone",Phone);
				 * dbsqlitedatabase.insert("login", null, cvContentValues);
				 * dbsqlitedatabase.close();
				 */
				String sqlInsertData = "INSERT INTO login "
						+ "(User_No, User_Name, Pwd ,Phone) values (NULL,"
						+ "'" + Name + "'" + "," + "'" + Pwd + "'" + "," + "'"
						+ Phone + "'" + ")";
				m_dbSQLiteDatabase.execSQL(sqlInsertData);
				m_dbSQLiteDatabase.close();
				@SuppressLint("WrongConstant") Toast t = Toast.makeText(this, "注册成功", 0);

				// 显示对话框
				t.show();
				Intent intent = new Intent();
				intent.setClass(registerActivity.this, loginActivity.class);
				registerActivity.this.startActivity(intent);
			} else {
				@SuppressLint("WrongConstant") Toast t = Toast.makeText(this, "注册失败", 0);

				// 显示对话框
				t.show();

			}

		} else {
			@SuppressLint("WrongConstant") Toast t = Toast.makeText(this, "用户名和密码不能为空", 0);

			// 显示对话框
			t.show();

		}

	}

}
