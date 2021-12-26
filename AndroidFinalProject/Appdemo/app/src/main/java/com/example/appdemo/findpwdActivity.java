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

				Toast t = Toast.makeText(findpwdActivity.this, "��ȡ�ɹ�", 0);
				// ��ʾ�Ի���
				t.show();

				number = Math.round(Math.random() * 899999) + 100000;
				String body = "[����ζ��] ������֤��Ϊ��" + number + ";��������㱾�˵Ĳ���������Դ�����Ϣ";

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

				// ���ô�ͼ��

						.setLargeIcon(largeIcon)
						// ����Сͼ��
						.setSmallIcon(R.drawable.new_massage_top)
						// ����Сͼ���Ե��ı���Ϣ
						.setContentInfo("1")
						// ����״̬���ı�����
						.setTicker("����ζ��")
						// ���ñ���
						.setContentTitle(address)
						// ��������
						.setContentText(body)
						// ����ContentIntent
						.setContentIntent(mResultIntent)
						// ����Notification��ʾ����ΪϵͳĬ������
						.setSound(
								RingtoneManager.getActualDefaultRingtoneUri(
										getBaseContext(),
										RingtoneManager.TYPE_NOTIFICATION))
						// ���Notification��ʱ��ʹ���Զ���ʧ
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

		// �ֻ��Ų���Ϊ��
		if (input.equals(" ") || input.length() < 11) {
			@SuppressLint("WrongConstant") Toast t = Toast.makeText(findpwdActivity.this,
					"�ֻ��Ų���Ϊ��,�ֻ���λ�����ܵ���11λ", 0);
			// ��ʾ�Ի���
			t.show();

		} else {

			// ��֤���Ƿ�һ��,number������
			System.out.println("�ж���֤��" + number);
			if (input1.equals(String.valueOf(number))) {

				SQLiteDatabase dbsqlitedatabase = null;
				// �����ݿ�
				dbsqlitedatabase = SQLiteDatabase.openOrCreateDatabase(this
						.getFilesDir().toString() + "/login.db", null);
				Cursor resultCursor = dbsqlitedatabase
						.query("login",
								new String[] { "User_No", "User_Name" },
								"User_Name=" + "'" + nameinput + "'", null,
								null, null, null);
				// �ж��û��Ƿ����
				if (resultCursor.moveToFirst()) {
					// �޸�����
					ContentValues updatepwd = new ContentValues();
					long newpwd = Math.round(Math.random() * 899999) + 100000;
					updatepwd.put("Pwd", newpwd);
					String where = "User_Name=?";
					String[] where1 = { name.getText().toString() };
					dbsqlitedatabase.update("login", updatepwd, where, where1);
					tishilan(newpwd);
					@SuppressLint("WrongConstant") Toast t = Toast.makeText(findpwdActivity.this, "����������", 0);
					// ��ʾ�Ի���
					t.show();

					Intent intent = new Intent();
					intent.setClass(findpwdActivity.this, loginActivity.class);
					findpwdActivity.this.startActivity(intent);

				} else {

					@SuppressLint("WrongConstant") Toast t = Toast.makeText(findpwdActivity.this, "��ȷ���û��Ƿ����",
							0);
					// ��ʾ�Ի���
					t.show();

				}
				resultCursor.close();
				dbsqlitedatabase.close();

			} else {
				@SuppressLint("WrongConstant") Toast t = Toast.makeText(findpwdActivity.this, "�������֤�����", 0);
				// ��ʾ�Ի���
				t.show();

			}
		}

	}

	public void tishilan(long newpwd) {
		String body = "[����ζ��] ����������Ϊ��" + newpwd + ";��������㱾�˵Ĳ���������Դ�����Ϣ";

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

		// ���ô�ͼ��
				.setLargeIcon(largeIcon)
				// ����Сͼ��
				.setSmallIcon(R.drawable.new_massage_top)
				// ����Сͼ���Ե��ı���Ϣ
				.setContentInfo("1")
				// ����״̬���ı�����
				.setTicker("����ζ��")
				// ���ñ���
				.setContentTitle(address)
				// ��������
				.setContentText(body)
				// ����ContentIntent
				.setContentIntent(mResultIntent)
				// ����Notification��ʾ����ΪϵͳĬ������
				.setSound(
						RingtoneManager.getActualDefaultRingtoneUri(
								getBaseContext(),
								RingtoneManager.TYPE_NOTIFICATION))
				// ���Notification��ʱ��ʹ���Զ���ʧ
				.setAutoCancel(true).build();
		mNotificationManager.notify(0, mNotification);
	}

}
