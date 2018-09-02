package com.fengdu.service;

import com.fengdu.entity.TImageEntity;

import java.util.List;
import java.util.Map;

/**
 * 图片表Service接口
 *
 * @author william_w
 * @email 2366207000@qq.com
 * @date 2018-06-30 22:33:00
 */
public interface TImageService {

    /**
     * 根据主键查询实体
     *
     * @param id 主键
     * @return 实体
     */
    TImageEntity queryObject(Long id);

    /**
     * 分页查询
     *
     * @param map 参数
     * @return list
     */
    List<TImageEntity> queryList(Map<String, Object> map);

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
     * @param tImage 实体
     * @return 保存条数
     */
    int save(TImageEntity tImage);

    /**
     * 根据主键更新实体
     *
     * @param tImage 实体
     * @return 更新条数
     */
    int update(TImageEntity tImage);

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
     * 批量保存图片
     * @param id
     * @param images
     * @param type
     */
	void saveImages(Long id, List<TImageEntity> images, String type);
}
