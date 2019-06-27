package com.mark.app.hjshop4a.uinew.performorder.model;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.base.model.BaseModel;

import java.util.ArrayList;
import java.util.List;

public class PayInfo extends BaseModel {
    String accountName;//下单账号
    String shopName;//店铺名称
    double mainProductPrice;//主商品价格
    int mainProductCount;//主商品下单数量
    int isAddProductFlage;//是否有附加商品
    List<AddProductPrice> addProductPrices;//附加商品价格
    double totalPrice;//总价
    String payPic;//支付图片
    String tbOrderSn;//买手填写淘宝订单号

    double payment;//买手填写支付价格
    long compltetTime;//完成时间
    public void countCompleteTime() {
        if(compltetTime>0)
            compltetTime=compltetTime-1000;

    }

    public List<PayInfoWithName> getData(){
        List<PayInfoWithName > payInfoWithNames =new ArrayList<>();
        PayInfoWithName data1 =new PayInfoWithName();
        data1.setName("下单账号");
        data1.setPayinfo(getAccountName());
        payInfoWithNames.add(data1);
        PayInfoWithName data2 =new PayInfoWithName();
        data2.setName("店铺名称");
        data2.setPayinfo(getShopName());
        payInfoWithNames.add(data2);
        PayInfoWithName data3 =new PayInfoWithName();
        data3.setName("主宝贝价格：");
        data3.setPayinfo(String.format(App.get().getString(R.string.S元),String.valueOf(getMainProductPrice())));
        payInfoWithNames.add(data3);
        PayInfoWithName data4 =new PayInfoWithName();
        data4.setName("拍下件数：");
        data4.setPayinfo(String.valueOf(getMainProductCount()));
        payInfoWithNames.add(data4);
        if(getIsAddProductFlage()==1) {
            for (int i = 0; i < getAddProductPrices().size(); i++) {
                PayInfoWithName data = new PayInfoWithName();
                data.setName("副宝贝价格：");
                data.setPayinfo(String.format(App.get().getString(R.string.S元), String.valueOf(getAddProductPrices().get(i).getProductPrice())));
                payInfoWithNames.add(data);
                PayInfoWithName payInfoWithName = new PayInfoWithName();
                payInfoWithName.setName("拍下件数：");
                payInfoWithName.setPayinfo(String.valueOf(getAddProductPrices().get(i).getProductCount()));
                payInfoWithNames.add(payInfoWithName);
            }
        }
        PayInfoWithName data5 =new PayInfoWithName();
        data5.setName("实际总价：");
        data5.setPayinfo(String.format(App.get().getString(R.string.S元),String.valueOf(getTotalPrice())));
        payInfoWithNames.add(data5);

        return payInfoWithNames;
    }


    public long getCompltetTime() {
        return compltetTime;
    }

    public void setCompltetTime(long compltetTime) {
        this.compltetTime = compltetTime;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public double getMainProductPrice() {
        return mainProductPrice;
    }

    public void setMainProductPrice(double mainProductPrice) {
        this.mainProductPrice = mainProductPrice;
    }

    public int getMainProductCount() {
        return mainProductCount;
    }

    public void setMainProductCount(int mainProductCount) {
        this.mainProductCount = mainProductCount;
    }

    public int getIsAddProductFlage() {
        return isAddProductFlage;
    }

    public void setIsAddProductFlage(int isAddProductFlage) {
        this.isAddProductFlage = isAddProductFlage;
    }

    public List<AddProductPrice> getAddProductPrices() {
        return addProductPrices;
    }

    public void setAddProductPrices(List<AddProductPrice> addProductPrices) {
        this.addProductPrices = addProductPrices;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getPayPic() {
        return payPic;
    }

    public void setPayPic(String payPic) {
        this.payPic = payPic;
    }

    public String getTbOrderSn() {
        return tbOrderSn;
    }

    public void setTbOrderSn(String tbOrderSn) {
        this.tbOrderSn = tbOrderSn;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }
}
 class  AddProductPrice{
    double productPrice;//附加商品价格
    int productCount;//附加商品数量

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }
}