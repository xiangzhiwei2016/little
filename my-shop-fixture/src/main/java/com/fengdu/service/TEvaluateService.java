package com.fengdu.service;

import com.fengdu.entity.TEvaluateEntity;
import com.fengdu.entity.TImageEntity;

import java.util.List;
import java.util.Map;

/**
 * 评价表Service接口
 *
 * @author william_w
 * @email 2366207000@qq.com
 * @date 2018-06-27 16:52:11
 */
public interface TEvaluateService {

    /**
     * 根据主键查询实体
     *
     * @param id 主键
     * @return 实体
     */
    TEvaluateEntity queryObject(Long id);

    /**
     * 分页查询
     *
     * @param map 参数
     * @return list
     */
    List<TEvaluateEntity> queryList(Map<String, Object> map);

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
     * @param tEvaluate 实体
     * @return 保存条数
     */
    int save(TEvaluateEntity tEvaluate);

    /**
     * 根据主键更新实体
     *
     * @param tEvaluate 实体
     * @return 更新条数
     */
    int update(TEvaluateEntity tEvaluate);

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

	List<TImageEntity> queryImages(Long id);
}
