package com.example.customview.activity;

import com.example.customview.R;
import com.example.customview.Adapter.AnimationAdapter;
import com.example.customview.Adapter.AnimationAdapter.OnImageClickListener;

import android.R.raw;
import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.Activity;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class AnimationViewActivity extends Activity {

	private RecyclerView mAnimationRecycle;
	private RelativeLayout mRelativeLayout;
	private ImageView mEndImage;
	public static int[] IMAGE_ARRAY = {R.drawable.kettle, R.drawable.cake,R.drawable.coffee, R.drawable.milk, 
			R.drawable.mobile, R.drawable.kettle, R.drawable.cake};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.animation_view_layout);
		mRelativeLayout = (RelativeLayout)findViewById(R.id.animation_ll);
		mEndImage = (ImageView)findViewById(R.id.animation_image);
		mAnimationRecycle = (RecyclerView)findViewById(R.id.animation_recycle);
		AnimationAdapter animationAdapter = new AnimationAdapter(this);
		mAnimationRecycle.setAdapter(animationAdapter);
		LinearLayoutManager layoutManager = new LinearLayoutManager(this);
		layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		mAnimationRecycle.setLayoutManager(layoutManager);
		
		animationAdapter.setOnImageClickListener(new OnImageClickListener() {
			
			@Override
			public void onImageClick(View view, int position) {
				// TODO Auto-generated method stub
				addCart(view, position);
			}
		});
	}
	
	private void addCart(View view, int position) {
		final float[] currentPosition = new float[2];
		
		int[] parent = new int[2];
		mRelativeLayout.getLocationInWindow(parent);
		Log.i("tag", "parent[0] = " + parent[0] + " parent[1]" + parent[1]);
		
		int[] startLoc = new int[2];
		view.getLocationInWindow(startLoc);
		Log.i("tag", "startLoc[0] = " + startLoc[0] + " startLoc[1]" + startLoc[1]);
		int[] endLoc = new int[2];
		mEndImage.getLocationInWindow(endLoc);
		Log.i("tag", "endLoc[0] = " + endLoc[0] + " endLoc[1] = " + endLoc[1]);
		
		final ImageView good = new ImageView(this);
		good.setImageResource(IMAGE_ARRAY[position]);
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(100, 100);
		mRelativeLayout.addView(good, params);
		
		float startX = startLoc[0];
        float startY = startLoc[1] - parent[1];
        float toX = endLoc[0] + mEndImage.getWidth() / 3;
        float toY = endLoc[1];
        
		Path path = new Path();
		path.moveTo(startLoc[0], startLoc[1]);
		path.quadTo((startX + toX) / 2, startY, toX, toY);
		final PathMeasure pathMeasure = new PathMeasure(path, false);
		
		ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, pathMeasure.getLength());
		valueAnimator.setDuration(1000);
		valueAnimator.addUpdateListener(new AnimatorUpdateListener() {
			
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				// TODO Auto-generated method stub
				float value = (Float) animation.getAnimatedValue();
				pathMeasure.getPosTan(value, currentPosition, null);
				good.setTranslationX(currentPosition[0]);
				good.setTranslationY(currentPosition[1]);
			}
		});
		
		valueAnimator.start();
		valueAnimator.addListener(new AnimatorListener() {
			
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
				mRelativeLayout.removeView(good);
			}
			
			@Override
			public void onAnimationCancel(Animator animation) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}
//	private TextView mTextView;
//	private Button mButton;
//		mTextView = (TextView)findViewById(R.id.animation_view_text);
//		mButton = (Button)findViewById(R.id.animation_view_button);
//		mButton.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				float fromAlpha = 1.0f;
//				float toAlpha = 0.0f;
//				Animation animation = new AlphaAnimation(fromAlpha, toAlpha);
//				animation.setDuration(3000);

//				Animation animation = AnimationUtils.loadAnimation(AnimationViewActivity.this, R.anim.alpha);

//				int fromXDelta = 0;
//				int toXDelta = getResources().getDisplayMetrics().widthPixels/2;
//				int fromYDelta = 0;
//				int toYDelta = getResources().getDisplayMetrics().heightPixels/2;
//				Animation animation = new TranslateAnimation(fromXDelta, toXDelta, fromYDelta, toYDelta);
//				animation.setDuration(3000);
//				AccelerateDecelerateInterpolator interpolator = new AccelerateDecelerateInterpolator();
//				animation.setInterpolator(interpolator);

//				Animation animation = new RotateAnimation(0, 60);
//				animation.setDuration(3000);

//				float fromX = 2.0f;
//				float toX = 1.0f;
//				float fromY = 1.0f;
//				float toY = 2.0f;
//				Animation animation = new ScaleAnimation(fromX, toX, fromY, toY);
//				animation.setDuration(3000);
//				mTextView.startAnimation(animation);
//			}
//		}
//	);
