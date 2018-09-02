package com.fengdu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.fengdu.dao.NideshopGoodsSpecificationDao;
import com.fengdu.entity.NideshopGoodsSpecificationEntity;
import com.fengdu.service.NideshopGoodsSpecificationService;

/**
 * 商品对应规格表值表Service实现类
 *
 * @author william_w
 * @email 2366207000@qq.com
 * @date 2018-06-24 10:30:31
 */
@Service("nideshopGoodsSpecificationService")
public class NideshopGoodsSpecificationServiceImpl implements NideshopGoodsSpecificationService {
    @Autowired
    private NideshopGoodsSpecificationDao nideshopGoodsSpecificationDao;

    @Override
    public NideshopGoodsSpecificationEntity queryObject(Integer id) {
        return nideshopGoodsSpecificationDao.queryObject(id);
    }

    
    @Override
    public List<NideshopGoodsSpecificationEntity> queryList(Map<String, Object> map) {
        return nideshopGoodsSpecificationDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return nideshopGoodsSpecificationDao.queryTotal(map);
    }

    @Override
    public int save(NideshopGoodsSpecificationEntity nideshopGoodsSpecification) {
        return nideshopGoodsSpecificationDao.save(nideshopGoodsSpecification);
    }

    @Override
    public int update(NideshopGoodsSpecificationEntity nideshopGoodsSpecification) {
        return nideshopGoodsSpecificationDao.update(nideshopGoodsSpecification);
    }

    @Override
    public int delete(Integer id) {
        return nideshopGoodsSpecificationDao.delete(id);
    }

    @Override
    public int deleteBatch(Integer[]ids) {
        return nideshopGoodsSpecificationDao.deleteBatch(ids);
    }
}
