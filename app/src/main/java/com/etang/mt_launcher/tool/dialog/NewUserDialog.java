package com.etang.mt_launcher.tool.dialog;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.webkit.WebView;

import com.etang.mt_launcher.tool.util.MTCore;

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
            stringBuffer.append(getDeviceInfo());
        } else {
            stringBuffer.append("设备报错");
            stringBuffer.append("%0D%0A%0D%0A");
            stringBuffer.append("报错信息：");
            stringBuffer.append(info);
            stringBuffer.append("%0D%0A%0D%0A");
            stringBuffer.append("设备信息：");
            stringBuffer.append("%0D%0A%0D%0A");
            stringBuffer.append(getDeviceInfo());
        }
        wv.loadUrl(stringBuffer.toString());
        Log.e("发送错误", stringBuffer.toString());
    }

    /**
     * 获取指定字段信息
     *
     * @return
     */
    private static String getDeviceInfo() {
        StringBuffer sb = new StringBuffer();
        sb.append("---主板：" + Build.BOARD);
        sb.append("%0D%0A%0D%0A");
        sb.append("---系统启动程序版本号：" + Build.BOOTLOADER);
        sb.append("%0D%0A%0D%0A");
        sb.append("---系统定制商：" + Build.BRAND);
        sb.append("%0D%0A%0D%0A");
        sb.append("---cpu指令集：" + Build.CPU_ABI);
        sb.append("%0D%0A%0D%0A");
        sb.append("---cpu指令集2：" + Build.CPU_ABI2);
        sb.append("%0D%0A%0D%0A");
        sb.append("---设置参数：" + Build.DEVICE);
        sb.append("%0D%0A%0D%0A");
        sb.append("---显示屏参数：" + Build.DISPLAY);
        sb.append("%0D%0A%0D%0A");
        sb.append("---无线电固件版本：" + Build.getRadioVersion());
        sb.append("%0D%0A%0D%0A");
        sb.append("---硬件识别码：" + Build.FINGERPRINT);
        sb.append("%0D%0A%0D%0A");
        sb.append("---硬件名称：" + Build.HARDWARE);
        sb.append("%0D%0A%0D%0A");
        sb.append("---HOST:" + Build.HOST);
        sb.append("%0D%0A%0D%0A");
        sb.append("---修订版本列表：" + Build.ID);
        sb.append("%0D%0A%0D%0A");
        sb.append("---硬件制造商：" + Build.MANUFACTURER);
        sb.append("%0D%0A%0D%0A");
        sb.append("---版本：" + Build.MODEL);
        sb.append("%0D%0A%0D%0A");
        sb.append("---硬件序列号：" + Build.SERIAL);
        sb.append("%0D%0A%0D%0A");
        sb.append("---手机制造商：" + Build.PRODUCT);
        sb.append("%0D%0A%0D%0A");
        sb.append("---描述Build的标签：" + Build.TAGS);
        sb.append("%0D%0A%0D%0A");
        sb.append("---TIME:" + Build.TIME);
        sb.append("%0D%0A%0D%0A");
        sb.append("---builder类型：" + Build.TYPE);
        sb.append("%0D%0A%0D%0A");
        sb.append("---USER:" + Build.USER);
        sb.append("%0D%0A%0D%0A");
        return sb.toString();
    }
}
