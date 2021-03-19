package com.etang.nt_launcher.tool.util;

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

    private String packageName;
    private Bitmap ico;
    private String Name;
    private Intent intent;

    public Intent getIntent() {
        return intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Bitmap getIco() {
        return ico;
    }

    public void setIco(Bitmap ico) {
        this.ico = ico;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
