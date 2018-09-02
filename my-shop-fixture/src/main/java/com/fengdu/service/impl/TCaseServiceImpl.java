package com.fengdu.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fengdu.constants.Constants;
import com.fengdu.dao.TCaseDao;
import com.fengdu.entity.TCaseDetailEntity;
import com.fengdu.entity.TCaseEntity;
import com.fengdu.entity.TImageEntity;
import com.fengdu.service.TCaseDetailService;
import com.fengdu.service.TCaseService;
import com.fengdu.utils.DateUtils;
import com.fengdu.utils.ShiroUtils;

/**
 * 案例表Service实现类
 *
 * @author william_w
 * @email 2366207000@qq.com
 * @date 2018-06-27 15:44:39
 */
@Service("tCaseService")
public class TCaseServiceImpl implements TCaseService {
	@Autowired
	private TCaseDao tCaseDao;

	@Autowired
	private TCaseDetailService tCaseDetailService;

	@Override
	public TCaseEntity queryObject(Long id) {
		TCaseEntity case1 = tCaseDao.queryObject(id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constants.MERCHANT_ID, ShiroUtils.getUserId());
		List<TCaseDetailEntity> tcaseDetailList = tCaseDetailService
				.queryList(map);
		for (TCaseDetailEntity tcaseDetail : tcaseDetailList) {
			if (id.equals(tcaseDetail.getCaseId())) {
				tcaseDetail.setUploadDetailList(tCaseDetailService.queryImages(tcaseDetail.getId()));
				case1.getCaseDetailList().add(tcaseDetail);
			}
		}
		return case1;
	}

	@Override
	public List<TCaseEntity> queryList(Map<String, Object> map) {
		map.put(Constants.MERCHANT_ID, ShiroUtils.getUserId());
		List<TCaseEntity> tcaseList = tCaseDao.queryList(map);
		List<TCaseDetailEntity> tcaseDetailList = tCaseDetailService
				.queryList(map);
		for (TCaseEntity tcase : tcaseList) {
			for (TCaseDetailEntity tcaseDetail : tcaseDetailList) {
				if (tcase.getId().equals(tcaseDetail.getCaseId())) {
					tcaseDetail.setUploadDetailList(tcaseDetail.getImgList());
					tcase.getCaseDetailList().add(tcaseDetail);
				}
			}
		}
		return tcaseList;
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		map.put(Constants.MERCHANT_ID, ShiroUtils.getUserId());
		return tCaseDao.queryTotal(map);
	}

	@Override
	public int save(TCaseEntity tCase) {
		tCase.setCreateTime(DateUtils.currentDate());
		tCase.setCreator(ShiroUtils.getUserName());
		tCase.setMerchantId(ShiroUtils.getUserId());
		List<TCaseDetailEntity> caseDetailList = tCase.getCaseDetailList();

		List<TImageEntity> imageList = tCase.getImgList();
		for (TImageEntity image : imageList) {
			tCase.setImgUrl(image.getImgUrl());
		}
		int count = tCaseDao.save(tCase);
		for (TCaseDetailEntity caseDetail : caseDetailList) {
			caseDetail.setCaseId(tCase.getId());
			caseDetail.setImgList(caseDetail.getUploadDetailList());
			tCaseDetailService.save(caseDetail);
		}
		return count;
	}

	@Override
	public int update(TCaseEntity tCase) {
		tCase.setModifyTime(DateUtils.currentDate());
		tCase.setModifier(ShiroUtils.getUserName());
		tCase.setMerchantId(ShiroUtils.getUserId());
		List<TImageEntity> imageList = tCase.getImgList();
		for (TImageEntity image : imageList) {
			tCase.setImgUrl(image.getImgUrl());
		}
		List<TCaseDetailEntity> caseDetailList = tCase.getCaseDetailList();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(Constants.MERCHANT_ID, ShiroUtils.getUserId());
		// 先删除
		List<TCaseDetailEntity> tcaseDetailList = tCaseDetailService
				.queryList(map);
		for (TCaseDetailEntity tcaseDetail : tcaseDetailList) {
			if (tCase.getId().equals(tcaseDetail.getCaseId())) {
				tCaseDetailService.delete(tcaseDetail.getId());
			}
		}
		for (TCaseDetailEntity caseDetail : caseDetailList) {
			caseDetail.setCaseId(tCase.getId());
			caseDetail.setImgList(caseDetail.getUploadDetailList());
			tCaseDetailService.save(caseDetail);
		}
		return tCaseDao.update(tCase);
	}

	@Override
	public int delete(Long id) {
		return tCaseDao.delete(id);
	}

	@Override
	public int deleteBatch(Long[] ids) {
		return tCaseDao.deleteBatch(ids);
	}

	@Override
	public List<TImageEntity> queryImages(Long id) {
		List<TImageEntity> images = new ArrayList<TImageEntity>();
		TCaseEntity entity = queryObject(id);
		if (null != entity && StringUtils.isNotBlank(entity.getImgUrl())) {
			TImageEntity image = new TImageEntity();
			image.setImgUrl(entity.getImgUrl());
			images.add(image);
		}
		return images;
	}
}
