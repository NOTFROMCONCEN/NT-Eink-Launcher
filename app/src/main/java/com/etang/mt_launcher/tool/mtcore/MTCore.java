package com.etang.mt_launcher.tool.mtcore;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.etang.mt_launcher.tool.mtcore.dialog.ErrorDialog;
import com.etang.mt_launcher.tool.mtcore.dialog.NewUserDialog;
import com.etang.mt_launcher.tool.mtcore.permission.SavePermission;
import com.etang.mt_launcher.tool.mtcore.toast.DiyToast;
import com.etang.mt_launcher.tool.mtcore.update.CheckUpdateDialog;

import ru.alexbykov.nopermission.BuildConfig;

/**
 * @Package: com.etang.nt_launcher.tool.util
 * @ClassName: MTCore
 * @Description: 存放整个APP能用到的变量，自定义类
 * @CreateDate: 2021/3/19 8:29
 * @UpdateDate: 2021/3/19 8:29
 */
public class MTCore {
    //APP名称
    public static String my_app_name = "梅糖桌面";
    //当前页面TAG
    public static String TAG = "MTCore";
    //服务器URL
    public static String SERVER_WEB_URL_KEY = "yp.nyanon.online";
    //用于时间的计数器
    public static int i_for_weather = 0;
    //微信推送KEY
    public static String SERVER_WeChat_URL_KEY = "SCT42251T0LTbGyWytVR7OQqmFZaLlTNO";
    //微信推送URL
    public static String SERVER_WeChat_URL = "sctapi.ftqq.com";
    //继承Comtext
    private static Context mContext;
    //继承Activity
    private static Activity mActivity;
    //错误代码
    private static String error_name_timeout = "E-1-TimeOut-网络链接超时";
    public static int error_code_timeout = 90001;

    //查找APP更新
    public static void CheckUpdate(final Context context, final Activity activity, final String where) {
        CheckUpdateDialog.check_update(context, activity, where);
    }

    //初始化MTCore（继承Context和Activity）
    public static void initMTCore(Context context, Activity activity) {
        mContext = context;
        mActivity = activity;
    }

    public static int get_Now_AndroidVersion() {
        int version = android.os.Build.VERSION.SDK_INT;
        Log.e(TAG, "get_Now_AndroidVersion: " + version);
        return version;
    }

    //APP ID
    public static String get_my_appAPPLICATION_ID() {
        return String.valueOf(BuildConfig.APPLICATION_ID);
    }

    /**
     * 错误代码
     */
    public static void check_erroe_code(int state) {
        switch (state) {
            case 90001:
                Toast.makeText(mContext, error_name_timeout, Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public static void showToast(Context context, String s, boolean long_or_short) {
        DiyToast.show(context, s, long_or_short);
    }

    public static void debug_show_dialog(final Context context, final String e, final String TAG) {
        ErrorDialog.showdialog(context, e, TAG);
    }

    public static void check_save_permission(Activity activity) {
        SavePermission.check(activity);
    }

    //APP版本代号
    public static String get_my_appVERSIONNAME() {
        String version_name = BuildConfig.VERSION_NAME;
        Log.i(TAG, "get_my_appVERSIONNAME: " + version_name);
        return version_name;
    }

    //APP版本代码
    public static int get_my_appVERSIONCODE() {
        int version_code = BuildConfig.VERSION_CODE;
        Log.i(TAG, "get_my_appVERSIONNAME: " + String.valueOf(version_code));
        return version_code;
    }
}
