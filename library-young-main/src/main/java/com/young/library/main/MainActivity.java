package com.young.library.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.young.library.base.manager.RouteManager;
import com.young.library.base.mvp.ui.activity.BaseActivity;
import com.young.library.main.follow.ui.fragment.MainFollowFragment;
import com.young.library.main.home.ui.fragment.MainHomeFragment;
import com.young.library.main.news.ui.fragment.MainNewsFragment;
import com.young.library.main.personal.ui.fragment.MainPersonalFragment;
import com.young.library.main.scan.ui.fragment.MainScanFragment;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author dupeng
 * @version 1.0.0, 2019/5/17  8:42 PM
 * @since android young
 */
@Route(path = RouteManager.MAIN_ACTIVITY_ROUTE)
public class MainActivity extends BaseActivity {


    private LinearLayout bar_home;
    private LinearLayout bar_follow;
    private LinearLayout bar_news;
    private LinearLayout bar_me;
    private ImageView bar_scan;

    private Fragment mCurrentFragment;
    private SparseArray<Fragment> mFragmentTab = new SparseArray<Fragment>();
    private int mLastSelectFragmentTag = 0;
    private View mCurrentSelectedTabView = null;

    @Override
    public void widgetClick(View view) {
        if (view != null) {
            this.switchTab(view);
            this.mLastSelectFragmentTag = view.getId();
            this.switchFragment(this.mFragmentTab.get(this.mLastSelectFragmentTag));
        }
    }

    @Override
    public void initParams(Bundle params) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(View view) {
        bar_home = getView(R.id.index_bottom_bar_home);
        bar_follow = getView(R.id.index_bottom_bar_follow);
        bar_news = getView(R.id.index_bottom_bar_news);
        bar_me = getView(R.id.index_bottom_bar_me);
        bar_scan = getView(R.id.index_bottom_bar_scan);
    }

    @Override
    public void setListener() {
        bar_home.setOnClickListener(this);
        bar_follow.setOnClickListener(this);
        bar_news.setOnClickListener(this);
        bar_me.setOnClickListener(this);
        bar_scan.setOnClickListener(this);
    }

    @Override
    public void initData(Context mContext) {
        // 初始化要打开的fragment
        this.mLastSelectFragmentTag = this.initLastSelectFragmentTag(this.getIntent());
        // 初始化fragment
        this.initFragmentAndStatus();

    }

    private int initLastSelectFragmentTag(Intent intent) {
        int lastTag = 0;
        //todo

        return lastTag;
    }

    private void initFragmentAndStatus() {

        if (this.mLastSelectFragmentTag == 0) {
            this.mLastSelectFragmentTag = R.id.index_bottom_bar_home;
        }

        this.mFragmentTab.put(
                R.id.index_bottom_bar_home,
                this.getSupportFragmentManager().findFragmentByTag(
                        R.id.index_bottom_bar_home + ""));
        this.mFragmentTab.put(
                R.id.index_bottom_bar_follow,
                this.getSupportFragmentManager().findFragmentByTag(
                        R.id.index_bottom_bar_follow + ""));
        this.mFragmentTab.put(
                R.id.index_bottom_bar_scan,
                this.getSupportFragmentManager().findFragmentByTag(
                        R.id.index_bottom_bar_scan + ""));
        this.mFragmentTab.put(
                R.id.index_bottom_bar_news,
                this.getSupportFragmentManager().findFragmentByTag(
                        R.id.index_bottom_bar_news + ""));
        this.mFragmentTab.put(
                R.id.index_bottom_bar_me,
                this.getSupportFragmentManager().findFragmentByTag(
                        R.id.index_bottom_bar_me + ""));

        for (int i = 0; i < this.mFragmentTab.size(); i++) {
            if (this.mFragmentTab.valueAt(i) == null) {
                int tmpValue = this.mFragmentTab.keyAt(i);
                if (tmpValue == R.id.index_bottom_bar_home) {
                    this.mFragmentTab.setValueAt(i, new MainHomeFragment());
                } else if (tmpValue == R.id.index_bottom_bar_follow) {
                    this.mFragmentTab.setValueAt(i, new MainFollowFragment());
                } else if (tmpValue == R.id.index_bottom_bar_scan) {
                    this.mFragmentTab.setValueAt(i, new MainScanFragment());
                } else if (tmpValue == R.id.index_bottom_bar_news) {
                    this.mFragmentTab.setValueAt(i, new MainNewsFragment());
                } else if (tmpValue == R.id.index_bottom_bar_me) {
                    this.mFragmentTab.setValueAt(i, new MainPersonalFragment());
                }
            }
        }

        this.mCurrentFragment = this.mFragmentTab
                .get(this.mLastSelectFragmentTag);

        this.getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_activity_fragment_container,
                        this.mCurrentFragment,
                        this.mLastSelectFragmentTag + "")
                .commitAllowingStateLoss();

        // 初始化tab状态
        this.switchTab(this.findViewById(this.mLastSelectFragmentTag));

    }

    private void switchTab(View currentView) {
        if (currentView != null) {
            if (this.mCurrentSelectedTabView != null) {
                if (this.mCurrentSelectedTabView != currentView) {
                    this.mCurrentSelectedTabView.setSelected(false);
                } else {
                    // 刷新
                    return;
                }
            }
            currentView.setSelected(true);
            this.mCurrentSelectedTabView = currentView;
        }
    }

    private void switchFragment(Fragment fragment) {
        if (fragment != null) {
            FragmentTransaction fragmentTransaction = this
                    .getSupportFragmentManager().beginTransaction();
            boolean isNeedRefresh = false;
            if (this.mCurrentFragment != fragment) {
                if (!fragment.isAdded()) {
                    fragmentTransaction
                            .hide(this.mCurrentFragment)
                            .add(R.id.main_activity_fragment_container,
                                    fragment, this.mLastSelectFragmentTag + "")
                            .show(fragment).commitAllowingStateLoss();
                    // isNeedRefresh = true;
                } else {
                    fragmentTransaction.hide(this.mCurrentFragment)
                            .show(fragment).commitAllowingStateLoss();
                }
                this.mCurrentFragment = fragment;
            } else {
                isNeedRefresh = true;
            }
        }
    }

}
