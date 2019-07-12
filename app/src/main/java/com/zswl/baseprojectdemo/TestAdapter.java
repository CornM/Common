package com.zswl.baseprojectdemo;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.zswl.common.base.BaseHeaderAdapter;
import com.zswl.common.base.BaseRecycleViewAdapter;
import com.zswl.common.base.ViewHolder;
import com.zswl.common.util.LogUtil;

public class TestAdapter extends BaseRecycleViewAdapter<TestBean> {
    public TestAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void onBind(TestBean testBean, ViewHolder holder, int position) {

        holder.setText(R.id.tv_name, testBean.getName());


    }

    @Override
    public boolean areItemTheSame(TestBean oldItem, TestBean newItem) {
        return oldItem.getName().equals(newItem.getName());
    }
}
