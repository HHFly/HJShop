package com.mark.app.hjshop4a.common.utils;

import java.text.DecimalFormat;

/**
 * Created by pc on 2018/3/27.
 */

public class MoneyUtils {
    public static String PennyToPound(float penny){
        String pound =null;
        try {

            DecimalFormat n = new DecimalFormat("#0.00");
          //  n.setCurrency(Currency.getInstance(Locale.UK));
            pound =n.format(penny/100);
          //  NumberFormat nf = new DecimalFormat("$,##.##");
            return pound;
        }catch (Exception e){
            return pound;
        }
    }
}
