package com.lee.app;

import java.io.File;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.os.Environment;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.lee.app.global.ToastAndLog;

public class Activity006 extends ActBase implements OnClickListener{

	private Button mBtnPlay, mBtnReplay, mBtnPause, mBtnStop;
	
	private MediaPlayer mediaPlayer;
	private SurfaceView surfaceView;
	private SeekBar seekBar;
	
	private int currentPosition;
	@SuppressWarnings("unused")
	private boolean isPlaying;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity006);
		initView();
		register();
	}

	@SuppressWarnings("deprecation")
	private void initView() {
		mBtnPlay = (Button) findViewById(R.id.btn_video_play);
		mBtnReplay = (Button) findViewById(R.id.btn_video_replay);
		mBtnPause = (Button) findViewById(R.id.btn_video_pause);
		mBtnStop = (Button) findViewById(R.id.btn_video_stop);
		
		seekBar = (SeekBar)findViewById(R.id.sb_process);
		
		surfaceView = (SurfaceView)findViewById(R.id.sv_video);
		// 下面设置surfaceView不维护自己的缓冲区
		// 而是等待屏幕的渲染引擎将内容推送到用户面前
		// 低版本手机加上参数 指定 自己不维护缓冲区
		surfaceView.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
	}

	private void register() {
		mBtnPlay.setOnClickListener(this);
		mBtnReplay.setOnClickListener(this);
		mBtnPause.setOnClickListener(this);
		mBtnStop.setOnClickListener(this);
		
		seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				int process = seekBar.getProgress();
				if(mediaPlayer!=null && mediaPlayer.isPlaying()){
					mediaPlayer.seekTo(process);
				}
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) { }
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) { }
		});
		
		surfaceView.getHolder().addCallback(new Callback() {
			
			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				if(mediaPlayer != null && mediaPlayer.isPlaying()){
					currentPosition = mediaPlayer.getCurrentPosition();
					stop();
				}
			}
			
			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				if(currentPosition > 0){
					play(currentPosition);
				}
			}
			
			@Override
			public void surfaceChanged(SurfaceHolder holder, int format, int width,int height) {
				
			}
		});
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.btn_video_play:
			play(0);
			break;
		case R.id.btn_video_replay:
			replay();
			break;
		case R.id.btn_video_pause:
			pause();
			break;
		case R.id.btn_video_stop:
			stop();
			break;
		}
	}

	/**
	 * 播放
	 * @param currentPosition 
	 */
	private void play(final int currentPosition) {
		File file = new File(Environment.getExternalStorageDirectory(),"lee.3gp");
		// 播放在线视频
//		String httpUrl = "http://192.168.0.1:8080/kaikai.3gp";
		if(file.exists() && file.length() > 0){
			try {
				mediaPlayer = new MediaPlayer();
				mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
				mediaPlayer.setDisplay(surfaceView.getHolder());

				mediaPlayer.setDataSource(file.getAbsolutePath());
//				mediaPlayer.setDataSource(httpUrl);
				
				// mediaPlayer.prepare();// 同步
				mediaPlayer.prepareAsync(); // 异步
				mediaPlayer.setOnPreparedListener(new OnPreparedListener() {
					@Override
					public void onPrepared(MediaPlayer mp) {
						mediaPlayer.start();
						int max = mediaPlayer.getDuration();
						seekBar.setMax(max);
						mediaPlayer.seekTo(currentPosition);
						
						new Thread(new Runnable() {
							@Override
							public void run() {
								isPlaying = true;
								while(true){
									int position = mediaPlayer.getCurrentPosition();
									seekBar.setProgress(position);
									try {
										Thread.sleep(500);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
								}
							}
						}).start();
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
						isPlaying = false;
						return false;
					}
				});
			} catch (Exception e) {
				ToastAndLog.makeText("播放失败");
				e.printStackTrace();
			}
		}else{
			ToastAndLog.makeText("视频文件不存在");
		}
	}

	/**
	 * 重新播放
	 */
	private void replay() {
		if(mediaPlayer != null && mediaPlayer.isPlaying()){
			mediaPlayer.seekTo(0);
		}
		play(0);
		mBtnPause.setText("暂停");
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
			isPlaying = false;
		}
	}
	
	
}
