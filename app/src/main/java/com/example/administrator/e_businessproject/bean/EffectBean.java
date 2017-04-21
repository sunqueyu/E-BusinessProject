package com.example.administrator.e_businessproject.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 类的用途：
 * Created by ${孙鹊禹}
 * on 2017/4/17 13:42
 */

public class EffectBean implements Serializable{

    /**
     * code : 200
     * data : [{"efficacy":"镇店之宝 美白爆款","goods_img":"https://image.yunifang.com/yunifang/images/goods/121/goods_img/170301091610311632161123479.jpg","goods_name":"镇店之宝丨美白嫩肤面膜7片","id":"121","is_allow_credit":false,"is_coupon_allowed":true,"market_price":99,"sales_volume":122703,"shop_price":49.9,"sort":0},{"efficacy":"以黑吸黑 净透亮肤","goods_img":"http://image.hmeili.com/yunifang/images/goods/389/goods_img/16081909368531961221339216.jpg","goods_name":"清爽平衡矿物蚕丝面膜黑面膜21片","id":"389","is_allow_credit":false,"is_coupon_allowed":true,"market_price":297,"sales_volume":102252,"shop_price":99.9,"sort":0},{"efficacy":"水嫩舒爽 鲜活亮颜","goods_img":"http://image.hmeili.com/yunifang/images/goods/85/goods_img/160819085747012099748482408.jpg","goods_name":"果味鲜饮|水果鲜润亮肤补水面膜套装17片","id":"85","is_allow_credit":false,"is_coupon_allowed":false,"market_price":240,"sales_volume":93236,"shop_price":59.9,"sort":0},{"efficacy":"深层补水 提亮肤色","goods_img":"http://image.hmeili.com/yunifang/images/goods/6/goods_img/160819084594721181484473699.jpg","goods_name":"好用不贵丨亮颜水润蚕丝面膜（寂地定制版）","id":"6","is_allow_credit":false,"is_coupon_allowed":false,"market_price":239.9,"sales_volume":71184,"shop_price":59.9,"sort":0},{"efficacy":"补水保湿 美白嫩肤","goods_img":"http://image.hmeili.com/yunifang/images/goods/343/goods_img/161109195539019451480402371.jpg","goods_name":"美白嫩肤润肤水150ml","id":"343","is_allow_credit":false,"is_coupon_allowed":true,"market_price":79,"sales_volume":56492,"shop_price":59,"sort":0},{"efficacy":"补水美白 越睡越白","goods_img":"http://image.hmeili.com/yunifang/images/goods/10/goods_img/161109200044614716799834077.jpg","goods_name":"睡出白美人|美白嫩肤睡眠面膜180g","id":"10","is_allow_credit":false,"is_coupon_allowed":true,"market_price":99,"sales_volume":42755,"shop_price":69,"sort":0},{"efficacy":"爆款面膜 明星同款","goods_img":"https://image.yunifang.com/yunifang/images/goods/8/goods_img/1703060954389257968293129.jpg","goods_name":"镇店之宝|美白嫩肤面膜20片","id":"8","is_allow_credit":false,"is_coupon_allowed":true,"market_price":298,"sales_volume":27501,"shop_price":119.9,"sort":0},{"efficacy":"深度保养 补水提亮","goods_img":"http://image.hmeili.com/yunifang/images/goods/83/goods_img/16081908576425344499831215.jpg","goods_name":"黑玫瑰矿物蚕丝面膜7片","id":"83","is_allow_credit":false,"is_coupon_allowed":true,"market_price":139,"sales_volume":26519,"shop_price":119,"sort":0},{"efficacy":"补水滋养 提亮修护","goods_img":"http://image.hmeili.com/yunifang/images/goods/95/goods_img/160819085823018111120147866.jpg","goods_name":"多效套装丨补水滋养提亮修护多效蚕丝面膜28片","id":"95","is_allow_credit":false,"is_coupon_allowed":true,"market_price":416,"sales_volume":26284,"shop_price":139.9,"sort":0},{"efficacy":"温和清洁 提亮肤色","goods_img":"http://image.hmeili.com/yunifang/images/goods/4/goods_img/160819084471812844778474460.jpg","goods_name":"黑玫瑰矿物洁面乳100ml","id":"4","is_allow_credit":false,"is_coupon_allowed":true,"market_price":59,"sales_volume":24624,"shop_price":49,"sort":0},{"efficacy":"吸黑焕彩 补水保湿","goods_img":"http://image.hmeili.com/yunifang/images/goods/428/goods_img/160819094034113421009937866.jpg","goods_name":"多彩水润亮颜蚕丝面膜套装21片","id":"428","is_allow_credit":false,"is_coupon_allowed":true,"market_price":270.9,"sales_volume":23957,"shop_price":79.9,"sort":0},{"efficacy":"密集滋养 补水保湿","goods_img":"http://image.hmeili.com/yunifang/images/goods/87/goods_img/16081908586078271308744782.jpg","goods_name":"玫瑰滋养蚕丝面膜7片","id":"87","is_allow_credit":false,"is_coupon_allowed":true,"market_price":99,"sales_volume":21734,"shop_price":79,"sort":0},{"efficacy":"清洁净透 以白养白","goods_img":"http://image.hmeili.com/yunifang/images/goods/379/goods_img/160819093561210755335724384.jpg","goods_name":"净透美白矿物蚕丝面膜黑面膜14片","id":"379","is_allow_credit":false,"is_coupon_allowed":true,"market_price":198,"sales_volume":17405,"shop_price":69.9,"sort":0},{"efficacy":"补水保湿 鲜活亮肤","goods_img":"http://image.hmeili.com/yunifang/images/goods/71/goods_img/160819085118618037277379170.jpg","goods_name":"红石榴矿物补水亮肤十件套","id":"71","is_allow_credit":false,"is_coupon_allowed":true,"market_price":319.9,"sales_volume":15537,"shop_price":159.99,"sort":0},{"efficacy":"美白补水 净白如初","goods_img":"http://image.hmeili.com/yunifang/images/goods/253/goods_img/16110920078693304771874925.jpg","goods_name":"美白嫩肤睡眠四件套","id":"253","is_allow_credit":false,"is_coupon_allowed":true,"market_price":309.9,"sales_volume":14070,"shop_price":239.9,"sort":0},{"efficacy":"补水亮肤 缤纷水嫩","goods_img":"http://image.hmeili.com/yunifang/images/goods/400/goods_img/16081909375548938767322654.jpg","goods_name":"缤纷鲜润矿物蚕丝面膜套装20片","id":"400","is_allow_credit":false,"is_coupon_allowed":true,"market_price":279.9,"sales_volume":13398,"shop_price":79.9,"sort":0},{"efficacy":"补水保湿 提亮肤色","goods_img":"http://image.hmeili.com/yunifang/images/goods/745/goods_img/160819095890910677609745693.jpg","goods_name":"红石榴矿物蚕丝面膜7片","id":"745","is_allow_credit":false,"is_coupon_allowed":true,"market_price":99,"sales_volume":11603,"shop_price":79,"sort":0},{"efficacy":"清洁补水 保湿提亮","goods_img":"http://image.hmeili.com/yunifang/images/goods/1249/goods_img/170104100411616941119547437.jpg","goods_name":"口碑推荐|盈透柔肤黑膜组合装42片","id":"1249","is_allow_credit":false,"is_coupon_allowed":true,"market_price":594,"sales_volume":8863,"shop_price":199.9,"sort":0},{"efficacy":"补水保湿 清爽控油","goods_img":"http://image.hmeili.com/yunifang/images/goods/161/goods_img/16081909077546170320476224.jpg","goods_name":"买1盒送1盒丨男士补水活力矿物蚕丝7片（共14片）","id":"161","is_allow_credit":false,"is_coupon_allowed":true,"market_price":99,"sales_volume":8350,"shop_price":59.9,"sort":0},{"efficacy":"温和清洁 补水美白","goods_img":"http://image.hmeili.com/yunifang/images/goods/185/goods_img/161109205558015059221705123.jpg","goods_name":"美白嫩肤护肤基础三件套装","id":"185","is_allow_credit":false,"is_coupon_allowed":true,"market_price":209.9,"sales_volume":7924,"shop_price":169.9,"sort":0},{"efficacy":"新品组合 鲜果养肤","goods_img":"http://image.hmeili.com/yunifang/images/goods/708/goods_img/16082615049413682815961884.jpg","goods_name":"水果护肤力量丨鲜果缤纷黑白面膜套装42片","id":"708","is_allow_credit":false,"is_coupon_allowed":true,"market_price":594,"sales_volume":7151,"shop_price":209.9,"sort":0},{"efficacy":"葡萄鲜饮 净透亮肤","goods_img":"http://image.hmeili.com/yunifang/images/goods/622/goods_img/160819094698413173347474163.jpg","goods_name":"葡萄籽琉璃亮颜黑面膜7片","id":"622","is_allow_credit":false,"is_coupon_allowed":true,"market_price":99,"sales_volume":6200,"shop_price":79,"sort":0},{"efficacy":"美白滋养 补水保湿","goods_img":"http://image.hmeili.com/yunifang/images/goods/344/goods_img/161109195851812653741618273.jpg","goods_name":"美白嫩肤润肤乳液120ml","id":"344","is_allow_credit":false,"is_coupon_allowed":true,"market_price":99,"sales_volume":5916,"shop_price":89,"sort":0},{"efficacy":"净透补水 润泽提亮","goods_img":"http://image.hmeili.com/yunifang/images/goods/1189/goods_img/161128144382319687354892538.jpg","goods_name":"热销黑膜丨清透水嫩亮颜黑面膜套装21片","id":"1189","is_allow_credit":false,"is_coupon_allowed":true,"market_price":297,"sales_volume":5904,"shop_price":99.9,"sort":0},{"efficacy":"提亮肤色 修护肌肤","goods_img":"http://image.hmeili.com/yunifang/images/goods/921/goods_img/161018083525512130589201446.jpg","goods_name":"甜橙晶透亮颜矿物面膜7片","id":"921","is_allow_credit":false,"is_coupon_allowed":true,"market_price":99,"sales_volume":5431,"shop_price":79,"sort":0},{"efficacy":"清洁净透 亮白润泽","goods_img":"http://image.hmeili.com/yunifang/images/goods/12/goods_img/1611092002609629373951762.jpg","goods_name":"美白嫩肤泥浆面膜260g","id":"12","is_allow_credit":false,"is_coupon_allowed":true,"market_price":129,"sales_volume":4352,"shop_price":129,"sort":0},{"efficacy":"清洁净透 以白养白","goods_img":"http://image.hmeili.com/yunifang/images/goods/534/goods_img/160819094245115265661305031.jpg","goods_name":"净透美白矿物蚕丝面膜套装28片","id":"534","is_allow_credit":false,"is_coupon_allowed":true,"market_price":396,"sales_volume":3954,"shop_price":139.9,"sort":0},{"efficacy":"清洁控油 补水提亮","goods_img":"http://image.hmeili.com/yunifang/images/goods/1251/goods_img/170104095895513706519167911.jpg","goods_name":"水果缤纷面膜超值装35片","id":"1251","is_allow_credit":false,"is_coupon_allowed":true,"market_price":495,"sales_volume":3924,"shop_price":169.9,"sort":0},{"efficacy":"提拉紧致 补水保湿","goods_img":"http://image.hmeili.com/yunifang/images/goods/81/goods_img/160819085711519851862212059.jpg","goods_name":"人参矿物蚕丝面膜7片","id":"81","is_allow_credit":false,"is_coupon_allowed":true,"market_price":109,"sales_volume":3810,"shop_price":89,"sort":0},{"efficacy":"清洁焕彩 净透亮肤","goods_img":"http://image.hmeili.com/yunifang/images/goods/334/goods_img/160819092848420294792181143.jpg","goods_name":"清爽平衡矿物蚕丝面膜黑面膜7片","id":"334","is_allow_credit":false,"is_coupon_allowed":true,"market_price":99,"sales_volume":3777,"shop_price":79,"sort":0}]
     * msg : success
     */

    private int code;
    private String msg;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "EffectBean{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBean implements Serializable{
        /**
         * efficacy : 镇店之宝 美白爆款
         * goods_img : https://image.yunifang.com/yunifang/images/goods/121/goods_img/170301091610311632161123479.jpg
         * goods_name : 镇店之宝丨美白嫩肤面膜7片
         * id : 121
         * is_allow_credit : false
         * is_coupon_allowed : true
         * market_price : 99.0
         * sales_volume : 122703
         * shop_price : 49.9
         * sort : 0
         */

        private String efficacy;
        private String goods_img;
        private String goods_name;
        private String id;
        private boolean is_allow_credit;
        private boolean is_coupon_allowed;
        private double market_price;
        private int sales_volume;
        private double shop_price;
        private int sort;

        public String getEfficacy() {
            return efficacy;
        }

        public void setEfficacy(String efficacy) {
            this.efficacy = efficacy;
        }

        public String getGoods_img() {
            return goods_img;
        }

        public void setGoods_img(String goods_img) {
            this.goods_img = goods_img;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public boolean isIs_allow_credit() {
            return is_allow_credit;
        }

        public void setIs_allow_credit(boolean is_allow_credit) {
            this.is_allow_credit = is_allow_credit;
        }

        public boolean isIs_coupon_allowed() {
            return is_coupon_allowed;
        }

        public void setIs_coupon_allowed(boolean is_coupon_allowed) {
            this.is_coupon_allowed = is_coupon_allowed;
        }

        public double getMarket_price() {
            return market_price;
        }

        public void setMarket_price(double market_price) {
            this.market_price = market_price;
        }

        public int getSales_volume() {
            return sales_volume;
        }

        public void setSales_volume(int sales_volume) {
            this.sales_volume = sales_volume;
        }

        public double getShop_price() {
            return shop_price;
        }

        public void setShop_price(double shop_price) {
            this.shop_price = shop_price;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "efficacy='" + efficacy + '\'' +
                    ", goods_img='" + goods_img + '\'' +
                    ", goods_name='" + goods_name + '\'' +
                    ", id='" + id + '\'' +
                    ", is_allow_credit=" + is_allow_credit +
                    ", is_coupon_allowed=" + is_coupon_allowed +
                    ", market_price=" + market_price +
                    ", sales_volume=" + sales_volume +
                    ", shop_price=" + shop_price +
                    ", sort=" + sort +
                    '}';
        }
    }
}
