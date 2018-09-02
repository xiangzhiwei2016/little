package com.fengdu.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 案例详情表实体
 * 表名 t_case_detail
 *
 * @author william_w
 * @email 2366207000@qq.com
 * @date 2018-07-07 11:28:31
 */
public class TCaseDetailEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Long id;
    private Long merchantId;
    //
    private Long caseId;
    private String caseName;
    //标题
    private String detailTitle;
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
    private List<TImageEntity> imgList = new ArrayList<>();

    private List<TImageEntity> uploadDetailList = new ArrayList<>();
     
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
     * 设置：
     */
    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }

    /**
     * 获取：
     */
    public Long getCaseId() {
        return caseId;
    }
    /**
     * 设置：标题
     */
    public void setDetailTitle(String detailTitle) {
        this.detailTitle = detailTitle;
    }

    /**
     * 获取：标题
     */
    public String getDetailTitle() {
        return detailTitle;
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

	public String getCaseName() {
		return caseName;
	}

	public void setCaseName(String caseName) {
		this.caseName = caseName;
	}

	public List<TImageEntity> getUploadDetailList() {
		return uploadDetailList;
	}

	public void setUploadDetailList(List<TImageEntity> uploadDetailList) {
		this.uploadDetailList = uploadDetailList;
	}
}
