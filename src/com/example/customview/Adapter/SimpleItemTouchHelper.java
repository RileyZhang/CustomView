package com.example.customview.Adapter;

import com.example.customview.Util.onMoveAndSwipedListener;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

public class SimpleItemTouchHelper extends ItemTouchHelper.Callback{

	private onMoveAndSwipedListener mOnMoveAndSwipedListener;
	
	public SimpleItemTouchHelper(onMoveAndSwipedListener listener) {
		// TODO Auto-generated constructor stub
		mOnMoveAndSwipedListener = listener;
	}

	@Override
	public boolean isItemViewSwipeEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	/**
     * 这个方法是用来设置我们拖动的方向以及侧滑的方向的
     */
	@Override
	public int getMovementFlags(RecyclerView arg0, ViewHolder arg1) {
		// TODO Auto-generated method stub
		final int moveFlag = ItemTouchHelper.DOWN | ItemTouchHelper.UP ;
		final int swipFlag = ItemTouchHelper.START | ItemTouchHelper.END;
		return makeMovementFlags(moveFlag, swipFlag);
	}

	/**
     * 当我们拖动item时会回调此方法
     */
	@Override
	public boolean onMove(RecyclerView arg0, ViewHolder viewHolder, ViewHolder target) {
		// TODO Auto-generated method stub
		if (viewHolder.getItemViewType() != target.getItemViewType()) {
			return false;
		}
		Log.i("callback", "viewHolder.getAdapterPosition() = " + viewHolder.getAdapterPosition() + " target.getAdapterPosition() = " + target.getAdapterPosition());
		mOnMoveAndSwipedListener.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
		return true;
	}

	/**
     * 当我们侧滑item时会回调此方法
     */
	@Override
	public void onSwiped(ViewHolder arg0, int arg1) {
		// TODO Auto-generated method stub
		Log.i("callback", "arg1 = " + arg1);
	}
	
	@Override
	public void onSelectedChanged(ViewHolder viewHolder, int actionState) {
		// TODO Auto-generated method stub
		if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
			viewHolder.itemView.setBackgroundColor(Color.LTGRAY);;
		}
		super.onSelectedChanged(viewHolder, actionState);
	}
	
	@Override
	public void clearView(RecyclerView recyclerView, ViewHolder viewHolder) {
		// TODO Auto-generated method stub
		viewHolder.itemView.setBackgroundColor(0);
		super.clearView(recyclerView, viewHolder);
	}
}
