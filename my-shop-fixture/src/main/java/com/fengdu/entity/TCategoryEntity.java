package com.fengdu.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 类别表实体
 * 表名 t_category
 *
 * @author william_w
 * @email 2366207000@qq.com
 * @date 2018-06-27 16:03:53
 */
public class TCategoryEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Long id;
    //类别名称
    private String categoryName;
    //商户id
    private Long merchantId;
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
     * 设置：类别名称
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * 获取：类别名称
     */
    public String getCategoryName() {
        return categoryName;
    }
    /**
     * 设置：商户id
     */
    public void setMerchantId(Long merchantId) {
        this.merchantId = merchantId;
    }

    /**
     * 获取：商户id
     */
    public Long getMerchantId() {
        return merchantId;
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
