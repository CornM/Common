package com.zswl.common.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.util.SparseArrayCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.List;

public abstract class BaseHeaderAdapter<T extends BaseBean> extends BaseRecycleViewAdapter<T> {

    private static final int BASE_ITEM_TYPE_HEADER = 100000;
    private static final int BASE_ITEM_TYPE_FOOTER = 200000;

    private SparseArrayCompat<View> mHeaderViews = new SparseArrayCompat<>();
    private SparseArrayCompat<View> mFootViews = new SparseArrayCompat<>();

    public BaseHeaderAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeaderViewPos(position)) {
            return mHeaderViews.keyAt(position);
        } else if (isFooterViewPos(position)) {
            return mFootViews.keyAt(position - getHeadersCount() - getRealItemCount());
        }
        return super.getItemViewType(position - getHeadersCount());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderViews.get(viewType) != null) {
            View headerView=mHeaderViews.get(viewType);
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);

            headerView.setLayoutParams(params);

            return new ViewHolder(mHeaderViews.get(viewType));

        } else if (mFootViews.get(viewType) != null) {
            View footerView=mFootViews.get(viewType);
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);

            footerView.setLayoutParams(params);

            return new ViewHolder(mFootViews.get(viewType));
        }
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    public int getItemCount() {
        return getHeadersCount() + getFootersCount() + getRealItemCount();
    }


    @Override
    protected T getItemBean(int position) {
        return super.getItemBean(position-getHeadersCount());
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull List<Object> payloads) {
        if (isHeaderViewPos(position)) {
            return;
        }
        if (isFooterViewPos(position)) {
            return;
        }
        super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public void refreshData(List<T> result) {
//        super.refreshData(result);
        int preSize = data.size();
        data.clear();
        notifyItemRangeRemoved(getHeadersCount(), preSize);
        data.addAll(result);
        notifyItemRangeInserted(getHeadersCount(), result.size());
    }

    @Override
    public void addData(List<T> result) {
//        super.addData(result);
        int preSize = data.size()+getHeadersCount();
        data.addAll(result);
        notifyItemRangeInserted(preSize, result.size());
    }

    //    @Override
//    public void onViewAttachedToWindow(ViewHolder holder) {
//        super.onViewAttachedToWindow(holder);
//        int position = holder.getLayoutPosition();
//        if (isHeaderViewPos(position) || isFooterViewPos(position)) {
//            WrapperUtils.setFullSpan(holder);
//        }
//    }
//    @Override
//    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
//        WrapperUtils.onAttachedToRecyclerView(this, recyclerView, new WrapperUtils.SpanSizeCallback() {
//            @Override
//            public int getSpanSize(GridLayoutManager layoutManager, GridLayoutManager.SpanSizeLookup oldLookup, int position) {
//                int viewType = getItemViewType(position);
//                if (mHeaderViews.get(viewType) != null) {
//                    return layoutManager.getSpanCount();
//                } else if (mFootViews.get(viewType) != null) {
//                    return layoutManager.getSpanCount();
//                }
//                if (oldLookup != null)
//                    return oldLookup.getSpanSize(position);
//                return 1;
//            }
//        });
//    }



    private boolean isHeaderViewPos(int position) {
        return position < getHeadersCount();
    }

    private boolean isFooterViewPos(int position) {
        return position >= getHeadersCount() + getRealItemCount();
    }

    private int getRealItemCount() {
        return data.size();
    }

    public void addHeaderView(View view) {
        mHeaderViews.put(mHeaderViews.size() + BASE_ITEM_TYPE_HEADER, view);
    }

    public void addFootView(View view) {
        mFootViews.put(mFootViews.size() + BASE_ITEM_TYPE_FOOTER, view);

    }

    public int getHeadersCount() {
        return mHeaderViews.size();
    }

    public int getFootersCount() {
        return mFootViews.size();
    }


}
