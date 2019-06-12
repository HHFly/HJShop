package com.mark.app.hjshop4a.uinew.bindinfo.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.uinew.bindinfo.model.ShopType;

import java.util.ArrayList;
import java.util.List;

public class ShopTypePickerView  extends Dialog{
    private boolean isCreate;
    private Button ivBtn,btnFinsh;
    private ShopTypeAdapter shopTypeAdapter;
    private RecyclerView recyclerView;
    Context context;

    public ShopTypePickerView(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.context =context;
    }


    public String getUpData() {
        String updata="";
        for(int i=0;i<shopTypeAdapter.getMapData().size();i++){
            updata  = updata+ shopTypeAdapter.getMapData().get(i).getType()+"/";
        }
        return  updata;
    }
    public String getTvData() {
        String updata="";
        for(int i=0;i<shopTypeAdapter.getMapData().size();i++){
            updata  = updata+ shopTypeAdapter.getMapData().get(i).getName()+"/";
        }
        return  updata;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_shoptype);
        Window window = this.getWindow();

        isCreate = true;

        /**
         * 位于底部
         */
        window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(params);
        /**
         * 设置弹出动画
         */
        window.setWindowAnimations(R.style.mystyle);
        ivBtn = findViewById(R.id.btn_cancel);
        ivBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        btnFinsh=findViewById(R.id.btn_finish);
        btnFinsh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onItemClickListener!=null){
                    onItemClickListener.onClick(getTvData());
                }
                dismiss();
            }
        });
        recyclerView = findViewById(R.id.recyclerView);
        shopTypeAdapter=new ShopTypeAdapter(getData());
        shopTypeAdapter.setOnItemClickListener(new ShopTypeAdapter.OnItemClickListener() {
            @Override
            public void onClick(boolean isChecked, ShopType data) {

            }
        });
        recyclerView.setAdapter(shopTypeAdapter);
        LinearLayoutManager provinceManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(provinceManager);
    }
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
    }

    public interface OnItemClickListener {
        /**
         *
         *
         * @param data
         */
        void onClick(String data);

    }
    private List<ShopType> getData(){
        List<ShopType> data =new ArrayList<>();
        ShopType a1 = new ShopType();
        a1.setName("服装鞋包");
        a1.setType("A");
        data.add(a1);
        ShopType a2 = new ShopType();
        a2.setName("手机数码");
        a2.setType("B");
        data.add(a2);
        ShopType a3 = new ShopType();
        a3.setName("家用电器");
        a3.setType("C");
        data.add(a3);
        ShopType a4 = new ShopType();
        a4.setName("美妆饰品");
        a4.setType("D");
        data.add(a4);
        ShopType a5 = new ShopType();
        a5.setName("母婴用品");
        a5.setType("E");
        data.add(a5);
        ShopType a6 = new ShopType();
        a6.setName("家居建材");
        a6.setType("F");
        data.add(a6);
        ShopType a7 = new ShopType();
        a7.setName("百货食品");
        a7.setType("G");
        data.add(a7);
        ShopType a8 = new ShopType();
        a8.setName("运动户外");
        a8.setType("H");
        data.add(a8);
        ShopType a9 = new ShopType();
        a9.setName("文化娱乐");
        a9.setType("I");
        data.add(a9);
        ShopType a10 = new ShopType();
        a10.setName("生活服务");
        a10.setType("J");
        data.add(a10);
        ShopType a11 = new ShopType();
        a11.setName("汽摩配件");
        a11.setType("K");
        data.add(a11);
        return  data;
    }
}
