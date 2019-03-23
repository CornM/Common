package com.zswl.baseprojectdemo;

import android.content.Context;

import com.zswl.common.base.BaseRecycleViewAdapter;
import com.zswl.common.base.ViewHolder;

public class TestAdapter extends BaseRecycleViewAdapter<TestBean> {
    public TestAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void onBind(TestBean testBean, ViewHolder holder, int position) {

    }
}
