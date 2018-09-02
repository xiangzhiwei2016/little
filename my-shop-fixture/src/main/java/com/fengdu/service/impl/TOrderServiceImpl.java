package com.fengdu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.fengdu.constants.Constants;
import com.fengdu.constants.OrderStatusEnum;
import com.fengdu.dao.TOrderDao;
import com.fengdu.entity.TOrderEntity;
import com.fengdu.service.TOrderService;
import com.fengdu.utils.DateUtils;
import com.fengdu.utils.ShiroUtils;

/**
 * 订单表Service实现类
 *
 * @author william_w
 * @email 2366207000@qq.com
 * @date 2018-06-30 14:08:57
 */
@Service("tOrderService")
public class TOrderServiceImpl implements TOrderService {
	@Autowired
	private TOrderDao tOrderDao;

	@Override
	public TOrderEntity queryObject(Long id) {
		return tOrderDao.queryObject(id);
	}

	@Override
	public List<TOrderEntity> queryList(Map<String, Object> map) {
    	map.put(Constants.MERCHANT_ID, ShiroUtils.getUserId());
		return tOrderDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
    	map.put(Constants.MERCHANT_ID, ShiroUtils.getUserId());
		return tOrderDao.queryTotal(map);
	}

	@Override
	public int save(TOrderEntity tOrder) {
		tOrder.setCreateTime(DateUtils.currentDate());
		tOrder.setCreator(ShiroUtils.getUserName());
		if(null == tOrder.getMerchantId()){
			tOrder.setMerchantId(ShiroUtils.getUserId());
		}
		return tOrderDao.save(tOrder);
	}

	@Override
	public int update(TOrderEntity tOrder) {
		tOrder.setModifyTime(DateUtils.currentDate());
		tOrder.setModifier(ShiroUtils.getUserName());
		return tOrderDao.update(tOrder);
	}

	@Override
	public int delete(Long id) {
		return tOrderDao.delete(id);
	}

	@Override
	public int deleteBatch(Long[] ids) {
		return tOrderDao.deleteBatch(ids);
	}

	@Override
	public int updateStatus(Long id) {
		TOrderEntity entity = this.queryObject(id);
		// 0取消订单 1已交定金
		entity.setOrderStatus(OrderStatusEnum.ORDER_CANCLE.getCode());
		return update(entity);
	}
}
