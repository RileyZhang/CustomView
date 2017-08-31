package com.example.customview.view;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.animation.AccelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class MyLinearLayout extends LinearLayout {
	
	private final static String TAG = MyLinearLayout.class.getSimpleName(); 
	private int yDown;
	private int yDistance;
	private int yMove;
	private int i, move;;
	private boolean intercept;
	private ScrollView mScrollView;
	private ValueAnimator mAnimator;
	private int mActionBottom, mActionTop;
	
	public MyLinearLayout(Context context) {
		this(context, null);
		// TODO Auto-generated constructor stub
	}

	public MyLinearLayout(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
		// TODO Auto-generated constructor stub
	}
	
	public MyLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		super.onLayout(changed, l, t, r, b);
		mScrollView = (ScrollView) getChildAt(0);
	}
	
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		Log.i(TAG, "MyLinearLayout onInterceptTouchEvent() ev.getAction() = " + ev.getAction());
		int y = (int)ev.getY();
		int scrollY = mScrollView.getScrollY();
		
		switch(ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			Log.i(TAG, "ACTION_DOWN y = " + y);
			yDown = y;
			if (mAnimator != null) {
				mAnimator.end();//一定要加这个，让布局立刻回位，不然的话，回弹动画没结束就马上进行下一次的下拉，布局会飞走噢  
			}
			break;
		case MotionEvent.ACTION_MOVE:
			Log.i(TAG, "ACTION_MOVE y = " + y + " yDown = " + yDown + " scrollY = " + scrollY);
			yMove = y;
			if (y > yDown && scrollY == 0) {
                if (!intercept) {
	                yDown = (int) ev.getY();  
					intercept = true;
                }
            }
			break;
		case MotionEvent.ACTION_UP:
			mActionBottom = getBottom();
			mActionTop = getTop();
			Log.i(TAG, "ACTION_UP i = " + i);
			mAnimator = ValueAnimator.ofInt(0, i);
			mAnimator.setDuration(500);
//			mAnimator.setInterpolator(new AccelerateInterpolator());
			mAnimator.addUpdateListener(new AnimatorUpdateListener() {
				
				@Override
				public void onAnimationUpdate(ValueAnimator animation) {
					// TODO Auto-generated method stub
					int currentValue = (Integer)animation.getAnimatedValue();
					layout(getLeft(), mActionTop - currentValue, getRight(), mActionBottom - currentValue);
				}
			});
			mAnimator.start();
			i = 0;
			intercept = false;
			break;
		}
		
		if (intercept) {
			move = yMove - yDown;
			i += move;
			Log.i(TAG, "i = " + i);
			if (i < 0) {//判断少于0时，要把isIntercept至为false了，不然布局会一直往上移动，会移出去
				intercept = false;
				 //这里做了 move - i 的处理，是为了要把布局刚好回位到y = 0的位置
				layout(getLeft(), getTop() + move - i, getRight(), getBottom() + move - i);
				i = 0;
			} else {
				layout(getLeft(), getTop() + move, getRight(), getBottom() + move);
			}
		}
		return super.onInterceptTouchEvent(ev);
	}
	
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		Log.i(TAG, "MyLinearLayout dispatchTouchEvent() ev.getAction() = " + ev.getAction());
		onInterceptTouchEvent(ev);
		return super.dispatchTouchEvent(ev);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		Log.i(TAG, "MyLinearLayout onTouchEvent() event.getAction() = " + event.getAction());
		int y = (int)event.getY();
		switch(event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			yDown = y;
			break;
		case MotionEvent.ACTION_MOVE:
			if (y > yDown) {
				yMove = y - yDown;
				yDistance += yMove;
				layout(getLeft(), getTop() + yMove, getRight(), getBottom() + yMove);
			}
			break;
		case MotionEvent.ACTION_UP:
			layout(getLeft(), getTop() - yDistance, getRight(), getBottom() - yDistance);
			yDistance = 0;
			break;
		}
		return true;
	}
}
