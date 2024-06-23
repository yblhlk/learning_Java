package com.sc.pojo;

//实体类：是为了对应数据库表
//类名 --> 表名
//属性名 --> 字段名（类型必须要一样 名称可以相同 可以不同）
public class AirQualityData {
    private String date;
    private String lv;
    private long AQI;
    private long sort;
    private long PM25;
    private long PM10;
    private long so2;
    private long no2;
    private double co;
    private long o3;

    public AirQualityData(String date, String lv, long AQI, long sort, long PM25, long PM10, long so2, long no2, double co, long o3) {
        this.date = date;
        this.lv = lv;
        this.AQI = AQI;
        this.sort = sort;
        this.PM25 = PM25;
        this.PM10 = PM10;
        this.so2 = so2;
        this.no2 = no2;
        this.co = co;
        this.o3 = o3;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLv() {
        return lv;
    }

    public void setLv(String lv) {
        this.lv = lv;
    }

    public long getAQI() {
        return AQI;
    }

    public void setAQI(long AQI) {
        this.AQI = AQI;
    }

    public long getSort() {
        return sort;
    }

    public void setSort(long sort) {
        this.sort = sort;
    }

    public long getPM25() {
        return PM25;
    }

    public void setPM25(long PM25) {
        this.PM25 = PM25;
    }

    public long getPM10() {
        return PM10;
    }

    public void setPM10(long PM10) {
        this.PM10 = PM10;
    }

    public long getSo2() {
        return so2;
    }

    public void setSo2(long so2) {
        this.so2 = so2;
    }

    public long getNo2() {
        return no2;
    }

    public void setNo2(long no2) {
        this.no2 = no2;
    }

    public double getCo() {
        return co;
    }

    public void setCo(double co) {
        this.co = co;
    }

    public long getO3() {
        return o3;
    }

    public void setO3(long o3) {
        this.o3 = o3;
    }
}
