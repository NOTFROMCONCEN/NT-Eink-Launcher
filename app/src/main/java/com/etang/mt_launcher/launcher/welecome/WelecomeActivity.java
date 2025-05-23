package com.etang.mt_launcher.launcher.welecome;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.etang.mt_launcher.R;
import com.etang.mt_launcher.launcher.MainActivity;
import com.etang.mt_launcher.tool.mtcore.MTCore;
import com.etang.mt_launcher.tool.mtcore.dialog.NewUserDialog;
import com.etang.mt_launcher.tool.mtcore.permission.SavePermission;
import com.etang.mt_launcher.tool.mtcore.toast.DiyToast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

/**
 * @Package: com.etang.nt_launcher.launcher.welecome
 * @ClassName: WelecomeActivity
 * @Description: “欢迎界面”FragmentActivity
 * @CreateDate: 2021/3/19 8:17
 * @UpdateDate: 2021/5/29 21:52
 */
public class WelecomeActivity extends FragmentActivity {

    ViewPager viewPager;
    PagerAdapter pagerAdapter;
    List<Fragment> list_fragment = new ArrayList<Fragment>();
    FloatingActionButton fab_welecome_pass;
    String state = "";
    private TextView tv_button, tv_title;
    private int pagersize = 0;

    //当前页面TAG
    private static String TAG = "WelecomeActivity";

    private class PagerAdapter extends FragmentPagerAdapter {


        public PagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return list_fragment.get(position);
        }

        @Override
        public int getCount() {
            return list_fragment.size();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);// 无Title
        setContentView(R.layout.activity_welecome);
        initView();
        try {
            Intent intent = getIntent();
            state = intent.getStringExtra("state");
            if (!state.equals("false")) {
                show_dialog();
            }
        } catch (Exception e) {
            state = "true";
            show_dialog();
        }
        fab_welecome_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (pagersize) {
                    case 0:
                        viewPager.setCurrentItem(1);
                        break;
                    case 1:
                        viewPager.setCurrentItem(2);
                        break;
                    case 2:
                        if (viewPager.getCurrentItem() == 2 && !state.equals("false")) {
                            dialog_newuser(true);
                            MTCore.showToast(view.getContext(), "初始化成功ヾ(≧▽≦*)o", true);
                        } else if (viewPager.getCurrentItem() == 2 && state.equals("false")) {
                            dialog_newuser(false);
                            MTCore.showToast(view.getContext(), "初始化成功ヾ(≧▽≦*)o", true);
                        }
                        break;
                }
            }
        });
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                switch (position) {
                    case 0:
                        pagersize = 0;
                        tv_title.setText("选择语言");
                        tv_button.setText("请选择你的语言");
                        tv_title.setVisibility(View.INVISIBLE);
                        tv_button.setVisibility(View.INVISIBLE);
                        break;
                    case 1:
                        pagersize = 1;
                        tv_title.setText("使用向导");
                        tv_button.setText("欢迎使用");
                        tv_title.setVisibility(View.VISIBLE);
                        tv_button.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        pagersize = 2;
                        tv_title.setText("说明书");
                        tv_button.setText("请扫码查阅说明书");
                        tv_title.setVisibility(View.VISIBLE);
                        tv_button.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        tv_title.setText("选择语言");
                        tv_button.setText("请选择你的语言");
                        tv_title.setVisibility(View.INVISIBLE);
                        tv_button.setVisibility(View.INVISIBLE);
                        break;
                    case 1:
                        tv_title.setText("使用向导");
                        tv_button.setText("欢迎使用");
                        tv_title.setVisibility(View.VISIBLE);
                        tv_button.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        tv_title.setText("说明书");
                        tv_button.setText("请扫码查阅说明书");
                        tv_title.setVisibility(View.VISIBLE);
                        tv_button.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        tv_title.setText("使用向导");
        tv_button.setText("欢迎使用");
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void show_dialog() {
        new AlertDialog.Builder(WelecomeActivity.this)
                .setTitle("权限申请：")
                .setMessage("梅糖桌面想要使用“本地存储权限”" + "\n" + "权限说明：用于存放缓存以及“更新功能”和“更换图标功能”")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MTCore.check_save_permission(WelecomeActivity.this);
                    }
                }).setNegativeButton("也是确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MTCore.check_save_permission(WelecomeActivity.this);
                    }
                }).setNeutralButton("还是确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MTCore.check_save_permission(WelecomeActivity.this);
                    }
                }).setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        MTCore.showToast(WelecomeActivity.this, "请给予权限", true);
                        MTCore.check_save_permission(WelecomeActivity.this);
                    }
                }).show();
    }

    private void initView() {
        tv_button = (TextView) findViewById(R.id.tv_title_button);
        tv_title = (TextView) findViewById(R.id.tv_title_text);
        fab_welecome_pass = findViewById(R.id.fab_welecome_pass);
        viewPager = (ViewPager) findViewById(R.id.pager_welecome);
        list_fragment.add(new ChoseFragment());
        list_fragment.add(new OneFragment());
        list_fragment.add(new TwoFragment());
        pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
    }

    private void dialog_newuser(final boolean show_or_notshow) {
        if (show_or_notshow) {
            final AlertDialog alertDialog = new AlertDialog.Builder(WelecomeActivity.this).create();
            View view = LayoutInflater.from(WelecomeActivity.this).inflate(R.layout.dialog_newuserchecker, null, false);
            alertDialog.setView(view);
            alertDialog.setCanceledOnTouchOutside(false);
            Button btn_con = (Button) view.findViewById(R.id.btn_newusercheck_con);
            Button btn_cls = (Button) view.findViewById(R.id.btn_newusercheck_cls);
            btn_cls.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                    startActivity(new Intent(WelecomeActivity.this, MainActivity.class));
                    finish();
                }
            });
            btn_con.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NewUserDialog.dialog_show(WelecomeActivity.this, "设备激活（新用户）：", true);
                    alertDialog.dismiss();
                    startActivity(new Intent(WelecomeActivity.this, MainActivity.class));
                    finish();
                }
            });
            alertDialog.show();
        } else {
            startActivity(new Intent(WelecomeActivity.this, MainActivity.class));
            finish();
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }


}