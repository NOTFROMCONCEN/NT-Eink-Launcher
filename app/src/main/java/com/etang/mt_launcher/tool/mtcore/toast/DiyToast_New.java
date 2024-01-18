package com.etang.mt_launcher.tool.mtcore.toast;

import android.content.Context;
import android.widget.Toast;

/**
 * - @Package: com.etang.nt_launcher.tool.toast
 * - @ClassName: DiyToast
 * - @Description: 自定义的Toast
 * - @CreateDate: 2021/3/19 8:27
 * - @UpdateDate: 2021/3/19 8:27
 */

public class DiyToast_New {
    private static Toast toast;

    public static void show(Context context, String message, boolean isLong) {
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(context, "MTCore_Message:" + message, isLong ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT);
        toast.show();
    }
}

