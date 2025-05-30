package com.etang.mt_launcher.launcher;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.WallpaperManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;

import com.etang.mt_launcher.R;
import com.etang.mt_launcher.launcher.settings.SettingActivity;
import com.etang.mt_launcher.launcher.settings.about.AboutActivity;
import com.etang.mt_launcher.launcher.settings.locker.MTLocker;
import com.etang.mt_launcher.launcher.settings.uirefresh.UireFreshActivity;
import com.etang.mt_launcher.launcher.settings.uselogs.AppUseLogsActivity;
import com.etang.mt_launcher.launcher.settings.weather.WeatherActivity;
import com.etang.mt_launcher.launcher.welecome.WelecomeActivity;
import com.etang.mt_launcher.tool.beans.Bean_AppInfo;
import com.etang.mt_launcher.tool.getapps.DeskTopGridViewBaseAdapter;
import com.etang.mt_launcher.tool.getapps.GetApps;
import com.etang.mt_launcher.tool.mtcore.MTCore;
import com.etang.mt_launcher.tool.mtcore.dialog.UnInstallDialog;
import com.etang.mt_launcher.tool.mtcore.savearrayutil.SaveArrayListUtil;
import com.etang.mt_launcher.tool.server.AppInstallServer;
import com.etang.mt_launcher.tool.sql.MyDataBaseHelper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

// _   _   _   _       ___       ___  ___   _____   _   _____       ___   __   _   _____
//| | | | | | | |     /   |     /   |/   | | ____| | | |_   _|     /   | |  \ | | /  ___|
//| |_| | | | | |    / /| |    / /|   /| | | |__   | |   | |      / /| | |   \| | | |
//|  _  | | | | |   / / | |   / / |__/ | | |  __|  | |   | |     / / | | | |\   | | |  _
//| | | | | |_| |  / /  | |  / /       | | | |___  | |   | |    / /  | | | | \  | | |_| |
//|_| |_| \_____/ /_/   |_| /_/        |_| |_____| |_|   |_|   /_/   |_| |_|  \_| \_____/

/**
 * @Package: com.etang.nt_launcher.launcher
 * @ClassName: MainActivity
 * @Description: Main活动，主界面，桌面界面
 * @CreateDate: 2021/3/19 8:18
 * @UpdateDate: 2021/3/19 8:18
 * <p>
 * <p>
 * 于2021年12月11日 17点43分
 * 不会真的有人尝试看懂风滚草方式写出来的代码吧，不会吧不会吧。
 * 哦原来是我自己尝试看懂啊。
 * 坏了，看不懂了
 * 于2022年3月7日 09点27分
 * 别改了，好家伙，完全看不懂了，找时间重构吧
 * 于2022年4月6日 17点07分
 * 完了，摆烂，开始摆烂，这些代码就这样吧
 * 于2022年8月31日 21点05分
 * 摆烂！！！！！！！！！！！！
 * 2022年9月9日 18点08分
 * remake
 * 2022年9月12日 12点28分
 * 想办法重置吧，lite版也要重置了
 */
public class MainActivity extends Activity implements OnClickListener {
    private BroadcastReceiver batteryLevelRcvr;
    private IntentFilter batteryLevelFilter;
    private Handler handler;
    private Runnable runnable;
    private static MyDataBaseHelper dbHelper_name_sql;
    private static SQLiteDatabase db;
    public static TextView tv_user_id, tv_time_hour, tv_time_min, tv_main_batterystate, tv_city, tv_temp_state, tv_last_updatetime, tv_main_nowdate;
    public static ImageView iv_setting_button, iv_setting_yinliang, iv_setting_refresh, iv_clean_button, iv_index_back;
    public static ToggleButton tg_apps_state;
    public static LinearLayout line_wather, line_bottom;
    public static String string_app_info = "";
    public static GridView mListView;
    public static List<Bean_AppInfo> beanAppInfos = new ArrayList<Bean_AppInfo>();
    public static boolean offline_mode = false;
    private AppInstallServer appinstallserver;
    private SharedPreferences sharedPreferences;
    private static String TAG = "MainActivity";
    //引入MTCore
    private MTCore mtCore = new MTCore();

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //全屏
        check_full_window();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);// 无Title
        setContentView(R.layout.activity_main);
        //绑定各类
        initView();// 绑定控件
        check_first_user();//检查是不是第一次使用
        MTCore.check_save_permission(MainActivity.this);//检查存取权限
        new_time_Thread();// 启用更新时间进程
        read_info_help(MainActivity.this, sharedPreferences);//集中存放读取信息相关方法
        // 长按弹出APP信息
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    String string_app_info = getAppInfo(position);
                    UnInstallDialog.uninstall_app(position, beanAppInfos, MainActivity.this, MainActivity.this, string_app_info, beanAppInfos.get(position).getName());
                } catch (Exception e) {
                    MTCore.ErrorDialog(MainActivity.this, e.toString(), TAG);//显示错误信息
                }
                return true;
            }
        });
        // 当点击GridView时，获取ID和应用包名并启动
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    // Intent intent=appInfos.get(position).getIntent();
                    // startActivity(intent);
                    Intent intent = getPackageManager().getLaunchIntentForPackage(beanAppInfos.get(position).getPackageName());
                    if (intent != null) {//点击的APP无异常
                        try {
                            db.execSQL("insert into appuselogs(appname,time)values(?,?)", new String[]{beanAppInfos.get(position).getName(), tv_main_nowdate.getText() + "--" + tv_time_hour.getText() + ":" + tv_time_min.getText()});
                            intent.putExtra("type", "110");
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            overridePendingTransition(0, 0);
                        } catch (Exception e) {
                            db.execSQL("create table appuselogs (_id integer primary key autoincrement,appname text,time text)");
                            MTCore.MessageDialog("数据库已部署，请再次点击你要打开的APP", MainActivity.this);
                        }
                    } else if (beanAppInfos.get(position).getPackageName().equals(getPackageName() + ".weather")) {//点击了“天气”
                        intent = new Intent(MainActivity.this, WeatherActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                    } else if (beanAppInfos.get(position).getPackageName().equals(getPackageName() + ".locker")) {//点击了“锁屏”
                        handler.removeCallbacks(runnable);
                        intent = new Intent(MainActivity.this, MTLocker.class);
                        startActivity(intent);
                        finish();
                    } else if (beanAppInfos.get(position).getPackageName().equals(getPackageName() + ".appuserlogs")) {//点击了“使用记录”
                        intent = new Intent(MainActivity.this, AppUseLogsActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                    } else if (beanAppInfos.get(position).getPackageName().equals(getPackageName() + ".systemupdate")) {//点击了“检查更新”
                        intent = new Intent(MainActivity.this, AboutActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                    } else if (beanAppInfos.get(position).getPackageName().equals(getPackageName() + ".launchersetting")) {//点击了“桌面设置”
                        intent = new Intent(MainActivity.this, SettingActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                    } else if (beanAppInfos.get(position).getPackageName().equals(getPackageName() + ".uirefresh")) {//点击了“刷新屏幕”
                        String s = Build.BRAND;
                        if (s.equals("Allwinner")) {
                            Intent intent_refresh = new Intent("android.eink.force.refresh");
                            sendBroadcast(intent_refresh);
                        } else {
                            startActivity(new Intent(MainActivity.this, UireFreshActivity.class));
                            overridePendingTransition(0, 0);
                        }
                    } else if (beanAppInfos.get(position).getPackageName().equals(getPackageName() + ".systemclean")) {//点击了“清理”
                        String s_clean = Build.BRAND;
                        if (s_clean.equals("Allwinner")) {
                            //唤醒广播
                            Intent intent_clear = new Intent("com.mogu.clear_mem");
                            sendBroadcast(intent_clear);
                        }
                    } else if (beanAppInfos.get(position).getPackageName().equals(getPackageName() + ".userhelper")) {
                        MTCore.showToast(getApplicationContext(), "打开", true);
                        intent.putExtra("state", "false");
                        intent = new Intent(MainActivity.this, WelecomeActivity.class);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                    } else {//出现异常
                        MTCore.ErrorDialog(MainActivity.this, "启动APP时出现“Intent”相关的异常", TAG);
                    }
                } catch (Exception e) {
                    MTCore.ErrorDialog(MainActivity.this, e.toString(), TAG);
                }
            }
        });
        //桌面壁纸长按事件
        iv_index_back.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                iv_index_back.setVisibility(View.GONE);
                mListView.setVisibility(View.VISIBLE);
                tg_apps_state.setChecked(true);
                return true;
            }
        });
        //切换应用列表
        tg_apps_state.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                if (isChecked) {
                    mListView.setVisibility(View.VISIBLE);
                    iv_index_back.setVisibility(View.GONE);
                } else {
                    mListView.setVisibility(View.GONE);
                    iv_index_back.setVisibility(View.VISIBLE);
                }
            }
        });
        //长按“小时”进入设置
        tv_time_hour.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                startActivity(new Intent(MainActivity.this, SettingActivity.class));
                overridePendingTransition(0, 0);
                return true;
            }
        });
        /**
         * 每次回到桌面开启常驻通知
         */
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                setNotification();
            }
        }, 50);
    }


    private String getAppInfo(int position) {
        return beanAppInfos.get(position).getPackageName();
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void read_info_help(Context c, SharedPreferences sharedPreferences) {
        initAppList(c);// 获取应用列表
        monitorBatteryState();// 监听电池信息
        check_text_size(c);//检查文本大小
        rember_name(c);// 读取昵称
        update_wathers(sharedPreferences);//更新天气
        check_view_hind(c, sharedPreferences);//检查底栏是否隐藏
        check_offline_mode(c, sharedPreferences);//检查离线模式是否打开
        check_oldman_mode(c, sharedPreferences);//检查老年模式是否打开
        check_Language(c, sharedPreferences);
        get_applist_number(c, sharedPreferences);//获取设定的应用列表列数
        images_upgrade(c, sharedPreferences);//更新图像信息
        set_app_setStackFromBottomMode(sharedPreferences);//检查并设置APP列表排列方式
        check_full_window();//检查全屏设置
    }

    private void check_full_window() {
        try {
            SharedPreferences sharedPreferences = getSharedPreferences("info", MODE_PRIVATE);
            if (sharedPreferences.getString("setting_full_windows", null).equals("true")) {
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            } else {
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            }
        } catch (Exception e) {
            SharedPreferences sharedPreferences = getSharedPreferences("info", MODE_PRIVATE);
            sharedPreferences.edit().putString("setting_full_windows", "false").commit();
            check_full_window();
            MTCore.showToast_new("出现错误", true);
        }
    }

    /**
     * 设置中文
     *
     * @param context
     * @param sharedPreferences
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void check_Language(Context context, SharedPreferences sharedPreferences) {
        int language = 0;
        try {
            //读取SharedPreferences数据，默认选中第一项
            language = Integer.valueOf(sharedPreferences.getString("language", null));
        } catch (Exception e) {
            sharedPreferences.edit().putString("language", "0").commit();
            check_Language(context, sharedPreferences);
        }
        //根据读取到的数据，进行设置
        Resources resources = getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        Configuration configuration = resources.getConfiguration();
        switch (language) {
            case 0:
                //自动获取
                configuration.setLocale(Locale.getDefault());
                break;
            case 1:
                //中文
                configuration.setLocale(Locale.CHINESE);
                break;
            case 2:
                //英文
                configuration.setLocale(Locale.ENGLISH);
                break;
            case 3:
                //日文
                configuration.setLocale(Locale.JAPANESE);
                break;
            default:
                break;
        }
        resources.updateConfiguration(configuration, displayMetrics);
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        SharedPreferences sharedPreferences = getSharedPreferences("info", MODE_PRIVATE);
        check_view_hind(MainActivity.this, sharedPreferences);//检查底栏是否隐藏
        check_offline_mode(MainActivity.this, sharedPreferences);//检查离线模式是否打开
        get_applist_number(MainActivity.this, sharedPreferences);//获取设定的应用列表列数
        images_upgrade(MainActivity.this, sharedPreferences);//更新图像信息
        set_app_setStackFromBottomMode(sharedPreferences);//检查并设置APP列表排列方式
    }


    public static void initSkinMode(Context context, String s) {
        try {
            // 获取壁纸管理器
            WallpaperManager wallpaperManager = WallpaperManager.getInstance(context);
            // 获取当前壁纸
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            BitmapDrawable wallpaperDrawable = (BitmapDrawable) wallpaperManager.getDrawable();
            // 将Drawable,转成Bitmap
            Bitmap bitmap = Bitmap.createBitmap(wallpaperDrawable.getBitmap());
            // 设置 背景
            if (s.equals("app_wallpaper")) {
                MainActivity.iv_index_back.setVisibility(View.VISIBLE);
                MainActivity.mListView.setVisibility(View.GONE);
                MainActivity.iv_index_back.setImageBitmap(bitmap);
                MainActivity.tg_apps_state.setVisibility(View.VISIBLE);
                tg_apps_state.setChecked(false);
            }
            if (s.equals("app_wallpaper_applist")) {
                MainActivity.iv_index_back.setVisibility(View.VISIBLE);
                MainActivity.mListView.setVisibility(View.VISIBLE);
                MainActivity.iv_index_back.setImageBitmap(bitmap);
                MainActivity.tg_apps_state.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            /**
             * 创建纯白bitmap
             */
            Bitmap bitmap = Bitmap.createBitmap(400, 400, Bitmap.Config.ARGB_8888); // 创建画布
            drawCanvas(bitmap);
            MainActivity.iv_index_back.setVisibility(View.VISIBLE);
            MainActivity.mListView.setVisibility(View.VISIBLE);
            MainActivity.iv_index_back.setImageBitmap(bitmap);
            MainActivity.tg_apps_state.setVisibility(View.GONE);
//            MTCore.showToast(context, "系统壁纸出错，重置为白色", true);
            MTCore.ErrorDialog(context, "系统壁纸获取出错 \n 请更改其他壁纸设置 \n 错误信息：" + e.toString(), TAG);
        }
    }

    public static void drawCanvas(Bitmap bitmap) {
        bitmap.eraseColor(Color.parseColor("#ff0000")); // 填充颜色
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setTextSize(100);
        paint.setColor(Color.DKGRAY);
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE); //用于设置字体填充的类型
//        canvas.drawText("Ken", 100, 100, paint);
        //最后通过Imageview显示出来
        MainActivity.iv_index_back.setImageBitmap(bitmap);
    }


    private void set_app_setStackFromBottomMode(SharedPreferences sharedPreferences) {
        if (sharedPreferences.getBoolean("app_setStackFromBottomMode", true) == false) {
            mListView.setStackFromBottom(false);
        } else {
            mListView.setStackFromBottom(true);
        }
    }

    private void check_first_user() {
        if (isFirstStart(MainActivity.this)) {//第一次
            //第一次启动预填充数据，并且跳转至欢迎界面
            //填充预设数据
            fillPresetData();
            //填充预设隐藏应用包名
            ArrayList<String> arrayList = new ArrayList<String>();
            arrayList.add("frist");
            SaveArrayListUtil.saveArrayList(MainActivity.this, arrayList, "start");//存储在本地
            //跳转到欢迎界面
            startActivity(new Intent(getApplicationContext(), WelecomeActivity.class));
            overridePendingTransition(0, 0);
            finish();
        } else {
            //更新桌面信息
            images_upgrade(MainActivity.this, sharedPreferences);
        }
    }

    private void fillPresetData() {
        SharedPreferences.Editor editor = getSharedPreferences("info", MODE_PRIVATE).edit();
        editor.putString("images_info", "applist");//默认显示内容
        editor.putString("images_app_listifo", "true");
        editor.putString("appname_state", "one");//是否显示APP名称
        editor.putString("applist_number", "5");//默认APP列表大小
        editor.putString("timetext_min_size", "40");//分钟时间大小
        editor.putString("timetext_hour_size", "70");//小时时间大小
        editor.putString("nametext_size", "16");//昵称文本大小
        editor.putString("dianchitext_size", "16");//电池文本大小
        editor.putString("datetext_size", "16");//日期文本大小
        editor.putString("setting_ico_hind", "false");//隐藏底栏
        editor.putString("offline", "false");//离线模式
        editor.putString("oldman", "false");//老年模式
        editor.putBoolean("app_setStackFromBottomMode", false);//默认显示内容
        editor.putString("icon_size", "45");//图标大小
        editor.putString("language", "0");//设置语言
        editor.apply();
    }


    public static void check_oldman_mode(Context context, SharedPreferences sharedPreferences) {
        try {
            String offline = sharedPreferences.getString("offline", null);
            if (offline.equals("true")) {
                offline_mode = true;
            } else {
                offline_mode = false;
            }
        } catch (Exception e) {
            SharedPreferences.Editor editor = context.getSharedPreferences("info", context.MODE_PRIVATE).edit();
            editor.putString("oldman", "false");//日期文本大小
            editor.apply();
        }
    }

    public static void check_offline_mode(Context context, SharedPreferences sharedPreferences) {
        try {
            String offline = sharedPreferences.getString("offline", null);
            if (offline.equals("true")) {
                offline_mode = true;
            } else {
                offline_mode = false;
            }
        } catch (Exception e) {
            SharedPreferences.Editor editor = context.getSharedPreferences("info", context.MODE_PRIVATE).edit();
            editor.putString("offline", "false");//日期文本大小
            editor.apply();
        }
    }

    public static void check_view_hind(Context context, SharedPreferences sharedPreferences) {
//        if (Build.BRAND.toString().equals("Allwinner")) {
//            iv_setting_refresh.setVisibility(View.GONE);
//        }
        try {
            String ico_info = sharedPreferences.getString("setting_ico_hind", null);
            if (ico_info.equals("true")) {
                line_bottom.setVisibility(View.GONE);
            } else {
                line_bottom.setVisibility(View.VISIBLE);
            }
        } catch (Exception e) {
            SharedPreferences.Editor editor = context.getSharedPreferences("info", context.MODE_PRIVATE).edit();
            editor.putString("setting_ico_hind", "false");//日期文本大小
            editor.apply();
        }
    }

    private void get_applist_number(Context context, SharedPreferences sharedPreferences) {
        try {
            String applist_number = sharedPreferences.getString("applist_number", null);
            if (applist_number.equals("auto")) {
                mListView.setNumColumns(GridView.AUTO_FIT);
            }
            if (!applist_number.equals("auto")) {
                mListView.setNumColumns(Integer.valueOf(applist_number));
            }
        } catch (Exception e) {
            SharedPreferences.Editor editor = getSharedPreferences("info", MODE_PRIVATE).edit();
            editor.putString("applist_number", "auto");
            editor.apply();
            mListView.setNumColumns(GridView.AUTO_FIT);
        }
    }

    public boolean isFirstStart(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("SHARE_APP_TAG", 0);
        Boolean isFirst = preferences.getBoolean("FIRSTStart", true);
        if (isFirst) {// 第一次
            preferences.edit().putBoolean("FIRSTStart", false).commit();
            return true;
        } else {
            return false;
        }
    }

    /**
     * 检查图片
     */
    private void images_upgrade(Context c, SharedPreferences sharedPreferences) {
        try {
            String images_mode = sharedPreferences.getString("images_info", null);
            if (images_mode.equals("ql")) {
                iv_index_back.setImageResource(R.drawable.mi_haole);
                iv_index_back.setVisibility(View.VISIBLE);
                mListView.setVisibility(View.GONE);
                tg_apps_state.setVisibility(View.VISIBLE);
                tg_apps_state.setChecked(false);
            }
            if (images_mode.equals("mz")) {
                iv_index_back.setImageResource(R.drawable.mi_meizi);
                iv_index_back.setVisibility(View.VISIBLE);
                mListView.setVisibility(View.GONE);
                tg_apps_state.setVisibility(View.VISIBLE);
                tg_apps_state.setChecked(false);
            }
            if (images_mode.equals("ll")) {
                iv_index_back.setImageResource(R.drawable.mi_luoli);
                iv_index_back.setVisibility(View.VISIBLE);
                mListView.setVisibility(View.GONE);
                tg_apps_state.setVisibility(View.VISIBLE);
                tg_apps_state.setChecked(false);
            }
            if (images_mode.equals("zy")) {
                iv_index_back.setImageResource(R.drawable.mi_zhiyu);
                iv_index_back.setVisibility(View.VISIBLE);
                mListView.setVisibility(View.GONE);
                tg_apps_state.setVisibility(View.VISIBLE);
                tg_apps_state.setChecked(false);
            }
            if (images_mode.equals("applist")) {
                iv_index_back.setImageResource(R.drawable.mi_haole);
                iv_index_back.setVisibility(View.GONE);
                mListView.setVisibility(View.VISIBLE);
                tg_apps_state.setVisibility(View.GONE);
            }
            if (images_mode.equals("")) {
                iv_index_back.setImageResource(R.drawable.mi_haole);
                iv_index_back.setVisibility(View.GONE);
                mListView.setVisibility(View.VISIBLE);
                MTCore.showToast(this, "请选择壁纸或者应用列表（设置-壁纸设置）", false);
            }
            if (images_mode.equals("app_wallpaper")) {
                initSkinMode(MainActivity.this, images_mode);
            }
            if (images_mode.equals("app_wallpaper_applist")) {
                initSkinMode(MainActivity.this, images_mode);
            }
        } catch (Exception e) {
            MTCore.ErrorDialog(c, "桌面壁纸出现错误，已重置为默认", TAG);
            sharedPreferences.edit().putString("images_info", "applist").apply();
        }
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        initAppList(MainActivity.this);
    }

    /**
     * +获取应用列表、隐藏应用
     *
     * @param context
     */
//    public static void initAppList(Context context) {
//        beanAppInfos = GetApps.GetAppList1(context);
//        ArrayList<String> hind_apparrayList = new ArrayList<String>();
//        hind_apparrayList.clear();
//        hind_apparrayList = SaveArrayListUtil.getSearchArrayList(context);
//        String s = Build.BRAND;
//        if (s.equals("Allwinner")) {
//            hind_apparrayList.add("com.android.settings");
//        }
//        Log.i(TAG, "initAppList: --------------removeing packname-----------");
//        for (int j = 0; j < hind_apparrayList.size(); j++) {
//            for (int i = 0; i < beanAppInfos.size(); i++) {
//                if (hind_apparrayList.get(j).equals(beanAppInfos.get(i).getPackageName())) {
//                    Log.i(TAG, "initAppList: remove:" + i);
//                    beanAppInfos.remove(i);
//                }
//            }
//        }
//        DeskTopGridViewBaseAdapter deskTopGridViewBaseAdapter = new DeskTopGridViewBaseAdapter(beanAppInfos, context);
//        mListView.setAdapter(deskTopGridViewBaseAdapter);
//    }
    public static void initAppList(Context context) {
        try {
            beanAppInfos = GetApps.GetAppList1(context);
            ArrayList<String> hind_apparrayList = SaveArrayListUtil.getSearchArrayList(context);
            String s = Build.BRAND;

            // 如果品牌是Allwinner，添加特定的应用包名
            if (s.equals("Allwinner")) {
                hind_apparrayList.add("com.android.settings");
            }
            Log.i(TAG, "initAppList: --------------hind_apparrayList-----------");
            Log.i(TAG, "initAppList: " + hind_apparrayList);
            Log.i(TAG, "initAppList: --------------beanAppInfos-----------");
            Log.i(TAG, "initAppList: " + beanAppInfos);

            // 使用Stream API来过滤不需要的应用
            // 注意：这里假设beanAppInfos是一个列表，并且getPackageName()方法不存在异常风险
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                beanAppInfos.removeIf(appInfo -> hind_apparrayList.stream().anyMatch(hindApp -> hindApp.equals(appInfo.getPackageName())));
            } else {
                for (int j = 0; j < hind_apparrayList.size(); j++) {
                    for (int i = 0; i < beanAppInfos.size(); i++) {
                        if (hind_apparrayList.get(j).equals(beanAppInfos.get(i).getPackageName())) {
                            Log.i(TAG, "initAppList: --------------removeing packname-----------");
                            Log.i(TAG, "initAppList: remove:" + beanAppInfos.get(i).getPackageName());
                            beanAppInfos.remove(i);
                        }
                    }
                }
            }

            // 创建适配器并设置到ListView
            DeskTopGridViewBaseAdapter deskTopGridViewBaseAdapter = new DeskTopGridViewBaseAdapter(beanAppInfos, context);
            mListView.setAdapter(deskTopGridViewBaseAdapter);
        } catch (Exception e) {
            // 记录异常信息，可以根据需要进行异常处理
            Log.e(TAG, "Error initializing app list", e);
        }
    }


    /**
     * 读取昵称
     * <p>
     * SQLite
     */
    public static void rember_name(Context c) {
        Cursor cursor = MainActivity.db.rawQuery("select * from name", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            @SuppressLint("Range") String username = cursor.getString(cursor.getColumnIndex("username"));
            MainActivity.tv_user_id.setText(username);
            if (MainActivity.tv_user_id.getText().toString().isEmpty()) {
                MainActivity.tv_user_id.setText("请设置文本（桌面设置中）");
            } else if (MainActivity.tv_user_id.getText().toString().equals("")) {
                MainActivity.tv_user_id.setText("请设置文本（桌面设置中）");
            }
        }
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
                SimpleDateFormat simpleDateFormat_hour = new SimpleDateFormat("HH");
                SimpleDateFormat simpleDateFormat_min = new SimpleDateFormat("mm");
                SimpleDateFormat simpleDateFormat_date = new SimpleDateFormat("yyyy/MM/dd");
                SimpleDateFormat simpleDateFormat_weekday = new SimpleDateFormat("EEEE"); // 用于显示星期
                // 获取当前日期和星期
                String currentDate = simpleDateFormat_date.format(new java.util.Date());
                String currentWeekday = simpleDateFormat_weekday.format(new java.util.Date());
                // 设置文本，这里假设你想要日期和星期在同一行显示，用空格分隔
                tv_main_nowdate.setText(currentDate + " " + currentWeekday);
                tv_time_hour.setText(simpleDateFormat_hour.format(new java.util.Date()));
                tv_time_min.setText(simpleDateFormat_min.format(new java.util.Date()));
                handler.postDelayed(runnable, 1000);
            }
        };
        handler.post(runnable);
    }

    /**
     * 绑定控件
     */
    private void initView() {
        sharedPreferences = getSharedPreferences("info", MODE_PRIVATE);
        line_bottom = (LinearLayout) findViewById(R.id.line_bottom);
        tv_main_nowdate = (TextView) findViewById(R.id.tv_main_nowdate);
        iv_clean_button = (ImageView) findViewById(R.id.iv_setting_clear);
        iv_setting_refresh = (ImageView) findViewById(R.id.iv_setting_refresh);
        mListView = (GridView) findViewById(R.id.mAppGridView);
        iv_setting_button = (ImageView) findViewById(R.id.iv_setting_button);
        tv_time_hour = (TextView) findViewById(R.id.tv_time_hour);
        tg_apps_state = (ToggleButton) findViewById(R.id.tg_apps_state);
        tv_time_min = (TextView) findViewById(R.id.tv_time_min);
        tv_user_id = (TextView) findViewById(R.id.tv_user_id);
        tv_main_batterystate = (TextView) findViewById(R.id.tv_main_batterystate);
        line_wather = (LinearLayout) findViewById(R.id.line_wather);
        tv_city = (TextView) findViewById(R.id.tv_city);
        iv_index_back = (ImageView) findViewById(R.id.iv_index_back);
        tv_temp_state = (TextView) findViewById(R.id.tv_temp_state);
        tv_last_updatetime = (TextView) findViewById(R.id.tv_last_updatetime);
        iv_setting_button.setOnClickListener(this);
        line_wather.setOnClickListener(this);
        iv_setting_refresh.setOnClickListener(this);
        iv_clean_button.setOnClickListener(this);
        String s_clean = Build.BRAND;
        if (s_clean.equals("Allwinner")) {
            iv_clean_button.setVisibility(View.VISIBLE);
        } else {
            iv_clean_button.setVisibility(View.GONE);
        }
        //数据库
        dbHelper_name_sql = new MyDataBaseHelper(getApplicationContext(), "info.db", null, 2);
        db = dbHelper_name_sql.getWritableDatabase();
        //动态注册
        appinstallserver = new AppInstallServer();
        appinstallserver.register(this);
    }

    private void update_wathers(SharedPreferences sharedPreferences) {
        if (!offline_mode) {
//            line_wather.setVisibility(View.VISIBLE);
            tv_temp_state.setText(sharedPreferences.getString("wather_info_temp", null));
            tv_last_updatetime.setText(sharedPreferences.getString("wather_info_updatetime", null));
            tv_city.setText(sharedPreferences.getString("wather_info_citytype", null));
            /**
             * 判断设置是不是隐藏天气布局
             */
            check_weather_view(sharedPreferences);
        } else {
//            line_wather.setVisibility(View.INVISIBLE);
        }
    }

    private void check_weather_view(SharedPreferences sharedPreferences) {
        if (sharedPreferences.getBoolean("isHind_weather", false) == true) {
//            line_wather.setVisibility(View.INVISIBLE);
        } else if (sharedPreferences.getBoolean("isHind_weather", false) == false) {
//            line_wather.setVisibility(View.VISIBLE);
        } else {
//            line_wather.setVisibility(View.VISIBLE);
        }
    }

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    String fengxiang = "";
                    String fengli = "";
                    String high = "";
                    String type = "";
                    String low = "";
                    String date = "";
                    JSONArray dataArray = (JSONArray) msg.obj;
                    try {
                        String json_today = dataArray.getString(0);

                        System.out.println("-----------" + json_today);

                        JSONObject jsonObject = dataArray.getJSONObject(0);
                        System.out.println(jsonObject);
                        if (jsonObject != null) {
                            fengxiang = jsonObject.optString("fengxiang");
                            fengli = jsonObject.optString("fengli");
                            high = jsonObject.optString("high");
                            type = jsonObject.optString("type");
                            low = jsonObject.optString("low");
                            date = jsonObject.optString("date");
                        }
                        Cursor cursor = db.rawQuery("select * from wather_city", null);
                        if (cursor.getCount() != 0) {
                            cursor.moveToFirst();
                            @SuppressLint("Range") String city = cursor.getString(cursor.getColumnIndex("city"));
                            tv_city.setText(city + "  " + type);
                        } else {
                            MTCore.showToast(getApplicationContext(), "请到“梅糖天气”设置位置信息", true);
                        }
                        @SuppressLint("Range") String city = cursor.getString(cursor.getColumnIndex("city"));
                        SharedPreferences.Editor editor = getSharedPreferences("info", MODE_PRIVATE).edit();
                        editor.putString("wather_info_citytype", city + "  " + type);
                        editor.putString("wather_info_wind", fengxiang);
                        editor.putString("wather_info_temp", high + "  " + low);
                        editor.putString("wather_info_updatetime", "于" + tv_time_hour.getText().toString() + ":" + tv_time_min.getText().toString() + "更新");
                        editor.apply();
                        /**
                         * 更新天气信息
                         */
                        SharedPreferences sharedPreferences;
                        sharedPreferences = getSharedPreferences("info", MODE_PRIVATE);
                        update_wathers(sharedPreferences);
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                    break;
                case 1:
                    MTCore.showToast(getApplicationContext(), "城市无效（已重置为上海）", true);
                    db.execSQL("update wather_city set city = ? ", new String[]{"上海"});
                    break;
                case 2:
                    SharedPreferences.Editor editor = getSharedPreferences("info", MODE_PRIVATE).edit();
                    editor.putString("wather_info_updatetime", "于" + tv_time_hour.getText().toString() + ":" + tv_time_min.getText().toString() + "更新（离线状态）");
                    editor.apply();
                    SharedPreferences sharedPreferences;
                    sharedPreferences = getSharedPreferences("info", MODE_PRIVATE);
                    update_wathers(sharedPreferences);
                    break;
                default:
                    break;
            }
        }
    };

    /**
     * 拦截返回键、Home键
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return false;
        }
        if (keyCode == KeyEvent.KEYCODE_HOME) {
            MTCore.showToast(MainActivity.this, "已经回到桌面", false);
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * Activity被销毁的同时销毁广播
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(batteryLevelRcvr);
        appinstallserver.unregister(this);
    }

    /**
     * 充电状态显示
     * <p>
     * Code Copy from http://blog.sina.com.cn/s/blog_c79c5e3c0102uyun.html
     */
    private void monitorBatteryState() {
        batteryLevelRcvr = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                StringBuilder sb = new StringBuilder();
                int rawlevel = intent.getIntExtra("level", -1);
                int scale = intent.getIntExtra("scale", -1);
                int status = intent.getIntExtra("status", -1);
                int health = intent.getIntExtra("health", -1);
                int level = -1; // percentage, or -1 for unknown
                if (rawlevel >= 0 && scale > 0) {
                    level = (rawlevel * 100) / scale;
                }
                if (BatteryManager.BATTERY_HEALTH_OVERHEAT == health) {
                    sb.append("'s battery feels very hot!");
                } else {
                    if (status == BatteryManager.BATTERY_STATUS_FULL) {//充电完成
                        sb.append(String.valueOf(level) + "% 已充满 ");
                        tv_main_batterystate.setText(sb.toString());
                    }
                    if (status == BatteryManager.BATTERY_STATUS_CHARGING) {//充电
                        sb.append(String.valueOf(level) + "% 充电中 ");
                        tv_main_batterystate.setText(sb.toString());
                    }
                    if (status == BatteryManager.BATTERY_STATUS_DISCHARGING) {//放电
                        sb.append(String.valueOf(level) + "% ");
                        tv_main_batterystate.setText(sb.toString());
                    }
                    if (status == BatteryManager.BATTERY_STATUS_NOT_CHARGING) {//未在充电
                        sb.append(String.valueOf(level) + "% ");
                        tv_main_batterystate.setText(sb.toString());
                    }
                }
                sb.append(' ');
            }
        };
        batteryLevelFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(batteryLevelRcvr, batteryLevelFilter);
    }

    /**
     * 桌面底栏功能 点击事件监听
     */
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            //设置
            case R.id.iv_setting_button:
                startActivity(new Intent(getApplicationContext(), SettingActivity.class));
                overridePendingTransition(0, 0);
                break;
            case R.id.iv_setting_clear:
                String s_clean = Build.BRAND;
                if (s_clean.equals("Allwinner")) {
                    Intent intent_clear = new Intent("com.mogu.clear_mem");
                    sendBroadcast(intent_clear);
//                    Intent intent = new Intent("android.eink.force.refresh");
//                    sendBroadcast(intent);
                }
                break;
            //刷新
            case R.id.iv_setting_refresh:
                String s = Build.BRAND;
                if (s.equals("Allwinner")) {
//                    Intent intent_clear = new Intent("com.mogu.clear_mem");
//                    sendBroadcast(intent_clear);
                    Intent intent = new Intent("android.eink.force.refresh");
                    sendBroadcast(intent);
                } else {
                    startActivity(new Intent(MainActivity.this, UireFreshActivity.class));
                    overridePendingTransition(0, 0);
                }
                break;
            default:
                break;
        }
    }

    // 添加常驻通知
    private void setNotification() {
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder;
        int channelId = 1;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {    //Android 8.0以上适配
            NotificationChannel channel = new NotificationChannel(String.valueOf(channelId), "channel_name", NotificationManager.IMPORTANCE_HIGH);
            manager.createNotificationChannel(channel);
            builder = new NotificationCompat.Builder(this, String.valueOf(channelId));
        } else {
            builder = new NotificationCompat.Builder(this);
        }
//        Intent intent = new Intent(this, MainActivity.class);
        Intent intent = new Intent();// 创建Intent对象
        intent.setAction(Intent.ACTION_MAIN);// 设置Intent动作
        intent.addCategory(Intent.CATEGORY_HOME);// 设置Intent种类
//        startActivity(intent);// 将Intent传递给Activity
//        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
        // 创建一个可变的 PendingIntent
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setContentTitle("点击此条通知回到桌面")//指定通知栏的标题内容
                .setContentText("软件后台运行中")//通知的正文内容
                .setWhen(0)//通知创建的时间
                .setAutoCancel(false)//点击通知后，自动取消
                .setStyle(new NotificationCompat.BigTextStyle().bigText("")).setSmallIcon(R.drawable.ic_launcher)//通知显示的小图标，只能用alpha图层的图片进行设置
                .setPriority(NotificationCompat.PRIORITY_MAX)//通知重要程度
                .setContentIntent(pi)//点击跳转
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher));
        Notification notification = builder.build();
        notification.flags |= Notification.FLAG_ONGOING_EVENT;
        manager.notify(channelId, notification);
    }

    /**
     * 检查文本大小并设置
     *
     * @param context
     */
    public static void check_text_size(Context context) {
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences("info", MODE_PRIVATE);
            MainActivity.tv_time_hour.setTextSize(Integer.valueOf(sharedPreferences.getString("timetext_hour_size", null)));
            MainActivity.tv_time_min.setTextSize(Integer.valueOf(sharedPreferences.getString("timetext_min_size", null)));
            MainActivity.tv_user_id.setTextSize(Integer.valueOf(sharedPreferences.getString("nametext_size", null)));
            MainActivity.tv_main_batterystate.setTextSize(Integer.valueOf(sharedPreferences.getString("dianchitext_size", null)));
        } catch (Exception e) {
            SharedPreferences.Editor editor = context.getSharedPreferences("info", MODE_PRIVATE).edit();
            /**
             * 设定文本大小预填充
             */
            editor.putString("timetext_min_size", "40");
            editor.putString("timetext_hour_size", "70");
            editor.putString("nametext_size", "17");//昵称文本大小
            editor.putString("dianchitext_size", "17");//电池文本大小
            editor.putString("datetext_size", "17");//日期文本大小
            editor.apply();
            check_text_size(context);
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }

}