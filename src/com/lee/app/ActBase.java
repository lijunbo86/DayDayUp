package com.lee.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.lee.util.ActivityCollector;

/**
 * @function Activity的基类，所有Activity都需要继承该类，方便对子类进行管理，
 * 					提供对Handle方法的封装，防止Handle内存泄露
 * @author William Lee
 * @date 2014年9月13日
 */
public class ActBase extends Activity {
	
	protected final String TAG = getClass().getSimpleName();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// 不显示标题
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		// 知晓当前是在哪一个活动
		Log.i(TAG,getClass().getSimpleName());
		
		// 将其子类活动添加到活动采集器
		ActivityCollector.addActivity(this);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		// 将其子类活从活动采集器移除
		ActivityCollector.removeActivity(this);
	}
	
	@Override
	public void startActivity(Intent intent) {
		super.startActivity(intent);
		overridePendingTransition(R.anim.common_right_in, R.anim.common_left_out);
	}

	@Override
	public void startActivityForResult(Intent intent, int requestCode) {
		super.startActivityForResult(intent, requestCode);
		overridePendingTransition(R.anim.common_right_in, R.anim.common_zoom_out);
	}

	@Override
	public void finish() {
		super.finish();
		overridePendingTransition(R.anim.common_left_in, R.anim.common_right_out);
	}
	
}
