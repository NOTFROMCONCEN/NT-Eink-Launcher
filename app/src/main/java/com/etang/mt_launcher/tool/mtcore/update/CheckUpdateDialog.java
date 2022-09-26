package com.etang.mt_launcher.tool.mtcore.update;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import androidx.core.content.FileProvider;

import com.etang.mt_launcher.BuildConfig;
import com.etang.mt_launcher.tool.mtcore.toast.DiyToast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * 于 2021年10月22日 23点01分 尝试解耦
 * 别看了，以前的版本早就找不到了，现在的能用就行
 * 有的地方越来越看不懂了
 * 于2021年12月11日 17点42分
 * 看懂了，但是又没看懂，试下多加两个注释
 * 于2022年1月2日 12点21分
 * 优化了整体结构，现在整个代码可以复制到其他工程使用了，前提是服务器有对应的资源
 * 与2022年3月7日 09点30分
 * 更新了链接服务器的方法，更加稳定了
 */
public class CheckUpdateDialog {
    //  上下文
    private static Context mContext;
    //  Activity
    private static Activity mActivity;
    //  对话框
    private static ProgressDialog mDownloadDialog;
    //  判断是否停止
    private static boolean mIsCancel = false;
    //  进度
    private static int mProgress;
    //  文件保存路径
    private static String mSavePath;
    //最新版本号
    private static int mVersion_new = 0;
    //当前下载链接
    private static String mVersion_Url = "";
    //当前更新日志
    private static String mVersion_Logs = "";

    /**
     * 检查更新
     *
     * @param context  继承上下文
     * @param activity 继承活动
     * @param where    从哪里调用的本方法
     */
    public static void check_update(final Context context, final Activity activity, final String where) {
        mContext = context;
        mActivity = activity;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final AlertDialog dialog = builder.create();
        dialog.setTitle("链接中......");
        dialog.setMessage("请稍后，正在链接中~~~");
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Bundle data = msg.getData();
                String val = data.getString("weblink_state");
                switch (val) {
                    case "1":
                        if (where.equals("about")) {
                            dialog.show();
                        }
                        break;
                    case "2":
                        if (where.equals("about")) {
                            Bundle data_error = msg.getData();
                            String error_message = data_error.getString("error_message");
                            dialog.dismiss();
                            DiyToast.showToast(mContext, error_message, true);
                            Log.e("11111111", "出现错误！！！！！！！: " + error_message);
//                            MTCore.check_erroe_code(MTCore.error_code_timeout);
                        }
                        break;
                    case "3":
                        if (where.equals("about")) {
                            dialog.dismiss();
                        }
                    case "4":
                        if (where.equals("about")) {
                            dialog.dismiss();
                        }
                        break;
                    case "5":
                        dialog.dismiss();
                        Bundle data_version = msg.getData();
                        //获取APP名称
                        String version_Apps = data_version.getString("version_Apps")
                                .replace("<app>", "")
                                .replace("</app>", "")
                                .replace(" ", "");
                        //获取版本号Code
                        String version_Code = data_version.getString("version_Code")
                                .replace("<code>", "")
                                .replace("</code>", "")
                                .replace(" ", "");
                        //获取版本号名字
                        String version_Name = data_version.getString("version_Name")
                                .replace("<name>", "")
                                .replace("</name>", "")
                                .replace(" ", "");
                        //获取下载链接
                        String version_Urls = data_version.getString("version_Urls")
                                .replace("<updateurl>", "")
                                .replace("</updateurl>", "")
                                .replace(" ", "");
                        //获取更新日志
                        String version_Logs = data_version.getString("version_Logs")
                                .replace("<updatemessage>", "")
                                .replace("</updatemessage>", "")
                                .replace("<date>", "")
                                .replace("</date>", "")
                                .replace("<message>", "")
                                .replace("</message>", "")
                                .replace(" ", "");
                        Log.e("TAG", "handleMessage: " + version_Logs);
                        mVersion_new = Integer.valueOf(version_Code);
                        mVersion_Url = version_Urls;
                        mVersion_Logs = version_Logs;
                        check_beta(where);
                        break;
                }
            }
        };

        new Thread(new Runnable() {
            @Override
            public void run() {
                Message msg_start = new Message();
                Bundle data = new Bundle();
                data.putString("weblink_state", "1");
                msg_start.setData(data);
                handler.sendMessage(msg_start);
                try {
                    Document doc = Jsoup.connect(
                            "https://yp.nyanon.online/data/User/admin/home/NaiYouApks/for%20web/Android/js/"
                                    + mContext.getPackageName() +
                                    ".xml").get();
                    /**
                     * 开始解析
                     * */
                    final Elements titleAndPic = doc.select("books");
                    /**
                     * 发送结果
                     * */
                    Message msg_version = new Message();
                    Bundle data_version = new Bundle();
                    data_version.putString("weblink_state", "5");
                    data_version.putString("version_Apps", String.valueOf(titleAndPic.get(0).select("app")));
                    data_version.putString("version_Code", String.valueOf(titleAndPic.get(0).select("code")));
                    data_version.putString("version_Name", String.valueOf(titleAndPic.get(0).select("name")));
                    data_version.putString("version_Urls", String.valueOf(titleAndPic.get(0).select("updateurl")));
                    data_version.putString("version_Logs", String.valueOf(titleAndPic.get(0).select("updatemessage")));
                    msg_version.setData(data_version);
                    handler.sendMessage(msg_version);
                } catch (Exception e) {
                    Message msg_link_error = new Message();
                    Bundle data_error = new Bundle();
                    data_error.putString("weblink_state", "2");
                    data_error.putString("error_message", e.toString());
                    msg_link_error.setData(data_error);
                    handler.sendMessage(msg_link_error);
                }
            }
        }).start();
    }

    private static void check_beta(final String where) {
        int version_code = BuildConfig.VERSION_CODE;
        if (version_code != mVersion_new) {
            if (version_code < mVersion_new) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setCancelable(false);// 设置是否可以通过点击Back键取消
                builder.setTitle("发现更新");
                builder.setMessage("发现最新版本：\n" + String.valueOf(mVersion_new) + "\nAPP需要更新。\n\n更新日志：\n" + mVersion_Logs);
                builder.setNeutralButton("更新", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startUpdate();
                    }
                });
                builder.setPositiveButton("关闭", null);
                builder.show();
            } else if (version_code == mVersion_new || version_code > mVersion_new) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setCancelable(false);// 设置是否可以通过点击Back键取消
                builder.setTitle("未发现更新或本地版本号过高");
                builder.setMessage("当前版本：" + "\n" + String.valueOf(version_code) + "\n" + "现有版本：" + "\n" + mVersion_new);
                builder.setNeutralButton("重新下载正式版", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startUpdate();
                    }
                });
                builder.setPositiveButton("关闭", null);
                builder.show();
            }
        } else {
            if (where == "about") {
                final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setCancelable(false);// 设置是否可以通过点击Back键取消
                builder.setTitle("未发现更新");
                builder.setMessage("当前版本：" + "\n" + String.valueOf(version_code) + "\n" + "现有版本：" + "\n" + mVersion_new);
                builder.setNeutralButton("重新下载", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startUpdate();
                    }
                });
                builder.setPositiveButton("关闭", null);
                builder.show();
            }
        }
    }


    private static void startUpdate() {
        boolean isGranted = true;
        if (android.os.Build.VERSION.SDK_INT >= 23) {
            if (mActivity.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                //如果没有写sd卡权限
                isGranted = false;
            }
            if (mActivity.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                isGranted = false;
            }
            if (!isGranted) {
                mActivity.requestPermissions(
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission
                                .ACCESS_FINE_LOCATION,
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        102);
            } else {
            }
        }
        mIsCancel = false;
        showDownloadDialog();
    }


    /*
     * 显示正在下载对话框
     */
    protected static void showDownloadDialog() {
        ProgressDialog dialog = new ProgressDialog(mContext);
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);// 设置水平进度条
        dialog.setCancelable(false);// 设置是否可以通过点击Back键取消
        dialog.setCanceledOnTouchOutside(false);// 设置在点击Dialog外是否取消Dialog进度条
        dialog.setTitle("正在下载......");// 设置标题
        dialog.setMessage("请不要离开本页面。如果安装失败，请前往 根目录/" + mContext.getPackageName() + "/ 目录下进行安装");
        dialog.setButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 隐藏当前对话框
                dialog.dismiss();
                // 设置下载状态为取消
                mIsCancel = true;
            }
        });
        dialog.setMax(100);
        mDownloadDialog = dialog;
        mDownloadDialog.show();
        // 下载文件
        downloadAPK();
    }

    /*
     * 开启新线程下载apk文件
     */
    private static void downloadAPK() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                        String sdPath = Environment.getExternalStorageDirectory() + "/";
//                      文件保存路径
                        mSavePath = sdPath + mContext.getPackageName() + "/";
                        File dir = new File(mSavePath);
                        if (!dir.exists()) {
                            dir.mkdir();
                        }
                        // 下载文件
                        HttpsURLConnection conn = (HttpsURLConnection) new URL(mVersion_Url).openConnection();
                        conn.connect();
                        InputStream is = conn.getInputStream();
                        int length = conn.getContentLength();
                        File apkFile = new File(mSavePath, mContext.getPackageName() + "_" + mVersion_new + ".apk");
                        FileOutputStream fos = new FileOutputStream(apkFile);
                        int count = 0;
                        byte[] buffer = new byte[5120];
                        while (!mIsCancel) {
                            int numread = is.read(buffer);
                            count += numread;
                            // 计算进度条的当前位置
                            mProgress = (int) (((float) count / length) * 100);
                            // 更新进度条
                            mUpdateProgressHandler.sendEmptyMessage(1);
                            // 下载完成
                            if (numread < 0) {
                                mUpdateProgressHandler.sendEmptyMessage(2);
                                break;
                            }
                            fos.write(buffer, 0, numread);
                        }
                        fos.close();
                        is.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 接收消息
     */
    private static Handler mUpdateProgressHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    // 设置进度条
                    mDownloadDialog.setProgress(mProgress);
                    break;
                case 2:
                    // 隐藏当前下载对话框
                    mDownloadDialog.dismiss();
                    // 安装 APK 文件
                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder.setCancelable(false);// 设置是否可以通过点击Back键取消
                    builder.setTitle("安装");
                    builder.setMessage("请点击“确定”按钮进行安装，如果安装失败请前往 根目录/" + mContext.getPackageName() + "/ 目录下进行安装");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            installApk(mSavePath);
                        }
                    });
                    builder.setNeutralButton("稍后安装", null);
                    builder.show();
            }
        }

    };

    public static void installApk(String apkPath) {
        if (mContext == null || TextUtils.isEmpty(apkPath)) {
            return;
        }
        File file = new File(mSavePath, mContext.getPackageName() + "_" + mVersion_new + ".apk");
        Intent intent = new Intent(Intent.ACTION_VIEW);
        //判读版本是否在7.0以上
        if (Build.VERSION.SDK_INT >= 24) {
            //provider authorities
            Uri apkUri = FileProvider.getUriForFile(mContext, mContext.getPackageName() + ".fileprovider", file);
            //Granting Temporary Permissions to a URI
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        }
        mActivity.finish();
        mContext.startActivity(intent);
    }
}