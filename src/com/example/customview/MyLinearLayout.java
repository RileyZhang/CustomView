package com.example.customview;

import com.example.customview.view.MyView;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class MyLinearLayout extends LinearLayout {

	private static final String TAG = "MyView";
			
	public MyLinearLayout(Context context, AttributeSet attrs) {
		// TODO Auto-generated constructor stub
		super(context, attrs);
	}
	
	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		Log.i(TAG, "MyLinearLayout() dispatchTouchEvent event = " + event.getAction());
		return super.dispatchTouchEvent(event);
	}
	
	@Override
	public boolean onInterceptTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		Log.i(TAG, "MyLinearLayout() onInterceptTouchEvent event = " + event.getAction());
		return super.onInterceptTouchEvent(event);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		Log.i(TAG, "MyLinearLayout() onTouchEvent event = " + event.getAction());
		return super.onTouchEvent(event);
	}
}
