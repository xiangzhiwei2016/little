package com.fengdu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fengdu.constants.Constants;
import com.fengdu.constants.ImageTypeEnum;
import com.fengdu.dao.TEvaluateDao;
import com.fengdu.entity.TEvaluateEntity;
import com.fengdu.entity.TImageEntity;
import com.fengdu.service.TEvaluateService;
import com.fengdu.service.TImageService;
import com.fengdu.utils.DateUtils;
import com.fengdu.utils.ShiroUtils;

/**
 * 评价表Service实现类
 *
 * @author william_w
 * @email 2366207000@qq.com
 * @date 2018-06-27 16:52:11
 */
@Service("tEvaluateService")
public class TEvaluateServiceImpl implements TEvaluateService {
    @Autowired
    private TEvaluateDao tEvaluateDao;
    
    
    @Autowired
    private TImageService tImageService;

    @Override
    public TEvaluateEntity queryObject(Long id) {
        return tEvaluateDao.queryObject(id);
    }

    @Override
    public List<TEvaluateEntity> queryList(Map<String, Object> map) {
    	map.put(Constants.MERCHANT_ID, ShiroUtils.getUserId());
        return tEvaluateDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
    	map.put(Constants.MERCHANT_ID, ShiroUtils.getUserId());
        return tEvaluateDao.queryTotal(map);
    }

    @Override
    public int save(TEvaluateEntity tEvaluate) {
    	tEvaluate.setCreateTime(DateUtils.currentDate());
    	tEvaluate.setCreator(ShiroUtils.getUserName());
        return tEvaluateDao.save(tEvaluate);
    }

    @Override
    public int update(TEvaluateEntity tEvaluate) {
    	tEvaluate.setModifyTime(DateUtils.currentDate());
    	tEvaluate.setModifier(ShiroUtils.getUserName());
        return tEvaluateDao.update(tEvaluate);
    }

    @Override
    public int delete(Long id) {
        return tEvaluateDao.delete(id);
    }

    @Override
    public int deleteBatch(Long[]ids) {
        return tEvaluateDao.deleteBatch(ids);
    }
    
    @Override
    public List<TImageEntity> queryImages(Long id) {
    	Map<String,Object> map  = new HashMap<String,Object>();
    	map.put(Constants.IMAGE_KEY, id);
    	map.put(Constants.IMAGE_TYPE, ImageTypeEnum.EVALUATE.getCode());
    	List<TImageEntity> oldImage = tImageService.queryList(map);
    	return oldImage;
    }
}
