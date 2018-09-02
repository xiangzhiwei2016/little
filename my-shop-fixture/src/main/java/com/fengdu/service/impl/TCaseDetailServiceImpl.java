package com.fengdu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fengdu.constants.Constants;
import com.fengdu.constants.ImageTypeEnum;
import com.fengdu.dao.TCaseDetailDao;
import com.fengdu.entity.TCaseDetailEntity;
import com.fengdu.entity.TCaseEntity;
import com.fengdu.entity.TImageEntity;
import com.fengdu.service.TCaseDetailService;
import com.fengdu.service.TCaseService;
import com.fengdu.service.TImageService;
import com.fengdu.utils.DateUtils;
import com.fengdu.utils.ShiroUtils;

/**
 * 案例详情表Service实现类
 *
 * @author william_w
 * @email 2366207000@qq.com
 * @date 2018-07-07 11:28:31
 */
@Service("tCaseDetailService")
public class TCaseDetailServiceImpl implements TCaseDetailService {
	@Autowired
	private TCaseDetailDao tCaseDetailDao;

	@Autowired
	private TImageService tImageService;
	
	@Autowired
	private TCaseService tCaseService;

	@Override
	public TCaseDetailEntity queryObject(Long id) {
		return tCaseDetailDao.queryObject(id);
	}

	@Override
	public List<TCaseDetailEntity> queryList(Map<String, Object> map) {
		map.put(Constants.MERCHANT_ID, ShiroUtils.getUserId());
		return tCaseDetailDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		map.put(Constants.MERCHANT_ID, ShiroUtils.getUserId());
		return tCaseDetailDao.queryTotal(map);
	}

	@Override
	public int save(TCaseDetailEntity tCaseDetail) {
		tCaseDetail.setMerchantId(ShiroUtils.getUserId());
		tCaseDetail.setCreateTime(DateUtils.currentDate());
		tCaseDetail.setCreator(ShiroUtils.getUserName());
		int count = tCaseDetailDao.save(tCaseDetail);
		// 商品图
		List<TImageEntity> imageList = tCaseDetail.getImgList();
		if (null != imageList && !imageList.isEmpty()) {
			tImageService.saveImages(tCaseDetail.getId(), imageList,
					ImageTypeEnum.DETAILS.getCode());
		}
		return count;
	}

	@Override
	public int update(TCaseDetailEntity tCaseDetail) {
		tCaseDetail.setModifyTime(DateUtils.currentDate());
		tCaseDetail.setModifier(ShiroUtils.getUserName());
		List<TImageEntity> imageList = tCaseDetail.getImgList();
		if (null != imageList && !imageList.isEmpty()) {
			// 商品图
			tImageService.saveImages(tCaseDetail.getId(), imageList,
					ImageTypeEnum.DETAILS.getCode());
		}
		return tCaseDetailDao.update(tCaseDetail);
	}

	@Override
	public int delete(Long id) {
		return tCaseDetailDao.delete(id);
	}

	@Override
	public int deleteBatch(Long[] ids) {
		return tCaseDetailDao.deleteBatch(ids);
	}

	@Override
	public List<TImageEntity> queryImages(Long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constants.IMAGE_KEY, id);
		map.put(Constants.IMAGE_TYPE, ImageTypeEnum.DETAILS.getCode());
		List<TImageEntity> images = tImageService.queryList(map);
		return images;
	}
	
	@Override
	public List<TCaseEntity> queryAllCases(Map<String, Object> map) {
		return tCaseService.queryList(map);
	}
	
	
}
