package com.example.customview.testAidl;

import android.os.RemoteException;
import android.util.Log;

public class IAddBinder extends IAdd.Stub{

	@Override
	public int add(int first, int second) throws RemoteException {
		// TODO Auto-generated method stub
		return first + second;
	}

	@Override
	public int multiply(int first, int second) throws RemoteException {
		// TODO Auto-generated method stub
		Log.i("aidl", "IAddBinder multiply");
		return first * second;
	}

}
