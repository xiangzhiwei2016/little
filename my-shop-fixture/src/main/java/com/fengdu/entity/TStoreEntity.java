package com.fengdu.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 门店表实体
 * 表名 t_store
 *
 * @author william_w
 * @email 2366207000@qq.com
 * @date 2018-06-30 21:50:02
 */
public class TStoreEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Long id;
    //商户id
    private Long merchantId;
    //商户名称
    private String merchantName;
    //主营业务
    private String mainBusiness;
    //地址
    private String address;
    //联系电话
    private String phoneNo;
    //创建时间
    private Date createTime;
    //修改时间
    private Date modifyTime;
    //创建者
    private String creator;
    //修改者
    private String modifier;
    // 经度
    private String lng;
    // 纬度
    private String lat;

    // 图片
    private List<TImageEntity> imgList = new ArrayList<TImageEntity>();
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
     * 设置：主营业务
     */
    public void setMainBusiness(String mainBusiness) {
        this.mainBusiness = mainBusiness;
    }

    /**
     * 获取：主营业务
     */
    public String getMainBusiness() {
        return mainBusiness;
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
     * 设置：联系电话
     */
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    /**
     * 获取：联系电话
     */
    public String getPhoneNo() {
        return phoneNo;
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

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}
}
