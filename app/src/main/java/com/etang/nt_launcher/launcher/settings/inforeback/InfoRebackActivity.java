package com.etang.nt_launcher.launcher.settings.inforeback;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.etang.nt_launcher.R;

/**
 * 意见反馈页面
 */
public class InfoRebackActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        //全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);// 无Title
        setContentView(R.layout.setting_inforeback);

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }

}
