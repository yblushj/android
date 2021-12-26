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

	// Ϊ��ť������ԴID����
	private void initView() {
		accountEt = (EditText) findViewById(R.id.accountEt);
		pwdEt = (EditText) findViewById(R.id.pwdEt);
		pwdEt1 = (EditText) findViewById(R.id.pwdEt1);
	}

	// ȷ���޸�����
	public void subBtn(View v) {

		String oldpwd = accountEt.getText().toString();
		String newpwd = pwdEt.getText().toString();
		String newpwd1 = pwdEt1.getText().toString();
		System.out.println(newpwd + "hhh" + newpwd1);

		if (newpwd.equals(newpwd1)) { // �ж��������������Ƿ�һ��
			String value = sharePrefer();

			if (value != "[δ��¼]" && value != " ")// �ж��û��Ƿ��¼
			{
				String pwd = sharePrefer1();
				if (pwd.equals(oldpwd)) {
					SQLiteDatabase dbsqlitedatabase = null;
					// �����ݿ�
					dbsqlitedatabase = SQLiteDatabase.openOrCreateDatabase(this
							.getFilesDir().toString() + "/login.db", null);
					// �޸�����
					ContentValues updatepwd = new ContentValues();
					updatepwd.put("Pwd", newpwd);
					String where = "User_Name=?";
					String[] where1 = { value };
					dbsqlitedatabase.update("login", updatepwd, where, where1);
					// �ر����ݿ�����
					dbsqlitedatabase.close();
					@SuppressLint("WrongConstant") Toast t = Toast.makeText(repwdActivity.this, "�޸ĳɹ�", 0);
					// ��ʾ�Ի���
					t.show();
					// ע��
					hh();
				}

				else {
					Toast t = Toast.makeText(repwdActivity.this, "�Բ��𣬾������������",
							0);
					// ��ʾ�Ի���
					t.show();
				}
			} else {

				@SuppressLint("WrongConstant") Toast t = Toast.makeText(repwdActivity.this, "�Բ����㻹δ��¼", 0);
				// ��ʾ�Ի���
				t.show();

			}

		} else {
			@SuppressLint("WrongConstant") Toast t = Toast.makeText(repwdActivity.this, "��������������벻һ��", 0);
			// ��ʾ�Ի���
			t.show();

		}

	}

	public String sharePrefer() {
		SharedPreferences SP = getSharedPreferences("SharePreferences", 0);
		String name = SP.getString("name", "[δ��¼]");
		System.out.println(name);
		return name;
	}

	public String sharePrefer1() {
		SharedPreferences SP = getSharedPreferences("SharePreferences", 0);
		String pwd = SP.getString("pwd", "[δ��¼]");
		System.out.println(pwd);
		return pwd;
	}

	// ע��
	public void hh() {
		SharedPreferences SP = getSharedPreferences("SharePreferences", 0);
		Editor hh = SP.edit();
		// �޸ĸý���Ӧ��ֵ
		hh.putString("name", "[δ��¼]");
		String name = SP.getString("name", "[δ��¼]");
		System.out.println(name + "nameɾ��ʧ��hh");
		hh.commit();
		Intent intent = new Intent(repwdActivity.this, loginActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
				| android.content.Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
	}

}
