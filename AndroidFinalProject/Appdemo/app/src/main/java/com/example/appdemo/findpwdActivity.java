package com.example.appdemo;
import android.annotation.SuppressLint;
import android.view.View;
import android.view.View.OnClickListener;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

public class findpwdActivity extends Activity {
	private NotificationManager mNotificationManager;
	private Notification mNotification;
	private PendingIntent mResultIntent;

	long number;
	String address = "1865232";
	String type = "1";

	@SuppressLint("WrongConstant")
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.findpwd);

		Uri sms = Uri.parse("smsto:1865232");
		Intent mIntent = new Intent(Intent.ACTION_SENDTO, sms);

		mResultIntent = PendingIntent.getActivity(findpwdActivity.this, 1,
				mIntent, Intent.FLAG_ACTIVITY_NEW_TASK);

		mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

		Button suButton = (Button) findViewById(R.id.subBtn);

		suButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Toast t = Toast.makeText(findpwdActivity.this, "获取成功", 0);
				// 显示对话框
				t.show();

				number = Math.round(Math.random() * 899999) + 100000;
				String body = "[开放味蕾] 您的验证码为：" + number + ";如果不是你本人的操作，请忽略此条信息";

				ContentResolver cr = getContentResolver();
				ContentValues values = new ContentValues();
				values.put("address", address);
				values.put("type", type);
				values.put("date", System.currentTimeMillis());
				values.put("body", body);
				cr.insert(Uri.parse("content://sms"), values);

				Bitmap largeIcon = BitmapFactory.decodeResource(
						findpwdActivity.this.getResources(),
						R.drawable.new_massage_main);
				mNotification = new NotificationCompat.Builder(getBaseContext())

				// 设置大图标

						.setLargeIcon(largeIcon)
						// 设置小图标
						.setSmallIcon(R.drawable.new_massage_top)
						// 设置小图标旁的文本信息
						.setContentInfo("1")
						// 设置状态栏文本标题
						.setTicker("开放味蕾")
						// 设置标题
						.setContentTitle(address)
						// 设置内容
						.setContentText(body)
						// 设置ContentIntent
						.setContentIntent(mResultIntent)
						// 设置Notification提示铃声为系统默认铃声
						.setSound(
								RingtoneManager.getActualDefaultRingtoneUri(
										getBaseContext(),
										RingtoneManager.TYPE_NOTIFICATION))
						// 点击Notification的时候使它自动消失
						.setAutoCancel(true).build();
				mNotificationManager.notify(0, mNotification);
			}
		});
	}

	public void Yes(View v) {

		EditText editext = (EditText) findViewById(R.id.accountEt);
		EditText yanzhenma = (EditText) findViewById(R.id.pwdEt);
		EditText name = (EditText) findViewById(R.id.name);
		String input = editext.getText().toString();
		String input1 = yanzhenma.getText().toString();
		String nameinput = name.getText().toString();

		// 手机号不能为空
		if (input.equals(" ") || input.length() < 11) {
			@SuppressLint("WrongConstant") Toast t = Toast.makeText(findpwdActivity.this,
					"手机号不能为空,手机号位数不能低于11位", 0);
			// 显示对话框
			t.show();

		} else {

			// 验证码是否一致,number是整形
			System.out.println("判断验证码" + number);
			if (input1.equals(String.valueOf(number))) {

				SQLiteDatabase dbsqlitedatabase = null;
				// 打开数据库
				dbsqlitedatabase = SQLiteDatabase.openOrCreateDatabase(this
						.getFilesDir().toString() + "/login.db", null);
				Cursor resultCursor = dbsqlitedatabase
						.query("login",
								new String[] { "User_No", "User_Name" },
								"User_Name=" + "'" + nameinput + "'", null,
								null, null, null);
				// 判断用户是否存在
				if (resultCursor.moveToFirst()) {
					// 修改密码
					ContentValues updatepwd = new ContentValues();
					long newpwd = Math.round(Math.random() * 899999) + 100000;
					updatepwd.put("Pwd", newpwd);
					String where = "User_Name=?";
					String[] where1 = { name.getText().toString() };
					dbsqlitedatabase.update("login", updatepwd, where, where1);
					tishilan(newpwd);
					@SuppressLint("WrongConstant") Toast t = Toast.makeText(findpwdActivity.this, "密码已重置", 0);
					// 显示对话框
					t.show();

					Intent intent = new Intent();
					intent.setClass(findpwdActivity.this, loginActivity.class);
					findpwdActivity.this.startActivity(intent);

				} else {

					@SuppressLint("WrongConstant") Toast t = Toast.makeText(findpwdActivity.this, "请确认用户是否存在",
							0);
					// 显示对话框
					t.show();

				}
				resultCursor.close();
				dbsqlitedatabase.close();

			} else {
				@SuppressLint("WrongConstant") Toast t = Toast.makeText(findpwdActivity.this, "输入的验证码错误", 0);
				// 显示对话框
				t.show();

			}
		}

	}

	public void tishilan(long newpwd) {
		String body = "[开放味蕾] 您的新密码为：" + newpwd + ";如果不是你本人的操作，请忽略此条信息";

		ContentResolver cr = getContentResolver();
		ContentValues values = new ContentValues();
		values.put("address", address);
		values.put("type", type);
		values.put("date", System.currentTimeMillis());
		values.put("body", body);
		cr.insert(Uri.parse("content://sms"), values);

		Bitmap largeIcon = BitmapFactory.decodeResource(
				findpwdActivity.this.getResources(),
				R.drawable.new_massage_main);
		mNotification = new NotificationCompat.Builder(getBaseContext())

		// 设置大图标
				.setLargeIcon(largeIcon)
				// 设置小图标
				.setSmallIcon(R.drawable.new_massage_top)
				// 设置小图标旁的文本信息
				.setContentInfo("1")
				// 设置状态栏文本标题
				.setTicker("开放味蕾")
				// 设置标题
				.setContentTitle(address)
				// 设置内容
				.setContentText(body)
				// 设置ContentIntent
				.setContentIntent(mResultIntent)
				// 设置Notification提示铃声为系统默认铃声
				.setSound(
						RingtoneManager.getActualDefaultRingtoneUri(
								getBaseContext(),
								RingtoneManager.TYPE_NOTIFICATION))
				// 点击Notification的时候使它自动消失
				.setAutoCancel(true).build();
		mNotificationManager.notify(0, mNotification);
	}

}
