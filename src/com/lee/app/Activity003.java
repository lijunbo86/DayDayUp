package com.lee.app;

import android.os.Bundle;
import android.widget.TextView;

import com.lee.service.MemoryService;

public class Activity003 extends ActBase {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity003);
		TextView mTvSize = (TextView)findViewById(R.id.tv_size);
		
		String sdTotalSize = MemoryService.getSDTotalSize(this);
		String sdAvailableSize = MemoryService.getSDAvailableSize(this);
		
		String romTotalSize = MemoryService.getRomTotalSize(this);
		String romAvailableSize = MemoryService.getRomAvailableSize(this);
		
		mTvSize.setText("SD���ܴ�С = " + sdTotalSize + "\nSD�����ô�С = " + sdAvailableSize
				+"\nMemory���ܴ�С" + romTotalSize + "\nMemory�����ô�С" + romAvailableSize);
		
	}
}
