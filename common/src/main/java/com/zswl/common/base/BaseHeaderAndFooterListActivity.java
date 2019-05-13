package com.zswl.common.base;

import android.view.LayoutInflater;
import android.view.View;

public abstract class BaseHeaderAndFooterListActivity<B extends BaseBean, A extends BaseHeaderAdapter<B>> extends BaseListActivity<B, A> {


    /**
     * 获取头部布局id
     *
     * @return
     */
    public abstract int getHeaderLayoutId();

    /**
     * 设置头部控件数据
     *
     * @param headerView
     */
    public abstract void setHeaderData(View headerView);


    /**
     * 获取底部布局
     *
     * @return
     */
    public int getFooterLayoutId() {
        return 0;
    }

    /**
     * 设置底部数据
     *
     * @param footerView
     */
    public void setFooterData(View footerView) {
    }


    @Override
    public void setAdapterWrapper() {
        LayoutInflater inflater = LayoutInflater.from(context);

        View headerView = inflater.inflate(getHeaderLayoutId(), null, false);
        setHeaderData(headerView);

        adapter.addHeaderView(headerView);
        int footerViewId = getFooterLayoutId();
        if (footerViewId != 0) {
            View footerView = inflater.inflate(footerViewId, null, false);
            setFooterData(footerView);
            adapter.addFootView(footerView);
        }
        super.setAdapterWrapper();
    }
}
