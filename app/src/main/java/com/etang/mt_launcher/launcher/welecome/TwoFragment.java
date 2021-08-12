package com.etang.mt_launcher.launcher.welecome;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.etang.mt_launcher.R;
import com.etang.mt_launcher.tool.dialog.PayMeDialog;

/**
 * @Package: com.etang.nt_launcher.launcher.welecome
 * @ClassName: TwoFragment
 * @Description: “欢迎界面”第二个碎片
 * @CreateDate: 2021/3/19 8:17
 * @UpdateDate: 2021/5/29 21:52
 */
public class TwoFragment extends Fragment {
    //当前页面TAG
    private static String TAG = "TwoFragment";
    //支持开发按钮
    private Button btn_welecome_show_paycode, btn_welecome_show_shuomingshu;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_welecome_2, null, false);
        initView(view);
        btn_welecome_show_paycode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PayMeDialog.show_dialog(getActivity());
            }
        });
        btn_welecome_show_shuomingshu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return view;
    }

    private void initView(View view) {
        btn_welecome_show_paycode = (Button) view.findViewById(R.id.btn_welecome_show_paycode);
        btn_welecome_show_shuomingshu = (Button) view.findViewById(R.id.btn_welecome_show_shuomingshu);
    }
}
