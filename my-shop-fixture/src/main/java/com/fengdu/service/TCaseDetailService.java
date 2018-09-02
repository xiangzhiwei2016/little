package com.fengdu.service;

import com.fengdu.entity.TCaseDetailEntity;
import com.fengdu.entity.TCaseEntity;
import com.fengdu.entity.TImageEntity;

import java.util.List;
import java.util.Map;

/**
 * 案例详情表Service接口
 *
 * @author william_w
 * @email 2366207000@qq.com
 * @date 2018-07-07 11:28:31
 */
public interface TCaseDetailService {

    /**
     * 根据主键查询实体
     *
     * @param id 主键
     * @return 实体
     */
    TCaseDetailEntity queryObject(Long id);

    /**
     * 分页查询
     *
     * @param map 参数
     * @return list
     */
    List<TCaseDetailEntity> queryList(Map<String, Object> map);

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
     * @param tCaseDetail 实体
     * @return 保存条数
     */
    int save(TCaseDetailEntity tCaseDetail);

    /**
     * 根据主键更新实体
     *
     * @param tCaseDetail 实体
     * @return 更新条数
     */
    int update(TCaseDetailEntity tCaseDetail);

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

	List<TCaseEntity> queryAllCases(Map<String, Object> map);
}
