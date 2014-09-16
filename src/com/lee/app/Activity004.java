package com.lee.app;

import java.io.File;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.lee.app.global.ToastAndLog;

public class Activity004 extends ActBase implements OnClickListener {

	private EditText mEtAddress;

	private Button mBtnPlay, mBtnReplay, mBtnPause, mBtnStop;
	
	private MediaPlayer mediaPlayer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity004);
		initView();
		register();
	}

	private void initView() {
		mEtAddress = (EditText) findViewById(R.id.et_address);
		mBtnPlay = (Button) findViewById(R.id.btn_play);
		mBtnReplay = (Button) findViewById(R.id.btn_replay);
		mBtnPause = (Button) findViewById(R.id.btn_pause);
		mBtnStop = (Button) findViewById(R.id.btn_stop);
	}

	private void register() {
		mBtnPlay.setOnClickListener(this);
		mBtnReplay.setOnClickListener(this);
		mBtnPause.setOnClickListener(this);
		mBtnStop.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.btn_play:
			play();
			break;
		case R.id.btn_replay:
			replay();
			break;
		case R.id.btn_pause:
			pause();
			break;
		case R.id.btn_stop:
			stop();
			break;
		}
	}

	/**
	 * 播放
	 */
	private void play() {
		String path = mEtAddress.getText().toString().trim();
		File file = new File(path);
		if(file.exists() && file.length() > 0){
			try {
				mediaPlayer = new MediaPlayer();
				mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
				mediaPlayer.setDataSource(path);
//				mediaPlayer.prepare();
				mediaPlayer.prepareAsync();
				mediaPlayer.setOnPreparedListener(new OnPreparedListener() {
					@Override
					public void onPrepared(MediaPlayer mp) {
						mediaPlayer.start();
					}
				});
				mBtnPlay.setEnabled(false);
				mediaPlayer.setOnCompletionListener(new OnCompletionListener() {
					@Override
					public void onCompletion(MediaPlayer mp) {
						mBtnPlay.setEnabled(true);
					}
				});
				mediaPlayer.setOnErrorListener(new OnErrorListener() {
					@Override
					public boolean onError(MediaPlayer mp, int what, int extra) {
						mBtnPlay.setEnabled(true);
						return false;
					}
				});
			} catch (Exception e) {
				ToastAndLog.makeText("播放失败");
				e.printStackTrace();
			}
		}else{
			ToastAndLog.makeText("音频文件不存在");
		}
	}

	/**
	 * 重新播放
	 */
	private void replay() {
		if(mediaPlayer != null && mediaPlayer.isPlaying()){
			mediaPlayer.seekTo(0);
			return;
		}
		play();
	}

	/**
	 * 暂停
	 */
	private void pause() {
		if("继续".equals(mBtnPause.getText().toString().trim())){
			mediaPlayer.start();
			mBtnPause.setText("暂停");
		}else if(mediaPlayer != null && mediaPlayer.isPlaying()){
			mediaPlayer.pause();
			mBtnPause.setText("继续");
		}
	}

	/**
	 * 停止播放
	 */
	private void stop() {
		if(mediaPlayer != null && mediaPlayer.isPlaying()){
			mediaPlayer.stop();
			mediaPlayer.release();
			mediaPlayer=null;
			mBtnPlay.setEnabled(true);
		}
	}
	
	
}
