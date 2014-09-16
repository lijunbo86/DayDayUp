package com.lee.app;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.xmlpull.v1.XmlSerializer;

import android.os.Bundle;
import android.os.Environment;
import android.util.Xml;
import android.view.View;
import android.widget.Toast;

import com.lee.entity.SmsInfo;

public class Activity001 extends ActBase {
	
	private List<SmsInfo> smsInfos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity001);
		initData();
	}
	
	private void initData() {
		smsInfos = new ArrayList<SmsInfo>();
		Random random = new Random();
		long number = 1525211251;
		for (int i = 0; i < 10; i++) {
			smsInfos.add(new SmsInfo(i, System.currentTimeMillis(), random
					.nextInt(2) + 1, "短信内容" + i, Long.toString(number) + i));
		}
	}

	public void backSmsOne(View view){
		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		sb.append("<smss>");
		for(SmsInfo info : smsInfos){
			sb.append("<sms>");
			
			sb.append("<address>");
			sb.append(info.getAddress());
			sb.append("</address>");
			
			sb.append("<type>");
			sb.append(info.getType());
			sb.append("</type>");
			
			sb.append("<body>");
			sb.append(info.getBody()+"<>");
			sb.append("</body>");
			
			sb.append("<date>");
			sb.append(info.getDate());
			sb.append("</date>");
			
			sb.append("</sms>");
		}
		sb.append("</smss>");
		
		try {
			File file = new File(Environment.getExternalStorageDirectory(), "backup.xml");
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(sb.toString().getBytes());
			fos.close();
			Toast.makeText(this, "备份成功", Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(this, "备份失败", Toast.LENGTH_SHORT).show();
		}
	}
	
	public void backSmsTwo(View view){
		try {
			XmlSerializer serializer = Xml.newSerializer();
			File file = new File(Environment.getExternalStorageDirectory(), "backup2.xml");
			FileOutputStream os = new FileOutputStream(file);
			// 初始化序列化器指定xml数据写入到哪个文件，并且指定文件的编码方式
			serializer.setOutput(os,"UTF-8");
			
			serializer.startDocument("UTF-8", true);
			serializer.startTag(null, "smss");
			for(SmsInfo info : smsInfos){
				serializer.startTag(null, "sms");
				serializer.attribute(null, "id", info.getId()+"");
				
				serializer.startTag(null, "body");
				serializer.text(info.getBody()+"<>");
				serializer.endTag(null, "body");
				
				serializer.startTag(null, "address");
				serializer.text(info.getAddress());
				serializer.endTag(null, "address");
				
				serializer.startTag(null, "type");
				serializer.text(info.getType() + "");
				serializer.endTag(null, "type");
				
				serializer.startTag(null, "date");
				serializer.text(info.getDate() + "");
				serializer.endTag(null, "date");
				
				serializer.endTag(null, "sms");
			}
			serializer.endTag(null, "smss");
			serializer.endDocument();
			os.close();
			Toast.makeText(this, "备份成功", Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(this, "备份失败", Toast.LENGTH_SHORT).show();
		}
	}
}
