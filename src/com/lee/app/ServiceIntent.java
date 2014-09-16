package com.lee.app;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class ServiceIntent extends IntentService {

	public ServiceIntent() {
		super("ServerIntent");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		Log.d("ServerIntent", "Thread id is " + Thread.currentThread().getId());
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.d("ServerIntent", "onDestroy executed");
	}


}
