package com.mark.app.hjshop4a.ui.dialog.factory;


import android.app.Activity;
import android.content.res.AssetManager;

import com.mark.app.hjshop4a.common.XmlParserHandler;
import com.mark.app.hjshop4a.login.activity.WheelDialog;
import com.mark.app.hjshop4a.model.common.CityModel;
import com.mark.app.hjshop4a.model.common.DistrictModel;
import com.mark.app.hjshop4a.model.common.ProvinceModel;
import com.mark.app.hjshop4a.widget.PickerScrollView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * 滑动选择工厂
 * Created by lenovo on 2017/9/7.
 */

public class WheelDialogFactory {

    /**
     * 所有省
     */
    static String[] mProvinceDatas;
    /**
     * key - 省 value - 市
     */
    static Map<String, String[]> mCitisDatasMap = new HashMap<String, String[]>();
    /**
     * key - 市 values - 区
     */
    static Map<String, String[]> mDistrictDatasMap = new HashMap<String, String[]>();

    /**
     * key - 区 values - 邮编
     */
    static Map<String, String> mZipcodeDatasMap = new HashMap<String, String>();

    /**
     * 当前省的名称
     */
    static String mCurrentProviceName;
    /**
     * 当前市的名称
     */
    static String mCurrentCityName;
    /**
     * 当前区的名称
     */
    static String mCurrentDistrictName ="";

    /**
     * 当前区的邮政编码
     */
    static String mCurrentZipCode ="";

    /**
     * 解析省市区的XML数据
     */
    public static void initProvinceDatas(InputStream input)
    {
        List<ProvinceModel> provinceList = null;

        try {

            // 创建一个解析xml的工厂对象
            SAXParserFactory spf = SAXParserFactory.newInstance();
            // 解析xml
            SAXParser parser = spf.newSAXParser();
            XmlParserHandler handler = new XmlParserHandler();
            parser.parse(input, handler);
            input.close();
            // 获取解析出来的数据
            provinceList = handler.getDataList();
            //*/ 初始化默认选中的省、市、区
            if (provinceList!= null && !provinceList.isEmpty()) {
                mCurrentProviceName = provinceList.get(0).getName();
                List<CityModel> cityList = provinceList.get(0).getCityList();
                if (cityList!= null && !cityList.isEmpty()) {
                    mCurrentCityName = cityList.get(0).getName();
                    List<DistrictModel> districtList = cityList.get(0).getDistrictList();
                    mCurrentDistrictName = districtList.get(0).getName();
                    mCurrentZipCode = districtList.get(0).getZipcode();
                }
            }
            //*/
            mProvinceDatas = new String[provinceList.size()];
            for (int i=0; i< provinceList.size(); i++) {
                // 遍历所有省的数据
                mProvinceDatas[i] = provinceList.get(i).getName();
                List<CityModel> cityList = provinceList.get(i).getCityList();
                String[] cityNames = new String[cityList.size()];
                for (int j=0; j< cityList.size(); j++) {
                    // 遍历省下面的所有市的数据
                    cityNames[j] = cityList.get(j).getName();
                    List<DistrictModel> districtList = cityList.get(j).getDistrictList();
                    String[] distrinctNameArray = new String[districtList.size()];
                    DistrictModel[] distrinctArray = new DistrictModel[districtList.size()];
                    for (int k=0; k<districtList.size(); k++) {
                        // 遍历市下面所有区/县的数据
                        DistrictModel districtModel = new DistrictModel(districtList.get(k).getName(), districtList.get(k).getZipcode());
                        // 区/县对于的邮编，保存到mZipcodeDatasMap
                        mZipcodeDatasMap.put(districtList.get(k).getName(), districtList.get(k).getZipcode());
                        distrinctArray[k] = districtModel;
                        distrinctNameArray[k] = districtModel.getName();
                    }
                    // 市-区/县的数据，保存到mDistrictDatasMap
                    mDistrictDatasMap.put(cityNames[j], distrinctNameArray);
                }
                // 省-市的数据，保存到mCitisDatasMap
                mCitisDatasMap.put(provinceList.get(i).getName(), cityNames);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {

        }
    }

    /**
     * 获取时间选择
     *
     * @return
     */
    public static WheelDialog getProvinceWheelDialog(InputStream input) {
        initProvinceDatas(input);
        List<PickerScrollView.ItemModel> data = new ArrayList<>();


        for (int i =0 ;i<mProvinceDatas.length;i++){
            PickerScrollView.ItemModel item = new PickerScrollView.ItemModel();
            item.setId(i);
            item.setName(mProvinceDatas[i]);
            data.add(item);
        }


        WheelDialog dialog = WheelDialog.getInstance(data);
        return dialog;
    }
}
