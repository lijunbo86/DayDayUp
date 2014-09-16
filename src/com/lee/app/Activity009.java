package com.lee.app;

import java.io.File;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import com.lee.app.global.ToastAndLog;

public class Activity009 extends ActBase {

	String PATH = Environment.getExternalStorageDirectory().getAbsolutePath()
			+ File.separator + "Lee" + File.separator;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity009);
	}

	public void openWordFile(View view) {

		Intent intent = new Intent();

		// intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//		intent.setAction(android.content.Intent.ACTION_VIEW);
		intent.setClassName("cn.wps.moffice_eng",
				"cn.wps.moffice.documentmanager.PreStartActivity2");

		Uri uri = Uri.fromFile(new File(PATH + "���ƿƽ���ָ��.doc"));
//		intent.setData(uri);
		
		String fileMimeType = "application/vnd.ms-word";
		intent.setDataAndType(uri,fileMimeType);
		
		
		
		try {
			startActivity(intent);
		} catch (ActivityNotFoundException e) {
			ToastAndLog.debug(PATH);
			ToastAndLog.makeText("��û�а�װָ����WPS���");
			e.printStackTrace();
		}
	}

	public void openWordFile2(View view) {
		Intent intent = new Intent();
		intent.setAction("com.olivephone.edit");
		String fileMimeType = "application/vnd.ms-word";
		
		Uri uri = Uri.fromFile(new File(PATH + "���ƿƽ���ָ��.doc"));
		intent.setDataAndType(uri,fileMimeType);
		
		try {
			startActivity(intent);
		} catch (ActivityNotFoundException e) {
			// ��⵽ϵͳ��δ��װOliveOffice��apk����
			// ���ȵ�www.olivephone.com/e.apk���ز���װ
			ToastAndLog.debug(PATH);
			ToastAndLog.makeText("��⵽ϵͳ��δ��װOliveOffice��apk����");
			e.printStackTrace();
		}

	}
}
