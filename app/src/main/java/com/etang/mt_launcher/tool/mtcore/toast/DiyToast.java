package com.etang.mt_launcher.tool.mtcore.toast;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.etang.mt_launcher.R;
import com.etang.mt_launcher.tool.mtcore.MTCore;

/**
 * @Package: com.etang.nt_launcher.tool.toast
 * @ClassName: DiyToast
 * @Description: 自定义的Toast
 * @CreateDate: 2021/3/19 8:27
 * @UpdateDate: 2021/3/19 8:27
 */
public class DiyToast {
    private static Toast toast;
    //当前页面TAG
    private static String TAG = "DiyToast";

    public static void show(Context context, String s, boolean long_or_short) {
        if (toast != null) {
            toast.cancel();
        }
        if (long_or_short) {
            toast = Toast.makeText(context, "MTCore_Message:" + s, Toast.LENGTH_LONG);
        } else {
            toast = Toast.makeText(context, "MTCore_Message:" + s, Toast.LENGTH_SHORT);
        }
//        toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.show();
    }
}
