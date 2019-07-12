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



}
