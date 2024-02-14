package com.etang.mt_launcher.tool.mtcore.getinfo;

import android.os.Build;

public class GetAppInfo {
    private String osVersion;
    private String deviceModel;
    private String deviceBrand;
    private String cpuArchitecture;

    public void AndroidInfo() {
        // 获取操作系统版本号
        osVersion = Build.VERSION.RELEASE;
        // 获取设备型号
        deviceModel = Build.MODEL;
        // 获取设备品牌
        deviceBrand = Build.BRAND;
        // 获取CPU架构
        cpuArchitecture = Build.CPU_ABI;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public String getDeviceBrand() {
        return deviceBrand;
    }

    public String getCpuArchitecture() {
        return cpuArchitecture;
    }
}
