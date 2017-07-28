package com.example.customview.view;

import java.util.ArrayList;
import java.util.List;

import com.example.customview.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.location.GpsStatus.Listener;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class ChartView extends View {

	private int leftColor, rightColor, textColor;
	private Paint mPaint, mChartPaint, mShadowPaint;
	private int mWidth, mHeight, mStartWidth,mSize,mChartWidth;
	private Rect mTextBound;
	private int number;
	private List<Integer> mValueList = new ArrayList<Integer>();
	private GetNumberListener listener;
	
	public void setValueList(List<Integer> list) {
		mValueList = list;
	}
	
	public ChartView(Context context) {
		this(context, null);
		// TODO Auto-generated constructor stub
	}

	public ChartView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
		// TODO Auto-generated constructor stub
	}
	
	
	public ChartView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.chartView, defStyleAttr, 0);
		int count = array.length();
		for (int i = 0; i < count; i++) {
			int type = array.getIndex(i);
			switch(type) {
			case R.styleable.chartView_leftColor:
				leftColor = array.getColor(type, Color.RED);
				break;
			case R.styleable.chartView_rightColor:
				rightColor = array.getColor(type, Color.GREEN);
				break;
			case R.styleable.chartView_textColor:
				textColor = array.getColor(type, Color.BLACK);
				break;
			default:
				break;
			}
		}
		
		array.recycle();
		init();
	}
	
	private void init(){
		mPaint = new Paint();
		mChartPaint = new Paint();
		mShadowPaint = new Paint();
		mTextBound = new Rect();
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);
		
		int width;
		int height;
		if (widthMode == MeasureSpec.EXACTLY) {
			width = widthSize;
		} else {
			width = widthSize/2;
		}
		
		if (heightMode == MeasureSpec.EXACTLY) {
			height = heightSize;
		} else {
			height = heightSize/2;
		}
		setMeasuredDimension(width, height);
	}
	
	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
		// TODO Auto-generated method stub
		super.onLayout(changed, left, top, right, bottom);
		mWidth = getWidth();
		mHeight = getHeight();
		
		mStartWidth = mWidth/13;
		mSize = mWidth/39;
		mChartWidth = mWidth/13 - mSize;
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		mPaint.setColor(Color.WHITE);
		Rect rect = new Rect(0, 0, mWidth, mHeight);
		canvas.drawRect(rect, mPaint);
		
		mPaint.setColor(Color.BLACK);
		canvas.drawLine(0, mHeight - 100, mWidth, mHeight - 100, mPaint);
		
		//画刻度
		mPaint.setColor(Color.BLACK);
		for (int i = 0 ; i < 12; i++) {
			int startX = mStartWidth * (i+1);
			int startY = mHeight - 100;
			canvas.drawLine(startX, startY, startX, startY + 20, mPaint);
			
			mPaint.setTextSize(20);
			mPaint.setTextAlign(Paint.Align.CENTER);
			String textString = String.valueOf(i + 1) + "月";
			mPaint.getTextBounds(textString, 0, textString.length(), mTextBound);
			canvas.drawText(textString, startX, startY + 40 + mTextBound.height()/2, mPaint);
		}
		
		int size = mHeight / 200;
		for (int i = 0; i < mValueList.size(); i++) {
			if (i%2 == 0) {
				mChartPaint.setColor(leftColor);
			} else {
				mChartPaint.setColor(rightColor);
			}
			
			if ((i == number * 2) || (i == number * 2 + 1)) {
				mShadowPaint.setColor(Color.LTGRAY);
			} else {
				mShadowPaint.setColor(Color.WHITE);
			}
			canvas.drawRect(mChartWidth, 0, mChartWidth + mSize, mHeight - 100, mShadowPaint);
          
			mChartPaint.setStyle(Paint.Style.FILL);
			canvas.drawRect(mChartWidth, mHeight - 100 - mValueList.get(i) * size, mChartWidth + mSize, mHeight - 100, mChartPaint);// 长方形
            
            mChartWidth += (i % 2 == 0) ? (getWidth() / 39) : (getWidth() / 13 - mSize);
		}
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub

		int x = (int)event.getX();
		int y = (int)event.getY();
		int left = 0;
		int top = 0;
		int right = mWidth/12;
		int bottom = mHeight - 100;
		switch(event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			for (int i = 0; i < 12; i++) {
				Rect rect = new Rect(left, top, right, bottom);
				left += mWidth/12;
				right += mWidth/12;
				if (rect.contains(x, y)) {
					listener.getNumber(i, x, y);
					number = i;
					invalidate();
				}
			}
			break;
		}
		return true;
	}
	
	public void setOnNumberListener(GetNumberListener numberListener) {
		listener = numberListener;
	}
	
	public interface GetNumberListener{
		void getNumber(int i,int x, int y);
	}
}
