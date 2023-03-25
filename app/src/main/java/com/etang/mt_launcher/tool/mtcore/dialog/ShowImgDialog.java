package com.etang.mt_launcher.tool.mtcore.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
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
public class ShowImgDialog {
    //当前页面TAG
    private static String TAG = "PayMeDialog";

    public static void show_dialog(final Activity activity, final String input_dev) {
        final AlertDialog alertDialog = new AlertDialog.Builder(activity).create();
        View view = LayoutInflater.from(activity).inflate(R.layout.dialog_showimg_dialog, null, false);
        alertDialog.setView(view);
        //绑定控件
        Button btn_payme_close = (Button) view.findViewById(R.id.btn_showimg_close);
        Button btn_payme_openlink = (Button) view.findViewById(R.id.btn_showimg_openlink);
        ImageView iv_show = (ImageView) view.findViewById(R.id.iv_showimg_indeximg);
        if (input_dev.equals("moan")) {
            iv_show.setImageResource(R.drawable.qrcode_for_moan);
        }
        btn_payme_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        btn_payme_openlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (input_dev.equals("moan")){
                    Uri uri = Uri.parse("https://nyanon.online/2001/default/for_moan.html");
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    intent.setData(uri);
                    activity.startActivity(intent);
                }
                if (input_dev.equals("kindle")) {
                    Uri uri = Uri.parse("https://nyanon.online/2023/default/for_kindle.html");
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    intent.setData(uri);
                    activity.startActivity(intent);
                }
            }
        });
        alertDialog.show();
    }
}
