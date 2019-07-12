package com.zswl.baseprojectdemo;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.zswl.common.api.ApiService;
import com.zswl.common.base.BaseHeaderAdapter;
import com.zswl.common.base.BaseHeaderAndFooterListFragment;
import com.zswl.common.base.BaseListFragment;
import com.zswl.common.base.BaseRecycleViewAdapter;
import com.zswl.common.base.HttpResult;
import com.zswl.common.util.ToastUtil;

import java.util.List;

import io.reactivex.Observable;

public class TestFragment extends BaseListFragment<TestBean, TestAdapter> implements BaseRecycleViewAdapter.OnItemClickListener {


    @Override
    protected int getItemLayout() {
        return R.layout.item_test_layout;
    }

    @Override
    protected Observable<HttpResult<List<TestBean>>> getApi(int page) {
        return ApiService.getInstance().getApi(Api.class).getTestData(page, limit);

    }

//    @Override
//    public int getHeaderLayoutId() {
//        return R.layout.header_main2;
//    }
//
//    @Override
//    public void setHeaderData(View headerView) {
//
//    }

    @Override
    public void setAdapterWrapper() {
        super.setAdapterWrapper();
        adapter.setItemClickListener(this);

    }

//    @Override
//    public int getFooterLayoutId() {
//        return R.layout.header_main2;
//    }

    @Override
    public void onItemClick(View itemView, int position) {
        TestBean bean=adapter.getDataList().get(position);
        ToastUtil.showShortToast(bean.getName());
    }
}
