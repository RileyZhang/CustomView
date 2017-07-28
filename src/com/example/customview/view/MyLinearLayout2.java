package com.example.customview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class MyLinearLayout2 extends LinearLayout{

	private static final String TAG = "MyLinearLayout";
	public MyLinearLayout2(Context context) {
		this(context, null);
		// TODO Auto-generated constructor stub
	}

	public MyLinearLayout2(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
		// TODO Auto-generated constructor stub
	}

	public MyLinearLayout2(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		Log.i(TAG, "MyLinearLayout2 dispatchTouchEvent()");
		return super.dispatchTouchEvent(ev);
	}
	
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		Log.i(TAG, "MyLinearLayout2 onInterceptTouchEvent()");
		return super.onInterceptTouchEvent(ev);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		Log.i(TAG, "MyLinearLayout2 onTouchEvent()");
		return super.onTouchEvent(event);
	}
}
