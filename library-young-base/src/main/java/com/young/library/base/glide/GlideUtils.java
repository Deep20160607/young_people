package com.young.library.base.glide;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

/**
 * @author dupeng
 * @version 2.6.0, 2019/2/2  1:41 PM
 * @since android 17MiddleTeacher
 */
public class GlideUtils {

//    /**
//     * 加载图片
//     *
//     * @param context  context
//     * @param iv       imageView
//     * @param url      图片地址
//     * @param emptyImg 默认展位图
//     */
//    public static void loadImage(Context context, ImageView iv, String url, int emptyImg) {
//        if (!TextUtils.isEmpty(url)) {
//            GlideApp.with(context)
//                    .load(url)
//                    .error(emptyImg)
//                    .placeholder(iv.getDrawable())
//                    .transition(new DrawableTransitionOptions().crossFade())
//                    .into(iv);
//        } else {
//            loadImage(context, iv, emptyImg, emptyImg);
//        }
//    }
//
//    /**
//     * 加载圆角图片
//     *
//     * @param context  context
//     * @param iv       imageView
//     * @param url      图片地址
//     * @param emptyImg 默认展位图
//     */
//    public static void loadRoundImage(Context context, ImageView iv, String url, int emptyImg) {
//        if (!TextUtils.isEmpty(url)) {
//            GlideApp.with(context)
//                    .load(url)
//                    .error(emptyImg)
//                    .placeholder(iv.getDrawable())
//                    .transition(new DrawableTransitionOptions().crossFade())
//                    .transform(new RoundedCorners(20)).into(iv);
//        } else {
//            loadRoundImage(context, iv, emptyImg, emptyImg);
//        }
//    }
//
//    /**
//     * 加载圆形图片
//     *
//     * @param context  context
//     * @param iv       imageView
//     * @param url      图片地址
//     * @param emptyImg 默认展位图
//     */
//    public static void loadCircleImage(Context context, ImageView iv, String url, int emptyImg) {
//        if (!TextUtils.isEmpty(url)) {
//            Glide.with(context)
//                    .load(url)
//                    .error(emptyImg)
//                    .placeholder(iv.getDrawable())
//                    .transition(new DrawableTransitionOptions().crossFade())
//                    .transform(new CircleCrop()).into(iv);
//        } else {
//            loadCircleImage(context, iv, emptyImg, emptyImg);
//        }
//    }
//
//    /**
//     * 加载drawable中的图片资源
//     *
//     * @param context  context
//     * @param iv       imageView
//     * @param resId    图片地址
//     * @param emptyImg 默认展位图
//     */
//    public static void loadImage(Context context, ImageView iv, int resId, int emptyImg) {
//        Glide.with(context).load(resId).placeholder(emptyImg).into(iv);
//    }
//
//    /**
//     * 加载drawable中的图片资源 圆角
//     *
//     * @param context  context
//     * @param iv       imageView
//     * @param resId    图片地址
//     * @param emptyImg 默认展位图
//     */
//    public static void loadRoundImage(Context context, ImageView iv, int resId, int emptyImg) {
//        Glide.with(context).load(emptyImg).placeholder(emptyImg).transform(new RoundedCorners(20)).into(iv);
//    }
//
//    /**
//     * 加载drawable中的图片资源 圆形
//     *
//     * @param context  context
//     * @param iv       imageView
//     * @param resId    图片地址
//     * @param emptyImg 默认展位图
//     */
//    public static void loadCircleImage(Context context, ImageView iv, int resId, int emptyImg) {
//        Glide.with(context).load(emptyImg).placeholder(emptyImg).transform(new CircleCrop()).into(iv);
//    }


}
