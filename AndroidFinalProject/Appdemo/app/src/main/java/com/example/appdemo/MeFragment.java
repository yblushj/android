package com.example.appdemo;

import java.net.ContentHandler;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;

import androidx.fragment.app.Fragment;

public class MeFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		return inflater.inflate(R.layout.tab02, container, false);
	}

	// /显示用户登录的昵称，因为这个Textview控件是在这个Fragment里的所以要在这里获取此控件ID
	public void onStart() {
		super.onStart();
		sharePrefer();

	}

	public void sharePrefer() {
		TextView nicheng = (TextView) getActivity().findViewById(R.id.nicheng);
		SharedPreferences SP = getActivity().getSharedPreferences(
				"SharePreferences", 0);
		String name = SP.getString("name", "[未登录]");
		System.out.println(name);
		nicheng.setText("昵称 :" + " " + name);
	}

	// /Fragment的侦听要在这个方法里写
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Intent intent = new Intent();
		// 跳到(登录或者注册页面)的侦听
		Button button = (Button) getActivity().findViewById(
				R.id.loginorregister);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(getActivity(), loginORregisterActivity.class);
				getActivity().startActivity(intent);
			}
		});

		// 注销用户名的侦听按钮
		Button button1 = (Button) getActivity().findViewById(R.id.zuxiao);

		button1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				SharedPreferences SP = getActivity().getSharedPreferences(
						"SharePreferences", 0);
				Editor hh = SP.edit();
				// 修改该建对应的值
				hh.putString("name", "[未登录]");
				String name = SP.getString("name", "[未登录]");
				System.out.println(name + "name删除失败hh");
				hh.commit();
				Intent intent = new Intent(getActivity(), loginActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
						| android.content.Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);

			}
		});

		// 跳转到修改密码界面的按钮侦听
		Button button2 = (Button) getActivity().findViewById(R.id.repwd);

		button2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(getActivity(), repwdActivity.class);
				getActivity().startActivity(intent);

			}
		});

	}

}
