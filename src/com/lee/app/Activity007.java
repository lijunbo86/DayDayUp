package com.lee.app;

import java.io.File;

import com.lee.app.global.ToastAndLog;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;

public class Activity007 extends ActBase {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity007);
	}
	
	/**
	 * 
	 * @param view
	 */
	public void zhaoxiang(View view){
		Intent intent = new Intent();
		intent.setAction("android.media.action.IMAGE_CAPTURE");
		intent.addCategory("android.intent.category.DEFAULT");
		File file = new File(Environment.getExternalStorageDirectory(),System.currentTimeMillis() + ".jpg");
		Uri uri = Uri.fromFile(file);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
		startActivity(intent);
	}
	
	/**
	 * 
	 * @param view
	 */
	public void luxiang(View view){
		Intent intent = new Intent();
		intent.setAction("android.media.action.VIDEO_CAPTURE");
		intent.addCategory("android.intent.category.DEFAULT");
		File file = new File(Environment.getExternalStorageDirectory(),System.currentTimeMillis() + ".3gp");
		Uri uri = Uri.fromFile(file);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
		startActivityForResult(intent, 0);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		ToastAndLog.makeText("调用录像机完毕");
	}
	
}
