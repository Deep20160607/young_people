package com.young.library.base.config;

import android.content.Context;
import android.content.pm.ApplicationInfo;

import com.young.library.base.BaseApplication;
import com.young.library.base.utils.Utils;

import java.util.Map;
/**
 * layout id
 *
 * @author dupeng
 * @version 1.0.0, 2019/5/17  6:04 PM
 * @since android young
 */
public class AppBaseLayoutConfig {
    private static int mNormalLayout;
    private static int mForceLayout;
    private static int mProgressLayout;
    private static int mErrorDilaogLayout;
    private static int mNotifyIcon;
    private static boolean mIsLogin = false;
    private static Class<?> mMainActivityClass = null;
    private static String mRequestUrl;
    private static String mProductId;
    private static String mProductName;
    private static Map<String, String> mUserInfoParams;
    private static String mChineseAppName;
    private static String mApkMD5 = "";
    private static int mLoadIconResId = -1;

    public AppBaseLayoutConfig() {
    }

    public static void initAppLayout(int normalDiloag, int forceDiloag, int progressDiloag, int errorDialoglayout, int notifyIcon) {
        mNormalLayout = normalDiloag;
        mForceLayout = forceDiloag;
        mProgressLayout = progressDiloag;
        mErrorDilaogLayout = errorDialoglayout;
        mNotifyIcon = notifyIcon;
    }

    public static void initAppParams(String requestUrl, String productId, String productName, Map<String, String> params, Class<?> mainActivity, String appName) {
        mChineseAppName = appName;
        mRequestUrl = requestUrl;
        mProductId = productId;
        mProductName = productName;
        mMainActivityClass = mainActivity;
        mUserInfoParams = params;
    }

    public static void initMainActivity(Class<?> mainActivity) {
        mMainActivityClass = mainActivity;
    }

    public static void initUserParams(Map<String, String> params, boolean isLogin) {
        mUserInfoParams = params;
        setAppIsLogin(isLogin);
    }

    public static void setLoadIconResId(int mLoadIconResId) {
        mLoadIconResId = mLoadIconResId;
    }

    public static int getLoadIconResId() {
        return mLoadIconResId;
    }

    public static String getChineseAppName() {
        return mChineseAppName;
    }

    public static void setAppIsLogin(boolean isLogin) {
        mIsLogin = isLogin;
    }

    public static boolean isLogin() {
        return mIsLogin;
    }

    public static String getRequestUrl() {
        return mRequestUrl;
    }

    public static String getProductId() {
        return mProductId;
    }

    public static String getProductName() {
        return mProductName;
    }

    public static String getApkMD5() {
        try {
            if (Utils.isStringEmpty(mApkMD5)) {
                Context cxt = BaseApplication.getContext();
                ApplicationInfo appInfo = cxt.getPackageManager().getApplicationInfo(cxt.getPackageName(), 0);
                mApkMD5 = Utils.MD5File(appInfo.sourceDir);
            }
        } catch (Exception var2) {
            ;
        }

        return mApkMD5;
    }

    public static Map<String, String> getUserInfoParams() {
        return mUserInfoParams;
    }

    public static int getNormalLayout() {
        return mNormalLayout;
    }

    public static int getForceLayout() {
        return mForceLayout;
    }

    public static int getProgressLayout() {
        return mProgressLayout;
    }

    public static int getErrorDilaogLayout() {
        return mErrorDilaogLayout;
    }

    public static int getNotifyIcon() {
        return mNotifyIcon;
    }

    public static Class<?> getMainActivityClass() {
        return mMainActivityClass;
    }
}

