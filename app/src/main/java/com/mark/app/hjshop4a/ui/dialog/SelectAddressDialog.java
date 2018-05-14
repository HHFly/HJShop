package com.mark.app.hjshop4a.ui.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.AssetManager;
import android.location.Address;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.mark.app.hjshop4a.R;
import com.mark.app.hjshop4a.common.utils.JsonUtils;
import com.mark.app.hjshop4a.common.utils.LogUtils;
import com.mark.app.hjshop4a.ui.dialog.model.AddressData;
import com.mark.app.hjshop4a.ui.dialog.model.WheelData;
import com.mark.app.hjshop4a.ui.dialog.wheelviewlibrary.WheelView;
import com.mark.app.hjshop4a.ui.dialog.wheelviewlibrary.adapter.ArrayWheelAdapter;
import com.mark.app.hjshop4a.ui.dialog.wheelviewlibrary.listener.OnWheelChangedListener;
import com.mark.app.hjshop4a.ui.dialog.wheelviewlibrary.listener.SelectInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class SelectAddressDialog implements OnClickListener,
        OnWheelChangedListener {
    private boolean isMyDatas;//是否自定义数据


    public static final int STYLE_ONE = 1;//一级联动
    public static final int STYLE_TWO = 2;//二级联动
    public static final int STYLE_THREE = 3;//三级联动

    /**
     * 所有省
     */
    protected AddressData[] mProvinceDatas;
    /**
     * key - 省 value - 市
     */
    protected Map<String, AddressData[]> mCitisDatasMap = new HashMap<String, AddressData[]>();
    /**
     * key - 省 value - 市
     */
    protected Map<String,   ArrayList<Map>> mCitisDatasKeyMap = new HashMap<String,   ArrayList<Map>>();
    /**
     * key - 市 values - 区
     */
    protected Map<String, AddressData[]> mDistrictDatasMap = new HashMap<String, AddressData[]>();
    /**
     * key - 市 values - 区
     */
    protected Map<Map, ArrayList<Map>> mDistrictDatasKeyMap = new HashMap<Map, ArrayList<Map>>();
    /**
     * key - 区 values - 邮编
     */
    protected Map<String, String> mZipcodeDatasMap = new HashMap<String, String>();


    /**
     * 当前区的postion
     */
    protected int mCurrentDistrictNamePosition;
    /**
     * 当前省的postion
     */
    protected int mCurrentProviceNamePosition;
    /**
     * 当前市的postion
     */
    protected int mCurrentCityNamePosition;
    /**
     * 当前省的名称
     */
    protected AddressData mCurrentProviceName;
    /**
     * 当前市的名称
     */
    protected AddressData mCurrentCityName;
    /**
     * 当前区的名称
     */
    protected AddressData mCurrentDistrictName ;
    /**
     * 当前区的邮政编码
     */
    protected String mCurrentZipCode = "";
    /**
     * 当前市的Map名称
     */
    protected Map  mCurrentCityNameMap;


    private WheelView mViewProvince;
    private WheelView mViewCity;
    private WheelView mViewDistrict;
    private Button mBtnConfirm, mBtnCancel;
    private Activity context;
    private Dialog overdialog;
    private SelectInterface selectAdd;
    private int tmp1, tmp2, tmp3;
    private int type;
    private long mCurrentProviceNameID,mCurrentCityNameID,mCurrentDistrictNameID;

    public SelectAddressDialog(final Activity context,
                               SelectInterface selectAdd, int type, AddressData[] mProvinceDatas) {
        this.selectAdd = selectAdd;
        this.type = type;
        this.context = context;
        View overdiaView = View.inflate(context,
                R.layout.dialog_select_address, null);

        mViewProvince = (WheelView) overdiaView.findViewById(R.id.id_province);
        mViewCity = (WheelView) overdiaView.findViewById(R.id.id_city);
        mViewDistrict = (WheelView) overdiaView.findViewById(R.id.id_district);
        if (STYLE_TWO == type) {
            mViewDistrict.setVisibility(View.GONE);
        }
        if (STYLE_ONE == type) {
            mViewDistrict.setVisibility(View.GONE);
            mViewCity.setVisibility(View.GONE);
        }
        mBtnConfirm = (Button) overdiaView.findViewById(R.id.btn_confirm);
        mBtnCancel = (Button) overdiaView.findViewById(R.id.btn_cancel);
        overdialog = new Dialog(context, R.style.dialog_lhp);
        Window window = overdialog.getWindow();
        window.setWindowAnimations(R.style.mystyle); // 添加动画
        overdialog.setContentView(overdiaView);
        overdialog.setCanceledOnTouchOutside(true);
        //设置滚轮滑动监听
        setUpListener();
        if (mProvinceDatas == null) {
            setUpData();
            isMyDatas = false;
        } else {
            isMyDatas = true;
            this.mProvinceDatas = mProvinceDatas;
            mCurrentProviceName = mProvinceDatas[0];
            mViewProvince.setViewAdapter(new ArrayWheelAdapter<>(context,
                    this.mProvinceDatas));
            // 设置可见条目数量
            mViewProvince.setVisibleItems(7);
            mViewCity.setVisibleItems(7);
            mViewDistrict.setVisibleItems(7);


        }

    }

    public SelectAddressDialog(final Activity context,
                               SelectInterface selectAdd, int type, long provice , long city , long county ) {
        this.selectAdd = selectAdd;
        this.type = type;
        this.context = context;
        this.mCurrentProviceNameID=provice;
        this.mCurrentCityNameID= city;
        this.mCurrentDistrictNameID=county;
        View overdiaView = View.inflate(context,
                R.layout.dialog_select_address, null);

        mViewProvince = (WheelView) overdiaView.findViewById(R.id.id_province);
        mViewCity = (WheelView) overdiaView.findViewById(R.id.id_city);
        mViewDistrict = (WheelView) overdiaView.findViewById(R.id.id_district);
        if (STYLE_TWO == type) {
            mViewDistrict.setVisibility(View.GONE);
        }
        if (STYLE_ONE == type) {
            mViewDistrict.setVisibility(View.GONE);
            mViewCity.setVisibility(View.GONE);
        }
        mBtnConfirm = (Button) overdiaView.findViewById(R.id.btn_confirm);
        mBtnCancel = (Button) overdiaView.findViewById(R.id.btn_cancel);
        overdialog = new Dialog(context, R.style.dialog_lhp);
        Window window = overdialog.getWindow();
        window.setWindowAnimations(R.style.mystyle); // 添加动画
        overdialog.setContentView(overdiaView);
        overdialog.setCanceledOnTouchOutside(true);
        //设置滚轮滑动监听
        setUpListener();
        setUpData();
        isMyDatas = false;

    }
    public void showDialog() {

        if (overdialog != null) {
            if (mViewProvince != null) mViewProvince.setCurrentItem(mCurrentProviceNamePosition);
            if (mViewCity != null) mViewCity.setCurrentItem(mCurrentCityNamePosition);
            if (mViewDistrict != null) mViewDistrict.setCurrentItem(mCurrentDistrictNamePosition);
            overdialog.show();
            Window win = overdialog.getWindow();
            //弹出的窗口左上右下的距离
            win.getDecorView().setPadding(0, 0, 0, 0);
            WindowManager.LayoutParams lp = win.getAttributes();
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            win.setGravity(Gravity.BOTTOM);
            win.setAttributes(lp);
        }
    }

    /**
     * 各个滚轮的滚动监听
     *
     * @param wheel    the wheel view whose state has changed
     * @param oldValue the old value of current item
     * @param newValue the new value of current item
     */
    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {
        if (wheel == mViewProvince) {
            updateCities();
        } else if (wheel == mViewCity) {
            updateAreas();
        } else if (wheel == mViewDistrict) {
            mCurrentDistrictName = mDistrictDatasMap.get(mCurrentCityName.getId())[newValue];
            tmp3 = newValue;
            mCurrentZipCode = mZipcodeDatasMap.get(mCurrentDistrictName);
        }
    }

    /**
     * 根据当前的市，更新区WheelView的信息
     */
    private void updateAreas() {

        int pCurrent = mViewCity.getCurrentItem();
        tmp2 = pCurrent;
        mCurrentCityName = mCitisDatasMap.get(mCurrentProviceName.getId())[pCurrent];

//        mCurrentCityNameMap =mCitisDatasKeyMap.get(mCurrentProviceName);
        AddressData[] areas = mDistrictDatasMap.get(mCurrentCityName.getId());

        if (areas == null) {
            areas = new AddressData[]{};
        }
        mCurrentDistrictName = areas[0];
        mViewDistrict.setViewAdapter(new ArrayWheelAdapter<AddressData>(context,
                areas));
        mViewDistrict.setCurrentItem(0);
    }

    /**
     * 根据当前的省，更新市WheelView的信息
     */
    private void updateCities() {
        //获取当前省控件中选中的条目的index
        int pCurrent = mViewProvince.getCurrentItem();
        tmp1 = pCurrent;
        mCurrentProviceName = mProvinceDatas[pCurrent];
        if (!isMyDatas) {//不是自定义数据
            AddressData[] cities = mCitisDatasMap.get(mCurrentProviceName.getId());
            if (cities == null) {
                cities = new AddressData[]{};
            }
            mViewCity
                    .setViewAdapter(new ArrayWheelAdapter<AddressData>(context, cities));
            mViewCity.setCurrentItem(0);
            updateAreas();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_confirm:
                if (type == STYLE_TWO) {
                    selectAdd.selectedResult(mCurrentProviceName + "-" + mCurrentCityName);
                } else if (type == STYLE_ONE) {
                    selectAdd.selectedResult(mCurrentProviceName,mCurrentCityName,mCurrentDistrictName);
                } else {
                    selectAdd.selectedResult(mCurrentProviceName,mCurrentCityName,mCurrentDistrictName);
                }
                mCurrentProviceNamePosition = tmp1;
                mCurrentCityNamePosition = tmp2;
                mCurrentDistrictNamePosition = tmp3;

                overdialog.cancel();
                break;

            case R.id.btn_cancel:
                overdialog.cancel();
                break;
            default:
                break;
        }
    }

    /**
     * 设置向上的数据
     */
    private void setUpData() {
//        initProvinceDatas();
        initProvinceDatasTxt();
        mViewProvince.setViewAdapter(new ArrayWheelAdapter<AddressData>(context,
                mProvinceDatas));
        // 设置可见条目数量
        mViewProvince.setVisibleItems(7);
        mViewCity.setVisibleItems(7);
        mViewDistrict.setVisibleItems(7);
        updateCities();
        updateAreas();
    }

    /**
     * 添加滑动的监听事件
     */
    private void setUpListener() {
        // 添加change事件
        mViewProvince.addChangingListener(this);
        // 添加change事件
        mViewCity.addChangingListener(this);
        // 添加change事件
        mViewDistrict.addChangingListener(this);
        // 添加onclick事件
        mBtnConfirm.setOnClickListener(this);
        // 添加onclick事件
        mBtnCancel.setOnClickListener(this);
    }

    /**
     * 解析省市区的XML数据
     */

//    protected void initProvinceDatas() {
//        List<ProvinceModel> provinceList = null;
//        AssetManager asset = context.getAssets();
//        try {
//            InputStream input = asset.open("province_data.xml");
//            // 创建一个解析xml的工厂对象
//            SAXParserFactory spf = SAXParserFactory.newInstance();
//            // 解析xml
//            SAXParser parser = spf.newSAXParser();
//            XmlParserHandler handler = new XmlParserHandler();
//            parser.parse(input, handler);
//            input.close();
//            // 获取解析出来的数据
//            provinceList = handler.getDataList();
//            //初始化默认选中的省、市、区
//            if (provinceList != null && !provinceList.isEmpty()) {
//                mCurrentProviceName = provinceList.get(0).getName();
//                List<CityModel> cityList = provinceList.get(0).getCityList();
//                if (cityList != null && !cityList.isEmpty()) {
//                    mCurrentCityName = cityList.get(0).getName();
//                    List<DistrictModel> districtList = cityList.get(0)
//                            .getDistrictList();
//                    mCurrentDistrictName = districtList.get(0).getName();
//                    mCurrentZipCode = districtList.get(0).getZipcode();
//                }
//            }
//
//            mProvinceDatas = new String[provinceList.size()];
//            for (int i = 0; i < provinceList.size(); i++) {
//                // 遍历所有省的数据
//                mProvinceDatas[i] = provinceList.get(i).getName();
//                List<CityModel> cityList = provinceList.get(i).getCityList();
//                String[] cityNames = new String[cityList.size()];
//                for (int j = 0; j < cityList.size(); j++) {
//                    // 遍历省下面的所有市的数据
//                    cityNames[j] = cityList.get(j).getName();
//                    List<DistrictModel> districtList = cityList.get(j)
//                            .getDistrictList();
//                    String[] distrinctNameArray = new String[districtList
//                            .size()];
//                    DistrictModel[] distrinctArray = new DistrictModel[districtList
//                            .size()];
//                    for (int k = 0; k < districtList.size(); k++) {
//                        // 遍历市下面所有区/县的数据
//                        DistrictModel districtModel = new DistrictModel(
//                                districtList.get(k).getName(), districtList
//                                .get(k).getZipcode());
//                        // 区/县对于的邮编，保存到mZipcodeDatasMap
//                        mZipcodeDatasMap.put(districtList.get(k).getName(),
//                                districtList.get(k).getZipcode());
//                        distrinctArray[k] = districtModel;
//                        distrinctNameArray[k] = districtModel.getName();
//                    }
//                    // 市-区/县的数据，保存到mDistrictDatasMap
//                    mDistrictDatasMap.put(cityNames[j], distrinctNameArray);
//                }
//                // 省-市的数据，保存到mCitisDatasMap
//                mCitisDatasMap.put(provinceList.get(i).getName(), cityNames);
//            }
//        } catch (Throwable e) {
//            e.printStackTrace();
//        } finally {
//
//        }
//    }
    /**
     * 解析省市区的txt数据
     */

    protected void initProvinceDatasTxt() {
       WheelData wheelData = null;
        AssetManager asset = context.getAssets();
        try {
            String jsondat =getJson(context,"addr.txt");

            wheelData = JsonUtils.fromJson(jsondat,WheelData.class);
            ArrayList<Map> provinceList =  wheelData.getProvince();
            Map<String,ArrayList<Map>> cityList= wheelData.getCity();
            Map<String,ArrayList<Map>> districtList= wheelData.getDistrict();
            mProvinceDatas = new AddressData[provinceList.size()];
            for(int i=0;i<provinceList.size();i++) {
                AddressData addressDataprovince = new AddressData();
                addressDataprovince.setId(provinceList.get(i).get("id").toString());
                addressDataprovince.setName(provinceList.get(i).get("name").toString());
                mProvinceDatas[i] =addressDataprovince;
               String ProvinceId =provinceList.get(i).get("id").toString();
               if(ProvinceId .equals(String.valueOf(mCurrentProviceNameID))){
                   mCurrentProviceNamePosition=i;  //记录初始化postion
                   LogUtils.log(String.valueOf(tmp1));
               }
                ArrayList<Map> cityData=  cityList.get(ProvinceId);
               AddressData[] cityNames =new AddressData[cityData.size()];
               for (int j=0;j<cityData.size();j++){
                   AddressData addressDatacity =new AddressData();
                   addressDatacity.setId(cityData.get(j).get("id").toString());
                   addressDatacity.setName(cityData.get(j).get("name").toString());
                   cityNames[j]=addressDatacity;
                   String CityId =cityData.get(j).get("id").toString();
                   if(CityId .equals(String.valueOf(mCurrentCityNameID))){
                       mCurrentCityNamePosition=j;  //记录初始化postion
                       LogUtils.log(String.valueOf(tmp2));
                   }
                   ArrayList<Map> districtData=  districtList.get(CityId);
                   AddressData[] districtNames =new AddressData[districtData.size()];

                   for(int k=0;k<districtData.size();k++){
                       AddressData addressDatadistrict =new AddressData();
                       addressDatadistrict.setId(districtData.get(k).get("id").toString());
                       addressDatadistrict.setName(districtData.get(k).get("name").toString());
                       districtNames[k]=addressDatadistrict;
                       String Districtid =districtData.get(k).get("id").toString();
                       if(Districtid .equals(String.valueOf(mCurrentDistrictNameID))){
                           mCurrentDistrictNamePosition=k;  //记录初始化postion
                           LogUtils.log(String.valueOf(tmp3));
                       }
                   }
                   mDistrictDatasMap.put(cityData.get(j).get("id").toString(),districtNames);


               }
                // 省-市的数据，保存到mCitisDatasMap
//                mCitisDatasKeyMap.put(provinceList.get(i).get("name").toString(),cityData);
                mCitisDatasMap.put(provinceList.get(i).get("id").toString(), cityNames);
            }



            //初始化默认选中的省、市、区
//            if (wheelData != null ) {
//                mCurrentProviceName = wheelData.getProvince().get(0).getName();
//                cityList = wheelData.getCity();
//                if (cityList != null && !cityList.isEmpty()) {
////                    mCurrentCityName = cityList.get(0).getName();
//                    districtList = wheelData.getDistrict();
//
////                    mCurrentDistrictName = districtList.get(0).getName();
////                    mCurrentZipCode = districtList.get(0).getZipcode();
//                }
//            }

        } catch (Throwable e) {
            e.printStackTrace();
        } finally {

        }
    }
    /**
     * 从asset路径下读取对应文件转String输出
     * @param mContext
     * @return
     */
    public static String getJson(Context mContext, String fileName) {
        // TODO Auto-generated method stub
        StringBuilder sb = new StringBuilder();
        AssetManager am = mContext.getAssets();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    am.open(fileName)));
            String next = "";
            while (null != (next = br.readLine())) {
                sb.append(next);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            sb.delete(0, sb.length());
        }
        return sb.toString().trim();
    }

}
