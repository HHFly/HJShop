package com.mark.app.hjshop4a.ui.homepager.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.base.fragment.BaseFragment;
import com.mark.app.hjshop4a.common.androidenum.homepager.RoleType;
import com.mark.app.hjshop4a.common.androidenum.userinfo.UserInfoType;
import com.mark.app.hjshop4a.common.listener.DefOnUploadPicListener;
import com.mark.app.hjshop4a.common.utils.ActivityJumpUtils;
import com.mark.app.hjshop4a.common.utils.TakeImgUtil;
import com.mark.app.hjshop4a.common.utils.ToastUtils;
import com.mark.app.hjshop4a.data.entity.BaseResultEntity;
import com.mark.app.hjshop4a.data.help.DefaultObserver;
import com.mark.app.hjshop4a.model.login.model.LoginType;
import com.mark.app.hjshop4a.ui.bankcard.model.InfoBank;
import com.mark.app.hjshop4a.ui.dialog.SelectAddressDialog;
import com.mark.app.hjshop4a.ui.dialog.factory.FunctionDialogFactory;
import com.mark.app.hjshop4a.ui.dialog.wheelviewlibrary.listener.SelectInterface;
import com.mark.app.hjshop4a.ui.homepager.adapter.MeAdapter;
import com.mark.app.hjshop4a.ui.homepager.model.MeCenterInfo;
import com.mark.app.hjshop4a.ui.login.activity.LoginSwitchActivity;
import com.mark.app.hjshop4a.ui.userinfo.model.CommitUserInfo;
import com.mark.app.hjshop4a.ui.userinfo.model.UserInfo;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

/**
 * 首页-我的
 * Created by lenovo on 2017/8/27.
 */

public class MeFragment extends BaseFragment  {
    private MeAdapter mAdapter;
    private final static int REQUESTCODE = 1; // 返回的结果码
  private  MeFragment meFragment;
  private int role;

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public int getContentResId() {
        return R.layout.fragment_me;
    }

    @Override
    public void findView() {
        role=App.getAppContext().getRoleType();
        meFragment=this;
    }

    @Override
    public void setListener() {
        setClickListener(R.id.titlebar_tv_right);
        setClickListener(R.id.titlebar_tv_title);

    }

    @Override
    public void initView() {
        setTvText(R.id.titlebar_tv_right,"切换账号");
        initRvAdapter(role,false,new MeCenterInfo());
        requestData(role,false);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.titlebar_tv_right:
//            ActivityJumpUtils.actLoginSwicth(getActivity() );
//                退出
                Intent intent = new Intent(getActivity(), LoginSwitchActivity.class);

                this.startActivityForResult(intent,REQUESTCODE);
                break;
            case R.id.titlebar_tv_title:
                ActivityJumpUtils.actLogin(getActivity());
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==1){
            LoginType loginType= (LoginType) data.getSerializableExtra("LoginType");
            requestData(loginType.getRoleType(),false);
        }
        TakeImgUtil.onActivityResult(getActivity(), requestCode, resultCode, data, new TakeImgUtil.CallBack() {
            @Override
            public void back(Uri var1, int id) {
                requestUpdateDataOfNewPic(var1,id);

            }
        });

    }
    /**
     * 请求更新数据，有新图片
     */
    private boolean requestUpdateDataOfNewPic(Uri uri,final int id) {
        showLoadingDialog();
        final boolean[] isSuccess = new boolean[1];
        luban(uri, new DefOnUploadPicListener() {
            @Override
            public void onLoadPicFinish(String imgUrl) {
                super.onLoadPicFinish(imgUrl);
                CommitUserInfo commitUserInfo =new CommitUserInfo();
                commitUserInfo.setUserHeadImg(imgUrl);
//                setSdvImgInside(id,imgUrl);
                changelogoImg(UserInfoType.HEADIMG,commitUserInfo);
                isSuccess[0] =true;
            }

            @Override
            public void onLoadPicUnSuccessFinish() {
                super.onLoadPicUnSuccessFinish();
//                ToastUtils.show("上传图片失败");
                hideLoadingDialog();
                isSuccess[0] =false;
            }
        });
        return isSuccess[0];
    }
    /**
     * 请求数据
     */
    private void changelogoImg(int type,CommitUserInfo userInfo) {
//        showLoadingDialog();

        App.getServiceManager().getPdmService()
                .setUserInfo(UserInfoType.HEADIMG,userInfo.getMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver() {


                    @Override
                    public void onSuccess(BaseResultEntity obj) {
                        ToastUtils.show("修改成功");
                        requestData(role,true);
                    }

                    @Override
                    public void onAllFinish() {
                        super.onAllFinish();
                        hideLoadingDialog();
                    }
                });
    }
    /**
     * 初始化adapter
     *
     *
     */
    private void initRvAdapter( int role,boolean isRefresh ,MeCenterInfo data) {

            RecyclerView rv = getView(R.id.recyclerView);
            switch (role) {
                case RoleType.MEMBER:
                    mAdapter = new MeAdapter(role, getActivity(), data);
                    break;
                case RoleType.BUSINESS:
                    mAdapter = new MeAdapter(role, getActivity(), data);
                    break;
                case RoleType.AREAAGENT:
                    mAdapter = new MeAdapter(role, getActivity(), data);
                    break;
                case RoleType.PROVINCIALAGENT:
                    mAdapter = new MeAdapter(role, getActivity(), data);
                    break;
            }
            rv.setLayoutManager(new LinearLayoutManager(getContext()));
            rv.setAdapter(mAdapter);
            mAdapter.setOnItemClickListener(new MeAdapter.OnItemClickListener() {
                @Override
                public void onClickUserInfo() {
                    ActivityJumpUtils.actUserInfo(getActivity());
                }

                @Override
                public void onClickUserPic(int id) {
                    FunctionDialogFactory.showTakePhoneIDDialog(meFragment, id);
                }


            });

    }
    /**
     * 请求数据
     */
    private void requestData(final int roletype, final Boolean isRefresh) {
//        showLoadingDialog();
        App.getServiceManager().getPdmService()
                .center(roletype)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<MeCenterInfo>() {


                    @Override
                    public void onSuccess(BaseResultEntity<MeCenterInfo> obj) {
                        MeCenterInfo data = obj.getResult();
                        initRvAdapter(roletype,isRefresh,data);
                    }

                    @Override
                    public void onAllFinish() {
                        super.onAllFinish();
                        hideLoadingDialog();
                    }
                });
    }

}
