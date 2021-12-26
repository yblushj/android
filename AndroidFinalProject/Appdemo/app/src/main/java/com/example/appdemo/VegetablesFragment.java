package com.example.appdemo;

import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class VegetablesFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		return inflater.inflate(R.layout.tab03, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		Button button1 = (Button) getActivity().findViewById(R.id.remencaipu);
		Button button2 = (Button) getActivity().findViewById(R.id.nianyefan);
		Button button3 = (Button) getActivity().findViewById(R.id.tang);
		Button button4 = (Button) getActivity().findViewById(R.id.zaocan);
		Button button5 = (Button) getActivity().findViewById(R.id.dangao);
		Button button6 = (Button) getActivity().findViewById(R.id.doufu);
		Button button7 = (Button) getActivity().findViewById(R.id.xia);
		Button button8 = (Button) getActivity().findViewById(R.id.paigu);
		Button button9 = (Button) getActivity().findViewById(R.id.ji);
		Button button10 = (Button) getActivity().findViewById(R.id.yu);
		Button button11 = (Button) getActivity().findViewById(R.id.niurou);
		Button button12 = (Button) getActivity().findViewById(R.id.yangrou);
		Button button13 = (Button) getActivity().findViewById(R.id.jidan);
		Button button14 = (Button) getActivity().findViewById(R.id.sanyao);
		Button button15 = (Button) getActivity().findViewById(R.id.ou);
		Button button16 = (Button) getActivity().findViewById(R.id.luobo);
		Button button17 = (Button) getActivity().findViewById(R.id.baicai);
		Button button18 = (Button) getActivity().findViewById(R.id.jingzhengu);

		OnClickListener Onclick = new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				// 获取被点击的按钮的资源id
				intent.putExtra("ID", v.getId());
				intent.setClass(getActivity(), VegetablesTypeActivity.class);
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
		button11.setOnClickListener(Onclick);
		button12.setOnClickListener(Onclick);
		button13.setOnClickListener(Onclick);
		button14.setOnClickListener(Onclick);
		button15.setOnClickListener(Onclick);
		button16.setOnClickListener(Onclick);
		button17.setOnClickListener(Onclick);
		button18.setOnClickListener(Onclick);

	}

}
