package com.mark.app.hjshop4a.uinew.homepager.fragment;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.base.fragment.BaseFragment;
import com.mark.app.hjshop4a.common.utils.JsonUtils;
import com.mark.app.hjshop4a.data.entity.RainbowResultEntity;
import com.mark.app.hjshop4a.data.help.RainbowObserver;
import com.mark.app.hjshop4a.uinew.orderList.OrderPage;
import com.mark.app.hjshop4a.uinew.orderList.PageParam;
import com.mark.app.hjshop4a.uinew.orderList.ShowOrder;
import com.mark.app.hjshop4a.uinew.orderList.StatusOrderFragment;
import com.mark.app.hjshop4a.uinew.orderList.TabAdapter;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class StatusFragment extends BaseFragment {
    private SmartTabLayout viewpagertab;
    private ViewPager viewpager;
    StatusOrderFragment current;
    FragmentPagerItems pages;
    FragmentPagerItemAdapter adapter;
    @Override
    public int getContentResId() {
        return R.layout.fragment_status;
    }

    @Override
    public void findView() {
        viewpagertab = getView(R.id.smart_tab);
        viewpager = getView(R.id.viewpager);
    }

    @Override
    public void setListener() {

    }

    @Override
    public void initView() {
        setTvText(R.id.titlebar_tv_title,"已接任务");
//        setIvImage(R.id.bg_img,R.mipmap.home);
        setViewVisibilityGone(R.id.titlebar_iv_return,false);
        pages = new FragmentPagerItems(App.get());
        requestData();
    }
    /**
     * 请求数据
     */
    public void requestData( ) {

        PageParam pageParam = new PageParam();
        pageParam.setOrderStatus(-1);
        pageParam.setPageNo(1);
        App.getServiceManager().getmService()
                .getOrderList(pageParam.toPswJson())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RainbowObserver<OrderPage>() {


                    @Override
                    public void onSuccess(RainbowResultEntity<OrderPage> obj) {
                        OrderPage mData = JsonUtils.fromJson(obj.getResult(), OrderPage.class);
                        if(mData!=null) {

                            initTab(mData);
                        }else {
                            mData=new OrderPage();
                            List<ShowOrder> showOrders=new ArrayList<>();
                            mData.setOrders(showOrders);
                            initTab(mData);
                        }
                    }

                    @Override
                    public void onUnSuccessFinish() {

                    }

                    @Override
                    public void onAllFinish() {



                    }
                });
    }
    public  void initTab(OrderPage data){
        //使用工具类创建FragmentAdapter

//        String[] strs=new String[]{"哈哈","哈哈啊","断剑重铸之日","额","那就算了","嗯哼哼?"};
//
//        for (String str : strs) {
//            //将标题和fragment绑定,并添加
//            pages.add(FragmentPagerItem.of(str, ShopCarFragment.class));
//        }
        initData(data);
         adapter = new FragmentPagerItemAdapter(App.get().getHomePagerActivity().getSupportFragmentManager(),pages);

        //关联viewpager&adapter&TabLayout
        viewpager.setAdapter(adapter);
        viewpagertab.setViewPager(viewpager);

        //添加监听器时应该使用Tab来监听

        viewpagertab.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                current = (StatusOrderFragment) adapter.getPage(position);
                if(position==0){
                    current.setStatus(-1);
                }else {
                    current.setStatus(position+1);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        current = (StatusOrderFragment) adapter.getPage(0);
        current.setStatus(-1);
    }

    public void  initData(OrderPage data){
        pages.clear();
        pages.add(FragmentPagerItem.of(String.format(getString(R.string.全部),data.getAll()), StatusOrderFragment.class));
        pages.add(FragmentPagerItem.of(String.format(getString(R.string.进行中),data.getProcessing()), StatusOrderFragment.class));
        pages.add(FragmentPagerItem.of(String.format(getString(R.string.待发货),data.getWaitDelivery()), StatusOrderFragment.class));
        pages.add(FragmentPagerItem.of(String.format(getString(R.string.待评价),data.getWaitEvaluation()), StatusOrderFragment.class));
        pages.add(FragmentPagerItem.of(String.format(getString(R.string.待返款),data.getWaitReturnMoney()), StatusOrderFragment.class));
        pages.add(FragmentPagerItem.of(String.format(getString(R.string.已完成),data.getComplete()), StatusOrderFragment.class));
        pages.add(FragmentPagerItem.of(String.format(getString(R.string.失败订单),data.getClose()), StatusOrderFragment.class));
        if(null!=adapter){
            adapter.notifyDataSetChanged();
            viewpagertab.setViewPager(viewpager);
        }
    }
    @Override
    public void onClick(View v) {

    }
}
