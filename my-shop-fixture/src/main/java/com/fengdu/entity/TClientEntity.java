package com.fengdu.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 客户表实体
 * 表名 t_client
 *
 * @author william_w
 * @email 2366207000@qq.com
 * @date 2018-07-01 19:18:30
 */
public class TClientEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Long id;
    private Long merchantId;
    //客户名称
    private String clientName;
    //微信号
    private String wechatNo;
    //头像
    private String imgUrl;
    // 用户类型
    private String clientType;
    
    // 关注渠道
    private String channel;
    
    // 关注时间
    private Date registerTime;
    
    // 推广人员
    private Long upClientId;
    
    private String totalCount;
    
    private String memberCount;
    
    private Date lastLoginTime;
    
    private String mobile;
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
     * 设置：客户名称
     */
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    /**
     * 获取：客户名称
     */
    public String getClientName() {
        return clientName;
    }
    /**
     * 设置：微信号
     */
    public void setWechatNo(String wechatNo) {
        this.wechatNo = wechatNo;
    }

    /**
     * 获取：微信号
     */
    public String getWechatNo() {
        return wechatNo;
    }

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getClientType() {
		return clientType;
	}

	public void setClientType(String clientType) {
		this.clientType = clientType;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public Long getUpClientId() {
		return upClientId;
	}

	public void setUpClientId(Long upClientId) {
		this.upClientId = upClientId;
	}

	public String getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}

	public String getMemberCount() {
		return memberCount;
	}

	public void setMemberCount(String memberCount) {
		this.memberCount = memberCount;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}
