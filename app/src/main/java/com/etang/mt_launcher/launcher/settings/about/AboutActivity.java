package com.etang.mt_launcher.launcher.settings.about;

import android.app.AlertDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.etang.mt_launcher.BuildConfig;
import com.etang.mt_launcher.R;
import com.etang.mt_launcher.tool.dialog.CheckUpdateDialog;
import com.etang.mt_launcher.tool.dialog.PayMeDialog;
import com.etang.mt_launcher.tool.permission.SavePermission;
import com.etang.mt_launcher.tool.toast.DiyToast;

/**
 * @ProjectName: NT-Eink-Launcher
 * @Package: com.etang.nt_launcher.launcher.settings.about
 * @ClassName: AboutActivity
 * @Description: 关于梅糖桌面功能相关界面
 * @Author: 作者名：奶油话梅糖
 * @CreateDate: 2021/5/29 0029 22:14
 * @UpdateUser: 更新者：奶油话梅糖
 * @UpdateDate: 2021/5/29 0029 22:14
 * @UpdateRemark: 更新说明：修复UI问题
 */
public class AboutActivity extends AppCompatActivity {
    private ImageView iv_about_logo;//关于 LOGO
    //文本，分别是文本_返回，文本_标题，文本_按钮，文本_关于捐赠我
    private TextView tv_title, tv_button, tv_about_juanzeng, tv_about_showversion;
    //返回LinearLayout
    private LinearLayout lv_back;
    //检查更新按钮
    private Button btn_about_checkup_button;
    //当前TAG
    private static String TAG = "AboutActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置填充屏幕
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //设置Activity没有title栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置xml界面
        setContentView(R.layout.setting_about);
        //绑定控件
        initView();
        //标题
        tv_title.setText(getString(R.string.string_about));
        tv_button.setText(getString(R.string.string_version));
        //按钮 文本
        tv_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //新建一个dialog
                new AlertDialog.Builder(AboutActivity.this)
                        .setTitle("部分图片来自：iconfont.cn")
                        .setMessage("图标（launcher icon）：小白熊_猫草君 | \"糖果\"icon")
                        .setNegativeButton("关闭", null).show();
            }
        });
        //返回 线性布局 点击事件
        lv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //关于logo 图片 点击事件
        iv_about_logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //检查存储权限
                SavePermission.check_save_permission(AboutActivity.this);
                //检查更新
                CheckUpdateDialog.check_update(AboutActivity.this, AboutActivity.this, "about");
            }
        });
        //关于检查更新 按钮 点击事件
        btn_about_checkup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //检查存储权限
                SavePermission.check_save_permission(AboutActivity.this);
                //检查更新
                CheckUpdateDialog.check_update(AboutActivity.this, AboutActivity.this, "about");
            }
        });
        //关于捐赠 文本 点击事件
        tv_about_juanzeng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast提示
                DiyToast.showToast(getApplicationContext(), "请注意：是否捐赠并不影响正常使用", true);
                //显示捐赠弹出框
                PayMeDialog.show_dialog(AboutActivity.this);
            }
        });
        //设置版本号
        tv_about_showversion.setText("梅糖桌面 Project" + "\n" + BuildConfig.VERSION_NAME);
    }

    /**
     * 绑定控件
     */
    private void initView() {
        tv_about_showversion = (TextView) findViewById(R.id.tv_about_showversion);
        lv_back = (LinearLayout) findViewById(R.id.lv_back);
        iv_about_logo = (ImageView) findViewById(R.id.iv_about_logo);
        tv_button = (TextView) findViewById(R.id.tv_title_button);
        tv_title = (TextView) findViewById(R.id.tv_title_text);
        btn_about_checkup_button = (Button) findViewById(R.id.btn_about_checkup_button);
        tv_about_juanzeng = (TextView) findViewById(R.id.tv_about_juanzeng);
    }

    /**
     * 设置当前Activity结束时，无动画
     */
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }
}
