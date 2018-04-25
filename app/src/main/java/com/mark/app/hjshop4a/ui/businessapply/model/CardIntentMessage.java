package com.mark.app.hjshop4a.ui.businessapply.model;

import com.mark.app.hjshop4a.base.model.BaseModel;

import java.util.Map;

/**
 * Created by pc on 2018/4/25.
 */

public class CardIntentMessage extends BaseModel {
    private Map<Integer,String> map;
    private String cardId;

    public Map<Integer, String> getMap() {
        return map;
    }

    public void setMap(Map<Integer, String> map) {
        this.map = map;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }
}
