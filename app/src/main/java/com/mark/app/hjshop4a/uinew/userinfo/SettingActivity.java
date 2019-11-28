package com.mark.app.hjshop4a.uinew.userinfo;

import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.common.utils.ActivityJumpUtils;
import com.mark.app.hjshop4a.common.utils.CleanDataUtils;
import com.mark.app.hjshop4a.data.entity.BaseResultEntity;
import com.mark.app.hjshop4a.data.help.DefaultObserver;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SettingActivity extends BaseActivity {
    @Override
    public int getContentViewResId() {
        return R.layout.activity_setting;
    }

    @Override
    public void initView() {
        setTvText(R.id.titlebar_tv_title, R.string.setting_设置);
        try {
            setTvText(R.id.setting_tv_cache, CleanDataUtils.getTotalCacheSize(getApplicationContext()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setListener() {
        super.setListener();
        setClickListener(R.id.titlebar_iv_return);
        setClickListener(R.id.setting_change_psw);
        setClickListener(R.id.setting_clearsd);
        setClickListener(R.id.setting_login_out);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.titlebar_iv_return:
                finish();
                break;
            case R.id.setting_change_psw:
                //                  修改密码

                ActivityJumpUtils.actModifyPW(this);
                break;
            case R.id.setting_clearsd:
                clear();
                break;
            case R.id.setting_login_out:
                App.get().setLogin(null);
                loginout();

                break;
        }
    }
    private void clear(){
        try {
            CleanDataUtils.clearAllCache(getApplicationContext());
            setTvText(R.id.setting_tv_cache, CleanDataUtils.getTotalCacheSize(getApplicationContext()));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    /**
     * 请求数据登出
     */
    private void loginout() {
        App.getServiceManager().getmService()
                .logout()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver() {

                    @Override
                    public void onSuccess(BaseResultEntity obj) {
                        App.getAppContext().setUserInfo(null);
                        ActivityJumpUtils.actHomePager(getAppCompatActivity());
                    }

                    @Override
                    public void onAllFinish() {
                        super.onAllFinish();
                        hideLoadingDialog();
                    }
                });
    }
}
