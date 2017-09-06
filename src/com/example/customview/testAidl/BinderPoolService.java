package com.example.customview.testAidl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class BinderPoolService extends Service{

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		Log.i("aidl", "BinderPoolService onBind");
		return mBinderPool;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		Log.i("aidl", "BinderPoolService onCreate");
		super.onCreate();
	}
	
	private Binder mBinderPool = new IBinderPool.Stub(){

		@Override
		public IBinder queryBinder(int requestCode) throws RemoteException {
			// TODO Auto-generated method stub
			Log.i("aidl", "request code = " + requestCode);
			Binder binder = null;
			switch (requestCode) {
			case 1:
				binder = new IRectBinder();
				break;
			case 2:
				binder = new IAddBinder();
				break;
			default:
				break;
			}
			return binder;
		}
		
	};
	
}
