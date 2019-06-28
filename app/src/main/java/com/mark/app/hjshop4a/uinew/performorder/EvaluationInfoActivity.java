package com.mark.app.hjshop4a.uinew.performorder;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;

import com.facebook.drawee.view.SimpleDraweeView;
import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.common.androidenum.homepager.HPTabType;
import com.mark.app.hjshop4a.common.androidenum.other.BundleKey;
import com.mark.app.hjshop4a.common.listener.DefOnUploadPicListener;
import com.mark.app.hjshop4a.common.utils.ActivityJumpUtils;
import com.mark.app.hjshop4a.common.utils.FrescoUtils;
import com.mark.app.hjshop4a.common.utils.JsonUtils;
import com.mark.app.hjshop4a.common.utils.TakePhoneUtil;
import com.mark.app.hjshop4a.common.utils.ToastUtils;
import com.mark.app.hjshop4a.data.entity.RainbowResultEntity;
import com.mark.app.hjshop4a.data.help.RainbowObserver;
import com.mark.app.hjshop4a.ui.dialog.factory.FunctionDialogFactory;
import com.mark.app.hjshop4a.uinew.performorder.model.EvaluationInfo;
import com.mark.app.hjshop4a.uinew.performorder.model.EvaluationInfoParam;
import com.mark.app.hjshop4a.uinew.performorder.model.OrderPayParam;
import com.mark.app.hjshop4a.uinew.performorder.model.PayInfoParam;
import com.mark.app.hjshop4a.uinew.performorder.model.PerformParam;
import com.mark.app.hjshop4a.uinew.performorder.model.StepFour;
import com.mark.app.hjshop4a.widget.UpdateStepOneLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class EvaluationInfoActivity extends BaseActivity {
    EvaluationInfo data;
    String subOrderSn;
    SmartRefreshLayout mRefreshLayout;//刷新框架
    UpdateStepOneLayout updateStepTip1,updateStepTip2;//
    private int mapId;//1 23 4
    private Map<Integer,String> pic =new HashMap<>();
    CheckBox checkBox1,checkBox2;

    @Override
    public int getContentViewResId() {
        return R.layout.activity_evaluationinfo;
    }

    @Override
    public void initView() {
        setTvText(R.id.titlebar_tv_title,"订单评价");
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
        setClickListener(R.id.btn);
        setClickListener(R.id.tv_evaluationContext);
        setClickListener(R.id.sdv_img1);
        setClickListener(R.id.sdv_img2);
        setClickListener(R.id.sdv_img3);
        setClickListener(R.id.sdv_img4);
        setClickListener(R.id.sdv_img5);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.titlebar_iv_return:
                finish();
                break;
            case R.id.btn:
                nextStep();
                break;
            case R.id.tv_evaluationContext:
                //获取剪贴板管理器：
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
// 创建普通字符型
                ClipData  mClipData = ClipData.newPlainText("Label", getTvText(R.id.tv_evaluationContext));
                cm.setPrimaryClip(mClipData);

                break;
            case R.id.sdv_img1:
                showPic(1);
                break;
            case R.id.sdv_img2:
                showPic(2);
                break;
            case R.id.sdv_img3:
                showPic(3);
                break;
            case R.id.sdv_img4:
                showPic(4);

                break;
            case R.id.sdv_img5:
                showPic(5);

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
        checkBox1 =getView(R.id.cb_1);
        checkBox2 =getView(R.id.cb_2);
        updateStepTip1 =getView(R.id.up_img_1);
        updateStepTip2 =getView(R.id.up_img_2);
        updateStepTip1.setOnItemClickListener(new UpdateStepOneLayout.OnItemClickListener() {
            @Override
            public void onClickImg() {
                mapId=1;
                FunctionDialogFactory.showTakePhoneDialog(getActivity());
            }
        });
    }
    /**
     * 请求数据
     */
    private void requestData() {
//        showLoadingDialog();
        PayInfoParam performParam =new PayInfoParam();
        performParam.setSubOrderSn(subOrderSn);
        App.getServiceManager().getmService()
                .getEvaluationInfo(performParam.toPswJson())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RainbowObserver() {



                    @Override
                    public void onSuccess(RainbowResultEntity obj) {
                        data = JsonUtils.fromJson(obj.getResult(),EvaluationInfo.class);
                        //设置信息
                        initRvAdapter(data);

                    }

                    @Override
                    public void onAllFinish() {
                        super.onAllFinish();
                        mRefreshLayout.finishRefresh();
                    }
                });
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
                updateStepTip1.setImg(imgUrl);
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

    }
    /**
     * 下一步
     */
    private void nextStep() {
        setpic();
        if(!check()){return;}

        showLoadingDialog();
        EvaluationInfoParam evaluationInfoParam=new EvaluationInfoParam();
        evaluationInfoParam.setEvaluationPic(pic.get(1));
        evaluationInfoParam.setSubOrderSn(subOrderSn);
        App.getServiceManager().getmService()
                .evaluationOrder(evaluationInfoParam.toPswJson())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RainbowObserver() {



                    @Override
                    public void onSuccess(RainbowResultEntity obj) {
                        Boolean Sccess = JsonUtils.fromJson(obj.getResult(),Boolean.class);
                        if(Sccess){
                            ActivityJumpUtils.actHomePager(getActivity(), HPTabType.ORDERLIST);

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
            ToastUtils.show("请上传好评图片");
            return false;
        }
        if(!checkBox1.isChecked()){
            ToastUtils.show("请确认物流信息已显示签收");
            return false;
        }
        if(!checkBox2.isChecked()){
            ToastUtils.show("请在淘宝已确认收货");
            return false;
        }
        return true;
    }

    private void initRvAdapter(EvaluationInfo data) {
        if(data==null){
            setViewVisibility(R.id.rl_evaluationPics,false);
            setViewVisibility(R.id.ll_tv_evaluationContext,false);
            return;
        }
        switch (data.getEvaluationType()){
            case 1:
                setTvText(R.id.titlebar_tv_title,"五星好评");
                setViewVisibility(R.id.rl_evaluationPics,false);
                setViewVisibility(R.id.ll_tv_evaluationContext,false);

                break;
            case 2:
                setTvText(R.id.titlebar_tv_title,"文字好评");
                setViewVisibility(R.id.rl_evaluationPics,false);
                setViewVisibility(R.id.ll_tv_evaluationContext,true);
                setTvText(R.id.tv_evaluationContext,data.getEvaluationContext());
                break;
            case 3:
                setTvText(R.id.titlebar_tv_title,"图文好评");
                setViewVisibility(R.id.rl_evaluationPics,true);
                setViewVisibility(R.id.ll_tv_evaluationContext,true);
                setTvText(R.id.tv_evaluationContext,data.getEvaluationContext());
                initSDV(data.getEvaluationPics());
                break;
                default:
                    return;
        }


    }

    private void initSDV(List<String> evaluationPics) {
        setINVisibility(R.id.sdv_img1,false);
        setINVisibility(R.id.sdv_img2,false);
        setINVisibility(R.id.sdv_img3,false);
        setINVisibility(R.id.sdv_img4,false);
        setINVisibility(R.id.sdv_img5,false);
        if (0<evaluationPics.size()){
            FrescoUtils.sdvInside((SimpleDraweeView) getView(R.id.sdv_img1),evaluationPics.get(0));
            setINVisibility(R.id.sdv_img1,true);
        }
        if (1<evaluationPics.size()){
            FrescoUtils.sdvInside((SimpleDraweeView) getView(R.id.sdv_img2),evaluationPics.get(1));
            setINVisibility(R.id.sdv_img2,true);
        }
        if (2<evaluationPics.size()){
            FrescoUtils.sdvInside((SimpleDraweeView) getView(R.id.sdv_img3),evaluationPics.get(2));
            setINVisibility(R.id.sdv_img3,true);
        }
        if (3<evaluationPics.size()){
            FrescoUtils.sdvInside((SimpleDraweeView) getView(R.id.sdv_img4),evaluationPics.get(3));
            setINVisibility(R.id.sdv_img4,true);
        }
        if (4<evaluationPics.size()){
            FrescoUtils.sdvInside((SimpleDraweeView) getView(R.id.sdv_img5),evaluationPics.get(4));
            setINVisibility(R.id.sdv_img5,true);
        }
    }

    private void  showPic(int type){
        FunctionDialogFactory.showBigSdvDialog(App.get().getFragmentManager(),data.getEvaluationPics().get(type-1));
    }

}
