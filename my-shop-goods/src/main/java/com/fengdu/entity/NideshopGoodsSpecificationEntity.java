package com.fengdu.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 商品对应规格表值表实体
 * 表名 nideshop_goods_specification
 *
 * @author william_w
 * @email 2366207000@qq.com
 * @date 2018-06-24 10:30:31
 */
public class NideshopGoodsSpecificationEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Integer id;
    //
    private Integer goodsId;
    //
    private Integer specificationId;
    //
    private String value;
    //
    private String picUrl;

    /**
     * 设置：
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取：
     */
    public Integer getId() {
        return id;
    }
    /**
     * 设置：
     */
    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 获取：
     */
    public Integer getGoodsId() {
        return goodsId;
    }
    /**
     * 设置：
     */
    public void setSpecificationId(Integer specificationId) {
        this.specificationId = specificationId;
    }

    /**
     * 获取：
     */
    public Integer getSpecificationId() {
        return specificationId;
    }
    /**
     * 设置：
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 获取：
     */
    public String getValue() {
        return value;
    }
    /**
     * 设置：
     */
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    /**
     * 获取：
     */
    public String getPicUrl() {
        return picUrl;
    }
}
