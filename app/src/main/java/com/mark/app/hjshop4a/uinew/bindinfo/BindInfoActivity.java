package com.mark.app.hjshop4a.uinew.bindinfo;

import android.support.annotation.NonNull;
import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.data.entity.RainbowResultEntity;
import com.mark.app.hjshop4a.data.help.RainbowObserver;
import com.mark.app.hjshop4a.uinew.order.OrderInfo;
import com.mark.app.hjshop4a.uinew.userinfo.model.BindStatus;
import com.mark.app.hjshop4a.widget.BandInfoItemView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class BindInfoActivity extends BaseActivity {
    SmartRefreshLayout mRefreshLayout;//刷新框架
    BandInfoItemView Bind_id,Bind_bank,Bind_qq,Bind_wechat,Bind_taobao;
    @Override
    public int getContentViewResId() {
        return R.layout.activity_bindinfo;
    }

    @Override
    public void findView() {
        Bind_id =getView(R.id.Bind_id);
        Bind_bank =getView(R.id.Bind_bank);
        Bind_qq =getView(R.id.Bind_qq);
        Bind_wechat =getView(R.id.Bind_wechat);
        Bind_taobao =getView(R.id.Bind_taobao);
        mRefreshLayout =getView(R.id.refreshLayout);
        mRefreshLayout.setEnableLoadMore(false);
        mRefreshLayout.setEnableFooterTranslationContent(true);//是否上拉Footer的时候向上平移列表或者内容
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                requestData();
            }
        });
    }

    @Override
    public void setListener() {
        setClickListener(R.id.Bind_id);
        setClickListener(R.id.Bind_bank);
        setClickListener(R.id.Bind_qq);
        setClickListener(R.id.Bind_wechat);
        setClickListener(R.id.Bind_taobao);
        setClickListener(R.id.titlebar_iv_return);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Bind_id:
                break;
            case R.id.Bind_bank:
                break;
            case R.id.Bind_qq:
                break;
            case R.id.Bind_wechat:
                break;
            case R.id.Bind_taobao:
                break;
            case R.id.titlebar_iv_return:
                finish();
                break;

        }
    }

    @Override
    public void initView() {
        setTvText(R.id.titlebar_tv_title, R.string.账号绑定);
        requestData();
    }
    //请求数据
    private void requestData() {
        App.getServiceManager().getmService()
                .getBindStatus()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RainbowObserver<BindStatus>() {
                    @Override
                    public void onSuccess(RainbowResultEntity<BindStatus> obj) {
                            BindStatus data =obj.getObj();
                            switch (data.getIdCardStatus()){
                                case BindIDType.unupload:
                                        Bind_id.setStatus(getString(R.string.待上传));
                                    break;
                                case BindIDType.WAITCONFIRM:
                                    Bind_id.setStatus(getString(R.string.待审核));
                                    break;
                                case BindIDType.Verified:
                                    Bind_id.setStatus(getString(R.string.审核通过));
                                    break;
                                case BindIDType.FailureAudit:
                                    Bind_id.setStatus(getString(R.string.审核失败));
                                    break;
                            }
                        Bind_bank.setStatus(data.getBankStatus()==0?getString(R.string.未绑定):getString(R.string.已綁定));
                        Bind_qq.setStatus(data.getQqStatus()==0?getString(R.string.未绑定):getString(R.string.已綁定));
                        Bind_wechat.setStatus(data.getWechatStatus()==0?getString(R.string.未绑定):getString(R.string.已綁定));
                        Bind_taobao.setStatus(data.getAccountStatus()==0?getString(R.string.未绑定):getString(R.string.已綁定));
                    }

                });
    }
}
