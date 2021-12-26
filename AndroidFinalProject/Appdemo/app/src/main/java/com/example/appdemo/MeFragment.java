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

	// /��ʾ�û���¼���ǳƣ���Ϊ���Textview�ؼ��������Fragment�������Ҫ�������ȡ�˿ؼ�ID
	public void onStart() {
		super.onStart();
		sharePrefer();

	}

	public void sharePrefer() {
		TextView nicheng = (TextView) getActivity().findViewById(R.id.nicheng);
		SharedPreferences SP = getActivity().getSharedPreferences(
				"SharePreferences", 0);
		String name = SP.getString("name", "[δ��¼]");
		System.out.println(name);
		nicheng.setText("�ǳ� :" + " " + name);
	}

	// /Fragment������Ҫ�����������д
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		Intent intent = new Intent();
		// ����(��¼����ע��ҳ��)������
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

		// ע���û�����������ť
		Button button1 = (Button) getActivity().findViewById(R.id.zuxiao);

		button1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				SharedPreferences SP = getActivity().getSharedPreferences(
						"SharePreferences", 0);
				Editor hh = SP.edit();
				// �޸ĸý���Ӧ��ֵ
				hh.putString("name", "[δ��¼]");
				String name = SP.getString("name", "[δ��¼]");
				System.out.println(name + "nameɾ��ʧ��hh");
				hh.commit();
				Intent intent = new Intent(getActivity(), loginActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK
						| android.content.Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);

			}
		});

		// ��ת���޸��������İ�ť����
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
