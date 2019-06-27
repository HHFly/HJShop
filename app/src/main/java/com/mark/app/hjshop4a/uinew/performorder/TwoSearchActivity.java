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
import com.mark.app.hjshop4a.uinew.performorder.adapter.TwoSearchAdapter;
import com.mark.app.hjshop4a.uinew.performorder.model.AddProduct;
import com.mark.app.hjshop4a.uinew.performorder.model.AddProductPic;
import com.mark.app.hjshop4a.uinew.performorder.model.NextStepParam;
import com.mark.app.hjshop4a.uinew.performorder.model.PerformParam;
import com.mark.app.hjshop4a.uinew.performorder.model.StepTwo;
import com.mark.app.hjshop4a.uinew.performorder.model.StepTwoParam;
import com.mark.app.hjshop4a.uinew.performorder.model.VerifyParam;
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

public class TwoSearchActivity extends BaseActivity {
    String subOrderSn;
    SmartRefreshLayout mRefreshLayout;//刷新框架
    TwoSearchAdapter twoSearchAdapter;
    private Map<Integer,String> pic =new HashMap<>();
    private int mapId;//0 主 4 搜索 1  2 3货比   5.。。。副宝贝
    private int type =0;//0 主fu 1 货比
    UpdateStepLayout updateStep ;//主副
    UpdateStepOneLayout updateStepTip;//货比三家
    StepTwo data;
    @Override
    public int getContentViewResId() {
        return R.layout.activity_perform;
    }

    @Override
    public void initView() {
        setTvText(R.id.titlebar_tv_title,"搜索宝贝");
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
        performParam.setStep(2);
        performParam.setSubOrderSn(subOrderSn);
        App.getServiceManager().getmService()
                .getOrderInfo(performParam.toPswJson())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RainbowObserver() {



                    @Override
                    public void onSuccess(RainbowResultEntity obj) {
                        data = JsonUtils.fromJson(obj.getResult(),StepTwo.class);
                        //设置信息
                        if(data!=null) {
                            initRvAdapter(data);
                            twoSearchAdapter.startTime();
                        }
                    }

                    @Override
                    public void onAllFinish() {
                        super.onAllFinish();
                        mRefreshLayout.finishRefresh();
                    }
                });
    }

    private void initRvAdapter(final StepTwo data){

        if(twoSearchAdapter==null){
            RecyclerView rv = getView(R.id.recyclerView);
            twoSearchAdapter = new TwoSearchAdapter(data,data.getAddProductList());
            rv.setLayoutManager(new LinearLayoutManager(this));
            rv.setAdapter(twoSearchAdapter);
            twoSearchAdapter.setOnItemClickListener(new TwoSearchAdapter.OnItemClickListener() {
                @Override
                public void onClickNext() {
                    nextStep();
                }

                @Override
                public void onCircularProgressButton(String et, CircularProgressButton btn, StepTwo stepTwo) {
                    if("".equals(et)){
                        ToastUtils.show("请验证店铺:"+stepTwo.getShopName());
                        return;
                    }
                    verify(et,btn);
                }

                @Override
                public void onClickMainProductBrowsePic(UpdateStepOneLayout updateStepOneLayout) {
                    mapId=0;
                    type=1;
                    updateStepTip=updateStepOneLayout;
                    FunctionDialogFactory.showTakePhoneDialog(getActivity());
                }

                @Override
                public void onClickSearchPic(UpdateStepOneLayout updateStepOneLayout) {
                    mapId=4;
                    type=1;
                    updateStepTip=updateStepOneLayout;
                    FunctionDialogFactory.showTakePhoneDialog(getActivity());
                }


                @Override
                public void onClickHuobisanjiaPic1(UpdateStepOneLayout updateStepOneLayout) {
                    mapId=1;
                    updateStepTip= updateStepOneLayout;
                    type=1;
                    FunctionDialogFactory.showTakePhoneDialog(getActivity());
                }

                @Override
                public void onClickHuobisanjiaPic2(UpdateStepOneLayout updateStepOneLayout) {
                    mapId=2;
                    type=1;
                    updateStepTip= updateStepOneLayout;
                    FunctionDialogFactory.showTakePhoneDialog(getActivity());
                }

                @Override
                public void onClickHuobisanjiaPic3(UpdateStepOneLayout updateStepOneLayout) {
                    mapId=3;
                    type=1;
                    updateStepTip= updateStepOneLayout;
                    FunctionDialogFactory.showTakePhoneDialog(getActivity());
                }

                @Override
                public void onCircularProgressButtonDeputy(String et, CircularProgressButton btn, AddProduct addProduct) {
                    if("".equals(et)){
                        ToastUtils.show("请验证商品:"+addProduct.getAuditKeyWord());
                        return;
                    }
                    verify(et,btn);
                }

                @Override
                public void onClickProductBrowsePic(UpdateStepLayout updateStepLayout, AddProduct addProduct,int pos) {
                    mapId=pos+5;
                    type=0;
                    updateStep=updateStepLayout;
                    FunctionDialogFactory.showTakePhoneDialog(getActivity());
                }





            });
        }
        else {
            twoSearchAdapter.notifyData(data,data.getAddProductList(),true);
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
     * 请求更新数据
     */
    private void requestUpdateDataOfNewPic(Uri uri) {
        showLoadingDialog();

        luban(uri, new DefOnUploadPicListener() {
            @Override
            public void onLoadPicFinish(String imgUrl) {
                super.onLoadPicFinish(imgUrl);
//                requestUpdateData(imgUrl);
                switch (type){
                    case 0:
                        updateStep.setImg(imgUrl);
                        break;
                    case 1:
                        updateStepTip.setImg(imgUrl);

                        break;

                }
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

    /**
     * 验证店铺
     */
    private void verify(String et, final CircularProgressButton btn) {
//        showLoadingDialog();
        btn.setProgress(50);
        VerifyParam verifyParam =new VerifyParam();
        verifyParam.setVerification(et);
        verifyParam.setSubOrderSn(subOrderSn);
        App.getServiceManager().getmService()
                .verify(verifyParam.toPswJson())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RainbowObserver() {


                    @Override
                    public void onSuccess(RainbowResultEntity obj) {
                            boolean succeed =JsonUtils.fromJson(obj.getResult(),Boolean.class);
                            btn.setProgress(succeed?100:-1);
                    }

                    @Override
                    public void onUnSuccessFinish() {
                        btn.setProgress(0);
                        super.onUnSuccessFinish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        btn.setProgress(0);
                        super.onError(e);
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
        setpic();
        if(!check()){return;}

        showLoadingDialog();
        NextStepParam nextStepParam =new NextStepParam();
        nextStepParam.setStep(2);
        nextStepParam.setSubOrderSn(subOrderSn);
        StepTwoParam stepTwoParam =new StepTwoParam();
        stepTwoParam.setMainProductBrowsePic(pic.get(0));
        stepTwoParam.setSearchPic(pic.get(4));
        stepTwoParam.setHuobisanjiaPic1(pic.get(1));
        stepTwoParam.setHuobisanjiaPic2(pic.get(2));
        stepTwoParam.setHuobisanjiaPic3(pic.get(3));
        if(data.getIsAddProductFlage()==1){
            List<AddProductPic> addProductPics =new ArrayList<>();
            for (int i=0;i<data.getAddProductList().size();i++){
               AddProductPic addProductPic =new AddProductPic();
               addProductPic.setAddProductId(data.getAddProductList().get(i).getAddProductId());
                addProductPic.setProductBrowsePic(pic.get(i+5));
                addProductPics.add(addProductPic);
            }
            stepTwoParam.setAddProductPics(addProductPics);
        }
        nextStepParam.setJsonData(stepTwoParam.toJson());
        App.getServiceManager().getmService()
                .nextStep(nextStepParam.toPswJson())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RainbowObserver() {



                    @Override
                    public void onSuccess(RainbowResultEntity obj) {
                        Boolean Sccess = JsonUtils.fromJson(obj.getResult(),Boolean.class);
                        if(Sccess){
                            ActivityJumpUtils.actStepThree(getActivity(),subOrderSn);

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

        if(TextUtils.isEmpty(pic.get(4))){
            ToastUtils.show("请上传搜索关键词图片");
            return false;
        }
        if(TextUtils.isEmpty(pic.get(0))){
            ToastUtils.show("请上传主宝贝浏览图片");
            return false;
        }
        if(TextUtils.isEmpty(pic.get(1))){
            ToastUtils.show("请上传货比三家图片1");
            return false;
        }
        if(TextUtils.isEmpty(pic.get(2))){
            ToastUtils.show("请上传货比三家图片2");
            return false;
        }
        if(TextUtils.isEmpty(pic.get(3))){
            ToastUtils.show("请上传货比三家图片3");
            return false;
        }
        if(data.getIsAddProductFlage()==1){
            for (int i=0;i<data.getAddProductList().size();i++){
                if(TextUtils.isEmpty(pic.get(i+5))){
                    ToastUtils.show("请上传副宝贝图片");
                    return false;
                }
            }
        }
        return true;
    }
}
