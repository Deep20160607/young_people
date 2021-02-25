package com.young.ningx;

import android.content.Context;

import androidx.multidex.MultiDex;

import com.umeng.commonsdk.UMConfigure;
import com.young.library.base.BaseApplication;

/**
 *
 *
 * @author dupeng
 * @version 1.0.0, 2018/12/5  10:50 AM
 * @since android young
 */
public class LauncherApp extends BaseApplication {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);

    }

    @Override
    public void onCreate() {
        super.onCreate();
        //Bugly
        initBugly();
    }



    private void initBugly() {
        /**
         * 注意: 即使您已经在AndroidManifest.xml中配置过appkey和channel值，也需要在App代码中调
         * 用初始化接口（如需要使用AndroidManifest.xml中配置好的appkey和channel值，
         * UMConfigure.init调用中appkey和channel参数请置为null）。
         */
        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, null);
        // 获取当前包名
        String packageName = getApplicationContext().getPackageName();
        // 获取当前进程名
//        String processName = CommonUtils.getProcessName(android.os.Process.myPid());
//        // 设置是否为上报进程
//        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(getApplicationContext());
//        strategy.setUploadProcess(processName == null || processName.equals(packageName));
//        CrashReport.initCrashReport(getApplicationContext(), MyConstants.BUGLY_ID, false, strategy);
    }
}
