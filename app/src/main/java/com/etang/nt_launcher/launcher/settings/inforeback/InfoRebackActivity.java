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
 * @ProjectName: NT-Eink-Launcher
 * @Package: com.etang.nt_launcher.launcher.settings.about
 * @ClassName: HindAppSetting
 * @Description: 梅糖桌面信息反馈相关界面和功能
 * @Author: 作者名：奶油话梅糖
 * @CreateDate: 2021/5/29 0029 22:14
 * @UpdateUser: 更新者：奶油话梅糖
 * @UpdateDate: 2021/5/29 0029 22:14
 * @UpdateRemark: 更新说明：修复UI问题
 */
public class InfoRebackActivity extends AppCompatActivity {
    //文本，按钮，返回
    private TextView tv_title_button, tv_title_text;
    //返回LinearLayout
    private LinearLayout lv_back;
    //当前TAG
    private static String TAG = "InfoRebackActivity";


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

    /**
     * 绑定控件
     */
    private void initView() {
        tv_title_button = (TextView) findViewById(R.id.tv_title_button);
        tv_title_button.setVisibility(View.INVISIBLE);
        tv_title_text = (TextView) findViewById(R.id.tv_title_text);
        tv_title_text.setText("信息反馈");
        lv_back = (LinearLayout) findViewById(R.id.lv_back);
    }


    /**
     * 结束当前Activity时，关闭所有动画
     */
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }

}
