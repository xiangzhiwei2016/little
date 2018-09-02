package com.fengdu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.fengdu.dao.TProductTestDao;
import com.fengdu.entity.TProductTestEntity;
import com.fengdu.service.TProductTestService;

/**
 * Service实现类
 *
 * @author william_w
 * @email 2366207000@qq.com
 * @date 2018-06-24 11:03:19
 */
@Service("tProductTestService")
public class TProductTestServiceImpl implements TProductTestService {
    @Autowired
    private TProductTestDao tProductTestDao;

    @Override
    public TProductTestEntity queryObject(Long id) {
        return tProductTestDao.queryObject(id);
    }

    @Override
    public List<TProductTestEntity> queryList(Map<String, Object> map) {
        return tProductTestDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return tProductTestDao.queryTotal(map);
    }

    @Override
    public int save(TProductTestEntity tProductTest) {
        return tProductTestDao.save(tProductTest);
    }

    @Override
    public int update(TProductTestEntity tProductTest) {
        return tProductTestDao.update(tProductTest);
    }

    @Override
    public int delete(Long id) {
        return tProductTestDao.delete(id);
    }

    @Override
    public int deleteBatch(Long[]ids) {
        return tProductTestDao.deleteBatch(ids);
    }
}
