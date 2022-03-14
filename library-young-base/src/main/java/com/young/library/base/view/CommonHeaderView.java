package com.young.library.base.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.young.library.base.R;
import com.young.library.base.utils.DisplayUtils;
import com.young.library.base.utils.Utils;

/**
 * @author dupeng
 * @version 2.9.0, 2019/5/18  3:16 PM
 * @since android 17MiddleTeacher
 */
public class CommonHeaderView extends LinearLayout implements View.OnClickListener {
    public static final int TITLE_BACK_LEFT = 0;
    public static final int TITLE_BACK_RIGHT = 1;
    public static final int TITLE_CENTER = 2;
    public static final int TITLE_MORE = 3;
    public static final int TITLE_ANIMATION = 4;
    private TextView mHeaderLeft;
    private TextView mHeaderRight;
    private TextView mHeaderCenter;
    private TextView mSubTitle;
    private ImageView mRightImage;
    private RelativeLayout mRelativeLayout;
    private OnClickBackListener mOnClickBackListener;
    private View mSegmentLine;

    /**
     * @param onClickBackListener the mOnClickBackListener to set
     */
    public void setOnClickBackListener(OnClickBackListener onClickBackListener) {
        this.mOnClickBackListener = onClickBackListener;
    }

    public CommonHeaderView(Context context) {
        super(context, null);
    }

    public interface OnClickBackListener {
        void onClickBackListener(int type);
    }

    public CommonHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mRelativeLayout = (RelativeLayout) this.findViewById(R.id
                .teacher_common_header_title_layout);
        this.mHeaderLeft = (TextView) this.findViewById(R.id.teacher_common_header_left_button);
        this.mHeaderRight = (TextView) this.findViewById(R.id.teacher_common_header_right_button);
        this.mHeaderCenter = (TextView) this.findViewById(R.id.teacher_common_header_center_title);
        this.mSubTitle = (TextView) this.findViewById(R.id.teacher_common_header_sub_title);
        this.mSegmentLine = this.findViewById(R.id.segment_line);
        this.mRightImage = (ImageView) this.findViewById(R.id
                .teacher_common_header_right_image);
        this.mHeaderLeft.setOnClickListener(this);
        this.mHeaderRight.setOnClickListener(this);
        this.mHeaderCenter.setOnClickListener(this);
        this.mRightImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (this.mOnClickBackListener != null) {
            int id = view.getId();
            if (id == R.id.teacher_common_header_left_button) {
                this.mOnClickBackListener.onClickBackListener(TITLE_BACK_LEFT);
            } else if (id == R.id.teacher_common_header_right_button) {
                this.mOnClickBackListener.onClickBackListener(TITLE_BACK_RIGHT);
            } else if (id == R.id.teacher_common_header_center_title) {
                this.mOnClickBackListener.onClickBackListener(TITLE_CENTER);
            }
        }
    }

    public void setCenterSingleLines(int lines) {
        this.mHeaderCenter.setLines(lines);
    }

    public void setCenterText(String title) {
        this.mHeaderCenter.setText(title);
    }

    public void setCenterTextSize(float size) {
        this.mHeaderCenter.setTextSize(size);
    }

    public void setSubCenterTextSize(float size) {
        this.mSubTitle.setTextSize(size);
    }

    public void setCenterTextType(Typeface typeface) {
        this.mHeaderCenter.setTypeface(typeface);
    }

    public void setCenterText(CharSequence text) {
        this.mHeaderCenter.setText(text);
    }

    public void setSubTitle(String subTitle) {
        this.mSubTitle.setText(subTitle);
        this.mSubTitle.setVisibility(View.VISIBLE);
    }

    public TextView getSubTitle() {
        return mSubTitle;
    }

    public void setLeftImageResource(int resId) {
        this.mHeaderLeft.setCompoundDrawablesWithIntrinsicBounds(resId == 0 ? null : this
                .getResources().getDrawable(resId), null, null, null);
    }

    /**
     * right 整体修改textview
     *
     * @param resId
     * @param textName
     */
    public void setRightBackGroundResource(int resId, String textName) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(LayoutParams
                .WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        layoutParams.rightMargin = 15;
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        layoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
        layoutParams.alignWithParent = true;
        this.mHeaderRight.setLayoutParams(layoutParams);
        this.mHeaderRight.setGravity(Gravity.CENTER);
        this.mHeaderRight.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        this.mHeaderRight.setBackgroundResource(resId);
        this.mHeaderRight.setText(textName);
        this.mHeaderRight.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 17);
        this.mHeaderRight.setPadding(20, 5, 20, 5);
    }

    public void setLeftBackGroundResource(int resId, String textName, int color) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(LayoutParams
                .WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        layoutParams.leftMargin = DisplayUtils.dip2px(this.getContext(), 12);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        layoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
        layoutParams.alignWithParent = true;
        this.mHeaderLeft.setLayoutParams(layoutParams);
        this.mHeaderLeft.setGravity(Gravity.CENTER);
        this.mHeaderLeft.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        this.mHeaderLeft.setBackgroundResource(resId);
        this.mHeaderLeft.setText(textName);
        this.mHeaderLeft.setTextColor(color);
        this.mHeaderLeft.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 17);
        this.mHeaderLeft.setPadding(20, 5, 20, 5);
    }

    public void setRightBackGroundResource(int resId, String textName, int color) {
        if (Utils.isStringEmpty(textName)) {
            this.mHeaderRight.setVisibility(View.GONE);
        } else {
            this.mHeaderRight.setVisibility(View.VISIBLE);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams
                    (LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            layoutParams.rightMargin = DisplayUtils.dip2px(this.getContext(), 12);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            layoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
            layoutParams.alignWithParent = true;
            this.mHeaderRight.setLayoutParams(layoutParams);
            this.mHeaderRight.setGravity(Gravity.CENTER);
            this.mHeaderRight.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
            this.mHeaderRight.setBackgroundResource(resId);
            this.mHeaderRight.setText(textName);
            this.mHeaderRight.setTextColor(color);
            this.mHeaderRight.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
            this.mHeaderRight.setPadding(20, 5, 20, 5);
        }
    }

    public void setRightStyle(int resId, String textName, int color, int textSize) {
        setRightBackGroundResource(resId, textName, color);
        this.mHeaderRight.setTextSize(TypedValue.COMPLEX_UNIT_DIP, textSize);

    }

    public void setRightImageResource(int resId) {
        this.mHeaderRight.setCompoundDrawablesWithIntrinsicBounds(resId == 0 ? null : this
                .getResources().getDrawable(resId), null, null, null);
    }

    public void setCenterImageResource(int resId) {
        this.mHeaderCenter.setCompoundDrawablesWithIntrinsicBounds(null, null, resId == 0 ? null
                : this.getResources().getDrawable(resId), null);
    }

    public void setDrawableLeft(Drawable drawable) {
        this.mHeaderLeft.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
    }

    public void setLeftText(String text) {
        this.mHeaderLeft.setText(text);
    }

    public void setLeftTextColor(int color) {
        this.mHeaderLeft.setTextColor(color);
    }

    public void setIsCloud(boolean bo) {
        this.mHeaderLeft.setPadding(0, 0, 0, 0);
    }

    public void setRightTextColor(int color) {
        this.mHeaderRight.setTextColor(color);
    }

    public void setRightTextColorSet(int colorSetId) {
        this.mHeaderRight.setTextColor(getResources().getColorStateList(colorSetId));
    }

    public void setRightText(String text) {
        this.mHeaderRight.setText(text);
    }

    public String getRightText() {
        return this.mHeaderRight.getText().toString();
    }

    public void setLeftTextEnable(boolean isEnable) {
        this.mHeaderLeft.setEnabled(isEnable);
    }

    public void setRightTextEnable(boolean isEnable) {
        this.mHeaderRight.setEnabled(isEnable);
    }

    public void setDrawableRight(Drawable drawable) {
        this.mHeaderRight.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
    }

    public void setImageVisible(int isLeftVisible, int isRightVisible) {
        this.mHeaderLeft.setVisibility(isLeftVisible);
        this.mHeaderRight.setVisibility(isRightVisible);
    }

    public void setTitleBackgroundColorLayout(int color) {
        this.mRelativeLayout.setBackgroundColor(this.getResources().getColor(color));
    }

    public void setTitleBackgroundResourceLayout(int resid) {
        this.mRelativeLayout.setBackgroundResource(resid);
    }

    public void setSegmentViewVisible(boolean visible) {
        if (visible) {
            this.mSegmentLine.setVisibility(View.VISIBLE);
        } else {
            this.mSegmentLine.setVisibility(View.GONE);
        }
    }


    /**
     * 获取图片
     *
     * @param url
     */
    public void setRightImageUrl(String url) {
        if (mRightImage != null) {
            mRightImage.setImageBitmap(Utils.getBitmapFromURL(url));
            mRightImage.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 设置右侧按钮图标
     *
     * @param resid
     */
    public void setRightImageResource2(int resid) {
        if (mRightImage != null) {
            mRightImage.setImageResource(resid);
            mRightImage.setVisibility(View.VISIBLE);
        }
    }

    public void setCenterTextTitle(String title) {
        this.mHeaderCenter.setText(title);
    }

    public void setCenterTextColor(int color) {
        this.mHeaderCenter.setTextColor(color);
    }

    public void setSubCenterTextColor(int color) {
        this.mSubTitle.setTextColor(color);
    }

    public void setCenterTextColor(ColorStateList colors) {
        this.mHeaderCenter.setTextColor(colors);
    }

    public TextView getHeaderRightView() {
        return this.mHeaderRight;
    }

    public TextView getHeaderCenterView() {
        return this.mHeaderCenter;
    }

    public View getHeaderLinlayoutView() {
        return this.mRelativeLayout;
    }
}
