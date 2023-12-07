package com.etang.mt_launcher.tool.mtcore.permission;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.widget.Toast;

import com.etang.mt_launcher.tool.mtcore.MTCore;

public class SavePermission_New {
    private static final String TAG = "SavePermission";
    private static final int REQUEST_CODE_STORAGE = 102;

    public static void check(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            boolean isGranted = true;
            String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};

            for (String permission : permissions) {
                if (activity.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                    isGranted = false;
                    break;
                }
            }

            if (!isGranted) {
                AlertDialog.Builder permission_dialog = new AlertDialog.Builder(activity);
                permission_dialog.setTitle("请给予存储权限")
                        .setMessage("梅糖桌面需要存储权限才能正常工作")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                MTCore.showToast(activity, "请给予存储权限", true);
                                activity.requestPermissions(permissions, REQUEST_CODE_STORAGE);
                            }
                        }).setNegativeButton("拒绝", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                MTCore.showToast(activity, "请给予存储权限", true);
                            }
                        }).create();
                permission_dialog.show();
            }
        }
    }
}
