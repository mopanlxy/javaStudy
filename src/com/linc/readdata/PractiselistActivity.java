package com.linc.readdata;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.testsystem.dao.PractiseDao;

public class PractiselistActivity extends Activity {

	private ListView lv_practisechapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_practiselist);
		lv_practisechapter = (ListView) findViewById(R.id.listView1);
		final PractiseDao pd = new PractiseDao(PractiselistActivity.this);
		String[] chapter = {"java数据类型和操作符","流程控制语句","面向对象","异常",
				"多线程","javaAPI，字符串类，工具类","IO流","集合","网络编程"};
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, 
				android.R.layout.simple_list_item_1, chapter );
		lv_practisechapter.setAdapter(adapter);
		
		lv_practisechapter.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				
					Intent intent_practise = new Intent(PractiselistActivity.this,PractiseActivity.class);

					switch (arg2) {
						case 0:
							intent_practise.putExtra("chapter_name", "chapter1");
							startActivity(intent_practise);
						break;
						
						case 1:
							intent_practise.putExtra("chapter_name", "chapter2");
							startActivity(intent_practise);
						break;
						
						case 2:
							intent_practise.putExtra("chapter_name", "chapter3");
							startActivity(intent_practise);
						break;

						case 3:
							intent_practise.putExtra("chapter_name", "chapter4");
							startActivity(intent_practise);
						break;
							
						case 4:
							intent_practise.putExtra("chapter_name", "chapter5");
							startActivity(intent_practise);
						break;
							
						case 5:
							intent_practise.putExtra("chapter_name", "chapter6");
							startActivity(intent_practise);
						break;
							
						case 6:
							intent_practise.putExtra("chapter_name", "chapter7");
							startActivity(intent_practise);
						break;
								
						case 7:
							intent_practise.putExtra("chapter_name", "chapter8");
							startActivity(intent_practise);
						break;
								
						case 8:
							intent_practise.putExtra("chapter_name", "chapter9");
							startActivity(intent_practise);
						break;
								
						case 9:
							intent_practise.putExtra("chapter_name", "chapter10");
							startActivity(intent_practise);
						break;
									
						case 10:
							intent_practise.putExtra("chapter_name", "chapter11");	
							startActivity(intent_practise);
						break;
									
						case 11:
							intent_practise.putExtra("chapter_name", "chapter12");
							startActivity(intent_practise);
						break;
						
						
					}
			}
		});
		
	}

	

}
