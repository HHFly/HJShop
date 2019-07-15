package com.mark.app.hjshop4a.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.mark.app.hjshop4a.R;

import me.jessyan.autosize.utils.AutoSizeUtils;

public class OrderLinearLayout extends WarpLinearLayout {
    private TextView textView;
    private TextView  text;
    private Context context;
    public OrderLinearLayout(Context context) {
        this(context, null);
    }

    public OrderLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context =context;
        initView();


    }

    public void addChild(String str){
        LayoutInflater inflater = LayoutInflater.from(context);
        View rootView = inflater.inflate(R.layout.item_remark_label,this , false);
        TextView tv = (TextView) rootView.findViewById(R.id.item_tv);
        tv.setText(str);
        addView(rootView);
    }
    public void clear(){
       removeAllViews();
        initView();
    }
    private void initView(){
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View rootView = inflater.inflate(R.layout.item_remark_title,this , false);
        addView(rootView);
    }

}
