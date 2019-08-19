package com.zswl.common.base;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2018/4/17 0017.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private List<Class> fragments;
    private String[] titles;
    private Class singleClass;
    private DealFragment dealFragment;
    public ViewPagerAdapter(FragmentManager fm, List<Class> fragments, String[] titles) {
        super(fm);
        this.fragments = fragments;
        this.titles = titles;
    }

    public ViewPagerAdapter(FragmentManager fm, Class singleClass, String[] titles) {
        super(fm);
        this.singleClass = singleClass;
        this.titles = titles;
    }

    public ViewPagerAdapter(FragmentManager fm, Class singleClass) {
        super(fm);
        this.singleClass = singleClass;
    }

    public void setTitles(String[] titles) {
        this.titles = titles;
    }

    public void setDealFragment(DealFragment dealFragment) {
        this.dealFragment = dealFragment;
    }

    @Override
    public int getCount() {
        return titles == null ? 0 : titles.length;
    }

    @Override
    public Fragment getItem(int position) {
        Class clazz = null;
        if (singleClass == null) {
            clazz = fragments.get(position);
        } else {
            clazz = singleClass;
        }
        Fragment fragment = null;
        try {
            fragment = (Fragment) clazz.newInstance();
            if (dealFragment!=null){
                dealFragment.onDeal(fragment,position);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
    }

    /**
     * 处理生成Fragment
     */
    public interface DealFragment {
        void onDeal(Fragment fragment, int position);
    }
}
