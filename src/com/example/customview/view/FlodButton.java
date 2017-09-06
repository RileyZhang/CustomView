package com.example.customview.view;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FlodButton extends ViewGroup {

	private int mWidth = 0;
	private int mHeight = 0;
	private int center = 0;
	private int x = 0;
	private int mMax = 0;
	private int mMin = 0;
	private int radius = 0;
	private View mChild;
	private int mChildWidth = 0;
	private int mChildHeight = 0;
	private int mTextLeft = 0;
	private int mTextTop = 0;
	private int mTextRight = 0;
	private int mTextBottom;
	
	public FlodButton(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public FlodButton(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
		// TODO Auto-generated constructor stub
		
	}
	
	public FlodButton(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		init(context);
	}

	private void init(Context context) {
		TextView textView = new TextView(context);
		textView.setText("地铁/景区/商圈/超时");
		textView.setTextSize(20);
		textView.setTextColor(Color.WHITE);
		addView(textView);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		if (mWidth == 0) {
			mWidth = MeasureSpec.getSize(widthMeasureSpec);
			mHeight = MeasureSpec.getSize(heightMeasureSpec);
			
			Log.i("riley", "mWidth = " + mWidth + " mHeight = " + mHeight);
			center = mHeight/2;
			x = mWidth - center;
			mMax = mWidth;
			mMin = 2 * center;
			radius = center / 2;
			
			mChild = this.getChildAt(0);
			measureChild(mChild, widthMeasureSpec, heightMeasureSpec);
			mChildWidth = mChild.getMeasuredWidth();
			mChildHeight = mChild.getMeasuredHeight();
			mTextLeft = center * 2 + 5;
			mTextTop = center - mChildHeight/2;
			mTextRight = mMax - center;
			mTextBottom = center + mChildHeight/2;
		}
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		Log.i("riley", "onLayout");
		mChild.layout(mTextLeft, mTextTop, mTextRight, mTextBottom);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		Log.i("riley", "onDraw()");
		super.onDraw(canvas);
	}

	@Override
	protected void dispatchDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		Log.i("riley", "dispatchDraw()");
		Paint paint = new Paint();
		paint.setColor(Color.RED);
		canvas.drawCircle(center, center, center, paint);
		
		RectF rectf = new RectF(center, 0, mWidth - center, 2* center);
		canvas.drawRect(rectf, paint);
		
		canvas.drawCircle(mWidth - center, center, center, paint);
		
		paint.setColor(Color.BLACK);
		canvas.drawCircle(center, center, radius, paint);
		super.dispatchDraw(canvas);
	}
	
	public void startCloseAnima() {
		ValueAnimator animator = ValueAnimator.ofInt(mMax, mMin);
		animator.addUpdateListener(new AnimatorUpdateListener() {
			
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				// TODO Auto-generated method stub
				mWidth = (Integer)animation.getAnimatedValue();
				Log.i("riley", "mWidth = " + mWidth);
				postInvalidate();
			}
		});
		
		ValueAnimator centerAnimator = ValueAnimator.ofInt(center/2, center);
		centerAnimator.addUpdateListener(new AnimatorUpdateListener() {
			
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				// TODO Auto-generated method stub
				radius = (Integer)animation.getAnimatedValue();
				postInvalidate();
			}
		});
		ValueAnimator textAnimator = ValueAnimator.ofInt(mMax - center, mTextLeft);
		textAnimator.addUpdateListener(new AnimatorUpdateListener() {
			
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				// TODO Auto-generated method stub
				mTextRight = (Integer)animation.getAnimatedValue();
				requestLayout();
			}
		});
		AnimatorSet animatorSet = new AnimatorSet();
		animatorSet.setDuration(1000);
		animatorSet.playTogether(animator, centerAnimator, textAnimator);
		animatorSet.start();
	}
	
	public void startOpenAnima() {
		ValueAnimator animator = ValueAnimator.ofInt(mMin, mMax);
		animator.addUpdateListener(new AnimatorUpdateListener() {
			
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				// TODO Auto-generated method stub
				mWidth = (Integer)animation.getAnimatedValue();
				Log.i("riley", "mWidth = " + mWidth);
				postInvalidate();
			}
		});
		
		ValueAnimator circleAnimator = ValueAnimator.ofInt(center, center/2);
		circleAnimator.addUpdateListener(new AnimatorUpdateListener() {
			
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				// TODO Auto-generated method stub
				radius = (Integer)animation.getAnimatedValue();
				postInvalidate();
			}
		});
		
		ValueAnimator textAnimator = ValueAnimator.ofInt(mTextLeft, mMax - center);
		textAnimator.addUpdateListener(new AnimatorUpdateListener() {
			
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				// TODO Auto-generated method stub
				mTextRight = (Integer)animation.getAnimatedValue();
				requestLayout();
			}
		});
		AnimatorSet animationSet = new AnimatorSet();
		animationSet.setDuration(1000);
		animationSet.playTogether(animator, textAnimator, circleAnimator);
		animationSet.start();
	}
	
}
