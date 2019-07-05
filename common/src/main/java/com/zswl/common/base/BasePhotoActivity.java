package com.zswl.common.base;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;

import com.huantansheng.easyphotos.EasyPhotos;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zswl.common.api.ExceptionHandle;
import com.zswl.common.util.RxUtil;
import com.zswl.common.util.ToastUtil;
import com.zswl.common.widget.FileUtil;
import com.zswl.common.widget.ImageUtil;
import com.zswl.common.widget.SelectPhotoDialog;


import java.io.File;
import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import top.zibin.luban.CompressionPredicate;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

import static com.zswl.common.widget.SelectPhotoDialog.REQUEST_CODE_CAMERA;

public abstract class BasePhotoActivity extends BackActivity {
    //    private String takePhotoPath;
//    private Disposable disposable;

    /**
     * 图片路径
     *
     * @param path
     */
    private void dealWithZipImg(final String path) {

        Luban.with(this)
                .load(path)
                .ignoreBy(100)
                .filter(new CompressionPredicate() {
                    @Override
                    public boolean apply(String path) {
                        return !(TextUtils.isEmpty(path) || path.toLowerCase().endsWith(".gif"));
                    }
                })
                .setCompressListener(new OnCompressListener() {
                    @Override
                    public void onStart() {
                        // TODO 压缩开始前调用，可以在方法内启动 loading UI
                    }

                    @Override
                    public void onSuccess(File file) {
                        // TODO 压缩成功后调用，返回压缩后的图片文件
                        imagePath(path, file.getPath());
                    }

                    @Override
                    public void onError(Throwable e) {
                        // TODO 当压缩过程出现问题时调用
                    }
                }).launch();



//        Observable.just(ImageUtil.getimage(path)).compose(RxUtil.io_main_nolife())
//                .subscribe(new Observer<String>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        disposable = d;
//                    }
//
//                    @Override
//                    public void onNext(String result) {
//                        imagePath(path, result);
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//
//                });
    }

    /**
     * 压缩之后的图片路径
     *
     * @param srcPath 压缩之前的图片路径
     * @param zipPath 压缩之后的图片路径
     */
    public abstract void imagePath(String srcPath, String zipPath);

    /**
     * 打开相机
     */
    protected void openCamera() {
        String authority = context.getApplicationContext().getPackageName() + ".provider";
        EasyPhotos.createCamera((Activity) context)//参数说明：上下文
                .setFileProviderAuthority(authority)//参数说明：见下方`FileProvider的配置`
                .start(REQUEST_CODE_CAMERA);
    }

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


//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        if (disposable != null)
//            disposable.dispose();
//    }
}
