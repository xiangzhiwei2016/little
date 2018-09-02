package com.fengdu.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 营销活动的类型实体
 * 表名 t_campaign_type
 *
 * @author william_w
 * @email 2366207000@qq.com
 * @date 2018-07-01 16:19:18
 */
public class TCampaignTypeEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private Long id;
    //商户id
    private Long merchantId;
    //活动名（如.定金折扣.组团.点评送礼）
    private String name;
    //具体的活动规则json
    private String rule;
    //活动状态（0不可用，1可用）
    private String status;
    //描述
    private String description;
    //活动生效时间
    private Date effectTime;
    //活动过期时间
    private Date expiryTime;
    //创建时间
    private Date createTime;
    //修改时间
    private Date modifyTime;
    //创建者
    private String creator;
    //修改者
    private String modifier;
    
    // 营销活动类型1.定金折扣2.组团返现3.赠送礼品
    private String type;
    
    // 定金
    private String deposit;
    
    // 折扣
    private String discount;

    // 组团
    private String group;
    
    // 满额
    private String moneyFull;

    // 满额
    private String moneyBack;
    // 礼品
    private String gift;

    // 人数
    private String count;

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
     * 设置：活动名（如.定金折扣.组团.点评送礼）
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：活动名（如.定金折扣.组团.点评送礼）
     */
    public String getName() {
        return name;
    }
    /**
     * 设置：具体的活动规则json
     */
    public void setRule(String rule) {
        this.rule = rule;
    }

    /**
     * 获取：具体的活动规则json
     */
    public String getRule() {
        return rule;
    }
    /**
     * 设置：活动状态（0不可用，1可用）
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取：活动状态（0不可用，1可用）
     */
    public String getStatus() {
        return status;
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
     * 设置：活动生效时间
     */
    public void setEffectTime(Date effectTime) {
        this.effectTime = effectTime;
    }

    /**
     * 获取：活动生效时间
     */
    public Date getEffectTime() {
        return effectTime;
    }
    /**
     * 设置：活动过期时间
     */
    public void setExpiryTime(Date expiryTime) {
        this.expiryTime = expiryTime;
    }

    /**
     * 获取：活动过期时间
     */
    public Date getExpiryTime() {
        return expiryTime;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDeposit() {
		return deposit;
	}

	public void setDeposit(String deposit) {
		this.deposit = deposit;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getMoneyFull() {
		return moneyFull;
	}

	public void setMoneyFull(String moneyFull) {
		this.moneyFull = moneyFull;
	}

	public String getMoneyBack() {
		return moneyBack;
	}

	public void setMoneyBack(String moneyBack) {
		this.moneyBack = moneyBack;
	}

	public String getGift() {
		return gift;
	}

	public void setGift(String gift) {
		this.gift = gift;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}
}
