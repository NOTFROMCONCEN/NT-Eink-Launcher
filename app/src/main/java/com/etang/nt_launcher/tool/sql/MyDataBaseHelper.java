package com.etang.nt_launcher.tool.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.etang.nt_launcher.tool.dialog.DeBugDialog;
import com.etang.nt_launcher.tool.toast.DiyToast;

/**
 * @Package: com.etang.nt_launcher.tool.sql
 * @ClassName: MyDataBaseHelper
 * @Description: 数据库
 * @CreateDate: 2021/3/19 8:27
 * @UpdateDate: 2021/3/19 8:27
 */
public class MyDataBaseHelper extends SQLiteOpenHelper {
    //当前页面TAG
    private static String TAG = "MyDataBaseHelper";
    //继承Context
    private Context context;

    public MyDataBaseHelper(Context context, String name,
                            CursorFactory factory, int version) {
        super(context, name, factory, version);
        // TODO Auto-generated constructor stub
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        try {
            //存放昵称
            db.execSQL("create table name(_id integer primary key autoincrement,username text)");
            //插入默认值
            db.execSQL("insert into name(username)values(?)", new String[]{""});
            //存放天气地理位置
            db.execSQL("create table wather_city(_id integer primary key autoincrement,city text)");
            //插入默认值
            db.execSQL("insert into wather_city(city)values(?)",
                    new String[]{"上海"});
        } catch (Exception e) {
            DeBugDialog.debug_show_dialog(context, String.valueOf(e), TAG);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
    }
}
