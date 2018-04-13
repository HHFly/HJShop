package com.mark.app.hjshop4a.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.Paint.Style;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.mark.app.hjshop4a.base.model.BaseModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 自定义滚动选择器
 *
 * @author 网上控件
 */
public class PickerScrollView extends View {

    private final String TAG = "PickerView";
    /**
     * text之间间距和minTextSize之比
     */
    private final float MARGIN_ALPHA = 2.8f;
    /**
     * 自动回滚到中间的速度
     */
    private final float SPEED = 2;

    private List<ItemModel> mDataList;     //数据源

    /**
     * 选中的位置，这个位置是mDataList的中心位置，一直不变
     */
    private int mCurrentSelected;       //选中的数据源索引
    private Paint mTextPaint;           //文本画笔
    private Paint mRectPaint;
    private float mMaxTextSize = 20;    //最大文本大小
    private float mMinTextSize = 10;    //最小文本大小
    private float mMaxTextAlpha = 255;  //最大透明度
    private float mMinTextAlpha = 120;  //最小透明度
    private int mColorText = 0x333333;  //字体颜色
    private int mShowCount = 5;         //显示条数

    private int mViewHeight;   //View宽度
    private int mViewWidth;    //View高度
    private float mLastDownY;  //最后触摸位置
    /**
     * 滑动的距离
     */
    private float mMoveLen = 0;
    private boolean isInit = false;
    private onSelectListener mSelectListener;
    private Timer timer;
    private MyTimerTask mTask;

    private Handler updateHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            if (Math.abs(mMoveLen) < SPEED) {
                mMoveLen = 0;
                if (mTask != null) {
                    mTask.cancel();
                    mTask = null;
                    useOnSelectListener();
                }
            } else
                // 这里mMoveLen / Math.abs(mMoveLen)是为了保有mMoveLen的正负号，以实现上滚或下滚  
                mMoveLen = mMoveLen - mMoveLen / Math.abs(mMoveLen) * SPEED;
            invalidate();
        }

    };

    public PickerScrollView(Context context) {
        this(context, null);
    }

    public PickerScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        initParam();
        initPaint();
        initNormalData();
    }

    /**
     * 初始化测试数据
     */
    private void initNormalData() {
        mDataList.clear();
        for (int i = 0; i < 6; i++) {
            ItemModel item = new ItemModel();
            item.setId(i);
            item.setName("itemContent" + i);
            mDataList.add(item);
        }
    }

    /**
     * 初始化参数
     */
    private void initParam() {
        timer = new Timer();
        mDataList = new ArrayList<>();
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setStyle(Style.FILL);
        mTextPaint.setTextAlign(Align.CENTER);
        mTextPaint.setColor(mColorText);
        mRectPaint = new Paint();
        mRectPaint.setColor(Color.WHITE);
    }

    /**
     * 设置选中监听器
     *
     * @param listener
     */
    public void setOnSelectListener(onSelectListener listener) {
        mSelectListener = listener;
        useOnSelectListener();
    }

    /**
     * 调用选中监听器
     */
    public void useOnSelectListener() {
        if (mSelectListener != null) {
            mSelectListener.onSelect(mDataList.get(mCurrentSelected));
        }
    }

    /**
     * 设置数据源
     *
     * @param list
     */
    public void setData(List<ItemModel> list) {
        if (mDataList == null) {
            mDataList = new ArrayList<>();
        }
        mDataList.clear();
        mDataList.addAll(list);
        invalidate();
    }

    /**
     * 选择选中的item的index
     *
     * @param selected
     */
    public void setSelected(int selected) {
        mCurrentSelected = selected;
        for (int i = 0; i < mCurrentSelected; i++) {
            moveHeadToTail();
        }
        invalidate();
    }

    /**
     * 把头部移动到尾巴
     */
    private void moveHeadToTail() {
        ItemModel head = mDataList.get(0);
        mDataList.remove(0);
        mDataList.add(head);
    }

    /**
     * 把尾巴移动到头部
     */
    private void moveTailToHead() {
        ItemModel tail = mDataList.get(mDataList.size() - 1);
        mDataList.remove(mDataList.size() - 1);
        mDataList.add(0, tail);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mViewHeight = getMeasuredHeight();
        mViewWidth = getMeasuredWidth();
        // 按照View的高度计算字体大小
        mMaxTextSize = mViewHeight / 8.0f;
        mMinTextSize = mMaxTextSize / 1.8f;
        isInit = true;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 根据index绘制view  
        if (isInit) {
            drawSelectedText(canvas);
            drawOtherText(canvas);
        }
    }

    /**
     * 绘制选中文本
     *
     * @param canvas
     */
    private void drawSelectedText(Canvas canvas) {
        if (mDataList == null || mDataList.size() == 0) return;
        int offsetY = -(int) (mViewHeight / 2 - mMaxTextSize);
        //根据移动位置改变文本大小和透明度
        float scale = parabola(mViewHeight / 4.0f, mMoveLen);
        float size = (mMaxTextSize - mMinTextSize) * scale + mMinTextSize;
        mTextPaint.setTextSize(size);
        mTextPaint.setAlpha((int) ((mMaxTextAlpha - mMinTextAlpha) * scale + mMinTextAlpha));

        //计算文本位置
        float x = (float) (mViewWidth / 2.0);
        float y = (float) (mViewHeight / 2.0 + mMoveLen);
        FontMetricsInt fmi = mTextPaint.getFontMetricsInt();
        float baseline = (float) (y - (fmi.bottom / 2.0 + fmi.top / 2.0));

        float yy = 2 * (baseline - y);

        canvas.drawRect(0, y + offsetY - yy, mViewWidth, y + offsetY + yy, mRectPaint);

        //开始绘制文本
        int currentIndex = mCurrentSelected;
        String textData = mDataList.get(currentIndex).getName();
        canvas.drawText(textData, x, baseline + offsetY, mTextPaint);
    }

    /**
     * 绘制其他文本
     *
     * @param canvas
     */
    private void drawOtherText(Canvas canvas) {
        int count = Math.min(mDataList.size(), mShowCount);
        for (int i = mCurrentSelected + 1; i < count; i++) {
            drawOtherText(canvas, i, 1);
        }
    }

    /**
     * 绘制其他文本
     *
     * @param canvas
     * @param position 距离mCurrentSelected的差值
     * @param type     1表示向下绘制，-1表示向上绘制
     */
    private void drawOtherText(Canvas canvas, int position, int type) {
        int offsetY = -(int) (mViewHeight / 2 - mMaxTextSize);
        //根据移动位置改变文本大小和透明度
        float d = MARGIN_ALPHA * mMinTextSize * position + type * mMoveLen;
        float scale = parabola(mViewHeight / 4.0f, d);
        float size = (mMaxTextSize - mMinTextSize) * scale + mMinTextSize;
        mTextPaint.setTextSize(size);
        mTextPaint.setAlpha((int) ((mMaxTextAlpha - mMinTextAlpha) * scale + mMinTextAlpha));

        //计算文本位置
        float y = (float) (mViewHeight / 2.0 + type * d);
        FontMetricsInt fmi = mTextPaint.getFontMetricsInt();
        float baseline = (float) (y - (fmi.bottom / 2.0 + fmi.top / 2.0));

        //开始绘制文本
        int index = mCurrentSelected + type * position;
        String textData = mDataList.get(index).getName();
        canvas.drawText(textData, (float) (mViewWidth / 2.0), baseline + offsetY, mTextPaint);
    }

    /**
     * 抛物线
     *
     * @param zero 零点坐标
     * @param x    偏移量
     * @return scale
     */
    private float parabola(float zero, float x) {
        float f = (float) (1 - Math.pow(x / zero, 2));
        return f < 0 ? 0 : f;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                doDown(event);
                break;
            case MotionEvent.ACTION_MOVE:
                doMove(event);
                break;
            case MotionEvent.ACTION_UP:
                doUp(event);
                break;
        }
        return true;
    }

    private void doDown(MotionEvent event) {
        if (mTask != null) {
            mTask.cancel();
            mTask = null;
        }
        mLastDownY = event.getY();
    }

    private void doMove(MotionEvent event) {

        mMoveLen += (event.getY() - mLastDownY);

        if (mMoveLen > MARGIN_ALPHA * mMinTextSize / 2) {
            // 往下滑超过离开距离  
            moveTailToHead();
            mMoveLen = mMoveLen - MARGIN_ALPHA * mMinTextSize;
        } else if (mMoveLen < -MARGIN_ALPHA * mMinTextSize / 2) {
            // 往上滑超过离开距离  
            moveHeadToTail();
            mMoveLen = mMoveLen + MARGIN_ALPHA * mMinTextSize;
        }

        mLastDownY = event.getY();
        invalidate();
    }

    private void doUp(MotionEvent event) {
        // 抬起手后mCurrentSelected的位置由当前位置move到中间选中位置  
        if (Math.abs(mMoveLen) < 0.0001) {
            mMoveLen = 0;
            return;
        }
        if (mTask != null) {
            mTask.cancel();
            mTask = null;
        }
        mTask = new MyTimerTask(updateHandler);
        timer.schedule(mTask, 0, 10);
    }

    class MyTimerTask extends TimerTask {
        Handler handler;

        public MyTimerTask(Handler handler) {
            this.handler = handler;
        }

        @Override
        public void run() {
            handler.sendMessage(handler.obtainMessage());
        }

    }

    public interface onSelectListener<T extends ItemModel> {
        void onSelect(T data);
    }

    /**
     * item数据模型
     */
    public static class ItemModel extends BaseModel {
        /**
         * id
         */
        private long id;
        /**
         * 名称
         */
        private String name;

        public ItemModel() {
        }

        public ItemModel(long id, String name) {
            this.id = id;
            this.name = name;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}