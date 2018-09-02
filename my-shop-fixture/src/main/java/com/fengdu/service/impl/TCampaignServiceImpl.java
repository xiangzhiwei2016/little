package com.fengdu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.fengdu.dao.TCampaignDao;
import com.fengdu.entity.TCampaignEntity;
import com.fengdu.service.TCampaignService;

/**
 * 营销活动表Service实现类
 *
 * @author william_w
 * @email 2366207000@qq.com
 * @date 2018-07-01 15:11:47
 */
@Service("tCampaignService")
public class TCampaignServiceImpl implements TCampaignService {
    @Autowired
    private TCampaignDao tCampaignDao;

    @Override
    public TCampaignEntity queryObject(Long id) {
        return tCampaignDao.queryObject(id);
    }

    @Override
    public List<TCampaignEntity> queryList(Map<String, Object> map) {
        return tCampaignDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return tCampaignDao.queryTotal(map);
    }

    @Override
    public int save(TCampaignEntity tCampaign) {
        return tCampaignDao.save(tCampaign);
    }

    @Override
    public int update(TCampaignEntity tCampaign) {
        return tCampaignDao.update(tCampaign);
    }

    @Override
    public int delete(Long id) {
        return tCampaignDao.delete(id);
    }

    @Override
    public int deleteBatch(Long[]ids) {
        return tCampaignDao.deleteBatch(ids);
    }
}
