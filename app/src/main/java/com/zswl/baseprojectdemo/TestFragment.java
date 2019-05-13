package com.zswl.baseprojectdemo;

import android.view.View;

import com.zswl.common.api.ApiService;
import com.zswl.common.base.BaseHeaderAndFooterListFragment;
import com.zswl.common.base.HttpResult;

import java.util.List;

import io.reactivex.Observable;

public class TestFragment extends BaseHeaderAndFooterListFragment<TestBean,TestAdapter> {
    @Override
    public int getHeaderLayoutId() {
        return R.layout.header_main2;
    }

    @Override
    public void setHeaderData(View headerView) {

    }

    @Override
    protected int getItemLayout() {
        return R.layout.item_test_layout;
    }

    @Override
    protected Observable<HttpResult<List<TestBean>>> getApi(int page) {
        return ApiService.getInstance().getApi(Api.class).getTestData(page,limit);

    }
}
