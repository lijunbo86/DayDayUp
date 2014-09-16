package com.lee.app;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnActionExpandListener;
import android.view.ViewConfiguration;
import android.view.Window;
import android.widget.SearchView;
import android.widget.SearchView.OnCloseListener;
import android.widget.Toast;

/**
 * @author William Lee
 *
 */
public class Activity008 extends ActBase {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity008);
		
		
		ActionBar actionBar = getActionBar();
		// 隐藏ActionBar
		// actionBar.hide(); 
		
		// 设置带有返回按钮
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		// 通过反射将ActionBar
		setOverflowShowingAlways();
	}
	
	
	
	
	
	private void setOverflowShowingAlways() {
		try {
			ViewConfiguration config = ViewConfiguration.get(this);
			Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
			menuKeyField.setAccessible(true);
			menuKeyField.setBoolean(config, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean onMenuOpened(int featureId, Menu menu) {
		if (featureId == Window.FEATURE_ACTION_BAR && menu != null) {
			if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
				try {
					Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
					m.setAccessible(true);
					m.invoke(menu, true);
				} catch (Exception e) {
					
				}
			}
		}
		return super.onMenuOpened(featureId, menu);
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.menu, menu);
		
		MenuItem item = menu.findItem(R.id.action_search);
		

		// ActionView展开和合并的时候显示不同的界面
		// 其实我们只需要去注册一个ActionView的监听器就能实现这样的功能
		item.setOnActionExpandListener(new OnActionExpandListener() {
			
			@Override
			public boolean onMenuItemActionExpand(MenuItem item) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public boolean onMenuItemActionCollapse(MenuItem item) {
				// TODO Auto-generated method stub
				return false;
			}
		});
		
		// 此处可以对SearchView进行相关属性的设置
		SearchView sv = (SearchView)item.getActionView();
		sv.setOnCloseListener(new OnCloseListener() {
			
			@Override
			public boolean onClose() {
				
				return false;
			}
		});
		
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		
		case android.R.id.home:  // 注意此时的ID是由系统提供的
	        finish();  
	        return true; 
		
		case R.id.action_rensheng:
			Toast.makeText(this, "人生", Toast.LENGTH_SHORT).show();
			return true;
			
		case R.id.action_jianchi:
			Toast.makeText(this, "坚持", Toast.LENGTH_SHORT).show();
			return true;
			
		case R.id.action_mengxiang:
			Toast.makeText(this, "梦想", Toast.LENGTH_SHORT).show();
			return true;
			
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
}
