package com.zswl.baseprojectdemo;

import com.zswl.common.base.BaseViewPagerActivity;
import com.zswl.common.base.ViewPagerAdapter;

public class Main2Activity extends BaseViewPagerActivity {
//    @BindView(R.id.tb)
//    DrawableTabLayout tabLayout;
//    @BindView(R.id.vp)
//    ViewPager viewPager;
    private String[] titles = {"度发货地", "地方", "发放", "发发达", "阿凡达", "的的大纲", "发的个", "P", "A"};

//    @Override
//    protected int getLayoutId() {
//        return R.layout.activity_main2;
//    }
//
//    @Override
//    protected void init() {
//        super.init();
//        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), TestFragment.class, titles);
//
//        viewPager.setAdapter(adapter);
//
//
//        tabLayout.setWithViewPager(viewPager);
//        tabLayout.setIndicatorImageRes(R.drawable.bg_actionbar);
//        tabLayout.setIndicatorMarginBottom(10);
//    }

    @Override
    public String getTitleName() {
        return "dffdfd";
    }

    @Override
    public String[] getTitles() {
        return titles;
    }

    @Override
    protected ViewPagerAdapter getViewPagerAdapter() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), TestFragment.class);
        return adapter;
    }

    //    @Override
//    public String getTitleName() {
//        return null;
//    }
//
//    @Override
//    public String[] getTitles() {
//        return new String[0];
//    }
//
//    @Override
//    protected ViewPagerAdapter getViewPagerAdapter() {
//        return null;
//    }


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
