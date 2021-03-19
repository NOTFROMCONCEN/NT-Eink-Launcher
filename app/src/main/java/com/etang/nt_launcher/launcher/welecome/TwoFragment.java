package com.etang.nt_launcher.launcher.welecome;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.etang.nt_launcher.R;

/**
 * @Package: com.etang.nt_launcher.launcher.welecome
 * @ClassName: TwoFragment
 * @Description: “欢迎界面”第二个碎片
 * @CreateDate: 2021/3/19 8:17
 * @UpdateDate: 2021/3/19 8:17
 */
public class TwoFragment extends Fragment {
    //当前页面TAG
    private static String TAG = "TwoFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_welecome_2, null, false);
        return view;
    }
}
