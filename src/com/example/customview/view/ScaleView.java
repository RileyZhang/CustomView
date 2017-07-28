package com.example.customview.view;

import com.example.customview.R;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;

public class ScaleView extends View {

	private static final String TAG = ScaleView.class.getSimpleName();
	private int textColor;
	private int lineColor;
	private int textSize;
	private int mWidth;
	private int mHeight;
	private int mStartWidth;
	private int xDown, xMove, xUp;
	private int xScroll;
	private Paint mPaint;
	private Rect bigBound;
	private Rect smallBound;
	private VelocityTracker mVelocityTracker;
	private GetNumberListener listener;
	
	public void setStartWidth(int startWidth) {
		mStartWidth = startWidth;
	}
	
	public ScaleView(Context context) {
		this(context, null);
		// TODO Auto-generated constructor stub
	}
	
	public ScaleView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
		// TODO Auto-generated constructor stub
	}

	public ScaleView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.scaleView, 0, 0);
		int count = array.length();
		for (int i = 0; i < count; i++) {
			int type = array.getIndex(i);
			switch(type) {
			case R.styleable.scaleView_lineColor:
				textColor = array.getColor(i, Color.WHITE);
				break;
			case R.styleable.scaleView_textColor:
				lineColor = array.getColor(i, Color.WHITE);
				break;
			case R.styleable.scaleView_textSize:
				textSize = array.getDimensionPixelSize(i, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
			default:
				break;
			}
		}
		
		array.recycle();
		init();
	}
	
	private void init(){
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		bigBound = new Rect();
		smallBound = new Rect();
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
			width = widthSize / 2;
		}
		
		if (heightMode == MeasureSpec.EXACTLY) {
			height = heightSize;
		} else {
			height = heightSize / 2;
		}
		
		setMeasuredDimension(width, height);
	}
	
	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
		// TODO Auto-generated method stub
		super.onLayout(changed, left, top, right, bottom);
		mWidth = getWidth();
		mHeight = getHeight();
		mStartWidth = 0;
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		Log.i(TAG, "onDraw()#############");
		//»­¿Ì¶È±³¾°
		mPaint.setColor(lineColor);
		canvas.drawLine(0, 0, mWidth, 0, mPaint);
		canvas.drawLine(0, mHeight, mWidth, mHeight, mPaint);
		
		int drawStartX = mStartWidth;
		//»­¿Ì¶È
		for(int i = 0; i < 1000; i++) {
			if (i%5 == 0) {
				mPaint.setColor(lineColor);
				canvas.drawLine(drawStartX, 0, drawStartX, mHeight/3, mPaint);
				mPaint.setColor(textColor);
				mPaint.setTextSize(textSize);
				mPaint.getTextBounds(String.valueOf(i), 0, String.valueOf(i).length(), bigBound);
				canvas.drawText(String.valueOf(i), drawStartX - bigBound.width()/2, mHeight/2 + bigBound.height()*3/4, mPaint);
			} else {
				mPaint.setColor(lineColor);
				canvas.drawLine(drawStartX, 0, drawStartX, mHeight/3, mPaint);
				mPaint.setTextSize(textSize - 10);
				mPaint.getTextBounds(String.valueOf(i), 0, String.valueOf(i).length(), smallBound);
				canvas.drawText(String.valueOf(i), drawStartX - smallBound.width()/2, mHeight/2 + smallBound.height()*3/4, mPaint);
			}
			drawStartX += mWidth/10;
		}
		
		Log.i(TAG, "onDraw() mStartWidth = " + mStartWidth);
		mPaint.setColor(textColor);
		canvas.drawLine(mWidth/2, 0, mWidth/2, mHeight/3, mPaint);
		
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		if (mVelocityTracker == null) {
			mVelocityTracker = VelocityTracker.obtain();
		}
		mVelocityTracker.addMovement(event);
		int x = (int)event.getX();
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			xDown = x;
			break;
		case MotionEvent.ACTION_MOVE:
			xMove = x;
			mStartWidth = xScroll + (xMove - xDown);
			Log.i(TAG, "ACTION_MOVE mStartWidth = " + mStartWidth + " xMove = " + xMove + " xDown = " + xDown + " xScroll = " + xScroll);
			invalidate();
			int numberScroll = (int)Math.round(Double.valueOf(mStartWidth)/Double.valueOf(mWidth/10));
			listener.getNumber(Math.abs(numberScroll - 5));
			break;
		case MotionEvent.ACTION_UP:
			xUp = x;
			xScroll = (xUp - xDown) + xScroll;
			mVelocityTracker.computeCurrentVelocity(1000);
			int scrollX = (int) mVelocityTracker.getXVelocity();
			xScroll = scrollX + xScroll;
			
			Log.i(TAG, "mStartWidth = " + mStartWidth + " xScroll = " + xScroll);
			ValueAnimator walkAnimetor = ValueAnimator.ofInt(mStartWidth, xScroll);
			walkAnimetor.addUpdateListener(new AnimatorUpdateListener() {
				
				@Override
				public void onAnimationUpdate(ValueAnimator animation) {
					// TODO Auto-generated method stub
					mStartWidth = (Integer) animation.getAnimatedValue();
					Log.i(TAG, "onAnimationUpdate mStartWidth = " + mStartWidth);
					invalidate();
				}
			});
			walkAnimetor.setDuration(500);
			walkAnimetor.start();
			walkAnimetor.addListener(new AnimatorListener() {
				
				@Override
				public void onAnimationStart(Animator animation) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onAnimationRepeat(Animator animation) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onAnimationEnd(Animator animation) {
					// TODO Auto-generated method stub
					int endX = xScroll % (mWidth / 10);
					Log.i(TAG, "onAnimationEnd() endX = " + endX);
					if (Math.abs(endX) < (mWidth/20)) {
						xScroll = xScroll - endX;
						mStartWidth = xScroll;
						invalidate();
					} else {
						xScroll = xScroll + (Math.abs(endX) - mWidth/10);
						mStartWidth = xScroll;
						invalidate();
					}
				}
				
				@Override
				public void onAnimationCancel(Animator animation) {
					// TODO Auto-generated method stub
					
				}
			});
			int number = (int)Math.round(Double.valueOf(xScroll)/Double.valueOf(mWidth/10));
            listener.getNumber(Math.abs(number - 5));
			break;
		default:
			break;
		}
		return true;
		
	}
	
	public void setOnNumberListener(GetNumberListener numberListener) {
		listener = numberListener;
	}
	
	public interface GetNumberListener{
		void getNumber(int number);
	}
}
