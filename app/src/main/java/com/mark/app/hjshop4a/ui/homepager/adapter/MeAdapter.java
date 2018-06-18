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
import com.mark.app.hjshop4a.ui.businessapply.BusinessApplicationActivity;
import com.mark.app.hjshop4a.ui.goldbeanconsume.BusniessGoldBeanConsumeActivity;
import com.mark.app.hjshop4a.ui.business.zxing.BusniessZxingActivity;
import com.mark.app.hjshop4a.ui.dialog.factory.NormalDialogFactory;
import com.mark.app.hjshop4a.ui.goldbeanconsume.MemberGoldBeanConsumeActivity;
import com.mark.app.hjshop4a.ui.homepager.model.CenterDataModel;
import com.mark.app.hjshop4a.ui.homepager.model.MeCenterInfo;
import com.mark.app.hjshop4a.ui.homepager.model.RoleData;
import com.mark.app.hjshop4a.ui.onlinerecharge.OnlineRechargeActivity;
import com.mark.app.hjshop4a.ui.provinceagent.agentperFormance.PAgentPerformanceActivity;
import com.mark.app.hjshop4a.ui.provinceagent.areabusniess.ProvinceAreaBusniessActivity;
import com.mark.app.hjshop4a.ui.recommend.RecommendActivity;
import com.mark.app.hjshop4a.ui.withdraw.WithDrawActivity;
import com.mark.app.hjshop4a.ui.withdrawdetail.WithDrawDetailActivity;
import com.white.lib.utils.CallPhoneUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pc on 2018/4/13.
 */

public class MeAdapter extends MultipleSourcesRvAdapter {
    private int mRole ;
    Activity activity;
    private MeCenterInfo mData;
    private Map<String ,CenterDataModel> modelMap= new HashMap<>();
    final List<CenterDataModel> memberData=new RoleData().memberData();
    final List<CenterDataModel> merchatData=new RoleData().merchantData();
    final List<CenterDataModel> areaagentData=new RoleData().areaData();
    final List<CenterDataModel> provinceData=new RoleData().provinceData();
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

    public MeAdapter(int role,Activity act ,MeCenterInfo centerInfo) {
        activity =act;
        mRole =role;
        mData =centerInfo;
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
                holder.get(R.id.hm_sdv_logo).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (onItemClickListener != null) {
                            onItemClickListener.onClickUserPic(R.id.hm_sdv_logo);
                        }

                    }
                });
                holder.get(R.id.user_rl_info).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (onItemClickListener != null) {
                            onItemClickListener.onClickUserInfo();
                        }
                    }
                });
                holder.visibility(R.id.item_mesage, false);
                if(mData!=null) {
//                        用户名
                    holder.text(R.id.hm_tv_user_name,mData.getUserRealName());
                    //c会员账号
                    holder.text(R.id.hm_tv_user_phone,mData.getCellPhone());
                    holder.sdvInside(R.id.hm_sdv_logo,mData.getUserPic());

                }
                break;
            case 1:
                //                标题
                holder.text(R.id.hm_item_iv, R.string.me_代理管理中心);
                holder.text( R.id.hm_tv_other,R.string.me_查看全部);
                break;
            case 2:
                if(areaagentData.size()<=0){holder.setVisibility(false);break;}
                modelMap.put("3-1-1",areaagentData.get(0));
                holder.visibility(R.id.hm_lab1,true);
                holder.image(R.id.me_iv_lab1,areaagentData.get(0).getImg());
                holder.text(R.id.me_tv_lab1, areaagentData.get(0).getText());
                holder.get(R.id.hm_lab1).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onClickItem(modelMap.get("3-1-1").getOnclick());
                    }
                });

                areaagentData.remove(0);
                if(areaagentData.size()<=0){break;}
                modelMap.put("3-1-2",areaagentData.get(0));
                holder.visibility(R.id.hm_lab2,true);
                holder.image(R.id.me_iv_lab2,areaagentData.get(0).getImg());
                holder.text(R.id.me_tv_lab2, areaagentData.get(0).getText());
                holder.get(R.id.hm_lab2).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onClickItem(modelMap.get("3-1-2").getOnclick());
                    }
                });
                areaagentData.remove(0);
                if(areaagentData.size()<=0){break;}
                modelMap.put("3-1-3",areaagentData.get(0));
                holder.visibility(R.id.hm_lab3,true);
                holder.image(R.id.me_iv_lab3,areaagentData.get(0).getImg());
                holder.text(R.id.me_tv_lab3, areaagentData.get(0).getText());
                holder.get(R.id.hm_lab3).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onClickItem(modelMap.get("3-1-3").getOnclick());

                    }
                });
                areaagentData.remove(0);
                if(areaagentData.size()<=0){break;}
                modelMap.put("3-1-4",areaagentData.get(0));
                holder.visibility(R.id.hm_lab4,true);
                holder.image(R.id.me_iv_lab4,areaagentData.get(0).getImg());
                holder.text(R.id.me_tv_lab4, areaagentData.get(0).getText());
                holder.get(R.id.hm_lab4).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onClickItem(modelMap.get("3-1-4").getOnclick());

                    }
                });
                break;
            case 3:
                if(areaagentData.size()<=0){holder.setVisibility(false);break;}
                areaagentData.remove(0);
                if(areaagentData.size()<=0){holder.setVisibility(false);break;}
                modelMap.put("3-2-1",areaagentData.get(0));
                holder.visibility(R.id.hm_lab1,true);
                holder.image(R.id.me_iv_lab1,areaagentData.get(0).getImg());
                holder.text(R.id.me_tv_lab1, areaagentData.get(0).getText());
                holder.get(R.id.hm_lab1).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onClickItem(modelMap.get("3-2-1").getOnclick());
                    }
                });

                areaagentData.remove(0);
                if(areaagentData.size()<=0){break;}
                modelMap.put("3-2-2",areaagentData.get(0));
                holder.visibility(R.id.hm_lab2,true);
                holder.image(R.id.me_iv_lab2,areaagentData.get(0).getImg());
                holder.text(R.id.me_tv_lab2, areaagentData.get(0).getText());
                holder.get(R.id.hm_lab2).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onClickItem(modelMap.get("3-2-2").getOnclick());
                    }
                });
                areaagentData.remove(0);
                if(areaagentData.size()<=0){break;}
                modelMap.put("3-2-3",areaagentData.get(0));
                holder.visibility(R.id.hm_lab3,true);
                holder.image(R.id.me_iv_lab3,areaagentData.get(0).getImg());
                holder.text(R.id.me_tv_lab3, areaagentData.get(0).getText());
                holder.get(R.id.hm_lab3).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onClickItem(modelMap.get("3-2-3").getOnclick());

                    }
                });
                areaagentData.remove(0);
                if(areaagentData.size()<=0){break;}
                modelMap.put("3-2-4",areaagentData.get(0));
                holder.visibility(R.id.hm_lab4,true);
                holder.image(R.id.me_iv_lab4,areaagentData.get(0).getImg());
                holder.text(R.id.me_tv_lab4, areaagentData.get(0).getText());
                holder.get(R.id.hm_lab4).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onClickItem(modelMap.get("3-2-4").getOnclick());

                    }
                });
                break;
            case 4:
                if(areaagentData.size()<=0){holder.setVisibility(false);break;}
                areaagentData.remove(0);
                if(areaagentData.size()<=0){holder.setVisibility(false);break;}
                modelMap.put("3-3-1",areaagentData.get(0));
                holder.visibility(R.id.hm_lab1,true);
                holder.image(R.id.me_iv_lab1,areaagentData.get(0).getImg());
                holder.text(R.id.me_tv_lab1, areaagentData.get(0).getText());
                holder.get(R.id.hm_lab1).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onClickItem(modelMap.get("3-3-1").getOnclick());
                    }
                });

                areaagentData.remove(0);
                if(areaagentData.size()<=0){break;}
                modelMap.put("3-3-2",areaagentData.get(0));
                holder.visibility(R.id.hm_lab2,true);
                holder.image(R.id.me_iv_lab2,areaagentData.get(0).getImg());
                holder.text(R.id.me_tv_lab2, areaagentData.get(0).getText());
                holder.get(R.id.hm_lab2).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onClickItem(modelMap.get("3-3-2").getOnclick());
                    }
                });
                areaagentData.remove(0);
                if(areaagentData.size()<=0){break;}
                modelMap.put("3-3-3",areaagentData.get(0));
                holder.visibility(R.id.hm_lab3,true);
                holder.image(R.id.me_iv_lab3,areaagentData.get(0).getImg());
                holder.text(R.id.me_tv_lab3, areaagentData.get(0).getText());
                holder.get(R.id.hm_lab3).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onClickItem(modelMap.get("3-3-3").getOnclick());

                    }
                });
                areaagentData.remove(0);
                if(areaagentData.size()<=0){break;}
                modelMap.put("3-3-4",areaagentData.get(0));
                holder.visibility(R.id.hm_lab4,true);
                holder.image(R.id.me_iv_lab4,areaagentData.get(0).getImg());
                holder.text(R.id.me_tv_lab4, areaagentData.get(0).getText());
                holder.get(R.id.hm_lab4).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onClickItem(modelMap.get("3-3-4").getOnclick());

                    }
                });
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
                holder.get(R.id.hm_sdv_logo).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (onItemClickListener != null) {
                            onItemClickListener.onClickUserPic(R.id.hm_sdv_logo);
                        }

                    }
                });
                holder.get(R.id.user_rl_info).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (onItemClickListener != null) {
                            onItemClickListener.onClickUserInfo();
                        }
                    }
                });
                holder.visibility(R.id.item_mesage, false);
                if(mData!=null) {
//                        用户名
                    holder.text(R.id.hm_tv_user_name,mData.getUserRealName());
                    //c会员账号
                    holder.text(R.id.hm_tv_user_phone,mData.getCellPhone());
                    holder.sdvInside(R.id.hm_sdv_logo,mData.getUserPic());

                }
                break;
            case 1:
//                标题
                holder.text(R.id.hm_item_iv, R.string.me_代理管理中心);
                holder.text( R.id.hm_tv_other,R.string.me_查看全部);
                break;
            case 2:
                if(provinceData.size()<=0){holder.setVisibility(false);break;}
                modelMap.put("4-1-1",provinceData.get(0));
                holder.visibility(R.id.hm_lab1,true);
                holder.image(R.id.me_iv_lab1,provinceData.get(0).getImg());
                holder.text(R.id.me_tv_lab1, provinceData.get(0).getText());
                holder.get(R.id.hm_lab1).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onClickItem(modelMap.get("4-1-1").getOnclick());
                    }
                });

                provinceData.remove(0);
                if(provinceData.size()<=0){break;}
                modelMap.put("4-1-2",provinceData.get(0));
                holder.visibility(R.id.hm_lab2,true);
                holder.image(R.id.me_iv_lab2,provinceData.get(0).getImg());
                holder.text(R.id.me_tv_lab2, provinceData.get(0).getText());
                holder.get(R.id.hm_lab2).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onClickItem(modelMap.get("4-1-2").getOnclick());
                    }
                });
                provinceData.remove(0);
                if(provinceData.size()<=0){break;}
                modelMap.put("4-1-3",provinceData.get(0));
                holder.visibility(R.id.hm_lab3,true);
                holder.image(R.id.me_iv_lab3,provinceData.get(0).getImg());
                holder.text(R.id.me_tv_lab3, provinceData.get(0).getText());
                holder.get(R.id.hm_lab3).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onClickItem(modelMap.get("4-1-3").getOnclick());

                    }
                });
                provinceData.remove(0);
                if(provinceData.size()<=0){break;}
                modelMap.put("4-1-4",provinceData.get(0));
                holder.visibility(R.id.hm_lab4,true);
                holder.image(R.id.me_iv_lab4,provinceData.get(0).getImg());
                holder.text(R.id.me_tv_lab4, provinceData.get(0).getText());
                holder.get(R.id.hm_lab4).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onClickItem(modelMap.get("4-1-4").getOnclick());

                    }
                });
                break;
            case 3:
                if(provinceData.size()<=0){holder.setVisibility(false);break;}
                provinceData.remove(0);
                if(provinceData.size()<=0){holder.setVisibility(false);break;}
                modelMap.put("4-2-1",provinceData.get(0));
                holder.visibility(R.id.hm_lab1,true);
                holder.image(R.id.me_iv_lab1,provinceData.get(0).getImg());
                holder.text(R.id.me_tv_lab1, provinceData.get(0).getText());
                holder.get(R.id.hm_lab1).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onClickItem(modelMap.get("4-2-1").getOnclick());
                    }
                });

                provinceData.remove(0);
                if(provinceData.size()<=0){break;}
                modelMap.put("4-2-2",provinceData.get(0));
                holder.visibility(R.id.hm_lab2,true);
                holder.image(R.id.me_iv_lab2,provinceData.get(0).getImg());
                holder.text(R.id.me_tv_lab2, provinceData.get(0).getText());
                holder.get(R.id.hm_lab2).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onClickItem(modelMap.get("4-2-2").getOnclick());
                    }
                });
                provinceData.remove(0);
                if(provinceData.size()<=0){break;}
                modelMap.put("4-2-3",provinceData.get(0));
                holder.visibility(R.id.hm_lab3,true);
                holder.image(R.id.me_iv_lab3,provinceData.get(0).getImg());
                holder.text(R.id.me_tv_lab3, provinceData.get(0).getText());
                holder.get(R.id.hm_lab3).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onClickItem(modelMap.get("4-2-3").getOnclick());

                    }
                });
                provinceData.remove(0);
                if(provinceData.size()<=0){break;}
                modelMap.put("4-2-4",provinceData.get(0));
                holder.visibility(R.id.hm_lab4,true);
                holder.image(R.id.me_iv_lab4,provinceData.get(0).getImg());
                holder.text(R.id.me_tv_lab4, provinceData.get(0).getText());
                holder.get(R.id.hm_lab4).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onClickItem(modelMap.get("4-2-4").getOnclick());

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
                holder.get(R.id.hm_sdv_logo).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (onItemClickListener != null) {
                            onItemClickListener.onClickUserPic(R.id.hm_sdv_logo);
                        }

                    }
                });
            holder.get(R.id.user_rl_info).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onClickUserInfo();
                    }
                }
            });
            if(mData!=null) {
//                        用户名
                holder.text(R.id.hm_tv_user_name,mData.getUserRealName());
                //c会员账号
                holder.text(R.id.hm_tv_user_phone,mData.getCellPhone());
                //金豆
                holder.text(R.id.hm_tv_red, mData.getEvaDetailCount());
                //余额
                holder.text(R.id.hm_tv_balance, mData.getAccountBlance());
                //积分
                holder.text(R.id.hm_tv_integral, mData.getIntegral());
                //头像
                holder.sdvInside(R.id.hm_sdv_logo, mData.getUserPic());
            }
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
                if(memberData.size()<=0){holder.setVisibility(false);break;}
                modelMap.put("1-1-1",memberData.get(0));
                holder.visibility(R.id.hm_lab1,true);
                holder.image(R.id.me_iv_lab1,memberData.get(0).getImg());
                holder.text(R.id.me_tv_lab1, memberData.get(0).getText());
                holder.get(R.id.hm_lab1).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onClickItem(modelMap.get("1-1-1").getOnclick());
                    }
                });

                memberData.remove(0);
                if(memberData.size()<=0){break;}
                modelMap.put("1-1-2",memberData.get(0));
                holder.visibility(R.id.hm_lab2,true);
                holder.image(R.id.me_iv_lab2,memberData.get(0).getImg());
                holder.text(R.id.me_tv_lab2, memberData.get(0).getText());
                holder.get(R.id.hm_lab2).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onClickItem(modelMap.get("1-1-2").getOnclick());
                    }
                });
                memberData.remove(0);
                if(memberData.size()<=0){break;}
                modelMap.put("1-1-3",memberData.get(0));
                holder.visibility(R.id.hm_lab3,true);
                holder.image(R.id.me_iv_lab3,memberData.get(0).getImg());
                holder.text(R.id.me_tv_lab3, memberData.get(0).getText());
                holder.get(R.id.hm_lab3).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onClickItem(modelMap.get("1-1-3").getOnclick());

                    }
                });
                memberData.remove(0);
                if(memberData.size()<=0){break;}
                modelMap.put("1-1-4",memberData.get(0));
                holder.visibility(R.id.hm_lab4,true);
                holder.image(R.id.me_iv_lab4,memberData.get(0).getImg());
                holder.text(R.id.me_tv_lab4, memberData.get(0).getText());
                holder.get(R.id.hm_lab4).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onClickItem(modelMap.get("1-1-4").getOnclick());

                    }
                });

                break;
            case 5:
//                        功能内容
                if(memberData.size()<=0){holder.setVisibility(false);break;}
                memberData.remove(0);
                if(memberData.size()<=0){holder.setVisibility(false);break;}
                modelMap.put("1-2-1",memberData.get(0));
                holder.visibility(R.id.hm_lab1,true);
                holder.image(R.id.me_iv_lab1,memberData.get(0).getImg());
                holder.text(R.id.me_tv_lab1, memberData.get(0).getText());
                holder.get(R.id.hm_lab1).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onClickItem(modelMap.get("1-2-1").getOnclick());
                    }
                });

                memberData.remove(0);
                if(memberData.size()<=0){break;}
                modelMap.put("1-2-2",memberData.get(0));
                holder.visibility(R.id.hm_lab2,true);
                holder.image(R.id.me_iv_lab2,memberData.get(0).getImg());
                holder.text(R.id.me_tv_lab2, memberData.get(0).getText());
                holder.get(R.id.hm_lab2).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onClickItem(modelMap.get("1-2-2").getOnclick());
                    }
                });
                memberData.remove(0);
                if(memberData.size()<=0){break;}
                modelMap.put("1-2-3",memberData.get(0));
                holder.visibility(R.id.hm_lab3,true);
                holder.image(R.id.me_iv_lab3,memberData.get(0).getImg());
                holder.text(R.id.me_tv_lab3, memberData.get(0).getText());
                holder.get(R.id.hm_lab3).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onClickItem(modelMap.get("1-2-3").getOnclick());

                    }
                });
                memberData.remove(0);
                if(memberData.size()<=0){break;}
                modelMap.put("1-2-4",memberData.get(0));
                holder.visibility(R.id.hm_lab4,true);
                holder.image(R.id.me_iv_lab4,memberData.get(0).getImg());
                holder.text(R.id.me_tv_lab4, memberData.get(0).getText());
                holder.get(R.id.hm_lab4).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onClickItem(modelMap.get("1-2-4").getOnclick());

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
              holder.visibility(R.id.item_mesage, false);
              holder.get(R.id.hm_sdv_logo).setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                      if (onItemClickListener != null) {
                          onItemClickListener.onClickUserPic(R.id.hm_sdv_logo);
                      }

                  }
              });
              holder.get(R.id.user_rl_info).setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      if (onItemClickListener != null) {
                          onItemClickListener.onClickUserInfo();
                      }
                  }
              });
          if(mData!=null) {
//                        用户名
              holder.text(R.id.hm_tv_user_name,mData.getUserRealName());
              //c会员账号
//              holder.text(R.id.hm_tv_user_phone,mData.getCellPhone());
//              holder.text(R.id.hm_tv_user_shopname,"商户ID:"+String.valueOf(mData.getShopId()));
              holder.text(R.id.hm_tv_user_shopname,mData.getCellPhone());
              if(mData.getShopId()!=0){
                  holder.text(R.id.hm_tv_user_phone,"店铺ID:"+String.valueOf(mData.getShopId()));
              }
              holder.sdvInside(R.id.hm_sdv_logo,mData.getUserPic());

          }
              break;
          case 1:
//                      标题
              holder.text(R.id.hm_item_iv, R.string.me_商家管理中心);
              holder.text( R.id.hm_tv_other,R.string.me_查看全部);
              break;
          case 2:
//                       功能内容
              if(merchatData.size()<=0){holder.setVisibility(false);break;}
              modelMap.put("2-1-1",merchatData.get(0));
              holder.visibility(R.id.hm_lab1,true);
              holder.image(R.id.me_iv_lab1,merchatData.get(0).getImg());
              holder.text(R.id.me_tv_lab1, merchatData.get(0).getText());
              holder.get(R.id.hm_lab1).setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      onClickItem(modelMap.get("2-1-1").getOnclick());
                  }
              });

              merchatData.remove(0);
              if(merchatData.size()<=0){break;}
              modelMap.put("2-1-2",merchatData.get(0));
              holder.visibility(R.id.hm_lab2,true);
              holder.image(R.id.me_iv_lab2,merchatData.get(0).getImg());
              holder.text(R.id.me_tv_lab2, merchatData.get(0).getText());
              holder.get(R.id.hm_lab2).setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      onClickItem(modelMap.get("2-1-2").getOnclick());
                  }
              });
              merchatData.remove(0);
              if(merchatData.size()<=0){break;}
              modelMap.put("2-1-3",merchatData.get(0));
              holder.visibility(R.id.hm_lab3,true);
              holder.image(R.id.me_iv_lab3,merchatData.get(0).getImg());
              holder.text(R.id.me_tv_lab3, merchatData.get(0).getText());
              holder.get(R.id.hm_lab3).setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      onClickItem(modelMap.get("2-1-3").getOnclick());

                  }
              });
              merchatData.remove(0);
              if(merchatData.size()<=0){break;}
              modelMap.put("2-1-4",merchatData.get(0));
              holder.visibility(R.id.hm_lab4,true);
              holder.image(R.id.me_iv_lab4,merchatData.get(0).getImg());
              holder.text(R.id.me_tv_lab4, merchatData.get(0).getText());
              holder.get(R.id.hm_lab4).setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      onClickItem(modelMap.get("2-1-4").getOnclick());

                  }
              });
              break;
          case 3:
              //                       功能内容
              if(merchatData.size()<=0){holder.setVisibility(false);break;}
              merchatData.remove(0);
              if(merchatData.size()<=0){holder.setVisibility(false);break;}
              modelMap.put("2-2-1",merchatData.get(0));
              holder.visibility(R.id.hm_lab1,true);
              holder.image(R.id.me_iv_lab1,merchatData.get(0).getImg());
              holder.text(R.id.me_tv_lab1, merchatData.get(0).getText());
              holder.get(R.id.hm_lab1).setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      onClickItem(modelMap.get("2-2-1").getOnclick());
                  }
              });

              merchatData.remove(0);
              if(merchatData.size()<=0){break;}
              modelMap.put("2-2-2",merchatData.get(0));
              holder.visibility(R.id.hm_lab2,true);
              holder.image(R.id.me_iv_lab2,merchatData.get(0).getImg());
              holder.text(R.id.me_tv_lab2, merchatData.get(0).getText());
              holder.get(R.id.hm_lab2).setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      onClickItem(modelMap.get("2-2-2").getOnclick());
                  }
              });
              merchatData.remove(0);
              if(merchatData.size()<=0){break;}
              modelMap.put("2-2-3",merchatData.get(0));
              holder.visibility(R.id.hm_lab3,true);
              holder.image(R.id.me_iv_lab3,merchatData.get(0).getImg());
              holder.text(R.id.me_tv_lab3, merchatData.get(0).getText());
              holder.get(R.id.hm_lab3).setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      onClickItem(modelMap.get("2-2-3").getOnclick());

                  }
              });
              merchatData.remove(0);
              if(merchatData.size()<=0){break;}
              modelMap.put("2-2-4",merchatData.get(0));
              holder.visibility(R.id.hm_lab4,true);
              holder.image(R.id.me_iv_lab4,merchatData.get(0).getImg());
              holder.text(R.id.me_tv_lab4, merchatData.get(0).getText());
              holder.get(R.id.hm_lab4).setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      onClickItem(modelMap.get("2-2-4").getOnclick());

                  }
              });
              break;
          case 4:
//                    功能内容
              //                       功能内容
              if(merchatData.size()<=0){holder.setVisibility(false);break;}
              merchatData.remove(0);
              if(merchatData.size()<=0){holder.setVisibility(false);break;}
              modelMap.put("2-3-1",merchatData.get(0));
              holder.visibility(R.id.hm_lab1,true);
              holder.image(R.id.me_iv_lab1,merchatData.get(0).getImg());
              holder.text(R.id.me_tv_lab1, merchatData.get(0).getText());
              holder.get(R.id.hm_lab1).setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      onClickItem(modelMap.get("2-3-1").getOnclick());
                  }
              });

              merchatData.remove(0);
              if(merchatData.size()<=0){break;}
              modelMap.put("2-3-2",merchatData.get(0));
              holder.visibility(R.id.hm_lab2,true);
              holder.image(R.id.me_iv_lab2,merchatData.get(0).getImg());
              holder.text(R.id.me_tv_lab2, merchatData.get(0).getText());
              holder.get(R.id.hm_lab2).setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      onClickItem(modelMap.get("2-3-2").getOnclick());
                  }
              });
              merchatData.remove(0);
              if(merchatData.size()<=0){break;}
              modelMap.put("2-3-3",merchatData.get(0));
              holder.visibility(R.id.hm_lab3,true);
              holder.image(R.id.me_iv_lab3,merchatData.get(0).getImg());
              holder.text(R.id.me_tv_lab3, merchatData.get(0).getText());
              holder.get(R.id.hm_lab3).setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      onClickItem(modelMap.get("2-3-3").getOnclick());

                  }
              });
              merchatData.remove(0);
              if(merchatData.size()<=0){break;}
              modelMap.put("2-3-4",merchatData.get(0));
              holder.visibility(R.id.hm_lab4,true);
              holder.image(R.id.me_iv_lab4,merchatData.get(0).getImg());
              holder.text(R.id.me_tv_lab4, merchatData.get(0).getText());
              holder.get(R.id.hm_lab4).setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      onClickItem(modelMap.get("2-3-4").getOnclick());

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
  private void onClickItem(String click){
      switch (click){
          case "1-1-1": ActivityJumpUtils.actActivity(activity, MemberGoldBeanConsumeActivity.class);break;
          case "1-1-2": ActivityJumpUtils.actOnlineRecharge(activity,RoleType.MEMBER);break;
          case "1-1-3": ActivityJumpUtils.actAssetDetail(activity,RoleType.MEMBER);break;
          case "1-1-4": ActivityJumpUtils.actConsumption(activity,RoleType.MEMBER);break;
          case "1-2-1": ActivityJumpUtils.actBankCard(activity);break;
          case  "1-2-2":  ActivityJumpUtils.actRecommend(activity,RoleType.MEMBER);break;
          case  "1-2-3": agant();break;
          case  "1-2-4":ActivityJumpUtils.actBusinesApply(activity);break;
          case  "2-1-1": ActivityJumpUtils.actActivity(activity, ConsumeCommitActivity.class);break;
          case  "2-1-2":  ActivityJumpUtils.actActivity(activity, BusniessBillRecordActivity.class);break;
          case "2-1-3":  ActivityJumpUtils.actActivity(activity, BusniessGoldBeanConsumeActivity.class);break;
          case  "2-1-4":  ActivityJumpUtils.actConsumption(activity,RoleType.BUSINESS);break;
          case  "2-2-1":  ActivityJumpUtils.actActivity(activity, BusinessApplicationActivity.class);break;
          case "2-2-2":ActivityJumpUtils.actActivity(activity, OnlineRechargeActivity.class);break;
          case "2-2-3": ActivityJumpUtils.actActivity(activity, WithDrawActivity.class);break;
          case "2-2-4": ActivityJumpUtils.actActivity(activity, BusniessZxingActivity.class);break;
          case "2-3-1":    ActivityJumpUtils.actAssetDetail(activity,RoleType.BUSINESS);break;
          case "2-3-2": ActivityJumpUtils.actConsumption(activity,RoleType.BUSINESS);break;
          case "2-3-3":   ActivityJumpUtils.actBankCard(activity);break;
          case "2-3-4":  ActivityJumpUtils.actRecommend(activity,RoleType.BUSINESS);break;
          case  "3-1-1":ActivityJumpUtils.actActivity(activity, AreaBusniessActivity.class);break;
          case  "3-1-2": ActivityJumpUtils.actActivity(activity, BusinessReviewActivity.class);break;
          case  "3-1-3": ActivityJumpUtils.actActivity(activity, AreaBillReviewActivity.class);break;
          case "3-1-4": ActivityJumpUtils.actActivity(activity, WithDrawActivity.class);break;
          case "3-2-1": ActivityJumpUtils.actActivity(activity, WithDrawDetailActivity.class);break;
          case "3-2-2": ActivityJumpUtils.actActivity(activity, AreaIncomeActivity.class);break;
          case "3-2-3": ActivityJumpUtils.actActivity(activity, AreaBillReviewActivity.class);break;
          case "3-2-4":  ActivityJumpUtils.actActivity(activity, BankCardActivity.class);break;
          case "3-3-1":  ActivityJumpUtils.actActivity(activity, RecommendActivity.class);break;
          case "4-1-1":  ActivityJumpUtils.actActivity(activity, ProvinceAreaBusniessActivity.class);break;
          case "4-1-2": ActivityJumpUtils.actActivity(activity, AreaIncomeActivity.class);break;
          case "4-1-3": ActivityJumpUtils.actActivity(activity, WithDrawActivity.class);break;
          case "4-1-4": ActivityJumpUtils.actActivity(activity, WithDrawDetailActivity.class);break;
          case "4-2-1":ActivityJumpUtils.actActivity(activity, BankCardActivity.class);break;
          case "4-2-2": ActivityJumpUtils.actActivity(activity, RecommendActivity.class);break;
          case "4-2-3":  ActivityJumpUtils.actActivity(activity, PAgentPerformanceActivity.class);break;
      }
  }

//  申请代理
  private void agant(){
      final String strPhone = "400 101 7979";
      NormalDialogFactory.getNormalDialogTwoBtn()
              .setContentText( strPhone)
              .setRightBtnText(R.string.呼叫)
              .setRightBtnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      CallPhoneUtil.call(activity, strPhone.replace(" ",""));
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
        //
        void onClickUserPic(int id);

    }


}
