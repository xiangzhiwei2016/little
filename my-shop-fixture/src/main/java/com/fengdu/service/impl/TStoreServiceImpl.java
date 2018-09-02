package com.fengdu.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.fengdu.constants.Constants;
import com.fengdu.constants.ImageTypeEnum;
import com.fengdu.dao.TStoreDao;
import com.fengdu.entity.TImageEntity;
import com.fengdu.entity.TStoreEntity;
import com.fengdu.service.TImageService;
import com.fengdu.service.TStoreService;
import com.fengdu.utils.DateUtils;
import com.fengdu.utils.GetLatAndLngByBaiduUtils;
import com.fengdu.utils.ShiroUtils;
import com.fengdu.utils.StringUtils;
import com.google.gson.JsonObject;

/**
 * 门店表Service实现类
 *
 * @author william_w
 * @email 2366207000@qq.com
 * @date 2018-06-30 21:50:02
 */
@Service("tStoreService")
public class TStoreServiceImpl implements TStoreService {
	private static final Logger logger = LoggerFactory.getLogger(TStoreServiceImpl.class);
    @Autowired
    private TStoreDao tStoreDao;
    
    @Autowired
    private TImageService tImageService;
    
    @Override
    public TStoreEntity queryObject(Long id) {
        return tStoreDao.queryObject(id);
    }

    @Override
    public List<TStoreEntity> queryList(Map<String, Object> map) {
    	map.put(Constants.MERCHANT_ID, ShiroUtils.getUserId());
        return tStoreDao.queryList(map);
    }

    @Override
    public List<TImageEntity> queryImages(Long id) {
    	Map<String,Object> map  = new HashMap<String,Object>();
    	map.put(Constants.IMAGE_KEY, id);
    	map.put(Constants.IMAGE_TYPE, ImageTypeEnum.STORE.getCode());
    	List<TImageEntity> oldImage = tImageService.queryList(map);
    	return oldImage;
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
    	map.put(Constants.MERCHANT_ID, ShiroUtils.getUserId());
        return tStoreDao.queryTotal(map);
    }

    @Override
    public int save(TStoreEntity tStore) {
    	tStore.setCreateTime(DateUtils.currentDate());
    	tStore.setCreator(ShiroUtils.getUserName());
    	tStore.setMerchantId(ShiroUtils.getUserId());
    	String address = tStore.getAddress();
    	if(StringUtils.isNotEmpty(address)){
    		String result = GetLatAndLngByBaiduUtils.getLarLng(address);
    		JSONObject object = JSONObject.parseObject(result);
    		tStore.setLng(object.getString("lng"));
    		tStore.setLat(object.getString("lat"));
    	}
    	int count = tStoreDao.save(tStore);
    	List<TImageEntity> imageList = tStore.getImgList();
    	// 图片保存
    	if(null != imageList && !imageList.isEmpty()){
        	tImageService.saveImages(tStore.getId(), imageList, ImageTypeEnum.STORE.getCode());
    	}
        return count;
    }

    @Override
    public int update(TStoreEntity tStore) {
    	tStore.setModifyTime(DateUtils.currentDate());
    	tStore.setModifier(ShiroUtils.getUserName());
    	String address = tStore.getAddress();
    	if(StringUtils.isNotEmpty(address)){
    		String result = GetLatAndLngByBaiduUtils.getLarLng(address);
    		JSONObject object = JSONObject.parseObject(result);
    		tStore.setLng(object.getString("lng"));
    		tStore.setLat(object.getString("lat"));
    	}
    	int count = tStoreDao.update(tStore);
    	List<TImageEntity> imageList = tStore.getImgList();
    	// 图片保存
    	if(null != imageList && !imageList.isEmpty()){
        	tImageService.saveImages(tStore.getId(), imageList, ImageTypeEnum.STORE.getCode());
    	}
        return count;
    }

    @Override
    public int delete(Long id) {
        return tStoreDao.delete(id);
    }

    @Override
    public int deleteBatch(Long[]ids) {
        return tStoreDao.deleteBatch(ids);
    }
}
