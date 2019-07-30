package com.young.ningx.mvp.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.young.library.base.mvp.ui.activity.BaseActivity;
import com.young.ningx.R;
import com.young.ningx.mvp.chart.ScrollBar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author dupeng
 * @version 2.6.0, 2019/3/25  4:24 PM
 * @since android 17MiddleTeacher
 */
public class ScrollBarActivity extends BaseActivity {

    private List<Float> verticalList;
    private List<String> horizontalList;
    private ScrollBar barChart;
    private Random random;

    private int count =  100;

    private Button more;
    private Button small;

    @Override
    public void widgetClick(View v) {
        switch (v.getId()) {
            case R.id.more:
                horizontalList.clear();
                verticalList.clear();
                for (int i = 0; i < count; i++) {
                    horizontalList.add("" + i);
                }

                while (verticalList.size() < count) {
                    int randomInt = random.nextInt(1000);
                    verticalList.add((float) randomInt);
                }

                barChart.setHorizontalList(horizontalList);
                barChart.setVerticalList(verticalList);

                break;
            case R.id.small:

                horizontalList.clear();
                verticalList.clear();
                for (int i = 0; i < 6; i++) {
                    horizontalList.add("" + i);
                }

                while (verticalList.size() < 6) {
                    int randomInt = random.nextInt(1000);
                    verticalList.add((float) randomInt);
                }

                barChart.setHorizontalList(horizontalList);
                barChart.setVerticalList(verticalList);
                break;
        }
    }

    @Override
    public void initParams(Bundle params) {
        setSteepStatusBar(true);
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_scroll;
    }

    @Override
    public void initView(View view) {
        more = getView(view, R.id.more);
        small = getView(view, R.id.small);
        barChart = getView(view, R.id.barchart);
    }

    @Override
    public void setListener() {
        more.setOnClickListener(this);
        small.setOnClickListener(this);
        verticalList = new ArrayList<>();
        horizontalList = new ArrayList<>();

    }

    @Override
    public void initData(Context mContext) {
        for (int i = 0; i < 6; i++) {
            horizontalList.add("" + i);
        }

        random = new Random();
        while (verticalList.size() < 6) {
            int randomInt = random.nextInt(1000);
            verticalList.add((float) randomInt);
        }

        barChart.setHorizontalList(horizontalList);
        barChart.setVerticalList(verticalList);
    }
}
