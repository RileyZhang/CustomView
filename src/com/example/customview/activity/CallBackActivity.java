package com.example.customview.activity;

import com.example.customview.R;
import com.example.customview.view.MyView;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;

public class CallBackActivity extends Activity {

	private static final String TAG = "MyView";
	private MyView mMyView;
	private LinearLayout mMyLinearLayout;
	private Handler myHandler = new Handler();
	private Runnable mLoadingRunnable = new Runnable() {
	    @Override
	    public void run() {
//	      updateText();
	    }
	  };
	  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.call_back_layout);
		mMyLinearLayout = (LinearLayout)findViewById(R.id.my_linearlayout);
		mMyLinearLayout.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				return false;
			}
		});
		
		
		mMyView = (MyView)findViewById(R.id.my_view);
		mMyView.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				Log.i(TAG, "CallBackActivity setOnTouchListener onTouch event = " + event.getAction());
				return false;
			}
		});
		
		mMyView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.i(TAG, "CallBackActivity setOnTouchListener onClick ");
			}
		});
		
		
		getWindow().getDecorView().post(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				myHandler.post(mLoadingRunnable);
			}
		});
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
	
	@Override
	public void onUserInteraction() {
		// TODO Auto-generated method stub
		Log.i(TAG, "CallBackActivity onUserInteraction");
		super.onUserInteraction();
	}
	
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		Log.i(TAG, "CallBackActivity dispatchTouchEvent ev = " + ev.getAction());
		return super.dispatchTouchEvent(ev);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		Log.i(TAG, "CallBackActivity onTouchEvent event = " + event.getAction());
		return super.onTouchEvent(event);
	}
}
