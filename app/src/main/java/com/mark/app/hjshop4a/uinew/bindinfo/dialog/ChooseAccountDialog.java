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

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.app.App;
import com.mark.app.hjshop4a.common.utils.JsonUtils;
import com.mark.app.hjshop4a.data.entity.RainbowResultEntity;
import com.mark.app.hjshop4a.data.help.RainbowObserver;
import com.mark.app.hjshop4a.ui.homepager.model.UserCenter;
import com.mark.app.hjshop4a.uinew.bindinfo.dialog.adapter.ChooseAdapter;
import com.mark.app.hjshop4a.uinew.bindinfo.dialog.adapter.ShopTypeAdapter;
import com.mark.app.hjshop4a.uinew.bindinfo.model.AccountInfoPass;
import com.mark.app.hjshop4a.uinew.bindinfo.model.ShopType;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ChooseAccountDialog extends Dialog {
    private boolean isCreate;
    Context context;
    private ChooseAdapter chooseAdapter;
    private Button ivBtn;
    private RecyclerView recyclerView;
    List<AccountInfoPass> mData;
    AccountInfoPass chooseData =new AccountInfoPass();
    public ChooseAccountDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.context =context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_choose_account);
        Window window = this.getWindow();

        isCreate = true;

        /**
         * 位于底部
         */
        window.setGravity(Gravity.CENTER);
        WindowManager.LayoutParams params = window.getAttributes();

        window.setAttributes(params);
        /**
         * 设置弹出动画
         */
        window.setWindowAnimations(R.style.mystyle);
        ivBtn = findViewById(R.id.btn);
        ivBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onItemClickListener!=null){
                    onItemClickListener.onClick(chooseData);
                }
                dismiss();
            }
        });
        requestData();
    }

    @Override
    public void show() {

        super.show();
        requestData();
    }

    /**
     * 请求数据
     */
    private void requestData() {
        App.getServiceManager().getmService()
                .getSuccessAcccounts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RainbowObserver<List<AccountInfoPass>>() {



                    @Override
                    public void onSuccess(RainbowResultEntity<List<AccountInfoPass>> obj) {

                            mData = JsonUtils.getList(obj.getResult(), AccountInfoPass.class);
                        if(mData!=null&&mData.size()!=0) {
                            mData.get(0).setSelect(true);
                            chooseData = mData.get(0);
                            initAdapter(mData, true);
                        }
                    }

                    @Override
                    public void onAllFinish() {
                        super.onAllFinish();

                    }
                });
    }
    private void initAdapter(List<AccountInfoPass> data,Boolean isFresh){
        if(chooseAdapter==null){
            recyclerView = findViewById(R.id.recyclerView);
            chooseAdapter=new ChooseAdapter(data);
            chooseAdapter.setOnItemClickListener(new ChooseAdapter.OnItemClickListener() {
                @Override
                public void onClick(int pos, AccountInfoPass data1) {
                    setSelect(pos);
                    initAdapter(mData,true);
                }




            });
            recyclerView.setAdapter(chooseAdapter);
            LinearLayoutManager provinceManager = new LinearLayoutManager(context);
            recyclerView.setLayoutManager(provinceManager);
        }else {
            chooseAdapter.notifyData(data,isFresh);
        }
    }
    private void setSelect(int pos){
        for (int i=0;i<mData.size();i++){
            if(i==pos){
                mData.get(i).setSelect(true);
            }else {
                mData.get(i).setSelect(false);
            }

        }
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
        void onClick(AccountInfoPass data);

    }
}
