package com.example.customview.Util;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.support.v7.widget.RecyclerView.State;
import android.view.View;

public class SpaceItemDecoration extends ItemDecoration {

	private int mSpace;
	public SpaceItemDecoration(int space) {
		// TODO Auto-generated constructor stub
		mSpace = space;
	}
	
	@Override
	public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
		// TODO Auto-generated method stub
		int pos = parent.getChildAdapterPosition(view);
		outRect.left = mSpace;
        outRect.top = 0;
        outRect.bottom = mSpace;
        outRect.right = mSpace;
	}
}
