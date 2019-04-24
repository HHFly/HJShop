package com.mark.app.hjshop4a.ui.start;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.common.utils.ActAnimationUtils;
import com.mark.app.hjshop4a.common.utils.ActivityJumpUtils;
import com.mark.app.hjshop4a.common.utils.StatusBarUtil;
import com.mark.app.hjshop4a.model.launch.GuidePage;
import com.mark.app.hjshop4a.ui.homepager.activity.HomePagerActivity;
import com.mark.app.hjshop4a.ui.start.adapter.GuideVpAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 引导页
 */
public class GuideActivity extends BaseActivity implements ViewPager.OnPageChangeListener {
    private String mJsonData;
private List<GuidePage> data;
    // 点的组
    private ViewGroup llPointGroup;
    // 点之间的宽度
    private int pWidth;
    // 选中的点view对象
    private View mSelectPointView;
    @Override
    public int getContentViewResId() {
        return R.layout.activity_guide;
    }

    @Override
    public void getIntentParam(Bundle bundle) {
        if (bundle != null) {
//            mJsonData = bundle.getString(BundleKey.JSON);
        }
    }

    @Override
    public void findView() {
        llPointGroup = (ViewGroup) findViewById(R.id.ll_guide_point_group);
        mSelectPointView = findViewById(R.id.select_point);
        //获取LayoutInflater

    }

    @Override
    public void setListener() {
            setClickListener(R.id.button);
    }

    @Override
    public void initView() {
        StatusBarUtil.immersive(this);
        setViewSelected(R.id.select_point1,true);
//        List<GuidePage> data = JsonUtils.getList(new JsonParser().parse(mJsonData), GuidePage.class);
        data = new ArrayList<>();
//        data.add(new GuidePage(R.mipmap.ic_start));
        data.add(new GuidePage(R.mipmap.ic_guide_2));
        data.add(new GuidePage(R.mipmap.ic_guide_3));
        data.add(new GuidePage(R.mipmap.ic_guide_4));

        LayoutParams params; // 参数类
        LayoutInflater inflater = LayoutInflater.from(llPointGroup.getContext());
        for (int i = 0; i < data.size(); i++) {

            // 根据图片的个数, 每循环一次向LinearLayout中添加一个点
         ImageView   view = (ImageView) inflater.inflate(R.layout.view_circle, llPointGroup, false);


            llPointGroup.addView(view);
        }
        bindData(data);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.button:
                goHome();
                break;
        }
    }

    /**
     * 设置引导页
     *
     * @param data
     */
    private void bindData(List<GuidePage> data) {
        if (data == null) {
            return;
        }
        ViewPager viewPager = getView(R.id.guide_vp);
        GuideVpAdapter adapter = new GuideVpAdapter(data);
        adapter.setOnItemClickListener(new GuideVpAdapter.OnItemClickListener() {
            @Override
            public void onClickLastPage(int position) {
//                goHome();

            }
        });
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setCurrentItem(0);
        viewPager.setOnPageChangeListener(this);
    }

    /**
     * 回到首页
     */
    private void goHome() {
        ActivityJumpUtils.actActivity(getActivity(), HomePagerActivity.class);
        finish();
        ActAnimationUtils.actAlphaOut(getActivity());
    }
    /*
      * 当页面正在滚动时 position 当前选中的是哪个页面 positionOffset 比例 positionOffsetPixels 偏移像素
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        //获取两个点间的距离,获取一次即可
        if(pWidth==0) {
            pWidth = llPointGroup.getChildAt(1).getLeft()
                    - llPointGroup.getChildAt(0).getLeft();
        }

        // 获取点要移动的距离
        int leftMargin = (int) (pWidth * (position + positionOffset));
        // 给红点设置参数
        RelativeLayout.LayoutParams params = (android.widget.RelativeLayout.LayoutParams) mSelectPointView
                .getLayoutParams();
        params.leftMargin = leftMargin;
        mSelectPointView.setLayoutParams(params);
    }

    /**
     * 当页面被选中
     */
    @Override
    public void onPageSelected(int position) {
        if(position==0){
            setViewSelected(R.id.select_point2,false);
            setViewSelected(R.id.select_point1,true);
        }
        if(position==1){
            setViewSelected(R.id.select_point2,true);
            setViewSelected(R.id.select_point1,false);
        }
        // 显示体验按钮
        if (position == data.size() - 1) {
            setViewVisibility(R.id.rl_point,false);
            setViewVisibility(R.id.button,true);
        } else {
            setViewVisibility(R.id.button,false);// 隐藏
            setViewVisibility(R.id.rl_point,true);
        }
    }

    /**
     * 当页面滚动状态改变
     */
    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
