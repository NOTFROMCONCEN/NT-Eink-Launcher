
package com.etang.mt_launcher.tool.server;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import com.etang.mt_launcher.launcher.MainActivity;
import com.etang.mt_launcher.tool.mtcore.MTCore;
import com.etang.mt_launcher.tool.mtcore.toast.DiyToast;

public class AppInstallServer extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            if (intent.getAction().equals(Intent.ACTION_PACKAGE_ADDED)) {
                String packageName = intent.getData().getSchemeSpecificPart();
                MTCore.showToast(context, "安装了" + packageName, true);
                MainActivity.initAppList(context);
            } else if (intent.getAction().equals(Intent.ACTION_PACKAGE_REMOVED)) {
                String packageName = intent.getData().getSchemeSpecificPart();
                boolean isReplace = intent.getBooleanExtra(Intent.EXTRA_REPLACING, false);
                MTCore.showToast(context, "卸载了" + packageName, true);
                MainActivity.initAppList(context);
            } else if (intent.getAction().equals(Intent.ACTION_PACKAGE_REPLACED)) {
                String packageName = intent.getData().getSchemeSpecificPart();
                MTCore.showToast(context, "更新了" + packageName, true);
                MainActivity.initAppList(context);
            }
        } catch (Exception e) {
            MTCore.ErrorDialog(context, "服务出现错误：应用安装卸载监听服务", "AppInstallServer");
        }
    }

    public IntentFilter getIntentFilter() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_PACKAGE_ADDED);
        intentFilter.addAction(Intent.ACTION_PACKAGE_REMOVED);
        intentFilter.addAction(Intent.ACTION_PACKAGE_REPLACED);
        intentFilter.addDataScheme("package");
        return intentFilter;
    }

    public void register(Context context) {
        context.registerReceiver(this, getIntentFilter());
    }

    public void unregister(Context context) {
        context.unregisterReceiver(this);
    }
}

