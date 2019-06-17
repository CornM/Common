package com.zswl.baseprojectdemo;

import android.view.View;

import com.zswl.common.api.ApiService;
import com.zswl.common.base.BackActivity;
import com.zswl.common.base.BaseHeaderAndFooterListActivity;
import com.zswl.common.base.BaseViewPagerActivity;
import com.zswl.common.base.HttpResult;
import com.zswl.common.base.ViewPagerAdapter;

import java.util.List;

import io.reactivex.Observable;

public class Main2Activity extends BaseViewPagerActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main2;
    }

    @Override
    public String getTitleName() {
        return null;
    }

    @Override
    public String[] getTitles() {
        return new String[0];
    }

    @Override
    protected ViewPagerAdapter getViewPagerAdapter() {
        return null;
    }



//
//    @Override
//    protected int getItemLayout() {
//        return R.layout.item_test_layout;
//    }
//
//    @Override
//    protected Observable<HttpResult<List<TestBean>>> getApi(int page) {
//        return ApiService.getInstance().getApi(Api.class).getTestData(page,limit);
//    }
//
//    @Override
//    public int getHeaderLayoutId() {
//        return R.layout.header_main2;
//    }
//
//    @Override
//    public void setHeaderData(View headerView) {
//
//    }


}
