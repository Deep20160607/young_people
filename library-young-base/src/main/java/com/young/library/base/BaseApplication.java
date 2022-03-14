package com.young.library.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import com.alibaba.android.arouter.launcher.ARouter;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.young.library.base.manager.AppManager;
import com.young.library.net.RxRestfulClient;
import com.young.library.net.config.OkHttpConfig;
import com.young.library.net.cookie.store.SPCookieStore;

import java.util.HashMap;
import java.util.Map;

import cn.jpush.android.api.JPushInterface;
import okhttp3.OkHttpClient;

public class BaseApplication extends Application {
    private RefWatcher mRefWatcher;
    private static Context mContext;
    private OkHttpClient okHttpClient = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        initRouter(this);
        initJpush();
        initOkHttpClient();
        initRxRestfulClient();

        //检测内存泄漏
        initLeakCanary();

        //注册监听每个activity的生命周期,便于堆栈式管理
        registerActivityLifecycleCallbacks(mCallbacks);
    }

    private void initJpush() {
        JPushInterface.setDebugMode(BuildConfig.DEBUG);
        JPushInterface.init(mContext);
        //停止推送服务
        //public static void stopPush(Context context);

        //恢复推送服务
        //public static void resumePush(Context context);

        //用来检查 Push Service 是否已经被停止
        //public static boolean isPushStopped(Context context);
    }

    public static Context getContext() {
        return mContext;
    }

    /**
     * 初始化ARouter
     *
     * @param mApplication
     */
    private void initRouter(BaseApplication mApplication) {
        if (BuildConfig.DEBUG) {
            //打印日志，默认关闭
            ARouter.openLog();
            //开启调试模式，默认关闭（如果在InstantRun模式下运行，必须开启调试模式！！！线上版本需要关闭，否则有安全风险）
            ARouter.openDebug();
            //打印日志的时候打印线程的堆栈信息，方便排错。
            ARouter.printStackTrace();
        }
        //尽可能早的初始化，推荐在Application中初始化
        ARouter.init(mApplication);
    }

    /**
     * 初始化OkHttpClient
     */
    private void initOkHttpClient() {
        //全局请求头信息
        Map<String, Object> headerMaps = new HashMap<>();

        okHttpClient = new OkHttpConfig
                .Builder(this)
                //设置全局请求头信息
                .setHeaders(headerMaps)
                //.setAddInterceptor(new CommonParamsInterceptor(),new TokenOverdueInterceptor())
                //开启缓存策略（默认为false）
                //1,在有网的时候，先去读缓存，缓存时间到了，再去访问网络数据；
                //2,在没有网络的时候，去读缓存数据。
                .setCache(true)
                //
                .setCookieType(new SPCookieStore(this))
                .setReadTimeout(10)
                .setWriteTimeout(10)
                .setConnectTimeout(10)
                .setDebug(true)
                .build();
    }

    /**
     * 初始化配置RxRestfulClient
     */
    private void initRxRestfulClient() {

        RxRestfulClient
                .getInstance()
                .init(this)
                .config()
                //配置全局baseUrl
                //.setBaseUrl(ParentsBaseConfig.RELEASE_HOST_PLATFORM)
                //开启全局配置
                .setOkClient(okHttpClient);
    }

    private void initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        mRefWatcher = LeakCanary.install(this);
    }


    public static RefWatcher getRefWatcher(Context context) {
        BaseApplication application = (BaseApplication) context.getApplicationContext();
        return application.mRefWatcher;
    }


    private ActivityLifecycleCallbacks mCallbacks = new ActivityLifecycleCallbacks() {

        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            AppManager.getInstance().addActivity(activity);
        }

        @Override
        public void onActivityStarted(Activity activity) {
        }

        @Override
        public void onActivityResumed(Activity activity) {
        }

        @Override
        public void onActivityPaused(Activity activity) {
        }

        @Override
        public void onActivityStopped(Activity activity) {
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            AppManager.getInstance().removeActivity(activity);
        }
    };
}
