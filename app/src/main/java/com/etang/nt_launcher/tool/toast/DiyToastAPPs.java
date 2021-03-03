package com.etang.nt_launcher.tool.toast;

import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.etang.nt_launcher.R;


public class DiyToastAPPs {
    private static Toast toast;

    public static void showToast(final String app_pack_info, final Context context, String s, boolean long_or_short) {
        if (toast != null) {
            toast.cancel();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.layout_toast_back_apps, null);
        TextView tv = (TextView) view.findViewById(R.id.tv_toast_show_apps);
        TextView tv_open = (TextView) view.findViewById(R.id.tv_toast_show_apps_open);
        if (long_or_short) {
            toast = Toast.makeText(context, s, Toast.LENGTH_LONG);
        } else {
            toast = Toast.makeText(context, s, Toast.LENGTH_SHORT);
        }
        tv_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = context.getPackageManager().getLaunchIntentForPackage(app_pack_info);
                context.startActivity(intent);
                toast.cancel();
            }
        });
        toast.setView(view);
        tv.setText(s);
        toast.setGravity(Gravity.TOP, 0, 0);
        toast.show();
    }
}
