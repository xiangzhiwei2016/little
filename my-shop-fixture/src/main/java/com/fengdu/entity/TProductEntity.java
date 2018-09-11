package com.fengdu.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 商品表实体
 * 表名 t_product
 *
 * @author william_w
 * @email 2366207000@qq.com
 * @date 2018-06-27 14:55:47
 */
public class TProductEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Long id;
    //商品名称
    private String productName;
    //类别id
    private Long categoryId;
    // 类别名称
    private String categoryName;
    //会员价（精度4位）
    private BigDecimal vipPrice;
    //原价（精度4位）
    private BigDecimal price;
    //会员价（精度4位）
    private String vipPriceStr;
    //原价（精度4位）
    private String priceStr;
    //数量
    private BigDecimal countNum;
    //标签
    private String tag;
    private String tag2;
    private String tagDesc;
    //描述
    private String description;
    //商品状态
    private String status;
    //创建时间
    private Date createTime;
    //修改时间
    private Date modifyTime;
    //创建者
    private String creator;
    //修改者
    private String modifier;
    // 单位
    private String unit;
    // 商品图片
    private List<TImageEntity> imgList = new ArrayList<>();

    private Long merchantId;
    
    // 是否支持在线购买(0:否，1：是)
    private String buystatus="0";
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
     * 设置：类别id
     */
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * 获取：类别id
     */
    public Long getCategoryId() {
        return categoryId;
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
     * 设置：数量
     */
    public void setCountNum(BigDecimal countNum) {
        this.countNum = countNum;
    }

    /**
     * 获取：数量
     */
    public BigDecimal getCountNum() {
        return countNum;
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
     * 设置：商品状态
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取：商品状态
     */
    public String getStatus() {
        return status;
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

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<TImageEntity> getImgList() {
		return imgList;
	}

	public void setImgList(List<TImageEntity> imgList) {
		this.imgList = imgList;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getBuystatus() {
		return buystatus;
	}

	public void setBuystatus(String buystatus) {
		this.buystatus = buystatus;
	}

	public String getVipPriceStr() {
		return vipPriceStr;
	}

	public void setVipPriceStr(String vipPriceStr) {
		this.vipPriceStr = vipPriceStr;
	}

	public String getPriceStr() {
		return priceStr;
	}

	public void setPriceStr(String priceStr) {
		this.priceStr = priceStr;
	}

	public String getTag2() {
		return tag2;
	}

	public void setTag2(String tag2) {
		this.tag2 = tag2;
	}

	public String getTagDesc() {
		return tagDesc;
	}

	public void setTagDesc(String tagDesc) {
		this.tagDesc = tagDesc;
	}
}
