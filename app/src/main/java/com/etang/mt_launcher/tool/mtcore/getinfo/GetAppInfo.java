package com.etang.mt_launcher.tool.mtcore.getinfo;

import android.os.Build;

public class GetAppInfo {
    // 操作系统版本号
    private String osVersion;
    // 设备型号
    private String deviceModel;
    // 设备品牌
    private String deviceBrand;
    // CPU架构
    private String cpuArchitecture;

    // 构造函数，在创建对象时获取相关信息
    public GetAppInfo() {
        osVersion = Build.VERSION.RELEASE; // 获取操作系统版本号
        deviceModel = Build.MODEL; // 获取设备型号
        deviceBrand = Build.BRAND; // 获取设备品牌
        cpuArchitecture = Build.CPU_ABI; // 获取CPU架构
    }

    // 获取操作系统版本号
    public String getOsVersion() {
        return osVersion;
    }

    // 获取设备型号
    public String getDeviceModel() {
        return deviceModel;
    }

    // 获取设备品牌
    public String getDeviceBrand() {
        return deviceBrand;
    }

    // 获取CPU架构
    public String getCpuArchitecture() {
        return cpuArchitecture;
    }
}
