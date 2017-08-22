package com.example.customview.activity;


import java.util.ArrayList;
import java.util.List;

import com.example.customview.R;
import com.example.customview.Adapter.TabAdapter;
import com.example.customview.fragment.TableLayoutFragment;

import android.opengl.Visibility;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class WangYiActivity extends AppCompatActivity{

	private Toolbar mToolBar;
	private TabLayout mTabLayout;
	private ViewPager mViewPager;
	private TabAdapter mAdapter;
	private LinearLayout mTabsLl;
	private CheckBox mCheckBox;
	public static final List<String> tabTitle = new ArrayList<String>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_view);
		
		mToolBar = (Toolbar)findViewById(R.id.wang_yi_toolbar);
		setSupportActionBar(mToolBar);
		ActionBar ab = getSupportActionBar();
		ab.setDisplayHomeAsUpEnabled(true);
		
		mTabLayout = (TabLayout) findViewById(R.id.wang_yi_tablayout);
		mTabsLl = (LinearLayout) findViewById(R.id.tabs_ll);
		mViewPager = (ViewPager) findViewById(R.id.wang_yi_viewPage);
		initData();
		initView();
	}
	
	private void initData() {
		tabTitle.add("综艺");
		tabTitle.add("体育");
		tabTitle.add("新闻");
		tabTitle.add("热点");
		tabTitle.add("头条");
		tabTitle.add("军事");
		tabTitle.add("历史");
		tabTitle.add("科技");
		tabTitle.add("人文");
		tabTitle.add("地理");
	}
	
	private void initView() {
		List<Fragment> fragments = new ArrayList<Fragment>();
		for (int i = 0; i < tabTitle.size(); i++) {
			fragments.add(TableLayoutFragment.getInstance(i+1));
		}
		
		mAdapter = new TabAdapter(getSupportFragmentManager(), fragments);
		mViewPager.setAdapter(mAdapter);
		mTabLayout.setupWithViewPager(mViewPager);
		mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
		
		mCheckBox = (CheckBox) findViewById(R.id.check_box);
		mCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					mCheckBox.setBackgroundResource(R.drawable.up);
					mViewPager.setVisibility(View.GONE);
					mTabsLl.setVisibility(View.VISIBLE);
					getSupportFragmentManager().beginTransaction().replace(R.id.tabs_ll, TableLayoutFragment.getInstance(TableLayoutFragment.DELETE_FRAGMENT)).commit();
				} else {
					mCheckBox.setBackgroundResource(R.drawable.down);
					mTabsLl.setVisibility(View.GONE);
					mViewPager.setVisibility(View.VISIBLE);
				}
			}
		});
		
		//可以实现自定义tab的显示
//		for (int i = 0; i < mTabLayout.getTabCount(); i++) {
//			TabLayout.Tab tabLayout = mTabLayout.getTabAt(i);
//			tabLayout.setCustomView(layoutResId)
//		}
	}
}
