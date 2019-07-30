package com.young.library.base.manager;

import com.young.library.base.config.AppBaseConstants;
import com.young.library.base.utils.SharedPreferencesManager;

/**
 * @author dupeng
 * @version 2.6.0, 2019/2/9  8:26 AM
 * @since android 17MiddleTeacher
 */
public class LoginStateManager {

    private static LoginStateManager loginStateManager;

    public static LoginStateManager getInstance() {
        if(loginStateManager == null) {
            synchronized (LoginStateManager.class) {
                if(loginStateManager == null) {
                    loginStateManager = new LoginStateManager();
                }
            }
        }
        return loginStateManager;
    }

    /**
     * 是否登录
     *
     * @return
     */
    public boolean isLogin() {
        return SharedPreferencesManager.getBoolean(
                AppBaseConstants.SHARED_PREFERENCES_SET,
                AppBaseConstants.SHARED_PREFERENCES_LOGIN_STSTES, false);
    }

    /**
     * 设置是否登录
     *
     * @param isLogin
     */
    public void setLoginStatus(boolean isLogin) {
        SharedPreferencesManager.putBoolean(AppBaseConstants.SHARED_PREFERENCES_SET,
                AppBaseConstants.SHARED_PREFERENCES_LOGIN_STSTES, isLogin);
    }
}
