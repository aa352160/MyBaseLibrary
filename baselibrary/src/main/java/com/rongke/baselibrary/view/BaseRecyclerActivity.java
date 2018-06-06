package com.rongke.baselibrary.view;

import com.rongke.baselibrary.R;
import com.rongke.baselibrary.base.BaseActivity;
import com.rongke.baselibrary.base.BasePresenter;
import com.rongke.baselibrary.base.view.BaseRecyclerView;
import com.rongke.baselibrary.util.AutoUtils;
import com.rongke.baselibrary.view.recycler.footer.ClassicLoadMoreView;
import com.rongke.baselibrary.view.recycler.header.ClassicsRefreshHeader;
import com.yan.pullrefreshlayout.PullRefreshLayout;

/**
 * Created by jh352160 on 2018/4/20.
 */
public abstract class BaseRecyclerActivity<E extends BasePresenter> extends BaseActivity<E> {
    protected BaseRecyclerView targetRecycler;
    private PullRefreshLayout refreshLayout;

    protected int page = 1;

    public abstract void onRefresh();
    protected abstract void onLoadMore(int page);
    /* 初始化列表，设置LayoutManager与Adapter */
    protected abstract void initRecyclerView(BaseRecyclerView recyclerView);
    /* 初始化Activity中与列表无关的控件，如标题等 */
    protected abstract void initActivityView();

    @Override
    public int setLayoutRes() {
        return R.layout.activity_base_recycler;
    }

    @Override
    public void initView() {
        initActivityView();
        targetRecycler = findViewById(R.id.recycler_view);
        refreshLayout = findViewById(R.id.refresh_layout);

        initRecyclerView(targetRecycler);
        refreshLayout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override public void onRefresh() { refresh(); }
            @Override public void onLoading() { loadMore(); }
        });
        refreshLayout.setTargetView(targetRecycler);
        initData();
    }

    void initData() {
        refreshLayout.autoRefresh();
//        refresh();
    }

    private void refresh() {
        page = 1;
        onRefresh();
    }

    private void loadMore() {
        onLoadMore(page);
        page++;
    }

    public void loadMoreComplete() { refreshLayout.loadMoreComplete(); }
    public void refreshComplete() { refreshLayout.refreshComplete(); }
    protected void startRefresh(){ refreshLayout.autoRefresh(); }
    protected void startLoadMore(){ refreshLayout.autoLoading(); }
    protected void setLoadMoreEnable(boolean isEnable){
        if (isEnable){
            ClassicLoadMoreView classicLoadMoreView = new ClassicLoadMoreView(getApplicationContext(),refreshLayout);
            refreshLayout.setFooterView(classicLoadMoreView);
            refreshLayout.setLoadTriggerDistance(AutoUtils.getDisplayHeightValue(150));
        }
    }
    protected void setRefreshEnable(boolean isEnable){
        if (isEnable){
            ClassicsRefreshHeader header = new ClassicsRefreshHeader(getApplicationContext());
            AutoUtils.auto(header);
            refreshLayout.setHeaderView(header);
            refreshLayout.setRefreshTriggerDistance(AutoUtils.getDisplayHeightValue(150));
        }
    }
}
