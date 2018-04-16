package com.mark.app.hjshop4a.login.adapter;

import android.view.View;
import android.view.ViewGroup;

import com.mark.app.base.recylerview.IndexPath;
import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.adapter.AutoViewHolder;
import com.mark.app.hjshop4a.base.adapter.BaseRvAdapter;
import com.mark.app.hjshop4a.base.adapter.MultipleSourcesRvAdapter;

/**
 * Created by hui on 2018/4/15.
 */

public class LoginSwitchAdapter extends MultipleSourcesRvAdapter {
    private  int sectionsCount;
    public LoginSwitchAdapter() {
        sectionsCount =3;
    }

    @Override
    public int getSectionsCount() {
        return sectionsCount;
    }

    @Override
    public int getRowsCountInSection(int var1) {
        return 1;
    }

    @Override
    public View onCreateView(ViewGroup var1, int var2) {

        return inflater(var1, R.layout.item_account_number);
    }

    @Override
    public void onBindViewHolder(AutoViewHolder holder, IndexPath indexPath) {
            switch (indexPath.getSection()){
                case 0:holder.text(R.id.item_account_number_type, R.string.login_省代理账号);
                        holder.text(R.id.item_account_number,"1515457454");
                        break;
                case  1:holder.text(R.id.item_account_number_type, R.string.login_代理账号);
                    holder.text(R.id.item_account_number,"1515457454");
                    break;
                case  2: holder.text(R.id.item_account_number_type, R.string.login_商家账号);
                    holder.text(R.id.item_account_number,"1515457454");
                    break;
            }
    }
}
