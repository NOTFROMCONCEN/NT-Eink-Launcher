package com.etang.mt_launcher.tool.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.etang.mt_launcher.R;

import java.util.ArrayList;
import java.util.List;

public class ListAdapterHelper_AppUseLog extends BaseAdapter {


    public Context context;
    public List<Bean_AppUseLog> list = new ArrayList<Bean_AppUseLog>();

    public ListAdapterHelper_AppUseLog(Context convertView) {
        // TODO Auto-generated constructor stub
        this.context = convertView;
    }

    public void delData(int postion) {
        // TODO Auto-generated method stub
        MyDataBaseHelper dbHelper = new MyDataBaseHelper(context, "info.db",
                null, 2);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Bean_AppUseLog bean = list.get(postion);
        db.execSQL("delete from appuselogs where appname = ?",
                new String[]{bean.getName()});
    }

    public void setData(List<Bean_AppUseLog> list) {
        // TODO Auto-generated method stub
        this.list = list;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list != null ? list.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class ViewHolder {
        TextView tv_name, tv_time;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View view = LayoutInflater.from(context).inflate(
                R.layout.activity_app_use_logs_list, null, false);
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.tv_time = (TextView) view.findViewById(R.id.tv_pass);
        viewHolder.tv_name = (TextView) view.findViewById(R.id.tv_user);
        Bean_AppUseLog bean = list.get(position);
        viewHolder.tv_time.setText(bean.getTime());
        viewHolder.tv_name.setText(bean.getName());
        // view.setTag(viewHolder);
        // viewHolder = (viewHolder) view.getTag();
        return view;
    }
}
