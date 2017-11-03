package com.linc.readdata;

import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.testsystem.dao.NotebookDao;
import com.example.testsystem.dao.PractiseDao;
import com.example.testsystem.dao.TestDao;
import com.example.testsystem.entity.Practise;
import com.example.testsystem.entity.Test;

public class PractiseActivity extends Activity {
	private ListView lv_question;
	private Button btn_answer,btn_check;
	private List<HashMap<String, Object>> questionlist = null;
	private HashMap<Integer, String> chooselist = new HashMap<Integer, String>();
	boolean optiona = false;
	boolean optionb = false;
	boolean optionc = false;
	boolean optiond = false;
	private TextView topic;
	private String chapter_name = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_practise);
		
		lv_question = (ListView) findViewById(R.id.listView1);
		btn_answer = (Button) findViewById(R.id.btn_answer);
		btn_check = (Button) findViewById(R.id.check_answer);
		
		//根据章节的名字来找出对应的习题
		Intent getIntent = this.getIntent();
		chapter_name = getIntent.getStringExtra("chapter_name");
		PractiseDao pd = new PractiseDao(PractiseActivity.this);
		questionlist = pd.queryAll(chapter_name);
		MyAdapter ma = new MyAdapter(questionlist,chooselist);
		lv_question.setAdapter(ma);
		lv_question.setOnItemClickListener(null);
		
		
		//显示答案功能
		btn_answer.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				MyAdapter2 ma = new MyAdapter2(questionlist);
				lv_question.setAdapter(ma);
			}
		});
		
		
	}

	
	//开始做题页面
	class MyAdapter extends BaseAdapter{
		List<HashMap<String, Object>> data ;

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
					arg1 = LayoutInflater.from(PractiseActivity.this).inflate(R.layout.pracitse_item, null);
				}		
				final int id = (Integer) data.get(arg0).get("id");
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

	
	//显示答案的页面
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
					arg1 = LayoutInflater.from(PractiseActivity.this).inflate(R.layout.pracitse_item_result, null);
				}	
				
				final int id = (Integer) data.get(arg0).get("id");
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
				
				if(chooselist.get(id)==null || chooselist.get(id).trim().equals("")){
					choose_option.setText("");
				}else{
					choose_option.setText(chooselist.get(id));
				}

//				correctlist.add(id, data.get(arg0).get("correct_option").toString());
				TextView correct = (TextView)arg1. findViewById(R.id.correct_option);
				correct.setText(data.get(arg0).get("correct_option")==null?"":data.get(arg0).get("correct_option").toString());

			return arg1;
			
		}
		
	}

}
