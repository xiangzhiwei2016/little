package com.fengdu.service;

import com.fengdu.entity.TImageEntity;
import com.fengdu.entity.TStoreEntity;

import java.util.List;
import java.util.Map;

/**
 * 门店表Service接口
 *
 * @author william_w
 * @email 2366207000@qq.com
 * @date 2018-06-30 21:50:02
 */
public interface TStoreService {

    /**
     * 根据主键查询实体
     *
     * @param id 主键
     * @return 实体
     */
    TStoreEntity queryObject(Long id);

    /**
     * 分页查询
     *
     * @param map 参数
     * @return list
     */
    List<TStoreEntity> queryList(Map<String, Object> map);

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
     * @param tStore 实体
     * @return 保存条数
     */
    int save(TStoreEntity tStore);

    /**
     * 根据主键更新实体
     *
     * @param tStore 实体
     * @return 更新条数
     */
    int update(TStoreEntity tStore);

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
     * 查看所有图片
     * @param id
     * @return
     */
	List<TImageEntity> queryImages(Long id);
}
