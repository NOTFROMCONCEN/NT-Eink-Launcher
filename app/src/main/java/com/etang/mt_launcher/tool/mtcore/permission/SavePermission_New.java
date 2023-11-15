// 定义一个名为com.etang.mt_launcher.tool.mtcore.permission的包  
package com.etang.mt_launcher.tool.mtcore.permission;

// 导入Android的Manifest类，这个类包含了一些系统和应用的常量，例如应用的权限等  

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;

import com.etang.mt_launcher.tool.mtcore.MTCore;

// 定义一个名为SavePermission的公共类，这个类包含一个静态方法check，用于检查应用是否已获得某些权限  
public class SavePermission_New {
    // 定义一个私有的静态常量TAG，它的值为"SavePermission"，这个常量可能用于日志记录或调试等  
    private static final String TAG = "SavePermission";

    // 定义一个私有的静态常量REQUEST_CODE_STORAGE，它的值为102，这个常量可能用于请求权限时的请求码  
    private static final int REQUEST_CODE_STORAGE = 102;

    // 定义一个公共的静态方法check，它接受一个Activity类型的参数activity，用于检查应用是否已获得某些权限  
    public static void check(Activity activity) {
        // 定义一个布尔变量isGranted，并将其初始值设为true，这个变量用于记录应用是否已获得权限  
        boolean isGranted = true;

        // 检查当前设备的Android版本是否大于等于23（Android 6.0），如果是则执行下面的代码块  
        if (android.os.Build.VERSION.SDK_INT >= 23) {
            // 检查应用是否已获得WRITE_EXTERNAL_STORAGE权限，如果没有则将isGranted设为false并退出此if语句块  
            if (activity.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                isGranted = false;
            }
            // 检查应用是否已获得READ_EXTERNAL_STORAGE权限，如果没有则将isGranted设为false并退出此if语句块  
            if (activity.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                isGranted = false;
            }
            // 如果isGranted仍为false，即应用尚未获得这两个权限，则执行下面的代码块  
            if (!isGranted) {
                // 显示一个Toast消息，提示用户"请给予存储权限"  
                MTCore.showToast(activity, "请给予存储权限", true);
                // 请求上述两个权限，第一个参数是一个String数组，包含了需要请求的权限；第二个参数是请求码，用于识别和处理响应结果  
                activity.requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_STORAGE);
            } else {
            }
        }
    }
}