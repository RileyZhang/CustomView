package com.example.customview.activity;

import java.util.ArrayList;
import java.util.List;

import com.example.customview.R;
import com.example.customview.Util.GlobalConstant;
import com.example.customview.view.FlowView;
import com.example.customview.view.ScaleView;
import com.example.customview.view.ScaleView.GetNumberListener;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FlowActivity extends Activity {

	private TextView mTextView;
	private ScaleView mScaleView;
	private FlowView mFlowView;
	private List<String> stringList = new ArrayList<String>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.flow_view_layout);
		mScaleView = (ScaleView) findViewById(R.id.scale_view);
		mScaleView.setOnNumberListener(new GetNumberListener() {
			
			@Override
			public void getNumber(int number) {
				// TODO Auto-generated method stub
				mTextView.setText("选择的数字是：" + number);
			}
		});
		mTextView=(TextView) findViewById(R.id.scale_text_view);
		
		stringList.add("数据库");
        stringList.add("移动开发");
        stringList.add("前端开发");
        stringList.add("微信小程序");
        stringList.add("服务器开发");
        stringList.add("PHP");
        stringList.add("人工智能");
        stringList.add("大数据");
		mFlowView = (FlowView) findViewById(R.id.flow_view);
		for (String string:stringList) {
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
			params.setMargins(40, 40, 40, 40);
			TextView showText = new TextView(this);
			showText.setText(string);
			showText.setLayoutParams(params);
			showText.setTextColor(getResources().getColor(R.color.col_60b2d6));
			showText.setTextSize(20);
			showText.setBackground(getResources().getDrawable(R.drawable.voice_message_bg));
			mFlowView.addView(showText);
		}
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mScaleView.setStartWidth(0);
	}
}
