package com.example.customview.view;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class TestView extends View {

	private Paint mPaint;
	
	public TestView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	public TestView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
	}
	
	public TestView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	private void init() {
		mPaint = new Paint();
		mPaint.setStrikeThruText(true);
	}
	
}
