package com.fengdu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fengdu.constants.Constants;
import com.fengdu.constants.ImageTypeEnum;
import com.fengdu.dao.TProductDao;
import com.fengdu.entity.TImageEntity;
import com.fengdu.entity.TProductEntity;
import com.fengdu.service.TImageService;
import com.fengdu.service.TProductService;
import com.fengdu.utils.DateUtils;
import com.fengdu.utils.ShiroUtils;

/**
 * 商品表Service实现类
 *
 * @author william_w
 * @email 2366207000@qq.com
 * @date 2018-06-27 14:55:47
 */
@Service("tProductService")
public class TProductServiceImpl implements TProductService {
	@Autowired
	private TProductDao tProductDao;

	@Autowired
	private TImageService tImageService;

	@Override
	public TProductEntity queryObject(Long id) {
		return tProductDao.queryObject(id);
	}

	@Override
	public List<TProductEntity> queryList(Map<String, Object> map) {
		map.put(Constants.MERCHANT_ID, ShiroUtils.getUserId());
		return tProductDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		map.put(Constants.MERCHANT_ID, ShiroUtils.getUserId());
		return tProductDao.queryTotal(map);
	}

	@Override
	public int save(TProductEntity tProduct) {
		tProduct.setMerchantId(ShiroUtils.getUserId());
		tProduct.setCreateTime(DateUtils.currentDate());
		tProduct.setCreator(ShiroUtils.getUserName());
		// 商品图
		int count = tProductDao.save(tProduct);
		List<TImageEntity> imageList = tProduct.getImgList();
    	if(null != imageList && !imageList.isEmpty()){
        	tImageService.saveImages(tProduct.getId(), imageList, ImageTypeEnum.PRODUCT.getCode());
    	}
		return count;
	}

	@Override
	public int update(TProductEntity tProduct) {
		tProduct.setModifyTime(DateUtils.currentDate());
		tProduct.setModifier(ShiroUtils.getUserName());
		List<TImageEntity> imageList = tProduct.getImgList();
    	if(null != imageList && !imageList.isEmpty()){
    		// 商品图
        	tImageService.saveImages(tProduct.getId(), imageList, ImageTypeEnum.PRODUCT.getCode());
    	}
		return tProductDao.update(tProduct);
	}

	@Override
	public int delete(Long id) {
		return tProductDao.delete(id);
	}

	@Override
	public int deleteBatch(Long[] ids) {
		return tProductDao.deleteBatch(ids);
	}
	
	 @Override
    public List<TImageEntity> queryImages(Long id) {
    	Map<String,Object> map  = new HashMap<String,Object>();
    	map.put(Constants.IMAGE_KEY, id);
    	map.put(Constants.IMAGE_TYPE, ImageTypeEnum.PRODUCT.getCode());
    	List<TImageEntity> images = tImageService.queryList(map);
    	return images;
    }
}
