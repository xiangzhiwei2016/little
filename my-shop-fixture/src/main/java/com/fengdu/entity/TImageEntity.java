package com.fengdu.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 图片表实体
 * 表名 t_image
 *
 * @author william_w
 * @email 2366207000@qq.com
 * @date 2018-06-30 22:33:00
 */
public class TImageEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Long id;
    //可以是商品id,用户id,都行，只要有图片，都上传云，并保存路径
    private Long ikey;
    //类型（1.商品的，2.用户的头像3.案例4.详情的5.营销活动6.评价7.门店）
    private String type;
    //名称
    private String name;
    //路径
    private String imgUrl; 
    //创建时间
    private Date createTime;
    //修改时间
    private Date modifyTime;
    //创建者
    private String creator;
    //修改者
    private String modifier;
    
    private Long merchantId;

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
     * 设置：类型（1.商品的，2.用户的头像3.案例4.详情的5.营销活动6.评价7.门店）
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取：类型（1.商品的，2.用户的头像3.案例4.详情的5.营销活动6.评价7.门店）
     */
    public String getType() {
        return type;
    }
    /**
     * 设置：名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：名称
     */
    public String getName() {
        return name;
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

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Long getIkey() {
		return ikey;
	}

	public void setIkey(Long ikey) {
		this.ikey = ikey;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}
}
