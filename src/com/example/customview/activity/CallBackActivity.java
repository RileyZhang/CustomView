package com.example.customview.activity;

import com.example.customview.R;
import com.example.customview.view.MyView;

import android.app.Activity;
import android.os.Bundle;

public class CallBackActivity extends Activity {

	private MyView mMyView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.call_back_layout);
		mMyView = (MyView)findViewById(R.id.my_view);
	}
}
