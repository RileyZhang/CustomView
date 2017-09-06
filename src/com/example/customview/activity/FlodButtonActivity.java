package com.example.customview.activity;

import com.example.customview.R;
import com.example.customview.view.FlodButton;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class FlodButtonActivity extends Activity implements OnClickListener{

	private FlodButton mFlodButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.flod_button_layout);
		Window window = getWindow();
		WindowManager.LayoutParams attribute = window.getAttributes();
		attribute.flags = attribute.flags|WindowManager.LayoutParams.FLAG_FULLSCREEN;
		window.setAttributes(attribute);
		
		Button openButton = (Button)findViewById(R.id.open);
		Button closebutton = (Button)findViewById(R.id.close);
		openButton.setOnClickListener(this);
		closebutton.setOnClickListener(this);
		mFlodButton = (FlodButton)findViewById(R.id.flod_button);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()) {
		case R.id.open:
			mFlodButton.startOpenAnima();
			break;
		case R.id.close:
			mFlodButton.startCloseAnima();
			break;
		}
	}
}
