package com.lee.entity;

/**
 * 
 * @author William Lee
 * 
 */
public class WeatherInfo {

	private int id;

	private String temp;

	private String weather;

	private String wind;

	private String name;

	private String pm;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public String getWind() {
		return wind;
	}

	public void setWind(String wind) {
		this.wind = wind;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPm() {
		return pm;
	}

	public void setPm(String pm) {
		this.pm = pm;
	}

	@Override
	public String toString() {
		return "城市id = " + id + ", \n温度 = " + temp + ", \n天气 = " + weather
				+ ", \n风力 = " + wind + ", \n名称 = " + name + ", \n埋汰指数=" + pm
				+ "\n";
	}

}
