package com.example.customview.Adapter;

import java.util.ArrayList;
import java.util.List;

import com.example.customview.R;
import com.example.customview.Util.GlobalConstant;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MainAdapter extends BaseAdapter{

	private Context mContext;
	private LayoutInflater mLayoutInflater;
	private List<String> mItemList = new ArrayList<String>();
	public MainAdapter() {
		// TODO Auto-generated constructor stub
		mContext = GlobalConstant.getGlobalContext();
		mLayoutInflater = LayoutInflater.from(mContext);
	}
	
	public void setmItemList(List<String> itemList) {
		this.mItemList = itemList;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mItemList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mItemList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		MainViewHolder viewHolder = new MainViewHolder();
		if (convertView == null) {
			convertView = mLayoutInflater.inflate(R.layout.main_adapter_layout, parent, false);
			viewHolder.mTextView = (TextView) convertView.findViewById(R.id.tv_main_adapter);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (MainViewHolder)convertView.getTag();
		}
		viewHolder.mTextView.setText(mItemList.get(position));
		return convertView;
	}

	private class MainViewHolder {
		public TextView mTextView;
	}
}
