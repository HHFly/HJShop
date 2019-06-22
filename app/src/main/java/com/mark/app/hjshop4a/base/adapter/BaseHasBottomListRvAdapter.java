package com.mark.app.hjshop4a.base.adapter;

import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public  abstract class BaseHasBottomListRvAdapter<TopData, BodyData> extends BaseRvAdapter  {
    protected final int VIEW_TYPE_TOP = 0;
    protected final int VIEW_TYPE_BODY = 1;
    /**
     * 顶部数据源
     */
    private List<TopData> mTopData;
    /**
     * 数据源
     */
    private BodyData mBodyData;

    public List<TopData> getmTopData() {
        return mTopData;
    }

    public BodyData getmBodyData() {
        return mBodyData;
    }

    public BaseHasBottomListRvAdapter() {
        this(null, null);
    }

    public BaseHasBottomListRvAdapter(List<TopData> topData) {
        this( topData, null);
    }

    public BaseHasBottomListRvAdapter(BodyData bodyData) {
        this(null, bodyData);
    }
    public BaseHasBottomListRvAdapter(ArrayList<TopData> topData, BodyData bodyData) {
        this.mTopData =  new ArrayList<>();
        this.mBodyData =bodyData;
        if (topData != null) {
            mTopData.addAll(topData);
        }
    }
    public BaseHasBottomListRvAdapter( List<TopData> topData,BodyData bodyData) {
        this.mTopData = new ArrayList<>();
        this.mBodyData = bodyData;
        if (topData != null) {
            mTopData.addAll(topData);
        }
    }

    @Override
    public AutoViewHolder customOnCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case VIEW_TYPE_TOP: {
                view = inflater(parent, getTopItemResId());
                break;
            }
            default:
            case VIEW_TYPE_BODY: {
                view = inflater(parent, getBodyItemResId());
                break;
            }
        }
        return new AutoViewHolder(view);
    }
    /**
     * 获取顶部Item资源文件
     *
     * @return
     */
    @LayoutRes
    public abstract int getTopItemResId();

    /**
     * 获取列表数据Item资源文件
     *
     * @return
     */
    @LayoutRes
    public abstract int getBodyItemResId();

    @Override
    public int getItemViewType(int position) {
        if (position < getTopItemCount()) {
            return VIEW_TYPE_TOP;
        }
        return VIEW_TYPE_BODY;
    }
    @Override
    public int getItemCount() {
        return getTopItemCount() + getBodyItemCount();
    }

    @Override
    public void customBindView(AutoViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case VIEW_TYPE_TOP: {
                TopData topData = getDataItem(position);
                if (topData != null) {
                    bindTopData(holder, position, topData);
                }
                break;
            }
            default:
            case VIEW_TYPE_BODY: {
//                int bodyPos = position - getTopItemCount();
                BodyData bodyData = getmBodyData();
                if (bodyData != null) {
                    bindBodyData(holder, position, bodyData);
                }
                break;
            }
        }
    }


    /**
     * 获取顶部Item数量
     *
     * @return
     */
    public int getTopItemCount() {
        if (getmTopData() == null) {
            return 0;
        }
        return getmTopData().size();

    }

    /**
     * 获取列表数据Item数量
     *
     * @return
     */
    public int getBodyItemCount() {
        if (getTopItemResId() == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * 绑定顶部数据
     *
     * @param holder
     */
    public abstract void bindTopData(AutoViewHolder holder, int topPos, TopData data);

    /**
     * 绑定列表数据
     *
     * @param holder
     * @param bodyPosition 列表索引
     */
    public abstract void bindBodyData(AutoViewHolder holder, int bodyPosition, BodyData data);
    /**
     * 刷新顶部数据
     *
     * @param data
     */
    public void notifyData(List<TopData> data) {
        mTopData = data;
        notifyDataSetChanged();
    }
    /**
     * 刷新数据
     *
     * @param data
     */
    public void notifyData(List<TopData> data, boolean isRefresh) {
        if (isRefresh) {
            refresh(data);
        } else {
            loadMore(data);
        }
    }
    /**
     * 刷新数据
     *
     * @param topData   头部数据
     * @param bodyData  列表数据
     * @param isRefresh 分页数据是刷新还是加载更多
     */
    public void notifyData(List<TopData> topData, BodyData bodyData, boolean isRefresh) {
        notifyData(topData, bodyData, isRefresh, true);
    }
    public void notifyData(ArrayList<TopData> topData, BodyData bodyData, boolean isRefresh) {
        notifyData(topData, bodyData, isRefresh, true);
    }
    /**
     * 刷新数据
     *
     * @param topData     头部数据
     * @param bodyData    列表数据
     * @param isRefresh   分页数据是刷新还是加载更多
     * @param isNotifyTop 是否刷新头部数据
     */
    public void notifyData(List<TopData>topData,BodyData bodyData, boolean isRefresh, boolean isNotifyTop) {
        if (isNotifyTop) {
            mTopData = topData;
        }
        notifyData(topData, isRefresh);
    }
    /**
     * 刷新数据
     *
     * @param data
     */
    public void refresh(List<TopData> data) {
        resetBodyData();
        if (data != null) {
            getmTopData().addAll(data);
        }
        notifyDataSetChanged();
    }
    /**
     * 加载更多
     *
     * @param data
     */
    public void loadMore(List<TopData> data) {
        if (mTopData == null) {
            mTopData = new ArrayList<>();
        }
        if (data != null) {
            mTopData.addAll(data);
        }
        notifyDataSetChanged();
    }
    /**
     * 重置数据源
     */
    private void resetBodyData() {
        if (mTopData == null) {
            mTopData = new ArrayList<>();
        } else {
            mTopData.clear();
        }
    }


    /**
     * 获取Item数据
     *
     * @param position
     * @return
     */
    public TopData getDataItem(int position) {
        if (getmTopData() == null) {
            return null;
        }
        return getmTopData().get(position);
    }

}
