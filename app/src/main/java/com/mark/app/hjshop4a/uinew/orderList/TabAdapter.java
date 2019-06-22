package com.mark.app.hjshop4a.uinew.orderList;

import android.support.v4.app.FragmentManager;

import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

public class TabAdapter extends FragmentPagerItemAdapter {
    public TabAdapter(FragmentManager fm, FragmentPagerItems pages) {
        super(fm, pages);
    }
    public FragmentPagerItem getFragmentPagerItem(int position){
        return getPagerItem(position);
    }
}
