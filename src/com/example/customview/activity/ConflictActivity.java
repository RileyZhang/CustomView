package com.example.customview.activity;

import com.example.customview.R;

import android.app.Activity;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

public class ConflictActivity extends Activity {

	private static final String TAG = "MyLinearLayout";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.conflict_view_layout);
	}
	
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		Log.i(TAG, "ConflictActivity dispatchTouchEvent()");
		return super.dispatchTouchEvent(ev);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		Log.i(TAG, "ConflictActivity onTouchEvent()");
		return super.onTouchEvent(event);
	}
}
