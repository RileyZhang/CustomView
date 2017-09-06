package com.example.customview.activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

import com.example.customview.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SocketActivity extends Activity {

	private static final int UPDATE_TEXT_VIEW = 100;
	private MyHandler mHandler = new MyHandler(this);
	private TextView mTextView;
	private Button mButton;
	private EditText mEditText;
	private String buffer = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.socket_layout);
		mEditText = (EditText)findViewById(R.id.socket_edit);
		mButton = (Button)findViewById(R.id.socket_button);
		mTextView = (TextView)findViewById(R.id.socket_textview);
		
		mButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new MyThread(mEditText.getText().toString()).start();
			}
		});
	}
	
	private class MyThread extends Thread{
		
		public MyThread(String sendStr) {
			// TODO Auto-generated constructor stub
			
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
//			super.run();
			Log.i("riley", "MyThread run");
			Message msg = new Message();  
            msg.what = UPDATE_TEXT_VIEW;
			Bundle bundle = new Bundle();
			bundle.clear();
			try {
				Socket socket = new Socket();//"192.168.69.129", 10089
				socket.connect(new InetSocketAddress("192.168.69.129", 10089));
				
				Log.i("riley", "socket.isConnected = " + socket.isConnected());
				OutputStream ots = socket.getOutputStream();
				Log.i("riley", "socket.getOutputStream() time = " + System.currentTimeMillis());
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String line = null;
				
				while ((line = bufferedReader.readLine()) != null) {
					Log.i("riley", "line = " + line);
					buffer = line + buffer;
				}
				
				ots.write("android客户端".getBytes("gbk"));
				ots.flush();
				Log.i("riley", "ots time = " + System.currentTimeMillis());
				bundle.putString("msg", buffer.toString());
				msg.setData(bundle);
				mHandler.sendMessage(msg);
				bufferedReader.close();
				ots.close();
				socket.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			catch (SocketTimeoutException e) {
//				// TODO: handle exception
//				bundle.putString("msg", "服务器链接失败，请检查网络是否打开");
//				msg.setData(bundle);
//				mHandler.sendMessage(msg);
//			}
			
		}
	}
	
	private static class MyHandler extends Handler{
		WeakReference<SocketActivity> mSocketActivity;
		
		public MyHandler(SocketActivity socketActivity) {
			// TODO Auto-generated constructor stub
			mSocketActivity = new WeakReference<SocketActivity>(socketActivity);
		}
		
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			SocketActivity socketActivity = mSocketActivity.get();
			switch (msg.what) {
			case UPDATE_TEXT_VIEW:
				Bundle message = msg.getData();
				socketActivity.mTextView.setText(message.getString("msg"));
				break;

			default:
				break;
			}
		}
	}
}
