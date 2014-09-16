package com.lee.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class ActiMain extends ActBase {
	
	private ListView mLvTestDay;
	private ListAdapter mArrayAdapter;
	private String[] mArrStr;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mArrStr = getResources().getStringArray(R.array.arrTest);
		mLvTestDay = (ListView)findViewById(R.id.lv_test);
		mArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, mArrStr);
		
		mLvTestDay.setAdapter(mArrayAdapter);
		mLvTestDay.setOnItemClickListener(new OnItemClickListener(){
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
				try {
					String option = mArrStr[position];
					System.out.println(option);
					String className = "com.lee.app.Activity" + position(position);
					Class<?> clazz = Class.forName(className);
					
					Intent intent = new Intent(ActiMain.this, clazz);
					startActivity(intent);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			}
			/**
			 * @param position
			 * @return
			 */
			private String position(int position) {
				if(position <= 9){
					return "00" + position;
				}else if(position > 9 && position <= 99){
					return "0" + position;
				}else{
					return "" + position;
				}
			}
		});
	}
}
