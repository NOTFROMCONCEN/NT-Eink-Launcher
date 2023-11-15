package com.etang.mt_launcher.tool.mtcore.toast;

import android.content.Context;
import android.widget.Toast;

public class DiyToast_New_2 {
    private static Toast toast;

    public static void show(Context context, String message, boolean isLong) {
        if (toast != null) {
            toast.cancel();
        }
        if (context == null || message == null) {
            throw new IllegalArgumentException("Context or message is null!");
        }
        toast = Toast.makeText(context, "MTCore_Message:" + message, isLong ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT);
        toast.show();
    }
}