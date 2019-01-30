package com.zswl.common.util;

import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.zswl.common.R;
import com.zswl.common.base.BaseApplication;
import com.zswl.common.base.GlideApp;


import java.io.File;


/**
 * Created by Administrator on 2018/6/13 0013.
 */

public class GlideUtil {

    public static void showWithUrl(String url, ImageView target) {
        if (TextUtils.isEmpty(url)) {
//            ToastUtil.showShortToast("图片链接不存在");
            return;
        }

        if (url.startsWith("/")) {
            url = url.replaceFirst("/", "");
        }
        GlideApp.with(BaseApplication.getAppInstance()).load(BaseApplication.HOST + url)
                .placeholder(R.drawable.ic_place_holder)
                .error(R.drawable.ic_place_holder)
                .into(target);

    }

    public static void showWithRes(int resId, ImageView target) {
        GlideApp.with(BaseApplication.getAppInstance()).load(resId).into(target);
    }

    public static void showWithPath(String path, ImageView target) {
        GlideApp.with(BaseApplication.getAppInstance()).load(new File(path)).into(target);
    }

    public static void showCircleWithPath(String path, ImageView target) {
        GlideApp.with(BaseApplication.getAppInstance()).asBitmap().transform(new CircleCrop()).load(new File(path)).into(target);
    }

    public static void showCircleImg(String url, ImageView target) {
        GlideApp.with(BaseApplication.getAppInstance()).asBitmap()
                .error(R.drawable.ic_place_holder)
                .transform(new CircleCrop())
                .load(BaseApplication.HOST + url).into(target);
    }

}
