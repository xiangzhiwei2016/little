package com.fengdu.service;

import com.fengdu.entity.NideshopGoodsSpecificationEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品对应规格表值表Service接口
 *
 * @author william_w
 * @email 2366207000@qq.com
 * @date 2018-06-24 10:30:31
 */
public interface NideshopGoodsSpecificationService {

    /**
     * 根据主键查询实体
     *
     * @param id 主键
     * @return 实体
     */
    NideshopGoodsSpecificationEntity queryObject(Integer id);

    /**
     * 分页查询
     *
     * @param map 参数
     * @return list
     */
    List<NideshopGoodsSpecificationEntity> queryList(Map<String, Object> map);

    /**
     * 分页统计总数
     *
     * @param map 参数
     * @return 总数
     */
    int queryTotal(Map<String, Object> map);

    /**
     * 保存实体
     *
     * @param nideshopGoodsSpecification 实体
     * @return 保存条数
     */
    int save(NideshopGoodsSpecificationEntity nideshopGoodsSpecification);

    /**
     * 根据主键更新实体
     *
     * @param nideshopGoodsSpecification 实体
     * @return 更新条数
     */
    int update(NideshopGoodsSpecificationEntity nideshopGoodsSpecification);

    /**
     * 根据主键删除
     *
     * @param id
     * @return 删除条数
     */
    int delete(Integer id);

    /**
     * 根据主键批量删除
     *
     * @param ids
     * @return 删除条数
     */
    int deleteBatch(Integer[]ids);
}
