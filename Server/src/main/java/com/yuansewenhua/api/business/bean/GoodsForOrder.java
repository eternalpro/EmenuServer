package com.yuansewenhua.api.business.bean;


/**
 * Created by youkou on 2015/6/22.
 */
public class GoodsForOrder {
    //ID
    private int mid;
    //商品名称（中文）
    private String goodsName;
    //商品类型只能是（食物：GoodsEnum.FOOD）或（酒水：GoodsEnum.DRINK）
    private GoodsEnum type;
    //该商品的数量
    private int count = 0;
    //该商品的状态（0：正常；1：追加；-1：取消）
    private int status = 0;
    //单价: 格式：20/瓶
    private String price;

    public long getMid() {
        return mid;
    }

    public void setMid(int id) {
        this.mid = id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public GoodsEnum getType() {
        return type;
    }

    public void setType(GoodsEnum type) {
        this.type = type;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
