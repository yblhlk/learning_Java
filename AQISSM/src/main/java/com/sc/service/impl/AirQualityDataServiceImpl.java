package com.sc.service.impl;

import com.sc.mapper.AirQualityDataMapper;
import com.sc.pojo.AirQualityData;
import com.sc.service.AirQualityDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AirQualityDataServiceImpl implements AirQualityDataService{
    @Autowired
    AirQualityDataMapper mapper;
    public List<AirQualityData> show() {
        //做什么?  调用mapper实现业务功能
        return mapper.show();
    }
    public int add(AirQualityData p) {
        return mapper.add(p);
    }
    public int delete(String date) {
        return mapper.delete(date);
    }
}
