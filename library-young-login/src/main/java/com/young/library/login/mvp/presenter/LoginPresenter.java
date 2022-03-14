package com.young.library.login.mvp.presenter;

import com.young.library.base.mvp.presenter.BasePresenter;
import com.young.library.login.mvp.contract.LoginContract;
import com.young.library.login.mvp.model.LoginModel;

/**
 * @author dupeng
 * @version 2.9.0, 2019/5/18  2:19 PM
 * @since android 17MiddleTeacher
 */
public class LoginPresenter extends BasePresenter<LoginContract.Model, LoginContract.View>{


    @Override
    protected LoginContract.Model createModel() {
        return new LoginModel();
    }
}
