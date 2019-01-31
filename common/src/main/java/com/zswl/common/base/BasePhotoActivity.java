package com.zswl.common.base;

import android.Manifest;
import android.content.Intent;

import com.huantansheng.easyphotos.EasyPhotos;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zswl.common.api.ExceptionHandle;
import com.zswl.common.util.RxUtil;
import com.zswl.common.util.ToastUtil;
import com.zswl.common.widget.ImageUtil;
import com.zswl.common.widget.SelectPhotoDialog;


import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class BasePhotoActivity extends BackActivity {
    //    private String takePhotoPath;
    private Disposable disposable;

    /**
     * 图片路径
     *
     * @param path
     */
    private void dealWithZipImg(final String path) {

        Observable.just(ImageUtil.getimage(path)).compose(RxUtil.io_main_nolife())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(String result) {
                        imagePath(path, result);

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }

                });
    }

    /**
     * 压缩之后的图片路径
     *
     * @param srcPath 压缩之前的图片路径
     * @param zipPath 压缩之后的图片路径
     */
    public abstract void imagePath(String srcPath, String zipPath);

    /**
     * 选择头像
     */
    protected void changeHeaderImg() {

        RxPermissions permissions = new RxPermissions(this);
        // Must be done during an initialization phase like onCreate
        permissions
                .request(Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe(new BaseObserver<Boolean>(context) {
                    @Override
                    public void onError(ExceptionHandle.ResponeThrowable e) {
                        ToastUtil.showShortToast("请打开相机权限");
                    }

                    @Override
                    public void receiveResult(Boolean result) {
                        if (result) {
                            SelectPhotoDialog dialog = new SelectPhotoDialog(context);
                            dialog.setCount(1);
                            dialog.show();
                        }
                    }
                });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (RESULT_OK == resultCode) {

//        返回图片地址集合：如果你只需要获取图片的地址，可以用这个
            ArrayList<String> resultPaths = data.getStringArrayListExtra(EasyPhotos.RESULT_PATHS);
            if (resultPaths != null && resultPaths.size() > 0)
                dealWithZipImg(resultPaths.get(0));


        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (disposable != null)
            disposable.dispose();
    }
}