package com.etang.mt_launcher.tool.mtcore.dialog;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.webkit.WebView;

import com.etang.mt_launcher.tool.mtcore.MTCore;
import com.etang.mt_launcher.tool.mtcore.getinfo.GetSystemInfo;

/**
 * @Package: com.etang.nt_launcher.tool.dialog
 * @ClassName: NewUserDialog
 * @Description: 用于给服务器发送新用户激活信息、设备信息的弹出框（无View）
 * @CreateDate: 2021/3/19 8:23
 * @UpdateDate: 2021/3/19 8:23
 */
public class NewUserDialog {
    //当前页面TAG
    private static String TAG = "NewUserDialog";

    public static void dialog_show(Context context, String info, boolean newuser_or_debug) {
        if (newuser_or_debug) {
            test(context, info, newuser_or_debug);
        } else {
            test(context, info, newuser_or_debug);
        }
    }

    private static void test(Context context, String info, boolean newuser_or_debug) {
        GetSystemInfo getSystemInfo = new GetSystemInfo();
        WebView wv = new WebView(context);
        StringBuffer stringBuffer = new StringBuffer();
        //第一段
        stringBuffer.append("https://" + MTCore.SERVER_WeChat_URL + "/" + MTCore.SERVER_WeChat_URL_KEY + ".send?text=" + context.getPackageName() + "---");
        //第二段
        stringBuffer.append("&desp=");
        //判断激活的属性
        if (newuser_or_debug) {
            stringBuffer.append(info);
            stringBuffer.append("设备激活");
            stringBuffer.append("%0D%0A%0D%0A");
            stringBuffer.append("设备信息：");
            stringBuffer.append("%0D%0A%0D%0A");
            stringBuffer.append(getSystemInfo.getinfo());
        } else {
            stringBuffer.append("设备报错");
            stringBuffer.append("%0D%0A%0D%0A");
            stringBuffer.append("报错信息：");
            stringBuffer.append(info);
            stringBuffer.append("%0D%0A%0D%0A");
            stringBuffer.append("设备信息：");
            stringBuffer.append("%0D%0A%0D%0A");
            stringBuffer.append(getSystemInfo.getinfo());
        }
        wv.loadUrl(stringBuffer.toString());
        Log.e("发送错误", stringBuffer.toString());
    }
}
