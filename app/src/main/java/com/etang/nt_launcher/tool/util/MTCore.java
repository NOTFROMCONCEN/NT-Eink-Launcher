package com.etang.nt_launcher.tool.util;


import ru.alexbykov.nopermission.BuildConfig;

/**
 * @Package: com.etang.nt_launcher.tool.util
 * @ClassName: MTCore
 * @Description: 存放整个APP能用到的变量
 * @CreateDate: 2021/3/19 8:29
 * @UpdateDate: 2021/3/19 8:29
 */
public class MTCore {
    //APP名称
    public static String my_app_name = "梅糖桌面";
    //当前页面TAG
    public static String TAG = "MTCore";

    //APP ID
    public static String get_my_appAPPLICATION_ID() {
        return String.valueOf(BuildConfig.APPLICATION_ID);
    }

    //APP构造模式（正式版or测试版）
    public static String get_my_appBUILD_TYPE() {
        return String.valueOf(BuildConfig.BUILD_TYPE);
    }

    //APP Debug模式
    public static String get_my_appDEBUG() {
        return String.valueOf(BuildConfig.DEBUG);
    }

    //APP版本代号
    public static String get_my_appVERSIONNAME() {
        return String.valueOf(BuildConfig.VERSION_NAME);
    }

    //APP版本代码
    public static String get_my_appVERSIONCODE() {
        return String.valueOf(BuildConfig.VERSION_CODE);
    }
}
