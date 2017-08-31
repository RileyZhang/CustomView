package com.example.customview.activity;

import com.example.customview.R;
import com.example.customview.view.RoundDrawable;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

public class CustomDrawableActivity extends Activity {

	private ImageView mImageView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.custom_drawable_layout);
		mImageView = (ImageView)findViewById(R.id.custom_drawable_image);
		mImageView.setImageDrawable(new RoundDrawable(BitmapFactory.decodeResource(getResources(), R.drawable.timg)));
	}
}
