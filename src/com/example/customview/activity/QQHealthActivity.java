package com.example.customview.activity;

import com.example.customview.R;
import com.example.customview.view.QQHealthView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class QQHealthActivity extends Activity {

	private QQHealthView myQQHealthView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.qq_health_layout);
		myQQHealthView = (QQHealthView)findViewById(R.id.qq_health_view);
		myQQHealthView.setMySize(2345);
		myQQHealthView.setRank(11);
		myQQHealthView.setAverageSize(5436);
		Button qqButton = (Button)findViewById(R.id.qq_button);
		qqButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				myQQHealthView.reSet(6534, 8, 4567);
			}
		});
	}
}
