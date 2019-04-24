package com.mark.app.hjshop4a.common.permisstion.deflistener;

public interface PermissionCheckCallBack {
    void onHasPermission();

    void onUserHasAlreadyTurnedDown(String... var1);

    void onUserHasAlreadyTurnedDownAndDontAsk(String... var1);
}
