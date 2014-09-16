package com.lee.app.global;

import android.util.Log;
import android.widget.Toast;

/**
 * @author William Lee
 *
 */
public class ToastAndLog{

	private ToastAndLog(){
		// 工具类：禁止实例化
	}
	
	public static void makeText(CharSequence text) {
		Toast.makeText(App.getContext(), text, Toast.LENGTH_SHORT).show();
	}

	public static void makeText(int resId) {
		Toast.makeText(App.getContext(), resId, Toast.LENGTH_SHORT).show();
	}
	
	public static void debug(String msg){
		Log.d(Const.TAG, msg);
	}
	
}
