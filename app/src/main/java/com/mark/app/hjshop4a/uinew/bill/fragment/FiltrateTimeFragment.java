package com.mark.app.hjshop4a.uinew.bill.fragment;

import android.content.Context;
import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.fragment.BaseFragment;
import com.mark.app.hjshop4a.ui.dialog.wheelviewlibrary.WheelView;
import com.mark.app.hjshop4a.ui.dialog.wheelviewlibrary.adapter.NumericWheelAdapter;
import com.mark.app.hjshop4a.ui.dialog.wheelviewlibrary.listener.OnWheelChangedListener;

import java.util.Calendar;

public class FiltrateTimeFragment extends BaseFragment implements OnWheelChangedListener {
    public static final int START_YEAR = 2019;
    public static final int END_YEAR = 2030;
    private WheelView year;
    private WheelView month;
    int curYear;
    int curMonth ;//通过Calendar算出的月数要+1
//    private int[] SHADOWS_COLORS = new int[]{0xefE9E9E9, 0xcfE9E9E9, 0x3fE9E9E9};
    @Override
    public int getContentResId() {
        return R.layout.fragment_bill_filtrate_time;
    }

    @Override
    public void findView() {

    }

    @Override
    public void setListener() {

        setClickListener(R.id.btn_confirm);
        setClickListener(R.id.btn_cancel);
    }

    @Override
    public void initView() {
        setTvText(R.id.titlebar_tv_title,"选择时间");
        Calendar c = Calendar.getInstance();
         curYear = c.get(Calendar.YEAR);
         curMonth = c.get(Calendar.MONTH) + 1;//通过Calendar算出的月数要+1

        year = getView(R.id.id_province);
        initYear(getActivity());
        month = getView(R.id.id_city);
        initMonth(getActivity());

        year.setCurrentItem(curYear - START_YEAR);
        month.setCurrentItem(curMonth - 1);

        year.setVisibleItems(7);
        month.setVisibleItems(7);

        year.addChangingListener(this);
        month.addChangingListener(this);
        year.setShadowColor(0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF);
        month.setShadowColor(0xFFFFFFFF, 0xFFFFFFFF, 0xFFFFFFFF);

        setTvText(R.id.tv_time,String.valueOf(curYear)+"-"+String.valueOf(curMonth));
    }

    @Override
    public void onUnFirstResume() {
        if(year!=null&&month!=null) {
            year.setCurrentItem(curYear - START_YEAR);
            month.setCurrentItem(curMonth - 1);
            setTvText(R.id.tv_time, String.valueOf(curYear) + "-" + String.valueOf(curMonth));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_cancel:
                if(onItemClickListener!=null){
                    onItemClickListener.onClickCancel();
                }
                break;
            case R.id.btn_confirm:
                int dateyear =   year.getCurrentItem() + START_YEAR;//1900
                int datemonth = month.getCurrentItem()+1 ;
                Calendar c = Calendar.getInstance();

                c.set(dateyear,datemonth-1,1);
//                c.add(Calendar.MONTH, 0);
                c.set(Calendar.DAY_OF_MONTH,1);
                if(onItemClickListener!=null){
                    onItemClickListener.onClickOK(c.getTimeInMillis(),String.valueOf(dateyear)+"/"+String.valueOf(datemonth));
                }
                break;
        }
    }


    /**
     * 初始化年
     */
    private void initYear(Context context) {
        NumericWheelAdapter numericWheelAdapter = new NumericWheelAdapter(context,
                START_YEAR, END_YEAR);
        numericWheelAdapter.setLabel(" 年");
        year.setViewAdapter(numericWheelAdapter);
        year.setCyclic(true);
//        year.setBackgroundColor(context.getResources().getColor(R.color.color_main_blue));
    }

    /**
     * 初始化月
     */
    private void initMonth(Context context) {
        NumericWheelAdapter numericWheelAdapter = new NumericWheelAdapter(context, 1, 12, "%02d");
        numericWheelAdapter.setLabel(" 月");
        month.setViewAdapter(numericWheelAdapter);
        month.setCyclic(true);
    }

    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {
        if (wheel == year || wheel == month) {
            int yearNum = year.getCurrentItem() + START_YEAR;
            int monthNum = month.getCurrentItem() + 1;
            //如果当期滑动的是年并且当前月份不等于2时结束监听避免重复创建对象
//            if (wheel == year && monthNum != 2) {
//                return;
//            }
            setTvText(R.id.tv_time,String.valueOf(yearNum)+"-"+String.valueOf(monthNum));
//            initDay(yearNum, monthNum, context);
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
        void onClickOK(long date,String strDate);
        void onClickCancel();

    }
}
