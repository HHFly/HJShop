package com.haya.app.pandah4a.model.pay;

import android.os.Parcel;
import android.os.Parcelable;

import com.haya.app.pandah4a.common.androidenum.pay.PayResultStatus;

/**
 * 支付结果
 * Created by lenovo on 2017/9/9.
 */

public class PayResult implements Parcelable {
    /**
     * 状态值
     */
    private int resultStatus;
    /**
     * 状态描述
     */
    private String result;

    public PayResult(@PayResultStatus int status, String result) {
        this.resultStatus = status;
        this.result = result;
    }

    public PayResult(Parcel source) {
        this.resultStatus = source.readInt();
        this.result = source.readString();
    }

    public int getResultStatus() {
        return resultStatus;
    }

    public String getResult() {
        return result;
    }

    public static final Parcelable.Creator<PayResult> CREATOR = new Parcelable.Creator<PayResult>() {
        public PayResult createFromParcel(Parcel in) {
            return new PayResult(in);
        }

        public PayResult[] newArray(int size) {
            return new PayResult[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(resultStatus);
        dest.writeString(result);
    }
}
