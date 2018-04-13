package com.mark.app.hjshop4a.homepager.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.mark.app.base.recylerview.IndexPath;
import com.mark.app.base.recylerview.MkMultipleSourcesRvAdapter;
import com.mark.app.base.recylerview.MkViewHolder;
import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.common.androidenum.homepager.RoleType;

/**
 * Created by pc on 2018/4/13.
 */

public class MeAdapter extends MkMultipleSourcesRvAdapter {
    private int mRole ;
    @Override
    public int getSectionsCount() {
        switch (mRole){
            case RoleType.MEMBER: return 9 ;
        }
        return super.getSectionsCount();
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
            switch (i){
                case 0:return inflater(viewGroup, R.layout.item_me_top);
                case 1:return inflater(viewGroup,R.layout.item_me_body_titlebar);
                case 2:return inflater(viewGroup,R.layout.item_me_body_bottom);
                case 3:return inflater(viewGroup,R.layout.item_me_body_titlebar);
                case 4:return inflater(viewGroup,R.layout.item_me_body);
                case 5:return inflater(viewGroup,R.layout.item_me_body_bottom);
                default:return inflater(viewGroup,R.layout.item_me_bottom);
            }

    }

    @Override
    public void onBindViewHolder(MkViewHolder mkViewHolder, IndexPath indexPath) {
        switch (indexPath.getSection()){
            case 0:

        }
       
    }


}
