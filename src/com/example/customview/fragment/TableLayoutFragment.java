package com.example.customview.fragment;

import java.util.ArrayList;
import java.util.List;

import com.example.customview.R;
import com.example.customview.Adapter.AllAdapter;
import com.example.customview.Adapter.AllAdapter.OnAllListener;
import com.example.customview.Adapter.ChooseAdapter;
import com.example.customview.Adapter.SimpleItemTouchHelper;
import com.example.customview.Adapter.TabAdapter;
import com.example.customview.Adapter.WangyiAdapter;
import com.example.customview.Util.ChooseItemTouchHelper;
import com.example.customview.Util.SpaceItemDecoration;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.Space;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TableLayoutFragment extends Fragment {

	private static final String TABLE_LAYOUT_FRAGMENT = "table_layout_fragment";
	public static final int RECYCLE_FRAGMENT = 100;
	public static final int DELETE_FRAGMENT = 101;
	private int type;
	private RecyclerView mRecycleView;
	private WangyiAdapter mAdapter;
	private LinearLayout mDeleteLl;
	private RecyclerView all;
	private AllAdapter allAdapter;
	private RecyclerView choosen;
	private ChooseAdapter chooseAdapter;
	private int mPosition;
	public static List<String> choseTabs = new ArrayList<String>();
	public static List<String> allTabs = new ArrayList<String>();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		if(getArguments() != null) {
			type = getArguments().getInt(TABLE_LAYOUT_FRAGMENT);
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_tablelayout, container, false);
		if (type == DELETE_FRAGMENT) {
			view = inflater.inflate(R.layout.delete_fragment_layout, container, false);
		}
		initView(view);
		return view;
		
	}
	
	private void initView(View view) {
		Log.i("TABLE_LAYOUT_FRAGMENT", "initView()");
		TextView recycleText = (TextView)view.findViewById(R.id.recycle_text);
		View lineView = (View)view.findViewById(R.id.parting_line);
		mRecycleView = (RecyclerView)view.findViewById(R.id.recycle_view);
		TextView tabText = (TextView)view.findViewById(R.id.table_text);
		if (type != DELETE_FRAGMENT) {
			recycleText.setVisibility(View.GONE);
			lineView.setVisibility(View.GONE);
			mRecycleView.setVisibility(View.GONE);
			tabText.setVisibility(View.VISIBLE);
		}
		Log.i("TABLE_LAYOUT_FRAGMENT", "initView()2222222 type = " + type);
		
		switch(type) {
		case 1:
			tabText.setText("这是综艺Fragment");
			break;
		case 2:
			tabText.setText("这是体育Fragment");
            break;
        case 3:
        	tabText.setText("这是新闻Fragment");
            break;
        case 4:
        	tabText.setText("这是热点Fragment");
            break;
        case 5:
        	tabText.setText("这是头条Fragment");
            break;
        case 6:
        	tabText.setText("这是军事Fragment");
            break;
        case 7:
        	tabText.setText("这是历史Fragment");
            break;
        case 8:
        	tabText.setText("这是科技Fragment");
            break;
        case 9:
        	tabText.setText("这是人文Fragment");
            break;
        case 10:
        	tabText.setText("这是地理Fragment");
            break;
        case RECYCLE_FRAGMENT:
        	tabText.setVisibility(View.GONE);
        	recycleText.setVisibility(View.VISIBLE);
        	lineView.setVisibility(View.VISIBLE);
        	mRecycleView.setVisibility(View.VISIBLE);
        	initRecyclerView();
        	break;
        case DELETE_FRAGMENT:
        	initDeleteData();
        	initDeleteRecyclerView(view);
        	break;
    	default:
    		break;
		}
		Log.i("TABLE_LAYOUT_FRAGMENT", "initView()3333333 type = " + type);
	}
	
	private void initDeleteData() {
		choseTabs.add("头条");
        choseTabs.add("科技");
        choseTabs.add("热点");
        choseTabs.add("政务");
        choseTabs.add("移动互联");
        choseTabs.add("军事");
        choseTabs.add("历史");
        choseTabs.add("社会");
        choseTabs.add("财经");
        choseTabs.add("娱乐");


        allTabs.add("体育");
        allTabs.add("时尚");
        allTabs.add("房产");
        allTabs.add("论坛");
        allTabs.add("博客");
        allTabs.add("健康");
        allTabs.add("轻松一刻");
        allTabs.add("直播");
        allTabs.add("段子");
        allTabs.add("彩票");
        allTabs.add("直播");
        allTabs.add("段子");
        allTabs.add("彩票");
        allTabs.add("直播");
        allTabs.add("段子");
        allTabs.add("彩票");
        allTabs.add("轻松一刻");
        allTabs.add("直播");
        allTabs.add("段子");
        allTabs.add("彩票");
        allTabs.add("直播");
        allTabs.add("段子");
        allTabs.add("彩票");
        allTabs.add("直播");
        allTabs.add("段子");
        allTabs.add("彩票");
	}
	
	private void initDeleteRecyclerView(View view) {
		mDeleteLl = (LinearLayout)view.findViewById(R.id.delete_item_ll);
		
		choosen = (RecyclerView)view.findViewById(R.id.choosen_recycler);
		choosen.setLayoutManager(new GridLayoutManager(getActivity(), 4));
		chooseAdapter = new ChooseAdapter(getActivity());
		choosen.setAdapter(chooseAdapter);
		choosen.addItemDecoration(new SpaceItemDecoration(15));
		
		ChooseItemTouchHelper chooseItemTouchHelper = new ChooseItemTouchHelper(chooseAdapter);
		ItemTouchHelper itemTouchHelper = new ItemTouchHelper(chooseItemTouchHelper);
		itemTouchHelper.attachToRecyclerView(choosen);
		
		all = (RecyclerView)view.findViewById(R.id.all_recycler);
		all.setLayoutManager(new GridLayoutManager(getActivity(), 4));
		allAdapter = new AllAdapter(getActivity());
		all.setAdapter(allAdapter);
		all.addItemDecoration(new SpaceItemDecoration(15));
		allAdapter.setOnAllListener(new OnAllListener() {
			
			@Override
			public void allTabsItemClick(View view, int position) {
				// TODO Auto-generated method stub
				allItemClick(view);
				mPosition = position;
			}
		});
	}
	
	private void allItemClick(View view) {
		final PathMeasure mPathMeasure;
		final float[] mCurrentPosition = new float[2];
		int parentLoc[] = new int[2];
		mDeleteLl.getLocationInWindow(parentLoc);
		
		int startLoc[] = new int[2];
		view.getLocationInWindow(startLoc);
		
		final View startView = view;
		startView.setLayoutParams(new RelativeLayout.LayoutParams
				(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
		all.removeView(view);
		mDeleteLl.addView(startView);
		
		final View endView;
		float toX, toY;
		int endLoc[] = new int[2];
		int i = choseTabs.size();
		
		if (i == 0) {
			toX = view.getWidth();
			toY = view.getHeight();
		} else if (i % 4 == 0){
			endView = choosen.getChildAt(i - 4);
			endView.getLocationInWindow(endLoc);
			toX = endLoc[0] - parentLoc[0];
			toY = endLoc[1] + startView.getHeight() - parentLoc[1];
		} else {
			endView = choosen.getChildAt(i - 1);
			endView.getLocationInWindow(endLoc);
			toX = endLoc[0] + startView.getWidth() - parentLoc[0];
			toY = endLoc[1] - parentLoc[1];
		}
		
		float startX = startLoc[0] - parentLoc[0];
		float startY = startLoc[1] - parentLoc[1];
		
		Path path = new Path();
		path.moveTo(startX, startY);
		path.lineTo(toX, toY);
		mPathMeasure = new PathMeasure(path,false);
		
		ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, mPathMeasure.getLength());
		valueAnimator.setDuration(5000);
		valueAnimator.setInterpolator(new LinearInterpolator());
		valueAnimator.addUpdateListener(new AnimatorUpdateListener() {
			
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				// TODO Auto-generated method stub
				float value = (Float)animation.getAnimatedValue();
				mPathMeasure.getPosTan(value, mCurrentPosition, null);
				startView.setX(mCurrentPosition[0]);
				startView.setY(mCurrentPosition[1]);
			}
		});
		valueAnimator.start();
		valueAnimator.addListener(new AnimatorListener() {
			
			@Override
			public void onAnimationStart(Animator animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animator animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animator animation) {
				// TODO Auto-generated method stub
				all.setItemAnimator(new DefaultItemAnimator());
				choosen.setItemAnimator(new DefaultItemAnimator());
				choseTabs.add(choseTabs.size(), allTabs.get(mPosition));
				allTabs.remove(mPosition);
				
				chooseAdapter.notifyDataSetChanged();
				allAdapter.notifyDataSetChanged();
				
				allAdapter.notifyItemRemoved(mPosition);
				chooseAdapter.notifyItemInserted(mPosition);
				
				mDeleteLl.removeView(startView);
			}
			
			@Override
			public void onAnimationCancel(Animator animation) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	private void initRecyclerView() {
		LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
		layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
		mRecycleView.setLayoutManager(layoutManager);
		mAdapter = new WangyiAdapter(getActivity());
		mRecycleView.setAdapter(mAdapter);
		ItemTouchHelper.Callback callback = new SimpleItemTouchHelper(mAdapter);
		ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
		itemTouchHelper.attachToRecyclerView(mRecycleView);
//		itemTouchHelper.startDrag(viewHolder);
	}
	
	public static TableLayoutFragment getInstance(int type) {
		TableLayoutFragment fragment = new TableLayoutFragment();
		Bundle bundle = new Bundle();
		bundle.putSerializable(TABLE_LAYOUT_FRAGMENT, type);
		fragment.setArguments(bundle);
		return fragment;
	}
}
