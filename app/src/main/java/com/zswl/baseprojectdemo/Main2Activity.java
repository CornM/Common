package com.zswl.baseprojectdemo;

import com.zswl.common.api.ApiService;
import com.zswl.common.base.BaseListActivity;
import com.zswl.common.base.HttpResult;

import java.util.List;

import io.reactivex.Observable;

public class Main2Activity extends BaseListActivity<TestBean, TestAdapter> {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main2;
    }


    @Override
    protected int getItemLayout() {
        return R.layout.item_test_layout;
    }

    @Override
    protected Observable<HttpResult<List<TestBean>>> getApi(int page) {
        return ApiService.getInstance().getApi(Api.class).getTestData(page, limit);
    }
}
