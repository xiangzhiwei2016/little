package com.fengdu.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 商户表实体
 * 表名 t_merchant
 *
 * @author william_w
 * @email 2366207000@qq.com
 * @date 2018-06-27 17:00:44
 */
public class TMerchantEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Long id;
    //商户名称
    private String merchantName;
    
    //商户登录名
    private String mcLoginName;
    //密码（需加密）
    private String passWord;
    //创建时间
    private Date createTime;
    //修改时间
    private Date modifyTime;
    //创建者
    private String creator;
    //修改者
    private String modifier;

    // 小程序id
    private String appId;
    
    private String status;
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
     * 设置：商户名称
     */
    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    /**
     * 获取：商户名称
     */
    public String getMerchantName() {
        return merchantName;
    }
    /**
     * 设置：密码（需加密）
     */
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    /**
     * 获取：密码（需加密）
     */
    public String getPassWord() {
        return passWord;
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

	public String getMcLoginName() {
		return mcLoginName;
	}

	public void setMcLoginName(String mcLoginName) {
		this.mcLoginName = mcLoginName;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
