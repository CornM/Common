package com.zswl.common.base;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

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
}
