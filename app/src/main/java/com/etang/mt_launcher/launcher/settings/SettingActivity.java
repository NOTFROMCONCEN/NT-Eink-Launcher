package com.etang.mt_launcher.launcher.settings;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.etang.mt_launcher.R;
import com.etang.mt_launcher.launcher.MainActivity;
import com.etang.mt_launcher.launcher.settings.about.AboutActivity;
import com.etang.mt_launcher.launcher.settings.desktopsetting.DeskTopSettingActivity;
import com.etang.mt_launcher.launcher.settings.hindapp.HindAppSetting;
import com.etang.mt_launcher.launcher.settings.inforeback.InfoRebackActivity;
import com.etang.mt_launcher.launcher.settings.launcherimage.ChoseImagesActivity;
import com.etang.mt_launcher.launcher.settings.textsizesetting.TextSizeSetting;
import com.etang.mt_launcher.launcher.welecome.WelecomeActivity;
import com.etang.mt_launcher.tool.dialog.UnInstallDialog;
import com.etang.mt_launcher.tool.sql.MyDataBaseHelper;
import com.etang.mt_launcher.tool.toast.DiyToast;

/**
 * @Package: com.etang.nt_launcher.launcher.settings
 * @ClassName: SettingActivity
 * @Description: “设置”界面
 * @CreateDate: 2021/3/19 8:16
 * @UpdateDate: 2021/5/29 21:52
 */
public class SettingActivity extends Activity {
    //线性布局LinearLayout，
    // 返回、标题签名设置、重启桌面设置、
    // 消息反馈设置、APP列表设置、
    // 关于桌面设置、桌面设置、退出桌面设置、
    // 隐藏APP设置、签名设置、卸载桌面设置
    LinearLayout lv_back, lv_restart_setting, lv_inforeback_activity,
            lv_textsize_setting, lv_applist_setting, lv_about_activity, lv_desktop_setting,
            lv_hindapp_setting, lv_name_setting, lv_uninstall_setting;
    //复选框，隐藏设置图标（底栏）、离线模式、老年模式
    private CheckBox cb_hind_setting_ico, cb_setting_offlinemode, cb_setting_oldmanmode;
    //数据库
    private MyDataBaseHelper dbHelper_name_sql;
    private SQLiteDatabase db;
    //文本，返回、按钮、标题
    private TextView tv_button, tv_title;
    //当前页面TAG
    private static String TAG = "SettingActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        //全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);// 无Title
        setContentView(R.layout.activity_setting_new);
        initView();//绑定控件
        tv_title.setText("桌面设置");
        lv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //设置title文本
        tv_button.setText("语言|权限|说明书");
        //设置title点击事件
        tv_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WelecomeActivity.class);
                intent.putExtra("state", "false");
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });
        //壁纸设置
        lv_desktop_setting.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingActivity.this, ChoseImagesActivity.class));
                overridePendingTransition(0, 0);
            }
        });
        //打开关于界面
        lv_about_activity.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingActivity.this, AboutActivity.class));
                overridePendingTransition(0, 0);
            }
        });
        //桌面应用列表设置
        lv_applist_setting.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingActivity.this, DeskTopSettingActivity.class));
                overridePendingTransition(0, 0);
            }
        });
        //文本大小设置
        lv_textsize_setting.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingActivity.this, TextSizeSetting.class));
                overridePendingTransition(0, 0);
            }
        });
        //应用管理设置
        lv_hindapp_setting.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingActivity.this, HindAppSetting.class));
                overridePendingTransition(0, 0);
            }
        });
        //消息反馈
        lv_inforeback_activity.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingActivity.this, InfoRebackActivity.class));
                overridePendingTransition(0, 0);
            }
        });
        //卸载“奶糖桌面”
        lv_uninstall_setting.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                UnInstallDialog.UninstallApk(SettingActivity.this, SettingActivity.this, getPackageName());
            }
        });
        //重启APP
        lv_restart_setting.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
        //昵称设置
        lv_name_setting.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                show_name_dialog();
            }
        });
        //绑定sharedPreferences本地存储
        SharedPreferences sharedPreferences = getSharedPreferences("info", MODE_PRIVATE);
        String ico_info = sharedPreferences.getString("setting_ico_hind", null);
        String offline_info = sharedPreferences.getString("offline", null);
        String oldman_info = sharedPreferences.getString("oldman", null);
        try {
            //判断隐藏底栏是否被选中
            if (ico_info.equals("true")) {
                cb_hind_setting_ico.setChecked(true);
//                MainActivity.check_view_hind(SettingActivity.this, sharedPreferences);
            } else {
                cb_hind_setting_ico.setChecked(false);
//                MainActivity.check_view_hind(SettingActivity.this, sharedPreferences);
            }
            //判断离线模式是否被选中
            if (offline_info.equals("true")) {
                cb_setting_offlinemode.setChecked(true);
            } else {
                cb_setting_offlinemode.setChecked(false);
            }
            //判断老年模式是否被选中
            if (oldman_info.equals("true")) {
                cb_setting_oldmanmode.setChecked(true);
            } else {
                cb_setting_oldmanmode.setChecked(false);
            }
        } catch (Exception e) {
            //如果检查上面三个模式状态出现问题时，插入默认数据
            SharedPreferences.Editor editor = getSharedPreferences("info", MODE_PRIVATE).edit();
            editor.putString("setting_ico_hind", "false");//关闭隐藏底栏
            editor.putString("offline", "false");//关闭离线模式
            editor.putString("oldman", "false");//关闭老年模式
            editor.apply();
        }
        //老年模式选中事件监听
        cb_setting_oldmanmode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cb_setting_offlinemode.setChecked(true);
                    SharedPreferences.Editor editor = getSharedPreferences("info", MODE_PRIVATE).edit();
                    editor.putString("oldman", "true");//日期文本大小
                    editor.apply();
                    DiyToast.showToast(SettingActivity.this, "有时需要重启设备以应用更改，请使用“重载梅糖桌面”功能", true);
                    SharedPreferences sharedPreferences = getSharedPreferences("info", MODE_PRIVATE);
                    MainActivity.check_oldman_mode(SettingActivity.this, sharedPreferences);
                } else {
                    cb_setting_offlinemode.setChecked(false);
                    SharedPreferences.Editor editor = getSharedPreferences("info", MODE_PRIVATE).edit();
                    editor.putString("oldman", "false");//日期文本大小
                    editor.apply();
                    DiyToast.showToast(SettingActivity.this, "有时需要重启设备以应用更改，请使用“重载梅糖桌面”功能", true);
                    SharedPreferences sharedPreferences = getSharedPreferences("info", MODE_PRIVATE);
                    MainActivity.check_oldman_mode(SettingActivity.this, sharedPreferences);
                }
            }
        });
        //隐藏底栏模式选中事件监听
        cb_hind_setting_ico.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    SharedPreferences.Editor editor = getSharedPreferences("info", MODE_PRIVATE).edit();
                    editor.putString("setting_ico_hind", "true");//日期文本大小
                    editor.apply();
                    DiyToast.showToast(SettingActivity.this, "有时需要重启设备以应用更改，请使用“重载梅糖桌面”功能，长按桌面中的“小时”可以打开设置", true);
                    SharedPreferences sharedPreferences = getSharedPreferences("info", MODE_PRIVATE);
                    MainActivity.check_view_hind(SettingActivity.this, sharedPreferences);
                } else {
                    SharedPreferences.Editor editor = getSharedPreferences("info", MODE_PRIVATE).edit();
                    editor.putString("setting_ico_hind", "false");//日期文本大小
                    editor.apply();
                    DiyToast.showToast(SettingActivity.this, "有时需要重启设备以应用更改，请使用“重载梅糖桌面”功能，长按桌面中的“小时”可以打开设置", true);
                    SharedPreferences sharedPreferences = getSharedPreferences("info", MODE_PRIVATE);
                    MainActivity.check_view_hind(SettingActivity.this, sharedPreferences);
                }
            }
        });
        //离线模式选中事件监听
        cb_setting_offlinemode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    cb_setting_oldmanmode.setChecked(true);
                    SharedPreferences.Editor editor = getSharedPreferences("info", MODE_PRIVATE).edit();
                    editor.putString("offline", "true");//日期文本大小
                    editor.apply();
                    DiyToast.showToast(SettingActivity.this, "有时需要重启设备以应用更改，请使用“重载梅糖桌面”功能", true);
                    SharedPreferences sharedPreferences = getSharedPreferences("info", MODE_PRIVATE);
                    MainActivity.check_offline_mode(SettingActivity.this, sharedPreferences);
                } else {
                    cb_setting_oldmanmode.setChecked(false);
                    SharedPreferences.Editor editor = getSharedPreferences("info", MODE_PRIVATE).edit();
                    editor.putString("offline", "false");//日期文本大小
                    editor.apply();
                    DiyToast.showToast(SettingActivity.this, "有时需要重启设备以应用更改，请使用“重载梅糖桌面”功能", true);
                    SharedPreferences sharedPreferences = getSharedPreferences("info", MODE_PRIVATE);
                    MainActivity.check_offline_mode(SettingActivity.this, sharedPreferences);
                }
            }
        });
    }
//
//    private void show_dialog() {
//        String version = BuildConfig.VERSION_NAME;
//        AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
//        builder.setMessage("当前版本\n" + version);
//        builder.setPositiveButton("我知道了", null);
//        builder.show();
//    }

    /**
     * 设置主界面的顶部文案
     */
    private void show_name_dialog() {
        //新建一个dialog
        final AlertDialog builder = new AlertDialog.Builder(
                SettingActivity.this).create();
        //绑定一个自定义View
        View view = LayoutInflater.from(SettingActivity.this).inflate(
                R.layout.dialog_name_show, null, false);
        //设置自定义view
        builder.setView(view);
        final EditText et_name_get = (EditText) view
                .findViewById(R.id.et_title_name);
        final RadioButton ra_0 = (RadioButton) view
                .findViewById(R.id.radio0);
        final RadioButton ra_1 = (RadioButton) view
                .findViewById(R.id.radio1);
        final RadioButton ra_2 = (RadioButton) view
                .findViewById(R.id.radio2);
        final RadioButton ra_3 = (RadioButton) view
                .findViewById(R.id.radio3);
        final Button btn_con = (Button) view.findViewById(R.id.btn_dialog_rename_con);
        final Button btn_cls = (Button) view.findViewById(R.id.btn_dialog_rename_cls);
        //关闭按钮
        btn_cls.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.dismiss();
            }
        });
        //确定按钮
        btn_con.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //判断文本框、单选按钮是否被选中
                if (et_name_get.getText().toString().isEmpty()
                        && !ra_0.isChecked() && !ra_2.isChecked()
                        && !ra_3.isChecked() && !ra_1.isChecked()) {
                    //插入空白数据
                    db.execSQL("update name set username = ?",
                            new String[]{""});
                } else {
                    /**
                     * 很老的代码，等待更新
                     */
                    //判断哪个单选按钮被选中
                    if (ra_0.isChecked() || ra_1.isChecked()
                            || ra_2.isChecked() || ra_3.isChecked()) {
                        if (ra_0.isChecked()) {
                            db.execSQL(
                                    "update name set username = ?",
                                    new String[]{ra_0.getText()
                                            .toString() + getString(R.string.name_ebook)});
                        }
                        if (ra_1.isChecked()) {
                            db.execSQL(
                                    "update name set username = ?",
                                    new String[]{ra_1.getText()
                                            .toString() + getString(R.string.name_ebook)});
                        }
                        if (ra_2.isChecked()) {
                            db.execSQL(
                                    "update name set username = ?",
                                    new String[]{ra_2.getText()
                                            .toString() + getString(R.string.name_ebook)});
                        }
                        if (ra_3.isChecked()) {
                            db.execSQL(
                                    "update name set username = ?",
                                    new String[]{ra_3.getText()
                                            .toString() + getString(R.string.name_ebook)});
                        }
                    } else {
                        db.execSQL("update name set username = ?",
                                new String[]{et_name_get
                                        .getText().toString()});
                    }
                }
                //关闭dialog
                builder.dismiss();
                //重新载入MainActivity顶上的文本
                MainActivity.rember_name(SettingActivity.this);
            }
        });
        //现实dialog
        builder.show();
    }

    /**
     * 绑定控件
     */
    private void initView() {
        // TODO Auto-generated method stub
        lv_back = (LinearLayout) findViewById(R.id.lv_back);
        tv_button = (TextView) findViewById(R.id.tv_title_button);
        tv_title = (TextView) findViewById(R.id.tv_title_text);
        lv_restart_setting = (LinearLayout) findViewById(R.id.lv_restart_setting);
        lv_inforeback_activity = (LinearLayout) findViewById(R.id.lv_inforeback_activity);
        cb_setting_offlinemode = (CheckBox) findViewById(R.id.cb_setting_offlinemode);
        cb_hind_setting_ico = (CheckBox) findViewById(R.id.cb_hind_setting_ico);
        cb_setting_oldmanmode = (CheckBox) findViewById(R.id.cb_setting_oldmanmode);
        lv_name_setting = (LinearLayout) findViewById(R.id.lv_name_setting);
        lv_uninstall_setting = (LinearLayout) findViewById(R.id.lv_uninstall_setting);
        lv_hindapp_setting = (LinearLayout) findViewById(R.id.lv_hindapp_setting);
        lv_textsize_setting = (LinearLayout) findViewById(R.id.lv_textsize_setting);
        lv_desktop_setting = (LinearLayout) findViewById(R.id.lv_desktop_setting);
        lv_about_activity = (LinearLayout) findViewById(R.id.lv_about_activity);
        lv_applist_setting = (LinearLayout) findViewById(R.id.lv_applist_setting);
        //数据库
        dbHelper_name_sql = new MyDataBaseHelper(getApplicationContext(), "info.db",
                null, 2);
        db = dbHelper_name_sql.getWritableDatabase();
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