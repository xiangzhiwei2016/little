package com.fengdu.dao;

import com.fengdu.entity.TMerchantEntity;

/**
 * 商户表Dao
 *
 * @author william_w
 * @email 2366207000@qq.com
 * @date 2018-06-27 17:00:44
 */
public interface TMerchantDao extends BaseDao<TMerchantEntity> {
	 int queryMaxId();
}
