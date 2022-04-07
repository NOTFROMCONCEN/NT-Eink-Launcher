package com.etang.mt_launcher.launcher.settings.uselogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.etang.mt_launcher.R;
import com.etang.mt_launcher.tool.sql.Bean_AppUseLog;
import com.etang.mt_launcher.tool.sql.ListAdapterHelper_AppUseLog;
import com.etang.mt_launcher.tool.sql.MyDataBaseHelper;

import java.util.ArrayList;
import java.util.List;

public class AppUseLogsActivity extends AppCompatActivity {
    //文本，返回，按钮，标题
    private TextView tv_button, tv_title;
    //返回LinearLayout
    private LinearLayout lv_back;
    //数据库
    private MyDataBaseHelper dbHelper;
    private SQLiteDatabase db;
    //ListView
    private ListView lv_1;
    private List<Bean_AppUseLog> list = new ArrayList<Bean_AppUseLog>();
    private ListAdapterHelper_AppUseLog listHelpAdapter;
    //总使用次数
    private int all_use_logs_num = 0;
    private TextView tv_uselogs_num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);// 无Title
        setContentView(R.layout.activity_app_use_logs);
        initView();//绑定控件
        //设置title栏点击事件
        tv_title.setText("App启动记录");
        tv_button.setText("点此清空记录");
        lv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //优先读取数据库并检查
        initSQL();
        //删除所有数据
        tv_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(AppUseLogsActivity.this);
                builder.setTitle("清空");
                builder.setMessage("确定要清空数据库吗？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db.execSQL("DELETE FROM appuselogs");
                        initSQL();
                    }
                });
                builder.setNegativeButton("取消", null);
                builder.show();
            }
        });
        //设置总使用次数统计
        tv_uselogs_num.setText("从开始统计直到现在，一共启动了" + all_use_logs_num + "次各种APP");
    }

    private void initSQL() {
        try {
            list.clear();
            lv_1.setAdapter(null);
            listHelpAdapter.setData(null);
            Cursor cursor = db.rawQuery("select * from appuselogs", null);
            if (cursor.getCount() != 0) {
                all_use_logs_num = cursor.getCount();
                for (int i = 0; i < cursor.getCount(); i++) {
                    cursor.moveToNext();
                    list.add(new Bean_AppUseLog(cursor.getString(cursor
                            .getColumnIndex("appname")), cursor.getString(cursor
                            .getColumnIndex("time"))));
                }
            }
            listHelpAdapter.setData(list);
            lv_1.setAdapter(listHelpAdapter);
        } catch (Exception e) {
            //存放APP使用记录
            db.execSQL("create table appuselogs (_id integer primary key autoincrement,appname text,time text)");
            //重新运行
            initSQL();
        }
    }


    private void initView() {
        tv_uselogs_num = (TextView) findViewById(R.id.tv_uselogs_num);
        tv_button = (TextView) findViewById(R.id.tv_title_button);
        tv_title = (TextView) findViewById(R.id.tv_title_text);
        lv_back = (LinearLayout) findViewById(R.id.lv_back);
        lv_1 = (ListView) findViewById(R.id.lv_appuselogs);
        dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db", null, 2);
        db = dbHelper.getWritableDatabase();
        listHelpAdapter = new ListAdapterHelper_AppUseLog(AppUseLogsActivity.this);
    }
}