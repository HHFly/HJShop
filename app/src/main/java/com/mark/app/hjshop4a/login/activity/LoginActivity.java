package com.mark.app.hjshop4a.login.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.common.androidenum.other.BundleKey;
import com.mark.app.hjshop4a.common.utils.ActivityJumpUtils;
import com.mark.app.hjshop4a.common.utils.BundleUtils;
import com.mark.app.hjshop4a.common.utils.EditTextUtils;
import com.mark.app.hjshop4a.common.utils.PdUtils;
import com.mark.app.hjshop4a.common.utils.ToastUtils;
import com.mark.app.hjshop4a.common.valid.ValidUtils;
import com.mark.app.hjshop4a.data.entity.BaseResultEntity;
import com.mark.app.hjshop4a.data.help.DefaultObserver;
import com.mark.app.hjshop4a.login.androidenum.LoginSource;
import com.mark.app.hjshop4a.login.model.LoginRepo;
import com.mark.app.hjshop4a.ui.factory.NormalDialogFactory;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by pc on 2018/4/12.
 */

public class LoginActivity extends BaseActivity {
    //来源：@LoginSource
    int mSource;
    //密码是否可见
    boolean isPwdShow;
    //是否自动登录
    boolean isautologin;

    @Override
    public int getContentViewResId() {
      return R.layout.activity_login;
    }

    @Override
    public void initView() {
        mSource = BundleUtils.getInt(this, BundleKey.SOURCE, LoginSource.NORMAL);
        //设置标题栏
        setTvText(R.id.titlebar_tv_title, R.string.login_msg_login);
        setTvText(R.id.titlebar_tv_right,R.string.login_msg_register);
//        setViewVisibility(R.id.titlebar_iv_return, false);

    }

    @Override
    public void setListener() {
       setClickListener(R.id.login_tv_quick_login);
       setClickListener(R.id.login_tv_forget_pwd);
       setClickListener(R.id.titlebar_tv_right);
       setClickListener(R.id.titlebar_iv_return);
       setClickListener(R.id.login_iv_eye);
       setClickListener(R.id.login_btn);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.login_tv_quick_login:{
                isautologin =!isautologin;
                setViewSelected(R.id.login_tv_quick_login, isautologin);
                break;
            }
            case R.id.login_tv_forget_pwd:{
                //忘记密码
                String strPhone = getTvText(R.id.login_et_username);
                ActivityJumpUtils.actForgetPwd(getActivity(), strPhone);
                break;
            }
            case R.id.titlebar_tv_right:{
//               注册
                String strPhone = getTvText(R.id.login_et_username);
                ActivityJumpUtils.actRegister(getActivity(), strPhone);
                break;
            }
            case R.id.titlebar_iv_return:{
                finish();
                break;
            }
            case R.id.login_iv_eye: {
                //密码眼睛
                isPwdShow = !isPwdShow;
                changedEditTextVisiable(isPwdShow);
                break;
            }
            case R.id.login_btn:{
                //   登录
                login();
                break;
            }
        }
    }
//d登录
    private void login() {
        final String account = getTvText(R.id.login_et_username);
        String password = getTvText(R.id.login_et_pwd);
        if (!ValidUtils.get().phone(account)) {
            NormalDialogFactory.getNormalDialogOneBtn()
                    .setContentText(R.string.login_手机号格式错误)
                    .show(getFragmentManager());
            return;
        } else if (!ValidUtils.get().password(password)) {
            ToastUtils.show(R.string.login_密码由6到20位字符组成);
            return;
        }
//        showLoadingDialog();
//        App.getServiceManager().login(phoneCodeParam.getMap(), account, PdUtils.getMD5(password))
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new DefaultObserver<LoginRepo>() {
//                    @Override
//                    public void onSuccess(BaseResultEntity<LoginRepo> obj) {
//                        LoginRepo repo = obj.getResult();
//                        repo.setNowTime(obj.getNowTime());
//
//                        //保存登录信息
//                        App.get().setLogin(repo);
//
//                        //初始化设置信息
//
//
//                        //成功跳转
//                        if (mSource == LoginSource.SPLASH) {
////                            ActJumpUtils.actHome(getActivity());
//                            finish();
//                        } else {
//                            setResult(RESULT_OK);
//                            finish();
//                        }
//                    }
//
//                    @Override
//                    public void onAllFinish() {
//                        super.onAllFinish();
//                        hideLoadingDialog();
//                    }
//                });
    }

    /**
     * 密码输入框是否可见
     *
     * @param flag
     */
    private void changedEditTextVisiable(boolean flag) {
        EditText et = getView(R.id.login_et_pwd);
        EditTextUtils.changedEditTextVisiable(et, flag);
        setViewSelected(R.id.login_iv_eye, flag);
    }

}
