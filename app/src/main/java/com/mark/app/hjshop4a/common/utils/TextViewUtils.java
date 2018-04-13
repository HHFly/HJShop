package com.mark.app.hjshop4a.common.utils;

import android.annotation.SuppressLint;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 功能：TextView处理工具
 * 作者：林敬聚
 */

public class TextViewUtils {
    private static final String TAG = "TextViewUtils";

    /**
     * 将msg中的key内容的字体颜色变成keyColor
     *
     * @param msg
     * @param key
     * @param keyColor
     * @return
     */
    public static void setSpannable(TextView textView, String msg, String key, int keyColor) {
        if (TextUtils.isEmpty(key)) {
            LogUtils.logFormat(TAG, "setSpannable", "key is null");
            return;
        }
        SpannableStringBuilder result = getSpannable(msg, key, keyColor);
        textView.setText(result);
    }

    /**
     * 将msg中的key内容的字体颜色变成keyColor
     *
     * @param msg
     * @param key
     * @param keyColor
     * @return
     */
    public static SpannableStringBuilder getSpannable(String msg, String key, int keyColor) {
//        ShowUtils.logFormat(TAG, "getSpannable", "msg:" + msg);
//        ShowUtils.logFormat(TAG, "getSpannable", "key:" + key);
        SpannableStringBuilder result = new SpannableStringBuilder(msg);
        if (TextUtils.isEmpty(key)) {
            LogUtils.logFormat(TAG, "getSpannable", "key is null");
            return result;
        }
        try {
            ForegroundColorSpan span = new ForegroundColorSpan(keyColor);
            int start = msg.indexOf(key);
            int end = start + key.length();
            result.setSpan(span, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 数字添加下划线
     *
     * @param msg
     * @return
     */
    public static SpannableStringBuilder getSpannableExcep(String msg) {
        SpannableStringBuilder result = new SpannableStringBuilder(msg);
        try {
            int len = msg == null ? 0 : msg.length();
            for (int j = 0; j < len; j++) {
                char c = msg.charAt(j);
                Pattern p = Pattern.compile("[0-9]*");
                Matcher m = p.matcher(c + "");
                if (m.matches()) {
                    result.setSpan(new UnderlineSpan(), j, j + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 全部下划线
     *
     * @param msg
     * @return
     */
    public static SpannableStringBuilder getSpannableExcepAll(String msg) {
        SpannableStringBuilder result = new SpannableStringBuilder(msg);
        try {
            result.setSpan(new UnderlineSpan(), 0, msg.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 加粗
     *
     * @param content
     * @param searchContent
     * @return
     */
    public static SpannableStringBuilder changeSearchContentStyle(String content, String searchContent) {
        SpannableStringBuilder searchStyle = new SpannableStringBuilder();
        if (TextUtils.isEmpty(searchContent)) {
            searchStyle.append(content);
            LogUtils.logFormat(TAG, "changeSearchContentStyle", "searchContent is null");
            return searchStyle;
        }
        int start;
        while (content.contains(searchContent)) {
            start = content.indexOf(searchContent);
            searchStyle.append(getBoldSpannable(content.substring(0, start + searchContent.length()), searchContent));
            content = content.substring(start + searchContent.length());
        }
        searchStyle.append(content);
        return searchStyle;
    }

    /**
     * 部分加粗
     *
     * @param content
     * @param searchContent
     * @return
     */
    private static SpannableStringBuilder getBoldSpannable(String content, String searchContent) {
        int start = content.indexOf(searchContent);
        SpannableStringBuilder ssb = new SpannableStringBuilder(content);

        if (TextUtils.isEmpty(searchContent)) {
            LogUtils.logFormat(TAG, "getBoldSpannable", "searchContent is null");
            return ssb;
        }

        ssb.setSpan(new SearchStyleSpan(Typeface.NORMAL), start, start + searchContent.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return ssb;
    }

    /**
     * 添加中划线
     *
     * @param textView
     */
    public static void setTextViewPaintFlag(TextView textView) {
        if (textView != null) {
            textView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        }
    }

    /**
     * 添加中划线
     *
     * @param textView
     * @param msg
     */
    public static void setTextViewPaintFlag(TextView textView, String msg) {
        if (textView != null) {
            textView.setText(msg);
        }
        setTextViewPaintFlag(textView);
    }

    @SuppressLint("ParcelCreator")
    public static class SearchStyleSpan extends StyleSpan {
        public SearchStyleSpan(int style) {
            super(style);
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            ds.setFakeBoldText(true);
            //FIXME 这里还可以做其他差异性设置（修改文字大小等）
            super.updateDrawState(ds);
        }

        @Override
        public void updateMeasureState(TextPaint paint) {
            paint.setFakeBoldText(true);
            super.updateMeasureState(paint);
        }
    }
}
