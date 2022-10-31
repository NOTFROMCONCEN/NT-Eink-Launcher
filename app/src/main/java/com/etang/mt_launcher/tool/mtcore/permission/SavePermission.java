package com.etang.mt_launcher.tool.mtcore.permission;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;

import com.etang.mt_launcher.tool.mtcore.MTCore;
import com.etang.mt_launcher.tool.mtcore.toast.DiyToast;

/**
 * @Package: com.etang.nt_launcher.tool.permission
 * @ClassName: SavePermission
 * @Description: 用于检查、申请应用存储权限的方法
 * @CreateDate: 2021/3/19 8:26
 * @UpdateDate: 2021/3/19 8:26
 */
public class SavePermission {
    //当前页面TAG
    private static String TAG = "SavePermission";

    /**
     * 检查是否拥有存储权限
     */
    public static void check(Activity activity) {
        boolean isGranted = true;
        if (android.os.Build.VERSION.SDK_INT >= 23) {
            if (activity.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                //如果没有写sd卡权限
                isGranted = false;
            }
            if (activity.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                isGranted = false;
            }
            if (!isGranted) {
                MTCore.showToast(activity, "请给予存储权限", true);
                activity.requestPermissions(
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission
                                .ACCESS_FINE_LOCATION,
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        102);
            } else {
            }
        }
    }
}
