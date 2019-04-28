package com.mark.app.hjshop4a.uinew.homepager;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.base.fragment.BaseFragment;
import com.mark.app.hjshop4a.common.androidenum.homepager.RoleType;
import com.mark.app.hjshop4a.common.utils.ActivityJumpUtils;
import com.mark.app.hjshop4a.data.entity.BaseResultEntity;
import com.mark.app.hjshop4a.data.help.DefaultObserver;
import com.mark.app.hjshop4a.ui.dialog.factory.FunctionDialogFactory;


import com.mark.app.hjshop4a.ui.login.activity.LoginSwitchActivity;
import com.mark.app.hjshop4a.uinew.homepager.adapter.MeAdapter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MeFragment extends BaseFragment{
    private final static int REQUESTCODE = 1; // 返回的结果码
    private MeAdapter mAdapter;
    @Override
    public int getContentResId() {
        return R.layout.fragment_me;
    }

    @Override
    public void findView() {

    }

    @Override
    public void setListener() {
        setClickListener(R.id.titlebar_tv_right);
        setClickListener(R.id.titlebar_tv_title);
    }

    @Override
    public void initView() {
        setTvText(R.id.titlebar_tv_right,"退出");

        initRvAdapter(new MeCenterInfo());
        requestData();
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

    /**
     * 请求数据
     */
    private void requestData( ) {
//        showLoadingDialog();
        App.getServiceManager().getPdmService()
                .center()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<MeCenterInfo>() {


                    @Override
                    public void onSuccess(BaseResultEntity<MeCenterInfo> obj) {
                        MeCenterInfo data = obj.getResult();
                        initRvAdapter(data);
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
    private void initRvAdapter( MeCenterInfo data) {

        RecyclerView rv = getView(R.id.recyclerView);
       if(mAdapter==null) {
           if (rv != null) {
               rv.setLayoutManager(new LinearLayoutManager(getContext()));
               rv.setAdapter(mAdapter);
           }
           mAdapter.setOnItemClickListener(new MeAdapter.OnItemClickListener() {
               @Override
               public void onClickUserInfo() {
                   ActivityJumpUtils.actUserInfo(getActivity());
               }

               @Override
               public void onClickUserPic(int id) {
                   FunctionDialogFactory.showTakePhoneIDDialog(getActivity(), id);
               }


           });
       }else {
           mAdapter.setmData(data);
           mAdapter.notifyDataSetChanged();
       }
    }
}
