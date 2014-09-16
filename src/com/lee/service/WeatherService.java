package com.lee.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import android.util.Xml;
import com.lee.entity.WeatherInfo;

public class WeatherService {
	
	private WeatherService(){
		// ˽�й���
	}
	
	public static List<WeatherInfo> getWeatherInfos(InputStream is) throws Exception{
		
		XmlPullParser parser = Xml.newPullParser();
		// ��ʼ��������
		parser.setInput(is,"UTF-8");
		List<WeatherInfo> weatherInfos = null;
		WeatherInfo weatherInfo = null;
		int type = parser.getEventType();
		while(type != XmlPullParser.END_DOCUMENT){
			
			switch (type) {
			case XmlPullParser.START_TAG:
				if("infos".equals(parser.getName())){
					// ������ȫ�ֿ�ʼ�ı�ǩ
					weatherInfos = new ArrayList<WeatherInfo>();
				}else if("city".equals(parser.getName())){
					weatherInfo = new WeatherInfo();
					String idStr = parser.getAttributeValue(0);
					weatherInfo.setId(Integer.parseInt(idStr));
				}else if("temp".equals(parser.getName())){
					String temp = parser.nextText();
					weatherInfo.setTemp(temp);
				}else if("weather".equals(parser.getName())){
					String weather = parser.nextText();
					weatherInfo.setWeather(weather);
				}else if("wind".equals(parser.getName())){
					String wind = parser.nextText();
					weatherInfo.setWind(wind);
				}else if("name".equals(parser.getName())){
					String name = parser.nextText();
					weatherInfo.setName(name);
				}else if("pm".equals(parser.getName())){
					String pm = parser.nextText();
					weatherInfo.setPm(pm);
				}
				break;
				
			case XmlPullParser.END_TAG:
				if("city".equals(parser.getName())){
					/*
					 * ������ȫ�ֽ����ı�ǩ����������ӵ�����
					 * ����weatherinfo����Ϊ�գ��ͷ��ڴ�
					 */
					weatherInfos.add(weatherInfo);
					weatherInfo = null;
				}
				break;
			}
			type = parser.next();
		}
		return weatherInfos;
	}

}
