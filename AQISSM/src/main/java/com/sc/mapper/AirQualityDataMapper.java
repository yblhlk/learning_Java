package com.sc.mapper;

import com.sc.pojo.AirQualityData;

import java.util.Date;
import java.util.List;

public interface AirQualityDataMapper {
    public List<AirQualityData> show();
    public int add(AirQualityData p);
    public int delete(String date);
}
