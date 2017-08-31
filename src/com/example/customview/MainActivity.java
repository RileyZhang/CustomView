package com.example.customview;

import java.util.ArrayList;
import java.util.List;

import com.example.customview.Adapter.MainAdapter;
import com.example.customview.activity.AnimationViewActivity;
import com.example.customview.activity.CallBackActivity;
import com.example.customview.activity.ChartActivity;
import com.example.customview.activity.ConflictActivity;
import com.example.customview.activity.CustomDrawableActivity;
import com.example.customview.activity.FlowActivity;
import com.example.customview.activity.QQHealthActivity;
import com.example.customview.activity.WangYiActivity;
import com.example.customview.activity.WeiBoYunDongJiFenActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.MenuItemCompat.OnActionExpandListener;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

	private ListView mListView;
	private MainAdapter mMainAdapter;
	private DrawerLayout mDrawerLayout;
	private String[] stringArray = {"自定义view的回调步骤", "微博运动几分自定义view", "QQ健康view",
			"滑动刻度尺view", "柱状图view","滑动冲突view", "MaterialDesign view", "Animation view",
			"custom drawable"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
		NavigationView navigationView = (NavigationView)findViewById(R.id.navigation_view);
//		NavigationView navigationView2 = (NavigationView)findViewById(R.id.navigation_view_2);
		setupDrawerContent(navigationView);
//		setupDrawerContent(navigationView2);
		
		Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
//		toolbar.setNavigationIcon(R.drawable.back_pressed);
		ActionBar ab = getSupportActionBar();
		ab.setDisplayHomeAsUpEnabled(true);
		
		
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
				getItem(1,2,3,4,5,6);
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
					break;
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
				case 6:
					Intent intent7 = new Intent(MainActivity.this, WangYiActivity.class);
					startActivity(intent7);
					break;
				case 7:
					Intent intent8 = new Intent(MainActivity.this, AnimationViewActivity.class);
					startActivity(intent8);
					break;
				case 8:
					Intent intent9 = new Intent(MainActivity.this, CustomDrawableActivity.class);
					startActivity(intent9);
					break;
				default:
					break;
				}
			}
		});
	}
	
	private void getItem(int... value) {
		Log.i("riley", "value.length = " + value.length);
	}
	
	private void setupDrawerContent(NavigationView navigationView)
    {
        navigationView.setNavigationItemSelectedListener(

                new NavigationView.OnNavigationItemSelectedListener()
                {

                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem)
                    {
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.menu_main, menu);
		
		MenuItem searchItem = menu.findItem(R.id.action_search);
		OnActionExpandListener expandListener = new OnActionExpandListener() {
			
			@Override
			public boolean onMenuItemActionExpand(MenuItem arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "张开", Toast.LENGTH_SHORT).show();
				return true;
			}
			
			@Override
			public boolean onMenuItemActionCollapse(MenuItem arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "隐藏", Toast.LENGTH_SHORT).show();
				return true;
			}
		};
		MenuItemCompat.setOnActionExpandListener(searchItem, expandListener);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()) {
		case R.id.action_search:
			Toast.makeText(MainActivity.this, "搜索", Toast.LENGTH_SHORT).show();
			break;
		case R.id.action_add:
			Toast.makeText(MainActivity.this, "添加", Toast.LENGTH_SHORT).show();
			break;
		case R.id.action_setting:
			Toast.makeText(MainActivity.this, "overflow", Toast.LENGTH_SHORT).show();
			break;
		case android.R.id.home:
			Toast.makeText(MainActivity.this, "home", Toast.LENGTH_SHORT).show();
			mDrawerLayout.openDrawer(Gravity.LEFT);
			break;
		}
		return true;
	}
}
