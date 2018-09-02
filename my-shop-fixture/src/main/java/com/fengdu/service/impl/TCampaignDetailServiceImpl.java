package com.fengdu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.fengdu.constants.Constants;
import com.fengdu.dao.TCampaignDetailDao;
import com.fengdu.entity.TCampaignDetailEntity;
import com.fengdu.service.TCampaignDetailService;
import com.fengdu.utils.DateUtils;
import com.fengdu.utils.ShiroUtils;

/**
 * 营销活动详情表Service实现类
 *
 * @author william_w
 * @email 2366207000@qq.com
 * @date 2018-06-27 16:40:09
 */
@Service("tCampaignDetailService")
public class TCampaignDetailServiceImpl implements TCampaignDetailService {
    @Autowired
    private TCampaignDetailDao tCampaignDetailDao;

    @Override
    public TCampaignDetailEntity queryObject(Long id) {
        return tCampaignDetailDao.queryObject(id);
    }

    @Override
    public List<TCampaignDetailEntity> queryList(Map<String, Object> map) {
    	map.put(Constants.MERCHANT_ID, ShiroUtils.getUserId());
        return tCampaignDetailDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
    	map.put(Constants.MERCHANT_ID, ShiroUtils.getUserId());
        return tCampaignDetailDao.queryTotal(map);
    }

    @Override
    public int save(TCampaignDetailEntity tCampaignDetail) {
    	tCampaignDetail.setCreateTime(DateUtils.currentDate());
    	tCampaignDetail.setCreator(ShiroUtils.getUserName());
        return tCampaignDetailDao.save(tCampaignDetail);
    }

    @Override
    public int update(TCampaignDetailEntity tCampaignDetail) {
    	tCampaignDetail.setModifyTime(DateUtils.currentDate());
    	tCampaignDetail.setModifier(ShiroUtils.getUserName());
        return tCampaignDetailDao.update(tCampaignDetail);
    }

    @Override
    public int delete(Long id) {
        return tCampaignDetailDao.delete(id);
    }

    @Override
    public int deleteBatch(Long[]ids) {
        return tCampaignDetailDao.deleteBatch(ids);
    }
}
