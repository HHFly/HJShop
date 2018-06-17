package com.mark.app.hjshop4a.ui.businessapply;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Spinner;

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
import com.mark.app.hjshop4a.data.entity.BaseResultEntity;
import com.mark.app.hjshop4a.data.help.DefaultObserver;
import com.mark.app.hjshop4a.ui.businessapply.model.AddressInfo;
import com.mark.app.hjshop4a.ui.businessapply.model.BusinessApply;
import com.mark.app.hjshop4a.ui.businessapply.model.CompanyInfo;
import com.mark.app.hjshop4a.ui.businessapply.model.MerchantApplyPram;
import com.mark.app.hjshop4a.ui.businessapply.model.ShopCategory;
import com.mark.app.hjshop4a.ui.dialog.SelectAddressDialog;
import com.mark.app.hjshop4a.ui.dialog.factory.FunctionDialogFactory;
import com.mark.app.hjshop4a.ui.dialog.model.AddressData;
import com.mark.app.hjshop4a.ui.dialog.wheelviewlibrary.listener.SelectInterface;
import com.white.lib.utils.TakePhoneUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by pc on 2018/4/18.
 */

public class BusniessCompanyActivity extends BaseActivity implements SelectInterface {
    private BusinessApply mData;
    private CompanyInfo companyInfo;
    private AddressInfo addressInfo;
    private ShopCategory shopCategory;
    private List<ShopCategory> shopCategorys;
    MerchantApplyPram merchantApplyPram;
    //是否同意
    private boolean isAgree =true;
    //选择dialog
    private SelectAddressDialog wheelDialog;
    Spinner spinner;

    private Map<Integer,Uri>mUris =new LinkedHashMap<>();
    private List<Uri> mPicUris =new ArrayList<>();  //上传图片Uri集合
    Map<Integer,String>pic =new HashMap<>();//上传图片地址集合
    @Override
    public int getContentViewResId() {
        return R.layout.activity_business_company;
    }

    @Override
    public void getIntentParam(Bundle bundle) {
        super.getIntentParam(bundle);
        mData= (BusinessApply) bundle.getSerializable("BusinessApply");

    }

    @Override
    public void initView() {
        changedAgree(isAgree);
        setTvText(R.id.titlebar_tv_title,"公司信息");
        merchantApplyPram =new MerchantApplyPram();
        if(mData.getCompanyInfo()!=null) {
            merchantApplyPram.setData(mData);
        }
        bindData(mData);
        if(mData.getAuditStatus()!= Apply.UNPASS&&mData.getAuditStatus()!=Apply.ToBeAudited){
            setViewEnable(R.id.companyinfo_all,false);
            setViewEnable(R.id.company_layout_name,false);
            setViewEnable(R.id.company_layout_loacl,false);
            setViewEnable(R.id.company_layout_address,false);
            setViewEnable(R.id.store_layout_name,false);
            setViewEnable(R.id.shop_layout_type,false);
            setViewVisibility(R.id.layout_agree,false);
            setViewVisibility(R.id.button,false);
            setViewEnable(R.id.spinner,false);
            setViewEnable(R.id.imagebtn_shop,false);
            setViewEnable(R.id.imagebtn_licence,false);
            setViewEnable(R.id.item_sdv_1,false);
            setViewVisibility(R.id.item_iv_delete_1,false);
            setViewEnable(R.id.item_sdv_2,false);
            setViewVisibility(R.id.item_iv_delete_2,false);
            setViewEnable(R.id.item_sdv_3,false);
              setViewVisibility(R.id.item_iv_delete_3,false);
            setViewEnable(R.id.item_sdv_4,false);

            setViewVisibility(R.id.item_iv_delete_4,false);
        }

    }
    /**
     * 绑定数据
     *
     * @param repo
     */
    private void bindData(BusinessApply repo) {
        if(repo!=null) {
            companyInfo = repo.getCompanyInfo();

            shopCategorys = repo.getShopCategoryList();
//
            //设置分类下拉框

            BaseSpinnerAdapter adapter = new BaseSpinnerAdapter<ShopCategory>(shopCategorys) {
                @Override
                public SpinnerModel getSpinnerModelItem(ShopCategory data) {
                    SpinnerModel item = new SpinnerModel();
                    item.setId(data.getShopCategoryId());
                    item.setName(data.getShopCategoryName());
                    return item;

                }
            };
            spinner = findViewById(R.id.spinner);
            spinner.setAdapter(adapter);


            //选中已经设置的分类id
            if(companyInfo!=null) {
                spinner.setSelection(adapter.getPositionById(companyInfo.getShopCategoryId()));
                addressInfo = companyInfo.getAddressInfo();
                setTvText(R.id.company_tv_name, companyInfo.getCompanyName());
                setTvText(R.id.company_tv_loacl, addressInfo.getProvinceName() +"-"+ addressInfo.getCityName() +"-"+ addressInfo.getCountyName());
                setTvText(R.id.company_tv_address, addressInfo.getCompleteAddress());
//            setTvText(R.id.shop_tv_type,shopCategory.getShopCategoryName());
                setTvText(R.id.store_tv_name, companyInfo.getShopName());
                setSdvBig(R.id.imagebtn_licence, companyInfo.getLicencePic());
                setSdvBig(R.id.imagebtn_shop, companyInfo.getShopImg());

                setSdvBig(R.id.item_sdv_1, companyInfo.getShopInImg1());
                setViewVisibility(R.id.item_iv_delete_1,false);
                setSdvBig(R.id.item_sdv_2, companyInfo.getShopInImg2());
                setViewVisibility(R.id.item_rl_2, true);
                setViewVisibility(R.id.item_iv_delete_2,false);

                setSdvBig(R.id.item_sdv_3, companyInfo.getShopInImg3());
                setViewVisibility(R.id.item_rl_3, true);
                setViewVisibility(R.id.item_iv_delete_3,false);

                setSdvBig(R.id.item_sdv_4, companyInfo.getShopInImg4());
                setViewVisibility(R.id.item_rl_4, true);
                setViewVisibility(R.id.item_iv_delete_4,false);
              
            }

        }
    }
    @Override
    public void setListener() {
        setClickListener(R.id.titlebar_iv_return);
        setClickListener(R.id.company_layout_name);
        setClickListener(R.id.company_layout_loacl);
        setClickListener(R.id.company_layout_address);
        setClickListener(R.id.store_layout_name);
        setClickListener(R.id.shop_layout_type);
        setClickListener(R.id.register_layout_agree);
        setClickListener(R.id.button);
        setClickListener(R.id.register_tv_agreement);
        setClickListener(R.id.imagebtn_shop);
        setClickListener(R.id.imagebtn_licence);
        setClickListener(R.id.item_sdv_1);
//        setClickListener(R.id.item_iv_delete_1);
        setClickListener(R.id.item_sdv_2);
//        setClickListener(R.id.item_iv_delete_2);
        setClickListener(R.id.item_sdv_3);
//        setClickListener(R.id.item_iv_delete_3);
        setClickListener(R.id.item_sdv_4);
//        setClickListener(R.id.item_iv_delete_4);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.titlebar_iv_return:
                finish();
                break;
            case R.id.company_layout_name:
                //公司名称
                FunctionDialogFactory.showAddOneParamDialog(this,"",R.id.company_tv_name);
                break;
            case R.id.company_layout_loacl:
                //所在区域
                showDateDiaglog();
                break;
            case R.id.company_layout_address:
                //详细地址
                FunctionDialogFactory.showAddOneParamDialog(this,"",R.id.company_tv_address);
                break;
            case R.id.store_layout_name:
                //店铺名称
                FunctionDialogFactory.showAddOneParamDialog(this,"",R.id.store_tv_name);
                break;
            case R.id.shop_layout_type:
                //店铺分类

                break;

            case R.id.register_layout_agree:
                //同意
                isAgree = !isAgree;
                changedAgree(isAgree);
                break;
            case R.id.button:
                //提交
                    requestUpdateData();
                break;
            case R.id.imagebtn_shop:
                FunctionDialogFactory.showTakePhoneIDDialog(getAppCompatActivity(), R.id.imagebtn_shop);
                break;
            case R.id.imagebtn_licence:
                FunctionDialogFactory.showTakePhoneIDDialog(getAppCompatActivity(), R.id.imagebtn_licence);
                break;
            case R.id.item_sdv_1:
                FunctionDialogFactory.showTakePhoneIDDialog(getAppCompatActivity(),R.id.item_sdv_1);
                break;
            case R.id.item_sdv_2:
                FunctionDialogFactory.showTakePhoneIDDialog(getAppCompatActivity(),R.id.item_sdv_2);
                break;
            case R.id.item_sdv_3:
                FunctionDialogFactory.showTakePhoneIDDialog(getAppCompatActivity(),R.id.item_sdv_3);
                break;
            case R.id.item_sdv_4:
                FunctionDialogFactory.showTakePhoneIDDialog(getAppCompatActivity(),R.id.item_sdv_4);
                break;
            case R.id.item_iv_delete_1:
                remove(R.id.item_sdv_1);
                break;
            case R.id.item_iv_delete_2:
                remove(R.id.item_sdv_2);
                break;
            case R.id.item_iv_delete_3:
                remove(R.id.item_sdv_3);
                break;
            case R.id.item_iv_delete_4:
                remove(R.id.item_sdv_4);
                break;
            case R.id.register_tv_agreement:
                //同意协议内容
                String url = "file:///android_asset/agreement/terms_and_conditions.html";
                String title = getString(R.string.login_同意协议内容);
                ActivityJumpUtils.actWebActivity(getActivity(), url, title);
                break;


        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        TakePhoneUtil.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        TakeImgUtil.onActivityResult(this, requestCode, resultCode, data, new TakeImgUtil.CallBack() {
            @Override
            public void back(Uri var1, int id) {
                if(requestUpdateDataOfNewPic(var1,id)) {

                }
            }
        });
    }
    private void addpic(Uri uri,int id){
        mUris.put(id,uri);

        showpic(mUris);
    }
  //展示图片
    private void showpic(Map<Integer, Uri> mUris) {
        for (Integer in : mUris.keySet()) {
            //map.keySet()返回的是所有key的值
            Uri str = mUris.get(in);//得到每个key多对用value的值

            mPicUris.add(str);
        }

//        setViewVisibility(R.id.item_rl_2,false);
//        setViewVisibility(R.id.item_rl_3,false);
//        setViewVisibility(R.id.item_rl_4,false);
        Uri uri = Uri.parse("res:///" + R.mipmap.gird_storeimg_update);
        setSdvBig(R.id.item_sdv_1,uri);
        setSdvBig(R.id.item_sdv_2,uri);
        setSdvBig(R.id.item_sdv_3,uri);
        setSdvBig(R.id.item_sdv_4,uri);
        mUris.clear();
        if (mPicUris.size()>0){
            setSdvBig(R.id.item_sdv_1,mPicUris.get(0));
            this.mUris.put(R.id.item_sdv_1,mPicUris.get(0));
            mPicUris.remove(0);
            setViewVisibility(R.id.item_rl_2,true);
        }
        if (mPicUris.size()>0){
            this.mUris.put(R.id.item_sdv_2,mPicUris.get(0));
            setViewVisibility(R.id.item_rl_3,true);
            setSdvBig(R.id.item_sdv_2,mPicUris.get(0));
            mPicUris.remove(0);
        }
        if (mPicUris.size()>0){
            this.mUris.put(R.id.item_sdv_3,mPicUris.get(0));
            setViewVisibility(R.id.item_rl_4,true);
            setSdvBig(R.id.item_sdv_3,mPicUris.get(0));
            mPicUris.remove(0);
        }
        if (mPicUris.size()>0){
            this.mUris.put(R.id.item_sdv_4,mPicUris.get(0));
            setSdvBig(R.id.item_sdv_4,mPicUris.get(0));
            mPicUris.remove(0);
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
                pic.put(id,imgUrl);
                switch (id) {
                    case R.id.imagebtn_licence:
                        setSdvBig(id, uri);
                        break;
                    case R.id.imagebtn_shop:
                        setSdvBig(id, uri);
                        break;
                    default:
                        addpic(uri, id);
                        break;
                }
                hideLoadingDialog();

            }

            @Override
            public void onLoadPicUnSuccessFinish() {
                super.onLoadPicUnSuccessFinish();

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

        String companyName = getTvText(R.id.company_tv_name);
        String completeAddress = getTvText(R.id.company_tv_address);
       String localAddress= getTvText(R.id.company_tv_loacl);
        String shopName = getTvText(R.id.store_tv_name);
        long shopCategoryId = spinner.getSelectedItemId();
        String licenceImage =pic.get(R.id.imagebtn_licence);

        String shopImages =pic.get(R.id.imagebtn_shop);
        String shopImagesIn1= pic.get(R.id.item_sdv_1);
        String shopImagesIn2= pic.get(R.id.item_sdv_2);
        String shopImagesIn3= pic.get(R.id.item_sdv_3);
        String shopImagesIn4= pic.get(R.id.item_sdv_4);
        String shopImagesIn =shopImagesIn1+","+shopImagesIn2+","+shopImagesIn3+","+shopImagesIn4;
            //校验
        if(TextUtils.isEmpty(companyName))
        {
            ToastUtils.show("请输入公司名称");
            return;
        }
        if(TextUtils.isEmpty(localAddress))
        {
            ToastUtils.show("请选择所在区域");
            return;
        }
        if(TextUtils.isEmpty(completeAddress))
        {
            ToastUtils.show("请输入详细地址");
            return;
        }
        if(TextUtils.isEmpty(shopName))
        {
            ToastUtils.show("请输入店铺名称");
            return;
        }
        if(TextUtils.isEmpty(licenceImage))
        {
            licenceImage=mData.getCompanyInfo().getLicencePic();
            if(TextUtils.isEmpty(licenceImage)) {
                ToastUtils.show("请上传营业执照");
                return;
            }
        }
        if(TextUtils.isEmpty(shopImages))
        {
            shopImages=mData.getCompanyInfo().getShopImg();
            if(TextUtils.isEmpty(shopImages)) {
                ToastUtils.show("请上传店铺形象照片");
                return;
            }
        }
        if(TextUtils.isEmpty(shopImagesIn1))
        {
            shopImagesIn1=mData.getCompanyInfo().getShopInImg1();
            if(TextUtils.isEmpty(shopImagesIn1)){
            ToastUtils.show("请上传店铺照片1");
            return;
            }
        }
        if(TextUtils.isEmpty(shopImagesIn2))
        {
            shopImagesIn2=mData.getCompanyInfo().getShopInImg2();
            if(TextUtils.isEmpty(shopImagesIn2)) {
                ToastUtils.show("请上传店铺照片2");
                return;
            }
        }
        if(TextUtils.isEmpty(shopImagesIn3))
        {
            shopImagesIn3=mData.getCompanyInfo().getShopInImg3();
            if(TextUtils.isEmpty(shopImagesIn3)) {
                ToastUtils.show("请上传店铺照片3");
                return;
            }
        }
        if(TextUtils.isEmpty(shopImagesIn4))
        {
            shopImagesIn4=mData.getCompanyInfo().getShopInImg4();
            if(TextUtils.isEmpty(shopImagesIn4)) {
                ToastUtils.show("请上传店铺照片4");
                return;
            }
        }
        if (!isAgree) {
            ToastUtils.show(R.string.login_注册需要同意协议);
            return;
        }

        merchantApplyPram.setShopName(shopName);//店铺名称
//        merchantApplyPram.setProvinceId(1);//省ID
//        merchantApplyPram.setCityId(1);//市ID
//        merchantApplyPram.setCountyId(1);//区ID
        merchantApplyPram.setCompleteAddress(completeAddress);//详细地址
        merchantApplyPram.setShopCateogryId(shopCategoryId);//类目ID
        merchantApplyPram.setCompanyName(companyName);//公司名称
        merchantApplyPram.setShopLicence(licenceImage);//营业执照
        merchantApplyPram.setShopImg(shopImages);//店铺形象照片
        merchantApplyPram.setShopInImg1(shopImagesIn1);
        merchantApplyPram.setShopInImg2(shopImagesIn2);
        merchantApplyPram.setShopInImg3(shopImagesIn3);
        merchantApplyPram.setShopInImg4(shopImagesIn4);
        merchantApplyPram.setIsReadProtocol("1");
        merchantApplyPram.setLatitude("0");//纬度
        merchantApplyPram.setLongitude("0");//精度

        App.getServiceManager().getPdmService()
                .merchantApply(merchantApplyPram.getMap())
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
    private void  remove(int id){
        mUris.remove(id);
        pic.remove(id);
        showpic(mUris);
    }
    /**
     * 修改同意状态
     *
     * @param isAgree
     */
    private void changedAgree(boolean isAgree) {
        setViewSelected(R.id.register_layout_agree, isAgree);
//        if (textWatcher != null) {
//            textWatcher.onTextChanged("", 0, 0, 0);
//        }
    }

    /**
     * 弹出地址对话框--三级联动的效果
     *
     *
     */
    public void showDateDiaglog() {
        if(mData.getCompanyInfo()!=null&&mData.getCompanyInfo().getAddressInfo()!=null) {
            wheelDialog = new SelectAddressDialog(this,
                    this, SelectAddressDialog.STYLE_THREE, mData.getCompanyInfo().getAddressInfo().getProvinceId(), mData.getCompanyInfo().getAddressInfo().getCityId(), mData.getCompanyInfo().getAddressInfo().getCountyId());
            wheelDialog.showDialog();
        }else {
            wheelDialog = new SelectAddressDialog(this,
                    this, SelectAddressDialog.STYLE_THREE,0,0,0);
            wheelDialog.showDialog();
        }
    }


    @Override
    public void selectedResult(AddressData result1, AddressData result2, AddressData result3) {
        merchantApplyPram.setProvinceId(NumParseUtils.parseLong(result1.getId()));//省ID
        merchantApplyPram.setCityId(NumParseUtils.parseLong(result2.getId()));//市ID
        merchantApplyPram.setCountyId(NumParseUtils.parseLong(result3.getId()));//区ID
        setTvText(R.id.company_tv_loacl, result1.getName() +"-"+ result2.getName() +"-"+ result3.getName());
    }

    @Override
    public void selectedResult(String result) {

    }
}
