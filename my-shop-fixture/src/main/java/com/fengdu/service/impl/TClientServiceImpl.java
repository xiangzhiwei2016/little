package com.fengdu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.fengdu.constants.ClientTypeEnum;
import com.fengdu.constants.Constants;
import com.fengdu.dao.TClientDao;
import com.fengdu.entity.TClientEntity;
import com.fengdu.service.TClientService;
import com.fengdu.utils.ShiroUtils;

/**
 * 客户表Service实现类
 *
 * @author william_w
 * @email 2366207000@qq.com
 * @date 2018-07-01 19:18:30
 */
@Service("tClientService")
public class TClientServiceImpl implements TClientService {
    @Autowired
    private TClientDao tClientDao;

    @Override
    public TClientEntity queryObject(Long id) {
        return tClientDao.queryObject(id);
    }

    @Override
    public List<TClientEntity> queryList(Map<String, Object> map) {
    	map.put(Constants.MERCHANT_ID, ShiroUtils.getUserId());
        return tClientDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
    	map.put(Constants.MERCHANT_ID, ShiroUtils.getUserId());
        return tClientDao.queryTotal(map);
    }

    @Override
    public int save(TClientEntity tClient) {
    	tClient.setMerchantId(ShiroUtils.getUserId());
        return tClientDao.save(tClient);
    }

    @Override
    public int update(TClientEntity tClient) {
        return tClientDao.update(tClient);
    }

    @Override
    public int delete(Long id) {
        return tClientDao.delete(id);
    }

    @Override
    public int deleteBatch(Long[]ids) {
        return tClientDao.deleteBatch(ids);
    }
    
    /**
     * 成为推广员
     * @param id
     */
    @Override
    public void toSpread(Long id) {
    	TClientEntity client = queryObject(id);
    	client.setClientType(ClientTypeEnum.SPREAD.getCode());
    	update(client);
    }
}
