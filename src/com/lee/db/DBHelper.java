package com.lee.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper{

	private static final String CREATE_BOOK = "create table Book ("
																	+ "id integer primary key autoincrement, " 
																	+ "author text, "
																	+ "price real, " 
																	+ "pages integer, " 
																	+ "name text"
																	+ "category_id integer)";
	
	private static final String CREATE_BOOK_ADD_COLUMN = "alter table Book "
																		+ "add column category_id integer";
			
	private static final String CREATE_CATEGORY = "create table Category ("
																			+ "id integer primary key autoincrement, "
																			+ "category_name text, "
																			+ "category_code integer)";

	@SuppressWarnings("unused")
	private Context mContext;

	
	public DBHelper(Context context, String name, CursorFactory factory,	int version) {
		super(context, name, factory, version);
		mContext = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_BOOK);
		db.execSQL(CREATE_CATEGORY);
	}

	/**
	 * �������ݿ�����д����
	 * �����˿�汾������ɵ���©,�������Ա�֤���ݿ�ı�ṹ�����µ�,���ұ��е�����Ҳ��ȫ���ᶪʧ
	 * ע�⣺����ʱ��ԭ��ṹ����һ��Ķ���Ϊ�˱����һ�ΰ�װ���û��ı�ṹ��һ��
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		switch (oldVersion) {
		case 1:
			db.execSQL(CREATE_CATEGORY);
		case 2:
			db.execSQL(CREATE_BOOK_ADD_COLUMN);
			
		default:
			break;
		}
	}


}
