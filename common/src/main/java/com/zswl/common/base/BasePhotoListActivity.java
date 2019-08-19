package com.zswl.common.base;

import android.Manifest;
import android.content.Intent;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;

import com.huantansheng.easyphotos.EasyPhotos;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zswl.common.R;
import com.zswl.common.api.ExceptionHandle;
import com.zswl.common.util.ToastUtil;
import com.zswl.common.widget.SelectPhotoDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import top.zibin.luban.CompressionPredicate;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

public abstract class BasePhotoListActivity extends BackActivity implements ImageAdapter.selectPickListener {
    protected RecyclerView imgRv;
    protected Map<String, String> imgs;//存放要上传图片路径
    private List<ImageBean> dataImg;
    protected ImageAdapter adapter;
//    private Disposable disposable;
    protected int maxsize = 9;

    @Override
    protected void init() {
        super.init();
        imgRv = findViewById(R.id.rv);
        imgs = new HashMap<>();
        dataImg = new ArrayList<>();
        ImageBean bean = new ImageBean("");
        setAddImg(bean);
        dataImg.add(bean);
        adapter = new ImageAdapter(context, R.layout.item_imge);
        GridLayoutManager manager = new GridLayoutManager(context, 3);
        imgRv.setLayoutManager(manager);
        imgRv.setAdapter(adapter);
        adapter.refreshData(dataImg);
        adapter.setListener(this);
    }

    @Override
    public void photo() {
        changeHeaderImg();
    }

    /**
     * 此方法设置
     *
     * @param bean
     */
    protected void setAddImg(ImageBean bean) {

    }

//
//    //    private String takePhotoPath;
//
//    /**
//     * 图片路径
//     *
//     * @param path
//     */
//    public abstract void updateHeadrImg(String path);

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
                            dialog.setCount(maxsize - imgs.size());
                            dialog.show();
                        }
                    }
                });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (RESULT_OK == resultCode) {

            //返回图片地址集合：如果你只需要获取图片的地址，可以用这个
            ArrayList<String> resultPaths = data.getStringArrayListExtra(EasyPhotos.RESULT_PATHS);
            //返回图片地址集合时如果你需要知道用户选择图片时是否选择了原图选项，用如下方法获取
//            boolean selectedOriginal = data.getBooleanExtra(EasyPhotos.RESULT_SELECTED_ORIGINAL, false);
// capture new image
//                updateHeadrImg(takePhotoPath);
            if (resultPaths != null && resultPaths.size() > 0) {
//                for (String path : resultPaths) {
//                    updateHeadrImg(path);
//                }
                if (imgs.size() < maxsize) {
                    Luban.with(this)
                            .load(resultPaths)
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
                                    String path = file.getAbsolutePath();
                                    imgs.put("img" + imgs.size(), path);
                                    dataImg.add(0, new ImageBean(path));
                                    adapter.notifyDataChanged(dataImg);
                                }

                                @Override
                                public void onError(Throwable e) {
                                    // TODO 当压缩过程出现问题时调用
                                }
                            }).launch();


                }
            }
//                    updateHeadrImg(resultPaths.get(0));


        }
    }


//    public void updateHeadrImg(final String path) {
//        if (imgs.size() < maxsize) {
//            dataImg.add(0, new ImageBean(path));
//            adapter.refreshData(dataImg);
//
//            //进行压缩
//            Observable.just(ImageUtil.getimage(path))
//                    .subscribeOn(Schedulers.io()).subscribe(new Observer<String>() {
//                @Override
//                public void onSubscribe(Disposable d) {
//                    disposable = d;
//                }
//
//                @Override
//                public void onNext(String s) {
//                    imgs.put("img" + imgs.size(), s);
//                }
//
//                @Override
//                public void onError(Throwable e) {
//
//                }
//
//                @Override
//                public void onComplete() {
//
//                }
//            });
//
//        }
//    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        if (disposable != null)
//            disposable.dispose();
//    }
}
