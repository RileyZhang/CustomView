package com.example.customview.Adapter;

import java.util.Collections;

import com.example.customview.R;
import com.example.customview.Util.onMoveAndSwipedListener;
import com.example.customview.activity.WangYiActivity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class WangyiAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements onMoveAndSwipedListener{

	private Context mContext;
	private ItemClickListener mItemClickListener;
	
	public WangyiAdapter(Context context) {
		// TODO Auto-generated constructor stub
		mContext = context;
	}
	
	public interface ItemClickListener{
		public void onItemClick(int position);
	}
	
	public void setOnItemClickListener(ItemClickListener itemClickListener) {
		mItemClickListener = itemClickListener;
	}
	
	@Override
	public int getItemCount() {
		// TODO Auto-generated method stub
		return WangYiActivity.tabTitle.size();
	}

	@Override
	public void onBindViewHolder(ViewHolder viewHolder, final int position) {
		// TODO Auto-generated method stub
		TabsViewHolder tabsViewHolder = (TabsViewHolder)viewHolder;
		tabsViewHolder.mTableText.setText(WangYiActivity.tabTitle.get(position));
//		tabsViewHolder.mTableRl.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				mItemClickListener.onItemClick(position);
//			}
//		});
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parents, int position) {
		// TODO Auto-generated method stub
		View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_tablelayout2, parents, false);
		TabsViewHolder tabsViewHolder = new TabsViewHolder(view);
		return tabsViewHolder;
	}

	public class TabsViewHolder extends RecyclerView.ViewHolder {

		private TextView mTableText;
		private ImageView mTableImage;
		private RelativeLayout mTableRl;
		public TabsViewHolder(View itemView) {
			super(itemView);
			// TODO Auto-generated constructor stub
			mTableText = (TextView) itemView.findViewById(R.id.table_text);
			mTableImage = (ImageView)itemView.findViewById(R.id.table_image);
			mTableRl = (RelativeLayout) itemView.findViewById(R.id.table_rl);
		}
	}

	@Override
	public boolean onItemMove(int fromPosition, int toPosition) {
		// TODO Auto-generated method stub
		Collections.swap(WangYiActivity.tabTitle, fromPosition, toPosition);
		notifyItemMoved(fromPosition, toPosition);
		return true;
	}

	@Override
	public boolean onItemDismiss(int item) {
		// TODO Auto-generated method stub
		return false;
	}
}

