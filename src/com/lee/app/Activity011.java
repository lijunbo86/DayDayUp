package com.lee.app;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.lee.fragment.SecondRightFragment;

/**
 * @author William Lee
 *
 */
public class Activity011 extends ActBase implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity011);
		Button button = (Button)findViewById(R.id.btn_left_fragment);
		button.setOnClickListener(this);

		Log.d(TAG, "onCreate");
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		
		case R.id.btn_left_fragment:
			SecondRightFragment secondRightFragment = new SecondRightFragment();
			FragmentManager fm = getFragmentManager();
			FragmentTransaction ft = fm.beginTransaction();
			
			ft.replace(R.id.right_layout,secondRightFragment);
			ft.addToBackStack(null);
			ft.commit();
			break;
			
		default:
			break;
		}
	}
	

	@Override
	protected void onStart() {
		super.onStart();
		Log.d(TAG, "onStart");
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
		Log.d(TAG, "onRestart");
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		Log.d(TAG, "onResume");
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		Log.d(TAG, "onPause");
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		Log.d(TAG, "onStop");
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d(TAG, "onDestroy");
	}
	
}
