package com.young.library.base.mvp.presenter;

import com.young.library.base.mvp.view.IView;

/**
 * @author dupeng
 * @version 2.9.0, 2019/5/18  2:52 PM
 * @since android 17MiddleTeacher
 */
public interface IPresenter<V extends IView> {

    /**
     * 绑定 View
     */
    void attachView(V mView);

    /**
     * 解除 View
     */
    void detachView();
}
