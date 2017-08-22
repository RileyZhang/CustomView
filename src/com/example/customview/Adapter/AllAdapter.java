package com.example.customview.Adapter;

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

public class AllAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

	private Context mContext;
	private OnAllListener mAllListener;
	
	public interface OnAllListener {
        void allTabsItemClick(View view, int position);
	}
	
	public void setOnAllListener(OnAllListener onAllListener) {
		mAllListener = onAllListener;
	}
	
	public AllAdapter(Context context) {
		// TODO Auto-generated constructor stub
		mContext = context;
	}

	@Override
	public int getItemCount() {
		// TODO Auto-generated method stub
		return TableLayoutFragment.allTabs.size();
	}

	@Override
	public void onBindViewHolder(ViewHolder viewHolder, final int position) {
		// TODO Auto-generated method stub
		AllViewHolder allViewHolder = (AllViewHolder)viewHolder;
		allViewHolder.mTextView.setText(TableLayoutFragment.allTabs.get(position));
		allViewHolder.mTextView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mAllListener.allTabsItemClick(v, position);
			}
		});
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int arg1) {
		// TODO Auto-generated method stub
		View view = LayoutInflater.from(mContext).inflate(R.layout.deleted_item_layout, parent, false);
		AllViewHolder viewHolder = new AllViewHolder(view);
		return viewHolder;
	}

	private static class AllViewHolder extends RecyclerView.ViewHolder {

		private TextView mTextView;
		public AllViewHolder(View itemView) {
			super(itemView);
			// TODO Auto-generated constructor stub
			mTextView = (TextView)itemView.findViewById(R.id.deleted_item_text);
		}
		
	}
}
