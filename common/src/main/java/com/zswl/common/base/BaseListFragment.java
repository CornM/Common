package com.zswl.common.base;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.footer.LoadingView;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;
import com.zswl.common.R;
import com.zswl.common.R2;
import com.zswl.common.util.RxUtil;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import butterknife.BindView;
import io.reactivex.Observable;

public abstract class BaseListFragment<B extends BaseBean, A extends BaseRecycleViewAdapter<B>> extends BaseFragment {
    @BindView(R2.id.rv)
    protected RecyclerView recyclerView;
    @BindView(R2.id.refreshLayout)
    protected TwinklingRefreshLayout refreshLayout;
    protected A adapter;
    private int page;
    protected int limit = 10;
    protected BaseObserver<List<B>> observer;//加载数据回调
    protected boolean isLoadData = true;
    protected boolean isRefresh = true;

    @Override
    protected void init(View view) {
        initAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(getLayoutManager());
        SinaRefreshView sinaRefreshView = new SinaRefreshView(context);
        refreshLayout.setHeaderView(sinaRefreshView);
        refreshLayout.setBottomView(new LoadingView(context));
        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                isRefresh = true;
                page = 0;
                getListData(page);
            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                isRefresh = false;
                page = page + limit;
                getListData(page);
            }
        });

        observer = new BaseObserver<List<B>>(context, false) {
            @Override
            public void receiveResult(List<B> result) {
                if (!isRefresh) {
                    adapter.addData(result);
                    refreshLayout.finishLoadmore();
                } else {
                    adapter.refreshData(result);
                    refreshLayout.finishRefreshing();
                }
                finishLoadData();

            }
        };


        if (isLoadData)
            getListData(page);
    }

    /**
     * 初始化适配器
     */
    private void initAdapter() {
        Type type = getClass().getGenericSuperclass();
        Class<A> clazz = (Class<A>) ((ParameterizedType) type).getActualTypeArguments()[1];
        try {
            Constructor constructor = clazz.getDeclaredConstructor(Context.class, int.class);
            adapter = (A) constructor.newInstance(context, getItemLayout());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (Fragment.InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected int getViewId() {
        return R.layout.refresh_load_view;
    }

    /**
     * 列表布局
     *
     * @return
     */
    protected abstract int getItemLayout();

    /**
     * 数据刷新或者加载完之后调用此方法
     */
    public void finishLoadData() {

    }

    /**
     * 获取列表数据
     *
     * @param page
     */
    protected void getListData(int page) {
        if (getApi(page) == null) {
            return;
        }
        getApi(page).compose(RxUtil.io_main(lifeSubject))
                .subscribe(observer);
    }


    /**
     * 获取api接口
     *
     * @return
     */

    protected abstract Observable<HttpResult<List<B>>> getApi(int page);

    /**
     * 列表布局管理器
     *
     * @return
     */
    protected RecyclerView.LayoutManager getLayoutManager() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        return layoutManager;
    }


}
