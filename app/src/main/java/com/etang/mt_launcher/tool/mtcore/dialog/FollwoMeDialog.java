package com.etang.mt_launcher.tool.mtcore.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.etang.mt_launcher.R;

/**
 * @Package: com.etang.nt_launcher.tool.dialog
 * @ClassName: PayMeDialog
 * @Description: 用于展示赞赏支付方式的弹出框
 * @CreateDate: 2021/3/19 8:24
 * @UpdateDate: 2021/3/19 8:24
 */
public class FollwoMeDialog {
    //当前页面TAG
    private static String TAG = "FollwoMeDialog";

    public static void show(final Activity activity) {
        final AlertDialog alertDialog = new AlertDialog.Builder(activity).create();
        View view = LayoutInflater.from(activity).inflate(R.layout.dialog_follwome, null, false);
        alertDialog.setView(view);
        //绑定控件
        LinearLayout iv_ali = (LinearLayout) view.findViewById(R.id.iv_bilibili);
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
//                View view = LayoutInflater.from(activity).inflate(R.layout.dialog_, null, false);
                new AlertDialog.Builder(activity).setTitle("请扫码——哔哩哔哩").setView(view).setPositiveButton("关闭", null).show();
            }
        });
        alertDialog.show();
    }
}
