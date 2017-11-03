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


public class TestDao {
	
	private TestSystemSQLiteOpenHelper TsOpenhelper;
	private List<HashMap<String, Object>> data=new ArrayList<HashMap<String,Object>>();
	public TestDao(Context context) {
		TsOpenhelper = new TestSystemSQLiteOpenHelper(context);
		
	}
	public TestDao() {
		
	}
	
	/*******
	 * 寻找单个题目
	 * @param id
	 * @return
	 */
	public Test queryItem(int id) {
		SQLiteDatabase db = TsOpenhelper.getWritableDatabase();
		if(db.isOpen()){
			Cursor cursor = db.rawQuery("select _id,topic,optionA,optionB,optionC,optionD,correct_option from test where _id = ?;", new String[]{id+""});
			if(cursor!=null && cursor.moveToFirst()){
				//HashMap<String, Object> testlist = new HashMap<String, Object>();
				String topic = cursor.getString(1);
				String optionA = cursor.getString(2);
				String optionB = cursor.getString(3);
				String optionC = cursor.getString(4);
				String optionD = cursor.getString(5);
				String correct_option = cursor.getString(6);
				db.close();
				return new Test(id,topic,optionA,optionB,optionC,optionD,correct_option);
			}
			db.close();
			cursor.close();
		}
		return null;
	}
	
	/*********
	 * 导入试卷题目
	 * @param test
	 */
	public void insert(Test test) {
		SQLiteDatabase db = TsOpenhelper.getWritableDatabase();
		if(db.isOpen()){
			db.execSQL("insert into test(topic,optionA,optionB,optionC,optionD,correct_option) values(?,?,?,?,?,?);",
					new Object[]{test.getTopic(),test.getOptionA(),test.getOptionB(),test.getOptionC(),test.getOptionD(),
									test.getCorrect_option()});
//			db.execSQL("insert into chapter1(topic,optionA,optionB,optionC,optionD,correct_option) values(?,?,?,?,?,?);",
//					new Object[]{test.getTopic(),test.getOptionA(),test.getOptionB(),test.getOptionC(),test.getOptionD(),
//									test.getCorrect_option()});
//			db.execSQL("insert into chapter2(topic,optionA,optionB,optionC,optionD,correct_option) values(?,?,?,?,?,?);",
//					new Object[]{test.getTopic(),test.getOptionA(),test.getOptionB(),test.getOptionC(),test.getOptionD(),
//									test.getCorrect_option()});
//			db.execSQL("insert into chapter3(topic,optionA,optionB,optionC,optionD,correct_option) values(?,?,?,?,?,?);",
//					new Object[]{test.getTopic(),test.getOptionA(),test.getOptionB(),test.getOptionC(),test.getOptionD(),
//									test.getCorrect_option()});
//			db.execSQL("insert into chapter4(topic,optionA,optionB,optionC,optionD,correct_option) values(?,?,?,?,?,?);",
//					new Object[]{test.getTopic(),test.getOptionA(),test.getOptionB(),test.getOptionC(),test.getOptionD(),
//									test.getCorrect_option()});
//			db.execSQL("insert into chapter5(topic,optionA,optionB,optionC,optionD,correct_option) values(?,?,?,?,?,?);",
//					new Object[]{test.getTopic(),test.getOptionA(),test.getOptionB(),test.getOptionC(),test.getOptionD(),
//									test.getCorrect_option()});
//			db.execSQL("insert into chapter6(topic,optionA,optionB,optionC,optionD,correct_option) values(?,?,?,?,?,?);",
//					new Object[]{test.getTopic(),test.getOptionA(),test.getOptionB(),test.getOptionC(),test.getOptionD(),
//									test.getCorrect_option()});
//			db.execSQL("insert into chapter7(topic,optionA,optionB,optionC,optionD,correct_option) values(?,?,?,?,?,?);",
//					new Object[]{test.getTopic(),test.getOptionA(),test.getOptionB(),test.getOptionC(),test.getOptionD(),
//									test.getCorrect_option()});
//			db.execSQL("insert into chapter8(topic,optionA,optionB,optionC,optionD,correct_option) values(?,?,?,?,?,?);",
//					new Object[]{test.getTopic(),test.getOptionA(),test.getOptionB(),test.getOptionC(),test.getOptionD(),
//									test.getCorrect_option()});
//			db.execSQL("insert into chapter9(topic,optionA,optionB,optionC,optionD,correct_option) values(?,?,?,?,?,?);",
//					new Object[]{test.getTopic(),test.getOptionA(),test.getOptionB(),test.getOptionC(),test.getOptionD(),
//									test.getCorrect_option()});
//			db.execSQL("insert into chapter10(topic,optionA,optionB,optionC,optionD,correct_option) values(?,?,?,?,?,?);",
//					new Object[]{test.getTopic(),test.getOptionA(),test.getOptionB(),test.getOptionC(),test.getOptionD(),
//									test.getCorrect_option()});
//			db.execSQL("insert into chapter11(topic,optionA,optionB,optionC,optionD,correct_option) values(?,?,?,?,?,?);",
//					new Object[]{test.getTopic(),test.getOptionA(),test.getOptionB(),test.getOptionC(),test.getOptionD(),
//									test.getCorrect_option()});
//			db.execSQL("insert into chapter12(topic,optionA,optionB,optionC,optionD,correct_option) values(?,?,?,?,?,?);",
//					new Object[]{test.getTopic(),test.getOptionA(),test.getOptionB(),test.getOptionC(),test.getOptionD(),
//									test.getCorrect_option()});
		}
		db.close();
	}
	
	/******
	 * 显示全部题目
	 * @return
	 */
	public List<HashMap<String,Object>> queryAll(){
		SQLiteDatabase db = TsOpenhelper.getWritableDatabase();
		if(db.isOpen()){
			Cursor cursor = db.rawQuery("select _id,topic,optionA,optionB,optionC,optionD,correct_option from test order by random() limit 10", null);
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
	
//	public void deleteAll(){
//		SQLiteDatabase db = TsOpenhelper.getWritableDatabase();
//		if(db.isOpen()){
//			Cursor cursor = db.rawQuery("delete from chapter7;", null);
//			cursor.close();
//		}
//		db.close(); 
//	}
	
}
