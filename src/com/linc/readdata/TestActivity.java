package com.linc.readdata;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.example.testsystem.dao.AchievementDao;
import com.example.testsystem.dao.NotebookDao;
import com.example.testsystem.dao.TestDao;
import com.example.testsystem.entity.Achievement;
import com.example.testsystem.entity.Test;

import android.os.Bundle;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

public class TestActivity extends Activity {

	private ListView lv_question;
	private int num = 9;
	private Button btn_submit,btn_check;
	private List<HashMap<String, Object>> questionlist = null;//答案列表
	private TextView tv_achievement;
	private boolean[] add_notebook = new boolean[50];
	private HashMap<Integer, Integer> hashmap_saveid = new HashMap<Integer, Integer>();
	private String option = null;
	private HashMap<Integer, String> chooselist = new HashMap<Integer, String>();
	private int right_num=0,error_num=0;
	boolean optiona = false;
	boolean optionb = false;
	boolean optionc = false;
	boolean optiond = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);
		
		tv_achievement = (TextView) findViewById(R.id.text_achievement);
		lv_question = (ListView) findViewById(R.id.lv_question);
		btn_submit = (Button) findViewById(R.id.submit);
		btn_check = (Button) findViewById(R.id.check_answer);
		for (int i = 0; i < add_notebook.length; i++) {
			add_notebook[i] = false;
		}
		
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		//显示试题
		TestDao td = new TestDao(TestActivity.this);
		questionlist = td.queryAll();
		final MyAdapter ma = new MyAdapter(questionlist,chooselist);
		lv_question.setAdapter(ma);
		lv_question.setOnItemClickListener(null);
		
		//检查是否有漏做的题
		btn_check.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				for (int i = 0; i <= 9; i++) {
					int id = (Integer) questionlist.get(i).get("id");
					if(chooselist.get(id)==null){
						Toast.makeText(TestActivity.this, "未完成,请做完！", 0).show();
					}
				}
			}
		});
		
		//试卷提交
		btn_submit.setOnClickListener(new OnClickListener() {
			@Override
			
			public void onClick(View v) {
				MyAdapter2 ma = new MyAdapter2(questionlist);
				lv_question.setAdapter(ma);
				
				for (int i = 0; i <= 9; i++) {
					int id = (Integer) questionlist.get(i).get("id");
					if(chooselist.get(id)==null){
						error_num++;
					}else{
						if(chooselist.get(id).equals(questionlist.get(i).get("correct_option"))){
							right_num++;
						}else{
							error_num++;
						}
					}
				}
				AchievementDao ad = new AchievementDao(TestActivity.this);
				Achievement am = new Achievement("成绩："+"正确："+right_num+" 错误："+error_num, sdf.format(new Date()));
				ad.insert(am);
				Toast.makeText(TestActivity.this, "成绩已保存", 1).show();
				//显示成绩
				tv_achievement.setText("成绩："+"正确："+right_num+" 错误："+error_num);
			}
		});
	}
	
	
	//开始答题页面
	class MyAdapter extends BaseAdapter{
		List<HashMap<String, Object>> data ;
		int i = 1;
		public MyAdapter(List<HashMap<String, Object>> questionlist, HashMap<Integer, String> chooselist) {
			this.data=questionlist;
		}
		
		@Override
		public int getCount() {
			if(data!=null){
				return data.size();
			}else{
				return 0;
			}
		}
		//根据参数arg0的position获取对应的数据对象（自动调用）
		@Override
		public Object getItem(int arg0) {
			return data.get(arg0);
		}
	
		@Override
		public long getItemId(int arg0) {
			return arg0;
		}
		
		@Override
		public View getView(final int arg0, View arg1, ViewGroup arg2) {
			//第一次没有布局先创建布局，之后滚动条滚动时界面View对象已经存在，新的数据更新，替代原来的位置	
				if(arg1 == null){
					arg1 = LayoutInflater.from(TestActivity.this).inflate(R.layout.test_item, null);
				}		
				final int id = (Integer) data.get(arg0).get("id");
				hashmap_saveid.put(i, id);
				i++;
				TextView topic = (TextView) arg1.findViewById(R.id.tv_topic);
				topic.setText(data.get(arg0).get("topic").toString()==null?"":data.get(arg0).get("topic").toString());
				
				TextView optionA = (TextView) arg1.findViewById(R.id.text_optionA);
				optionA.setText(data.get(arg0).get("optionA").toString()==null?"":data.get(arg0).get("optionA").toString());
				
				TextView optionB = (TextView) arg1.findViewById(R.id.text_optionB);
				optionB.setText(data.get(arg0).get("optionB").toString()==null?"":data.get(arg0).get("optionB").toString());
				
				TextView optionC = (TextView) arg1.findViewById(R.id.text_optionC);
				optionC.setText(data.get(arg0).get("optionC").toString()==null?"":data.get(arg0).get("optionC").toString());
				
				TextView optionD = (TextView) arg1.findViewById(R.id.text_optionD);
				optionD.setText(data.get(arg0).get("optionD").toString()==null?"":data.get(arg0).get("optionD").toString());
				
				
				final TextView choose_option = (TextView) arg1.findViewById(R.id.select_option);
				final StringBuilder sb = new StringBuilder();
				if(chooselist.get(id)==null){
					choose_option.setText("");
				}else{
					choose_option.setText(chooselist.get(id));
				}
				
				
				optionA.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						if(optiona == false){
							optiona = true;
							sb.append("a");
							choose_option.setText(sb);
							chooselist.put(id, choose_option.getText().toString());
						}else{
							optiona = false;
							String str = sb.toString();
							str = str.replace("a", "");
							sb.delete(0, sb.length());
							sb.append(str);
							
							choose_option.setText(sb);
							chooselist.put(id, choose_option.getText().toString());
						
						}
					}
				});
				
				optionB.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						if(optionb == false){
							optionb = true;
							sb.append("b");
							choose_option.setText(sb);
							chooselist.put(id, choose_option.getText().toString());
						}else{
							optionb = false;
							String str = sb.toString();
							str = str.replace("b", "");
							sb.delete(0, sb.length());
							sb.append(str);
							
							choose_option.setText(sb);
							chooselist.put(id, choose_option.getText().toString());
						
						}
					}
				});
				
				optionC.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						if(optionc == false){
							optionc = true;
							sb.append("c");
							choose_option.setText(sb);
							chooselist.put(id, choose_option.getText().toString());
						}else{
							optionc = false;
							String str = sb.toString();
							str = str.replace("c", "");
							sb.delete(0, sb.length());
							sb.append(str);
							
							choose_option.setText(sb);
							chooselist.put(id, choose_option.getText().toString());
						
						}
					}
				});
				
				optionD.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						if(optiond == false){
							optiond = true;
							sb.append("d");
							choose_option.setText(sb);
							chooselist.put(id, choose_option.getText().toString());
						}else{
							optiond = false;
							String str = sb.toString();
							str = str.replace("d", "");
							sb.delete(0, sb.length());
							sb.append(str);
							
							choose_option.setText(sb);
							chooselist.put(id, choose_option.getText().toString());
						
						}
					}
					
				});
				
//				correctlist.add(id, data.get(arg0).get("correct_option").toString());

				TextView correct = (TextView)arg1. findViewById(R.id.correct_option);
				correct.setText(data.get(arg0).get("correct_option")==null?"":data.get(arg0).get("correct_option").toString());

			return arg1;
			
		}
		
	}

	//显示成绩和答案页面
	class MyAdapter2 extends BaseAdapter{
		List<HashMap<String, Object>> data ; 
		int right_num =0;
		int error_num = 0;
		public MyAdapter2(List<HashMap<String, Object>> questionlist) {
			this.data=questionlist;
		}
		@Override
		public int getCount() {
			if(data!=null){
				return data.size();
			}else{
				return 0;
			}
		}
		
		public void print() {
			System.out.println("right_num:"+right_num);
		}
		
		//根据参数arg0的position获取对应的数据对象（自动调用）
		@Override
		public Object getItem(int arg0) {
			return data.get(arg0);
		}
	
		@Override
		public long getItemId(int arg0) {
			return arg0;
		}
		
		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			//第一次没有布局先创建布局，之后滚动条滚动时界面View对象已经存在，新的数据更新，替代原来的位置	
				if(arg1 == null){
					arg1 = LayoutInflater.from(TestActivity.this).inflate(R.layout.test_result, null);
				}	
				
				final int id = (Integer) data.get(arg0).get("id");
				final NotebookDao nd = new NotebookDao(TestActivity.this);
				final TestDao td = new TestDao(TestActivity.this);
				TextView topic = (TextView) arg1.findViewById(R.id.tv_topic);
				topic.setText(data.get(arg0).get("topic").toString()==null?"":data.get(arg0).get("topic").toString());
				
				
				TextView optionA = (TextView) arg1.findViewById(R.id.text_optionA);
				optionA.setText(data.get(arg0).get("optionA").toString()==null?"":data.get(arg0).get("optionA").toString());
				
				TextView optionB = (TextView) arg1.findViewById(R.id.text_optionB);
				optionB.setText(data.get(arg0).get("optionB").toString()==null?"":data.get(arg0).get("optionB").toString());
				
				TextView optionC = (TextView) arg1.findViewById(R.id.text_optionC);
				optionC.setText(data.get(arg0).get("optionC").toString()==null?"":data.get(arg0).get("optionC").toString());
				
				TextView optionD = (TextView) arg1.findViewById(R.id.text_optionD);
				optionD.setText(data.get(arg0).get("optionD").toString()==null?"":data.get(arg0).get("optionD").toString());
				
				final TextView choose_option = (TextView) arg1.findViewById(R.id.select_option);
				if(chooselist.get(id)==null){
					choose_option.setText("");
				}else{
					choose_option.setText(chooselist.get(id));
				}
				

				TextView correct = (TextView)arg1. findViewById(R.id.correct_option);
				correct.setText(data.get(arg0).get("correct_option")==null?"":data.get(arg0).get("correct_option").toString());

				final Button btn_add_notebook = (Button) arg1.findViewById(R.id.btn_notebook);
				
				if(add_notebook[id]==true){
					btn_add_notebook.setText("已添加");
				}else{
					btn_add_notebook.setText("加入错题本");
				}
				
				
				
				btn_add_notebook.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						
						Test test = td.queryItem(id);
						nd.insert(test);
						btn_add_notebook.setText("已添加");
						add_notebook[id] = true;
						
					}
				});
				
			return arg1;
			
		}
		
	}

}
