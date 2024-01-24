package com.etang.mt_launcher.tool.mtcore.weatherupdate;


public class UpdateWeather {

    private void drop_code_by_time_updateweather() {
//        MTCore.i_for_weather++;
//        if (MTCore.i_for_weather > 5) {
//            MTCore.i_for_weather = 0;
//            Log.e("更新时间", "----------");
//            if (!offline_mode) {
//                if (tv_time_min.getText().toString().equals("00") || tv_time_min.getText().toString().equals("30")) {
////                            line_wather.setVisibility(View.VISIBLE);
//                    Cursor cursor = db.rawQuery("select * from wather_city", null);
//                    if (cursor.getCount() != 0) {
//                        cursor.moveToFirst();
//                        @SuppressLint("Range") String city = cursor.getString(cursor.getColumnIndex("city"));
//                        update_wather(MainActivity.this, city);
//                    }
//                    SharedPreferences sharedPreferences;
//                    sharedPreferences = getSharedPreferences("info", MODE_PRIVATE);
//                    update_wathers(sharedPreferences);
//                }
//            } else {
////                        line_wather.setVisibility(View.INVISIBLE);
//            }
//        }
    }


    private void drop_code_by_when_click_ui_update_weather() {
//        //天气
//        case R.id.line_wather:
//        Cursor cursor = db.rawQuery("select * from wather_city", null);
//        if (!offline_mode) {
//            if (cursor.getCount() != 0) {
//                cursor.moveToFirst();
//                @SuppressLint("Range") String city = cursor.getString(cursor.getColumnIndex("city"));
//                update_wather(MainActivity.this, city);
//                MTCore.showToast(MainActivity.this, "正在尝试更新：" + city, false);
//                /**
//                 * 更新天气信息
//                 */
//                SharedPreferences sharedPreferences;
//                sharedPreferences = getSharedPreferences("info", MODE_PRIVATE);
//                update_wathers(sharedPreferences);
//            }
//        } else {
//            MTCore.showToast(getApplicationContext(), "当前处于离线模式", true);
//        }
//        break;
    }

    private void drop_code_by_when_onResume_update_weather() {
//        /**
//         * 更新天气信息
//         */
//        if (!offline_mode) {
//            if (tv_time_min.getText().toString().equals("00") || tv_time_min.getText().toString().equals("30")) {
////                line_wather.setVisibility(View.VISIBLE);
//                Cursor cursor = db.rawQuery("select * from wather_city", null);
//                if (cursor.getCount() != 0) {
//                    cursor.moveToFirst();
//                    @SuppressLint("Range") String city = cursor.getString(cursor.getColumnIndex("city"));
//                    update_wather(MainActivity.this, city);
//                }
//                SharedPreferences sharedPreferences;
//                sharedPreferences = getSharedPreferences("info", MODE_PRIVATE);
//                update_wathers(sharedPreferences);
//            }
//        } else {
////            line_wather.setVisibility(View.INVISIBLE);
//        }
    }

    private void drop_code_by_handle_update_weather() {
//        public void update_wather(Context context, final String city) {
//            Log.i(TAG, "update_wather: start");
//            if (TextUtils.isEmpty(city)) {
//                MTCore.showToast(context, "城市错误，不在数据库中", true);
//                return;
//            }
//            Log.i(TAG, "update_wather: 开始获取城市天气信息");
//            new Thread() {
//                @Override
//                public void run() {
//                    // TODO Auto-generated method stub
//                    super.run();
//                    try {
//                        URL url = new URL("http://wthrcdn.etouch.cn/weather_mini?city=" + URLEncoder.encode(city, "UTF-8"));
//                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//                        conn.setConnectTimeout(5000);
//                        conn.setRequestMethod("GET");
//                        int code = conn.getResponseCode();
//                        if (code == 200) {
//                            Log.i(TAG, "run: 网络连接成功");
//                            // 连接网络成功
//                            InputStream in = conn.getInputStream();
//                            String data = StreamTool.decodeStream(in);
//                            // 解析json格式的数据
//                            JSONObject jsonObj = new JSONObject(data);
//                            // 获得desc的值
//                            String result = jsonObj.getString("desc");
//                            if ("OK".equals(result)) {
//                                // 城市有效，返回了需要的数据
//                                JSONObject dataObj = jsonObj.getJSONObject("data");
//                                JSONArray jsonArray = dataObj.getJSONArray("forecast");
//                                // 通知更新ui
//                                Message msg = Message.obtain();
//                                msg.obj = jsonArray;
//                                msg.what = 0;
//                                mHandler.sendMessage(msg);
//                            } else {
//                                // 城市无效
//                                Message msg = Message.obtain();
//                                msg.what = 1;
//                                mHandler.sendMessage(msg);
//                            }
//                        } else {
//                            // 联网失败
//                            Message msg = Message.obtain();
//                            msg.what = 2;
//                            mHandler.sendMessage(msg);
//                        }
//                    } catch (Exception e) {
//                        // TODO: handle exception
//                        e.printStackTrace();
//                        Message msg = Message.obtain();
//                        msg.what = 2;
//                        mHandler.sendMessage(msg);
//                    }
//                }
//
//                ;
//            }.start();
//        }
    }


}
