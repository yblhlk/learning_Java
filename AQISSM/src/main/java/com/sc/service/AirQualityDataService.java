package com.sc.service;

import com.sc.pojo.AirQualityData;

import java.util.Date;
import java.util.List;

public interface AirQualityDataService {
    //查询AQI数据业务功能
    public List<AirQualityData> show();
    //新增AQI数据业务功能
    public int add(AirQualityData p);
    //删除AQI数据业务功能
    public int delete(String date);
}
