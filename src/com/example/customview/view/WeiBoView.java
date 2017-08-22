package com.example.customview.view;

import com.example.customview.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.EmbossMaskFilter;
import android.graphics.MaskFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.Xfermode;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

public class WeiBoView extends View {

	private final String TAG = WeiBoView.class.getSimpleName();
	private String text;
	private int textColor;
	private int textSize;
	private int inCircleColor;
	private int outCirclrColor;
	private Paint mPaint;
	private Paint mCirclePaint;
	private float mStartSweepValue;
	private float mCurrentAngle;
	private int mCurrentPercent, mTargetPercent;
	private Rect mBound;
	private RectF circleRect;
	
	public WeiBoView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		// TODO Auto-generated constructor stub
		Log.i(TAG, "WeiBoView() 4444444444444");
	}

	public WeiBoView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		Log.i(TAG, "WeiBoView() 33333333333333");
		TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.WeiBoView, defStyleAttr, 0);
		int n = array.getIndexCount();
		Log.i(TAG, "n = " + n);
		for(int i = 0 ; i < n; i++) {
			int attr = array.getIndex(i);
			Log.i(TAG, "attr = " + attr);
			switch(attr) {
			case R.styleable.WeiBoView_titleColor:
				textColor = array.getColor(attr, Color.WHITE);
				break;
			case R.styleable.WeiBoView_titleSize:
				textSize = array.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
				break;
			case R.styleable.WeiBoView_inCircleColor:
				inCircleColor = array.getColor(attr, Color.WHITE);
				break;
			case R.styleable.WeiBoView_outCircleColor:
				outCirclrColor = array.getColor(attr, Color.WHITE);
				break;
				default:
					break;
			}
		}
		Log.i(TAG, "textColor = " + textColor + " ");
		array.recycle();
		init();
	}

	public WeiBoView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
		// TODO Auto-generated constructor stub
		Log.i(TAG, "WeiBoView() 222222222222");
		
	}

	public WeiBoView(Context context) {
		this(context, null);
		// TODO Auto-generated constructor stub
		Log.i(TAG, "WeiBoView() 111111111");
	}

	private void init() {
		mPaint = new Paint();
		mCirclePaint = new Paint();
		mStartSweepValue = -90;
		mCurrentAngle = 0;
		mCurrentPercent = 0;
		mBound = new Rect();
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);
		int width;
		int height;
		
		if (widthMode == MeasureSpec.EXACTLY) {
			width = widthSize;
		} else {
			width = widthSize * 1/2;
		}
		
		if (heightMode == MeasureSpec.EXACTLY) {
			height = heightSize;
		} else {
			height = heightSize * 1/2;
		}
		
		setMeasuredDimension(width, height);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		Rect rect = new Rect(50, 50, 150, 150);
		Paint paint = new Paint();
		paint.setColor(Color.RED);
		paint.setMaskFilter(new EmbossMaskFilter(new float[]{20, 20, 20}, 0.4f, 10, 15));
		canvas.drawRect(rect, paint);
		
		Canvas mCanvas = new Canvas();
		mCanvas.save();
		
		mPaint.setColor(outCirclrColor);
		mPaint.setAntiAlias(true);
		mPaint.setStyle(Paint.Style.STROKE);
		canvas.drawCircle(getWidth()/2, getHeight()/2, getWidth()/2, mPaint);
		
		mPaint.setColor(textColor);
		mPaint.setTextSize(textSize);
		text = String.valueOf(mCurrentPercent);
		mPaint.getTextBounds(text, 0, text.length(), mBound);
		canvas.drawText(text, getWidth()/2, getHeight()/2, mPaint);
		
		mPaint.setTextSize(textSize/3);
		canvas.drawText("·Ö", getWidth() * 3/5, getHeight()/3, mPaint);
		
		mPaint.setTextSize(textSize/2);
		Typeface font = Typeface.create(Typeface.DEFAULT, Typeface.BOLD_ITALIC);
		mPaint.setTypeface(font);
		mPaint.setTextSkewX((float)0.5);
		mPaint.setFakeBoldText(true);
		Path path = new Path();
		String string = "Î¢²©½¡¿µ";
		mPaint.getTextPath(string, 0, string.length(), getWidth()*1/4, getHeight()*3/5, path);
		path.close();
		canvas.drawPath(path, mPaint);
//		canvas.drawText(string, getWidth()*1/4, getHeight()*3/5, mPaint);
		
		mCirclePaint.setAntiAlias(true);
		mCirclePaint.setStyle(Paint.Style.STROKE);
		mCirclePaint.setStrokeWidth(10);
		mCirclePaint.setColor(inCircleColor);
		circleRect = new RectF(20, 20, getWidth() - 20, getHeight() - 20);
		canvas.drawArc(circleRect, mStartSweepValue, mCurrentAngle, false, mCirclePaint);
		
		if(mCurrentPercent < mTargetPercent) {
			mCurrentPercent += 1;
			mCurrentAngle += 3.6;
			postInvalidateDelayed(1000);
		}
	}
	
	public void setNumber(int size) {
		mCurrentPercent = 0;
		mTargetPercent = size;
		mCurrentAngle = 0;
		postInvalidate();
	}
}
