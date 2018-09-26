package com.fengdu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.fengdu.constants.Constants;
import com.fengdu.dao.TCampaignTypeDao;
import com.fengdu.entity.TCampaignTypeEntity;
import com.fengdu.service.TCampaignTypeService;
import com.fengdu.utils.DateUtils;
import com.fengdu.utils.ShiroUtils;

/**
 * 营销活动的类型Service实现类
 *
 * @author william_w
 * @email 2366207000@qq.com
 * @date 2018-07-01 15:11:47
 */
@Service("tCampaignTypeService")
public class TCampaignTypeServiceImpl implements TCampaignTypeService {
    @Autowired
    private TCampaignTypeDao tCampaignTypeDao;

    @Override
    public TCampaignTypeEntity queryObject(Long id) {
        return tCampaignTypeDao.queryObject(id);
    }

    @Override
    public List<TCampaignTypeEntity> queryList(Map<String, Object> map) {
    	map.put(Constants.MERCHANT_ID, ShiroUtils.getUserId());
        return tCampaignTypeDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
    	map.put(Constants.MERCHANT_ID, ShiroUtils.getUserId());
        return tCampaignTypeDao.queryTotal(map);
    }

    @Override
    public int save(TCampaignTypeEntity tCampaignType) {
    	tCampaignType.setCreateTime(DateUtils.currentDate());
    	tCampaignType.setCreator(ShiroUtils.getUserName());
    	// 商户
    	tCampaignType.setMerchantId(ShiroUtils.getUserId());
    	tCampaignType.setName(getTypeName(tCampaignType.getType()));
    	tCampaignType.setRule(getRule(tCampaignType));
        return tCampaignTypeDao.save(tCampaignType);
    }

    @Override
    public int update(TCampaignTypeEntity tCampaignType) {
    	tCampaignType.setModifyTime(DateUtils.currentDate());
    	tCampaignType.setModifier(ShiroUtils.getUserName());
    	tCampaignType.setName(getTypeName(tCampaignType.getType()));
    	tCampaignType.setRule(getRule(tCampaignType));
        return tCampaignTypeDao.update(tCampaignType);
    }

    @Override
    public int delete(Long id) {
        return tCampaignTypeDao.delete(id);
    }

    @Override
    public int deleteBatch(Long[]ids) {
        return tCampaignTypeDao.deleteBatch(ids);
    }
    
    @Override
	public int updateStatus(Long id) {
    	TCampaignTypeEntity entity = this.queryObject(id);
		entity.setStatus(Constants.STATUS_DISABLED);
		return update(entity);
	}
    
    /**
     * 营销活动类型1.定金折扣2.组团返现3.赠送礼品
     * @param type
     * @return
     */
	private String getTypeName(String type) {
		String name = null;
		if ("1".equals(type)) {
			name = "定金享折扣";
		} else if ("2".equals(type)) {
			name = "组团返现金";
		} else if ("3".equals(type)) {
			name = "点评送礼品";
		}
		return name;
	}
    
    /**
     * 根据营销活动类型拼装信息
     * @param type
     * @param tCampaignType
     * @return
     */
	private String getRule(TCampaignTypeEntity tCampaignType) {
		String rule = null;
		String type = tCampaignType.getType();
		if ("1".equals(type)) {
			rule = "定金" + tCampaignType.getDeposit() + Constants.MONEY_UNIT
					+ "抵" + tCampaignType.getDiscount() + Constants.MONEY_UNIT;
		} else if ("2".equals(type)) {
			rule = "满" + tCampaignType.getMoneyFull() + Constants.MONEY_UNIT
					+ "减" + tCampaignType.getMoneyBack()
					+ Constants.MONEY_UNIT;
		} else if ("3".equals(type)) {
			rule = "赠送" + tCampaignType.getGift();
		}
		return rule;
	}
}
