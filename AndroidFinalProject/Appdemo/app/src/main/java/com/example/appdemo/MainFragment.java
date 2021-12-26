package com.example.appdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

public class MainFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.tab01, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		ImageButton button1 = (ImageButton) getActivity().findViewById(
				R.id.shouye_1);
		ImageButton button2 = (ImageButton) getActivity().findViewById(
				R.id.shouye_2);
		ImageButton button3 = (ImageButton) getActivity().findViewById(
				R.id.shouye_3);
		ImageButton button4 = (ImageButton) getActivity().findViewById(
				R.id.shouye_4);
		ImageButton button5 = (ImageButton) getActivity().findViewById(
				R.id.shouye_5);
		ImageButton button6 = (ImageButton) getActivity().findViewById(
				R.id.shouye_6);
		ImageButton button7 = (ImageButton) getActivity().findViewById(
				R.id.shouye_7);
		ImageButton button8 = (ImageButton) getActivity().findViewById(
				R.id.shouye_8);
		ImageButton button9 = (ImageButton) getActivity().findViewById(
				R.id.shouye_9);
		ImageButton button10 = (ImageButton) getActivity().findViewById(
				R.id.shouye_10);

		OnClickListener Onclick = new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				// 获取被点击的按钮的资源id
				intent.putExtra("ID", v.getId());
				intent.setClass(getActivity(), MaindoingActivity.class);
				getActivity().startActivity(intent);
			}
		};
		// 给这些按钮设置同一个侦听
		button1.setOnClickListener(Onclick);
		button2.setOnClickListener(Onclick);
		button3.setOnClickListener(Onclick);
		button4.setOnClickListener(Onclick);
		button5.setOnClickListener(Onclick);
		button6.setOnClickListener(Onclick);
		button7.setOnClickListener(Onclick);
		button8.setOnClickListener(Onclick);
		button9.setOnClickListener(Onclick);
		button10.setOnClickListener(Onclick);
	}

}
