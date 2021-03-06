package com.mark.app.hjshop4a.common.utils.luban.core;

import android.support.annotation.Nullable;

public class Preconditions {
    public Preconditions() {
    }

    static <T> T checkNotNull(T reference) {
        if (reference == null) {
            throw new NullPointerException();
        } else {
            return reference;
        }
    }

    static <T> T checkNotNull(T reference, @Nullable Object errorMessage) {
        if (reference == null) {
            throw new NullPointerException(String.valueOf(errorMessage));
        } else {
            return reference;
        }
    }
}
