package com.young.library.login.mvp.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.young.library.base.config.AppBaseConstants;
import com.young.library.base.manager.RouteManager;
import com.young.library.base.mvp.ui.activity.BaseActivity;
import com.young.library.base.utils.DisplayUtils;
import com.young.library.base.utils.HandlerUtils;
import com.young.library.login.R;

/**
 * @author dupeng
 * @version 2.9.0, 2019/5/18  11:48 AM
 * @since android 17MiddleTeacher
 */
@Route(path = RouteManager.GUIDE_ACTIVITY_ROUTE)
public class GuideActivity extends BaseActivity implements HandlerUtils.OnReceiveMessageListener {

    private HandlerUtils.HandlerHolder handlerHolder;
    private TextView mRegisterBtn;
    private TextView mLoginBtn;
    private TextView mServer;
    private ImageView mHeadImg;//顶部icon图片
    private TextView mAppName;//应用名称文字

    //Animation
    private AnimationSet mHeadImgAnimation;//图标动画
    private AlphaAnimation mHeadTextAnimation;//文字动画
    private AnimationSet mBtnAnimation;//按钮动画
    private AnimationSet mBtnAnimationLogin;//登录按钮动画
    private AnimationSet mBtnAnimationTemporary;

    @Override
    public void widgetClick(View v) {
        int id = v.getId();
        if (id == R.id.teacher_guide_btn_login) {
            ARouter.getInstance().build(RouteManager.LOGIN_ACTIVITY_ROUTE)
                    .navigation();

        }
    }

    @Override
    public void initParams(Bundle params) {

    }

    @Override
    public View bindView() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_guide;
    }

    @Override
    public void initView(View view) {
        mRegisterBtn = getView(R.id.teacher_guide_btn_register);
        mLoginBtn = getView(R.id.teacher_guide_btn_login);

        mHeadImg = getView(R.id.teacher_guide_image_head);
        mAppName = getView(R.id.teacher_guide_image_app_name);

    }

    @Override
    public void setListener() {
        mRegisterBtn.setOnClickListener(this);
        mLoginBtn.setOnClickListener(this);
    }

    @Override
    public void initData(Context mContext) {
        handlerHolder = new HandlerUtils.HandlerHolder(this);
        //初始化动画
        initAnimation();
        //启动动画
        startAnimation();
    }

    /**
     * 初始化各个动画
     */
    private void initAnimation() {
        int screenHeight = DisplayUtils.getScreenHeight(this);

        //做HeadImage的动画
        mHeadImgAnimation = new AnimationSet(true);
        mHeadImgAnimation.setInterpolator(new LinearInterpolator());//使用同一个线性差值器

        //一个是位移
        int deltaY = screenHeight / 2 - DisplayUtils.dip2px(this, 80) - 300 - DisplayUtils.dip2px(this, 20);
        mHeadImgAnimation.addAnimation(new TranslateAnimation(0, 0, deltaY, 0));
        //一个是缩放
        mHeadImgAnimation.addAnimation(new ScaleAnimation(1.0f, 0.7f, 1.0f, 0.7f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f));
        mHeadImgAnimation.setFillAfter(true);
        mHeadImgAnimation.setDuration(400);
        mHeadImgAnimation.setRepeatCount(1);

        //做Title动画
        mHeadTextAnimation = new AlphaAnimation(0f, 1f);
        mHeadTextAnimation.setDuration(100);
        mHeadTextAnimation.setRepeatMode(1);
        mHeadTextAnimation.setFillAfter(true);
        mHeadTextAnimation.setInterpolator(new LinearInterpolator());

        //做两个按钮的动画
        mBtnAnimation = new AnimationSet(true);
        mBtnAnimation.setInterpolator(new LinearInterpolator());

        mBtnAnimationLogin = new AnimationSet(true);
        mBtnAnimationLogin.setInterpolator(new LinearInterpolator());

        mBtnAnimationTemporary = new AnimationSet(true);
        mBtnAnimationTemporary.setInterpolator(new LinearInterpolator());

        //先上移动
        mBtnAnimation.addAnimation(new TranslateAnimation(0, 0, 80, -5));

        //同时透明度变化
        mBtnAnimation.addAnimation(new AlphaAnimation(0f, 1f));
        mBtnAnimation.setRepeatMode(1);
        mBtnAnimation.setFillAfter(false);
        mBtnAnimation.setDuration(500);

        //先上移动
        mBtnAnimationLogin.addAnimation(new TranslateAnimation(0, 0, 80, -5));

        //同时透明度变化
        mBtnAnimationLogin.addAnimation(new AlphaAnimation(0f, 1f));
        mBtnAnimationLogin.setRepeatMode(1);
        mBtnAnimationLogin.setFillAfter(false);
        mBtnAnimationLogin.setDuration(500);

        //先上移动
        mBtnAnimationTemporary.addAnimation(new TranslateAnimation(0, 0, 80, -5));

        //同时透明度变化
        mBtnAnimationTemporary.addAnimation(new AlphaAnimation(0f, 1f));
        mBtnAnimationTemporary.setRepeatMode(1);
        mBtnAnimationTemporary.setFillAfter(false);
        mBtnAnimationTemporary.setDuration(500);
    }

    /**
     * 开始做动画
     */
    public void startAnimation() {
        //启动HEAD动画
        handlerHolder.sendEmptyMessage(AppBaseConstants.START_IMG_ANIM);
        //启动Text动画
        handlerHolder.sendEmptyMessageDelayed(AppBaseConstants.START_TEXT_ANIM, 300);
        //启动登录按钮动画
        handlerHolder.sendEmptyMessageDelayed(AppBaseConstants.START_LOGIN_ANIM, 400);
        //启动注册按钮动画
        handlerHolder.sendEmptyMessageDelayed(AppBaseConstants.START_REGIST_ANIM, 600);
        //设置是否展示切换环境开关
        handlerHolder.sendEmptyMessageDelayed(AppBaseConstants.SET_SERVER, 1000);

    }

    @Override
    public void handlerMessage(Message msg) {
        switch (msg.what) {
            case AppBaseConstants.START_IMG_ANIM: {
                mHeadImg.setVisibility(View.VISIBLE);
                mHeadImg.startAnimation(mHeadImgAnimation);
                break;
            }
            case AppBaseConstants.START_TEXT_ANIM: {
                mAppName.setVisibility(View.GONE);
                mAppName.startAnimation(mHeadTextAnimation);
                break;
            }
            case AppBaseConstants.START_REGIST_ANIM: {
                mRegisterBtn.setVisibility(View.VISIBLE);
                mRegisterBtn.startAnimation(mBtnAnimation);
                break;
            }
            case AppBaseConstants.START_LOGIN_ANIM: {
                mLoginBtn.setVisibility(View.VISIBLE);
                mLoginBtn.startAnimation(mBtnAnimationLogin);
                break;
            }
            case AppBaseConstants.SET_SERVER: {
                //setServerConfig();
                break;
            }
            default: {
                break;
            }
        }
    }
}
