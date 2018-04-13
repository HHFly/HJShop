package com.mark.app.hjshop4a.common.utils;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.widget.Toast;



import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zhuwh on 2017/9/6.
 */

public class PdUtils {

    public static final int CALL_REQUEST_PERMISSION_CODE = 1;//相机请求权限

    public static void sendSMS(Context context, String phoneNum, String body) {
        Uri uri = Uri.parse("smsto:" + phoneNum);
        Intent intentMessage = new Intent(Intent.ACTION_VIEW, uri);
        if (body != null) {
            intentMessage.putExtra("sms_body", body);
        }
        try {
            context.startActivity(intentMessage);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(context, "本机不支持发送短信功能", Toast.LENGTH_SHORT).show();
        }

    }

    public static void call(Activity context, String phoneNum) {

        try {
            MKPermissionUtils permissionUtils = new MKPermissionUtils(context, context);
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNum));
                context.startActivity(intent);
            } else {
//android6.0+以上系统，需要动态申请权限
                String[] PERMISSIONS = new String[]{Manifest.permission.CALL_PHONE};
                if (permissionUtils.lacksPermissions(PERMISSIONS)) {
                    final String permission = Manifest.permission.CALL_PHONE;  //电话权限
                    permissionUtils.requestPermissions(PERMISSIONS, CALL_REQUEST_PERMISSION_CODE, permission);
                } else {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNum));
                    context.startActivity(intent);
                }
            }
        } catch (ActivityNotFoundException e) {
            Toast.makeText(context, "本机不支持打电话功能", Toast.LENGTH_SHORT).show();
        }

    }



    /**
     * 获取缩放后的图片
     *
     * @param context
     * @param resId
     * @param newWidth
     * @return
     */
    private static Bitmap getScaleBitmap(Context context, int resId, int newWidth) {
        Bitmap oldBitmap = BitmapFactory.decodeResource(context.getResources(), resId);
        int width = oldBitmap.getWidth();
        int height = oldBitmap.getHeight();
        float scale = ((float) newWidth) / width;
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);
        Bitmap newBitmap = Bitmap.createBitmap(oldBitmap, 0, 0, width, height, matrix, true);
        oldBitmap.recycle();
        return newBitmap;
    }

    final static char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f'};

    public static String getMD5(String val) {

        try {
            byte[] btInput = val.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }
}
