package com.young.library.base.mvp.ui.activity;

import android.view.View;

import androidx.annotation.NonNull;

import com.young.library.base.mvp.presenter.BasePresenter;
import com.young.library.base.mvp.view.IView;

/**
 * @author dupeng
 * @version 1.0.0, 2019/2/1  3:26 PM
 * @since android young
 */
public abstract class BaseMvpActivity <P extends BasePresenter>
        extends BaseActivity implements IView {

    protected P mPresenter;

    @NonNull
    protected abstract P createPresenter();

    @Override
    public void initView(View view) {
        mPresenter = createPresenter();
        mPresenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    @Override
    public void showLoading() {
        showLoadingDialog();
    }

    @Override
    public void hideLoading() {
        hideLoadingDialog();
    }

    @Override
    public void onError(String eroMsg) {

    }
}
