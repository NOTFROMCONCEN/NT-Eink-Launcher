// 声明包名：com.etang.mt_launcher.tool.mtcore.toast  
package com.etang.mt_launcher.tool.mtcore.toast;

// 导入Android的Context类，它是一个抽象类，提供了关于应用环境全局信息的接口  

import android.content.Context;

// 导入Android的Gravity类，用于设置控件的位置  
import android.view.Gravity;

// 导入Android的LayoutInflater类，用于创建XML布局实例  
import android.view.LayoutInflater;

// 导入Android的View类，它是所有UI组件的根类  
import android.view.View;

// 导入Android的TextView类，用于显示文本信息  
import android.widget.TextView;

// 导入Android的Toast类，用于显示提示消息  
import android.widget.Toast;

// 导入自定义包中的R类，R类包含了这个应用程序的所有资源ID  
import com.etang.mt_launcher.R;

// 导入自定义包中的MTCore类，这个类应该包含了一些公共的方法或属性被本类的其他方法使用  
import com.etang.mt_launcher.tool.mtcore.MTCore;

// 声明一个公共类DiyToast，这个类包含了自定义Toast的方法  

/**
 * @Package: com.etang.nt_launcher.tool.toast
 * @ClassName: DiyToast
 * @Description: 自定义的Toast
 * @CreateDate: 2021/3/19 8:27
 * @UpdateDate: 2021/3/19 8:27
 */
public class DiyToast {
    // 声明一个私有的静态的Toast对象，这个对象在类的所有实例中共享，第一次使用时初始化，之后不再改变  
    private static Toast toast;

    // 声明一个私有的静态的String变量，用于记录当前页面的TAG，方便后续调试和错误排查  
    private static String TAG = "DiyToast";

    // 定义一个公共的静态方法show，这个方法接收三个参数：一个Context对象，一个String对象，和一个boolean对象  
    public static void show(Context context, String s, boolean long_or_short) {
        // 如果toast对象不为空，那么取消该对象的显示，避免重复显示  
        if (toast != null) {
            toast.cancel();
        }
        // 根据传入的boolean值决定Toast的显示时长，true为长时，false为短时  
        if (long_or_short) {
            toast = Toast.makeText(context, "MTCore_Message:" + s, Toast.LENGTH_LONG);
        } else {
            toast = Toast.makeText(context, "MTCore_Message:" + s, Toast.LENGTH_SHORT);
        }
        // 这行代码注释掉了，如果需要设置Toast的位置，可以去掉注释并选择合适的gravity值，这里设置为底部的位置，横向居中，纵向居中。  
//        toast.setGravity(Gravity.BOTTOM, 0, 0);  
        // 显示Toast对象，如果之前已经显示了一个Toast对象，那么会取消之前对象的显示并替换为新的对象。  
        toast.show();
    }
}