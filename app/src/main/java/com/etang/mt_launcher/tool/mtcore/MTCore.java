package com.etang.mt_launcher.tool.mtcore;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.etang.mt_launcher.tool.mtcore.dialog.NewUserDialog;

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

    public static void debug_show_dialog(final Context context, final String e, final String TAG) {
        //新建一个AlertDialog
        final AlertDialog builder = new AlertDialog.Builder(context).create();
        //设置标题
        switch (TAG) {
            case "AboutView":
                builder.setMessage(TAG + "|关于界面View出现错误信息：" + "\n" + e);
                break;
            case "AboutActivity":
                builder.setMessage(TAG + "|关于设置出现错误信息：" + "\n" + e);
                break;
            case "DeskTopSettingActivity":
                builder.setMessage(TAG + "|应用列表设置设置出现错误信息：" + "\n" + e);
                break;
            case "HindAppSetting":
                builder.setMessage(TAG + "|隐藏应用设置出现错误信息：" + "\n" + e);
                break;
            case "InfoRebackActivity":
                builder.setMessage(TAG + "|信息反馈设置出现错误信息：" + "\n" + e);
                break;
            case "ChoseImagesActivity":
                builder.setMessage(TAG + "|选择壁纸设置出现错误信息：" + "\n" + e);
                break;
            case "TextSizeSetting":
                builder.setMessage(TAG + "|文本大小设置出现错误信息：" + "\n" + e);
                break;
            case "UireFreshActivity":
                builder.setMessage(TAG + "|刷新设置出现错误信息：" + "\n" + e);
                break;
            case "WeatherActivity":
                builder.setMessage(TAG + "|天气设置出现错误信息：" + "\n" + e);
                break;
            case "SettingActivity":
                builder.setMessage(TAG + "|设置界面出现错误信息：" + "\n" + e);
                break;
            case "ChoseFragment":
                builder.setMessage(TAG + "|语言选择界面出现错误信息：" + "\n" + e);
                break;
            case "OneFragment":
                builder.setMessage(TAG + "|出现错误信息：" + "\n" + e);
                break;
            case "TwoFragment":
                builder.setMessage(TAG + "|出现错误信息：" + "\n" + e);
                break;
            case "WelecomeActivity":
                builder.setMessage(TAG + "|欢迎界面出现错误信息：" + "\n" + e);
                break;
            case "MainActivity":
                builder.setMessage(TAG + "|桌面主页出现错误信息：" + "\n" + e);
                break;
            case "BitMapTools":
                builder.setMessage(TAG + "|位图工具出现错误信息：" + "\n" + e);
                break;
            case "CheckUpdateDialog":
                builder.setMessage(TAG + "|软件更新Dialog出现错误信息：" + "\n" + e);
                break;
            case "MessageDialog":
                builder.setMessage(TAG + "|普通信息Dialog出现错误信息：" + "\n" + e);
                break;
            case "NewUserDialog":
                builder.setMessage(TAG + "|发送新用户信息Dialog出现错误信息：" + "\n" + e);
                break;
            case "PayMeDialog":
                builder.setMessage(TAG + "|支付Dialog出现错误信息：" + "\n" + e);
                break;
            case "UnInstallDialog":
                builder.setMessage(TAG + "|卸载应用Dialog出现错误信息：" + "\n" + e);
                break;
            case "SavePermission":
                builder.setMessage(TAG + "|检查、申请权限出现错误信息：" + "\n" + e);
                break;
            case "SaveArrayImageUtil":
                builder.setMessage(TAG + "|存储列表（图片）出现错误信息：" + "\n" + e);
                break;
            case "SaveArrayListUtil":
                builder.setMessage(TAG + "|存储列表（应用）出现错误信息：" + "\n" + e);
                break;
            case "AppInstallServer":
                builder.setMessage(TAG + "|监听APP安装服务出现错误信息：" + "\n" + e);
                break;
            case "MyDataBaseHelper":
                builder.setMessage(TAG + "|数据库出现错误信息：" + "\n" + e);
                break;
            case "DiyToast":
                builder.setMessage(TAG + "|自定义Toast出现错误信息：" + "\n" + e);
                break;
            case "ApInfo":
                builder.setMessage(TAG + "|APP信息出现错误信息：" + "\n" + e);
                break;
            case "DeskTopGridViewBaseAdapter":
                builder.setMessage(TAG + "|桌面容器出现错误信息：" + "\n" + e);
                break;
            case "GetApps":
                builder.setMessage(TAG + "|获取APP信息出现错误信息：" + "\n" + e);
                break;
            case "MTCore":
                builder.setMessage(TAG + "|MT核心出现错误信息：" + "\n" + e);
                break;
            case "StreamTool":
                builder.setMessage(TAG + "|字节流工具出现错误信息：" + "\n" + e);
                break;
        }
        //关闭按钮
        builder.setButton("关闭", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                builder.dismiss();
            }
        });
        //推送信息按钮
        builder.setButton2("发送错误（需要网络）", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                NewUserDialog.dialog_show(context, e, false);
                builder.dismiss();
            }
        });
        //显示Dialog
        builder.show();
        Window window = builder.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.dimAmount = 0f;
        window.setAttributes(lp);
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
