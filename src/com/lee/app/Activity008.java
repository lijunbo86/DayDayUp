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
		// ����ActionBar
		// actionBar.hide(); 
		
		// ���ô��з��ذ�ť
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		// ͨ�����佫ActionBar
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
		

		// ActionViewչ���ͺϲ���ʱ����ʾ��ͬ�Ľ���
		// ��ʵ����ֻ��Ҫȥע��һ��ActionView�ļ���������ʵ�������Ĺ���
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
		
		// �˴����Զ�SearchView����������Ե�����
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
		
		case android.R.id.home:  // ע���ʱ��ID����ϵͳ�ṩ��
	        finish();  
	        return true; 
		
		case R.id.action_rensheng:
			Toast.makeText(this, "����", Toast.LENGTH_SHORT).show();
			return true;
			
		case R.id.action_jianchi:
			Toast.makeText(this, "���", Toast.LENGTH_SHORT).show();
			return true;
			
		case R.id.action_mengxiang:
			Toast.makeText(this, "����", Toast.LENGTH_SHORT).show();
			return true;
			
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
}
