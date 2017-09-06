package com.example.customview.testAidl;

import com.example.customview.R;
import com.example.customview.Util.ComputerEntity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class CalculateClient extends Activity implements OnClickListener{
	
	private Button bindButton;
	private Button unBindButton;
	private Button calculateButton;
	private Button addComputerButton;
	private Button rectButton;
	private Button addButton;
	private Button registerButton;
	private Button unregisterButton;
	private ICalculate mCalculate;
	private IBinderPool mBinderPool;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.aidl_calculate_layout);
		bindButton = (Button)findViewById(R.id.bind_server);
		unBindButton = (Button)findViewById(R.id.unbind_server);
		calculateButton = (Button)findViewById(R.id.calculate);
		addComputerButton = (Button)findViewById(R.id.add_computer);
		registerButton = (Button)findViewById(R.id.register);
		unregisterButton = (Button)findViewById(R.id.unregister);
		rectButton = (Button)findViewById(R.id.rect);
		addButton = (Button)findViewById(R.id.add);
		bindButton.setOnClickListener(this);
		unBindButton.setOnClickListener(this);
		calculateButton.setOnClickListener(this);
		addComputerButton.setOnClickListener(this);
		registerButton.setOnClickListener(this);
		unregisterButton.setOnClickListener(this);
		rectButton.setOnClickListener(this);
		addButton.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bind_server:
			bindServer();
			bindBinderPoolServer();
			break;
		case R.id.unbind_server:
			unBindServer();
			break;
		case R.id.calculate:
			calculate();
			break;
		case R.id.add_computer:
			addComputer();
			break;
		case R.id.rect:
			rect();
			break;
		case R.id.add:
			add();
			break;
		case R.id.register:
			registerListener();
			break;
		case R.id.unregister:
			unregisterListener();
			break;
		default:
			break;
		}
	}
	
	private ServiceConnection mServiceConnection = new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			Log.i("aidl", "onServiceDisconnected");
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			Log.i("aidl", "onServiceConnected");
			mCalculate = ICalculate.Stub.asInterface(service);
		}
	};
	
	private ServiceConnection mBinderPoolServiceConnection = new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			mBinderPool = IBinderPool.Stub.asInterface(service);
		}
	};
	
	private void bindServer() {
		Log.i("aidl", "bindServer");
		Intent intent = new Intent();
		intent.setAction("com.example.customview.testAidl.CalculateServer");
		intent.setPackage(getPackageName());
		bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
	}
	
	private void bindBinderPoolServer() {
		Intent intent = new Intent(this, BinderPoolService.class);
		bindService(intent, mBinderPoolServiceConnection, Context.BIND_AUTO_CREATE);
	};
	
	private void unBindServer() {
		unbindService(mServiceConnection);
	}
	
	private void rect() {
		try {
			IRect binder = IRect.Stub.asInterface(mBinderPool.queryBinder(1));
			binder.area(5, 6);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void add() {
		try {
			IAdd binder = IAdd.Stub.asInterface(mBinderPool.queryBinder(2));
			binder.multiply(5, 6);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void calculate() {
		try {
			mCalculate.add(2, 4);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void addComputer() {
		ComputerEntity computer = new ComputerEntity(3, "lenover", "530");
		try {
			mCalculate.addComputer(computer);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	IOnComputerArrivedListener mOnComputerArrivedListener = new IOnComputerArrivedListener.Stub() {
		
		@Override
		public void computerArrivedListener(ComputerEntity computer) throws RemoteException {
			// TODO Auto-generated method stub
			Log.i("aidl", "computerArrivedListener computerId = " + computer.mComputerId);
			
		}
	};
	
	private void registerListener() {
		try {
			mCalculate.registerOnComputerArrivedListener(mOnComputerArrivedListener);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void unregisterListener() {
		try {
			mCalculate.unRegisterOnComputerArrivedListener(mOnComputerArrivedListener);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Intent intent = new Intent();
		intent.setAction("com.example.customview.testAidl.CalculateServer");
		intent.setPackage(getPackageName());
		stopService(intent);
	}
}
