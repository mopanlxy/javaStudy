package com.linc.readdata;

import java.util.HashMap;
import java.util.List;

import com.example.testsystem.dao.AchievementDao;
import com.example.testsystem.dao.NotebookDao;
import com.linc.readdata.ErrorActivity.MyAdapter;
import com.linc.readdata.ErrorActivity.MyAdapter2;

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

public class AchievementActivity extends Activity {
	private ListView lv_question;
	private Button btn_clear;
	private List<HashMap<String, Object>> questionlist = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_achievement);
		
		lv_question = (ListView) findViewById(R.id.listView1);
		btn_clear = (Button) findViewById(R.id.btn_clear);

		AchievementDao ad = new AchievementDao(AchievementActivity.this);
		questionlist = ad.queryAll();
		MyAdapter ma = new MyAdapter(questionlist);
		lv_question.setAdapter(ma);
		lv_question.setOnItemClickListener(null);
		
		//������гɼ���¼
		btn_clear.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Builder builder = new AlertDialog.Builder(AchievementActivity.this);
				builder.setMessage("ȷ��Ҫ�����");
				final AchievementDao ad = new AchievementDao(AchievementActivity.this);
				builder.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						ad.deleteAll();						
						show();
					}
				});
				builder.setNegativeButton("ȡ��", null);
				builder.show();				
				ad.deleteAll();
			}
		});
		
		
	}

	//listview�ϵ���ʾ
		private void show() {
			AchievementDao ad = new AchievementDao(AchievementActivity.this);
			questionlist = ad.queryAll();
			
			if(questionlist!=null){
				MyAdapter ma = new MyAdapter(questionlist);
				lv_question.setAdapter(ma);
			}else{
				MyAdapter ma = new MyAdapter(questionlist);
				lv_question.setAdapter(ma);
			}
		}
	
	class MyAdapter extends BaseAdapter{
		List<HashMap<String, Object>> data ;
	
		public MyAdapter(List<HashMap<String, Object>> questionlist) {
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
					arg1 = LayoutInflater.from(AchievementActivity.this).inflate(R.layout.achievement_item, null);
				}		
				final int id = (Integer) data.get(arg0).get("id");
				
				TextView topic = (TextView) arg1.findViewById(R.id.text_achievement);
				topic.setText(data.get(arg0).get("information").toString()==null?"":data.get(arg0).get("information").toString());
				
				TextView correct = (TextView)arg1. findViewById(R.id.text_time);
				correct.setText(data.get(arg0).get("time")==null?"":data.get(arg0).get("time").toString());
	
			return arg1;
			
		}
		
	}

}
