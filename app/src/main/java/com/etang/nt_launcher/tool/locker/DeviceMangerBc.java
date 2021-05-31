package com.etang.nt_launcher.tool.locker;

import android.app.admin.DeviceAdminReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * @ProjectName: NT-Eink-Launcher
 * @Package: com.etang.nt_launcher.tool.locker
 * @ClassName: DeviceMangerBc
 * @Description: java类作用描述
 * @Author: 作者名：奶油话梅糖
 * @CreateDate: 2021/5/31 0031 13:17
 * @UpdateUser: 更新者：奶油话梅糖
 * @UpdateDate: 2021/5/31 0031 13:17
 * @UpdateRemark: 更新说明：
 */
//广播接收器
public class DeviceMangerBc extends DeviceAdminReceiver {

    @Override
    public void onEnabled(Context context, Intent intent) {
        super.onEnabled(context, intent);
        Toast.makeText(context, "已获取设备管理者权限", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDisabled(Context context, Intent intent) {
        super.onDisabled(context, intent);
        Toast.makeText(context, "已取消设备管理者权限", Toast.LENGTH_SHORT).show();
    }
}
