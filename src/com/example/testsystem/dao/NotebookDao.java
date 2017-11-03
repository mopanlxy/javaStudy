package com.example.testsystem.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.testsystem.db.TestSystemSQLiteOpenHelper;
import com.example.testsystem.entity.Practise;
import com.example.testsystem.entity.Test;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class NotebookDao {
	/***
	 * 底层错题本的功能
	 */
	
	private TestSystemSQLiteOpenHelper TsOpenhelper;
	private List<HashMap<String, Object>> data=new ArrayList<HashMap<String,Object>>();
	public NotebookDao(Context context) {
		TsOpenhelper = new TestSystemSQLiteOpenHelper(context);
		
	}

	/*****
	 * 插入错题
	 * @param test
	 */
	public void insert(Test test) {
		SQLiteDatabase db = TsOpenhelper.getWritableDatabase();
		if(db.isOpen()){
			db.execSQL("insert into notebook(topic,optionA,optionB,optionC,optionD,correct_option) values(?,?,?,?,?,?);",
					new Object[]{test.getTopic(),test.getOptionA(),test.getOptionB(),test.getOptionC(),test.getOptionD(),
									test.getCorrect_option()});
			
		}
		db.close();
	}
	
	/********
	 * 插入全部错题
	 * @return
	 */
	public List<HashMap<String,Object>> queryAll(){
		SQLiteDatabase db = TsOpenhelper.getWritableDatabase();
		if(db.isOpen()){
			Cursor cursor = db.rawQuery("select _id,topic,optionA,optionB,optionC,optionD,correct_option from notebook;", null);
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
	
	/********
	 * 删除单个错题
	 * @param id
	 */
	public void delete(int id) {
		SQLiteDatabase db = TsOpenhelper.getWritableDatabase();
		if(db.isOpen()){
			db.execSQL("delete from notebook where _id = ?",
					new Integer[]{id});
		}
		db.close();
	}
	
}
