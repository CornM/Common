package com.zswl.common.base;


import com.zswl.common.R2;

/**
 * Created by Administrator on 2018/7/28 0028.
 */

public class ImageBean extends BaseBean {
    private String imgPath;
    private int imgRes = R2.drawable.ic_add;

    public ImageBean(String imgPath) {
        this.imgPath = imgPath;
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
