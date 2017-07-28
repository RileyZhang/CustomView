package com.example.customview.Util;

import android.content.Context;

public class GlobalConstant {

	private static Context mContext;
	public static void setGlobalContext(Context context) {
		mContext = context;
	}
	
	public static Context getGlobalContext() {
		return mContext;
	}
}
