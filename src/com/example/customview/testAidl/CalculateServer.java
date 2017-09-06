package com.example.customview.testAidl;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.example.customview.Util.ComputerEntity;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;

public class CalculateServer extends Service {

	private static final String TAG = "aidl";
	private CopyOnWriteArrayList<ComputerEntity> mComputerEntityList = new CopyOnWriteArrayList<ComputerEntity>();
	private RemoteCallbackList<IOnComputerArrivedListener> callbackList = new RemoteCallbackList<IOnComputerArrivedListener>();
	private int index = 5;
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		Log.i(TAG, "server() onBind");
		return mBinder;
	}
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		Log.i(TAG, "server onCreate()");
		mComputerEntityList.add(new ComputerEntity(0,"apple","macbookpro"));
		mComputerEntityList.add(new ComputerEntity(1,"microsoft","surfacebook"));
		mComputerEntityList.add(new ComputerEntity(2,"dell","XPS13"));
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (index < 10) {
					try {
						Thread.sleep(30000);
						index++;
						Log.i(TAG, "index = " + index);
						ComputerEntity computer = new ComputerEntity(index, "###", "###");
						int count = callbackList.beginBroadcast();
						for (int i = 0; i < count; i++) {
							IOnComputerArrivedListener computerArrivedListener = callbackList.getBroadcastItem(i);
							computerArrivedListener.computerArrivedListener(computer);
						}
						callbackList.finishBroadcast();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();
		super.onCreate();
	}
	
	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		Log.i(TAG, "server onStart()");
		super.onStart(intent, startId);
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		Log.i(TAG, "server onStartCommand()");
		return super.onStartCommand(intent, flags, startId);
	}
	
	private Binder mBinder = new ICalculate.Stub() {
		
		@Override
		public int add(int first, int second) throws RemoteException {
			// TODO Auto-generated method stub
			int sum = first + second;
			Log.i(TAG, "sum = " + sum);
			return sum;
		}

		@Override
		public void addComputer(ComputerEntity computer) throws RemoteException {
			// TODO Auto-generated method stub
			Log.i(TAG, "addComputer computer = " + computer.mModel);
			mComputerEntityList.add(computer);
		}

		@Override
		public List<ComputerEntity> getComputerList() throws RemoteException {
			// TODO Auto-generated method stub
			Log.i(TAG, "addComputer mComputerEntityList.size() = " + mComputerEntityList.size());
			return mComputerEntityList;
		}

		@Override
		public void registerOnComputerArrivedListener(IOnComputerArrivedListener listener) throws RemoteException {
			// TODO Auto-generated method stub
			callbackList.register(listener);
		}

		@Override
		public void unRegisterOnComputerArrivedListener(IOnComputerArrivedListener listener) throws RemoteException {
			// TODO Auto-generated method stub
			callbackList.unregister(listener);
		}
		
		
	};
	
	public boolean onUnbind(Intent intent) {
		Log.i(TAG, "server onUnbind()");
		return true;
	};
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		Log.i(TAG, "server onDestroy()");
		super.onDestroy();
	}
}
