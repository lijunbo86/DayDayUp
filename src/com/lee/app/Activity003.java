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
		
		mTvSize.setText("SD卡总大小 = " + sdTotalSize + "\nSD卡可用大小 = " + sdAvailableSize
				+"\nMemory卡总大小" + romTotalSize + "\nMemory卡可用大小" + romAvailableSize);
		
	}
}
