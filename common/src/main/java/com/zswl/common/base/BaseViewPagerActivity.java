package com.zswl.common.base;

import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.zswl.common.R;
import com.zswl.common.widget.tablayout.TabLayout;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseViewPagerActivity extends BackActivity {
    private TabLayout tabLayout;
    protected ViewPager viewPager;
    TextView tvTitle;
    private ArrayList<Class> fragments;
    protected ViewPagerAdapter adapter = null;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_view_pager_layout;
    }

    @Override
    protected void init() {
        super.init();
        tabLayout = findViewById(R.id.tl);
        viewPager = findViewById(R.id.vp);
        tvTitle = findViewById(R.id.tv_action_bar_title);
        if (tvTitle != null)
            tvTitle.setText(getTitleName());
        fragments = new ArrayList();
        getFragments(fragments);
        if (getViewPagerAdapter() == null) {
            adapter = new ViewPagerAdapter(getSupportFragmentManager(), fragments, getTitles());
        } else {
            adapter = getViewPagerAdapter();
        }
        if (adapter.getCount() > 4) {
            tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        }
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setNeedSwitchAnimation(true);
        tabLayout.setIndicatorWidthWrapContent(true);
    }

    /**
     * 获取actionbar 名字
     *
     * @return
     */
    public abstract String getTitleName();

    /**
     * 获取viewpager标题
     *
     * @return
     */
    public abstract String[] getTitles();

    protected ViewPagerAdapter getViewPagerAdapter() {
        return null;
    }

    public abstract void getFragments(List<Class> fragments);

}
