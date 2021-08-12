package com.etang.mt_launcher.tool.locker;

import android.app.admin.DevicePolicyManager;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.etang.mt_launcher.launcher.MainActivity;

/**
 * @ProjectName: NT-Eink-Launcher
 * @Package: com.etang.nt_launcher.tool
 * @ClassName: LockScreen
 * @Description: java类作用描述
 * @Author: 作者名：奶油话梅糖
 * @CreateDate: 2021/5/31 0031 13:16
 * @UpdateUser: 更新者：奶油话梅糖
 * @UpdateDate: 2021/5/31 0031 13:16
 * @UpdateRemark: 更新说明：
 */
//一键锁屏
public class LockScreen extends AppCompatActivity {

    private DevicePolicyManager devicePolicyManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        devicePolicyManager = (DevicePolicyManager) getSystemService(DEVICE_POLICY_SERVICE);
        //若未注册设备管理器，跳转到配置界面
        try {
            devicePolicyManager.lockNow();
            finish();
        } catch (Exception e) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}