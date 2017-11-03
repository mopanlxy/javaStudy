package com.linc.readdata;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import jxl.Sheet;
import jxl.Workbook;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testsystem.dao.AchievementDao;
import com.example.testsystem.dao.TestDao;
import com.example.testsystem.entity.Test;

public class MainActivity extends Activity {
	private ImageButton btn_test,btn_practise,btn_error_notebook,btn_achievement,btn_about;
	private Button btn_import;
	TextView txt = null;
	String DB_PATH = "/data/data/com.linc.readdata/databases/";
	String DB_NAME = "test.db";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn_test = (ImageButton) findViewById(R.id.ImageButton_testsystem);
		btn_practise = (ImageButton) findViewById(R.id.ImageButton_practise);
		btn_error_notebook = (ImageButton) findViewById(R.id.ImageButton_notebook);
		btn_achievement = (ImageButton) findViewById(R.id.ImageButton_achievement);
		//btn_import = (Button) findViewById(R.id.btn_import);
		btn_about = (ImageButton) findViewById(R.id.ImageButton_about);
		
		  //将题目数据库导入到手机当中
		  // 检查 SQLite 数据库文件是否存在
		  if ((new File(DB_PATH + DB_NAME)).exists() == false) {
			   // 如 SQLite 数据库文件不存在，再检查一下 database 目录是否存在
			   File f = new File(DB_PATH);
			   // 如 database 目录不存在，新建该目录
			   if (!f.exists()) {
				   f.mkdir();
			   }
			   try {
			    // 得到 assets 目录下我们实现准备好的 SQLite 数据库作为输入流
			    InputStream is = getBaseContext().getAssets().open(DB_NAME);
			    // 输出流
			    OutputStream os = new FileOutputStream(DB_PATH + DB_NAME);
			    // 文件写入
			    byte[] buffer = new byte[1024];
			    int length;
			    while ((length = is.read(buffer)) > 0) {
			    	os.write(buffer, 0, length);
			    }
			    // 关闭文件流
			    os.flush();
			    os.close();
			    is.close();
			   } catch (Exception e) {
			    e.printStackTrace();
			   }
		  }
		  
		
		
		try {
			//readExcel();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//进入考试系统
		btn_test.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Builder builder = new AlertDialog.Builder(
				MainActivity.this);
				builder.setTitle("进入之后系统将随机抽取一部分题进行测试，确定继续吗");
				builder.setPositiveButton("确定",new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								Intent intent_test = new Intent(MainActivity.this,TestActivity.class);
								startActivity(intent_test);
							}
						});
				builder.setNegativeButton("取消", null);
				builder.show();
				
				
			}
		});
		
		//进入章节练习
		btn_practise.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent_praciselist = new Intent(MainActivity.this,PractiselistActivity.class);
				startActivity(intent_praciselist);
				
			}
		});
		
		//进入错题本
		btn_error_notebook.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent_notebook = new Intent(MainActivity.this,ErrorActivity.class);
				startActivity(intent_notebook);
				
			}
		});

		//进入成绩查询
		btn_achievement.setOnClickListener(new OnClickListener() {
	
			@Override
			public void onClick(View v) {
				Intent intent_achievement = new Intent(MainActivity.this,AchievementActivity.class);
				startActivity(intent_achievement);
				
			}
		});

//		btn_import.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
////				Builder builder = new AlertDialog.Builder(
////						MainActivity.this);
////				builder.setTitle("修改当前群组名：");
////				final EditText et_contactgroup = new EditText(MainActivity.this);
////				builder.setView(et_contactgroup);
////				builder.setPositiveButton("确定",new DialogInterface.OnClickListener() {
////							@Override
////							public void onClick(DialogInterface dialog,
////									int which) {
////								Aftercontactgroup_name = et_contactgroup.getText().toString();
////								boolean updateflag = false;
////								for (int i = 0; i < contactgrouplist.size(); i++) {
////									if (contactgrouplist.get(i).get("group_name").toString().trim().equals(Aftercontactgroup_name.trim())) {
////										updateflag = true;
////									}
////								}
////
////								if (!updateflag) {
////									if(!Aftercontactgroup_name.equals("")){
////										ContactgroupDao cgd = new ContactgroupDao(ContactGroupManager_itemActivity.this);
////										cgd.update(contactgroup_id,Aftercontactgroup_name);
////										show();
////										tv_contactgrouptitle.setText(et_contactgroup.getText().toString());
////										Toast.makeText(ContactGroupManager_itemActivity.this,"修改成功", 0).show();
////									}
////								} else {
////									Toast.makeText(ContactGroupManager_itemActivity.this,"群组名已存在", 0).show();
////								}
////
////							}
////						});
////				builder.setNegativeButton("取消", null);
////				builder.show();
//				
//			}
//		});
		
		//关于
		btn_about.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Builder builder = new AlertDialog.Builder(MainActivity.this);
				builder.setTitle("关于软件");
				builder.setMessage("版本号：1.0  设计人：梁秀宇 2015年5月");
				builder.setPositiveButton("确定", null);
				builder.show();
			}
		});
	}

	//从excel文档中
	public void readExcel() throws Exception {
//		  try {
			  
			   TestDao td = new TestDao(MainActivity.this);
			   File sdCardFile = Environment.getExternalStorageDirectory();
			   File file = new File(sdCardFile,"test.xls");
			   InputStream is = new FileInputStream(file);
			   //Workbook book = Workbook.getWorkbook(new File("mnt/sdcard/test.xls"));
			   Workbook book = Workbook.getWorkbook(is);
			   
			   int num = book.getNumberOfSheets();
//			   txt.setText("the num of sheets is " + num+ "\n");
			   // 鑾峰緱绗竴涓伐浣滆〃瀵硅薄
			   Sheet sheet = book.getSheet(0);
	//		   int Rows = sheet.getRows();
	//		   int Cols = sheet.getColumns();
	//		   txt.append("the name of sheet is " + sheet.getName() + "\n");
	//		   txt.append("total rows is " + Rows + "\n");
	//		   txt.append("total cols is " + Cols + "\n");
	//		   for (int i = 0; i < Cols; ++i) {
	//		    for (int j = 0; j < Rows; ++j) {
	//		     // getCell(Col,Row)鑾峰緱鍗曞厓鏍肩殑鍊�		     	txt.append("contents:" + sheet.getCell(i,j).getContents() + "\n");
	//		    }
	//		   }
			   
			    int rowNum = 2; 	// 行标
				int colNum = 3; 	// 列标
				for (rowNum = 2; rowNum < 14; rowNum++) {
					// 获取第rowNum行
					//row = sheet.getRow((short) rowNum);
					Test test = new Test();
					for (colNum = 3; colNum < 81; colNum++) {
						//cell = row.getCell((short) colNum);// 根据当前行的位置来创建一个单元格对象
						if(colNum == 3){
							test.setTopic(sheet.getCell(colNum, rowNum).getContents());
						}else if(colNum == 4){
							test.setOptionA(sheet.getCell(colNum, rowNum).getContents());
						}else if(colNum == 5){
							test.setOptionB(sheet.getCell(colNum, rowNum).getContents());
						}else if(colNum == 6){
							
							test.setOptionC(sheet.getCell(colNum, rowNum).getContents());
						}else if(colNum == 7){
							test.setOptionD(sheet.getCell(colNum, rowNum).getContents());
						}else if(colNum == 8){
							test.setCorrect_option(sheet.getCell(colNum, rowNum).getContents());
						}
						//System.out.println(sheet.getCell(rowNum, colNum).toString());// 获取当前单元格中的内容
					}
					td.insert(test);
					//System.out.println(test.toString());// 获取当前单元格中的内容
					
				} 
		  
				book.close();
	}
}
