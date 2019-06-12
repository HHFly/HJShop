package com.mark.app.hjshop4a.uinew.bindinfo.dialog;

import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.adapter.AutoViewHolder;
import com.mark.app.hjshop4a.base.adapter.BaseListRvAdapter;
import com.mark.app.hjshop4a.uinew.bindinfo.model.ShopType;
import com.mark.app.hjshop4a.widget.SmoothCheckBox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShopTypeAdapter extends BaseListRvAdapter<ShopType> {
    private Map<Integer,ShopType> map =new HashMap<>();
    public ShopTypeAdapter(List<ShopType> data) {
        super(data);
    }

    @Override
    public int getItemResId() {
        return R.layout.item_shoptype;
    }

    public List<ShopType> getMapData() {
    List<ShopType> data =new ArrayList<>();
//遍历map中的值
        for (ShopType value : map.values()) {
            data.add(value);
        }
      return data ;
    }

    @Override
    public void bindBodyData(final AutoViewHolder holder, final int bodyPos, final ShopType data) {
            holder.text(R.id.tv_name,data.getName());
        SmoothCheckBox smoothCheckBox =holder.get(R.id.scbox);

        smoothCheckBox.setOnCheckedChangeListener(new SmoothCheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SmoothCheckBox smoothCheckBox, boolean isChecked) {
                holder.itemView.setSelected(isChecked);
                if(isChecked){
                    if(map.size()<3){
                        map.put(bodyPos,data);
                    }else {
                        smoothCheckBox.setChecked(false);
                    }
                }else {
                    map.remove(bodyPos);
                }
                if(onItemClickListener!=null){
                    onItemClickListener.onClick(isChecked,data);
                }
            }
        });
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    holder.get(R.id.scbox).performClick();

                }
            });
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
    }

    public interface OnItemClickListener {
        /**
         * 点击修改分类
         *
         * @param data
         */
        void onClick(boolean isChecked,ShopType data);

    }

}
