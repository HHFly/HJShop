package com.mark.app.hjshop4a.uinew.bindinfo.data;

import com.mark.app.hjshop4a.widget.PickerScrollView;

import java.util.ArrayList;
import java.util.List;

public class BindData {

    public static List<PickerScrollView.ItemModel> getData(){
        List<PickerScrollView.ItemModel> data =new ArrayList<>();
        PickerScrollView.ItemModel a1 =new  PickerScrollView.ItemModel();
        a1.setId(1);
        a1.setName( "三星");
        data.add(a1);
        PickerScrollView.ItemModel a2 =new  PickerScrollView.ItemModel();
        a2.setId(2);
        a2.setName( "四星");
        data.add(a2);
        PickerScrollView.ItemModel a3 =new  PickerScrollView.ItemModel();
        a3.setId(3);
        a3.setName( "五星");
        data.add(a3);
        PickerScrollView.ItemModel a4 =new  PickerScrollView.ItemModel();
        a4.setId(4);
        a4.setName( "一钻");
        data.add(a4);
        PickerScrollView.ItemModel a5 =new  PickerScrollView.ItemModel();
        a5.setId(5);
        a5.setName( "二钻");
        data.add(a5);
        PickerScrollView.ItemModel a6 =new  PickerScrollView.ItemModel();
        a6.setId(6);
        a6.setName( "三钻");
        data.add(a6);
        PickerScrollView.ItemModel a7 =new  PickerScrollView.ItemModel();
        a7.setId(7);
        a7.setName( "四钻");
        data.add(a7);
        PickerScrollView.ItemModel a8 =new  PickerScrollView.ItemModel();
        a8.setId(8);
        a8.setName( "五钻");
        data.add(a8);

        return data;
    }
    public static int getLevelbyStr(String id){
        switch (id){
            case  "三星":
                return 1;
            case "四星":
                return 2;
            case "五星":
                return 3;
            case "一钻":
                return 4;
            case "二钻":
                return 5;
            case  "三钻":
                return 6;
            case "四钻":
                return 7;
            case "五钻":
                return 8;
        }
        return 1;
    }
    public static String getLevelStr(int id){
        switch (id){
            case 1:
                return "三星";
            case 2:
                return "四星";
            case 3:
                return "五星";
            case 4:
                return "一钻";
            case 5:
                return "二钻";
            case 6:
                return "三钻";
            case 7:
                return "四钻";
            case 8:
                return "五钻";
        }
        return "三星";
    }
    public static String getTypebyStr(String data) {
        switch (data){
            case "服装鞋包":
                return "A";
            case "手机数码":
                return "B";
            case "家用电器":
                return "C";
            case "美妆饰品":
                return "D";
            case "母婴用品":
                return "E";
            case "家居建材":
                return "F";
            case "百货食品":
                return "G";
            case "运动户外":
                return "H";
            case "文化娱乐":
                return "I";
            case "生活服务":
                return "J";
            case "汽摩配件":
                return "K";

        }
        return "";
    }
    public static String getStrbyType(String data){
        switch (data){
            case "A":
                return "服装鞋包";
            case "B":
                return "手机数码";
            case "C":
                return "家用电器";
            case "D":
                return "美妆饰品";
            case "E":
                return "母婴用品";
            case "F":
                return "家居建材";
            case "G":
                return "百货食品";
            case "H":
                return "运动户外";
            case "I":
                return "文化娱乐";
            case "J":
                return "生活服务";
            case "K":
                return "汽摩配件";



        }
        return "";
    }
}
