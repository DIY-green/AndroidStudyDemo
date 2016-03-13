package com.cheng.networkframestudy.retrofit.model;

/**
 * Created by Android on 2016/3/13.
 */
public class IPDetail {

    /**
     * code : 0
     * data : {"ip":"210.75.225.254","country":"中国","area":"华北","region":"北京市","city":"北京市","county":"","isp":"电信","country_id":"86","area_id":"100000","region_id":"110000","city_id":"110000","county_id":"-1","isp_id":"100017"}
     */

    private int code;
    /**
     * ip : 210.75.225.254
     * country : 中国
     * area : 华北
     * region : 北京市
     * city : 北京市
     * county :
     * isp : 电信
     * country_id : 86
     * area_id : 100000
     * region_id : 110000
     * city_id : 110000
     * county_id : -1
     * isp_id : 100017
     */

    private DataEntity data;

    public void setCode(int code) {
        this.code = code;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public DataEntity getData() {
        return data;
    }

    public static class DataEntity {
        private String ip;
        private String country;
        private String area;
        private String region;
        private String city;
        private String county;
        private String isp;
        private String country_id;
        private String area_id;
        private String region_id;
        private String city_id;
        private String county_id;
        private String isp_id;

        public void setIp(String ip) {
            this.ip = ip;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public void setCounty(String county) {
            this.county = county;
        }

        public void setIsp(String isp) {
            this.isp = isp;
        }

        public void setCountry_id(String country_id) {
            this.country_id = country_id;
        }

        public void setArea_id(String area_id) {
            this.area_id = area_id;
        }

        public void setRegion_id(String region_id) {
            this.region_id = region_id;
        }

        public void setCity_id(String city_id) {
            this.city_id = city_id;
        }

        public void setCounty_id(String county_id) {
            this.county_id = county_id;
        }

        public void setIsp_id(String isp_id) {
            this.isp_id = isp_id;
        }

        public String getIp() {
            return ip;
        }

        public String getCountry() {
            return country;
        }

        public String getArea() {
            return area;
        }

        public String getRegion() {
            return region;
        }

        public String getCity() {
            return city;
        }

        public String getCounty() {
            return county;
        }

        public String getIsp() {
            return isp;
        }

        public String getCountry_id() {
            return country_id;
        }

        public String getArea_id() {
            return area_id;
        }

        public String getRegion_id() {
            return region_id;
        }

        public String getCity_id() {
            return city_id;
        }

        public String getCounty_id() {
            return county_id;
        }

        public String getIsp_id() {
            return isp_id;
        }

        @Override
        public String toString() {
            return "DataEntity{" +
                    "ip='" + ip + '\'' +
                    ", country='" + country + '\'' +
                    ", area='" + area + '\'' +
                    ", region='" + region + '\'' +
                    ", city='" + city + '\'' +
                    ", county='" + county + '\'' +
                    ", isp='" + isp + '\'' +
                    ", country_id='" + country_id + '\'' +
                    ", area_id='" + area_id + '\'' +
                    ", region_id='" + region_id + '\'' +
                    ", city_id='" + city_id + '\'' +
                    ", county_id='" + county_id + '\'' +
                    ", isp_id='" + isp_id + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "IPDetail{" +
                "code=" + code +
                ", data=" + data +
                '}';
    }
}
