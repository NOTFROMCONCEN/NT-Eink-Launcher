package com.etang.mt_launcher.launcher.settings.locker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.etang.mt_launcher.R;
import com.etang.mt_launcher.launcher.MainActivity;
import com.etang.mt_launcher.launcher.welecome.WelecomeActivity;
import com.etang.mt_launcher.tool.toast.DiyToast;
import com.etang.mt_launcher.tool.util.MTCore;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MTLocker extends AppCompatActivity {
    private Handler handler;
    private Runnable runnable;
    private ImageView iv_locker_xuanzhuan;
    private TextView tv_locker_nowtime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);// 无Title
        setContentView(R.layout.activity_mtlocker);
        initView();
        new_time_Thread();
        iv_locker_xuanzhuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rollScreen();
            }
        });
    }

    private void rollScreen() {
        if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        else if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }


    private void initView() {
        tv_locker_nowtime = (TextView) findViewById(R.id.tv_locker_nowtime);
        iv_locker_xuanzhuan = (ImageView) findViewById(R.id.iv_locker_xuanzhuan);
        DiyToast.showToast(MTLocker.this, "已启用待机，长按任意位置退出", true);
        View my_locker_main_view = (View) findViewById(R.id.my_locker_main_view);
        my_locker_main_view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                handler.removeCallbacks(runnable);
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
                String time = simpleDateFormat_hour.format(new Date()) + ":" + simpleDateFormat_min.format(new Date());
                tv_locker_nowtime.setText(time);
                handler.postDelayed(runnable, 5000);
                Log.e("LOCKER", "run: 锁屏界面获取时间运行中");
            }
        };
        handler.post(runnable);
    }

}