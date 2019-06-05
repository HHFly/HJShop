package com.mark.app.hjshop4a.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mark.app.hjshop4a.R;

public class BandInfoItemView extends LinearLayout {
    private Type mType;
    TextView msg,status;
    ImageView img;
    public BandInfoItemView(Context context) {
        super(context);
    }

    public BandInfoItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mType = new Type(context, attrs);
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.item_bindinfo, this);
         msg  =findViewById(R.id.msg);
        msg.setText(mType.msg);
         status =findViewById(R.id.status);
        status.setText(mType.status);
         img =findViewById(R.id.img);
         img.setBackgroundResource(mType.img);
    }


    private final static class Type {
        /*
         *对齐方式 right 0，left 1，center 2
         */
        private int img ;
        private String msg="";
        private String status="";

        Type(Context context, AttributeSet attrs) {
            if (attrs == null) {
                return;
            }
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BindInfoLayout);
            img =typedArray.getResourceId(R.styleable.BindInfoLayout_img,img);
            msg = typedArray.getString(R.styleable.BindInfoLayout_msg);
            status = typedArray.getString(R.styleable.BindInfoLayout_status);

            typedArray.recycle();
        }


    }
    public int getImg() {
        return mType.img;
    }

    public void setImg(int img) {
        mType.img = img;
        invalidate();
    }

    public String getMsg() {
        return mType.msg;
    }

    public void setMsg(String msg) {
        mType.msg = msg;
        invalidate();
    }

    public String getStatus() {
        return mType.status;
    }

    public void setStatus(String status) {
        mType.status = status;
        invalidate();
    }

}
