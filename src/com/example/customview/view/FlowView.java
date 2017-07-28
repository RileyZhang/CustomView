package com.example.customview.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

public class FlowView extends ViewGroup {

	public FlowView(Context context) {
		this(context, null);
		// TODO Auto-generated constructor stub
	}

	public FlowView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
		// TODO Auto-generated constructor stub
	}

	public FlowView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	@Override
	public LayoutParams generateLayoutParams(AttributeSet attrs) {
		// TODO Auto-generated method stub
		return new MarginLayoutParams(getContext(), attrs);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);
		
		for (int i = 0; i < getChildCount(); i++) {
			View childView = getChildAt(i);
			measureChild(childView, widthMeasureSpec, heightMeasureSpec);
		}
		setMeasuredDimension(widthSize, heightSize);
	}
	
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		int left = 0;
		int top = 0;
		for (int i = 0; i < getChildCount(); i++) {
			View childView = getChildAt(i);
			MarginLayoutParams params = (MarginLayoutParams) childView.getLayoutParams();
			int lc = left + params.leftMargin;
			int rc = childView.getMeasuredWidth() + lc;
			int tc = top + params.topMargin;
			int bc = childView.getMeasuredHeight() + tc;
			childView.layout(lc, tc, rc, bc);
			
			if ((rc + childView.getMeasuredWidth()) > getMeasuredWidth()) {
				left = 0;
				top = bc;
			} else {
				left = rc;
			}
			
		}
	}

}
