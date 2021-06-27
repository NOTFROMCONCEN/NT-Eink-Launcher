package com.etang.nt_launcher.tool.getapps;

import android.content.Intent;
import android.graphics.Bitmap;

/**
 * @Package: com.etang.nt_launcher.tool.util
 * @ClassName: AppInfo
 * @Description: 获取APP信息JavaBean
 * @CreateDate: 2021/3/19 8:27
 * @UpdateDate: 2021/3/19 8:27
 */
public class AppInfo {
    //当前页面TAG
    private static String TAG = "AppInfo";
    //包名
    private String packageName;
    //图标
    private Bitmap ico;
    //名称
    private String Name;
    //链接
    private Intent intent;

    /**
     * 获取链接
     *
     * @return
     */
    public Intent getIntent() {
        return intent;
    }

    /**
     * 设置链接
     *
     * @param intent
     */
    public void setIntent(Intent intent) {
        this.intent = intent;
    }

    /**
     * 获取包名
     *
     * @return
     */
    public String getPackageName() {
        return packageName;
    }

    /**
     * 设置包名
     *
     * @param packageName
     */
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    /**
     * 获取图标
     *
     * @return
     */
    public Bitmap getIco() {
        return ico;
    }

    /**
     * 设置图标
     *
     * @param ico
     */
    public void setIco(Bitmap ico) {
        this.ico = ico;
    }

    /**
     * 获取名称
     *
     * @return
     */
    public String getName() {
        return Name;
    }

    /**
     * 设置名称
     *
     * @param name
     */
    public void setName(String name) {
        Name = name;
    }
}
