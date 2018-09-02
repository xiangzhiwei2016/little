package com.fengdu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.fengdu.constants.Constants;
import com.fengdu.dao.TCategoryDao;
import com.fengdu.entity.TCategoryEntity;
import com.fengdu.service.TCategoryService;
import com.fengdu.utils.DateUtils;
import com.fengdu.utils.ShiroUtils;

/**
 * 类别表Service实现类
 *
 * @author william_w
 * @email 2366207000@qq.com
 * @date 2018-06-27 16:03:53
 */
@Service("tCategoryService")
public class TCategoryServiceImpl implements TCategoryService {
    @Autowired
    private TCategoryDao tCategoryDao;

    @Override
    public TCategoryEntity queryObject(Long id) {
        return tCategoryDao.queryObject(id);
    }

    @Override
    public List<TCategoryEntity> queryList(Map<String, Object> map) {
    	map.put(Constants.MERCHANT_ID, ShiroUtils.getUserId());
        return tCategoryDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
    	map.put(Constants.MERCHANT_ID, ShiroUtils.getUserId());
        return tCategoryDao.queryTotal(map);
    }

    @Override
    public int save(TCategoryEntity tCategory) {
    	tCategory.setCreateTime(DateUtils.currentDate());
    	tCategory.setCreator(ShiroUtils.getUserName());
    	tCategory.setMerchantId(ShiroUtils.getUserId());
        return tCategoryDao.save(tCategory);
    }

    @Override
    public int update(TCategoryEntity tCategory) {
		tCategory.setModifyTime(DateUtils.currentDate());
		tCategory.setModifier(ShiroUtils.getUserName());
        return tCategoryDao.update(tCategory);
    }

    @Override
    public int delete(Long id) {
        return tCategoryDao.delete(id);
    }

    @Override
    public int deleteBatch(Long[]ids) {
        return tCategoryDao.deleteBatch(ids);
    }
}
