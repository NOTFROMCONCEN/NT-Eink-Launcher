package com.etang.mt_launcher.tool.getapps;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.etang.mt_launcher.R;
import com.etang.mt_launcher.tool.beans.Bean_AppInfo;
import com.etang.mt_launcher.tool.mtcore.savearrayutil.SaveArrayImageUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @Package: com.etang.nt_launcher.tool.util
 * @ClassName: DeskTopGridViewBaseAdapter
 * @Description: java类作用描述
 * @CreateDate: 2021/3/19 8:27
 * @UpdateDate: 2021/3/19 8:27
 */
public class DeskTopGridViewBaseAdapter extends BaseAdapter {
    Context context;
    List<Bean_AppInfo> beanAppInfos = new ArrayList<Bean_AppInfo>();
    //当前页面TAG
    private static String TAG = "DeskTopGridViewBaseAdapter";

    public DeskTopGridViewBaseAdapter(List<Bean_AppInfo> beanAppInfos, Context context) {
        this.beanAppInfos = beanAppInfos;
        this.context = context;
    }

    @Override
    public int getCount() {
        return beanAppInfos.size();
    }

    @Override
    public Object getItem(int position) {
        return beanAppInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.list_gridview_item, null);
            holder = new Holder();
            holder.ico = (ImageView) convertView.findViewById(R.id.iv_gridview_app_icon);
            holder.Name = (TextView) convertView.findViewById(R.id.tv_gridview_app_name);
            holder.line_appinfo = (LinearLayout) convertView.findViewById(R.id.line_appinfo);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.ico.setImageBitmap(beanAppInfos.get(position).getIco());
        get_iconsize(context, holder);
        holder.Name.setText(beanAppInfos.get(position).getName());
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList = SaveArrayImageUtil.getSearchArrayList(context);
        for (int i = 1; i < arrayList.size(); i++) {
            String str = arrayList.get(i);
            String[] all = str.split("-");
            if (beanAppInfos.get(position).getPackageName().equals(all[0])) {
                holder.ico.setImageURI(Uri.fromFile(new File("sdcard/ntlauncher/" + all[1])));
            }
        }
        get_appname_info(holder);//读取APP名称和边框大小设置
        return convertView;
    }


    private void get_iconsize(Context context, Holder holder) {
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences("info", context.MODE_PRIVATE);
            int size = Integer.valueOf(sharedPreferences.getString("icon_size", null));
            ViewGroup.LayoutParams params = holder.ico.getLayoutParams();
            params.height = size;
            params.width = size;
            holder.ico.setLayoutParams(params);
        } catch (Exception e) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("info", context.MODE_PRIVATE);
            sharedPreferences.edit().putString("icon_size", "45").commit();
            get_iconsize(context, holder);
        }
    }

    /**
     * 读取APP名称和边框大小设置
     *
     * @param holder
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void get_appname_info(Holder holder) {
        SharedPreferences sharedPreferences;
        sharedPreferences = context.getSharedPreferences("info_app_list_state", context.MODE_PRIVATE);
        String appname_state = sharedPreferences.getString("appname_state", null);
        String appblok_state = sharedPreferences.getString("appblok_state", null);
        try {
            if (sharedPreferences != null) {
                if (appname_state.equals("one")) {
                    holder.Name.setSingleLine(true);
                    holder.Name.setVisibility(View.VISIBLE);
                }
                if (appname_state.equals("nope")) {
                    holder.Name.setSingleLine(false);
                    holder.Name.setVisibility(View.VISIBLE);
                }
                if (appname_state.equals("hind")) {
                    holder.Name.setVisibility(View.GONE);
                }
                if (appname_state.equals("null")) {
                    holder.Name.setSingleLine(true);
                    holder.Name.setVisibility(View.VISIBLE);
                }


                if (appblok_state.equals("null")) {
                    holder.line_appinfo.setBackground(null);
                }
                if (appblok_state.equals("hind_blok")) {
                    holder.line_appinfo.setBackground(null);
                }
                if (appblok_state.equals("show_blok_line")) {
                    holder.line_appinfo.setBackgroundResource(R.drawable.shaper_zhijiao_lins_show);
                }
                if (appblok_state.equals("show_blok_yuan")) {
                    holder.line_appinfo.setBackgroundResource(R.drawable.shaper_yuanjiao_lins_show);
                }
                if (appblok_state.equals("show_nocolor_blok_line")) {
                    holder.line_appinfo.setBackgroundResource(R.drawable.shaper_zhijiao_lins_nocolor);
                }
                if (appblok_state.equals("show_nocolor_blok_yuan")) {
                    holder.line_appinfo.setBackgroundResource(R.drawable.shaper_yuanjiao_nocolor_show);
                }
                if (appblok_state.equals("show_blok")) {
                    holder.line_appinfo.setBackgroundResource(R.drawable.shaper_yuanjiao_lins_show);
                }
                if (appblok_state.equals("show_nocolor_blok")) {
                    holder.line_appinfo.setBackgroundResource(R.drawable.shaper_yuanjiao_nocolor_show);
                }
            }
        } catch (Exception e) {
            SharedPreferences.Editor editor = context.getSharedPreferences("info_app_list_state", context.MODE_PRIVATE).edit();
            editor.putString("appname_state", "one");
            editor.putString("appblok_state", "show_nocolor_blok_yuan");
            editor.apply();
            get_appname_info(holder);
        }
    }

    private static class Holder {
        ImageView ico;
        TextView Name;
        LinearLayout line_appinfo;
    }
}
