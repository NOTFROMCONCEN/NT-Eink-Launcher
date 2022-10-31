package com.etang.mt_launcher.launcher.settings.weather;

import android.app.Activity;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.etang.mt_launcher.R;
import com.etang.mt_launcher.tool.mtcore.MTCore;
import com.etang.mt_launcher.tool.sql.MyDataBaseHelper;
import com.etang.mt_launcher.tool.mtcore.toast.DiyToast;

/**
 * @Package: com.etang.nt_launcher.launcher.settings.weather
 * @ClassName: WeatherActivity
 * @Description: “天气”设置
 * @CreateDate: 2021/3/19 8:16
 * @UpdateDate: 2021/5/29 21:52
 */
public class WeatherActivity extends Activity {
    //按钮，确定，关闭
    private Button btn_wather_con, btn_wather_cls;
    //输入城市
    private EditText et_city_get;
    //数据库
    private MyDataBaseHelper dbHelper;
    private SQLiteDatabase db;
    //SharedPreferences存储
    SharedPreferences sharedPreferences;
    //文本，返回，按钮，标题
    private TextView tv_button, tv_title;
    //返回LinearLayout
    private LinearLayout lv_back;
    //单选框
    private RadioButton ra_weather_view_vis, ra_weather_view_gone;
    //当前页面TAG
    private static String TAG = "WeatherActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);// 无Title
        setContentView(R.layout.activity_weather);
        //绑定控件
        initView();
        //检查sharedPreferences存储并配置单选按钮状态
        check_radiobutton();
        //确定按钮
        btn_wather_con.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //如果文本框为空
                if (et_city_get.getText().toString().isEmpty()) {
                    //提示
                    MTCore.showToast(getApplicationContext(), "请输入城市", true);
                } else {
                    //向数据库内插入输入的城市
                    db.execSQL("update wather_city set city = ? ",
                            new String[]{et_city_get.getText().toString()});
                    //结束当前界面
                    finish();
                }
            }
        });
        //关闭按钮
        btn_wather_cls.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //显示天气文本内容单选按钮
        ra_weather_view_vis.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getSharedPreferences("info", MODE_PRIVATE).edit();
                editor.putBoolean("isHind_weather", false);
                editor.apply();
                //重新读取设置单选按钮状态
                check_radiobutton();
            }
        });
        //隐藏天气文本内容单选按钮
        ra_weather_view_gone.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getSharedPreferences("info", MODE_PRIVATE).edit();
                editor.putBoolean("isHind_weather", true);
                editor.apply();
                //重新读取设置单选按钮状态
                check_radiobutton();
            }
        });
        tv_button.setVisibility(View.INVISIBLE);
        tv_title.setText("天气设置");
        lv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * 从sharedPreferences中读取配置信息，然后设置在单选按钮上
     */
    private void check_radiobutton() {
        if (sharedPreferences.getBoolean("isHind_weather", false) == true) {
            ra_weather_view_gone.setChecked(true);
        } else {
            ra_weather_view_vis.setChecked(true);
        }
    }

    /**
     * 绑定控件
     */
    private void initView() {
        // TODO Auto-generated method stub
        //数据库
        dbHelper = new MyDataBaseHelper(getApplicationContext(), "info.db",
                null, 2);
        db = dbHelper.getWritableDatabase();
        //设置sharedPreferences存储
        sharedPreferences = getSharedPreferences("info", MODE_PRIVATE);
        ra_weather_view_gone = (RadioButton) findViewById(R.id.ra_weather_view_gone);
        ra_weather_view_vis = (RadioButton) findViewById(R.id.ra_weather_view_vis);
        lv_back = (LinearLayout) findViewById(R.id.lv_back);
        tv_button = (TextView) findViewById(R.id.tv_title_button);
        tv_title = (TextView) findViewById(R.id.tv_title_text);
        tv_title = (TextView) findViewById(R.id.tv_title_text);
        btn_wather_cls = (Button) findViewById(R.id.btn_wather_cls);
        btn_wather_con = (Button) findViewById(R.id.btn_wather_con);
        et_city_get = (EditText) findViewById(R.id.et_city_get);
    }

    /**
     * 设置界面被关闭时没有动画
     */
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }

}
