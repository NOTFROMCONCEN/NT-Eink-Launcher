package com.etang.nt_launcher.tool.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
    //当前页面TAG
    private static String TAG = "PayMeDialog";

    public static void show_dialog(final Activity activity) {
        final AlertDialog alertDialog = new AlertDialog.Builder(activity).create();
        View view = LayoutInflater.from(activity).inflate(R.layout.dialog_payme, null, false);
        alertDialog.setView(view);
        //绑定控件
        ImageView iv_ali = (ImageView) view.findViewById(R.id.iv_alipay);
        iv_ali.setImageResource(R.drawable.im_alipay);
        ImageView iv_wechat = (ImageView) view.findViewById(R.id.iv_wechatpay);
        iv_wechat.setImageResource(R.drawable.im_wechatpay);
        ImageView iv_aifadian = (ImageView) view.findViewById(R.id.iv_aifadian);
        iv_aifadian.setImageResource(R.drawable.im_aifadian);
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
        iv_aifadian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = LayoutInflater.from(activity).inflate(R.layout.dialog_payme_aifadian, null, false);
                new AlertDialog.Builder(activity).setTitle("请扫码——爱发电").setView(view).setPositiveButton("关闭", null).show();
            }
        });
        alertDialog.show();
    }
}
