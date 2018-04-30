package com.mark.app.hjshop4a.ui.calendarview;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.base.Activity.BaseActivity;
import com.mark.app.hjshop4a.common.utils.NumberUtils;
import com.mark.app.hjshop4a.widget.calendarview.bean.DateBean;
import com.mark.app.hjshop4a.widget.calendarview.listener.OnMultiChooseListener;
import com.mark.app.hjshop4a.widget.calendarview.listener.OnPagerChangeListener;
import com.mark.app.hjshop4a.widget.calendarview.weiget.CalendarView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Set;


/**
 * Created by pc on 2018/4/23.
 */

public class CalendarViewActivity extends BaseActivity {
    private  String title ;
    private CalendarView calendarView;
    private ArrayList<DateBean> mData =new ArrayList<>() ; //选择的时间
    @Override
    public int getContentViewResId() {
        return R.layout.activity_select_calenderview;
    }

    @Override
    public void findView() {
        super.findView();
        calendarView =(CalendarView) findViewById(R.id.calendar);

    }

    @Override
    public void getIntentParam(Bundle bundle) {
        super.getIntentParam(bundle);
        title =bundle.getString("title");

    }

    @Override
    public void initView() {
            setTvText(R.id.titlebar_tv_title,title);
        Calendar calendar = Calendar.getInstance();

        //年
        int year = calendar.get(Calendar.YEAR);
            //月
        int month = calendar.get(Calendar.MONTH)+1;

        setTvText(R.id.title,year+"年"+month+"月");
        BindData(year,month);
    }
private void BindData( int year,int month){
    calendarView
            .setStartEndDate("2017.1", "2019.12")
            .setDisableStartEndDate("2017.10.7", "2019.10.7")
            .setInitDate(year+"."+month)
            .init();
    calendarView.setOnMultiChooseListener(new OnMultiChooseListener() {
        @Override
        public void onMultiChoose(View view, DateBean date, boolean flag, ArrayList<DateBean> chooseday) {
            String d = date.getSolar()[0] + "." + date.getSolar()[1] + "." + date.getSolar()[2] + ".";

            ArrayList<DateBean> dateBeans =new ArrayList<>();
            //排序
            for(int i=0;i<chooseday.size();i++){
                for (int j=i+1;j<chooseday.size();j++)
                {
                    if(chooseday.get(i).getSolar()[0]>chooseday.get(j).getSolar()[0]){
                        dateBeans.add(chooseday.get(j));
                        break;
                    }
                    if(chooseday.get(i).getSolar()[1]>chooseday.get(j).getSolar()[1]){
                        dateBeans.add(chooseday.get(j));
                        break;
                    }
                    if(chooseday.get(i).getSolar()[2]>chooseday.get(j).getSolar()[2]){
                        dateBeans.add(chooseday.get(j));

                    }
                }
                dateBeans.add(chooseday.get(i));
            }
            mData =new ArrayList<>();
            for (int i=0; i<chooseday.size();i++){
                mData.add(chooseday.get(i));
            }
            //设置
            if (dateBeans.size()>0){
                int[] time =dateBeans.get(0).getSolar();
                setTvText(R.id.tv_time_start,time[0] + "年" + time[1] + "月"+time[2]+"日");
                dateBeans.remove(0);
            }
            else {
                setTvText(R.id.tv_time_start,"");
                setTvText(R.id.tv_time_end,"");
            }
            if (dateBeans.size()>0){
                int[] time =dateBeans.get(0).getSolar();
                setTvText(R.id.tv_time_end,time[0] + "年" + time[1] + "月"+time[2]+"日");
                dateBeans.remove(0);
            }else{
                setTvText(R.id.tv_time_end,"");
            }



        }


    });

    calendarView.setOnPagerChangeListener(new OnPagerChangeListener() {
        @Override
        public void onPagerChanged(int[] date) {
            setTvText(R.id.title,date[0] + "年" + date[1] + "月");

        }
    });
}
    @Override
    public void setListener() {
        setClickListener(R.id.titlebar_iv_return);
        setClickListener(R.id.set);
        setClickListener(R.id.reset);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.titlebar_iv_return:
                finish();
                break;
            case R.id.set:
                if(mData.size()==2) {
                    String s =  mData.get(0).getSolar()[0] + "." +  mData.get(0).getSolar()[1] + "." +  mData.get(0).getSolar()[2];
                    String e =  mData.get(0).getSolar()[0] + "." +  mData.get(0).getSolar()[1] + "." +  mData.get(0).getSolar()[2];

                    long starttime = Num(s);
                    long endtime =Num(e);
                    Intent intent = this.getIntent();
                    intent.putExtra("sTime", starttime);
                    intent.putExtra("eTime", endtime);
                    this.setResult(2, intent);
                    this.finish();
                }

                break;
            case R.id.reset:
                mData.clear();
                calendarView.clear();
                setTvText(R.id.tv_time_start,"");
                setTvText(R.id.tv_time_end,"");
                break;
        }
    }
    private long Num(String time){
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy.MM.dd",
                Locale.CHINA);
        Date date;
        try {
            date = sdr.parse(time);
            return date.getTime();
        } catch (ParseException e1) {
            e1.printStackTrace();
        }
        return 0;
    }
    public void lastMonth(View view) {
        calendarView.lastMonth();
    }

    public void nextMonth(View view) {
        calendarView.nextMonth();
    }
}
