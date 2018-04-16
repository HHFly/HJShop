package com.mark.app.hjshop4a.ui.userinfo;

import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.ui.dialog.AddOneEtParamDialog;
import com.mark.app.hjshop4a.ui.dialog.SelectDateDialog;
import com.mark.app.hjshop4a.ui.dialog.SexDialog;

/**
 * Created by pc on 2018/4/16.
 */

public class CertificationInfoActivity extends BaseActivity {
    private AddOneEtParamDialog mAddOneEtParamDialog;
    private SexDialog sexDialog;
    private SelectDateDialog selectDateDialog;
    @Override
    public int getContentViewResId() {
        return R.layout.activity_certification_information;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sexDialog =null;
        mAddOneEtParamDialog=null;
    }

    @Override
    public void initView() {
     setTvText(R.id.titlebar_tv_title,"认证资料");

    }

    @Override
    public void setListener() {
        setClickListener(R.id.titlebar_iv_return);
        setClickListener(R.id.user_layout_user_name);
        setClickListener(R.id.user_layout_user_sex);
        setClickListener(R.id.user_layout_user_date);
        setClickListener(R.id.user_layout_user_invitation);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.titlebar_iv_return:
                finish();
                break;
            case R.id.user_layout_user_name:
//                姓名
                showAddOneParamDialog(R.string.certificationinfo_请输入用户名,R.id.user_tv_user_name);
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
                showAddOneParamDialog(R.string.certificationinfo_请输入推荐人号码,R.id.certification_tv_user_invitation);
                break;

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
                setTvText(idres,str);
            }
        });
        selectDateDialog.showDateDialog(this);
    }

    /**
     * 显示一个参数的对话框
     */
    private void showAddOneParamDialog(@StringRes int paramEmptyHint, @IdRes final int idres) {
        if (mAddOneEtParamDialog == null) {
            mAddOneEtParamDialog = AddOneEtParamDialog.getInstance();
        }
        mAddOneEtParamDialog.setOnDialogClickListener(new AddOneEtParamDialog.DefOnDialogClickListener() {
            @Override
            public void onClickCommit(AddOneEtParamDialog dialog, String data) {
//                requestUpdateParam(type, data);
                setTvText(idres,data);
                dialog.dismiss();
            }
        });
//        mAddOneEtParamDialog.setTvParamName(paramName);
        mAddOneEtParamDialog.setTvEmptyParamHint(paramEmptyHint);
        mAddOneEtParamDialog.show(getFragmentManager());
    }


    /*
    * 显示性别选择*/
    private  void  showSexDialog(@IdRes final int idres){
        if(sexDialog ==null){
            sexDialog =new SexDialog();
        }
        sexDialog.setOnDialogClickListener(new SexDialog.OnDialogClickListener() {
            @Override
            public void onClickNo() {
                setTvText(idres,"保密");
            }

            @Override
            public void onClickMan() {
                setTvText(idres,"男");
            }

            @Override
            public void onClickWoman() {
                setTvText(idres,"女");
            }
        });
        sexDialog.setContent(this.getActivity());
        sexDialog.show(getFragmentManager());
    }
}
