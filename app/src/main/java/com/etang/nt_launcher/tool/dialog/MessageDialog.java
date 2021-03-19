package com.etang.nt_launcher.tool.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * @Package: com.etang.nt_launcher.tool.dialog
 * @ClassName: MessageDialog
 * @Description: 用于展示普通信息的Dialog
 * @CreateDate: 2021/3/19 8:23
 * @UpdateDate: 2021/3/19 8:23
 */
public class MessageDialog {
    private void show_dialog(final String msg, Activity activity) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("来自系统的消息");
        builder.setMessage(msg);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
    }
}
