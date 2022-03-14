package com.young.library.base.config;

/**
 * 静态变量
 *
 * @author dupeng
 * @version 1.0.0, 2019/2/9  8:50 AM
 * @since android young
 */
public class AppBaseConstants {

    //---base----------------------------------------//
    public static final String SHARED_PREFERENCES_SET = "shared_preferences_set";
    public static final String SHARED_PREFERENCES_LOGIN_STSTES = "login_states";


    //--业务----------------------------------------//

    /** 去登陆注册页*/
    public static final int GO_GUIDE = 0x10000001;
    /** 去首页*/
    public static final int GO_MAIN = 0x10000002;

    /**
     * 保存splash页面图片
     */
    public static final String SHARED_PREFERENCES_FLASH_INFO = "flash_link_info";


    public static final int START_IMG_ANIM = 1;//启动图片动画
    public static final int START_TEXT_ANIM = 2;//启动文字动画
    public static final int START_REGIST_ANIM = 3;//启动注册动画
    public static final int START_LOGIN_ANIM = 4;//启动登录动画
    public static final int SET_SERVER = 5;//设置服务器

}
