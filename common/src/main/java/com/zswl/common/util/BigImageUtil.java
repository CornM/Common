package com.zswl.common.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.zswl.common.base.ImageBean;
import com.zswl.common.widget.preview.ImagePreviewActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 查看大图
 */
public class BigImageUtil {
    private static void toBigImage(Context context, int imgType, String... url) {
        if (url.length == 0)
            return;
        List<ImageBean> imageInfoList = new ArrayList<>();
        for (String img : url) {
            ImageBean imageInfo = new ImageBean(img);
            if (imgType == ImageBean.IMAGE_LOCAL) {
                imageInfo.setType(ImageBean.IMAGE_LOCAL);
            } else {
                imageInfo.setType(ImageBean.IMAGE_INTERNET);
            }
            imageInfoList.add(imageInfo);
        }
        Intent intent = new Intent(context, ImagePreviewActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(ImagePreviewActivity.IMAGE_INFO, (Serializable) imageInfoList);
        bundle.putInt(ImagePreviewActivity.CURRENT_ITEM, 0);
        intent.putExtras(bundle);
        context.startActivity(intent);
        ((Activity) context).overridePendingTransition(0, 0);
    }

    public static void toBigNetImage(Context context, String... url) {
        toBigImage(context, 2, url);
    }

    public static void toBigLocalImage(Context context, String... url) {
        toBigImage(context, 1, url);
    }
}
