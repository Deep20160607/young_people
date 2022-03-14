package com.young.ningx.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.young.library.base.config.AppBaseConstants;
import com.young.library.base.config.AppBaseLayoutConfig;
import com.young.library.base.manager.LoginStateManager;
import com.young.library.base.manager.RouteManager;
import com.young.library.base.mvp.ui.activity.BaseActivity;
import com.young.library.base.utils.HandlerUtils;
import com.young.library.base.utils.PermissionUtils;
import com.young.library.base.utils.SharedPreferencesManager;
import com.young.library.base.utils.ToastUtils;
import com.young.library.base.utils.Utils;
import com.young.ningx.R;

/**
 * @author dupeng
 * @version 1.0.0, 2018/12/5  10:50 AM
 * @since android young
 */
@Route(path = RouteManager.WELCOME_ACTIVITY_ROUTE)
public class WelcomeActivity extends BaseActivity implements HandlerUtils.OnReceiveMessageListener {

    private HandlerUtils.HandlerHolder handlerHolder;
    private static final int PERMISSION_REQUEST_CODE = 101;
    private  int delayed_message = 1000;
    /**
     * 是否已经登录
     */
    private boolean isLogin;
    /**
     * 权限申请，是否有权限
     */
    private boolean isGrant;

    private RelativeLayout mWelcomeLayout; //闪屏
    private ImageView mWelcomeImage;//广告
    private Button mAdvNextBtn; //跳过
    private TextView mCountDownTextView;//倒计时

    private Context mContext;
    private MyCountDownTimer mCountDownTimer;

    @Override
    public void widgetClick(View v) {
        int id = v.getId();
    }

    @Override
    public void initParams(Bundle params) {
        mContext = this;
        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            finish();
            return;
        }
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    public void initView(View view) {
        mWelcomeImage = getView(R.id.teacher_welcome_image);
        mWelcomeLayout = getView(R.id.teacher_welcome_layout);
        mAdvNextBtn = getView(R.id.teacher_main_next_btn);
        mCountDownTextView = getView(R.id.start_skip_count_down);
    }

    @Override
    public void setListener() {
        mCountDownTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(TimePickerViewActivity.class);
                ARouter.getInstance().build(RouteManager.MAIN_ACTIVITY_ROUTE)
                        .navigation();
                finish();
            }
        });
    }

    @Override
    public void initData(Context mContext) {
        handlerHolder = new HandlerUtils.HandlerHolder(this);

        isLogin = LoginStateManager.getInstance().isLogin();

        String flashDataJson = SharedPreferencesManager.getString(
                AppBaseConstants.SHARED_PREFERENCES_SET,
                AppBaseConstants.SHARED_PREFERENCES_FLASH_INFO, "");

        if(Utils.isStringEmpty(flashDataJson)) {
            mWelcomeImage.setVisibility(View.VISIBLE);
            mWelcomeImage.setImageDrawable(getResources().getDrawable(
                    R.mipmap.ic_screen_default));
            delayed_message = 3000;
        }else {
            mWelcomeImage.setVisibility(View.GONE);
            delayed_message = 1000;
        }

        isGrant = PermissionUtils.checkAndApplyfPermissionActivity(this,
                new String[]{"android.permission.WRITE_EXTERNAL_STORAGE",
                        "android.permission.READ_PHONE_STATE",}, PERMISSION_REQUEST_CODE);

        if(isGrant) {
            goToNextPage();
        }
    }

    private void goToNextPage() {
        Message message = Message.obtain();
        if(isLogin) {
            message.what = AppBaseConstants.GO_MAIN;
        }else {
            message.what = AppBaseConstants.GO_GUIDE;
        }
        mCountDownTextView.setText(delayed_message/1000 + "s 跳过");
        //创建倒计时类
        mCountDownTimer = new MyCountDownTimer(delayed_message, 1000);
        mCountDownTimer.start();
        handlerHolder.sendMessageDelayed(message, delayed_message);
    }

    private void getApkMd5Value() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                AppBaseLayoutConfig.getApkMD5();
            }
        }).start();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            boolean isNoStorgePermission = false;
            for (int index = 0; index < permissions.length; index++) {
                String name = permissions[index];
                int value = grantResults[index];
                if (Utils.isStringEquals(name, "android.permission.WRITE_EXTERNAL_STORAGE")
                        && value == PackageManager.PERMISSION_DENIED) {
                    isNoStorgePermission = true;
                }
            }
            if (isNoStorgePermission && ActivityCompat.shouldShowRequestPermissionRationale(this,
                    "android.permission.WRITE_EXTERNAL_STORAGE")) {
                ToastUtils.showShort("请给年轻人客户端存储权限，否在会造成app无法正常使用！");
            }
            goToNextPage();
        }
    }

    @Override
    public void handlerMessage(Message msg) {
        switch (msg.what) {
            case AppBaseConstants.GO_GUIDE:
                ARouter.getInstance().build(RouteManager.GUIDE_ACTIVITY_ROUTE)
                        .navigation();
                finish();
                break;

                case AppBaseConstants.GO_MAIN:
                    ARouter.getInstance().build(RouteManager.MAIN_ACTIVITY_ROUTE)
                            .navigation();
                    finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
        }
        if(handlerHolder != null) {
            handlerHolder.removeCallbacksAndMessages(null);//清除掉所有的message 防止引起内存泄漏
        }
    }

    class MyCountDownTimer extends CountDownTimer {
        /**
         * @param millisInFuture
         *      表示以「 毫秒 」为单位倒计时的总数
         *      例如 millisInFuture = 1000 表示1秒
         *
         * @param countDownInterval
         *      表示 间隔 多少微秒 调用一次 onTick()
         *      例如: countDownInterval = 1000 ; 表示每 1000 毫秒调用一次 onTick()
         *
         */

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }


        public void onFinish() {
            mCountDownTextView.setText("0s 跳过");
        }

        public void onTick(long millisUntilFinished) {
            mCountDownTextView.setText( millisUntilFinished / 1000 + "s 跳过");
        }

    }
}
