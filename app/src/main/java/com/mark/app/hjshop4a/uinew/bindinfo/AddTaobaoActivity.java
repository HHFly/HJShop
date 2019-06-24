package com.mark.app.hjshop4a.uinew.bindinfo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.text.TextUtils;
import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.common.androidenum.other.ActResultCode;
import com.mark.app.hjshop4a.common.androidenum.other.BundleKey;
import com.mark.app.hjshop4a.common.listener.DefOnUploadPicListener;
import com.mark.app.hjshop4a.common.utils.JsonUtils;
import com.mark.app.hjshop4a.common.utils.TakeImgUtil;
import com.mark.app.hjshop4a.common.utils.ToastUtils;
import com.mark.app.hjshop4a.common.utils.ValidUtils;
import com.mark.app.hjshop4a.data.entity.RainbowResultEntity;
import com.mark.app.hjshop4a.data.help.RainbowObserver;
import com.mark.app.hjshop4a.ui.dialog.SexDialog;
import com.mark.app.hjshop4a.ui.dialog.WheelDialog;
import com.mark.app.hjshop4a.ui.dialog.factory.FunctionDialogFactory;
import com.mark.app.hjshop4a.uinew.bindinfo.dialog.AreaPickerView;
import com.mark.app.hjshop4a.uinew.bindinfo.dialog.ShopTypePickerView;
import com.mark.app.hjshop4a.uinew.bindinfo.model.AccountInfoParam;
import com.mark.app.hjshop4a.uinew.bindinfo.model.BuyerAccount;
import com.mark.app.hjshop4a.uinew.bindinfo.model.BuyerAccountParam;
import com.mark.app.hjshop4a.uinew.bindinfo.model.CAddress;
import com.mark.app.hjshop4a.uinew.bindinfo.model.PAddress;
import com.mark.app.hjshop4a.uinew.bindinfo.model.ShopType;
import com.mark.app.hjshop4a.uinew.dialog.YesNoDialog;
import com.mark.app.hjshop4a.widget.PickerScrollView;
import com.mark.app.hjshop4a.widget.UpdateImgLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AddTaobaoActivity extends BaseActivity  {
    UpdateImgLayout huabeiPic ,levelPic,realAuthenticatePic;
    private SexDialog sexDialog;
    private YesNoDialog yesNoDialog;
    private ShopTypePickerView shopTypeDialog;
    //选择dialog
    private AreaPickerView addressDialog;
    //
    WheelDialog Leveldialog ;
    List<PAddress> PAddressdata;
    private Map<Integer,String> pic =new HashMap<>();

    long id,addressId ;
    int type;
    AccountInfoParam accountInfoParam =new AccountInfoParam();
   BuyerAccountParam buyerAccountParam =new BuyerAccountParam();

    @Override
    public int getContentViewResId() {
        return R.layout.activity_bind_taobao;
    }

    @Override
    public void findView() {

        huabeiPic  =getView(R.id.up_huabei);
        huabeiPic.setOnItemClickListener(new UpdateImgLayout.OnItemClickListener() {
            @Override
            public void onClickImg() {
                FunctionDialogFactory.showTakePhoneIDDialog(getActivity(),R.id.up_huabei);
            }
        });
        levelPic  =getView(R.id.up_realAuthenticate);
        levelPic.setOnItemClickListener(new UpdateImgLayout.OnItemClickListener() {
            @Override
            public void onClickImg() {
                FunctionDialogFactory.showTakePhoneIDDialog(getActivity(),R.id.up_realAuthenticate);
            }
        });
        realAuthenticatePic  =getView(R.id.up_levelPic);
        realAuthenticatePic.setOnItemClickListener(new UpdateImgLayout.OnItemClickListener() {
            @Override
            public void onClickImg() {
                FunctionDialogFactory.showTakePhoneIDDialog(getActivity(),R.id.up_levelPic);
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        TakeImgUtil.onActivityResult(getActivity(), requestCode, resultCode, data, new TakeImgUtil.CallBack() {
            @Override
            public void back(Uri var1, int id) {
                requestUpdateDataOfNewPic(var1,id);

            }
        });

    }

    @Override
    public void getIntentParam(Bundle bundle) {
        super.getIntentParam(bundle);
        id =bundle.getLong(BundleKey.ID);
        type =bundle.getInt(BundleKey.SHOWTYPE);
    }
    @Override
    public void initView() {

        setTvText(R.id.titlebar_tv_title,R.string.绑定淘宝账号);
        setViewVisibility(R.id.mask,type==1);
        setViewVisibility(R.id.btn,type!=1);
        requestAddressData();
        if(type!=2){
            requestData();
        }


    }

    @Override
    public void setListener() {
        setClickListener(R.id.titlebar_iv_return);
        setClickListener(R.id.user_layout_user_sex);
        setClickListener(R.id.certification_layout_user_city);
        setClickListener(R.id.user_layout_user_ishuabei);
        setClickListener(R.id.user_layout_user_shoptype);
        setClickListener(R.id.user_layout_user_level);
        setClickListener(R.id.btn);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.titlebar_iv_return:
                finish();
                break;
            case R.id.user_layout_user_sex:
                showSexDialog(R.id.user_tv_user_sex);
                break;
            case R.id.certification_layout_user_city:
                showAddressDiaglog();
                break;
            case R.id.user_layout_user_ishuabei:
                showYesNoDialog(R.id.user_tv_user_ishuabei);
                break;
            case R.id.user_layout_user_shoptype:
                showShoptypeDiaglog();
                break;
            case R.id.user_layout_user_level:
                showLevelDiaglog();
                break;
            case R.id.btn:
                commit();
                break;
        }
    }
    /**
     *
     *
     *
     */
    public void showLevelDiaglog() {
        if(Leveldialog==null){
            Leveldialog = WheelDialog.getInstance(getData());
           Leveldialog.setOnDialogClickListener(new WheelDialog.OnDialogClickListener() {
               @Override
               public void onCancel() {

               }

               @Override
               public void onOk(PickerScrollView.ItemModel data) {
                    setTvText(R.id.user_tv_user_level,data.getName());
               }
           });
        }
            Leveldialog.show(getFragmentManager());

    }
    private List<PickerScrollView.ItemModel> getData(){
        List<PickerScrollView.ItemModel> data =new ArrayList<>();
        PickerScrollView.ItemModel a1 =new  PickerScrollView.ItemModel();
        a1.setId(1);
        a1.setName( "三星");
        data.add(a1);
        PickerScrollView.ItemModel a2 =new  PickerScrollView.ItemModel();
        a2.setId(2);
        a2.setName( "四星");
        data.add(a2);
        PickerScrollView.ItemModel a3 =new  PickerScrollView.ItemModel();
        a3.setId(3);
        a3.setName( "五星");
        data.add(a3);
        PickerScrollView.ItemModel a4 =new  PickerScrollView.ItemModel();
        a4.setId(4);
        a4.setName( "一钻");
        data.add(a4);
        PickerScrollView.ItemModel a5 =new  PickerScrollView.ItemModel();
        a5.setId(5);
        a5.setName( "二钻");
        data.add(a5);
        PickerScrollView.ItemModel a6 =new  PickerScrollView.ItemModel();
        a6.setId(6);
        a6.setName( "三钻");
        data.add(a6);
        PickerScrollView.ItemModel a7 =new  PickerScrollView.ItemModel();
        a7.setId(7);
        a7.setName( "四钻");
        data.add(a7);
        PickerScrollView.ItemModel a8 =new  PickerScrollView.ItemModel();
        a8.setId(8);
        a8.setName( "五钻");
        data.add(a8);
        
        return data;
    }
    private String getLevelStr(int id){
        switch (id){
            case 1:
               return "三星";
            case 2:
                return "四星";
            case 3:
                return "五星";
            case 4:
                return "一钻";
            case 5:
                return "二钻";
            case 6:
                return "三钻";
            case 7:
                return "四钻";
            case 8:
                return "五钻";
        }
        return "三星";
    }
    private String getShopTypeStr(String data){
        String shopType="";
       try {
        String[] str =   data.split("/");
           for(int i =0;i<str.length;i++){
             shopType +=  getStrbyType(str[i])+"/";
           }
       }
       catch (Exception e){
           e.printStackTrace();
       }
       return shopType;
    }
    private String getStrbyType(String data){
        switch (data){
            case "A":
              return "服装鞋包";
            case "B":
                return "手机数码";
            case "C":
                return "家用电器";
            case "D":
                return "美妆饰品";
            case "E":
                return "母婴用品";
            case "F":
                return "家居建材";
            case "G":
                return "百货食品";
            case "H":
                return "运动户外";
            case "I":
                return "文化娱乐";
            case "J":
                return "生活服务";
            case "K":
                return "汽摩配件";



        }
        return "";
    }
    /**
     *
     *
     *
     */
    public void showShoptypeDiaglog() {
        if(shopTypeDialog==null){
          shopTypeDialog =new ShopTypePickerView(this,R.style.dialog_lhp);
          shopTypeDialog.setOnItemClickListener(new ShopTypePickerView.OnItemClickListener() {
              @Override
              public void onClick(String data) {
                  setTvText(R.id.user_tv_user_shoptype,data);
              }
          });

        }
        shopTypeDialog.show();

    }

    /**
     * 弹出地址对话框--级联动的效果
     *
     *
     */
    public void showAddressDiaglog() {
        if(addressDialog==null){
            requestAddressData();
        }
        addressDialog.setSelect(null);
        addressDialog.show();
    }
    /*
     * 显示性别选择*/
    private  void  showSexDialog(@IdRes final int idres){
        if(sexDialog ==null){
            sexDialog =new SexDialog();
        }
        sexDialog.setOnDialogClickListener(new SexDialog.OnDialogClickListener() {
            @Override
            public void onClickNo(SexDialog dialog) {
//                setTvText(idres,"保密");


                dialog.dismiss();
            }

            @Override
            public void onClickMan(SexDialog dialog) {
                setTvText(idres,"男");

                dialog.dismiss();
            }

            @Override
            public void onClickWoman(SexDialog dialog) {
                setTvText(idres,"女");


                dialog.dismiss();
            }

        });
        sexDialog.setContent(this.getActivity());
        sexDialog.show(getFragmentManager());
    }


    /*
     * 显示性别选择*/
    private  void  showYesNoDialog(@IdRes final int idres){
        if(yesNoDialog ==null){
            yesNoDialog =new YesNoDialog();
        }
        yesNoDialog.setOnDialogClickListener(new YesNoDialog.OnDialogClickListener() {
            @Override
            public void onClickNo() {
                setTvText(idres,R.string.否);
            }

            @Override
            public void onClickYes() {
                setTvText(idres,R.string.是);
            }


        });
        yesNoDialog.setContent(this.getActivity());
        yesNoDialog.show(getFragmentManager());
    }
    private void  changeDataUI(BuyerAccount data){
        setTvText(R.id.et_wangwangname,data.getAccountName());
        setTvText(R.id.et_recivename,data.getReceiverName());
        setTvText(R.id.et_recivephone,data.getReceiverPhone());
        setTvText(R.id.tv_user_city,data.getAddressStr());
        setTvText(R.id.et_reciveaddress,data.getAddresDetail());
        setTvText(R.id.user_tv_user_sex,data.getSex()==1?getString(R.string.男):getString(R.string.女));
        setTvText(R.id.user_tv_user_level,getLevelStr(data.getLevel()));
        setTvText(R.id.user_tv_user_shoptype,getShopTypeStr(data.getShoppingType()));
        setTvText(R.id.user_tv_user_ishuabei,data.getIsHuabei()==0?getString(R.string.不是):getString(R.string.是));
        levelPic.setImg(data.getLevelPic());
        huabeiPic.setImg(data.getHuabeiPic());
        realAuthenticatePic.setImg(data.getRealAuthenticatePic());
    }

    /**
     * 请求上传图片
     */
    private void requestUpdateDataOfNewPic(Uri uri,final int id) {
        showLoadingDialog();

        luban(uri, new DefOnUploadPicListener() {
            @Override
            public void onLoadPicFinish(String imgUrl) {
                super.onLoadPicFinish(imgUrl);
//                requestUpdateData(imgUrl);

                pic.put(id,imgUrl);
                switch (id){
                    case R.id.up_huabei:
                        huabeiPic.setImg(imgUrl);
                        break;
                    case R.id.up_levelPic:
                        levelPic.setImg(imgUrl);
                        break;
                    case R.id.up_realAuthenticate:
                        realAuthenticatePic.setImg(imgUrl);
                        break;
                }


            }

            @Override
            public void onLoadPicUnSuccessFinish() {
                super.onLoadPicUnSuccessFinish();
                ToastUtils.show("上传图片失败");
                hideLoadingDialog();

            }
        });

    }

    //请求数据
    private void requestData() {
        accountInfoParam.setBuyerAccountId(id);
        App.getServiceManager().getmService()
                .getBuyerAccount(accountInfoParam.toPswJson())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RainbowObserver<BuyerAccount>() {
                    @Override
                    public void onSuccess(RainbowResultEntity<BuyerAccount> obj) {

                        BuyerAccount data = JsonUtils.fromJson(obj.getResult(),BuyerAccount.class);
                        if(data!=null) {
                            changeDataUI(data);
                        }
                    }

                    @Override
                    public void onAllFinish() {
//                        RefreshLayoutUtils.finish(mRefreshLayout);
                    }
                });
    }
    private void requestAddressData(){
        App.getServiceManager().getmService()
                .getProvinceList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RainbowObserver<List<PAddress>>() {
                    @Override
                    public void onSuccess(RainbowResultEntity<List<PAddress>> obj) {
                        PAddressdata = JsonUtils.getList(obj.getResult(),PAddress.class);
                        if(addressDialog==null) {
                            addressDialog = new AreaPickerView(getActivity(),
                                    R.style.dialog_lhp, PAddressdata);
                            addressDialog.setAreaPickerViewCallback(new AreaPickerView.AreaPickerViewCallback() {
                                @Override
                                public void callback(PAddress pAddress, CAddress cAddress) {
                                    setTvText(R.id.tv_user_city,pAddress.getProvinceName()+cAddress.getCityName());
                                    addressId=cAddress.getAddressId();
                                }


                            });
                        }
                    }

                    @Override
                    public void onAllFinish() {
//                        RefreshLayoutUtils.finish(mRefreshLayout);
                    }
                });
    }
    private void setpic(){
        String url ="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560415409646&di=4b4d5a87786acb4902a92ae2f4d64d89&imgtype=0&src=http%3A%2F%2Fimg009.hc360.cn%2Fg8%2FM08%2FEE%2F89%2FwKhQt1N9cmGEHKQQAAAAAN7_jII892.jpg";
        pic.put(R.id.up_levelPic,url);
        pic.put(R.id.up_huabei,url);
        pic.put(R.id.up_realAuthenticate,url);
    }
    /**
     * 确认修改
     */
    private void commit() {
        setpic();
        String et_wangwangname = getTvText(R.id.et_wangwangname);
        String et_recivename = getTvText(R.id.et_recivename);
        String et_recivephone = getTvText(R.id.et_recivephone);
        String tv_user_city = getTvText(R.id.tv_user_city);
        String et_reciveaddress = getTvText(R.id.et_reciveaddress);
        String user_tv_user_sex = getTvText(R.id.user_tv_user_sex);
        String user_tv_user_level = getTvText(R.id.user_tv_user_level);
        String user_tv_user_shoptype = getTvText(R.id.user_tv_user_shoptype);
        String user_tv_user_ishuabei = getTvText(R.id.user_tv_user_ishuabei);
        String img1 = pic.get(R.id.up_levelPic);
        String img2 = pic.get(R.id.up_huabei);
        String img3 = pic.get(R.id.up_realAuthenticate);
        if (TextUtils.isEmpty(et_wangwangname)) {
            ToastUtils.show("请输入旺旺名称");
            return;
        }
        if (TextUtils.isEmpty(et_recivename)) {
            ToastUtils.show("请输入收货人名称");
            return;
        }
        if (!ValidUtils.phone(et_recivephone)) {
            ToastUtils.show("请输入正确的手机");
            return;
        }
        if (TextUtils.isEmpty(tv_user_city)) {
            ToastUtils.show("请选择收货地区");
            return;
        }
        if (TextUtils.isEmpty(et_reciveaddress)) {
            ToastUtils.show("请输入收货地址");
            return;
        }
        if (TextUtils.isEmpty(user_tv_user_sex)) {
            ToastUtils.show("请选择性别");
            return;
        }
        if (TextUtils.isEmpty(user_tv_user_level)) {
            ToastUtils.show("请选择账号等级");
            return;
        }
        if (TextUtils.isEmpty(user_tv_user_shoptype)) {
            ToastUtils.show("请选择购物类别");
            return;
        }
        if (TextUtils.isEmpty(user_tv_user_ishuabei)) {
            ToastUtils.show("请选择是否使用花呗");
            return;
        }
        if(TextUtils.isEmpty(img1)){
            ToastUtils.show("请上传账号等级图片");
            return;
        }
        if(TextUtils.isEmpty(img2)){
            ToastUtils.show("请上传花呗图片");
            return;
        }
        if(TextUtils.isEmpty(img3)){
            ToastUtils.show("请上传账号认证图片");
            return;
        }

        buyerAccountParam.setAccountName(et_wangwangname);
        buyerAccountParam.setReceiverName(et_recivename);
        buyerAccountParam.setReceiverPhone(et_recivephone);

        buyerAccountParam.setAddressId((int) addressId);

        buyerAccountParam.setAddresDetail(et_reciveaddress);
        buyerAccountParam.setSex(sexDialog.getSex());
        buyerAccountParam.setAge(18);
        buyerAccountParam.setLevel((int) Leveldialog.getmSelectItem().getId());
        buyerAccountParam.setShoppingType(shopTypeDialog.getUpData());
        buyerAccountParam.setLevelPic(img1);
        buyerAccountParam.setHuabeiPic(img2);
        buyerAccountParam.setRealAuthenticatePic(img3);
        buyerAccountParam.setIsHuabei(yesNoDialog.getYesNo());
        showLoadingDialog();
        App.getServiceManager().getmService().addBuyerAccount(buyerAccountParam.toPswJson())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RainbowObserver() {


                    @Override
                    public void onSuccess(RainbowResultEntity obj) {

                        ToastUtils.show("修改成功");
                        setResult(ActResultCode.RESULT_OK);
                        finish();

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
