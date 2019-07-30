package com.young.library.base.mvp.view;

/**
 * @author dupeng
 * @version 2.9.0, 2019/5/18  2:53 PM
 * @since android 17MiddleTeacher
 */
public interface IView {

    void showLoading();

    void hideLoading();

    void onError(String eroMsg);
}
