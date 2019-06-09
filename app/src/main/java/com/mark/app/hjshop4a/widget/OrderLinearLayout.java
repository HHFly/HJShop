package com.mark.app.hjshop4a.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;

import com.mark.app.hjshop4a.R;

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
        textView =new TextView(context);
        textView.setText(R.string.活动要求);
        textView.setPadding(0,0,0,0);
        textView.setTextColor(Color.parseColor("#333333"));
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX,24);
        addView(textView);


    }

    public void addChild(String str){
        text =new TextView(context);
        text.setTextColor(Color.parseColor("#999999"));
        text.setTextSize(TypedValue.COMPLEX_UNIT_PX,20);
        text.setPadding(11,4,11,4);
        text.setBackground(context.getResources().getDrawable(R.drawable.shape_gray_6px));
        text.setText(str);
        addView(text);
    }
}