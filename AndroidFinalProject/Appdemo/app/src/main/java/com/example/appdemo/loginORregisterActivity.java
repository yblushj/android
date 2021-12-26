package com.example.appdemo;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.appdemo.R;

public class loginORregisterActivity extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		OpenOrCreateDB();
	}

	private void OpenOrCreateDB() {

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
		dbSQLiteDatabase.close();
	}

	public void login_register(View v) {
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.login:
			intent.setClass(loginORregisterActivity.this, loginActivity.class);
			loginORregisterActivity.this.startActivity(intent);
			break;
		case R.id.passenger:
			intent.setClass(loginORregisterActivity.this, MainActivity.class);
			loginORregisterActivity.this.startActivity(intent);
			break;
		default:
			intent.setClass(loginORregisterActivity.this,
					registerActivity.class);
			loginORregisterActivity.this.startActivity(intent);

			break;
		}

	}

};
