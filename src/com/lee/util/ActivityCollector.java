package com.lee.util;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Process;

/**
 * @function 活动采集器
 * @author William Lee
 * @date 2014年9月12日
 */
public class ActivityCollector {
	
	public static List<Activity> activities = new ArrayList<Activity>();
	
	public static void addActivity(Activity activity){
		activities.add(activity);
	}
	
	public static void removeActivity(Activity activity){
		activities.remove(activity);
	}
	
	public static void finishAll(){
		for(Activity activity : activities){
			activity.finish();
		}
		Process.killProcess(Process.myPid());
	}
}
