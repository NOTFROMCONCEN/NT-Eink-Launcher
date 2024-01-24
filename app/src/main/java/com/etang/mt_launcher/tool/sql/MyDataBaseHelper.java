package com.etang.mt_launcher.tool.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.etang.mt_launcher.tool.mtcore.MTCore;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MyDataBaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_HELPER_TAG = "MyDataBaseHelper";
    private Context context;

    public MyDataBaseHelper(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL("CREATE TABLE name (_id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT)");

            // 使用SQLiteStatement进行参数化插入
            String insertNameSql = "INSERT INTO name (username) VALUES (?)";
            SQLiteStatement statement = db.compileStatement(insertNameSql);
            statement.bindString(1, ""); // 绑定第一个参数（索引从1开始）
            statement.executeInsert();

            db.execSQL("CREATE TABLE wather_city (_id INTEGER PRIMARY KEY AUTOINCREMENT, city TEXT)");

            String insertCitySql = "INSERT INTO wather_city (city) VALUES (?)";
            SQLiteStatement cityStatement = db.compileStatement(insertCitySql);
            cityStatement.bindString(1, "上海");
            cityStatement.executeInsert();

            db.execSQL("CREATE TABLE appuselogs (_id INTEGER PRIMARY KEY AUTOINCREMENT, appname TEXT, time TEXT)");
        } catch (Exception e) {
            MTCore.ErrorDialog(context, e.toString(), DATABASE_HELPER_TAG);
        }
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
    }
}
