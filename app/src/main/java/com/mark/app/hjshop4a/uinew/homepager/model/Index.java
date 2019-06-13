package com.mark.app.hjshop4a.uinew.homepager.model;

import com.mark.app.hjshop4a.base.model.BaseModel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Index extends BaseModel {
    List<Banner>  banners ;//banner列表
    List<ShowProduct> products;//展示图片列表
    List<IndexModel> listData =new ArrayList<>();

    public List<Banner> getBanners() {
        return banners;
    }

    public void setBanners(List<Banner> banners) {
        this.banners = banners;
    }

    public List<ShowProduct> getProducts() {
        return products;
    }

    public void setProducts(List<ShowProduct> products) {
        this.products = products;
    }

    public List<IndexModel> getListData() {
        return listData;
    }

  private void getlsit(){
      IndexModel model=new IndexModel();
        if(products!=null){
            for(int i =0;i<products.size();i++){
                if(model.isNull()){
                    model.setData(products.get(i));
                }else {
                    listData.add(model);
                    model=new IndexModel();
                }

//                    model.setProduct1();
            }
        }

  }
}
