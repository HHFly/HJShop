package com.mark.app.hjshop4a.homepager.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.mark.app.base.recylerview.IndexPath;
import com.mark.app.base.recylerview.MkMultipleSourcesRvAdapter;
import com.mark.app.base.recylerview.MkViewHolder;
import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.adapter.AutoViewHolder;
import com.mark.app.hjshop4a.base.adapter.MultipleSourcesRvAdapter;
import com.mark.app.hjshop4a.common.androidenum.homepager.RoleType;

/**
 * Created by pc on 2018/4/13.
 */

public class MeAdapter extends MultipleSourcesRvAdapter {
    private int mRole ;
    @Override
    public int getSectionsCount() {
        switch (mRole){
            case RoleType.MEMBER: return 7;
            case RoleType.BUSINESS:return 8;
            case RoleType.AREAAGENT:return 6;
            case RoleType.PROVINCIALAGENT :return 6;
        }
        return 1;
    }

    public MeAdapter(int role) {
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


                }
                break;

            case RoleType.AREAAGENT:
                break;
            case RoleType.PROVINCIALAGENT:
                break;
        }

        return inflater(viewGroup,R.layout.item_me_top);
    }

    @Override
    public void onBindViewHolder(AutoViewHolder holder, IndexPath indexPath) {
        switch (mRole){
            case RoleType.MEMBER://会员
                switch (indexPath.getSection()){
                    case 0:   //顶部数据
                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (onItemClickListener != null) {
                                    onItemClickListener.onClickUserInfo();
                                }
                            }
                        });
//                        用户名
                        holder.text(R.id.hm_tv_user_nickname,"151515151515");
//
                        break;
                    case 1:
//                      标题
                        holder.text(R.id.hm_item_iv, R.string.me_我的订单);
                        holder.text( R.id.hm_tv_other,R.string.me_查看全部);
                        break;
                    case 2:
//                       功能内容
                        break;
                    case 3:
                        //标题
                        holder.text(R.id.hm_item_iv, R.string.me_会员中心);
                        holder.text( R.id.hm_tv_other,R.string.me_查看全部);
                        break;
                    case 4:
//                    功能内容
                        break;
                    case 5:
//                        功能内容
                        break;
                    case 6:
//                       条目
                        holder.text(R.id.hm_tv_item_name, R.string.me_关于惠家);
                        break;
                    case 7:
//                        底部多余

                        break;

                }
                break;
            case RoleType.BUSINESS://商户
            case RoleType.AREAAGENT://区代理
            case RoleType.PROVINCIALAGENT://省代理

        }

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
