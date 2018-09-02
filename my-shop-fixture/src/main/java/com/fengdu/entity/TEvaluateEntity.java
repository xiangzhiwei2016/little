package com.fengdu.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 评价表实体
 * 表名 t_evaluate
 *
 * @author william_w
 * @email 2366207000@qq.com
 * @date 2018-06-27 16:52:11
 */
public class TEvaluateEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Long id;
    //商户id
    private Long merchantId;
    //商户id
    private Long productId;
    private String productName;
    //客户id
    private Long clientId;
    private String clientName;

    //评分
    private String score;
    //描述
    private String description;
    //创建时间
    private Date createTime;
    //修改时间
    private Date modifyTime;
    //创建者
    private String creator;
    //修改者
    private String modifier;
    
    //头像
    private String imgUrl;
    //电话
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
     * 设置：商户id
     */
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    /**
     * 获取：商户id
     */
    public Long getProductId() {
        return productId;
    }
    /**
     * 设置：客户id
     */
    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    /**
     * 获取：客户id
     */
    public Long getClientId() {
        return clientId;
    }
    /**
     * 设置：评分
     */
    public void setScore(String score) {
        this.score = score;
    }

    /**
     * 获取：评分
     */
    public String getScore() {
        return score;
    }
    /**
     * 设置：描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取：描述
     */
    public String getDescription() {
        return description;
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

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}
