package com.fengdu.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 订单表实体
 * 表名 t_order
 *
 * @author william_w
 * @email 2366207000@qq.com
 * @date 2018-06-30 14:08:57
 */
public class TOrderEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Long id;
    //订单编号
    private String orderNo;
    private Long merchantId;
    //商品id
    private Long productId;
    private String productName;

    //客户id
    private Long clientId;
    private String clientName;

    //订单状态（）
    private String orderStatus;
    //创建时间
    private Date createTime;
    //修改时间
    private Date modifyTime;
    //创建者
    private String creator;
    //修改者
    private String modifier;

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
     * 设置：订单编号
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * 获取：订单编号
     */
    public String getOrderNo() {
        return orderNo;
    }
    /**
     * 设置：商品id
     */
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    /**
     * 获取：商品id
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
     * 设置：订单状态（）
     */
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * 获取：订单状态（）
     */
    public String getOrderStatus() {
        return orderStatus;
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

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}
