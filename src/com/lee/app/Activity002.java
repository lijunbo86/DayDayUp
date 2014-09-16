package com.lee.app;

import java.util.List;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.lee.entity.WeatherInfo;
import com.lee.service.WeatherService;

public class Activity002 extends ActBase {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity002);
		TextView mTvWeather = (TextView) findViewById(R.id.tv_weather);
		try {
			List<WeatherInfo> infos = WeatherService
					.getWeatherInfos(Activity002.this.getClassLoader()
							.getResourceAsStream("weather.xml"));
			StringBuffer sb = new StringBuffer();
			for(WeatherInfo info : infos){
				String str = info.toString();
				sb.append(str);
				sb.append("\n");
			}
			mTvWeather.setText(sb.toString());
			Toast.makeText(this, "解析信息成功", Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(this, "解析信息失败", Toast.LENGTH_SHORT).show();;
		}
	}
}
