package com.etang.mt_launcher.launcher.settings.hindapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.etang.mt_launcher.R;
import com.etang.mt_launcher.launcher.settings.SettingActivity;
import com.etang.mt_launcher.launcher.settings.about.AboutActivity;
import com.etang.mt_launcher.launcher.settings.uirefresh.UireFreshActivity;
import com.etang.mt_launcher.launcher.settings.weather.WeatherActivity;
import com.etang.mt_launcher.launcher.welecome.WelecomeActivity;
import com.etang.mt_launcher.tool.getapps.AppInfo;
import com.etang.mt_launcher.tool.getapps.DeskTopGridViewBaseAdapter;
import com.etang.mt_launcher.tool.getapps.GetApps;
import com.etang.mt_launcher.tool.mtcore.MTCore;
import com.etang.mt_launcher.tool.mtcore.savearrayutil.SaveArrayListUtil;

import java.util.ArrayList;
import java.util.List;
// _   _   _   _       ___       ___  ___   _____   _   _____       ___   __   _   _____
//| | | | | | | |     /   |     /   |/   | | ____| | | |_   _|     /   | |  \ | | /  ___|
//| |_| | | | | |    / /| |    / /|   /| | | |__   | |   | |      / /| | |   \| | | |
//|  _  | | | | |   / / | |   / / |__/ | | |  __|  | |   | |     / / | | | |\   | | |  _
//| | | | | |_| |  / /  | |  / /       | | | |___  | |   | |    / /  | | | | \  | | |_| |
//|_| |_| \_____/ /_/   |_| /_/        |_| |_____| |_|   |_|   /_/   |_| |_|  \_| \_____/
// code by 邢启瑞 xingqirui@petalmail.com

/**
 * @ProjectName: NT-Eink-Launcher
 * @Package: com.etang.nt_launcher.launcher.settings.about
 * @ClassName: HindAppSetting
 * @Description: 梅糖桌面隐藏APP设置相关界面和功能
 * @Author: 作者名：奶油话梅糖
 * @CreateDate: 2021/5/29 0029 22:14
 * @UpdateUser: 更新者：奶油话梅糖
 * @UpdateDate: 2021/5/29 0029 22:14
 * @UpdateRemark: 更新说明：修复UI问题
 */
public class HindAppSetting extends AppCompatActivity {
    //APP列表
    private static List<AppInfo> appInfos = new ArrayList<AppInfo>();
    //隐藏的APP列表
    private static List<AppInfo> appHindInfos = new ArrayList<AppInfo>();
    //GridView列表
    private static GridView mHindGridView;
    //文本，返回，按钮，标题
    private TextView tv_button, tv_title;
    //返回LinearLayout
    private LinearLayout lv_back;
    //当前TAG
    private static String TAG = "HindAppSetting";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);// 无Title
        setContentView(R.layout.setting_hind_app);
        //绑定空间
        initView();
        //加载软件列表
        initAppList(HindAppSetting.this);
        //设置标题
        tv_title.setText("应用管理");
        lv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mHindGridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                show_dialog_helper(appHindInfos.get(position).getPackageName());
                return true;
            }
        });
        tv_button.setText("刷新");
        tv_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MTCore.showToast(getApplicationContext(), "刷新成功", false);
                initAppList(getApplicationContext());
            }
        });
        // 当点击GridView时，获取ID和应用包名并启动
        mHindGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                try {
                    // Intent intent=appInfos.get(position).getIntent();
                    // startActivity(intent);
                    Intent intent = getPackageManager().getLaunchIntentForPackage(
                            appHindInfos.get(position).getPackageName());

                    Log.e("TAG", String.valueOf(intent));

                    if (intent != null) {//点击的APP无异常
                        intent.putExtra("type", "110");
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                    } else if (appHindInfos.get(position).getPackageName().equals(getPackageName() + ".weather")) {//点击了“天气”
                        intent = new Intent(HindAppSetting.this, WeatherActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                    } else if (appHindInfos.get(position).getPackageName().equals(getPackageName() + ".systemupdate")) {//点击了“检查更新”
                        intent = new Intent(HindAppSetting.this, AboutActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
//                        CheckUpdateDialog.check_update(MainActivity.this, MainActivity.this);
                    } else if (appHindInfos.get(position).getPackageName().equals(getPackageName() + ".launchersetting")) {//点击了“桌面设置”
                        intent = new Intent(HindAppSetting.this, SettingActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                    } else if (appHindInfos.get(position).getPackageName().equals(getPackageName() + ".uirefresh")) {//点击了“刷新屏幕”
                        String s = Build.BRAND;
                        if (s.equals("Allwinner")) {
                            Intent intent_refresh = new Intent("android.eink.force.refresh");
                            sendBroadcast(intent_refresh);
                        } else {
                            startActivity(new Intent(HindAppSetting.this, UireFreshActivity.class));
                            overridePendingTransition(0, 0);
                        }
                    } else if (appHindInfos.get(position).getPackageName().equals(getPackageName() + ".systemclean")) {//点击了“清理”
                        String s_clean = Build.BRAND;
                        if (s_clean.equals("Allwinner")) {
                            //唤醒广播
                            Intent intent_clear = new Intent("com.mogu.clear_mem");
                            sendBroadcast(intent_clear);
                        }
                    } else if (appHindInfos.get(position).getPackageName().equals(getPackageName() + ".userhelper")) {
                        MTCore.showToast(getApplicationContext(), "打开", true);
                        intent.putExtra("state", "false");
                        intent = new Intent(HindAppSetting.this, WelecomeActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                    } else {//出现异常
                        MTCore.ErrorDialog(HindAppSetting.this, "启动APP时出现“Intent”相关的异常", TAG);
                    }
                } catch (Exception e) {
                    MTCore.ErrorDialog(HindAppSetting.this, e.toString(), TAG);
                }
            }
        });
    }

    /**
     * 展示软件信息dialog
     *
     * @param packname 包名
     */
    private void show_dialog_helper(final String packname) {
        //新建一个dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(HindAppSetting.this);
        //设置dialog标题
        builder.setTitle("要取消隐藏应用吗？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ArrayList<String> hind_apparrayList = new ArrayList<String>();
                hind_apparrayList.clear();
                hind_apparrayList = SaveArrayListUtil.getSearchArrayList(HindAppSetting.this);
                for (int i = 0; i < hind_apparrayList.size(); i++) {
                    if (hind_apparrayList.get(i).equals(packname)) {
                        hind_apparrayList.remove(i);
                        continue;
                    }
                }
                SaveArrayListUtil.saveArrayList(HindAppSetting.this, hind_apparrayList, "1");
                initAppList(HindAppSetting.this);
            }
        });
        builder.setNeutralButton("取消", null);
        builder.show();
    }

    private void initView() {
        lv_back = (LinearLayout) findViewById(R.id.lv_back);
        tv_button = (TextView) findViewById(R.id.tv_title_button);
        tv_title = (TextView) findViewById(R.id.tv_title_text);
        mHindGridView = (GridView) findViewById(R.id.mHindGridView);
    }

    /**
     * +获取应用列表、隐藏应用
     *
     * @param context
     */
    public static void initAppList(Context context) {
        appInfos.clear();
        appHindInfos.clear();
        appInfos = GetApps.GetAppList1(context);
        ArrayList<String> hind_apparrayList = new ArrayList<String>();
        hind_apparrayList.clear();
        hind_apparrayList = SaveArrayListUtil.getSearchArrayList(context);
        for (int j = 0; j < hind_apparrayList.size(); j++) {
            for (int i = 0; i < appInfos.size(); i++) {
                if (hind_apparrayList.get(j).equals(appInfos.get(i).getPackageName())) {
                    appHindInfos.add(appInfos.remove(i));
                    continue;
                }
            }
        }
        DeskTopGridViewBaseAdapter deskTopGridViewBaseAdapter = new DeskTopGridViewBaseAdapter(appHindInfos,
                context);
        mHindGridView.setAdapter(deskTopGridViewBaseAdapter);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }

}
