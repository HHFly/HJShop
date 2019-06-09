package com.mark.app.hjshop4a.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.common.utils.FrescoUtils;
import com.mark.app.hjshop4a.model.login.model.LoginType;
import com.mark.app.hjshop4a.ui.dialog.factory.FunctionDialogFactory;
import com.mark.app.hjshop4a.uinew.login.adapter.LoginSwitchAdapter;

public class UpdateImgLayout extends LinearLayout {
    private Type mType;
    TextView title,tipimg;
    SimpleDraweeView img;
    public UpdateImgLayout(Context context) {
        super(context);
    }

    public UpdateImgLayout(final Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mType = new Type(context, attrs);
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.item_update_img, this);
        title  =findViewById(R.id.tv_title);
        title.setText(mType.title);
        tipimg =findViewById(R.id.tv_tip);
       tipimg.setOnClickListener(new OnClickListener() {
           @Override
           public void onClick(View v) {
               FunctionDialogFactory.showBigSdvDialog(App.get().getFragmentManager(),mType.tipimg);
           }
       });
        img =findViewById(R.id.sdv_img);
        img.setImageResource(mType.img);

        img.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener!=null){
                    onItemClickListener.onClickImg();
                }
            }
        });
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
        void onClickImg();


    }
    private final static class Type {
        /*
         *对齐方式 right 0，left 1，center 2
         */
        private int img ;
        private String title="";
        private int tipimg;

        Type(Context context, AttributeSet attrs) {
            if (attrs == null) {
                return;
            }
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.UpdateImgayout);
            img =typedArray.getResourceId(R.styleable.UpdateImgayout_img,img);
            title = typedArray.getString(R.styleable.UpdateImgayout_title);
            tipimg = typedArray.getResourceId(R.styleable.UpdateImgayout_tipimg,tipimg);

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

    public String getTitle() {
        return mType.title;
    }

    public void setTitle(String title) {
        mType.title = title;
        invalidate();
    }

    public int getTipimg() {
        return mType.tipimg;
    }

    public void setTipimg(int tipimg) {
        mType.tipimg = tipimg;
        invalidate();
    }
}
