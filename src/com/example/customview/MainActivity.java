package com.example.customview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import com.example.customview.Adapter.MainAdapter;
import com.example.customview.activity.CallBackActivity;
import com.example.customview.activity.ChartActivity;
import com.example.customview.activity.ConflictActivity;
import com.example.customview.activity.FlowActivity;
import com.example.customview.activity.QQHealthActivity;
import com.example.customview.activity.WeiBoYunDongJiFenActivity;

import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends Activity{

	private ListView mListView;
	private MainAdapter mMainAdapter;
	private String[] stringArray = {"自定义view的回调步骤", "微博运动几分自定义view", "QQ健康view", 
			"滑动刻度尺view", "柱状图view","滑动冲突view"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mListView = (ListView) findViewById(R.id.main_list_view);
		mMainAdapter = new MainAdapter();
		int count = stringArray.length;
		List<String> itemList = new ArrayList<String>();
		for (int i = 0; i < count; i++) {
			itemList.add(stringArray[i]);
		}
		mMainAdapter.setmItemList(itemList);
		mListView.setAdapter(mMainAdapter);
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				switch(position) {
				case 0:
					Intent intent = new Intent(MainActivity.this, CallBackActivity.class);
					startActivity(intent);
					break;
				case 1:
					Intent intent2 = new Intent(MainActivity.this, WeiBoYunDongJiFenActivity.class);
					startActivity(intent2);
					break;
				case 2:
					Intent intent3 = new Intent(MainActivity.this, QQHealthActivity.class);
					startActivity(intent3);
				case 3:
					Intent intent4 = new Intent(MainActivity.this, FlowActivity.class);
					startActivity(intent4);
					break;
				case 4:
					Intent intent5 = new Intent(MainActivity.this, ChartActivity.class);
					startActivity(intent5);
					break;
				case 5:
					Intent intent6 = new Intent(MainActivity.this, ConflictActivity.class);
					startActivity(intent6);
					break;
				default:
					break;
				}
			}
		});
	}
}
