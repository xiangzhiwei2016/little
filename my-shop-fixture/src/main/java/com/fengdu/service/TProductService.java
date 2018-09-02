package com.fengdu.service;

import com.fengdu.entity.TImageEntity;
import com.fengdu.entity.TProductEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品表Service接口
 *
 * @author william_w
 * @email 2366207000@qq.com
 * @date 2018-06-27 14:55:47
 */
public interface TProductService {

    /**
     * 根据主键查询实体
     *
     * @param id 主键
     * @return 实体
     */
    TProductEntity queryObject(Long id);

    /**
     * 分页查询
     *
     * @param map 参数
     * @return list
     */
    List<TProductEntity> queryList(Map<String, Object> map);

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
     * @param tProduct 实体
     * @return 保存条数
     */
    int save(TProductEntity tProduct);

    /**
     * 根据主键更新实体
     *
     * @param tProduct 实体
     * @return 更新条数
     */
    int update(TProductEntity tProduct);

    /**
     * 根据主键删除
     *
     * @param id
     * @return 删除条数
     */
    int delete(Long id);

    /**
     * 根据主键批量删除
     *
     * @param ids
     * @return 删除条数
     */
    int deleteBatch(Long[]ids);

    /**
     * 通过id获取所有的图片
     * @param id
     * @return
     */
	List<TImageEntity> queryImages(Long id);
}
