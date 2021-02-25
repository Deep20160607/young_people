package com.young.ningx.mvp.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.google.gson.Gson;
import com.young.library.base.manager.RouteManager;
import com.young.library.base.utils.ToastUtils;
import com.young.ningx.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class TimePickerViewActivity extends Activity {

//    private TextView startTimePicker;
//    private TextView endTimePicker;
    private Context mContext;
//    private TimePickerView startTime;
//    private TimePickerView endTime;

    private ExpandableTextView mETV;

    private String json = "{\"practice\": {\n" +
            "                \"listen\": 77,\n" +
            "                \"oral\": \"12/81\",\n" +
            "                \"vocab\": 77,\n" +
            "                \"sync\": 77,\n" +
            "                \"workbook\": 77\n" +
            "            }}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_picker_view);
//        mContext = this;
//        startTimePicker = findViewById(R.id.start_time_picker);
//        endTimePicker = findViewById(R.id.end_time_picker);
//        mETV = (ExpandableTextView)this.findViewById(R.id.etv);
//        mETV.setExpandListener(new ExpandableTextView.OnExpandListener() {
//            @Override
//            public void onExpand(ExpandableTextView view) {
//                ToastUtils.showShort("onExpand");
//            }
//
//            @Override
//            public void onShrink(ExpandableTextView view) {
//                ToastUtils.showShort("onShrink");
//            }
//        });
//        initTimePicker();
//
//        startTimePicker.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//                startTime.show();
//            }
//        });
//
//        endTimePicker.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                endTime.show();
//            }
//        });

    }

    private void initTimePicker() {
//        Calendar selectedDate = Calendar.getInstance();//系统当前时间
//        Calendar startDate = Calendar.getInstance();
//        startDate.setTimeInMillis(1544432794000l);
//
//        Calendar endDate = Calendar.getInstance();
//        endDate.setTimeInMillis(1544605594000l);
//        //时间选择器
//        startTime = new TimePickerBuilder(mContext, new OnTimeSelectListener() {
//            @Override
//            public void onTimeSelect(Date date, View v) {
//                startTimePicker.setText(getTime(date));
//            }
//        }).setType(new boolean[]{true, true, true, true, true, false}).setDate(selectedDate).setRangDate(startDate, endDate).build();
//
//        //时间选择器
//        endTime = new TimePickerBuilder(mContext, new OnTimeSelectListener() {
//            @Override
//            public void onTimeSelect(Date date, View v) {
//                endTimePicker.setText(getTime(date));
//            }
//        }).build();
//
//
//        jsonbean j = new Gson().fromJson(json, jsonbean.class);

//        for (Map.Entry<String, String> entry : j.practice.entrySet()) {
//            Log.v("tag","Key = " + entry.getKey() + ", Value = " + entry.getValue());
//        }

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Looper.prepare();
//                Log.v("tag", "---1");
//                new Handler(new Handler.Callback() {
//                    @Override
//                    public boolean handleMessage(Message msg) {
//                        return false;
//                    }
//                });
//                Looper.loop();
//                Log.v("tag", "---2");
//            }
//        }).start();
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        Log.d("getTime()", "choice date millis: " + date.getTime());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(date);
    }

    @Override
    public void finish() {
        super.finish();
        Log.v("Tag", "finish");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.v("Tag", "onBackPressed");
    }

    @Override
    protected void onResume() {
        Log.v("Tag", "__-----------TimePickerViewActivity--onResume");
        super.onResume();
    }
}
