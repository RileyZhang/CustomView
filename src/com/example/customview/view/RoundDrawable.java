package com.example.customview.view;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.graphics.Shader;

public class RoundDrawable extends Drawable {

	private Paint mPaint;
	private Bitmap mBitmap;
	
	public RoundDrawable(Bitmap bitmap) {
		// TODO Auto-generated constructor stub
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mBitmap = bitmap;
		
		BitmapShader bitmapShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
		mPaint.setShader(bitmapShader);
	}
	
	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		canvas.drawRoundRect(0, 0, 500, 500, 50, 50, mPaint);
//		canvas.drawRect(0, 0, 500, 500, mPaint);
	}

	@Override
	public void setAlpha(int alpha) {
		// TODO Auto-generated method stub
		mPaint.setAlpha(alpha);
	}

	@Override
	public void setColorFilter(ColorFilter colorFilter) {
		// TODO Auto-generated method stub
		mPaint.setColorFilter(colorFilter);
	}

	@Override
	public int getOpacity() {
		// TODO Auto-generated method stub
		return  PixelFormat.TRANSLUCENT;
	}

}
