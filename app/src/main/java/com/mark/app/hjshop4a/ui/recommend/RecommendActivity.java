package com.mark.app.hjshop4a.ui.recommend;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.ui.recommend.model.Redata;
import com.mark.app.hjshop4a.ui.recommend.model.ZXingCode;

import java.util.ArrayList;

/**
 * Created by pc on 2018/4/16.
 */

public class RecommendActivity extends BaseActivity {
    private  RecommendAdapter recommendAdapter;
    private ArrayList<Redata> redata =new ArrayList<>();
    private ZXingCode zXingCode =new ZXingCode();
    @Override
    public int getContentViewResId() {
        return R.layout.activity_title_right_base_rvlist;
    }

    @Override
    public void initView() {
        setTvText(R.id.titlebar_tv_title,"我的推荐");
        setTvText(R.id.titlebar_tv_right,"分享");
        zXingCode.setCode("测试");
        Redata re =new Redata();
        re.setName("mark");
        re.setDate("2018-01-03 19：22");
        redata.add(re);
       initAdapter();
    }

    private void initAdapter() {
        if(recommendAdapter ==null){
            RecyclerView rv = getView(R.id.recyclerView);
            recommendAdapter = new RecommendAdapter(zXingCode, redata);
            rv.setLayoutManager(new LinearLayoutManager(this));
            rv.setAdapter(recommendAdapter);
        }
        else {
            recommendAdapter.notifyData(redata,true);
        }
    }

    @Override
    public void setListener() {
        setClickListener(R.id.titlebar_iv_return);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.titlebar_iv_return:
                finish();
                break;
        }
    }
}
