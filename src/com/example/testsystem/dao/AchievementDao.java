package com.example.testsystem.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.testsystem.db.TestSystemSQLiteOpenHelper;
import com.example.testsystem.entity.Achievement;
import com.example.testsystem.entity.Test;

public class AchievementDao {
	private TestSystemSQLiteOpenHelper TsOpenhelper;
	private List<HashMap<String, Object>> data=new ArrayList<HashMap<String,Object>>();
	public AchievementDao(Context context) {
		TsOpenhelper = new TestSystemSQLiteOpenHelper(context);
		
	}
	
	/******
	 * 插入一次成绩
	 * @param achievement
	 */
	public void insert(Achievement achievement) {
		SQLiteDatabase db = TsOpenhelper.getWritableDatabase();
		if(db.isOpen()){
			db.execSQL("insert into achievement(information,time) values(?,?);",
					new Object[]{achievement.getInformation(),achievement.getTime()});
		}
		db.close();
	}
	
	/******
	 * 显示全部成绩
	 * @return
	 */
	public List<HashMap<String,Object>> queryAll(){
		SQLiteDatabase db = TsOpenhelper.getWritableDatabase();
		if(db.isOpen()){
			Cursor cursor = db.rawQuery("select _id,information,time from achievement;", null);
			if (cursor!=null && cursor.getCount()>0) {
				while (cursor.moveToNext()) {
					HashMap<String, Object> testlist = new HashMap<String, Object>();
					int id = cursor.getInt(0);
					String information = cursor.getString(1);
					String time = cursor.getString(2);
					testlist.put("id",id);
					testlist.put("information",information);
					testlist.put("time",time);
					data.add(testlist);
				}
				db.close();
				cursor.close();
				return data;
			}
			db.close(); cursor.close();
		}
		return null;
	}
	
	/*******
	 * 清除全部成绩
	 */
	public void deleteAll(){
		SQLiteDatabase db = TsOpenhelper.getWritableDatabase();
		if(db.isOpen()){
			db.execSQL("delete from achievement;");
		}
		db.close(); 
	}
	
}
