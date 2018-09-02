package com.fengdu.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 营销活动表实体
 * 表名 t_campaign
 *
 * @author william_w
 * @email 2366207000@qq.com
 * @date 2018-07-01 15:11:47
 */
public class TCampaignEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Long id;
    //商户id
    private Long merchantId;
    //活动类型id
    private Long typeId;
    //活动状态(1.可用2.不可用)
    private String status;
    //备注
    private String remark;
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
     * 设置：活动类型id
     */
    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    /**
     * 获取：活动类型id
     */
    public Long getTypeId() {
        return typeId;
    }
    /**
     * 设置：活动状态(1.可用2.不可用)
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取：活动状态(1.可用2.不可用)
     */
    public String getStatus() {
        return status;
    }
    /**
     * 设置：备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取：备注
     */
    public String getRemark() {
        return remark;
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
