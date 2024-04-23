package com.etang.mt_launcher.tool.getapps;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;

import com.etang.mt_launcher.R;
import com.etang.mt_launcher.tool.beans.Bean_AppInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Package: com.etang.nt_launcher.tool.util
 * @ClassName: GetApps
 * @Description: 获取设备内APP信息并装载进List中
 * @CreateDate: 2021/3/19 8:28
 * @UpdateDate: 2021/3/19 8:28
 */
public class GetApps {
    //当前页面TAG
    private static String TAG = "GetApps";

    /**
     * 获取APP列表
     *
     * @param context 继承context
     * @return
     */
    public static List<Bean_AppInfo> GetAppList1(Context context) {
        List<Bean_AppInfo> list = new ArrayList<Bean_AppInfo>();
        PackageManager pm = context.getPackageManager();
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> activities = pm.queryIntentActivities(mainIntent, 0);
        for (ResolveInfo info : activities) {
            String packName = info.activityInfo.packageName;
            if (packName.equals(context.getPackageName())) {
                continue;
            }
            Bean_AppInfo mInfo = new Bean_AppInfo();
            mInfo.setIco(drawableToBitmap(info.activityInfo.applicationInfo.loadIcon(pm)));
            mInfo.setName(info.activityInfo.applicationInfo.loadLabel(pm)
                    .toString());
            mInfo.setPackageName(packName);
            Intent launchIntent = new Intent();
            launchIntent.setComponent(new ComponentName(packName,
                    info.activityInfo.name));
            mInfo.setIntent(launchIntent);
            list.add(mInfo);
        }
        add_diary(context, list);
        return list;
    }

    /**
     * 添加自定义APP信息
     *
     * @param context 继承context
     * @param list    继承list
     */
    private static void add_diary(Context context, List<Bean_AppInfo> list) {
        /**
         * 添加“天气”
         */
        Bean_AppInfo mInfo_weather = new Bean_AppInfo();
        mInfo_weather.setName(context.getString(R.string.app_desktopweather));
        mInfo_weather.setPackageName(context.getPackageName() + ".weather");
        Resources r_weather = context.getResources();
        Bitmap bmp_weather = BitmapFactory.decodeResource(r_weather, R.drawable.ic_weather);
        mInfo_weather.setIco(Bitmap.createBitmap(bmp_weather));
        list.add(mInfo_weather);
        /**
         * 添加“桌面设置”
         */
        Bean_AppInfo mInfo_launchersetting = new Bean_AppInfo();
        mInfo_launchersetting.setName(context.getString(R.string.app_desktopsetting));
        mInfo_launchersetting.setPackageName(context.getPackageName() + ".launchersetting");
        Resources r_launchersetting = context.getResources();
        Bitmap bmp_launchersetting = BitmapFactory.decodeResource(r_launchersetting, R.drawable.ic_setting);
        mInfo_launchersetting.setIco(Bitmap.createBitmap(bmp_launchersetting));
        list.add(mInfo_launchersetting);
//        /**
//         * 如果设备是多看添加“一键清理”
//         */
//        String s_clean = Build.BRAND;
//        if (s_clean.equals("Allwinner")) {
//            AppInfo mInfo_systemclean = new AppInfo();
//            mInfo_systemclean.setName("一键清理");
//            mInfo_systemclean.setPackageName(context.getPackageName() + ".systemclean");
//            Resources r_systemclean = context.getResources();
//            Bitmap bmp_systemclean = BitmapFactory.decodeResource(r_systemclean, R.drawable.ic_clean);
//            mInfo_systemclean.setIco(Bitmap.createBitmap(bmp_systemclean));
//            list.add(mInfo_systemclean);
//        }
//        /**
//         * \添加“检查更新”
//         */
        Bean_AppInfo mInfo_systemupdate = new Bean_AppInfo();
        mInfo_systemupdate.setName(context.getString(R.string.app_desktopupdate));
        mInfo_systemupdate.setPackageName(context.getPackageName() + ".systemupdate");
        Resources r_systemupdate = context.getResources();
        Bitmap bmp_systemupdate = BitmapFactory.decodeResource(r_systemupdate, R.drawable.ic_systemupdate);
        mInfo_systemupdate.setIco(Bitmap.createBitmap(bmp_systemupdate));
        list.add(mInfo_systemupdate);
        /**
         * \添加“刷新屏幕”
         */
        Bean_AppInfo mInfo_uirefresh = new Bean_AppInfo();
        mInfo_uirefresh.setName(context.getString(R.string.app_desktopreforces));
        mInfo_uirefresh.setPackageName(context.getPackageName() + ".uirefresh");
        Resources r_uirefresh = context.getResources();
        Bitmap bmp_uirefresh = BitmapFactory.decodeResource(r_uirefresh, R.drawable.ic_update);
        mInfo_uirefresh.setIco(Bitmap.createBitmap(bmp_uirefresh));
        list.add(mInfo_uirefresh);
        /**
         * \添加“使用记录”
         */
        Bean_AppInfo mInfo_appuserlogs = new Bean_AppInfo();
        mInfo_appuserlogs.setName("使用记录");
        mInfo_appuserlogs.setPackageName(context.getPackageName() + ".appuserlogs");
        Resources r_mInfo_appuserlogs = context.getResources();
        Bitmap bmp_appuserlogs = BitmapFactory.decodeResource(r_mInfo_appuserlogs, R.drawable.ic_appuserlogs);
        mInfo_appuserlogs.setIco(Bitmap.createBitmap(bmp_appuserlogs));
        list.add(mInfo_appuserlogs);
        /**
         * \添加“锁屏”
         */
        Bean_AppInfo mInfo_locker = new Bean_AppInfo();
        mInfo_locker.setName("梅糖待机");
        mInfo_locker.setPackageName(context.getPackageName() + ".locker");
        Resources r_mInfo_locker = context.getResources();
        Bitmap bmp_locker = BitmapFactory.decodeResource(r_mInfo_locker, R.drawable.ic_mtlocker);
        mInfo_locker.setIco(Bitmap.createBitmap(bmp_locker));
        list.add(mInfo_locker);
//        /**
//         * \添加“工具箱”
//         */
//        AppInfo mInfo_userhelper = new AppInfo();
//        mInfo_userhelper.setName("工具箱");
//        mInfo_userhelper.setPackageName(context.getPackageName() + ".toolsbox");
//        Resources r_userhelper = context.getResources();
//        Bitmap bmp_userhelper = BitmapFactory.decodeResource(r_userhelper, R.drawable.ic_appuserlogs);
//        mInfo_userhelper.setIco(Bitmap.createBitmap(bmp_userhelper));
//        list.add(mInfo_userhelper);
    }

    /**
     * drawable强转bitmap
     *
     * @param drawable
     * @return
     */
    public static final Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(),
                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
//        return BitMapTools.toRoundCorner(bitmap, 64);//灰色bitmap
        return bitmap;//正常bitmap
    }
}
