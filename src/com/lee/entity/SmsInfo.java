package com.lee.entity;

public class SmsInfo {
	
	private int id;
	
	private long date;
	
	private int type;
	
	private String body;
	
	private String address;
	
	public SmsInfo() {
		// TODO Auto-generated constructor stub
	}

	public SmsInfo(int id, long date, int type, String body, String address) {
		this.id = id;
		this.date = date;
		this.type = type;
		this.body = body;
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
