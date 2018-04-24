package com.mark.app.hjshop4a.ui.homepager.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import com.mark.app.base.recylerview.IndexPath;
import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.adapter.AutoViewHolder;
import com.mark.app.hjshop4a.base.adapter.MultipleSourcesRvAdapter;
import com.mark.app.hjshop4a.common.androidenum.homepager.RoleType;
import com.mark.app.hjshop4a.common.utils.ActivityJumpUtils;
import com.mark.app.hjshop4a.ui.areaagent.agentperformance.AgentPerformanceActivity;
import com.mark.app.hjshop4a.ui.areaagent.areabusniess.AreaBusniessActivity;
import com.mark.app.hjshop4a.ui.areaagent.areaincome.AreaIncomeActivity;
import com.mark.app.hjshop4a.ui.areaagent.billreview.AreaBillReviewActivity;
import com.mark.app.hjshop4a.ui.areaagent.businessreview.BusinessReviewActivity;
import com.mark.app.hjshop4a.ui.bankcard.activity.BankCardActivity;
import com.mark.app.hjshop4a.ui.business.billrecord.BusniessBillRecordActivity;
import com.mark.app.hjshop4a.ui.business.busniessinfo.BusniessInfoActivity;
import com.mark.app.hjshop4a.ui.business.consumecommit.ConsumeCommitActivity;
import com.mark.app.hjshop4a.ui.business.goldbeanconsume.BusniessGoldBeanConsumeActivity;
import com.mark.app.hjshop4a.ui.business.zxing.BusniessZxingActivity;
import com.mark.app.hjshop4a.ui.dialog.factory.NormalDialogFactory;
import com.mark.app.hjshop4a.ui.goldbeanconsume.MemberGoldBeanConsumeActivity;
import com.mark.app.hjshop4a.ui.onlinerecharge.OnlineRechargeActivity;
import com.mark.app.hjshop4a.ui.provinceagent.ProvinceAreaBusniessActivity;
import com.mark.app.hjshop4a.ui.recommend.RecommendActivity;
import com.mark.app.hjshop4a.ui.withdraw.WithDrawActivity;
import com.mark.app.hjshop4a.ui.withdrawdetail.WithDrawDetailActivity;
import com.white.lib.utils.CallPhoneUtil;

/**
 * Created by pc on 2018/4/13.
 */

public class MeAdapter extends MultipleSourcesRvAdapter {
    private int mRole ;
    Activity activity;
    @Override
    public int getSectionsCount() {
        switch (mRole){
            case RoleType.MEMBER: return 9;
            case RoleType.BUSINESS:return 8;
            case RoleType.AREAAGENT:return 6;
            case RoleType.PROVINCIALAGENT :return 5;
        }
        return 1;
    }

    public MeAdapter(int role,Activity act) {
        activity =act;
        mRole =role;
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }

    @Override
    public int getRowsCountInSection(int i) {
        return 1;
    }

    @Override
    public View onCreateView(ViewGroup viewGroup, int i) {
        switch (mRole) {
            case RoleType.MEMBER:
                switch (i) {
                    case 0:
                        return inflater(viewGroup, R.layout.item_me_top);

                    case 1:
                        return inflater(viewGroup, R.layout.item_me_body_titlebar);
                    case 2:
                        return inflater(viewGroup, R.layout.item_me_body_bottom);
                    case 3:
                        return inflater(viewGroup, R.layout.item_me_body_titlebar);
                    case 4:
                        return inflater(viewGroup, R.layout.item_me_body);
                    case 5:
                        return inflater(viewGroup, R.layout.item_me_body_bottom);
                    case 6:
                        return inflater(viewGroup, R.layout.item_me_bottom);
                    case 7:
                        return inflater(viewGroup, R.layout.item_me_bottom);
                    default:
                        return inflater(viewGroup, R.layout.item_me_last_bottom);
                }

            case RoleType.BUSINESS:
                switch (i){
                    case 0:
                        return inflater(viewGroup,R.layout.item_me_top);
                    case 1:
                        return inflater(viewGroup, R.layout.item_me_body_titlebar);
                    case 2:
                        return inflater(viewGroup,R.layout.item_me_body);
                    case 3:
                        return inflater(viewGroup,R.layout.item_me_body);
                    case 4:
                        return inflater(viewGroup,R.layout.item_me_body_bottom);
                    case 5:
                        return inflater(viewGroup,R.layout.item_me_top);
                    case 6:
                        return inflater(viewGroup,R.layout.item_me_body_bottom);
                    case 7:
                        return inflater(viewGroup,R.layout.item_me_last_bottom);

                }
                break;

            case RoleType.AREAAGENT:

                switch (i){
                    case 0:
                        return inflater(viewGroup,R.layout.item_me_top);
                    case 1:
                        return inflater(viewGroup, R.layout.item_me_body_titlebar);
                    case 2:
                        return inflater(viewGroup,R.layout.item_me_body);
                    case 3:
                        return inflater(viewGroup,R.layout.item_me_body);
                    case 4:
                        return inflater(viewGroup,R.layout.item_me_body_bottom);
                    case 5:
                        return inflater(viewGroup,R.layout.item_me_bottom);

                }
                break;
            case RoleType.PROVINCIALAGENT:
                switch (i){
                    case 0:
                        return inflater(viewGroup,R.layout.item_me_top);
                    case 1:
                        return inflater(viewGroup, R.layout.item_me_body_titlebar);
                    case 2:
                        return inflater(viewGroup,R.layout.item_me_body);
                    case 3:
                        return inflater(viewGroup,R.layout.item_me_body_bottom);
                    case 4:
                        return inflater(viewGroup,R.layout.item_me_bottom);

                }

                break;
        }

        return inflater(viewGroup,R.layout.item_me_top);
    }

    @Override
    public void onBindViewHolder(AutoViewHolder holder, IndexPath indexPath) {
        switch (mRole){
            case RoleType.MEMBER://会员
                bindMember(holder,indexPath);
               break;
            case RoleType.BUSINESS://商户
                bindBusniess(holder,indexPath);
                        break;
            case RoleType.AREAAGENT://区代理
                bindAreaAgent(holder,indexPath);
               break;
            case RoleType.PROVINCIALAGENT://省代理
                bindProvinceAgent(holder,indexPath);
        }

    }
    /*
        * 绑定区域代理数据*/
    private void bindAreaAgent(AutoViewHolder holder, IndexPath indexPath) {
        switch (indexPath.getSection()){
            case 0://顶部数据
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                            if(App.hasToken()){
//                               ActivityJumpUtils.actLogin(activity);
//                            }
                        ActivityJumpUtils.actUserInfo(activity);
                    }
                });
//                        用户名
                holder.text(R.id.hm_tv_user_nickname,"151515151515");
                holder.visibility(R.id.item_mesage,false);
                break;
            case 1:
                //                标题
                holder.text(R.id.hm_item_iv, R.string.me_代理管理中心);
                holder.text( R.id.hm_tv_other,R.string.me_查看全部);
                break;
            case 2:
                holder.image(R.id.me_iv_lab1,R.mipmap.store);
                holder.image(R.id.me_iv_lab2,R.mipmap.busniess_audit);
                holder.image(R.id.me_iv_lab3,R.mipmap.achievement);
                holder.image(R.id.me_iv_lab4,R.mipmap.withdrawcash);
                holder.text(R.id.me_tv_lab1, R.string.me_区域商户 );
                holder.text(R.id.me_tv_lab2, R.string.me_商户申请);
                holder.text(R.id.me_tv_lab3, R.string.me_代理业绩);
                holder.text(R.id.me_tv_lab4, R.string.me_余额提现);
                holder.get(R.id.hm_lab1).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ActivityJumpUtils.actActivity(activity, AreaBusniessActivity.class);
                    }
                });
                holder.get(R.id.hm_lab2).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ActivityJumpUtils.actActivity(activity, BusinessReviewActivity.class);
                    }
                });
                holder.get(R.id.hm_lab3).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ActivityJumpUtils.actActivity(activity, AgentPerformanceActivity.class);
                    }
                });
                holder.get(R.id.hm_lab4).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ActivityJumpUtils.actActivity(activity, WithDrawActivity.class);
                    }
                });
                break;
            case 3:
                holder.image(R.id.me_iv_lab1,R.mipmap.cashdetial);
                holder.image(R.id.me_iv_lab2,R.mipmap.profit);
                holder.image(R.id.me_iv_lab3,R.mipmap.order_audit);
                holder.image(R.id.me_iv_lab4,R.mipmap.bankcard);
                holder.text(R.id.me_tv_lab1, R.string.me_提现记录);
                holder.text(R.id.me_tv_lab2, R.string.me_区域收益);
                holder.text(R.id.me_tv_lab3, R.string.me_辖区报单审核);
                holder.text(R.id.me_tv_lab4,R.string.me_银行卡);
                holder.get(R.id.hm_lab1).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ActivityJumpUtils.actActivity(activity, WithDrawDetailActivity.class);
                    }
                });
                holder.get(R.id.hm_lab2).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ActivityJumpUtils.actActivity(activity, AreaIncomeActivity.class);
                    }
                });
                holder.get(R.id.hm_lab3).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ActivityJumpUtils.actActivity(activity, AreaBillReviewActivity.class);
                    }
                });
                holder.get(R.id.hm_lab4).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ActivityJumpUtils.actActivity(activity, BankCardActivity.class);
                    }
                });
                break;
            case 4:
                holder.image(R.id.me_iv_lab1,R.mipmap.recommend);
                holder.text(R.id.me_tv_lab1,R.string.me_我的推荐);
                holder.get(R.id.hm_lab1).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ActivityJumpUtils.actActivity(activity, RecommendActivity.class);
                    }
                });
                holder.visibility(R.id.hm_lab2,false);
                holder.visibility(R.id.hm_lab3,false);
                holder.visibility(R.id.hm_lab4,false);
                break;
            case 5:
                holder.image(R.id.hm_iv_logo,R.mipmap.about);
                holder.text(R.id.hm_tv_item_name, R.string.me_关于惠家);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ActivityJumpUtils.actAbout(activity);
                    }
                });
                break;

        }
    }

    /*
    * 绑定省代理数据*/
    private void bindProvinceAgent(AutoViewHolder holder, IndexPath indexPath) {
        switch (indexPath.getSection()){
            case 0://顶部数据
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                            if(App.hasToken()){
//                               ActivityJumpUtils.actLogin(activity);
//                            }
                        ActivityJumpUtils.actUserInfo(activity);
                    }
                });
//                        用户名
                holder.text(R.id.hm_tv_user_nickname,"151515151515");
                break;
            case 1:
//                标题
                holder.text(R.id.hm_item_iv, R.string.me_代理管理中心);
                holder.text( R.id.hm_tv_other,R.string.me_查看全部);
                break;
            case 2:
                holder.image(R.id.me_iv_lab1,R.mipmap.city);
                holder.image(R.id.me_iv_lab2,R.mipmap.profit);
                holder.image(R.id.me_iv_lab3,R.mipmap.withdrawcash);
                holder.image(R.id.me_iv_lab4,R.mipmap.cashdetial);
                holder.text(R.id.me_tv_lab1, R.string.me_区域城市);
                holder.text(R.id.me_tv_lab2, R.string.me_区域收益);
                holder.text(R.id.me_tv_lab3, R.string.me_余额提现);
                holder.text(R.id.me_tv_lab4, R.string.me_提现记录);
                holder.get(R.id.hm_lab1).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ActivityJumpUtils.actActivity(activity, ProvinceAreaBusniessActivity.class);
                    }
                });
                holder.get(R.id.hm_lab2).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ActivityJumpUtils.actActivity(activity, AreaIncomeActivity.class);
                    }
                });
                holder.get(R.id.hm_lab3).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ActivityJumpUtils.actActivity(activity, WithDrawActivity.class);
                    }
                });
                holder.get(R.id.hm_lab4).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ActivityJumpUtils.actActivity(activity, WithDrawDetailActivity.class);
                    }
                });
                break;
            case 3:
                holder.image(R.id.me_iv_lab1,R.mipmap.bankcard);
                holder.image(R.id.me_iv_lab2,R.mipmap.recommend);
                holder.text(R.id.me_tv_lab1,R.string.me_银行卡);
                holder.text(R.id.me_tv_lab2,R.string.me_我的推荐);
                holder.visibility(R.id.hm_lab3,false);
                holder.visibility(R.id.hm_lab4,false);
                holder.get(R.id.hm_lab1).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ActivityJumpUtils.actActivity(activity, BankCardActivity.class);
                    }
                });
                holder.get(R.id.hm_lab2).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ActivityJumpUtils.actActivity(activity, RecommendActivity.class);
                    }
                });
                break;
            case 4:
                holder.image(R.id.hm_iv_logo,R.mipmap.about);
                holder.text(R.id.hm_tv_item_name, R.string.me_关于惠家);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ActivityJumpUtils.actAbout(activity);
                    }
                });
                break;
        }
    }

    /*
    * 绑定会员数据*/
    private void bindMember(AutoViewHolder holder, IndexPath indexPath){
        switch (indexPath.getSection())
        {
            case 0:   //顶部数据
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                            if(App.hasToken()){
//                               ActivityJumpUtils.actLogin(activity);
//                            }
                        ActivityJumpUtils.actUserInfo(activity);
                    }
                });
//                        用户名
                holder.text(R.id.hm_tv_user_nickname,"151515151515");
//
                break;
            case 1:
//                      标题
                holder.setVisibility(false);
                holder.text(R.id.hm_item_iv, R.string.me_我的订单);
                holder.text( R.id.hm_tv_other,R.string.me_查看全部);
                break;
            case 2:
//                       功能内容
                holder.setVisibility(false);
                break;
            case 3:
                //标题
                holder.text(R.id.hm_item_iv, R.string.me_会员中心);
                holder.text( R.id.hm_tv_other,R.string.me_查看全部);
                break;
            case 4:
//                    功能内容
//                        holder.text(R.id.me_iv_lab1, R.string.me_银行卡);
//                        holder.text(R.id.me_iv_lab2, R.string.me_我的推荐);
//                        holder.text(R.id.me_iv_lab3, R.string.me_申请代理);
//                        holder.text(R.id.me_iv_lab4, R.string.me_申请商户);
                holder.image(R.id.me_iv_lab1,R.mipmap.gold_bean);
                holder.image(R.id.me_iv_lab2,R.mipmap.onlinerecharge);
                holder.image(R.id.me_iv_lab3,R.mipmap.assets);
                holder.image(R.id.me_iv_lab4,R.mipmap.bill);
                holder.text(R.id.me_tv_lab1, R.string.me_金豆消费);
                //           金豆消费
                holder.get(R.id.hm_lab1).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ActivityJumpUtils.actActivity(activity, MemberGoldBeanConsumeActivity.class);
                    }
                });
                holder.text(R.id.me_tv_lab2, R.string.me_在线充值);
                holder.get(R.id.hm_lab2).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ActivityJumpUtils.actOnlineRecharge(activity,RoleType.MEMBER);
                    }
                });
                holder.text(R.id.me_tv_lab3, R.string.me_资产明细);
                //             资产明细
                holder.get(R.id.hm_lab3).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ActivityJumpUtils.actAssetDetail(activity,RoleType.MEMBER);
                    }
                });
                holder.text(R.id.me_tv_lab4, R.string.me_会员账单);
                //             资产明细
                holder.get(R.id.hm_lab4).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ActivityJumpUtils.actConsumption(activity,RoleType.MEMBER);
                    }
                });
                break;
            case 5:
//                        功能内容
                holder.image(R.id.me_iv_lab1,R.mipmap.bankcard);
                holder.image(R.id.me_iv_lab2,R.mipmap.recommend);
                holder.image(R.id.me_iv_lab3,R.mipmap.agent);
                holder.image(R.id.me_iv_lab4,R.mipmap.busniess);
                holder.text(R.id.me_tv_lab1, R.string.me_银行卡);
                holder.text(R.id.me_tv_lab2, R.string.me_我的推荐);
                holder.text(R.id.me_tv_lab3,R.string.me_申请代理);
                holder.text(R.id.me_tv_lab4,  R.string.me_申请商户);
//                        银行卡
                holder.get(R.id.hm_lab1).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ActivityJumpUtils.actBankCard(activity);
                    }
                });
                //   我的推荐
                holder.get(R.id.hm_lab2).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ActivityJumpUtils.actRecommend(activity,RoleType.MEMBER);
                    }
                });
//                     申请代理
                holder.get(R.id.hm_lab3).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        agant();
                    }
                });
//                        申请商户
                holder.get(R.id.hm_lab4).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ActivityJumpUtils.actBusinesApply(activity);
                    }
                });
                break;
            case 6:
//                       条目  我的收藏
                holder.setVisibility(false);
                holder.text(R.id.hm_tv_item_name, R.string.me_我的收藏);

                break;
            case 7:
                holder.setVisibility(false);
                holder.text(R.id.hm_tv_item_name, R.string.me_收货地址);
                break;
            case 8:
                holder.image(R.id.hm_iv_logo,R.mipmap.about);
                holder.text(R.id.hm_tv_item_name, R.string.me_关于惠家);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ActivityJumpUtils.actAbout(activity);
                    }
                });
                break;
        }

    }
/*
* 绑定商户数据
* */
  private  void bindBusniess(AutoViewHolder holder, IndexPath indexPath){
      switch (indexPath.getSection()){
          case 0:   //顶部数据
              holder.itemView.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
//                            if(App.hasToken()){
//                               ActivityJumpUtils.actLogin(activity);
//                            }

                      ActivityJumpUtils.actUserInfo(activity);
                  }
              });
//                        用户名
              holder.text(R.id.hm_tv_user_nickname,"151515151515");
              holder.visibility(R.id.item_mesage,false);
//
              break;
          case 1:
//                      标题
              holder.text(R.id.hm_item_iv, R.string.me_商家管理中心);
              holder.text( R.id.hm_tv_other,R.string.me_查看全部);
              break;
          case 2:
//                       功能内容
              holder.image(R.id.me_iv_lab1, R.mipmap.store);
              holder.image(R.id.me_iv_lab2, R.mipmap.order);
              holder.image(R.id.me_iv_lab3, R.mipmap.gold_bean);
              holder.image(R.id.me_iv_lab4, R.mipmap.busniessbill);
              holder.text(R.id.me_tv_lab1, R.string.me_线下报单);
              holder.text(R.id.me_tv_lab2, R.string.me_报单账单);
              holder.text(R.id.me_tv_lab3, R.string.me_金豆兑换);
              holder.text(R.id.me_tv_lab4, R.string.me_账单);
              holder.get(R.id.hm_lab1).setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      ActivityJumpUtils.actActivity(activity, ConsumeCommitActivity.class);
                  }
              });
              holder.get(R.id.hm_lab2).setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      ActivityJumpUtils.actActivity(activity, BusniessBillRecordActivity.class);
                  }
              });
              holder.get(R.id.hm_lab3).setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      ActivityJumpUtils.actActivity(activity, BusniessGoldBeanConsumeActivity.class);
                  }
              });

              break;
          case 3:
              //                       功能内容
              holder.image(R.id.me_iv_lab1, R.mipmap.busniessinfo);
              holder.image(R.id.me_iv_lab2, R.mipmap.onlinerecharge);
              holder.image(R.id.me_iv_lab3, R.mipmap.withdrawals);
              holder.image(R.id.me_iv_lab4, R.mipmap.zxing);
              holder.text(R.id.me_tv_lab1, R.string.me_商户信息);
              holder.text(R.id.me_tv_lab2, R.string.me_在线充值);
              holder.text(R.id.me_tv_lab3, R.string.me_余额提现);
              holder.text(R.id.me_tv_lab4, R.string.me_收豆二维码);
              holder.get(R.id.hm_lab1).setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      ActivityJumpUtils.actActivity(activity, BusniessInfoActivity.class);
                  }
              });
              holder.get(R.id.hm_lab2).setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      ActivityJumpUtils.actActivity(activity, OnlineRechargeActivity.class);
                  }
              });
              holder.get(R.id.hm_lab3).setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      ActivityJumpUtils.actActivity(activity, WithDrawActivity.class);
                  }
              });
              holder.get(R.id.hm_lab4).setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      ActivityJumpUtils.actActivity(activity, BusniessZxingActivity.class);
                  }
              });
              break;
          case 4:
//                    功能内容
              //                       功能内容
              holder.image(R.id.me_iv_lab1, R.mipmap.assetdetail);
              holder.image(R.id.me_iv_lab2, R.mipmap.applicationrecord);
              holder.image(R.id.me_iv_lab3, R.mipmap.bankcard);
              holder.image(R.id.me_iv_lab4, R.mipmap.recommend);
              holder.text(R.id.me_tv_lab1, "资产详情");
              holder.text(R.id.me_tv_lab2, "申请记录");
              holder.text(R.id.me_tv_lab3, "银行卡");
              holder.text(R.id.me_tv_lab4, "我的推荐");

              holder.get(R.id.hm_lab1).setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      ActivityJumpUtils.actAssetDetail(activity,RoleType.BUSINESS);
                  }
              });
              holder.get(R.id.hm_lab2).setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      ActivityJumpUtils.actConsumption(activity,RoleType.BUSINESS);
                  }
              });
//
              holder.get(R.id.hm_lab3).setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      ActivityJumpUtils.actBankCard(activity);
                  }
              });
              holder.get(R.id.hm_lab4).setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      ActivityJumpUtils.actRecommend(activity,RoleType.BUSINESS);
                  }
              });
              break;
          case 5:
              //                      标题
              holder.setVisibility(false);
              holder.text(R.id.hm_item_iv, R.string.me_商家管理中心);
              holder.text( R.id.hm_tv_other,R.string.me_查看全部);

              break;
          case 6:
              holder.setVisibility(false);
              break;
          case 7:

              //                       条目  关于惠家
              holder.text(R.id.hm_tv_item_name, R.string.me_关于惠家);
              holder.image(R.id.hm_iv_logo,R.mipmap.about);
              holder.itemView.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      ActivityJumpUtils.actAbout(activity);
                  }
              });
              break;
      }
  }
//  申请代理
  private void agant(){
      final String strPhone = "4001017979";
      NormalDialogFactory.getNormalDialogTwoBtn()
              .setContentText( strPhone)
              .setRightBtnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      CallPhoneUtil.call(activity, strPhone);
                  }
              }).show(activity.getFragmentManager());
  }
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
    }

    public interface OnItemClickListener {
        /**
         * 点击个人信息
         *
         *
         */
        void onClickUserInfo();


    }


}
