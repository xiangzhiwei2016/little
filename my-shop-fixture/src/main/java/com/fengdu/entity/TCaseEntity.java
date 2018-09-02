package com.fengdu.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 案例表实体
 * 表名 t_case
 *
 * @author william_w
 * @email 2366207000@qq.com
 * @date 2018-06-27 15:44:39
 */
public class TCaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Long id;
    //案例名称
    private String caseName;
    //地址
    private String address;
    //面积
    private String area;
    //风格
    private String style;
    //商品ids(多个商品id)
    private String products;
    //创建时间
    private Date createTime;
    //修改时间
    private Date modifyTime;
    //创建者
    private String creator;
    //修改者
    private String modifier;
    // 主图
    private String imgUrl;
    
    private Long merchantId;
   
    private List<TImageEntity> imgList = new ArrayList<TImageEntity>();

    private List<TCaseDetailEntity> caseDetailList =  new ArrayList<TCaseDetailEntity>();
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
     * 设置：案例名称
     */
    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    /**
     * 获取：案例名称
     */
    public String getCaseName() {
        return caseName;
    }
    /**
     * 设置：地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取：地址
     */
    public String getAddress() {
        return address;
    }
    /**
     * 设置：面积
     */
    public void setArea(String area) {
        this.area = area;
    }

    /**
     * 获取：面积
     */
    public String getArea() {
        return area;
    }
    /**
     * 设置：风格
     */
    public void setStyle(String style) {
        this.style = style;
    }

    /**
     * 获取：风格
     */
    public String getStyle() {
        return style;
    }
    /**
     * 设置：商品ids(多个商品id)
     */
    public void setProducts(String products) {
        this.products = products;
    }

    /**
     * 获取：商品ids(多个商品id)
     */
    public String getProducts() {
        return products;
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

	public List<TImageEntity> getImgList() {
		return imgList;
	}

	public void setImgList(List<TImageEntity> imgList) {
		this.imgList = imgList;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public List<TCaseDetailEntity> getCaseDetailList() {
		return caseDetailList;
	}

	public void setCaseDetailList(List<TCaseDetailEntity> caseDetailList) {
		this.caseDetailList = caseDetailList;
	}
}
