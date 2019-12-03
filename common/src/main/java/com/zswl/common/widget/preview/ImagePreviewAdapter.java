package com.zswl.common.widget.preview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.github.chrisbanes.photoview.OnPhotoTapListener;
import com.github.chrisbanes.photoview.PhotoView;
import com.zswl.common.R;
import com.zswl.common.base.ImageBean;
import com.zswl.common.util.GlideUtil;

import java.util.List;



public class ImagePreviewAdapter extends PagerAdapter implements OnPhotoTapListener {

    private List<ImageBean> imageInfo;
    private Context context;
    private View currentView;

    public ImagePreviewAdapter(Context context, @NonNull List<ImageBean> imageInfo) {
        super();
        this.imageInfo = imageInfo;
        this.context = context;
    }

    @Override
    public int getCount() {
        return imageInfo.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
        currentView = (View) object;
    }

    public View getPrimaryItem() {
        return currentView;
    }

    public ImageView getPrimaryImageView() {
        ImageView imageView= currentView.findViewById(R.id.pv);
        showExcessPic(imageView);
        return imageView;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_photoview, container, false);
        final ProgressBar pb = (ProgressBar) view.findViewById(R.id.pb);
        final PhotoView imageView = (PhotoView) view.findViewById(R.id.pv);

        ImageBean info = this.imageInfo.get(position);
        imageView.setOnPhotoTapListener(this);
        if (info.getType() == ImageBean.IMAGE_INTERNET) {
            GlideUtil.showWithUrl(info.getImgPath(), imageView);
        } else {
            GlideUtil.showWithPath(info.getImgPath(), imageView);
        }

        //如果需要加载的loading,需要自己改写,不能使用这个方法
//        NineGridView.getImageLoader().onDisplayImage(view.getContext(), imageView, info.bigImageUrl);

//        pb.setVisibility(View.VISIBLE);
//        Glide.with(context).load(info.bigImageUrl)//
//                .placeholder(R.drawable.ic_default_image)//
//                .error(R.drawable.ic_default_image)//
//                .diskCacheStrategy(DiskCacheStrategy.ALL)//
//                .listener(new RequestListener<String, GlideDrawable>() {
//                    @Override
//                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
//                        pb.setVisibility(View.GONE);
//                        return false;
//                    }
//
//                    @Override
//                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
//                        pb.setVisibility(View.GONE);
//                        return false;
//                    }
//                }).into(imageView);

        container.addView(view);
        return view;
    }

    /**
     * 展示过度图片
     */
    private void showExcessPic(ImageView imageView) {
//        //先获取大图的缓存图片
//        Bitmap cacheImage = GlideApp.with(context).asBitmap(). (imageInfo.bigImageUrl);
//        //如果大图的缓存不存在,在获取小图的缓存
//        if (cacheImage == null)
//            cacheImage = NineGridView.getImageLoader().getCacheImage(imageInfo.thumbnailUrl);
//        //如果没有任何缓存,使用默认图片,否者使用缓存
//        if (cacheImage == null) {
        imageView.setImageResource(R.drawable.ic_place_holder);
//        } else {
//            imageView.setImageBitmap(cacheImage);
//        }
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }


    /**
     * 单击屏幕关闭
     */
    @Override
    public void onPhotoTap(ImageView view, float x, float y) {
        ((ImagePreviewActivity) context).finishActivityAnim();
    }
}