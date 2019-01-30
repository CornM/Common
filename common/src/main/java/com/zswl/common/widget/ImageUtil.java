package com.zswl.common.widget;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;

import com.zswl.common.util.LogUtil;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Administrator on 2018/4/16 0016.
 */

public class ImageUtil {
    private static final String TAG = "ImageUtil";

    /**
     * 质量压缩方法
     *
     * @param image
     * @return
     */
    public static String compressImage(Bitmap image) {
        String uploadPath;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 80;
        while (baos.toByteArray().length / 1024 > 800) { // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();// 重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
            options -= 5;// 每次都减少10
            LogUtil.d("ImageUtil-----------options" + options);
            LogUtil.d("ImageUtil-----------img" + image.getByteCount());
        }

        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
//        srcPath=srcPath.replace(":","");
        uploadPath = FileUtil.getUploadImgPath()+ System.currentTimeMillis();
        save(bitmap, new File(uploadPath), Bitmap.CompressFormat.JPEG,false);
        LogUtil.d(TAG, uploadPath);
        LogUtil.d("ImageUtil----->size:" + bitmap.getByteCount() / 1024);
        return uploadPath;
    }

    /**
     * 图片按比例大小压缩方法（根据路径获取图片并压缩）
     *
     * @param srcPath
     * @return
     */
    public static String getimage(String srcPath) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        // 开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);// 此时返回bm为空
//        RxLogTool.d("ImageUtil----->size:"+bitmap.getByteCount());
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
//        RxLogTool.d("ImageUtil", "===========w========" + w);
//        RxLogTool.d("ImageUtil", "===========h========" + h);
        // 现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
        float hh = 1920f;// 这里设置高度为800f
        float ww = 1080f;// 这里设置宽度为480f
        // 缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;// be=1表示不缩放
        if (w > h && w > ww) {// 如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {// 如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;// 设置缩放比例
        // 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
//        RxLogTool.d("ImageUtil.size:" + bitmap.getByteCount());
        return compressImage(bitmap);// 压缩好比例大小后再进行质量压缩
    }


    public static Bitmap convertViewToBitmap(View view) {
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        return bitmap;
    }



    public static boolean save(Bitmap src, File file, Bitmap.CompressFormat format, boolean recycle) {
        if (!isEmptyBitmap(src) && FileUtil.createOrExistsFile(file)) {
            LogUtil.d(TAG,src.getWidth() + ", " + src.getHeight());
            OutputStream os = null;
            boolean ret = false;

            try {
                os = new BufferedOutputStream(new FileOutputStream(file));
                ret = src.compress(format, 100, os);
                if (recycle && !src.isRecycled()) {
                    src.recycle();
                }
            } catch (IOException var10) {
                var10.printStackTrace();
            } finally {
                if (os!=null){
                    try {
                        os.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            return ret;
        } else {
            return false;
        }
    }


    public static boolean isEmptyBitmap(Bitmap src) {
        return src == null || src.getWidth() == 0 || src.getHeight() == 0;
    }
}
