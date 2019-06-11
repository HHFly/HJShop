package com.mark.app.hjshop4a.ui.dialog.wheelviewlibrary.listener;

import com.mark.app.hjshop4a.ui.dialog.model.AddressData;

public interface SelectInterface {

    /**
     * 用于传递所选的控件结果
     *
     * @param result
     */
    void selectedResult(AddressData result1 ,AddressData result2 ,AddressData result3 );
    void selectedResult(AddressData result1 ,AddressData result2 );
    void selectedResult(String result);
}
