package com.mark.app.hjshop4a.common.valid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by pc on 2018/3/22.
 */

public class LoadImageFromUrlUtils {
    private static LoadImageFromUrlUtils loadImageFromUrl;
    private static Context context;
    private static String imageurl;
    private static NetHandler netHandler;

    /**
     * 获取实例
     *
     * @return
     */
    private static LoadImageFromUrlUtils getInstance() {
        if (loadImageFromUrl == null) {
            synchronized (LoadImageFromUrlUtils.class) {
                if (loadImageFromUrl == null) {
                    loadImageFromUrl = new LoadImageFromUrlUtils();
                }
            }
        }
        return loadImageFromUrl;
    }
    public static  void donwloadImg(Context contexts, String url){
        context = contexts;
        imageurl = url;
        new Thread(loadImage).start();
    }
    private static Runnable loadImage =new Runnable() {
        @Override
        public void run() {


            Message message=new Message();
            message.obj= loadImageFromUrl(imageurl);
            if(message.obj!=null){
                message.what=0;
                messageHandler.sendMessage(message);
            }else {
                message.what=1;
                messageHandler.sendMessage(message);
            }
        }
    };
    private static Bitmap loadImageFromUrl(String url) {
        Bitmap bitmap = null;
        try{

            bitmap = BitmapFactory.decodeStream(getImageStream(url));

        }
        catch (Exception e) {
            e.printStackTrace();

        }
        return bitmap;
    }
    /**
     * Get image from newwork
     * @param path The path of image
     * @return InputStream
     * @throws Exception
     */
    private static InputStream getImageStream(String path) throws Exception{
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5 * 1000);
        conn.setRequestMethod("GET");
        if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
            return conn.getInputStream();
        }
        return null;
    }
    private static Handler messageHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what){
                case 0:
                    Bitmap result=(Bitmap) msg.obj;
                    onSuccess(result);
                    break;
                case 1:
                    String e=(String) msg.obj;
                    onFailure(e.toString());
                    break;

            }
        }
    };

    public static void onSuccess(Bitmap bitmap){
        netHandler.onSuccess(bitmap);
    }
    public static void onFailure(Object object){
        netHandler.onFailure(object);
    }
    public static void setOnNetListener(NetHandler netListener){
        netHandler=netListener;

    }
    public interface NetHandler{
        public void onSuccess(Bitmap result);

        public void onFailure(Object exception);

    }

}
