package com.example.customview.testAidl;

import android.os.RemoteException;
import android.util.Log;

public class IRectBinder extends IRect.Stub{

	@Override
	public int area(int length, int width) throws RemoteException {
		// TODO Auto-generated method stub
		Log.i("aidl", "IRectBinder area");
		return length * width;
	}

	@Override
	public int perimeter(int length, int width) throws RemoteException {
		// TODO Auto-generated method stub
		return 2 * (length + width);
	}

}
