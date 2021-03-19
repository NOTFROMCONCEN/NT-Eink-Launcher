package com.etang.nt_launcher.tool.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.etang.nt_launcher.R;

/**
 * @Package: com.etang.nt_launcher.tool.dialog
 * @ClassName: PayMeDialog
 * @Description: 用于展示赞赏支付方式的弹出框
 * @CreateDate: 2021/3/19 8:24
 * @UpdateDate: 2021/3/19 8:24
 */
public class PayMeDialog {

    public static void show_dialog(final Activity activity) {
        final AlertDialog alertDialog = new AlertDialog.Builder(activity).create();
        View view = LayoutInflater.from(activity).inflate(R.layout.dialog_payme, null, false);
        alertDialog.setView(view);
        //绑定控件
        ImageView iv_ali = (ImageView) view.findViewById(R.id.iv_alipay);
        ImageView iv_wechat = (ImageView) view.findViewById(R.id.iv_wechatpay);
        Button btn_payme_close = (Button) view.findViewById(R.id.btn_payme_close);
        btn_payme_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        iv_ali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = LayoutInflater.from(activity).inflate(R.layout.dialog_payme_ali, null, false);
                new AlertDialog.Builder(activity).setTitle("请扫码——支付宝").setView(view).setPositiveButton("关闭", null).show();
            }
        });
        iv_wechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = LayoutInflater.from(activity).inflate(R.layout.dialog_payme_wechat, null, false);
                new AlertDialog.Builder(activity).setTitle("请扫码——微信").setView(view).setPositiveButton("关闭", null).show();
            }
        });
        alertDialog.show();
//        if (number.equals("no")) {
//            View view = LayoutInflater.from(activity).inflate(R.layout.dialog_payme, null, false);
//            builder.setView(view);
//        } else if (number.equals("ali")) {
//            View view = LayoutInflater.from(activity).inflate(R.layout.dialog_payme_ali, null, false);
//            builder.setView(view);
//        } else if (number.equals("aifadian")) {
//            View view = LayoutInflater.from(activity).inflate(R.layout.dialog_payme_aifadian, null, false);
//            builder.setView(view);
//        } else if (number.equals("wechat")) {
//            View view = LayoutInflater.from(activity).inflate(R.layout.dialog_payme_wechat, null, false);
//            builder.setView(view);
//        }
//        builder.setTitle("请选择平台");
//        builder.setPositiveButton("支付宝", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                show_dialog(activity, "ali");
//            }
//        });
//        builder.setNegativeButton("微信", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                show_dialog(activity, "wechat");
//            }
//        });
//        builder.setNeutralButton("关闭", null);
//        builder.show();
    }
}
