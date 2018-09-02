package com.fengdu.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 营销活动详情表实体 表名 t_campaign_detail
 *
 * @author william_w
 * @email 2366207000@qq.com
 * @date 2018-07-01 16:19:19
 */
public class TCampaignDetailEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//
	private Long id;
	// 参加的客户id
	private Long clientId;
	private String clientName;
	private Long merchantId;
	// 营销活动类型id
	private Long typeId;

	private String typeName;
	// 备注
	private String remark;
	// 创建时间
	private Date createTime;
	// 修改时间
	private Date modifyTime;
	// 创建者
	private String creator;
	// 修改者
	private String modifier;

	private String wechatNo;
	
	private String imgUrl;
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
	 * 设置：参加的客户id
	 */
	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	/**
	 * 获取：参加的客户id
	 */
	public Long getClientId() {
		return clientId;
	}

	/**
	 * 设置：营销活动类型id
	 */
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	/**
	 * 获取：营销活动类型id
	 */
	public Long getTypeId() {
		return typeId;
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

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getWechatNo() {
		return wechatNo;
	}

	public void setWechatNo(String wechatNo) {
		this.wechatNo = wechatNo;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
}
