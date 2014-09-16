package com.lee.app.global;

import android.app.Application;
/**
 * 
 * @author William Lee
 *
 */
public class App extends Application {

	//定义一个全局的Context
	private static App context;

	//调用此方法返回一个Context实例
    public static App getContext() {
        return context;
    }
    
	@Override
	public void onCreate() {
		super.onCreate();
        context = this;
	}
}
