package com.mark.app.hjshop4a.ui.userinfo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.view.View;
import android.widget.Switch;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.common.androidenum.userinfo.UserInfoType;
import com.mark.app.hjshop4a.common.utils.BundleUtils;
import com.mark.app.hjshop4a.common.utils.TakeImgUtil;
import com.mark.app.hjshop4a.common.utils.ToastUtils;
import com.mark.app.hjshop4a.common.utils.ValidUtils;
import com.mark.app.hjshop4a.data.entity.BaseResultEntity;
import com.mark.app.hjshop4a.data.help.DefaultObserver;
import com.mark.app.hjshop4a.ui.dialog.AddOneEtParamDialog;
import com.mark.app.hjshop4a.ui.dialog.SelectDateDialog;
import com.mark.app.hjshop4a.ui.dialog.SexDialog;
import com.mark.app.hjshop4a.ui.dialog.factory.FunctionDialogFactory;
import com.mark.app.hjshop4a.ui.userinfo.model.CommitUserInfo;
import com.mark.app.hjshop4a.ui.userinfo.model.UserInfo;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by pc on 2018/4/16.
 */

public class CertificationInfoActivity extends BaseActivity {
    private AddOneEtParamDialog mAddOneEtParamDialog;
    private SexDialog sexDialog;
    private SelectDateDialog selectDateDialog;
    private UserInfo mData;
    private CommitUserInfo commitUserInfo;
    @Override
    public int getContentViewResId() {
        return R.layout.activity_certification_information;
    }

    @Override
    public void getIntentParam(Bundle bundle) {
        super.getIntentParam(bundle);
        if(bundle!=null)
        {
            mData= (UserInfo) bundle.getSerializable("UserInfoType");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sexDialog =null;
        mAddOneEtParamDialog=null;
        selectDateDialog=null;
    }

    @Override
    public void initView() {
     setTvText(R.id.titlebar_tv_title,"认证资料");

        bindData();
    }
    private void bindData(){
        if(mData!=null){
            setTvText(R.id.user_tv_user_name,mData.getUserRealName());
            setTvText(R.id.user_tv_user_sex,SwitchSex(mData.getGender()));
            setTvText(R.id.user_tv_user_date,mData.getBirthday());
            setTvText(R.id.certification_tv_user_invitation,mData.getRecommend());
            setTvText(R.id.tv_id_card,mData.getIdcard());
        }
    }
    @Override
    public void setListener() {
        setClickListener(R.id.titlebar_iv_return);
        setClickListener(R.id.user_layout_user_name);
        setClickListener(R.id.user_layout_user_sex);
        setClickListener(R.id.user_layout_user_date);
        setClickListener(R.id.user_layout_user_invitation);
        setClickListener(R.id.rl_id_card);
        setClickListener(R.id.rl_id_card_img);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.titlebar_iv_return:
                finish();
                break;
            case R.id.user_layout_user_name:
//                姓名
               ShowDialog(R.id.user_layout_user_name,false);
                break;
            case R.id.user_layout_user_sex:
//                性别
                showSexDialog(R.id.user_tv_user_sex);
                break;
            case R.id.user_layout_user_date:
//                日期
                showSelectdateDialog(R.id.user_tv_user_date);
                break;
            case R.id.user_layout_user_invitation:
//                推荐人

                break;
            case R.id.rl_id_card:
                //身份证号码

                ShowDialog( R.id.rl_id_card,false);
                break;
            case R.id.rl_id_card_img:
                //身份证
                Intent intent =new Intent(this,IDCardImgActivity.class);
                BundleUtils.getInstance().putString("userIdCardFront",mData.getIdcardFront()).putString("userIdCardSide",mData.getIdcardSide()).addIntent(intent);
                this.startActivityForResult(intent,1);
                break;
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==1){
           mData.setIdcardFront(data.getStringExtra("userIdCardFront"));
           mData.setIdcardSide(data.getStringExtra("userIdCardSide"));
        }

    }
    /**
     * 显示一个时间的对话框
     */
    private void showSelectdateDialog(@IdRes final int idres) {
        if(selectDateDialog==null){
            selectDateDialog = new SelectDateDialog();
        }
        selectDateDialog.setOnDialogClickListener(new SelectDateDialog.OnDialogClickListener() {
            @Override
            public void onClickDate(String str) {
//                setTvText(idres,str);
                CommitUserInfo commitUserInfo =new CommitUserInfo();
                commitUserInfo.setBirthday(str);
                requestData(idres,commitUserInfo,str);
            }
        });
        selectDateDialog.showDateDialog(this);
    }


    //    填写信息dialog
    private  void  ShowDialog(final int type,Boolean isNumber){
        AddOneEtParamDialog mAddOneEtParamDialog = AddOneEtParamDialog.getInstance(isNumber,mData.getIdcard());

        mAddOneEtParamDialog.setOnDialogClickListener(new AddOneEtParamDialog.DefOnDialogClickListener() {
            @Override
            public void onClickCommit(AddOneEtParamDialog dialog, String data) {
                CommitUserInfo commitUserInfo =new CommitUserInfo();
                switch (type){
                    case R.id.user_layout_user_name:
        //真实姓名
                        commitUserInfo.setUserRealName(data);
//                        setTvText(R.id.user_tv_user_name,data);
                        requestData(R.id.user_tv_user_name,commitUserInfo,data);  break;
                    case R.id.rl_id_card:
//身份证号
                        if(ValidUtils.IDCard(data)){
                            commitUserInfo.setUserIdCard(data);
//                            setTvText(R.id.tv_id_card,data);
                            requestData(R.id.tv_id_card,commitUserInfo,data);break;
                        }else {
                            ToastUtils.show("身份证号格式不正确");
                        }



                }
                dialog.dismiss();
            }
        });

        mAddOneEtParamDialog.show(this.getFragmentManager());
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
                CommitUserInfo commitUserInfo =new CommitUserInfo();
                commitUserInfo.setGender("0");
                requestData(idres,commitUserInfo,"保密");
                dialog.dismiss();
            }

            @Override
            public void onClickMan(SexDialog dialog) {
//                setTvText(idres,"男");
                CommitUserInfo commitUserInfo =new CommitUserInfo();
                commitUserInfo.setGender("1");
                requestData(idres,commitUserInfo,"男");
                dialog.dismiss();
            }

            @Override
            public void onClickWoman(SexDialog dialog) {
//                setTvText(idres,"女");
                CommitUserInfo commitUserInfo =new CommitUserInfo();
                commitUserInfo.setGender("2");
                requestData(idres,commitUserInfo,"女");
                dialog.dismiss();
            }

        });
        sexDialog.setContent(this.getActivity());
        sexDialog.show(getFragmentManager());
    }
    private String SwitchSex(int type){
        switch (type){
            case 0: return "保密";
            case 1: return "男";
            case 2: return "女";
            default: return "";
    }
    }

    /**
     * 请求数据
     */
    private void requestData(final int type, CommitUserInfo userInfo, final String data) {
        showLoadingDialog();

        App.getServiceManager().getPdmService()
                .setUserInfo(UserInfoType.CERTIFI,userInfo.getMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver() {


                    @Override
                    public void onSuccess(BaseResultEntity obj) {

                        setTvText(type,data);
                    }

                    @Override
                    public void onAllFinish() {
                        super.onAllFinish();
                        hideLoadingDialog();
                    }
                });
    }
}
