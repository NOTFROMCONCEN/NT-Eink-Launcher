// 声明包名：com.etang.mt_launcher.tool.mtcore.toast  
package com.etang.mt_launcher.tool.mtcore.toast;

// 导入Android的Context类，它是一个抽象类，提供了关于应用环境全局信息的接口  

import android.content.Context;

// 导入Android的Toast类，用于显示提示消息  
import android.widget.Toast;

// 这是一个Java类文档注释，用于描述类的基本信息。  

/**
 * - @Package: com.etang.nt_launcher.tool.toast
 * - @ClassName: DiyToast
 * - @Description: 自定义的Toast，这个类用于自定义Toast消息的显示。
 * - @CreateDate: 2021/3/19 8:27
 * - @UpdateDate: 2021/3/19 8:27
 */
public class DiyToast_New {
    // 声明一个私有的静态的Toast对象，这个对象在类的所有实例中共享，第一次使用时初始化，之后不再改变。  
    private static Toast toast;

    // 定义一个公共的静态方法show，这个方法接收三个参数：一个Context对象，一个String对象，和一个boolean对象。该方法用于显示Toast消息。  
    public static void show(Context context, String message, boolean isLong) {
        // 如果之前已经有一个Toast消息在显示，取消该消息的显示。  
        if (toast != null) {
            toast.cancel();
        }
        // 根据传入的boolean值决定Toast的显示时长，true为长时，false为短时。  
        toast = Toast.makeText(context, "MTCore_Message:" + message, isLong ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT);
        // 显示Toast消息。  
        toast.show();
    }
}