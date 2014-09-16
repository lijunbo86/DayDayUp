package com.lee.app;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;

public class Activity005 extends ActBase {
	
	private SoundPool soundPool;
	private int soundIdOne,soundIdTwo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity005);
		initSoundPool();
	}

	private void initSoundPool() {
		soundPool = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
		// 注意此方法是异步执行，应放在onCreate函数中初始化
		soundIdOne  = soundPool.load(this, R.raw.right, 1);
		soundIdTwo  = soundPool.load(this, R.raw.error, 1);
	}
	
	public void fireOne(View view){
		// 卡带机 Walkman 最后一个参数的效果如同卡带机
		soundPool.play(soundIdOne, 1.0f, 1.0f, 0, 0, 1.0f);
	}
	
	public void fireTwo(View view){
		soundPool.play(soundIdTwo, 1.0f, 1.0f, 0, 0, 1.0f);
	}
}
