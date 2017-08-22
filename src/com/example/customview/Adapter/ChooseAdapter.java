package com.example.customview.Adapter;

import java.util.Collections;

import com.example.customview.R;
import com.example.customview.Util.onMoveAndSwipedListener;
import com.example.customview.fragment.TableLayoutFragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class ChooseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements onMoveAndSwipedListener{

	private Context mContext;
	
	public ChooseAdapter(Context context) {
		// TODO Auto-generated constructor stub
		mContext = context;
	}
	
	@Override
	public boolean onItemMove(int fromPosition, int toPosition) {
		// TODO Auto-generated method stub
		//交换mItems数据的位置
        Collections.swap(TableLayoutFragment.choseTabs, fromPosition, toPosition);
        //交换RecyclerView列表中item的位置
        notifyItemMoved(fromPosition, toPosition);
		return true;
	}

	@Override
	public boolean onItemDismiss(int position) {
		// TODO Auto-generated method stub
		TableLayoutFragment.choseTabs.remove(position);
		notifyItemRemoved(position);
		return true;
	}

	@Override
	public int getItemCount() {
		// TODO Auto-generated method stub
		return TableLayoutFragment.choseTabs.size();
	}

	@Override
	public void onBindViewHolder(ViewHolder viewHolder, final int position) {
		// TODO Auto-generated method stub
		ChooseViewHolder chooseViewHolder = (ChooseViewHolder)viewHolder;
		chooseViewHolder.mTextView.setText(TableLayoutFragment.choseTabs.get(position));
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int arg1) {
		// TODO Auto-generated method stub
		View view = LayoutInflater.from(mContext).inflate(R.layout.deleted_item_layout, parent, false);
		ChooseViewHolder chooseViewHolder = new ChooseViewHolder(view);
		return chooseViewHolder;
	}

	private static class ChooseViewHolder extends RecyclerView.ViewHolder {

		private TextView mTextView;
		public ChooseViewHolder(View itemView) {
			super(itemView);
			// TODO Auto-generated constructor stub
			mTextView = (TextView)itemView.findViewById(R.id.deleted_item_text);
		}
	}
	
}
