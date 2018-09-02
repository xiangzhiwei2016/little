package com.fengdu.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * 实体
 * 表名 t_ord
 *
 * @author william_w
 * @email 2366207000@qq.com
 * @date 2018-08-18 20:56:29
 */
public class TOrdEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Integer id;
    //1:定金2:下单
    private String orderType;
    //实际需要支付的金额
    private BigDecimal totalFee;
    //
    private String orderNo;
    //
    private Integer payStatus;
    //
    private String payId;
    //
    private Integer clientId;
    private String clientName;
    //
    private Integer merchantId;
    //
    private Integer orderStatus;
    //
    private String consignee;
    //
    private Integer shippingStatus;
    //
    private Integer campaignId;
    //
    private String country;
    //
    private String province;
    //
    private String city;
    //
    private String district;
    //
    private String address;
    //
    private String mobile;
    //
    private String postscript;
    //
    private Integer shippingId;
    //
    private String shippingName;
    //
    private String payName;
    //
    private BigDecimal shippingFee;
    //
    private Integer integral;
    //
    private BigDecimal integralMoney;
    //订单总价
    private BigDecimal orderPrice;
    //商品总价
    private BigDecimal goodsPrice;
    //
    private Date addTime;
    //
    private Date confirmTime;
    //
    private Date payTime;
    //配送费用
    private Integer freightPrice;
    //使用的优惠券id
    private Integer couponId;
    //
    private Integer parentId;
    //
    private BigDecimal couponPrice;
    //
    private String callbackStatus;
    //
    private String shippingNo;
    //
    private BigDecimal fullCutPrice;

    private String imgUrl;
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
     * 设置：1:定金2:下单
     */
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    /**
     * 获取：1:定金2:下单
     */
    public String getOrderType() {
        return orderType;
    }
    /**
     * 设置：实际需要支付的金额
     */
    public void setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
    }

    /**
     * 获取：实际需要支付的金额
     */
    public BigDecimal getTotalFee() {
        return totalFee;
    }
    /**
     * 设置：
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * 获取：
     */
    public String getOrderNo() {
        return orderNo;
    }
    /**
     * 设置：
     */
    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    /**
     * 获取：
     */
    public Integer getPayStatus() {
        return payStatus;
    }
    /**
     * 设置：
     */
    public void setPayId(String payId) {
        this.payId = payId;
    }

    /**
     * 获取：
     */
    public String getPayId() {
        return payId;
    }
    /**
     * 设置：
     */
    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    /**
     * 获取：
     */
    public Integer getClientId() {
        return clientId;
    }
    /**
     * 设置：
     */
    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    /**
     * 获取：
     */
    public Integer getMerchantId() {
        return merchantId;
    }
    /**
     * 设置：
     */
    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * 获取：
     */
    public Integer getOrderStatus() {
        return orderStatus;
    }
    /**
     * 设置：
     */
    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    /**
     * 获取：
     */
    public String getConsignee() {
        return consignee;
    }
    /**
     * 设置：
     */
    public void setShippingStatus(Integer shippingStatus) {
        this.shippingStatus = shippingStatus;
    }

    /**
     * 获取：
     */
    public Integer getShippingStatus() {
        return shippingStatus;
    }
    /**
     * 设置：
     */
    public void setCampaignId(Integer campaignId) {
        this.campaignId = campaignId;
    }

    /**
     * 获取：
     */
    public Integer getCampaignId() {
        return campaignId;
    }
    /**
     * 设置：
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * 获取：
     */
    public String getCountry() {
        return country;
    }
    /**
     * 设置：
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * 获取：
     */
    public String getProvince() {
        return province;
    }
    /**
     * 设置：
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * 获取：
     */
    public String getCity() {
        return city;
    }
    /**
     * 设置：
     */
    public void setDistrict(String district) {
        this.district = district;
    }

    /**
     * 获取：
     */
    public String getDistrict() {
        return district;
    }
    /**
     * 设置：
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取：
     */
    public String getAddress() {
        return address;
    }
    /**
     * 设置：
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取：
     */
    public String getMobile() {
        return mobile;
    }
    /**
     * 设置：
     */
    public void setPostscript(String postscript) {
        this.postscript = postscript;
    }

    /**
     * 获取：
     */
    public String getPostscript() {
        return postscript;
    }
    /**
     * 设置：
     */
    public void setShippingId(Integer shippingId) {
        this.shippingId = shippingId;
    }

    /**
     * 获取：
     */
    public Integer getShippingId() {
        return shippingId;
    }
    /**
     * 设置：
     */
    public void setShippingName(String shippingName) {
        this.shippingName = shippingName;
    }

    /**
     * 获取：
     */
    public String getShippingName() {
        return shippingName;
    }
    /**
     * 设置：
     */
    public void setPayName(String payName) {
        this.payName = payName;
    }

    /**
     * 获取：
     */
    public String getPayName() {
        return payName;
    }
    /**
     * 设置：
     */
    public void setShippingFee(BigDecimal shippingFee) {
        this.shippingFee = shippingFee;
    }

    /**
     * 获取：
     */
    public BigDecimal getShippingFee() {
        return shippingFee;
    }
    /**
     * 设置：
     */
    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    /**
     * 获取：
     */
    public Integer getIntegral() {
        return integral;
    }
    /**
     * 设置：
     */
    public void setIntegralMoney(BigDecimal integralMoney) {
        this.integralMoney = integralMoney;
    }

    /**
     * 获取：
     */
    public BigDecimal getIntegralMoney() {
        return integralMoney;
    }
    /**
     * 设置：订单总价
     */
    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    /**
     * 获取：订单总价
     */
    public BigDecimal getOrderPrice() {
        return orderPrice;
    }
    /**
     * 设置：商品总价
     */
    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    /**
     * 获取：商品总价
     */
    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }
    /**
     * 设置：
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * 获取：
     */
    public Date getAddTime() {
        return addTime;
    }
    /**
     * 设置：
     */
    public void setConfirmTime(Date confirmTime) {
        this.confirmTime = confirmTime;
    }

    /**
     * 获取：
     */
    public Date getConfirmTime() {
        return confirmTime;
    }
    /**
     * 设置：
     */
    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    /**
     * 获取：
     */
    public Date getPayTime() {
        return payTime;
    }
    /**
     * 设置：配送费用
     */
    public void setFreightPrice(Integer freightPrice) {
        this.freightPrice = freightPrice;
    }

    /**
     * 获取：配送费用
     */
    public Integer getFreightPrice() {
        return freightPrice;
    }
    /**
     * 设置：使用的优惠券id
     */
    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    /**
     * 获取：使用的优惠券id
     */
    public Integer getCouponId() {
        return couponId;
    }
    /**
     * 设置：
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取：
     */
    public Integer getParentId() {
        return parentId;
    }
    /**
     * 设置：
     */
    public void setCouponPrice(BigDecimal couponPrice) {
        this.couponPrice = couponPrice;
    }

    /**
     * 获取：
     */
    public BigDecimal getCouponPrice() {
        return couponPrice;
    }
    /**
     * 设置：
     */
    public void setCallbackStatus(String callbackStatus) {
        this.callbackStatus = callbackStatus;
    }

    /**
     * 获取：
     */
    public String getCallbackStatus() {
        return callbackStatus;
    }
    /**
     * 设置：
     */
    public void setShippingNo(String shippingNo) {
        this.shippingNo = shippingNo;
    }

    /**
     * 获取：
     */
    public String getShippingNo() {
        return shippingNo;
    }
    /**
     * 设置：
     */
    public void setFullCutPrice(BigDecimal fullCutPrice) {
        this.fullCutPrice = fullCutPrice;
    }

    /**
     * 获取：
     */
    public BigDecimal getFullCutPrice() {
        return fullCutPrice;
    }

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
}
