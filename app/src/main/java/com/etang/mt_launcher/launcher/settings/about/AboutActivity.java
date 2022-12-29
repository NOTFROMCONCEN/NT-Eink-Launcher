package com.etang.mt_launcher.launcher.settings.about;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.etang.mt_launcher.R;
import com.etang.mt_launcher.tool.mtcore.update.CheckUpdateDialog;
import com.etang.mt_launcher.tool.mtcore.dialog.FollwoMeDialog;
import com.etang.mt_launcher.tool.mtcore.dialog.MessageDialog;
import com.etang.mt_launcher.tool.mtcore.dialog.PayMeDialog;
import com.etang.mt_launcher.tool.mtcore.permission.SavePermission;
import com.etang.mt_launcher.tool.mtcore.toast.DiyToast;
import com.etang.mt_launcher.tool.mtcore.MTCore;

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
    private TextView tv_title, tv_button, tv_about_juanzeng, tv_about_showversion, tv_about_guanzhu;
    //返回LinearLayout
    private LinearLayout lv_back;
    //检查更新按钮
    private Button btn_about_checkup_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置填充屏幕
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //设置Activity没有title栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //随机开启页面
//        Random random = new Random();
//        int i = random.nextInt(10);
//        if (i >= 5) {
//            setContentView(R.layout.setting_about_fang);
//        } else {
//            setContentView(R.layout.setting_about_yuan);
//        }
        setContentView(R.layout.setting_about_fang);
        //绑定控件
        initView();
        //标题
        tv_title.setText(getString(R.string.string_about));
        tv_button.setText("打开WIFI设置");
        tv_button.setVisibility(View.VISIBLE);
        //按钮 文本
        tv_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                PayMeDialog.show_iconlink_dialog(AboutActivity.this);
//                Intent i = new Intent();
//                i = new Intent(Settings.ACTION_WIFI_SETTINGS);
//                startActivity(i);
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
//                checkupdate();
                Intent i = new Intent();
                i = new Intent(Settings.ACTION_WIFI_SETTINGS);
                startActivity(i);
            }
        });
        //关于检查更新 按钮 点击事件
        btn_about_checkup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkupdate();
            }
        });
        //关于捐赠 文本 点击事件
        tv_about_juanzeng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast提示
                MTCore.showToast(getApplicationContext(), "请注意：是否捐赠并不影响正常使用", true);
                //显示捐赠弹出框
                PayMeDialog.show_dialog(AboutActivity.this);
            }
        });
        //弹出关注方式Dialog
        tv_about_guanzhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast提示
                MTCore.showToast(getApplicationContext(), "使用对应APP扫码即可~~~~", true);
                //显示捐赠弹出框
                MTCore.FollwoMeDialog(AboutActivity.this);
            }
        });
        //设置版本号
//        tv_about_showversion.setText("梅糖桌面 Project" + "\n" + MTCore.get_my_appVERSIONNAME());
//        tv_about_showversion.setText("梅糖桌面 Project" + "\n" + BuildConfig.VERSION_NAME);
    }

    private void checkupdate() {
        if (MTCore.get_Now_AndroidVersion() == 19) {
            MTCore.MessageDialog("很抱歉，检测到您使用的是Android4.4（SDK=19），目前梅糖桌面的更新功能在此版本中有致命BUG，请通过“酷安”APP进行在线更新。或使用ADB安装最新版。",
                    AboutActivity.this);
        } else {
            //检查存储权限
            MTCore.check_save_permission(AboutActivity.this);
            //检查更新
            MTCore.CheckUpdate(AboutActivity.this, AboutActivity.this, "about");
        }
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
        tv_about_guanzhu = (TextView) findViewById(R.id.tv_about_guanzhu);
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
