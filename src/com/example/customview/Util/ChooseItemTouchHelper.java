package com.example.customview.Util;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.helper.ItemTouchHelper;

public class ChooseItemTouchHelper extends ItemTouchHelper.Callback {

	private onMoveAndSwipedListener moveAndSwipedListener;
	
	public ChooseItemTouchHelper(onMoveAndSwipedListener listener) {
		// TODO Auto-generated constructor stub
		moveAndSwipedListener = listener;
	}

	@Override
	public int getMovementFlags(RecyclerView arg0, ViewHolder arg1) {
		// TODO Auto-generated method stub
		final int dragFlag = ItemTouchHelper.DOWN | ItemTouchHelper.UP
				|ItemTouchHelper.LEFT |ItemTouchHelper.RIGHT;
		final int swipeFlag = ItemTouchHelper.START |ItemTouchHelper.END;
		return makeMovementFlags(dragFlag, swipeFlag);
	}

	@Override
	public boolean onMove(RecyclerView arg0, ViewHolder viewHolder, ViewHolder target) {
		// TODO Auto-generated method stub
		//如果两个item不是一个类型的，我们让他不可以拖拽
        if (viewHolder.getItemViewType() != target.getItemViewType()) {
            return false;
        }
        moveAndSwipedListener.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
		return true;
	}

	@Override
	public void onSwiped(ViewHolder viewHolder, int direction) {
		// TODO Auto-generated method stub
		moveAndSwipedListener.onItemDismiss(viewHolder.getAdapterPosition());
	}

	
}
