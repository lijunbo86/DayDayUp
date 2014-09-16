package com.lee.service;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;

public class LoginService {

	private LoginService() { }

	/**
	 * 保存用户信息
	 * @param context
	 * @param username
	 * @param password
	 */
	public static void saveUserInfo(Context context,String username,String password){
		SharedPreferences preferences = context.getSharedPreferences("config", Context.MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.putString("username", username);
		editor.putString("password", password);
		editor.commit();
	}
	
	/**
	 * 清除用户信息
	 * @param context
	 * @param username
	 * @param password
	 */
	public static void clearUserInfo(Context context,String username,String password){
		SharedPreferences preferences = context.getSharedPreferences("config", Context.MODE_PRIVATE);
		Editor editor = preferences.edit();
		editor.putString("username", username);
		editor.putString("password", password);
		editor.commit();
	}
	
	/**
	 * 获取用户信息
	 * @param context
	 * @return map
	 */
	public static Map<String,String> getUserInfo(Context context){
		SharedPreferences preferences = context.getSharedPreferences("config", Context.MODE_PRIVATE);
		String us = preferences.getString("username", "");
		String pw = preferences.getString("password", "");
		if(!TextUtils.isEmpty(us) && !TextUtils.isEmpty(pw)){
			Map<String,String> map = new HashMap<String, String>();
			map.put("username", us);
			map.put("password", pw);
			return map;
		}
		return null;
	}
}
