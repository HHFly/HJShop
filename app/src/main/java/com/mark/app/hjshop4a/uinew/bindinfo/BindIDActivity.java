package com.mark.app.hjshop4a.uinew.bindinfo;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.common.androidenum.other.ActResultCode;
import com.mark.app.hjshop4a.common.listener.DefOnUploadPicListener;
import com.mark.app.hjshop4a.common.utils.JsonUtils;
import com.mark.app.hjshop4a.common.utils.TakeImgUtil;
import com.mark.app.hjshop4a.common.utils.ToastUtils;
import com.mark.app.hjshop4a.common.utils.ValidUtils;
import com.mark.app.hjshop4a.data.entity.RainbowResultEntity;
import com.mark.app.hjshop4a.data.help.RainbowObserver;
import com.mark.app.hjshop4a.ui.dialog.factory.FunctionDialogFactory;
import com.mark.app.hjshop4a.uinew.bindinfo.model.IDInfoParam;
import com.mark.app.hjshop4a.uinew.userinfo.model.UserCardInfo;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class BindIDActivity extends BaseActivity {
    IDInfoParam idInfoParam =new IDInfoParam();
    private Map<Integer,String> pic =new HashMap<>();

    @Override
    public int getContentViewResId() {
        return R.layout.activity_bind_id;
    }

    @Override
    public void initView() {
        setTvText(R.id.titlebar_tv_title, R.string.绑定身份信息);
        requestData();
    }

    @Override
    public void setListener() {
        setClickListener(R.id.titlebar_iv_return);
        setClickListener(R.id.ll_front);
        setClickListener(R.id.ll_after);
        setClickListener(R.id.btn);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.titlebar_iv_return:
                finish();
                break;
            case R.id.ll_front:
                FunctionDialogFactory.showTakePhoneIDDialog(this,R.id.imagebtn1);
                break;
            case R.id.ll_after:
                FunctionDialogFactory.showTakePhoneIDDialog(this,R.id.imagebtn2);
                break;
            case R.id.btn:
                commit();
                break;
        }
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
    /**
     * 请求更新数据
     */
    private void requestUpdateDataOfNewPic(Uri uri,final int id) {
        showLoadingDialog();

        luban(uri, new DefOnUploadPicListener() {
            @Override
            public void onLoadPicFinish(String imgUrl) {
                super.onLoadPicFinish(imgUrl);
//                requestUpdateData(imgUrl);

                pic.put(id,imgUrl);



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
        showLoadingDialog();
        App.getServiceManager().getmService()
                .getUserCardInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RainbowObserver<UserCardInfo>() {
                    @Override
                    public void onSuccess(RainbowResultEntity<UserCardInfo> obj) {
                        UserCardInfo data = JsonUtils.fromJson(obj.getResult(), UserCardInfo.class);
                        if(data!=null) {
                            setTvText(R.id.et_name, data.getRealName());
                            setTvText(R.id.et_num, data.getIdCard());
                            setSdvBig(R.id.imagebtn1, data.getIdCardFront());
                            setSdvBig(R.id.imagebtn2, data.getIdCardBack());
                            if (data.getFailReason() != null) {
                                ToastUtils.show(data.getFailReason());
                            }
                        }
                    }

                    @Override
                    public void onAllFinish() {
                        hideLoadingDialog();
                    }
                });
    }
    private void setPic(){
        String url ="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1560415409646&di=4b4d5a87786acb4902a92ae2f4d64d89&imgtype=0&src=http%3A%2F%2Fimg009.hc360.cn%2Fg8%2FM08%2FEE%2F89%2FwKhQt1N9cmGEHKQQAAAAAN7_jII892.jpg";
        pic.put(R.id.imagebtn1,url);
        pic.put(R.id.imagebtn2,url);

    }
    /**
     * 确认修改
     */
    private void commit() {
        setPic();
        String et_name = getTvText(R.id.et_name);
        String et_num = getTvText(R.id.et_num);

        if (TextUtils.isEmpty(et_name)) {
            ToastUtils.show(R.string.请输入姓名);
            return;
        }
        if (!ValidUtils.IDCard(et_num)) {
            ToastUtils.show(R.string.身份证号不正确);
            return;
        }
        if(TextUtils.isEmpty(pic.get(R.id.imagebtn1))){
            ToastUtils.show("请上传身份证正面照");
            return;
        }
        if(TextUtils.isEmpty(pic.get(R.id.imagebtn2))){
            ToastUtils.show("请上传身份证反面照");
            return;
        }

        idInfoParam.setRealName(et_name);
        idInfoParam.setIdCard(et_num);
        idInfoParam.setIdCardFront(pic.get(R.id.imagebtn1));
        idInfoParam.setIdCardBack(pic.get(R.id.imagebtn2));
        showLoadingDialog();
        App.getServiceManager().getmService().uploadIdcard(idInfoParam.toPswJson())
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
