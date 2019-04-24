package com.mark.app.hjshop4a.common.utils;

import com.mark.app.hjshop4a.base.model.PagingBaseModel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;

/**
 * 下拉加载工具类
 * Created by lenovo on 2017/9/11.
 */

public class RefreshLayoutUtils {
    /**
     * 加载更多
     *
     * @param view
     * @param data
     * @param listener
     */
    public static void loadMore(final RefreshLayout view, final PagingBaseModel data, final OnLoadMoreListener listener) {
        if (data != null) {
            data.loadMore(new PagingBaseModel.PagingListener() {
                @Override
                public void lastPage() {
                    RefreshLayoutUtils.finish(view, data);
                }

                @Override
                public void loadMore(int nextPage) {
                    if (listener != null) {
                        listener.onLoadMore(nextPage,data.getTimestamp());
                    }
                }
            });
        } else {
            RefreshLayoutUtils.finish(view, data);
        }
    }

    /**
     * 完成刷新和加载
     *
     * @param view
     */
    public static void finish(RefreshLayout view) {
        finish(view, true);
    }

    /**
     * 完成刷新和加载
     *
     * @param view
     */
    public static void finish(RefreshLayout view, PagingBaseModel data) {
        boolean hasMore = false;
        if (data != null) {
            hasMore = !data.isLastPage();
        }
        finish(view, hasMore);
    }

    /**
     * 完成刷新和加载
     *
     * @param view
     */
    private static void finish(RefreshLayout view, boolean hasMore) {
        if (view != null) {
//            view.setLoadmoreFinished(false);
            view.setNoMoreData(false);
            if (view.getState() == RefreshState.Refreshing) {
                finishRefresh(view);
                if (hasMore) {
                    view.setNoMoreData(false);
                }
            } else if (view.getState() == RefreshState.Loading) {
                finishLoadmore(view, hasMore);
            }
        }
    }

    /**
     * 刷新完成
     *
     * @param view
     */
    private static void finishRefresh(RefreshLayout view) {
        if (view != null && view.getState() == RefreshState.Refreshing) {
            view.finishRefresh();
        }
    }

    /**
     * 加载完成
     *
     * @param view
     * @param hasMore
     */
    private static void finishLoadmore(RefreshLayout view, boolean hasMore) {
        if (view != null && view.getState() == RefreshState.Loading) {
//            view.finishLoadmore(true);
//            view.setLoadmoreFinished(!hasMore);
            view.finishLoadMore();
            view.setNoMoreData(!hasMore);//1.0.5 以上
        }
    }

    /**
     * 加载下一页监听
     */
    public interface OnLoadMoreListener {
        /**
         * 加载更多
         *
         * @param nextPage
         */
        void onLoadMore(int nextPage,long timestamp);
    }
}
