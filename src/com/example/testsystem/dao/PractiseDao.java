package com.example.testsystem.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.testsystem.db.TestSystemSQLiteOpenHelper;
import com.example.testsystem.entity.Practise;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class PractiseDao {
	
	private TestSystemSQLiteOpenHelper TsOpenhelper;
	private List<HashMap<String, Object>> data=new ArrayList<HashMap<String,Object>>();
	public PractiseDao(Context context) {
		TsOpenhelper = new TestSystemSQLiteOpenHelper(context);
		
	}
	
	/*******
	 * 显示单个练习题
	 * @param id
	 * @param table
	 * @return
	 */
	public Practise queryItem(int id,String table) {
		SQLiteDatabase db = TsOpenhelper.getWritableDatabase();
		if(db.isOpen()){
			Cursor cursor = db.rawQuery("select _id,topic,optionA,optionB,optionC,optionD,correct_option from "+table+" where _id = ?;", new String[]{id+""});
			if(cursor!=null && cursor.moveToFirst()){
				//HashMap<String, Object> testlist = new HashMap<String, Object>();
				String topic = cursor.getString(1);
				String optionA = cursor.getString(2);
				String optionB = cursor.getString(3);
				String optionC = cursor.getString(4);
				String optionD = cursor.getString(5);
				String correct_option = cursor.getString(6);
				db.close();
				return new Practise(topic,optionA,optionB,optionC,optionD,correct_option);
			}
			db.close();
			cursor.close();
		}
		return null;
	}
	
	/*****
	 * 显示某个章节全部练习题
	 * @param chapter
	 * @return
	 */
	public List<HashMap<String,Object>> queryAll(String chapter){
		SQLiteDatabase db = TsOpenhelper.getWritableDatabase();
		if(db.isOpen()){
			Cursor cursor = db.rawQuery("select _id,topic,optionA,optionB,optionC,optionD,correct_option from "+" "+chapter, null);
			if (cursor!=null && cursor.getCount()>0) {
				while (cursor.moveToNext()) {
					HashMap<String, Object> testlist = new HashMap<String, Object>();
					int id = cursor.getInt(0);
					String topic = cursor.getString(1);
					String optionA = cursor.getString(2);
					String optionB = cursor.getString(3);
					String optionC = cursor.getString(4);
					String optionD = cursor.getString(5);
					String correct_option = cursor.getString(6);
					testlist.put("id",id);
					testlist.put("topic",topic);
					testlist.put("optionA",optionA);
					testlist.put("optionB",optionB);
					testlist.put("optionC",optionC);
					testlist.put("optionD",optionD);
					testlist.put("correct_option",correct_option);
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
}
