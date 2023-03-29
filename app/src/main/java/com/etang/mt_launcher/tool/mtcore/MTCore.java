package com.etang.mt_launcher.tool.mtcore;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.etang.mt_launcher.BuildConfig;
import com.etang.mt_launcher.launcher.settings.about.AboutActivity;
import com.etang.mt_launcher.tool.mtcore.dialog.ErrorDialog;
import com.etang.mt_launcher.tool.mtcore.dialog.FollwoMeDialog;
import com.etang.mt_launcher.tool.mtcore.dialog.MessageDialog;
import com.etang.mt_launcher.tool.mtcore.dialog.NewUserDialog;
import com.etang.mt_launcher.tool.mtcore.permission.SavePermission;
import com.etang.mt_launcher.tool.mtcore.toast.DiyToast;
import com.etang.mt_launcher.tool.mtcore.update.CheckUpdateDialog;
// _   _   _   _       ___       ___  ___   _____   _   _____       ___   __   _   _____
//| | | | | | | |     /   |     /   |/   | | ____| | | |_   _|     /   | |  \ | | /  ___|
//| |_| | | | | |    / /| |    / /|   /| | | |__   | |   | |      / /| | |   \| | | |
//|  _  | | | | |   / / | |   / / |__/ | | |  __|  | |   | |     / / | | | |\   | | |  _
//| | | | | |_| |  / /  | |  / /       | | | |___  | |   | |    / /  | | | | \  | | |_| |
//|_| |_| \_____/ /_/   |_| /_/        |_| |_____| |_|   |_|   /_/   |_| |_|  \_| \_____/

/**
 * @Package: com.etang.nt_launcher.tool.util
 * @ClassName: MTCore
 * @Description: 存放整个APP能用到的变量，自定义类
 * @CreateDate: 2021/3/19 8:29
 * @UpdateDate: 2021/3/19 8:29
 */
public class MTCore {
    //APP版本号_versionCode
    private static String my_app_versionCode;
    //APP版本名称_versionName
    private static String my_app_versionName;
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
    private static String error_name_sqlError = "E-2-sqlError-数据库错误";
    public static int error_code_sqlError = 90002;
    //MTCore版本
    private static String MTCore_version = "2.2";

    //初始化MTCore（继承Context和Activity）
    public static void initMTCore(Context context, Activity activity) {
        mContext = context;
        mActivity = activity;
        setMy_app_versionCode(String.valueOf(BuildConfig.VERSION_CODE));
        setMy_app_versionName(BuildConfig.VERSION_NAME);
    }

    //获取MTCore版本
    public static String getMTCore_version() {
        return MTCore_version;
    }

    //获取MTCore版本代码
    public static String getMy_app_versionCode() {
        return my_app_versionCode;
    }

    //获取APP版本名
    public static String getMy_app_versionName() {
        return my_app_versionName;
    }

    //设置APP版本代码
    public static void setMy_app_versionCode(String my_app_versionCode) {
        MTCore.my_app_versionCode = my_app_versionCode;
    }

    //设置APP版本名
    public static void setMy_app_versionName(String my_app_versionName) {
        MTCore.my_app_versionName = my_app_versionName;
    }

    public static void FollwoMeDialog(final Activity activity) {
        FollwoMeDialog.show(activity);
    }

    public static void MessageDialog(final String msg, Activity activity) {
        MessageDialog.show(msg, activity);
    }


    public static void ErrorDialog(final Context context, final String e, final String TAG) {
        ErrorDialog.show(context, e, TAG);
    }

    //查找APP更新
    public static void CheckUpdate_new(final String where) {
        if (MTCore.get_Now_AndroidVersion() == 19) {
            showToast(mContext, "很抱歉，当前“梅糖桌面”对4.x版本支持并不完善，如出现报错和停止运行，请拍照或截图发送给：1097681347@qq.com", true);
            CheckUpdateDialog.check_update(mContext, mActivity, where);
        } else {
            CheckUpdateDialog.check_update(mContext, mActivity, where);
        }
    }

    public static int get_Now_AndroidVersion() {
        int version = android.os.Build.VERSION.SDK_INT;
        Log.e(TAG, "get_Now_AndroidVersion: " + version);
        return version;
    }

    public static void showToast_new(String s, boolean long_or_short) {
        DiyToast.show(mContext, s, long_or_short);
    }

    public static void showToast(Context context, String s, boolean long_or_short) {
        DiyToast.show(context, s, long_or_short);
    }

    public static void check_save_permission(Activity activity) {
        SavePermission.check(activity);
    }
}
