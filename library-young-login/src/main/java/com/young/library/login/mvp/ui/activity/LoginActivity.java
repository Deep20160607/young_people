package com.young.library.login.mvp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.young.library.base.manager.RouteManager;
import com.young.library.base.mvp.presenter.BasePresenter;
import com.young.library.base.mvp.ui.activity.BaseMvpActivity;
import com.young.library.base.utils.Utils;
import com.young.library.base.view.ClearEditText;
import com.young.library.base.view.CommonHeaderView;
import com.young.library.login.R;
import com.young.library.login.mvp.presenter.LoginPresenter;

/**
 * @author dupeng
 * @version 2.9.0, 2019/5/18  2:18 PM
 * @since android 17MiddleTeacher
 */
@Route(path = RouteManager.LOGIN_ACTIVITY_ROUTE)
public class LoginActivity extends BaseMvpActivity implements CommonHeaderView.OnClickBackListener {

    private TextView mTvHeadTitle;

    private ClearEditText mPsdTypePhoneInput;
    private RelativeLayout mRlPsdTypePasswordInputLayout;
    private ClearEditText mPsdTypePassWordInput;

    private RelativeLayout mRlVerificationCodeTypePhoneLayout;
    private ClearEditText mVerificationCodeTypePhone;


    private TextView mTvForgetPassWord;
    private TextView mTvVerificationCodeLogin;
    private TextView mTvRegister;

    private TextView mTvNextPage;
    private boolean isVerificationCodeLogin = false;

    @NonNull
    @Override
    protected BasePresenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    public void widgetClick(View v) {
        int id = v.getId();

        if (id == R.id.tv_teacher_login_psd_type_forget) {//forger psd

//            Intent intentForget = new Intent(this, ResetPassWordActivity.class);
//            startActivity(intentForget);

        } else if (id == R.id.teacher_login_btn_login) {
//            if(isVerificationCodeLogin) {
//                showLoading();
//                mPresenter.loadVerificationCodeLogin(mVerificationCodeTypePhone.getTextWithoutSpace());
//            }else {
//                showLoading();
//                mPresenter.loadPsdLogin(mPsdTypePhoneInput.getText().toString(), mPsdTypePassWordInput.getText().toString());
//            }


        } else if (id == R.id.teacher_login_btn_register) {
//            Intent intentForget = new Intent(this, NewUserRegisterActivity.class);
//            startActivity(intentForget);

        } else if (id == R.id.teacher_vertify_code_login_btn) {
            switchLoginType();

        } else if (id == R.id.teacher_login_image_head) {
        }
    }

    /**
     *切换登录类型
     */
    private void switchLoginType() {
        isVerificationCodeLogin = !isVerificationCodeLogin;
        if(isVerificationCodeLogin) {
            mTvHeadTitle.setText("验证码登录");
            mTvVerificationCodeLogin.setText("用密码登录");
            mTvNextPage.setText("获取验证码");
            mPsdTypePhoneInput.setVisibility(View.GONE);
            mRlPsdTypePasswordInputLayout.setVisibility(View.GONE);
            mRlVerificationCodeTypePhoneLayout.setVisibility(View.VISIBLE);
            if(Utils.isStringEmpty(mVerificationCodeTypePhone.getText().toString()) || mVerificationCodeTypePhone.getText().toString().length() != 13) {
                mTvNextPage.setEnabled(false);
            }else {
                mTvNextPage.setEnabled(true);
            }
        }else {
            mTvHeadTitle.setText("密码登录");
            mTvVerificationCodeLogin.setText("用验证码登录");
            mTvNextPage.setText("登录");
            mPsdTypePhoneInput.setVisibility(View.VISIBLE);
            mRlPsdTypePasswordInputLayout.setVisibility(View.VISIBLE);
            mRlVerificationCodeTypePhoneLayout.setVisibility(View.GONE);
            if(!Utils.isStringEmpty(mPsdTypePhoneInput.getText().toString()) &&
                    !Utils.isStringEmpty(mPsdTypePassWordInput.getText().toString())) {
                mTvNextPage.setEnabled(true);
            }else {
                mTvNextPage.setEnabled(false);
            }
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
        return R.layout.activity_login;
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        CommonHeaderView commonHeaderView = getView(R.id.teacher_login_guide);
        commonHeaderView.setImageVisible(View.VISIBLE, View.INVISIBLE);
        commonHeaderView.setSegmentViewVisible(false);
        commonHeaderView.setOnClickBackListener(this);
        commonHeaderView.setCenterTextSize(17);
        commonHeaderView.setCenterText("");

        mTvHeadTitle = getView(R.id.teacher_login_image_head);
        mPsdTypePhoneInput = getView(R.id.edt_teacher_login_psd_type_phone);

        mRlPsdTypePasswordInputLayout = getView(R.id.rl_teacher_login_psd_type_password);
        mPsdTypePassWordInput = getView(R.id.edt_teacher_login_psd_type_password);
        mTvForgetPassWord = getView(R.id.tv_teacher_login_psd_type_forget);

        mRlVerificationCodeTypePhoneLayout = getView(R.id.rl_teacher_login_verification_code_type_phone);
        mVerificationCodeTypePhone = getView(R.id.edt_teacher_login_verification_code_type_phone);

        mTvNextPage = getView(R.id.teacher_login_btn_login);

        mTvVerificationCodeLogin = getView(R.id.teacher_vertify_code_login_btn);
        mTvRegister = getView(R.id.teacher_login_btn_register);

    }

    @Override
    public void setListener() {
        mPsdTypePhoneInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String pwdText = mPsdTypePassWordInput.getText().toString();
                mTvNextPage.setEnabled(!Utils.isStringEmpty(s.toString()) && !Utils.isStringEmpty(pwdText));
            }
        });

        mPsdTypePassWordInput.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String phoneText = mPsdTypePhoneInput.getText().toString();
                mTvNextPage.setEnabled(!Utils.isStringEmpty(s.toString()) && !Utils.isStringEmpty(phoneText));
            }
        });

        mVerificationCodeTypePhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s == null || s.length() == 0) return;
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < s.length(); i++) {
                    if (i != 3 && i != 8 && s.charAt(i) == ' ') {
                        continue;
                    } else {
                        sb.append(s.charAt(i));
                        if ((sb.length() == 4 || sb.length() == 9) && sb.charAt(sb.length() - 1) != ' ') {
                            sb.insert(sb.length() - 1, ' ');
                        }
                    }
                }
                if (!Utils.isStringEmpty(sb.toString()) && !sb.toString().equals(s.toString())) {
                    try {
                        mVerificationCodeTypePhone.setText(sb.toString());
                        mVerificationCodeTypePhone.setSelection(sb.length());
                    }catch (Exception e) {

                    }
                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                mTvNextPage.setEnabled(!Utils.isStringEmpty(s.toString()) && Utils.replaceBlank(s.toString()).length() == 11);
            }
        });

        mTvForgetPassWord.setOnClickListener(this);
        mTvNextPage.setOnClickListener(this);
        mTvVerificationCodeLogin.setOnClickListener(this);
        mTvRegister.setOnClickListener(this);
        mTvHeadTitle.setOnClickListener(this);
    }

    @Override
    public void initData(Context mContext) {
        mPsdTypePhoneInput.setHint("请输入账号或手机号");
        mPsdTypePhoneInput.setInputType(InputType.TYPE_CLASS_NUMBER);
        mPsdTypePhoneInput.setFilters(new InputFilter[]{new InputFilter.LengthFilter(16)});

        mPsdTypePassWordInput.setHint("请输入密码");
        mPsdTypePassWordInput.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        mPsdTypePassWordInput.setFilters(new InputFilter[]{new InputFilter.LengthFilter(16)});

        mVerificationCodeTypePhone.setHint("请输入手机号码");
        mVerificationCodeTypePhone.setInputType(InputType.TYPE_CLASS_NUMBER);
        mVerificationCodeTypePhone.setFilters(new InputFilter[]{new InputFilter.LengthFilter(13)});

    }

    @Override
    public void onClickBackListener(int type) {
        switch (type) {
            case CommonHeaderView.TITLE_BACK_LEFT:
                finish();
                break;
            case CommonHeaderView.TITLE_BACK_RIGHT:
                break;
            default: {
                break;
            }
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }
}
