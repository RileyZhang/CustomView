package com.example.customview.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.example.customview.R;
import com.example.customview.view.ChartView;
import com.example.customview.view.ChartView.GetNumberListener;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

public class ChartActivity extends Activity {

	private ChartView mChartView;
	private RelativeLayout mRelativeLayout;
	private TextView mTextView;
	private List<Integer> list = new ArrayList<Integer>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chart_view_layout);
		mChartView = (ChartView)findViewById(R.id.chart_view);
		mRelativeLayout = (RelativeLayout)findViewById(R.id.chart_rlayout);
		mTextView = new TextView(getApplicationContext());
		Random random = new Random();
        while (list.size() < 24) {
            int randomInt = random.nextInt(100);
            list.add(randomInt);
        }
		mChartView.setValueList(list);
		
		mChartView.setOnNumberListener(new GetNumberListener() {
			
			@Override
			public void getNumber(int number, int x, int y) {
				// TODO Auto-generated method stub
				mRelativeLayout.removeView(mTextView);
				RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
				params.leftMargin = x - 100;
				if (x - 100 < 0) {
                    params.leftMargin = 0;
                } else if (x - 100 > mRelativeLayout.getWidth() - mTextView.getWidth()) {
                    params.leftMargin = mRelativeLayout.getWidth() - mTextView.getWidth();
                }
				params.topMargin = 100;
				mTextView.setLayoutParams(params);
				mTextView.setTextColor(getResources().getColor(R.color.white));
				mTextView.setTextSize(10);
				mTextView.setText("选择的数字为:" + list.get(number * 2) + "," + list.get(number * 2 + 1));
				mTextView.setBackground(getResources().getDrawable(R.drawable.voice_message_bg));
				mRelativeLayout.addView(mTextView);
			}
		});
	}
}
