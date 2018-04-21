package com.mark.app.hjshop4a.ui.homepager.start;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;


import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.common.utils.ActAnimationUtils;
import com.mark.app.hjshop4a.common.utils.ActivityJumpUtils;
import com.mark.app.hjshop4a.common.utils.StatusBarUtil;
import com.mark.app.hjshop4a.model.launch.GuidePage;
import com.mark.app.hjshop4a.ui.homepager.activity.HomePagerActivity;
import com.mark.app.hjshop4a.ui.homepager.start.adapter.GuideVpAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 引导页
 */
public class GuideActivity extends BaseActivity {
    private String mJsonData;

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
    }

    @Override
    public void setListener() {

    }

    @Override
    public void initView() {
        StatusBarUtil.immersive(this);
//        List<GuidePage> data = JsonUtils.getList(new JsonParser().parse(mJsonData), GuidePage.class);
        List<GuidePage> data = new ArrayList<>();
//        data.add(new GuidePage(R.mipmap.ic_start));
        data.add(new GuidePage(R.mipmap.ic_guide_2));
        data.add(new GuidePage(R.mipmap.ic_guide_3));
        data.add(new GuidePage(R.mipmap.ic_guide_4));
        bindData(data);
    }

    @Override
    public void onClick(View v) {

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
                goHome();
            }
        });
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setCurrentItem(0);
    }

    /**
     * 回到首页
     */
    private void goHome() {
        ActivityJumpUtils.actActivity(getActivity(), HomePagerActivity.class);
        finish();
        ActAnimationUtils.actAlphaOut(getActivity());
    }
}
