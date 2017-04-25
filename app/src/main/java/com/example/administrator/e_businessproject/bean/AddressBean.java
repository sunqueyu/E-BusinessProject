package com.example.administrator.e_businessproject.bean;

import java.util.List;

/**
 * 类的用途：
 * Created by ${孙鹊禹}
 * on 2017/4/25 14:45
 */

public class AddressBean {

    /**
     * data : [{"areaDetail":"??7?","areaId":"??","cityId":"??","fixedtel":"??","id":1018,"name":"sqy","phoneNumber":"123456789","provinceId":"??","zipCode":"49"}]
     * response : addressDefault
     */

    private String response;
    private List<DataBean> data;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "AddressBean{" +
                "response='" + response + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBean {
        /**
         * areaDetail : ??7?
         * areaId : ??
         * cityId : ??
         * fixedtel : ??
         * id : 1018
         * name : sqy
         * phoneNumber : 123456789
         * provinceId : ??
         * zipCode : 49
         */

        private String areaDetail;
        private String areaId;
        private String cityId;
        private String fixedtel;
        private int id;
        private String name;
        private String phoneNumber;
        private String provinceId;
        private String zipCode;

        public String getAreaDetail() {
            return areaDetail;
        }

        public void setAreaDetail(String areaDetail) {
            this.areaDetail = areaDetail;
        }

        public String getAreaId() {
            return areaId;
        }

        public void setAreaId(String areaId) {
            this.areaId = areaId;
        }

        public String getCityId() {
            return cityId;
        }

        public void setCityId(String cityId) {
            this.cityId = cityId;
        }

        public String getFixedtel() {
            return fixedtel;
        }

        public void setFixedtel(String fixedtel) {
            this.fixedtel = fixedtel;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getProvinceId() {
            return provinceId;
        }

        public void setProvinceId(String provinceId) {
            this.provinceId = provinceId;
        }

        public String getZipCode() {
            return zipCode;
        }

        public void setZipCode(String zipCode) {
            this.zipCode = zipCode;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "areaDetail='" + areaDetail + '\'' +
                    ", areaId='" + areaId + '\'' +
                    ", cityId='" + cityId + '\'' +
                    ", fixedtel='" + fixedtel + '\'' +
                    ", id=" + id +
                    ", name='" + name + '\'' +
                    ", phoneNumber='" + phoneNumber + '\'' +
                    ", provinceId='" + provinceId + '\'' +
                    ", zipCode='" + zipCode + '\'' +
                    '}';
        }
    }
}
