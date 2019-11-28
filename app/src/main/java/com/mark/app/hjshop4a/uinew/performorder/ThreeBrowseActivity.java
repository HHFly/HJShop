package com.mark.app.hjshop4a.uinew.performorder;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.dd.CircularProgressButton;
import com.mark.app.hjshop4a.BuildConfig;
import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.common.androidenum.other.BundleKey;
import com.mark.app.hjshop4a.common.listener.DefOnUploadPicListener;
import com.mark.app.hjshop4a.common.utils.ActivityJumpUtils;
import com.mark.app.hjshop4a.common.utils.JsonUtils;
import com.mark.app.hjshop4a.common.utils.TakePhoneUtil;
import com.mark.app.hjshop4a.common.utils.ToastUtils;
import com.mark.app.hjshop4a.data.entity.RainbowResultEntity;
import com.mark.app.hjshop4a.data.help.RainbowObserver;
import com.mark.app.hjshop4a.ui.dialog.factory.FunctionDialogFactory;
import com.mark.app.hjshop4a.uinew.dialog.CloseOrderDialog;
import com.mark.app.hjshop4a.uinew.dialog.YesNoDialog;
import com.mark.app.hjshop4a.uinew.performorder.adapter.ThreeBrowseAdapter;
import com.mark.app.hjshop4a.uinew.performorder.adapter.TwoSearchAdapter;
import com.mark.app.hjshop4a.uinew.performorder.model.AddProduct;
import com.mark.app.hjshop4a.uinew.performorder.model.AddProductPic;
import com.mark.app.hjshop4a.uinew.performorder.model.CloseOrderParam;
import com.mark.app.hjshop4a.uinew.performorder.model.NextStepParam;
import com.mark.app.hjshop4a.uinew.performorder.model.PerformParam;
import com.mark.app.hjshop4a.uinew.performorder.model.StepThree;
import com.mark.app.hjshop4a.uinew.performorder.model.StepThreeParam;
import com.mark.app.hjshop4a.uinew.performorder.model.StepTwo;
import com.mark.app.hjshop4a.uinew.performorder.model.StepTwoParam;
import com.mark.app.hjshop4a.widget.UpdateStepLayout;
import com.mark.app.hjshop4a.widget.UpdateStepOneLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ThreeBrowseActivity extends BaseActivity {
    String subOrderSn;
    SmartRefreshLayout mRefreshLayout;//刷新框架
    StepThree data;
    ThreeBrowseAdapter threeBrowseAdapter;
    UpdateStepOneLayout updateStepTip;//
    private Map<Integer,String> pic =new HashMap<>();
    private int mapId;//0 主 4 搜索 1  2 3货比   5.。。。副宝贝
    private CloseOrderDialog closeOrderDialog;
    @Override
    public int getContentViewResId() {
        return R.layout.activity_perform;
    }

    @Override
    public void initView() {
        setTvText(R.id.titlebar_tv_title,"浏览评价");
        requestData();
    }
    @Override
    public void getIntentParam(Bundle bundle) {
        super.getIntentParam(bundle);
        subOrderSn =bundle.getString(BundleKey.ORDER_SN);
    }
    @Override
    public void setListener() {
        setClickListener(R.id.titlebar_iv_return);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.titlebar_iv_return:
                finish();
                break;
        }
    }
    @Override
    public void findView() {
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
    /**
     * 请求数据
     */
    private void requestData() {
//        showLoadingDialog();
        PerformParam performParam =new PerformParam();
        performParam.setStep(3);
        performParam.setSubOrderSn(subOrderSn);
        App.getServiceManager().getmService()
                .getOrderInfo(performParam.toPswJson())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RainbowObserver() {



                    @Override
                    public void onSuccess(RainbowResultEntity obj) {
                        data = JsonUtils.fromJson(obj.getResult(),StepThree.class);
                        //设置信息
                        initRvAdapter(data);
//                        twoSearchAdapter.startTime();
                    }

                    @Override
                    public void onAllFinish() {
                        super.onAllFinish();
                        mRefreshLayout.finishRefresh();
                    }
                });
    }
    private void initRvAdapter(final StepThree data){

        if(threeBrowseAdapter==null){
            RecyclerView rv = getView(R.id.recyclerView);
            threeBrowseAdapter = new ThreeBrowseAdapter(data);
            rv.setLayoutManager(new LinearLayoutManager(this));
            rv.setAdapter(threeBrowseAdapter);
            threeBrowseAdapter.setOnItemClickListener(new ThreeBrowseAdapter.OnItemClickListener() {
                @Override
                public void onClickNext() {
                    nextStep();
                }

                @Override
                public void onClickClose() {
                    showCloseOrderDialog();
                }



                @Override
                public void onClickHuobisanjiaPic1(UpdateStepOneLayout updateStepOneLayout) {
                    mapId=1;
                    updateStepTip= updateStepOneLayout;

                    FunctionDialogFactory.showTakePhoneDialog(getActivity());
                }

                @Override
                public void onClickHuobisanjiaPic2(UpdateStepOneLayout updateStepOneLayout) {
                    mapId=2;

                    updateStepTip= updateStepOneLayout;
                    FunctionDialogFactory.showTakePhoneDialog(getActivity());
                }

                @Override
                public void onClickHuobisanjiaPic3(UpdateStepOneLayout updateStepOneLayout) {
                    mapId=3;

                    updateStepTip= updateStepOneLayout;
                    FunctionDialogFactory.showTakePhoneDialog(getActivity());
                }




            });
            threeBrowseAdapter.startTime();
        }
        else {
            threeBrowseAdapter.setData(data);
            threeBrowseAdapter.notifyDataSetChanged();
        }
//        boolean isShowEmpty = isRefresh && (data == null || data.size() == 0);
//        setViewVisibility(R.id.empty_layout_empty, isShowEmpty);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        TakePhoneUtil.onActivityResult(getActivity(), requestCode, resultCode, data, new TakePhoneUtil.CallBack() {
            @Override
            public void back(Uri var1) {
                requestUpdateDataOfNewPic(var1);
            }


        });

    }

    /**
     * 请求上传图片
     *
     */
    private void requestUpdateDataOfNewPic(Uri uri) {
        showLoadingDialog();

        luban(uri, new DefOnUploadPicListener() {
            @Override
            public void onLoadPicFinish(String imgUrl) {
                super.onLoadPicFinish(imgUrl);
//                requestUpdateData(imgUrl);
                updateStepTip.setImg(imgUrl);
                pic.put(mapId,imgUrl);


                hideLoadingDialog();

            }

            @Override
            public void onLoadPicUnSuccessFinish() {
                super.onLoadPicUnSuccessFinish();
                ToastUtils.show("上传图片失败");
                hideLoadingDialog();

            }

        });

    }
    private void setpic(){
        String url ="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560415409646&di=4b4d5a87786acb4902a92ae2f4d64d89&imgtype=0&src=http%3A%2F%2Fimg009.hc360.cn%2Fg8%2FM08%2FEE%2F89%2FwKhQt1N9cmGEHKQQAAAAAN7_jII892.jpg";
        pic.put(1,url);
        pic.put(2,url);
        pic.put(3,url);
        pic.put(4,url);
        pic.put(0,url);
    }
    /**
     * 下一步
     */
    private void nextStep() {
        if(BuildConfig.DEBUG_ENV) {
            setpic();
        }
        if(!check()){return;}

        showLoadingDialog();
        NextStepParam nextStepParam =new NextStepParam();
        nextStepParam.setStep(3);
        nextStepParam.setSubOrderSn(subOrderSn);
        StepThreeParam stepThreeParam =new StepThreeParam();

        stepThreeParam.setEvaluationBrowsePic(pic.get(1));
        stepThreeParam.setWwChat(pic.get(2));
        stepThreeParam.setAskEveryone(pic.get(3));

        nextStepParam.setJsonData(stepThreeParam.toJson());
        App.getServiceManager().getmService()
                .nextStep(nextStepParam.toPswJson())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RainbowObserver() {



                    @Override
                    public void onSuccess(RainbowResultEntity obj) {
                        Boolean Sccess = JsonUtils.fromJson(obj.getResult(),Boolean.class);
                        if(Sccess){
                            ActivityJumpUtils.actStepFour(getActivity(),subOrderSn);

                        }else {
                            ToastUtils.show("失败！："+obj.getReason());
                        }
                    }

                    @Override
                    public void onAllFinish() {
                        super.onAllFinish();
                        hideLoadingDialog();
                    }
                });
    }

    private boolean check(){

        if(TextUtils.isEmpty(pic.get(1))){
            ToastUtils.show("请上传评价浏览");
            return false;
        }
        if(TextUtils.isEmpty(pic.get(2))){
            ToastUtils.show("请上传旺旺聊天");
            return false;
        }
        if(TextUtils.isEmpty(pic.get(3))){
            ToastUtils.show("请上传问大家浏览");
            return false;
        }

        return true;
    }
    /*
     * 显示性别选择*/
    private  void  showCloseOrderDialog(){
        if(closeOrderDialog ==null){
            closeOrderDialog =new CloseOrderDialog();
        }
        closeOrderDialog.setOnDialogClickListener(new CloseOrderDialog.OnDialogClickListener() {
            @Override
            public void onClickNo(CloseOrderDialog dialog) {

            }

            @Override
            public void onClickYes(int data) {
                closeOrder(data);
            }



        });
        closeOrderDialog.setContent(this.getActivity());
        closeOrderDialog.show(getFragmentManager());
    }
    private void closeOrder(int data){
        CloseOrderParam closeOrderParam =new CloseOrderParam();
        closeOrderParam.setSubOrderSn(subOrderSn);
        closeOrderParam.setFailReasonId(data);
        App.getServiceManager().getmService()
                .closeOrder(closeOrderParam.toPswJson())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RainbowObserver() {



                    @Override
                    public void onSuccess(RainbowResultEntity obj) {
                        Boolean Sccess = JsonUtils.fromJson(obj.getResult(),Boolean.class);
                        if(Sccess){
                            ActivityJumpUtils.actHomePager(getActivity());

                        }else {
                            ToastUtils.show("失败！："+obj.getReason());
                        }
                    }

                    @Override
                    public void onAllFinish() {
                        super.onAllFinish();
                        hideLoadingDialog();
                    }
                });
    }
}
