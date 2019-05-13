package com.zswl.baseprojectdemo;

import android.content.Context;
import android.widget.TextView;

import com.zswl.common.base.BaseHeaderAdapter;
import com.zswl.common.base.BaseRecycleViewAdapter;
import com.zswl.common.base.ViewHolder;

public class TestAdapter extends BaseHeaderAdapter<TestBean> {
    public TestAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    @Override
    public void onBind(TestBean testBean, ViewHolder holder, int position) {
        TextView textView=holder.getView(R.id.tv_name);
        textView.setText(testBean.getName());
    }
}
