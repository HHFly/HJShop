package com.mark.app.hjshop4a.common.listener;

/**
 * 上传图片监听
 */
public interface OnUploadPicListener<T> {
    void onLoadPicFinish(String imgUrl);

    void onLoadPicUnSuccessFinish();
}