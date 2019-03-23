package com.zswl.common.base;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.zswl.common.R;
import com.zswl.common.util.GlideUtil;

/**
 * Created by Administrator on 2018/7/28 0028.
 */

public class ImageAdapter extends BaseRecycleViewAdapter<ImageBean> {
    private selectPickListener listener;

    public ImageAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    public void setListener(selectPickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onBind(ImageBean imageBean, ViewHolder holder, int position) {
        ImageView img = holder.getView(R.id.iv_img);
        if (TextUtils.isEmpty(imageBean.getImgPath())) {
            GlideUtil.showWithRes(imageBean.getImgRes(), img);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.photo();
                    }
                }
            });
        } else {
            holder.itemView.setOnClickListener(null);
            if (listener != null)
                listener.showImg(imageBean, img);
//            GlideUtil.showWithPath(imageBean.getImgPath(), img);
        }
    }

    /**
     * 点击添加照片按钮监听回调
     */
    public interface selectPickListener {
        void photo();

        void showImg(ImageBean imageBean, ImageView imageView);
    }
}
