package com.zswl.common.base;


import com.zswl.common.R;

/**
 * Created by Administrator on 2018/7/28 0028.
 */

public class ImageBean extends BaseBean {
    private String imgPath;
    private int imgRes = R.drawable.ic_add_img;
    public static final int IMAGE_LOCAL = 1;//本地图片
    public static final int IMAGE_INTERNET = 2;//网络图片

    private int type;

    public int imageViewHeight;
    public int imageViewWidth;
    public int imageViewX;
    public int imageViewY;

    public ImageBean(String imgPath) {
        this.imgPath = imgPath;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public int getImgRes() {
        return imgRes;
    }

    public void setImgRes(int imgRes) {
        this.imgRes = imgRes;
    }
}
