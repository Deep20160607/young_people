package com.young.library.main.follow.ui.fragment;

import com.young.library.base.mvp.presenter.BasePresenter;
import com.young.library.base.mvp.ui.fragment.BaseFragment;
import com.young.library.main.R;

/**
 * @author dupeng
 * @version 2.9.0, 2019/5/17  11:58 PM
 * @since android 17MiddleTeacher
 */
public class MainFollowFragment extends BaseFragment{
    @Override
    protected int bindLayout() {
        return R.layout.fragment_follow;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected boolean useEventBus() {
        return false;
    }

    @Override
    public void onError(String eroMsg) {

    }
}
