package com.mark.app.hjshop4a.common.utils.luban.core;

import java.io.File;

public interface OnCompressListener {
    void onStart();

    void onSuccess(File var1);

    void onError(Throwable var1);
}
