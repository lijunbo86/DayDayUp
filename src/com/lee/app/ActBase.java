package com.lee.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.lee.util.ActivityCollector;

/**
 * @function Activity�Ļ��࣬����Activity����Ҫ�̳и��࣬�����������й���
 * 					�ṩ��Handle�����ķ�װ����ֹHandle�ڴ�й¶
 * @author William Lee
 * @date 2014��9��13��
 */
public class ActBase extends Activity {
	
	protected final String TAG = getClass().getSimpleName();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// ����ʾ����
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		// ֪����ǰ������һ���
		Log.i(TAG,getClass().getSimpleName());
		
		// ����������ӵ���ɼ���
		ActivityCollector.addActivity(this);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		// ���������ӻ�ɼ����Ƴ�
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
