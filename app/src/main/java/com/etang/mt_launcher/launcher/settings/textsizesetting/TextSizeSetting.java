package com.etang.mt_launcher.launcher.settings.textsizesetting;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.etang.mt_launcher.R;
import com.etang.mt_launcher.launcher.MainActivity;
import com.etang.mt_launcher.tool.mtcore.MTCore;

/**
 * @Package: com.etang.nt_launcher.launcher.settings.textsizesetting
 * @ClassName: TextSizeSetting
 * @Description: “文本大小”设置
 * @CreateDate: 2021/3/19 8:15
 * @UpdateDate: 2021/5/29 21:52
 */
public class TextSizeSetting extends AppCompatActivity {
    //按钮，小时、分钟、日期、签名、电池、重设文本大小
    private Button btn_timetext_hour_size, btn_timetext_min_size, btn_datetext_size, btn_nametext_size, btn_dianchitext_size, btn_textsize_resert;
    //SharedPreferences本地存储，用于存储设置信息
    private SharedPreferences sharedPreferences;
    //文本，返回，按钮，标题
    private TextView tv_button, tv_title;
    //返回LinearLayout
    private LinearLayout lv_back;
    //当前页面TAG
    private static String TAG = "TextSizeSetting";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);// 无Title
        setContentView(R.layout.setting_textsize);
        //绑定控件
        initView();
        tv_title.setText("文本设置");
        lv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_timetext_min_size.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_size_dialog("timetext_min_size", sharedPreferences.getString("timetext_hour_size", null));
            }
        });
        btn_timetext_hour_size.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_size_dialog("timetext_hour_size", sharedPreferences.getString("timetext_hour_size", null));
            }
        });
        btn_nametext_size.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_size_dialog("nametext_size", sharedPreferences.getString("nametext_size", null));
            }
        });
        btn_dianchitext_size.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_size_dialog("dianchitext_size", sharedPreferences.getString("dianchitext_size", null));
            }
        });
        btn_datetext_size.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_size_dialog("datetext_size", sharedPreferences.getString("datetext_size", null));
            }
        });
        tv_button.setText("重置文本大小");
        tv_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getSharedPreferences("info", MODE_PRIVATE).edit();
                /**
                 * 设定文本大小预填充
                 */
                editor.putString("timetext_min_size", "50");
                editor.putString("timetext_hour_size", "70");
                editor.putString("nametext_size", "17");//昵称文本大小
                editor.putString("dianchitext_size", "17");//电池文本大小
                editor.putString("datetext_size", "17");//日期文本大小
                editor.apply();
                MTCore.showToast(TextSizeSetting.this, "重置成功", true);
            }
        });
        btn_textsize_resert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getSharedPreferences("info", MODE_PRIVATE).edit();
                /**
                 * 设定文本大小预填充
                 */
                editor.putString("timetext_min_size", "50");
                editor.putString("timetext_hour_size", "70");
                editor.putString("nametext_size", "17");//昵称文本大小
                editor.putString("dianchitext_size", "17");//电池文本大小
                editor.putString("datetext_size", "17");//日期文本大小
                editor.apply();
                MTCore.showToast(TextSizeSetting.this, "重置成功", true);
            }
        });
    }

    /**
     * 展示文本大小设置对话框
     *
     * @param name     当前选择的东西
     * @param textSize 文本大小
     */
    private void show_size_dialog(final String name, String textSize) {
        //新建dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(TextSizeSetting.this);
        //从资源文件导入view
        View view = LayoutInflater.from(TextSizeSetting.this).inflate(R.layout.dialog_seekbar_and_edittext, null);
        //判断当前name是什么，即选择的什么，用于dialog对话框的title标题
        switch (name) {
            case "timetext_min_size":
                //时间文本大小（分钟）
                builder.setTitle("时间文本大小设置（分钟\n当前值：" + textSize);
                break;
            case "timetext_hour_size":
                //时间文本大小（小时）
                builder.setTitle("时间文本大小设置（小时\n当前值：" + textSize);
                break;
            case "nametext_size":
                //昵称文本大小
                builder.setTitle("昵称文本大小设置\n当前值：" + textSize);
                break;
            case "dianchitext_size":
                //电池文本大小
                builder.setTitle("电池文本大小设置\n当前值：" + textSize);
                break;
            case "datetext_size":
                //日期文本大小
                builder.setTitle("日期文本大小设置\n当前值：" + textSize);
                break;
        }
        //给dialog设置自定义view界面
        builder.setView(view);
        final int[] number = {0};
        final SeekBar dialog_seekbar = (SeekBar) view.findViewById(R.id.dialog_seekbar);
        final TextView dialog_textview = (TextView) view.findViewById(R.id.dialog_textview);
        dialog_seekbar.setProgress(Integer.valueOf(textSize));
        dialog_seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                dialog_textview.setText(String.valueOf(seekBar.getProgress()));
                number[0] = seekBar.getProgress();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferences.Editor editor = getSharedPreferences("info", MODE_PRIVATE).edit();
                editor.putString(name, String.valueOf(number[0]));
                editor.apply();
                MainActivity.check_text_size(TextSizeSetting.this);
            }
        });
        builder.setNeutralButton("关闭", null);
        builder.show();
    }

    /**
     * 控件绑定
     */
    private void initView() {
        // TODO Auto-generated method stub
        //获取sharedPreferences本地存储
        sharedPreferences = getSharedPreferences("info", MODE_PRIVATE);
        lv_back = (LinearLayout) findViewById(R.id.lv_back);
        btn_textsize_resert = (Button) findViewById(R.id.btn_textsize_resert);
        btn_timetext_min_size = (Button) findViewById(R.id.btn_timetext_min_size);
        btn_timetext_hour_size = (Button) findViewById(R.id.btn_timetext_hour_size);
        btn_dianchitext_size = (Button) findViewById(R.id.btn_dianchitext_size);
        btn_nametext_size = (Button) findViewById(R.id.btn_nametext_size);
        btn_datetext_size = (Button) findViewById(R.id.btn_datetext_size);
        tv_button = (TextView) findViewById(R.id.tv_title_button);
        tv_title = (TextView) findViewById(R.id.tv_title_text);
    }

    /**
     * 结束当前界面时关闭所有动画
     */
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }

}