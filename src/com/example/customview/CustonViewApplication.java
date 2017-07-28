package com.example.customview;

import com.example.customview.Util.GlobalConstant;

import android.app.Application;

public class CustonViewApplication extends Application{

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		GlobalConstant.setGlobalContext(getApplicationContext());
	}
}
