package com.etang.nt_launcher.launcher.settings.inforeback;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.etang.nt_launcher.R;

/**
 * @Package: com.etang.nt_launcher.launcher.settings.inforeback
 * @ClassName: InfoRebackActivity
 * @Description: “意见反馈”界面
 * @CreateDate: 2021/3/19 8:15
 * @UpdateDate: 2021/3/19 8:15
 */
public class InfoRebackActivity extends AppCompatActivity {

    private TextView tv_title_button, tv_title_text;
    private LinearLayout lv_back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        //全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);// 无Title
        setContentView(R.layout.setting_inforeback);
        initView();
        lv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        tv_title_button = (TextView) findViewById(R.id.tv_title_button);
        tv_title_button.setVisibility(View.INVISIBLE);
        tv_title_text = (TextView) findViewById(R.id.tv_title_text);
        tv_title_text.setText("信息反馈");
        lv_back = (LinearLayout) findViewById(R.id.lv_back);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }

}
