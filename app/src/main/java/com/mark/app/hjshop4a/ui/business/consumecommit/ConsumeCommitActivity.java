package com.mark.app.hjshop4a.ui.business.consumecommit;

import android.content.Intent;
import android.net.Uri;
import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.common.utils.ActivityJumpUtils;
import com.mark.app.hjshop4a.common.utils.TakeImgUtil;
import com.mark.app.hjshop4a.ui.dialog.factory.FunctionDialogFactory;

/**
 * Created by pc on 2018/4/19.
 */

public class ConsumeCommitActivity  extends BaseActivity{
    @Override
    public int getContentViewResId() {
        return R.layout.activity_order_commit;
    }

    @Override
    public void initView() {
        setTvText(R.id.titlebar_tv_title,"线下消费提交");
        setTvText(R.id.titlebar_tv_right,"说明");
        setIvImage(R.id.titlebar_iv_logo,R.mipmap.ic_tip);
    }

    @Override
    public void setListener() {
        setClickListener(R.id.titlebar_iv_return);
        setClickListener(R.id.titlebar_tv_right);
        setClickListener(R.id.member_id);
        setClickListener(R.id.roletype);
        setClickListener(R.id.consmue_count);
        setClickListener(R.id.service_charge);
        setClickListener(R.id.commodity_name);
        setClickListener(R.id.commodity_conut);
        setClickListener(R.id.commodity_price);
        setClickListener(R.id.imagebtn);
        setClickListener(R.id.button);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.titlebar_iv_return:
                finish();
                break;
            case R.id.titlebar_tv_right:
                ActivityJumpUtils.actActivity(this,CommitRuleActivity.class);
            break;
            case R.id.member_id:
//                *会员账号
                FunctionDialogFactory.showAddOneParamDialog(this,"",R.id.tv_member_id);
                break;
            case R.id.roletype:
//                *账户类别
                break;
            case R.id.consmue_count:
//                *消费金额
                FunctionDialogFactory.showAddOneParamDialog(this,"",R.id.tv_consmue_count);
                break;
            case R.id.service_charge:
//                * 服务费
                break;
            case R.id.commodity_name:
//                *商品名称
                FunctionDialogFactory.showAddOneParamDialog(this,"",R.id.tv_commodity_name);
                break;
            case R.id.commodity_conut:
//                *商品数量
                FunctionDialogFactory.showAddOneParamDialog(this,"",R.id.tv_commodity_conut);
                break;
            case R.id.commodity_price:
//                *商品单价
                FunctionDialogFactory.showAddOneParamDialog(this,"",R.id.tv_commodity_price);
                break;
            case R.id.imagebtn:
//             图片
                FunctionDialogFactory.showTakePhoneDialog(getAppCompatActivity(), R.id.imagebtn);
                break;
            case R.id.button:
//                提交
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        TakeImgUtil.onActivityResult(this, requestCode, resultCode, data, new TakeImgUtil.CallBack() {
            @Override
            public void back(Uri var1, int id) {

                setSdvBig(id, var1);
            }
        });
    }
}
