package com.fengdu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.fengdu.constants.Constants;
import com.fengdu.constants.OrderStatusEnum;
import com.fengdu.dao.TOrdDao;
import com.fengdu.entity.TOrdEntity;
import com.fengdu.service.TOrdService;
import com.fengdu.utils.ShiroUtils;

/**
 * Service实现类
 *
 * @author william_w
 * @email 2366207000@qq.com
 * @date 2018-08-18 20:56:29
 */
@Service("tOrdService")
public class TOrdServiceImpl implements TOrdService {
    @Autowired
    private TOrdDao tOrdDao;

    @Override
    public TOrdEntity queryObject(Integer id) {
        return tOrdDao.queryObject(id);
    }

    @Override
    public List<TOrdEntity> queryList(Map<String, Object> map) {
    	map.put(Constants.MERCHANT_ID, ShiroUtils.getUserId());
        return tOrdDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
    	map.put(Constants.MERCHANT_ID, ShiroUtils.getUserId());
        return tOrdDao.queryTotal(map);
    }

    @Override
    public int save(TOrdEntity tOrd) {
        return tOrdDao.save(tOrd);
    }

    @Override
    public int update(TOrdEntity tOrd) {
        return tOrdDao.update(tOrd);
    }

    @Override
    public int delete(Integer id) {
        return tOrdDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[]ids) {
        return tOrdDao.deleteBatch(ids);
    }
    
    @Override
	public int updateStatus(Integer id) {
    	TOrdEntity entity = this.queryObject(id);
		entity.setOrderStatus(OrderStatusEnum.ORDER_CANCLE.getCode());
		return update(entity);
	}
}
