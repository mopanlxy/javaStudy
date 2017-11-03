package com.example.testsystem.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class TestSystemSQLiteOpenHelper extends SQLiteOpenHelper {

	public TestSystemSQLiteOpenHelper(Context context) {
		super(context, "test.db", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "create table test(_id integer primary key autoincrement,topic text,optionA text,optionB text,optionC text,optionD text,correct_option text,explain text)";
		String sql2 = "create table notebook(_id integer primary key autoincrement,topic text,optionA text,optionB text,optionC text,optionD text,correct_option text,explain text);";
		String sql3 = "create table chapter1(_id integer primary key autoincrement,topic text,optionA text,optionB text,optionC text,optionD text,correct_option text,explain text)";
		String sql4 = "create table chapter2(_id integer primary key autoincrement,topic text,optionA text,optionB text,optionC text,optionD text,correct_option text,explain text)";
		String sql5 = "create table chapter3(_id integer primary key autoincrement,topic text,optionA text,optionB text,optionC text,optionD text,correct_option text,explain text)";
		String sql6 = "create table chapter4(_id integer primary key autoincrement,topic text,optionA text,optionB text,optionC text,optionD text,correct_option text,explain text)";
		String sql7 = "create table chapter5(_id integer primary key autoincrement,topic text,optionA text,optionB text,optionC text,optionD text,correct_option text,explain text)";
		String sql8 = "create table chapter6(_id integer primary key autoincrement,topic text,optionA text,optionB text,optionC text,optionD text,correct_option text,explain text)";
		String sql9 = "create table chapter7(_id integer primary key autoincrement,topic text,optionA text,optionB text,optionC text,optionD text,correct_option text,explain text)";
		String sql10 = "create table chapter8(_id integer primary key autoincrement,topic text,optionA text,optionB text,optionC text,optionD text,correct_option text,explain text)";
		String sql11 = "create table chapter9(_id integer primary key autoincrement,topic text,optionA text,optionB text,optionC text,optionD text,correct_option text,explain text)";
		String sql12 = "create table chapter10(_id integer primary key autoincrement,topic text,optionA text,optionB text,optionC text,optionD text,correct_option text,explain text)";
		String sql13 = "create table chapter11(_id integer primary key autoincrement,topic text,optionA text,optionB text,optionC text,optionD text,correct_option text,explain text)";
		String sql14 = "create table chapter12(_id integer primary key autoincrement,topic text,optionA text,optionB text,optionC text,optionD text,correct_option text,explain text)";
		String sql15 = "create table achievement(_id integer primary key autoincrement,information text, time text)";

		db.execSQL(sql);
		db.execSQL(sql2);
		db.execSQL(sql3);
		db.execSQL(sql4);
		db.execSQL(sql5);
		db.execSQL(sql6);
		db.execSQL(sql7);
		db.execSQL(sql8);
		db.execSQL(sql9);
		db.execSQL(sql10);
		db.execSQL(sql11);
		db.execSQL(sql12);
		db.execSQL(sql13);
		db.execSQL(sql14);
		db.execSQL(sql15);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		
	}

}
