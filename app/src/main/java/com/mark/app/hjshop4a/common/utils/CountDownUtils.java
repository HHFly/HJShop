package com.mark.app.hjshop4a.common.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.mark.app.hjshop4a.R;


/**
 * 倒计时工具类
 * Created by lenovo on 2017/9/5.
 */

public class CountDownUtils {

    //倒计时结束时间
    private long mEndTIme;
    //倒计时总时长
    private final long COUNT_DOWN_TIME = 60 * 1000;

    //开始倒计时
    private final int COUNT_DOWN_START = 1;

    //倒计时准备与结束的文案
    private int resIdNormal = R.string.获取验证码;
    private int resIdOver = R.string.重新获取;
    private String strNormal;
    private String strOver;

    //倒计时的视图View
    private TextView btn;
    //倒计时总时长
    private long mTime = COUNT_DOWN_TIME;
    //倒计时刷新间隔
    private long mDelay = 1000;

    //倒计时监听
    private CountDownListener listener;

    private boolean isCountDown;

    //倒计时控制器
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case COUNT_DOWN_START: {
                    //开始倒计时
                    countDownStart();
                    break;
                }
            }
        }
    };

    public CountDownUtils(Context context, TextView tv) {
        strNormal = context.getString(resIdNormal);
        strOver = context.getString(resIdOver);
        this.btn = tv;
    }

    /**
     * 获取实例
     */
    public static CountDownUtils getInstance(Context context) {
        return getInstance(context, null);
    }

    /**
     * 获取实例
     */
    public static CountDownUtils getInstance(Context context, TextView tv) {
        return new CountDownUtils(context, tv);
    }

    /**
     * 设置倒计时总时长
     *
     * @param time
     */
    public void setTime(long time) {
        mTime = time;
    }

    /**
     * 设置倒计时间隔
     *
     * @param delay
     */
    public void setDelay(long delay) {
        mDelay = delay;
    }

    /**
     * 准备倒计时
     */
    public void pre() {
        countDownPre();
    }

    /**
     * 开始倒计时
     */
    public void start() {
        countDownPre();
        countDownStart();
    }

    /**
     * 倒计时结束
     */
    public void over() {
        countDownOver();
    }

    /**
     * 停止倒计时
     */
    public void stop() {
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
    }

    /**
     * 释放资源
     */
    public void onDestroy() {
        strOver = null;
        strNormal = null;
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
            mHandler = null;
        }
        btn = null;
        listener = null;
    }

    /**
     * 发送Handler请求
     *
     * @param what
     */
    private void send(int what, long delay) {
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
            mHandler.sendEmptyMessageDelayed(what, delay);
        }
    }

    /**
     * 准备倒计时
     */
    private void countDownPre() {
        isCountDown = true;
        mEndTIme = System.currentTimeMillis() + mTime;
        //按钮设置为不可点击
        if (btn != null) {
            btn.setEnabled(false);
        }
        if (listener != null) {
            listener.countDownPre();
        }
    }

    /**
     * 开始倒计时
     */
    private void countDownStart() {
        isCountDown = true;
        //倒计时剩余时间
        long remainTime = mEndTIme - System.currentTimeMillis();
        if (remainTime >= 0) {
            //将毫秒转换为秒
            int second = (int) (remainTime / 1000);
            if (remainTime % 1000 != 0) {
                second++;
            }
            if (second < 0) {
                second = 0;
            }
            if (btn != null) {
                btn.setText(second + "S");
            }

            //隔一秒继续
            send(COUNT_DOWN_START, mDelay);
        } else {
            //倒计时结束
            countDownOver();
        }
        if (listener != null) {
            listener.countDownStart(remainTime);
        }
    }

    /**
     * 倒计时结束
     */
    private void countDownOver() {
        isCountDown = false;
        if (btn != null) {
            btn.setEnabled(true);
            btn.setText(strOver);
        }
        if (listener != null) {
            listener.countDownOver();
        }
    }

    /**
     * 是否处于倒计时
     *
     * @return
     */
    public boolean isCountDown() {
        return isCountDown;
    }

    /**
     * 设置倒计时监听
     *
     * @param listener
     */
    public void setCountDownListener(CountDownListener listener) {
        this.listener = listener;
    }

    /**
     * 倒计时监听
     */
    public interface CountDownListener {
        /**
         * 准备倒计时
         */
        void countDownPre();

        /**
         * 开始倒计时
         *
         * @param remainTime 剩余时间毫秒
         */
        void countDownStart(long remainTime);

        /**
         * 结束倒计时
         */
        void countDownOver();
    }

    /**
     * 默认倒计时监听
     */
    public static class DefaultCountDownListener implements CountDownListener {

        @Override
        public void countDownPre() {

        }

        @Override
        public void countDownStart(long remainTime) {

        }

        @Override
        public void countDownOver() {

        }
    }
}
