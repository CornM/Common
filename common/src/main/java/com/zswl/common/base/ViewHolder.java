package com.zswl.common.base;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zswl.common.util.GlideUtil;

/**
 * Created by Administrator on 2018/4/16 0016.
 */

public class ViewHolder extends RecyclerView.ViewHolder {

    public ViewHolder(View itemView) {
        super(itemView);
    }

    public <T> T getView(int id) {
        SparseArray holder = (SparseArray) itemView.getTag();
        if (holder == null) {
            holder = new SparseArray();
            itemView.setTag(holder);
        }
        View view = (View) holder.get(id);
        if (view == null) {
            view = itemView.findViewById(id);
            holder.put(id, view);
        }
        return (T) view;

    }


    public void setText(int id, String content) {
        TextView textView = getView(id);
        textView.setText(content);
    }

    public void setCircleImage(int id, String url) {
        ImageView imageView = getView(id);
        GlideUtil.showCircleImg(url, imageView);
    }

    public void setImage(int id, String url) {
        ImageView imageView = getView(id);
        GlideUtil.showWithUrl(url, imageView);
    }

    public void setResourceImage(int id, int resourceId) {
        ImageView imageView = getView(id);
        GlideUtil.showWithRes(resourceId, imageView);
    }

    public void setChildViewClickListener(int id, final ViewClickListener listener) {
        View view = getView(id);
        if (!view.hasOnClickListeners()) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null)
                        listener.OnChildClick(v, getLayoutPosition());
                }
            });
        }

    }

    public void setItemClickListener(final ViewClickListener listener) {
        if (!itemView.hasOnClickListeners()) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null)
                        listener.OnItemClick(v, getLayoutPosition());
                }
            });
        }
    }

    public interface ViewClickListener {
        /**
         * 子view点击事件回调
         *
         * @param view
         * @param position
         */
        void OnChildClick(View view, int position);

        /**
         * item点击事假回调
         *
         * @param view
         * @param position
         */
        void OnItemClick(View view, int position);
    }

}
