package com.etang.mt_launcher.launcher.welecome;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.etang.mt_launcher.R;
import com.etang.mt_launcher.tool.mtcore.MTCore;
import com.etang.mt_launcher.tool.mtcore.dialog.ShowImgDialog;

import java.util.Locale;

/**
 * @Package: com.etang.nt_launcher.launcher.welecome
 * @ClassName: ChoseFragment
 * @Description: “欢迎界面”选择语言碎片
 * @CreateDate: 2021/3/19 8:16
 * @UpdateDate: 2021/5/29 21:52
 */
public class ChoseFragment extends Fragment {
    //SharedPreferences本地存储
    private SharedPreferences sharedPreferences;
    //单选按钮、自动语言、中文语言、英文语言、日语语言
    private RadioButton ra_welecome_auto, ra_welecome_cn, ra_welecome_en, ra_welecome_jp;
    //当前页面TAG
    private static String TAG = "ChoseFragment";
    //单选按钮组
    private RadioGroup rg_welecome_choselan;
    //展示按钮
    private Button btn_welecome_showchose, btn_welecome_for_kindle, btn_welecome_for_moan;
    int i = 0;


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_welecome_chose, null, false);
        initView(view);//绑定控件
        CheckLanguage();//检查语言
        //已停用代码
//        if (sharedPreferences.getString("language", null).equals("0")) {
//            ra_welecome_auto.setChecked(true);
//        }
//        if (sharedPreferences.getString("language", null).equals("1")) {
//            ra_welecome_cn.setChecked(true);
//        }
//        if (sharedPreferences.getString("language", null).equals("2")) {
//            ra_welecome_en.setChecked(true);
//        }
//        if (sharedPreferences.getString("language", null).equals("3")) {
//            ra_welecome_jp.setChecked(true);
//        }
        //显示多语言选项
        btn_welecome_showchose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (i == 0) {
                    rg_welecome_choselan.setVisibility(View.VISIBLE);
                    i = 1;
                } else {
                    rg_welecome_choselan.setVisibility(View.GONE);
                    i = 0;
                }
            }
        });
        //给kindle用户的一封信
        btn_welecome_for_kindle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MTCore.showToast(getActivity(),"请使用其他设备扫码查看，或点击“打开链接”",true);
                ShowImgDialog.show_dialog(getActivity(),"kindle");
            }
        });
        //给墨案用户的一封信
        btn_welecome_for_moan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MTCore.showToast(getActivity(),"请使用其他设备扫码查看，或点击“打开链接”",true);
                ShowImgDialog.show_dialog(getActivity(),"moan");
            }
        });
        //自动选择语言
        ra_welecome_auto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences.edit().putString("language", "0").commit();
                check_Language(getActivity(), sharedPreferences);
                Intent intent = new Intent(getActivity(), getActivity().getClass());
                intent.putExtra("state", "false");
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                getActivity().overridePendingTransition(0, 0);
                getActivity().finish();
            }
        });
        //选择中文
        ra_welecome_cn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences.edit().putString("language", "1").commit();
                check_Language(getActivity(), sharedPreferences);
                Intent intent = new Intent(getActivity(), getActivity().getClass());
                intent.putExtra("state", "false");
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                getActivity().overridePendingTransition(0, 0);
                getActivity().finish();
            }
        });
        //选择英文
        ra_welecome_en.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences.edit().putString("language", "2").commit();
                check_Language(getActivity(), sharedPreferences);
                Intent intent = new Intent(getActivity(), getActivity().getClass());
                intent.putExtra("state", "false");
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                getActivity().overridePendingTransition(0, 0);
                getActivity().finish();
            }
        });
        //选择日语
        ra_welecome_jp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences.edit().putString("language", "3").commit();
                check_Language(getActivity(), sharedPreferences);
                Intent intent = new Intent(getActivity(), getActivity().getClass());
                intent.putExtra("state", "false");
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                getActivity().overridePendingTransition(0, 0);
                getActivity().finish();
            }
        });
        return view;
    }

    /**
     * 检查已存储语言
     */
    private void CheckLanguage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                switch (sharedPreferences.getString("language", null)) {
                    case "0":
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ra_welecome_auto.setChecked(true);
                            }
                        });
                        break;
                    case "1":
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ra_welecome_cn.setChecked(true);
                            }
                        });
                        break;
                    case "2":
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ra_welecome_en.setChecked(true);
                            }
                        });
                        break;
                    case "3":
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ra_welecome_jp.setChecked(true);
                            }
                        });
                        break;
                }
            }
        }).start();
    }

    /**
     * 检查系统语言
     *
     * @param context
     * @param sharedPreferences
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void check_Language(Context context, SharedPreferences sharedPreferences) {
        int language = 0;
        try {
            //读取SharedPreferences数据，默认选中第一项
            language = Integer.valueOf(sharedPreferences.getString("language", null));
        } catch (Exception e) {
            sharedPreferences.edit().putString("language", "0");
            check_Language(context, sharedPreferences);
        }
        //根据读取到的数据，进行设置
        Resources resources = getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        Configuration configuration = resources.getConfiguration();
        switch (language) {
            case 0:
                //自动获取
                configuration.setLocale(Locale.getDefault());
                break;
            case 1:
                //中文
                configuration.setLocale(Locale.CHINESE);
                break;
            case 2:
                //英文
                configuration.setLocale(Locale.ENGLISH);
                break;
            case 3:
                //日文
                configuration.setLocale(Locale.JAPANESE);
                break;
            default:
                break;
        }
        resources.updateConfiguration(configuration, displayMetrics);
    }

    /**
     * 绑定控件
     *
     * @param view
     */
    private void initView(View view) {
        sharedPreferences = getActivity().getSharedPreferences("info", Context.MODE_PRIVATE);
        ra_welecome_auto = (RadioButton) view.findViewById(R.id.ra_welecome_auto);
        ra_welecome_cn = (RadioButton) view.findViewById(R.id.ra_welecome_china);
        ra_welecome_en = (RadioButton) view.findViewById(R.id.ra_welecome_english);
        ra_welecome_jp = (RadioButton) view.findViewById(R.id.ra_welecome_japanese);
        rg_welecome_choselan = (RadioGroup) view.findViewById(R.id.rg_welecome_choselan);
        btn_welecome_showchose = (Button) view.findViewById(R.id.btn_welecome_showchose);
        btn_welecome_for_kindle = (Button) view.findViewById(R.id.btn_welecome_for_kindle);
        btn_welecome_for_moan = (Button) view.findViewById(R.id.btn_welecome_for_moan);
    }
}
