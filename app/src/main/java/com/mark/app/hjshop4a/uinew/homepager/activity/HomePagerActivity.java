package com.mark.app.hjshop4a.uinew.homepager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.View;


import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.app.AppContext;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.base.fragment.BaseFragment;
import com.mark.app.hjshop4a.common.androidenum.homepager.HPTabType;
import com.mark.app.hjshop4a.common.androidenum.other.BundleKey;
import com.mark.app.hjshop4a.common.update.DownloadDialogUtils;
import com.mark.app.hjshop4a.common.utils.ActivityJumpUtils;
import com.mark.app.hjshop4a.common.utils.FragmentUtils;
import com.mark.app.hjshop4a.common.utils.LogUtils;
import com.mark.app.hjshop4a.common.utils.SPUtil;
import com.mark.app.hjshop4a.common.utils.ToastUtils;
import com.mark.app.hjshop4a.ui.homepager.fragment.ClassifyFragment;


import com.mark.app.hjshop4a.uinew.bindinfo.dialog.ChooseAccountDialog;
import com.mark.app.hjshop4a.uinew.bindinfo.model.AccountInfoPass;
import com.mark.app.hjshop4a.uinew.homepager.fragment.HomeFragment;
import com.mark.app.hjshop4a.uinew.homepager.fragment.MeFragment;
import com.mark.app.hjshop4a.uinew.homepager.fragment.StatusFragment;
import com.mark.app.hjshop4a.uinew.order.OrderFragment;


/**
 * 首页
 */
public class HomePagerActivity extends BaseActivity {

    //View
    private View tab1;      //首页
    private View tab2;      //分
    private View tab3;      //
    private View tab4;      //我的
    private View tab5;      //客服
   private HomeFragment homeFragment;
   private ClassifyFragment classifyFragment;
   private OrderFragment orderFrament;
    private MeFragment meFragment;
    private StatusFragment statusFragment;
    private BaseFragment mCurFragment;
    private ChooseAccountDialog chooseAccountDialog;
    //tab类型
    private int mType = HPTabType.HOME;
    private boolean isSecondBack;//双击退出

    @Override
    public int getContentViewResId() {
        return R.layout.activity_home_pager;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        getIntentParam(intent.getExtras());
        selectTab(mType);
    }

    @Override
    public void getIntentParam(Bundle bundle) {
        if (bundle != null) {
            mType = bundle.getInt(BundleKey.TYPE);
        }
    }

    @Override
    public void findView() {
        tab1 = getView(R.id.hp_layout_tab1);
        tab2 = getView(R.id.hp_layout_tab2);
        tab3 = getView(R.id.hp_layout_tab3);
        tab4 = getView(R.id.hp_layout_tab4);
        tab5 =getView(R.id.hp_layout_tab5);
    }

    @Override
    public void setListener() {
        tab1.setOnClickListener(this);
        tab2.setOnClickListener(this);
        tab3.setOnClickListener(this);
        tab4.setOnClickListener(this);
        tab5.setOnClickListener(this);
    }

    @Override
    public void initView() {
        switchActJump();
        createFragment();
        selectTab(mType);
        validUpdate();
        App.get().setHomePagerActivity(this);
        if (!App.hasToken()) {
            ActivityJumpUtils.actLogin(getActivity());
        }
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.hp_layout_tab1: {
                selectTab(tab1);
                break;
            }
            case R.id.hp_layout_tab2: {
                if (App.hasToken()) {
                    selectTab(tab2);
                } else {
                    ActivityJumpUtils.actLogin(getActivity());
                }

//                selectTab(tab2);
                break;
            }
            case R.id.hp_layout_tab3: {
                if (App.hasToken()) {
                    showChooseAccountDiaglog();
                } else {
                    ActivityJumpUtils.actLogin(getActivity());
                }



                break;
            }
            case R.id.hp_layout_tab4: {
//                selectTab(tab4);
                if (App.hasToken()) {
                    selectTab(tab4);
                } else {
                    ActivityJumpUtils.actLogin(getActivity());
                }
                break;

            }
            case R.id.hp_layout_tab5:
                selectTab(tab5);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DownloadDialogUtils.onDestroy();
    }
    public void showChooseAccountDiaglog() {
        if(chooseAccountDialog==null){
            chooseAccountDialog = new ChooseAccountDialog(this, R.style.dialog_lhp);
            chooseAccountDialog.setOnItemClickListener(new ChooseAccountDialog.OnItemClickListener() {
                @Override
                public void onClick(AccountInfoPass data) {
                    orderFrament.setId(data.getBuyerAccountId());
                    selectTab(tab3);
                }
            });

        }
        chooseAccountDialog.show();

    }
    /**
     * 初始化fragment
     */
    private void createFragment() {
        homeFragment =new HomeFragment();
        meFragment = new MeFragment();
        classifyFragment =new ClassifyFragment();
        orderFrament = new OrderFragment();
        statusFragment =new StatusFragment();
    }

    /**
     * 是否启动引导页
     */
    private void switchActJump() {
//        boolean isOpenCustom = CustomerServiceUtils.notificationEntrance(getActivity());

            //是否第一次引导
            if (isFirstGuide()) {
                LogUtils.logFormat(this, "switchActJump", "go to GuideActivity");
//                ActivityJumpUtils.actGuideNORMAL(getActivity());
            } else {
                LogUtils.logFormat(this, "switchActJump", "go to StartActivity");
//                ActivityJumpUtils.actStart(getActivity());
//                ActivityJumpUtils.actGuideNORMAL(getActivity());
            }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            if (isSecondBack) {
                finish();
            } else {
                String info = getString(R.string.home_再按一次退出);
                ToastUtils.show(info);
                isSecondBack = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isSecondBack = false;
                    }
                }, 2000);
            }
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    /**
     * 选择tab
     *
     * @param type
     */
    private void selectTab(@HPTabType int type) {
        switch (type) {
            case HPTabType.HOME: {
                selectTab(tab1);
                break;
            }
            case HPTabType.ORDERLIST: {
                selectTab(tab2);
                break;
            }
            case HPTabType.ORDER: {
                selectTab(tab3);
                break;
            }
            case HPTabType.ME: {
                selectTab(tab4);
                break;
            }
            case HPTabType.SERVICE: {
                selectTab(tab5);
                break;
            }
        }
    }

    /**
     * 选择tab
     *
     * @param view
     */
    private void selectTab(View view) {
        tab1.setSelected(false);
        tab2.setSelected(false);
        tab3.setSelected(false);
        tab4.setSelected(false);
        tab5.setSelected(false);
        view.setSelected(true);

        switch (view.getId()) {
            case R.id.hp_layout_tab1: {
                switchFragment(homeFragment);
                break;
            }
            case R.id.hp_layout_tab2: {
                switchFragment(statusFragment);
                break;
            }
            case R.id.hp_layout_tab3: {
                switchFragment(orderFrament);
                break;
            }
            case R.id.hp_layout_tab4: {
                switchFragment(meFragment);
                break;
            }
            case R.id.hp_layout_tab5: {
                switchFragment(classifyFragment);

                break;
            }
        }

    }

    
    /**
     * 选择Fragment
     *
     * @param fragment
     */
    private void switchFragment(Fragment fragment) {
        mCurFragment = FragmentUtils.selectFragment(this, mCurFragment, fragment, R.id.hp_layout_content);
        mCurFragment.onUnFirstResume();
    }

    /**
     * 是否第一次引导
     *
     * @return
     */
    private boolean isFirstGuide() {
        SPUtil utils = SPUtil.getInstance(this);
        String VERSION_CODE = "versionCode";
        //当前版本号
        int curVersion = AppContext.versionCode();
        //旧的版本号
        int oldVersion = utils.getInt(VERSION_CODE, 0);
        if (curVersion > oldVersion) {
            //如果当前版本号与旧版本号不一致
            //新版本，保存当前版本号
            utils.putInt(VERSION_CODE, curVersion);
            return true;
        } else {
            return false;
        }
    }

    public StatusFragment getStatusFragment() {
        if(statusFragment==null){
            statusFragment=new StatusFragment();
        }
        return statusFragment;
    }
}
