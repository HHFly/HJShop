package com.mark.app.hjshop4a.uinew.bindinfo;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.common.utils.ActivityJumpUtils;
import com.mark.app.hjshop4a.common.utils.JsonUtils;
import com.mark.app.hjshop4a.common.utils.ToastUtils;
import com.mark.app.hjshop4a.data.entity.RainbowResultEntity;
import com.mark.app.hjshop4a.data.help.RainbowObserver;
import com.mark.app.hjshop4a.ui.dialog.factory.FunctionDialogFactory;
import com.mark.app.hjshop4a.uinew.dialog.OneEtParamDialog;
import com.mark.app.hjshop4a.uinew.order.OrderInfo;
import com.mark.app.hjshop4a.uinew.userinfo.model.BindStatus;
import com.mark.app.hjshop4a.uinew.userinfo.model.UserCardInfo;
import com.mark.app.hjshop4a.uinew.userinfo.model.UserInfo;
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
    OneEtParamDialog dialogQQ,dialogWeixin;
    QqWechatParam qqWechatParam =new QqWechatParam();
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
        dialogQQ =OneEtParamDialog.getInstance(R.string.请输入您的QQ账号,R.string.绑定您的QQ账号);
        dialogQQ.setOnDialogClickListener(new OneEtParamDialog.OnDialogClickListener() {
            @Override
            public void onClickCancel(OneEtParamDialog dialog) {
                dialogQQ.dismiss();
            }

            @Override
            public void onClickCommit(OneEtParamDialog dialog, String data) {
                updateUserInfo(data,0);

            }
        });
        dialogWeixin =OneEtParamDialog.getInstance(R.string.请输入您的微信号,R.string.绑定您的微信号);
        dialogWeixin.setOnDialogClickListener(new OneEtParamDialog.OnDialogClickListener() {
            @Override
            public void onClickCancel(OneEtParamDialog dialog) {
                dialogWeixin.dismiss();
            }

            @Override
            public void onClickCommit(OneEtParamDialog dialog, String data) {
                updateUserInfo(data,1);
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
                ActivityJumpUtils.actID(this);

                break;
            case R.id.Bind_bank:
                ActivityJumpUtils.actBankCard(this);
                break;
            case R.id.Bind_qq:
            dialogQQ.show(getFragmentManager());
                break;
            case R.id.Bind_wechat:
            dialogWeixin.show(getFragmentManager());
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
                            BindStatus data = JsonUtils.fromJson(obj.getResult(),BindStatus.class);
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
                        requestUserInfo();
                    }

                });
    }
//修改联系方式
    private void updateUserInfo(String data, final int type){
        switch (type){
            case 0:
                qqWechatParam.setQq(data);
                break;
            case 1:
                qqWechatParam.setWechat(data);
                break;
        }
        App.getServiceManager().getmService().updateUserInfo(qqWechatParam.toPswJson())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RainbowObserver() {


                    @Override
                    public void onSuccess(RainbowResultEntity obj) {
                        ToastUtils.show(R.string.修改成功);
                        switch (type){
                            case 0:
                              dialogQQ.dismiss();
                                break;
                            case 1:
                              dialogWeixin.dismiss();
                                break;
                        }
                    }
                });
    }

    //获取联系方式

    private void requestUserInfo(){

        App.getServiceManager().getmService().getUserInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RainbowObserver<UserInfo>() {


                    @Override
                    public void onSuccess(RainbowResultEntity<UserInfo> obj) {
                        qqWechatParam =JsonUtils.fromJson(obj.getResult(), QqWechatParam.class);
                        dialogQQ.setEtValue(qqWechatParam.getQq());
                        dialogWeixin.setEtValue(qqWechatParam.getWechat());
                    }
                });
    }
}
