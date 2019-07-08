package com.mark.app.hjshop4a.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.mark.app.hjshop4a.R;


import java.util.ArrayList;
import java.util.List;

/**
 * 自动换行的LineLayout
 */
public class WarpLinearLayout extends LinearLayout {
    //没有行数限制
    public static final int VALUE_NO_MAX = -1;
    private Context context;
    private Type mType;
    private List<WarpLine> mWarpLineGroup;

    public WarpLinearLayout(Context context) {
        this(context, null);
    }

    public WarpLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mType = new Type(context, attrs);
        this.context =context;
    }
    public void addChild(String str){
      TextView  text =new TextView(context);
        text.setTextColor(Color.parseColor("#289af8"));
        text.setTextSize(TypedValue.COMPLEX_UNIT_DIP,getTipStr());
        text.setPadding(11,4,11,4);
        text.setBackground(context.getResources().getDrawable(R.drawable.shape_blue_6px));
        text.setText(str);
        addView(text);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int withMode = MeasureSpec.getMode(widthMeasureSpec);
        int withSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int with = 0;
        int height = 0;
        int childCount = getChildCount();
        /**
         * 在调用childView。getMeasre之前必须先调用该行代码，用于对子View大小的测量
         */
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        /**
         * 计算宽度
         */
        switch (withMode) {
            case MeasureSpec.EXACTLY:
                with = withSize;
                break;
            case MeasureSpec.AT_MOST:
                for (int i = 0; i < childCount; i++) {
                    if (i != 0) {
                        with += mType.horizontal_Space;
                    }
                    with += getChildAt(i).getMeasuredWidth();
                }
                with += getPaddingLeft() + getPaddingRight();
                with = with > withSize ? withSize : with;
                break;
            case MeasureSpec.UNSPECIFIED:
                for (int i = 0; i < childCount; i++) {
                    if (i != 0) {
                        with += mType.horizontal_Space;
                    }
                    with += getChildAt(i).getMeasuredWidth();
                }
                with += getPaddingLeft() + getPaddingRight();
                break;
            default:
                with = withSize;
                break;

        }
        /**
         * 根据计算出的宽度，计算出所需要的行数
         */
        WarpLine warpLine = new WarpLine();
        /**
         * 不能够在定义属性时初始化，因为onMeasure方法会多次调用
         */
        if (mWarpLineGroup == null) {
            mWarpLineGroup = new ArrayList<>();
        }
        mWarpLineGroup.clear();
        for (int i = 0; i < childCount; i++) {
            if (warpLine.lineWidth + getChildAt(i).getMeasuredWidth() + mType.horizontal_Space > with) {
                //换行
                if (warpLine.lineView.size() == 0) {
                    warpLine.addView(getChildAt(i));
                    //如果当前有最大行数且mWarpLineGroup大小大于等于最大行数
                    //退出循环
                    if (mType.maxLine != VALUE_NO_MAX && mWarpLineGroup.size() >= mType.maxLine) {
                        break;
                    }
                    mWarpLineGroup.add(warpLine);
                    warpLine = new WarpLine();
                } else {
                    //如果当前有最大行数且mWarpLineGroup大小大于等于最大行数
                    //退出循环
                    if (mType.maxLine != VALUE_NO_MAX && mWarpLineGroup.size() == mType.maxLine) {
                        break;
                    }
                    mWarpLineGroup.add(warpLine);
                    warpLine = new WarpLine();
                    warpLine.addView(getChildAt(i));
                }
            } else {
                //不换行
                warpLine.addView(getChildAt(i));
            }
        }

        //如果没有最大限制或者有最大限制且当前行数小于最大行
        if (mType.maxLine == VALUE_NO_MAX || mWarpLineGroup.size() < mType.maxLine) {
            /**
             * 添加最后一行
             */
            if (warpLine.lineView.size() > 0 && !mWarpLineGroup.contains(warpLine)) {
                mWarpLineGroup.add(warpLine);
            }
        }
        /**
         * 计算宽度
         */
        height = getPaddingTop() + getPaddingBottom();
        for (int i = 0; i < mWarpLineGroup.size(); i++) {
            if (i != 0) {
                height += mType.vertical_Space;
            }
            height += mWarpLineGroup.get(i).height;
        }
        switch (heightMode) {
            case MeasureSpec.EXACTLY:
                height = heightSize;
                break;
            case MeasureSpec.AT_MOST:
                height = height > heightSize ? heightSize : height;
                break;
            case MeasureSpec.UNSPECIFIED:
                break;
            default:
                break;
        }
        setMeasuredDimension(with, height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        t = getPaddingTop();
        for (int i = 0; i < mWarpLineGroup.size(); i++) {
            int left = getPaddingLeft();
            WarpLine warpLine = mWarpLineGroup.get(i);
            int lastWidth = getMeasuredWidth() - warpLine.lineWidth;
            for (int j = 0; j < warpLine.lineView.size(); j++) {
                View view = warpLine.lineView.get(j);
                if (isFull()) {//需要充满当前行时
                    view.layout(left, t, left + view.getMeasuredWidth() + lastWidth / warpLine.lineView.size(), t + view.getMeasuredHeight());
                    left += view.getMeasuredWidth() + mType.horizontal_Space + lastWidth / warpLine.lineView.size();
                } else {
                    switch (getGrivate()) {
                        case 0://右对齐
                            view.layout(left + lastWidth, t, left + lastWidth + view.getMeasuredWidth(), t + view.getMeasuredHeight());
                            break;
                        case 2://居中对齐
                            view.layout(left + lastWidth / 2, t, left + lastWidth / 2 + view.getMeasuredWidth(), t + view.getMeasuredHeight());
                            break;
                        default://左对齐
                            view.layout(left, t, left + view.getMeasuredWidth(), t + view.getMeasuredHeight());
                            break;
                    }
                    left += view.getMeasuredWidth() + mType.horizontal_Space;
                }
            }
            t += warpLine.height + mType.vertical_Space;
        }
    }

    /**
     * 用于存放一行子View
     */
    private final class WarpLine {
        private List<View> lineView = new ArrayList<View>();
        /**
         * 当前行中所需要占用的宽度
         */
        private int lineWidth = getPaddingLeft() + getPaddingRight();
        /**
         * 该行View中所需要占用的最大高度
         */
        private int height = 0;

        private void addView(View view) {
            if (lineView.size() != 0) {
                lineWidth += mType.horizontal_Space;
            }
            height = height > view.getMeasuredHeight() ? height : view.getMeasuredHeight();
            lineWidth += view.getMeasuredWidth();
            lineView.add(view);
        }
    }

    /**
     * 对样式的初始化
     */
    private final static class Type {
        /*
         *对齐方式 right 0，left 1，center 2
        */
        private int grivate;
        /**
         * 水平间距,单位px
         */
        private float horizontal_Space;
        /**
         * 垂直间距,单位px
         */
        private float vertical_Space;
        /**
         * 是否自动填满
         */
        private boolean isFull;
        /**
         * 最大行数
         */
        private int maxLine;
        /**
         * ,单位dp
         */
        private float  title;
        private float  tipStr;
        Type(Context context, AttributeSet attrs) {
            if (attrs == null) {
                return;
            }
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.WarpLinearLayout);
            grivate = typedArray.getInt(R.styleable.WarpLinearLayout_grivate, grivate);
            horizontal_Space = typedArray.getDimension(R.styleable.WarpLinearLayout_horizontal_Space, horizontal_Space);
            vertical_Space = typedArray.getDimension(R.styleable.WarpLinearLayout_vertical_Space, vertical_Space);
            isFull = typedArray.getBoolean(R.styleable.WarpLinearLayout_isFull, isFull);
            maxLine = typedArray.getInt(R.styleable.WarpLinearLayout_maxLine, VALUE_NO_MAX);
            title = typedArray.getDimension(R.styleable.WarpLinearLayout_titleSize, title);
            tipStr = typedArray.getDimension(R.styleable.WarpLinearLayout_tipSize, tipStr);

            typedArray.recycle();
        }


    }
    public float getTitle() {
        return mType.title;
    }

    public void setTitle(float title) {
        mType.title = title;
    }

    public float getTipStr() {
        return mType.tipStr;
    }

    public void setTipStr(float tipStr) {
        mType.tipStr = tipStr;
    }
    public int getGrivate() {
        return mType.grivate;
    }

    public float getHorizontal_Space() {
        return mType.horizontal_Space;
    }

    public float getVertical_Space() {
        return mType.vertical_Space;
    }

    public boolean isFull() {
        return mType.isFull;
    }

    public void setGrivate(int grivate) {
        mType.grivate = grivate;
    }

    public void setHorizontal_Space(float horizontal_Space) {
        mType.horizontal_Space = horizontal_Space;
    }

    public void setVertical_Space(float vertical_Space) {
        mType.vertical_Space = vertical_Space;
    }

    public void setIsFull(boolean isFull) {
        mType.isFull = isFull;
    }

    /**
     * 每行子View的对齐方式
     */
    public final static class Gravite {
        public final static int RIGHT = 0;
        public final static int LEFT = 1;
        public final static int CENTER = 2;
    }


}
