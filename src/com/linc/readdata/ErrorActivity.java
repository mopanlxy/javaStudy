package com.linc.readdata;

import java.util.HashMap;
import java.util.List;

import com.example.testsystem.dao.NotebookDao;
import com.example.testsystem.dao.PractiseDao;
import com.linc.readdata.PractiseActivity.MyAdapter;
import com.linc.readdata.PractiseActivity.MyAdapter2;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class ErrorActivity extends Activity {
	
	
	private ListView lv_question;
	private Button btn_answer,btn_check;
	private List<HashMap<String, Object>> questionlist = null;
	private HashMap<Integer, String> chooselist = new HashMap<Integer, String>();
	boolean optiona = false;
	boolean optionb = false;
	boolean optionc = false;
	boolean optiond = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_error);
		
		lv_question = (ListView) findViewById(R.id.listView1);
		btn_answer = (Button) findViewById(R.id.btn_answer);
		btn_check = (Button) findViewById(R.id.check_answer);
		lv_question.setOnItemClickListener(null);
		
		final NotebookDao nbd = new NotebookDao(ErrorActivity.this);
		questionlist = nbd.queryAll();
		MyAdapter ma = new MyAdapter(questionlist,chooselist);
		lv_question.setAdapter(ma);
		//��ʾ�𰸹���
		btn_answer.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				MyAdapter2 ma = new MyAdapter2(questionlist);
				lv_question.setAdapter(ma);
			}
		});
	}
	
	//listview�ϵ���ʾ
	private void show() {
		NotebookDao nbd = new NotebookDao(ErrorActivity.this);
		questionlist = nbd.queryAll();
		//System.out.println("questionlist:"+questionlist);
		if(questionlist!=null){
			MyAdapter ma = new MyAdapter(questionlist,chooselist);
			lv_question.setAdapter(ma);
		}else{
			MyAdapter2 ma = new MyAdapter2(questionlist);
			lv_question.setAdapter(ma);
		}
	}

	//��ʼ�����ҳ��
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
		//���ݲ���arg0��position��ȡ��Ӧ�����ݶ����Զ����ã�
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
			//��һ��û�в����ȴ������֣�֮�����������ʱ����View�����Ѿ����ڣ��µ����ݸ��£����ԭ����λ��	
				if(arg1 == null){
					arg1 = LayoutInflater.from(ErrorActivity.this).inflate(R.layout.error_item, null);
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
				
				Button btn_remove = (Button) arg1.findViewById(R.id.btn_remove);
				btn_remove.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						Builder builder = new AlertDialog.Builder(ErrorActivity.this);
						builder.setMessage("ȷ��Ҫɾ����");
						builder.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								NotebookDao nd = new NotebookDao(ErrorActivity.this);
								nd.delete(id);
								show();
							}
						});
						builder.setNegativeButton("ȡ��", null);
						builder.show();
					}
				});
				
				final TextView choose_option = (TextView) arg1.findViewById(R.id.select_option);
				final StringBuilder sb = new StringBuilder();
				
				//��ֹѡ������г�������
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
				
	//			correctlist.add(id, data.get(arg0).get("correct_option").toString());
	
				TextView correct = (TextView)arg1. findViewById(R.id.correct_option);
				correct.setText(data.get(arg0).get("correct_option")==null?"":data.get(arg0).get("correct_option").toString());
	
			return arg1;
			
		}
	}
	
	//�л��ɴ𰸵�ҳ��
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
		
		//���ݲ���arg0��position��ȡ��Ӧ�����ݶ����Զ����ã�
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
			//��һ��û�в����ȴ������֣�֮�����������ʱ����View�����Ѿ����ڣ��µ����ݸ��£����ԭ����λ��	
				if(arg1 == null){
					arg1 = LayoutInflater.from(ErrorActivity.this).inflate(R.layout.error_item_result, null);
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
				if(chooselist.get(id)==null){
					choose_option.setText("");
				}else{
					choose_option.setText(chooselist.get(id));
				}
				
				Button btn_remove = (Button) arg1.findViewById(R.id.btn_remove);
				btn_remove.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						
						Builder builder = new AlertDialog.Builder(ErrorActivity.this);
						builder.setMessage("ȷ��Ҫɾ����");
						builder.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								NotebookDao nd = new NotebookDao(ErrorActivity.this);
								nd.delete(id);
								show();
							}
						});
						builder.setNegativeButton("ȡ��", null);
						builder.show();
					}
				});
				
				TextView correct = (TextView)arg1. findViewById(R.id.correct_option);
				correct.setText(data.get(arg0).get("correct_option")==null?"":data.get(arg0).get("correct_option").toString());
	
			return arg1;
			
		}
		
	}
}
