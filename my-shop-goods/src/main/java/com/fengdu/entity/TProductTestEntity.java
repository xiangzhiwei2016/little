package com.fengdu.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * 实体
 * 表名 t_product_test
 *
 * @author william_w
 * @email 2366207000@qq.com
 * @date 2018-06-24 11:03:19
 */
public class TProductTestEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Long id;
    //商品名称
    private String productName;
    //会员价（精度4位）
    private BigDecimal vipPrice;
    //原价（精度4位）
    private BigDecimal price;
    //标签
    private String tag;
    //描述
    private String productDesc;
    //商品状态
    private String productStatus;
    //创建时间
    private Date createTime;
    //修改时间
    private Date modifyTime;
    //创建者
    private String creator;
    //修改者
    private String modifier;

    /**
     * 设置：
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取：
     */
    public Long getId() {
        return id;
    }
    /**
     * 设置：商品名称
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * 获取：商品名称
     */
    public String getProductName() {
        return productName;
    }
    /**
     * 设置：会员价（精度4位）
     */
    public void setVipPrice(BigDecimal vipPrice) {
        this.vipPrice = vipPrice;
    }

    /**
     * 获取：会员价（精度4位）
     */
    public BigDecimal getVipPrice() {
        return vipPrice;
    }
    /**
     * 设置：原价（精度4位）
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 获取：原价（精度4位）
     */
    public BigDecimal getPrice() {
        return price;
    }
    /**
     * 设置：标签
     */
    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     * 获取：标签
     */
    public String getTag() {
        return tag;
    }
    /**
     * 设置：描述
     */
    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    /**
     * 获取：描述
     */
    public String getProductDesc() {
        return productDesc;
    }
    /**
     * 设置：商品状态
     */
    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }

    /**
     * 获取：商品状态
     */
    public String getProductStatus() {
        return productStatus;
    }
    /**
     * 设置：创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取：创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }
    /**
     * 设置：修改时间
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    /**
     * 获取：修改时间
     */
    public Date getModifyTime() {
        return modifyTime;
    }
    /**
     * 设置：创建者
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * 获取：创建者
     */
    public String getCreator() {
        return creator;
    }
    /**
     * 设置：修改者
     */
    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    /**
     * 获取：修改者
     */
    public String getModifier() {
        return modifier;
    }
}
