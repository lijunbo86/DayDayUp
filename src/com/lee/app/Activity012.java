package com.lee.app;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Activity012 extends ActBase implements OnClickListener {
	
	private Button startService;

	private Button stopService;

	private Button bindService;

	private Button unbindService;

	private Button startIntentService;

	private ServiceFirst.DownloadBinder downloadBinder;

	private ServiceConnection connection = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {
			
		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			downloadBinder = (ServiceFirst.DownloadBinder) service;
			downloadBinder.startDownload();
			downloadBinder.getProgress();
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity012);
		
		startService = (Button) findViewById(R.id.start_service);
		stopService = (Button) findViewById(R.id.stop_service);
		bindService = (Button) findViewById(R.id.bind_service);
		unbindService = (Button) findViewById(R.id.unbind_service);
		startIntentService = (Button) findViewById(R.id.start_intent_service);
		
		startService.setOnClickListener(this);
		stopService.setOnClickListener(this);
		bindService.setOnClickListener(this);
		unbindService.setOnClickListener(this);
		startIntentService.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		
		case R.id.start_service:
			Intent startIntent = new Intent(this, ServiceFirst.class);
			startService(startIntent);
			break;
			
		case R.id.stop_service:
			Intent stopIntent = new Intent(this, ServiceFirst.class);
			stopService(stopIntent);
			break;
			
		case R.id.bind_service:
			Intent bindIntent = new Intent(this, ServiceFirst.class);
			bindService(bindIntent, connection, BIND_AUTO_CREATE);
			break;
			
		case R.id.unbind_service:
			unbindService(connection);
			break;
			
		case R.id.start_intent_service:
			Log.d("Activity012", "Thread id is " + Thread.currentThread().getId());
			Intent intentService = new Intent(this, ServiceIntent.class);
			startService(intentService);
			break;
			
		default:
			break;
		}
	}
}
