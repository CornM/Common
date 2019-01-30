package com.zswl.common.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.huantansheng.easyphotos.Builder.AlbumBuilder;
import com.huantansheng.easyphotos.EasyPhotos;
import com.zswl.common.R;
import com.zswl.common.util.EasyPhotosEngine;
import com.zswl.common.util.ToastUtil;

import java.io.File;


/**
 * Created by Administrator on 2018/6/20 0020.
 */

public class SelectPhotoDialog extends Dialog implements View.OnClickListener {
    private Context context;
//    private SelectListener listener;
    public static final int REQUEST_CODE_CAMERA = 2;
    public static final int REQUEST_CODE_LOCAL = 3;
    private File cameraFile;

    private int count = 1;
    private String authority;

    public SelectPhotoDialog(@NonNull Context context) {
        super(context, R.style.ActionSheetDialogStyle);
        this.context = context;
        init();
    }


    private void init() {
        setContentView(R.layout.select_pic_layout);
        authority = context.getApplicationContext().getPackageName() + ".provider";
        setViewLocation();
        View takePhoto = findViewById(R.id.tv_take_photo);
        View galley = findViewById(R.id.tv_galley);
        View cancel = findViewById(R.id.tv_cancle);
        takePhoto.setOnClickListener(this);
        galley.setOnClickListener(this);
        cancel.setOnClickListener(this);

    }


    /**
     * 设置可选几张图片
     *
     * @param count
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * 设置dialog位于屏幕底部
     */
    private void setViewLocation() {
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(dm);
        int height = dm.heightPixels;

        Window window = this.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.x = 0;
        lp.y = height;
        lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
        lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        // 设置显示位置
        onWindowAttributesChanged(lp);
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.tv_take_photo) {
            EasyPhotos.createCamera((Activity) context)//参数说明：上下文
                    .setFileProviderAuthority(authority)//参数说明：见下方`FileProvider的配置`
                    .start(REQUEST_CODE_CAMERA);
//                takePhoto();
//                if (listener != null) {
//                    listener.photo(cameraFile.getAbsolutePath());
//                }

        } else if (i == R.id.tv_galley) {
            AlbumBuilder builder = EasyPhotos.createAlbum((Activity) context, false, EasyPhotosEngine.getInstance())//参数说明：上下文，是否显示相机按钮，[配置Glide为图片加载引擎](https://github.com/HuanTanSheng/EasyPhotos/wiki/12-%E9%85%8D%E7%BD%AEImageEngine%EF%BC%8C%E6%94%AF%E6%8C%81%E6%89%80%E6%9C%89%E5%9B%BE%E7%89%87%E5%8A%A0%E8%BD%BD%E5%BA%93)
                    .setFileProviderAuthority(authority).setPuzzleMenu(false);//参数说明：见下方`FileProvider的配置`

            builder.setCount(count).start(REQUEST_CODE_LOCAL);


//                selectLocalPic();
//                if (listener != null) {
//                    listener.photo(cameraFile.getAbsolutePath());
//                }

        } else if (i == R.id.tv_cancle) {
        }
        dismiss();
    }

    /**
     * 图库选择
     */
    private void selectLocalPic() {
        Intent intent;
        if (Build.VERSION.SDK_INT < 19) {
            intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");

        } else {
            intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        }
        ((Activity) context).startActivityForResult(intent, REQUEST_CODE_LOCAL);
    }

    /**
     * 拍照
     */
    private void takePhoto() {
        if (!FileUtil.sdCardIsAvailable()) {
            ToastUtil.showShortToast("SD不可用");
            return;
        }
        String path = FileUtil.getSDCardPath() + Environment.DIRECTORY_DCIM + File.separator + System.currentTimeMillis() + ".jpg";
        cameraFile = new File(path);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        String authority = context.getApplicationContext().getPackageName() + ".provider";
        Uri uri = FileProvider.getUriForFile(context, authority, cameraFile);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);

        ((Activity) context).startActivityForResult(intent, REQUEST_CODE_CAMERA);
    }

//    public void setListener(SelectListener listener) {
//        this.listener = listener;
//    }
//
//    public interface SelectListener {
//        void photo(String path);
//    }


}
