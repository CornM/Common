package com.zswl.common.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zswl.common.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/16 0016.
 */

public abstract class BaseRecycleViewAdapter<T> extends RecyclerView.Adapter<ViewHolder>  {
    protected Context context;
    protected List<T> data;
    protected int layoutId;
    protected LayoutInflater inflater;
    protected DiffCallBack callBack;

    public BaseRecycleViewAdapter(Context context, int layoutId) {
        this.context = context;
        this.layoutId = layoutId;
        this.data = new ArrayList<>();
        callBack = new DiffCallBack();
        inflater = LayoutInflater.from(context);
    }


    public BaseRecycleViewAdapter(Context context, List<T> data, int layoutId) {
        this(context, layoutId);
        this.data.addAll(data);

    }

    protected T getItemBean(int position) {
        return data.get(position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(layoutId, parent, false);
        ViewHolder holder = new ViewHolder(view);
        setViewClickListener(holder);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        onBind(getItemBean(position), holder, position);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull List<Object> payloads) {
        if (payloads.isEmpty()) {
            T bean = getItemBean(position);
            onBind(bean, holder, position);
        } else {
            T bean = getItemBean(position);
            onBindWithPayloads(bean, holder, position, payloads);

        }

    }

    public abstract void onBind(T t, ViewHolder holder, int position);

    /**
     * 局部刷新
     *
     * @param t
     * @param holder
     * @param position
     * @param payloads
     */
    public void onBindWithPayloads(T t, ViewHolder holder, int position, List<Object> payloads) {

    }

    /**
     * 判断是不是同一个Bean
     *
     * @param oldItem
     * @param newItem
     * @return
     */
    public boolean areItemTheSame(T oldItem, T newItem) {
        return false;
    }

    /**
     * 判断内容是否一样
     *
     * @param oldItem
     * @param newItem
     * @return
     */
    public boolean areContentTheSame(T oldItem, T newItem) {
        return false;
    }

    /**
     * 局部更新
     *
     * @param oldItem
     * @param newItem
     * @return
     */
    public Object getPayload(T oldItem, T newItem) {

        return null;
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    /**
     * 获取数据集合
     *
     * @return
     */
    public List<T> getDataList() {
        return data;
    }


    public void notifyDataChanged(List<T> result) {
        callBack.setNewDataList(result);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(callBack, false);
        data = result;
        diffResult.dispatchUpdatesTo(this);
    }


    public void refreshData(List<T> result) {
        notifyDataChanged(result);
    }

    public void addData(List<T> result) {
        int preSize = data.size();
        data.addAll(result);
        notifyItemRangeInserted(preSize, result.size());
    }


    /**
     * 此方法中创建View点击事件
     *
     * @param holder
     */
    protected void setViewClickListener(ViewHolder holder) {

    }

    public class DiffCallBack extends DiffUtil.Callback {

        private List<T> newDataList;

        public void setNewDataList(List<T> newDataList) {
            this.newDataList = newDataList;
        }

        @Override
        public int getOldListSize() {
            return getItemCount();
        }

        @Override
        public int getNewListSize() {
            return newDataList == null ? 0 : newDataList.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {

            return areItemTheSame(data.get(oldItemPosition), newDataList.get(newItemPosition));
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return areContentTheSame(data.get(oldItemPosition), newDataList.get(newItemPosition));
        }

        @Nullable
        @Override
        public Object getChangePayload(int oldItemPosition, int newItemPosition) {
            return getPayload(null, newDataList.get(newItemPosition));
        }
    }


}
