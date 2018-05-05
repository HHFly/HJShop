package com.mark.app.hjshop4a.ui.business.consumecommit;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.base.adapter.BaseSpinnerAdapter;
import com.mark.app.hjshop4a.common.androidenum.other.ActResultCode;
import com.mark.app.hjshop4a.common.listener.DefOnUploadPicListener;
import com.mark.app.hjshop4a.common.utils.ActivityJumpUtils;
import com.mark.app.hjshop4a.common.utils.NumParseUtils;
import com.mark.app.hjshop4a.common.utils.TakeImgUtil;
import com.mark.app.hjshop4a.common.utils.ToastUtils;
import com.mark.app.hjshop4a.common.utils.ValidUtils;
import com.mark.app.hjshop4a.data.entity.BaseResultEntity;
import com.mark.app.hjshop4a.data.help.DefaultObserver;
import com.mark.app.hjshop4a.ui.business.consumecommit.model.Custom;
import com.mark.app.hjshop4a.ui.business.consumecommit.model.CustomPram;
import com.mark.app.hjshop4a.ui.business.consumecommit.model.Model;
import com.mark.app.hjshop4a.ui.businessapply.model.ShopCategory;
import com.mark.app.hjshop4a.ui.dialog.AddOneEtParamDialog;
import com.mark.app.hjshop4a.ui.dialog.factory.FunctionDialogFactory;
import com.mark.app.hjshop4a.ui.userinfo.model.CommitUserInfo;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by pc on 2018/4/19.
 */

public class ConsumeCommitActivity  extends BaseActivity{
    private AddOneEtParamDialog mAddOneEtParamDialog;
    private Double discounts=0.0 ;//服务费率
    Spinner spinner;
    private  String pic;//上传图片地址
    @Override
    public int getContentViewResId() {
        return R.layout.activity_order_commit;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAddOneEtParamDialog=null;
    }
    @Override
    public void initView() {
        setTvText(R.id.titlebar_tv_title,"线下消费提交");
        setTvText(R.id.titlebar_tv_right,"说明");
        setIvImage(R.id.titlebar_iv_logo,R.mipmap.ic_tip);
        requestData();

    }

    @Override
    public void setListener() {
        setClickListener(R.id.titlebar_iv_return);
        setClickListener(R.id.titlebar_tv_right);
        setClickListener(R.id.member_id);
        setClickListener(R.id.roletype);
        setClickListener(R.id.consmue_count);
        setClickListener(R.id.service_charge);
        setClickListener(R.id.commodity_name);
        setClickListener(R.id.commodity_conut);
        setClickListener(R.id.commodity_price);
        setClickListener(R.id.imagebtn);
        setClickListener(R.id.button);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.titlebar_iv_return:
                finish();
                break;
            case R.id.titlebar_tv_right:
                ActivityJumpUtils.actActivity(this,CommitRuleActivity.class);
            break;
            case R.id.member_id:
//                *会员账号
                FunctionDialogFactory.showAddOneParamDialogNum(this,"",R.id.tv_member_id);
                break;
            case R.id.roletype:
//                *账户类别
                break;
            case R.id.consmue_count:
//                *消费金额
                showDialog();
                break;
            case R.id.service_charge:
//                * 服务费
                break;
            case R.id.commodity_name:
//                *商品名称
                FunctionDialogFactory.showAddOneParamDialog(this,"",R.id.tv_commodity_name);
                break;
            case R.id.commodity_conut:
//                *商品数量
                FunctionDialogFactory.showAddOneParamDialogNum(this,"",R.id.tv_commodity_conut);
                break;
            case R.id.commodity_price:
//                *商品单价
                FunctionDialogFactory.showAddOneParamDialog(this,"",R.id.tv_commodity_price);
                break;
            case R.id.imagebtn:
//             图片
                FunctionDialogFactory.showTakePhoneIDDialog(getAppCompatActivity(), R.id.imagebtn);
                break;
            case R.id.button:
//                提交
                requestUpdateData();
                break;
        }
    }
    private  void showDialog(){
        if(mAddOneEtParamDialog==null) {
            mAddOneEtParamDialog = AddOneEtParamDialog.getInstance(true);
            mAddOneEtParamDialog.setOnDialogClickListener(new AddOneEtParamDialog.DefOnDialogClickListener() {
                @Override
                public void onClickCommit(AddOneEtParamDialog dialog, String data) {
                    Double count = NumParseUtils.parseDouble(data);


                    Double service =discounts*count;
                    setTvText(R.id.tv_service_charge,service.toString());
                    setTvText(R.id.tv_consmue_count, data);
                    dialog.dismiss();
                }
            });

        }
        mAddOneEtParamDialog.show(this.getFragmentManager());
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        TakeImgUtil.onActivityResult(this, requestCode, resultCode, data, new TakeImgUtil.CallBack() {
            @Override
            public void back(Uri var1, int id) {
                requestUpdateDataOfNewPic(var1,id);
            }
        });
    }

    /**
     * 请求数据
     */
    private void requestData() {
        showLoadingDialog();
        App.getServiceManager().getPdmService()
                .getCustomsData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<Custom>() {


                    @Override
                    public void onSuccess(BaseResultEntity<Custom> obj) {
                        Custom data =obj.getResult();
                            bindData(data);
                        requestImgCode();

                    }

                    @Override
                    public void onUnSuccessFinish() {
                        super.onUnSuccessFinish();
                        hideLoadingDialog();
                    }

                    @Override
                    public void onAllFinish() {
                        super.onAllFinish();
//                        hideLoadingDialog();
                    }
                });
    }

    private void bindData(Custom data) {
        if (data != null) {
            discounts = NumParseUtils.parseDouble(data.getDiscounts());
            String captcha = data.getCaptcha();
            List<Model> modelList = data.getModelList();


            //设置分类下拉框

            BaseSpinnerAdapter adapter = new BaseSpinnerAdapter<Model>(modelList) {
                @Override
                public SpinnerModel getSpinnerModelItem(Model data) {
                    SpinnerModel item = new SpinnerModel();
                    item.setId(data.getModelId());
                    item.setName(data.getModelName());
                    return item;

                }
            };
            spinner = findViewById(R.id.spinner);
            spinner.setAdapter(adapter);
            setSdvInside(R.id.audit,data.getCaptcha());
        }
    }

    /**
     * 请求更新数据，有新图片
     */
    private boolean requestUpdateDataOfNewPic(final Uri uri,final int id) {
        showLoadingDialog();
        final boolean[] isSuccess = new boolean[1];
        luban(uri, new DefOnUploadPicListener() {
            @Override
            public void onLoadPicFinish(String imgUrl) {
                super.onLoadPicFinish(imgUrl);
//                requestUpdateData(imgUrl);

                ToastUtils.show("上传图片成功");
                pic=imgUrl;
                setSdvBig(id, uri);
                hideLoadingDialog();
                isSuccess[0] =true;
            }

            @Override
            public void onLoadPicUnSuccessFinish() {
                super.onLoadPicUnSuccessFinish();
                ToastUtils.show("上传图片失败");
                hideLoadingDialog();
                isSuccess[0] =false;
            }
        });
        return isSuccess[0];
    }

    /**
     * 提交申请
     */
    private void requestUpdateData() {

        String memberId = getTvText(R.id.tv_member_id);//h会员账号
        long ModelId=0;
        if(spinner!=null) {
            ModelId= spinner.getSelectedItemId(); //服务类型
        }
        String consumecount = getTvText(R.id.tv_consmue_count);
        String commodityname = getTvText(R.id.tv_commodity_name);
        String commodityconut = getTvText(R.id.tv_commodity_conut);
        String commodityprice = getTvText(R.id.tv_commodity_price);
        String audit = getTvText(R.id.et_audit);
        //校验
        if(!ValidUtils.phone(memberId))
        {
            ToastUtils.show("请输入正确会员账号");
            return;
        }
        if(TextUtils.isEmpty(consumecount))
        {
            ToastUtils.show("请输入消费金额");
            return;
        }
        if(TextUtils.isEmpty(commodityname))
        {
            ToastUtils.show("请输入商品名称");
            return;
        }
        if(TextUtils.isEmpty(commodityconut))
        {
            ToastUtils.show("请输入商品数量");
            return;
        }
        if(TextUtils.isEmpty(commodityprice))
        {
            ToastUtils.show("请输入商品价格");
            return;
        }
        if(TextUtils.isEmpty(pic))
        {
            ToastUtils.show("请上传单据图片");
            return;
        }
        if(TextUtils.isEmpty(audit))
        {
            ToastUtils.show("请输入验证码");
            return;
        }
        CustomPram customPram =new CustomPram();
        customPram.setCellPhone(memberId);
        customPram.setModelId(ModelId);
        customPram.setOcMoeny(consumecount);
        customPram.setProductName(commodityname);
        customPram.setProductNum(NumParseUtils.parseInt(commodityconut));
        customPram.setProductPrice(commodityprice);
        customPram.setBuyProof(pic);
        customPram.setCaptchaPc(audit);
        App.getServiceManager().getPdmService()
                .customs(customPram.getMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver() {
                    @Override
                    public void onSuccess(BaseResultEntity obj) {
                        ToastUtils.show("提交成功");
                        setResult(ActResultCode.RESULT_OK);
                        finish();
                    }

                    @Override
                    public void onAllFinish() {
                        super.onAllFinish();
                        hideLoadingDialog();
                    }
                });


    }

    /**
     * 请求图片验证码
     */
    private void requestImgCode() {
        showLoadingDialog();
        App.getServiceManager().getPdmService()
                .randomVerification()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver() {


                    @Override
                    public void onSuccess(BaseResultEntity obj) {
                        String data = (String) obj.getResult();
                        if(data!=null) {
                            setSdvInside(R.id.audit,data);
                        }
                    }

                    @Override
                    public void onUnSuccessFinish() {
                        super.onUnSuccessFinish();

                    }

                    @Override
                    public void onAllFinish() {
                        super.onAllFinish();
                        hideLoadingDialog();
                    }
                });
    }
}
