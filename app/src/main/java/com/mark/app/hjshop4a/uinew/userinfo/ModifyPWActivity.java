package com.mark.app.hjshop4a.uinew.userinfo;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.common.androidenum.userinfo.UserInfoType;
import com.mark.app.hjshop4a.common.utils.ActivityJumpUtils;
import com.mark.app.hjshop4a.common.utils.EditTextUtils;
import com.mark.app.hjshop4a.common.utils.MD5Utils;
import com.mark.app.hjshop4a.common.utils.ToastUtils;
import com.mark.app.hjshop4a.common.utils.ValidUtils;
import com.mark.app.hjshop4a.data.entity.BaseResultEntity;
import com.mark.app.hjshop4a.data.entity.RainbowResultEntity;
import com.mark.app.hjshop4a.data.help.DefaultObserver;
import com.mark.app.hjshop4a.data.help.RainbowObserver;
import com.mark.app.hjshop4a.ui.userinfo.model.CommitUserInfo;
import com.mark.app.hjshop4a.uinew.login.model.UpdateLoginPSWParam;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by pc on 2018/4/16.
 */

public class ModifyPWActivity extends BaseActivity {
    private  boolean newflag =false;
    private  boolean oldflag =false;
    UpdateLoginPSWParam pswParam =new UpdateLoginPSWParam();
    @Override
    public int getContentViewResId() {
        return R.layout.activity_user_modify_pwd;
    }

    @Override
    public void initView() {
        setTvText(R.id.titlebar_tv_title,"设置密码");
    }

    @Override
    public void setListener() {
        setClickListener(R.id.iv_new_eye);
        setClickListener(R.id.iv_old_eye);
        setClickListener(R.id.btn);
        setClickListener(R.id.titlebar_iv_return);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.titlebar_iv_return:
                finish();
                break;
            case R.id.iv_old_eye:
                oldflag=!oldflag;
                EditText oet = getView(R.id.et_old_pwd);
                EditTextUtils.changedEditTextVisiable(oet, oldflag);
                setViewSelected(R.id.iv_old_eye, oldflag);
                break;
            case R.id.iv_new_eye:
                newflag =!newflag;
                EditText net = getView(R.id.et_new_pwd);
                EditTextUtils.changedEditTextVisiable(net, newflag);
                setViewSelected(R.id.iv_new_eye, newflag);
                break;
            case R.id.btn:
//                确定
                commit();
                break;
        }
    }
    private  void commit(){
        String old =getTvText(R.id.et_old_pwd);
        String news =getTvText(R.id.et_new_pwd);
        String newsAgain =getTvText(R.id.modify_et_pwd_again);
        if(TextUtils.isEmpty(old)){
            ToastUtils.show("请输入旧密码");
            return;
        }
        if(TextUtils.isEmpty(news)){
            ToastUtils.show("请输入新密码");
            return;
        }
        if(TextUtils.isEmpty(newsAgain)){
            ToastUtils.show("请确认密码");
            return;
        }
        if(!ValidUtils.pwd(old)){
            ToastUtils.show("旧密码错误，请重试");
            return;
        }
        if(!ValidUtils.pwd(news)){
            ToastUtils.show(R.string.login_密码由6到20位字符组成);
            return;
        }
        if(!TextUtils.equals(news,newsAgain)){
            ToastUtils.show("两次输入密码不一致");
            return;
        }
        pswParam.setType(1);
        pswParam.setNewPassword(news);
        pswParam.setOldPassword(old);
        requestData();
    }
    /**
     * 请求数据
     */
    private void requestData() {
        showLoadingDialog();

        App.getServiceManager().getmService()
                .updateLoginPassword(pswParam.toPswJson())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RainbowObserver() {


                    @Override
                    public void onSuccess(RainbowResultEntity obj) {
                        ToastUtils.show("修改成功");
                        App.get().setLogin(null);
                        loginout();
                        ActivityJumpUtils.actHomePager(getAppCompatActivity());
                    }

                    @Override
                    public void onAllFinish() {
                        super.onAllFinish();
                        hideLoadingDialog();
                    }
                });
    }
    /**
     * 请求数据登出
     */
    private void loginout() {
        App.getServiceManager().getPdmService()
                .logout()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver() {

                    @Override
                    public void onSuccess(BaseResultEntity obj) {

                    }

                    @Override
                    public void onAllFinish() {
                        super.onAllFinish();
                        hideLoadingDialog();
                    }
                });
    }

}
