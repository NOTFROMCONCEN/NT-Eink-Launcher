package com.etang.nt_launcher.tool.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Window;
import android.view.WindowManager;

/**
 * @Package: com.etang.nt_launcher.tool.dialog
 * @ClassName: DeBugDialog
 * @Description: 用于展示报错信息的Dialog
 * @CreateDate: 2021/3/19 8:23
 * @UpdateDate: 2021/3/19 8:23
 */
public class DeBugDialog {
    /**
     * 显示报错Dialog，用于错误信息判断
     *
     * @param context Context继承
     * @param e       报错信息
     * @param TAG     当前报错页面的TAG
     */
    public static void debug_show_dialog(final Context context, final String e, final String TAG) {
        //新建一个AlertDialog
        final AlertDialog builder = new AlertDialog.Builder(context).create();
        //设置标题
        switch (TAG) {
            case "AboutView":
                builder.setMessage(TAG + "|关于界面View出现错误信息：" + "\n" + e);
                break;
            case "AboutActivity":
                builder.setMessage(TAG + "|关于设置出现错误信息：" + "\n" + e);
                break;
            case "DeskTopSettingActivity":
                builder.setMessage(TAG + "|应用列表设置设置出现错误信息：" + "\n" + e);
                break;
            case "HindAppSetting":
                builder.setMessage(TAG + "|隐藏应用设置出现错误信息：" + "\n" + e);
                break;
            case "InfoRebackActivity":
                builder.setMessage(TAG + "|信息反馈设置出现错误信息：" + "\n" + e);
                break;
            case "ChoseImagesActivity":
                builder.setMessage(TAG + "|选择壁纸设置出现错误信息：" + "\n" + e);
                break;
            case "TextSizeSetting":
                builder.setMessage(TAG + "|文本大小设置出现错误信息：" + "\n" + e);
                break;
            case "UireFreshActivity":
                builder.setMessage(TAG + "|刷新设置出现错误信息：" + "\n" + e);
                break;
            case "WeatherActivity":
                builder.setMessage(TAG + "|天气设置出现错误信息：" + "\n" + e);
                break;
            case "SettingActivity":
                builder.setMessage(TAG + "|设置界面出现错误信息：" + "\n" + e);
                break;
            case "ChoseFragment":
                builder.setMessage(TAG + "|语言选择界面出现错误信息：" + "\n" + e);
                break;
            case "OneFragment":
                builder.setMessage(TAG + "|出现错误信息：" + "\n" + e);
                break;
            case "TwoFragment":
                builder.setMessage(TAG + "|出现错误信息：" + "\n" + e);
                break;
            case "WelecomeActivity":
                builder.setMessage(TAG + "|欢迎界面出现错误信息：" + "\n" + e);
                break;
            case "MainActivity":
                builder.setMessage(TAG + "|桌面主页出现错误信息：" + "\n" + e);
                break;
            case "BitMapTools":
                builder.setMessage(TAG + "|位图工具出现错误信息：" + "\n" + e);
                break;
            case "CheckUpdateDialog":
                builder.setMessage(TAG + "|软件更新Dialog出现错误信息：" + "\n" + e);
                break;
            case "MessageDialog":
                builder.setMessage(TAG + "|普通信息Dialog出现错误信息：" + "\n" + e);
                break;
            case "NewUserDialog":
                builder.setMessage(TAG + "|发送新用户信息Dialog出现错误信息：" + "\n" + e);
                break;
            case "PayMeDialog":
                builder.setMessage(TAG + "|支付Dialog出现错误信息：" + "\n" + e);
                break;
            case "UnInstallDialog":
                builder.setMessage(TAG + "|卸载应用Dialog出现错误信息：" + "\n" + e);
                break;
            case "SavePermission":
                builder.setMessage(TAG + "|检查、申请权限出现错误信息：" + "\n" + e);
                break;
            case "SaveArrayImageUtil":
                builder.setMessage(TAG + "|存储列表（图片）出现错误信息：" + "\n" + e);
                break;
            case "SaveArrayListUtil":
                builder.setMessage(TAG + "|存储列表（应用）出现错误信息：" + "\n" + e);
                break;
            case "AppInstallServer":
                builder.setMessage(TAG + "|监听APP安装服务出现错误信息：" + "\n" + e);
                break;
            case "MyDataBaseHelper":
                builder.setMessage(TAG + "|数据库出现错误信息：" + "\n" + e);
                break;
            case "DiyToast":
                builder.setMessage(TAG + "|自定义Toast出现错误信息：" + "\n" + e);
                break;
            case "ApInfo":
                builder.setMessage(TAG + "|APP信息出现错误信息：" + "\n" + e);
                break;
            case "DeskTopGridViewBaseAdapter":
                builder.setMessage(TAG + "|桌面容器出现错误信息：" + "\n" + e);
                break;
            case "GetApps":
                builder.setMessage(TAG + "|获取APP信息出现错误信息：" + "\n" + e);
                break;
            case "MTCore":
                builder.setMessage(TAG + "|MT核心出现错误信息：" + "\n" + e);
                break;
            case "StreamTool":
                builder.setMessage(TAG + "|字节流工具出现错误信息：" + "\n" + e);
                break;
        }
        //关闭按钮
        builder.setButton("关闭", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                builder.dismiss();
            }
        });
        //推送信息按钮
        builder.setButton2("发送错误（需要网络）", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                NewUserDialog.dialog_show(context, e, false);
                builder.dismiss();
            }
        });
        //显示Dialog
        builder.show();
        Window window = builder.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.dimAmount = 0f;
        window.setAttributes(lp);
    }
}
