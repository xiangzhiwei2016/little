package com.fengdu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fengdu.constants.Constants;
import com.fengdu.dao.TImageDao;
import com.fengdu.entity.TImageEntity;
import com.fengdu.service.TImageService;
import com.fengdu.utils.ShiroUtils;

/**
 * 图片表Service实现类
 *
 * @author william_w
 * @email 2366207000@qq.com
 * @date 2018-06-30 22:33:00
 */
@Service("tImageService")
public class TImageServiceImpl implements TImageService {
    @Autowired
    private TImageDao tImageDao;

    @Override
    public TImageEntity queryObject(Long id) {
        return tImageDao.queryObject(id);
    }

    @Override
    public List<TImageEntity> queryList(Map<String, Object> map) {
    	map.put(Constants.MERCHANT_ID, ShiroUtils.getUserId());
        return tImageDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
    	map.put(Constants.MERCHANT_ID, ShiroUtils.getUserId());
        return tImageDao.queryTotal(map);
    }

    @Override
    public int save(TImageEntity tImage) {
    	tImage.setMerchantId(ShiroUtils.getUserId());
        return tImageDao.save(tImage);
    }

    @Override
    public int update(TImageEntity tImage) {
        return tImageDao.update(tImage);
    }

    @Override
    public int delete(Long id) {
        return tImageDao.delete(id);
    }

    @Override
    public int deleteBatch(Long[]ids) {
        return tImageDao.deleteBatch(ids);
    }
    
    @Override
    public void saveImages(Long id,List<TImageEntity> images,String type) {
    	// 先删掉
    	deleteImages(id,type);
    	for(TImageEntity image : images){
    		image.setIkey(id);
    		image.setType(type);
        	save(image);
    	}
    }
    
    public void deleteImages(Long id,String type) {
    	Map<String,Object> map  = new HashMap<String,Object>();
    	map.put(Constants.IMAGE_KEY, id);
    	map.put(Constants.IMAGE_TYPE, type);
    	List<TImageEntity> imageList = queryList(map);
    	for(TImageEntity entity : imageList){
    		delete(entity.getId());
    	}
    }
}
