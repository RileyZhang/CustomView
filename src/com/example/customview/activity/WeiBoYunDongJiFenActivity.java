package com.example.customview.activity;

import com.example.customview.R;
import com.example.customview.view.WeiBoView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class WeiBoYunDongJiFenActivity extends Activity {

	private WeiBoView mWeiBoView;
	private Button mIncreaseButton,mDecreaseButton; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wei_bo_layout);
		mWeiBoView = (WeiBoView) findViewById(R.id.wei_bo_view);
		mIncreaseButton = (Button) findViewById(R.id.increase);
		mIncreaseButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mWeiBoView.setNumber(40);
			}
		});
		
		mDecreaseButton = (Button) findViewById(R.id.decrease);
		mDecreaseButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mWeiBoView.setNumber(20);
			}
		});
	}
}
