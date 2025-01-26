package com.etang.mt_launcher.tool.getapps;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
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

import com.bumptech.glide.Glide;
import com.etang.mt_launcher.R;
import com.etang.mt_launcher.tool.beans.Bean_AppInfo;
import com.etang.mt_launcher.tool.mtcore.savearrayutil.SaveArrayImageUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DeskTopGridViewBaseAdapter extends BaseAdapter {
    private static final String TAG = "DeskTopGridViewBaseAdapter";
    private Context context;
    private List<Bean_AppInfo> beanAppInfos = new ArrayList<>();
    private SharedPreferences sharedPreferences;
    private SharedPreferences appListStatePrefs;
    private ArrayList<String> searchArrayList;

    public DeskTopGridViewBaseAdapter(List<Bean_AppInfo> beanAppInfos, Context context) {
        this.beanAppInfos = beanAppInfos;
        this.context = context;
        this.sharedPreferences = context.getSharedPreferences("info", context.MODE_PRIVATE);
        this.appListStatePrefs = context.getSharedPreferences("info_app_list_state", context.MODE_PRIVATE);
        this.searchArrayList = SaveArrayImageUtil.getSearchArrayList(context);
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
            convertView = LayoutInflater.from(context).inflate(R.layout.list_gridview_item, null);
            holder = new Holder();
            holder.ico = convertView.findViewById(R.id.iv_gridview_app_icon);
            holder.Name = convertView.findViewById(R.id.tv_gridview_app_name);
            holder.line_appinfo = convertView.findViewById(R.id.line_appinfo);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        Bean_AppInfo appInfo = beanAppInfos.get(position);
        holder.ico.setImageBitmap(appInfo.getIco());
        getIconSize(holder);
        holder.Name.setText(appInfo.getName());

        for (int i = 1; i < searchArrayList.size(); i++) {
            String[] all = searchArrayList.get(i).split("-");
            if (appInfo.getPackageName().equals(all[0])) {
                Glide.with(context).load(new File(Environment.getExternalStorageDirectory(), "ntlauncher/" + all[1])).into(holder.ico);
                break;
            }
        }

        getAppNameInfo(holder);

        return convertView;
    }

    private void getIconSize(Holder holder) {
        int size = sharedPreferences.getInt("icon_size", 45);
        ViewGroup.LayoutParams params = holder.ico.getLayoutParams();
        params.height = size;
        params.width = size;
        holder.ico.setLayoutParams(params);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void getAppNameInfo(Holder holder) {
        String appnameState = appListStatePrefs.getString("appname_state", "one");
        String appblokState = appListStatePrefs.getString("appblok_state", "show_nocolor_blok_yuan");

        switch (appnameState) {
            case "one":
                holder.Name.setSingleLine(true);
                holder.Name.setVisibility(View.VISIBLE);
                break;
            case "nope":
                holder.Name.setSingleLine(false);
                holder.Name.setVisibility(View.VISIBLE);
                break;
            case "hind":
                holder.Name.setVisibility(View.GONE);
                break;
            case "null":
                holder.Name.setSingleLine(true);
                holder.Name.setVisibility(View.VISIBLE);
                break;
        }

        switch (appblokState) {
            case "null":
            case "hind_blok":
                holder.line_appinfo.setBackground(null);
                break;
            case "show_blok_line":
                holder.line_appinfo.setBackgroundResource(R.drawable.shaper_zhijiao_lins_show);
                break;
            case "show_blok_yuan":
                holder.line_appinfo.setBackgroundResource(R.drawable.shaper_yuanjiao_lins_show);
                break;
            case "show_nocolor_blok_line":
                holder.line_appinfo.setBackgroundResource(R.drawable.shaper_zhijiao_lins_nocolor);
                break;
            case "show_nocolor_blok_yuan":
                holder.line_appinfo.setBackgroundResource(R.drawable.shaper_yuanjiao_nocolor_show);
                break;
            case "show_blok":
                holder.line_appinfo.setBackgroundResource(R.drawable.shaper_yuanjiao_lins_show);
                break;
            case "show_nocolor_blok":
                holder.line_appinfo.setBackgroundResource(R.drawable.shaper_yuanjiao_nocolor_show);
                break;
        }
    }

    private static class Holder {
        ImageView ico;
        TextView Name;
        LinearLayout line_appinfo;
    }
}
