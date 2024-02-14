package com.etang.mt_launcher.tool.mtcore.getinfo;

import android.os.Build;

public class GetSystemInfo {
    public String getinfo() {
        try {
            GetAppInfo getAppInfo = new GetAppInfo();
            getAppInfo.AndroidInfo();
            StringBuffer sb = new StringBuffer();
            String ln = "\r\n";
            sb.append("---主板：" + Build.BOARD);
            sb.append("%0D%0A%0D%0A");
            sb.append(ln);
            sb.append("---系统启动程序版本号：" + Build.BOOTLOADER);
            sb.append("%0D%0A%0D%0A");
            sb.append(ln);
            sb.append("---系统定制商：" + Build.BRAND);
            sb.append("%0D%0A%0D%0A");
            sb.append(ln);
            sb.append("---cpu指令集：" + Build.CPU_ABI);
            sb.append("%0D%0A%0D%0A");
            sb.append(ln);
            sb.append("---cpu指令集2：" + Build.CPU_ABI2);
            sb.append("%0D%0A%0D%0A");
            sb.append(ln);
            sb.append("---设置参数：" + Build.DEVICE);
            sb.append("%0D%0A%0D%0A");
            sb.append(ln);
            sb.append("---显示屏参数：" + Build.DISPLAY);
            sb.append("%0D%0A%0D%0A");
            sb.append(ln);
            sb.append("---无线电固件版本：" + Build.getRadioVersion());
            sb.append("%0D%0A%0D%0A");
            sb.append(ln);
            sb.append("---硬件识别码：" + Build.FINGERPRINT);
            sb.append("%0D%0A%0D%0A");
            sb.append(ln);
            sb.append("---硬件名称：" + Build.HARDWARE);
            sb.append("%0D%0A%0D%0A");
            sb.append(ln);
            sb.append("---HOST:" + Build.HOST);
            sb.append("%0D%0A%0D%0A");
            sb.append(ln);
            sb.append("---修订版本列表：" + Build.ID);
            sb.append("%0D%0A%0D%0A");
            sb.append(ln);
            sb.append("---硬件制造商：" + Build.MANUFACTURER);
            sb.append("%0D%0A%0D%0A");
            sb.append(ln);
            sb.append("---版本：" + Build.MODEL);
            sb.append("%0D%0A%0D%0A");
            sb.append(ln);
            sb.append("---硬件序列号：" + Build.SERIAL);
            sb.append("%0D%0A%0D%0A");
            sb.append(ln);
            sb.append("---手机制造商：" + Build.PRODUCT);
            sb.append("%0D%0A%0D%0A");
            sb.append(ln);
            sb.append("---描述Build的标签：" + Build.TAGS);
            sb.append("%0D%0A%0D%0A");
            sb.append(ln);
            sb.append("---TIME:" + Build.TIME);
            sb.append("%0D%0A%0D%0A");
            sb.append(ln);
            sb.append("---builder类型：" + Build.TYPE);
            sb.append("%0D%0A%0D%0A");
            sb.append(ln);
            sb.append("---USER:" + Build.USER);
            sb.append("%0D%0A%0D%0A");
            sb.append(ln);
            sb.append("---操作系统版本号:" + getAppInfo.getOsVersion());
            sb.append("%0D%0A%0D%0A");
            sb.append(ln);
            sb.append("---设备品牌:" + getAppInfo.getDeviceBrand());
            sb.append("%0D%0A%0D%0A");
            sb.append(ln);
            sb.append("---设备型号:" + getAppInfo.getDeviceModel());
            sb.append("%0D%0A%0D%0A");
            sb.append(ln);
            sb.append("---CPU架构:" + getAppInfo.getCpuArchitecture());
            sb.append("%0D%0A%0D%0A");
            sb.append(ln);
            return sb.toString();
        } catch (Exception e) {
            return "ERROR";
        }
    }
}
