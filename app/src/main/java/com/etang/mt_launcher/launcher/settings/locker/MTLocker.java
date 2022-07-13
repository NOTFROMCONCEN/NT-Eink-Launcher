package com.etang.mt_launcher.launcher.settings.locker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.etang.mt_launcher.R;
import com.etang.mt_launcher.launcher.MainActivity;
import com.etang.mt_launcher.tool.toast.DiyToast;
import com.etang.mt_launcher.tool.util.MTCore;

import java.text.SimpleDateFormat;

public class MTLocker extends AppCompatActivity {
    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);// 无Title
        setContentView(R.layout.activity_mtlocker);
        initView();
    }

    private void initView() {
        DiyToast.showToast(MTLocker.this, "已启用锁屏，长按任意位置退出。可在右上角更改其他设置", true);
        View my_locker_main_view = (View) findViewById(R.id.my_locker_main_view);
        my_locker_main_view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent intent = new Intent(MTLocker.this, MainActivity.class);
                startActivity(intent);
                finish();
                return true;
            }
        });
    }

    /**
     * 更新时间
     */
    private void new_time_Thread() {
        handler = new Handler();
        runnable = new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                SimpleDateFormat simpleDateFormat_hour = new SimpleDateFormat(
                        "HH");
                SimpleDateFormat simpleDateFormat_min = new SimpleDateFormat(
                        "mm");
                SimpleDateFormat simpleDateFormat_date = new SimpleDateFormat(
                        "yyyy/MM/dd");
                handler.postDelayed(runnable, 5000);
            }
        };
        handler.post(runnable);
    }

}