package com.zswl.common.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/16 0016.
 */

public abstract class BaseRecycleViewAdapter<T> extends RecyclerView.Adapter<ViewHolder> {
    protected Context context;
    protected List<T> data;
    protected int layoutId;
    protected LayoutInflater inflater;

    public BaseRecycleViewAdapter(Context context, int layoutId) {
        this.context = context;
        this.data = new ArrayList<>();
        this.layoutId = layoutId;
        inflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(layoutId, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        onBind(data.get(position), holder, position);
    }

    public abstract void onBind(T t, ViewHolder holder, int position);

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }




    public void refreshData(List<T> result) {
        int preSize = data.size();
        data.clear();
        notifyItemRangeRemoved(0, preSize);
        data.addAll(result);
        notifyItemRangeInserted(0, result.size());
    }

    public void addData(List<T> result) {
        int preSize = data.size();
        data.addAll(result);
        notifyItemRangeInserted(preSize, result.size());
    }

}
