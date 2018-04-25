package com.mark.app.hjshop4a.common.utils;

/**
 * Created by pc on 2018/4/25.
 */

public class BillUtil {
    /*会员金豆消费类型*/
    public static String swicthcomsumerType(int comsumerType) {
        switch (comsumerType){
            case 1:return "线下消费";
            case 2:return "线上购物";
            default:return "";
        }
    }
    /*审核状态*/
    public static String swichAuditStatus(int offlineOrderAuditStatus) {
        switch (offlineOrderAuditStatus){
            case 0:return  "待审核";
            case 1:return  "审核中";
            case 2:return  "审核通过";
            case 3:return  "审核不通过";
            default:return "";
        }
    }
}
