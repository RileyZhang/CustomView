package com.example.customview.Adapter;

import com.example.customview.R;
import com.example.customview.activity.AnimationViewActivity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

public class AnimationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

	private LayoutInflater mLayoutInflater;
	private OnImageClickListener mClickListener;
	
	public interface OnImageClickListener{
		public void onImageClick(View view, int position);
	}
	
	public void setOnImageClickListener(OnImageClickListener imageClickListener) {
		mClickListener = imageClickListener;
	}
	
	public AnimationAdapter(Context context) {
		// TODO Auto-generated constructor stub
		mLayoutInflater = LayoutInflater.from(context);
	}
	
	@Override
	public int getItemCount() {
		// TODO Auto-generated method stub
		return AnimationViewActivity.IMAGE_ARRAY.length;
	}

	@Override
	public void onBindViewHolder(ViewHolder viewHolder, final int position) {
		// TODO Auto-generated method stub
		AnimationViewHolder animationViewHolder = (AnimationViewHolder) viewHolder;
		animationViewHolder.mImageView.setImageResource(AnimationViewActivity.IMAGE_ARRAY[position]);
		animationViewHolder.mImageView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mClickListener.onImageClick(v, position);
			}
		});
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int position) {
		// TODO Auto-generated method stub
		View view = mLayoutInflater.inflate(R.layout.animation_item_layout, parent, false);
		AnimationViewHolder viewHolder = new AnimationViewHolder(view);
		return viewHolder;
	}

	private static class AnimationViewHolder extends ViewHolder {
		
		private ImageView mImageView;
		public AnimationViewHolder(View itemView) {
			super(itemView);
			// TODO Auto-generated constructor stub
			mImageView = (ImageView)itemView.findViewById(R.id.animation_item_image);
		}
		
	}
}
